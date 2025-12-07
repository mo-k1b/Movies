package com.example.movies.controller;

import com.example.movies.model.Favorite;
import com.example.movies.model.MovieDto;
import com.example.movies.model.User;
import com.example.movies.repository.FavoriteRepository;
import com.example.movies.repository.UserRepository;
import com.example.movies.service.MovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MovieController {

    private final MovieService movieService;
    private final UserRepository userRepository;
    private final FavoriteRepository favoriteRepository;

    public MovieController(MovieService movieService, UserRepository userRepository,
            FavoriteRepository favoriteRepository) {
        this.movieService = movieService;
        this.userRepository = userRepository;
        this.favoriteRepository = favoriteRepository;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        System.out.println("MovieController.index called");
        // Dashboard view
        model.addAttribute("topRated", movieService.getTopRatedMovies(20));
        model.addAttribute("latest", movieService.getLatestMovies(20));

        // Use all genres for the dropdown/list
        model.addAttribute("genres", movieService.getAllGenres());

        User user = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", user);

        if (user != null) {
            List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());
            List<MovieDto> favMovies = new ArrayList<>();
            for (Favorite fav : favorites) {
                MovieDto m = movieService.getMovieById(fav.getMovieId());
                if (m != null)
                    favMovies.add(m);
            }
            // Limit favs on home page
            model.addAttribute("favorites", favMovies.stream().limit(20).collect(Collectors.toList()));
        }

        return "index";
    }

    @GetMapping("/browse")
    public String browse(Model model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String search,
            HttpSession session) {

        List<MovieDto> movies;
        String title = "All Movies";

        if (search != null && !search.isBlank()) {
            movies = movieService.searchMovies(search);
            title = "Results for: " + search;
        } else if ("top".equals(type)) {
            movies = movieService.getTopRatedMovies(120); // Increased from 100
            title = "Top Rated Movies";
        } else if ("latest".equals(type)) {
            movies = movieService.getLatestMovies(120); // Increased from 100
            title = "Latest Releases";
        } else if (genre != null) {
            movies = movieService.getMoviesByGenre(genre);
            title = "Genre: " + genre;
        } else {
            movies = movieService.getAllMovies();
        }

        model.addAttribute("movies", movies);
        model.addAttribute("pageTitle", title);
        model.addAttribute("loggedInUser", session.getAttribute("user"));

        return "browse";
    }

    @GetMapping("/genres")
    public String genres(Model model, HttpSession session) {
        model.addAttribute("genres", movieService.getAllGenres());
        model.addAttribute("loggedInUser", session.getAttribute("user"));
        return "genres";
    }

    @GetMapping("/movie/{id}")
    public String movieDetails(@PathVariable int id, Model model, HttpSession session) {
        System.out.println("MovieController.movieDetails called for ID: " + id);
        MovieDto movie = movieService.getMovieById(id);
        if (movie == null)
            System.out.println("Movie is NULL for ID: " + id);
        model.addAttribute("movie", movie);

        User user = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", user);

        if (user != null) {
            boolean isFavorite = favoriteRepository.existsByUserIdAndMovieId(user.getId(), id);
            model.addAttribute("isFavorite", isFavorite);
        }

        return "details";
    }

    @GetMapping("/favorites")
    public String favorites(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/login";

        List<Favorite> favorites = favoriteRepository.findByUserId(user.getId());
        List<MovieDto> favMovies = new ArrayList<>();

        for (Favorite fav : favorites) {
            MovieDto m = movieService.getMovieById(fav.getMovieId());
            if (m != null)
                favMovies.add(m);
        }

        model.addAttribute("movies", favMovies);
        model.addAttribute("pageTitle", "My Favorites");
        model.addAttribute("loggedInUser", user);
        return "browse"; // Use browse template to properly display favorites
    }

    @GetMapping("/favorites/add/{id}")
    public String addFavorite(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/login";

        if (!favoriteRepository.existsByUserIdAndMovieId(user.getId(), id)) {
            favoriteRepository.save(new Favorite(user.getId(), id));
        }
        return "redirect:/movie/" + id;
    }

    @PostMapping("/movie/{id}/favorite")
    public String toggleFavorite(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null)
            return "redirect:/login";

        // Check if already a favorite
        Favorite existing = favoriteRepository.findByUserId(user.getId()).stream()
                .filter(f -> f.getMovieId() == id)
                .findFirst()
                .orElse(null);

        if (existing != null) {
            // Remove from favorites
            favoriteRepository.delete(existing);
        } else {
            // Add to favorites
            favoriteRepository.save(new Favorite(user.getId(), id));
        }

        return "redirect:/movie/" + id;
    }

    @GetMapping("/favorites/remove/{id}")
    public String removeFavorite(@PathVariable int id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            Favorite fav = favoriteRepository.findByUserId(user.getId()).stream()
                    .filter(f -> f.getMovieId() == id).findFirst().orElse(null);
            if (fav != null)
                favoriteRepository.delete(fav);
        }
        return "redirect:/movie/" + id;
    }
}
