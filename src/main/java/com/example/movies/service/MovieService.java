package com.example.movies.service;

import com.example.movies.model.MovieDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class for managing movie data operations.
 * <p>
 * This service handles all movie-related business logic including:
 * <ul>
 * <li>Fetching movies from local JSON resources and external APIs</li>
 * <li>Caching movie data for improved performance</li>
 * <li>Searching and filtering movies by various criteria</li>
 * <li>Providing sorted lists (top-rated, latest, by genre)</li>
 * </ul>
 * </p>
 * <p>
 * The service implements an in-memory caching strategy with a 1-hour TTL
 * to reduce API calls and improve response times. It falls back to a
 * curated list of movies if external data sources are unavailable.
 * </p>
 *
 * @author Movies Application
 * @version 1.0
 * @since 1.0
 */
@Service
public class MovieService {

    private final RestTemplate restTemplate;
    // TVMaze Shows API - reliable and free
    private final String API_URL = "https://api.tvmaze.com/shows";

    // Simple in-memory cache
    private List<MovieDto> cachedMovies = new ArrayList<>();
    private long lastFetchTime = 0;
    private static final long CACHE_DURATION = 1000 * 60 * 60; // 1 hour

    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

    public MovieService(RestTemplateBuilder builder) {
        this.restTemplate = builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    public List<MovieDto> getAllMovies() {
        System.out.println("MovieService.getAllMovies called. Cached: " + cachedMovies.size());
        if (!cachedMovies.isEmpty() && (System.currentTimeMillis() - lastFetchTime < CACHE_DURATION)) {
            return new ArrayList<>(cachedMovies);
        }
        return fetchMoviesFromApi();
    }

    private List<MovieDto> fetchMoviesFromApi() {
        System.out.println("MovieService.fetchMoviesFromApi called.");
        try {
            // Load from local resources instead of external API for stability and quality
            java.io.InputStream inputStream = getClass().getResourceAsStream("/movies.json");
            if (inputStream == null) {
                System.err.println("movies.json not found!");
                return getFallbackMovies();
            }

            MovieDto[] movies = objectMapper.readValue(inputStream, MovieDto[].class);
            if (movies != null) {
                cachedMovies = Arrays.asList(movies);
                lastFetchTime = System.currentTimeMillis();
                System.out.println("Loaded " + cachedMovies.size() + " movies from JSON.");
                return new ArrayList<>(cachedMovies);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to load movies from JSON: " + e.getMessage());
        }

        // If loading fails, use fallback
        if (cachedMovies.isEmpty()) {
            System.out.println("Using fallback movies.");
            cachedMovies = getFallbackMovies();
            System.out.println("Fallback movies count: " + cachedMovies.size());
        }
        return new ArrayList<>(cachedMovies);
    }

    public MovieDto getMovieById(int id) {
        System.out.println("MovieService.getMovieById called for ID: " + id);
        // Try cache first
        Optional<MovieDto> cached = cachedMovies.stream().filter(m -> m.getId() == id).findFirst();
        if (cached.isPresent()) {
            System.out.println("Found in cache (size " + cachedMovies.size() + ")");
            return cached.get();
        }

        // If not in cache (or cache empty), fetch specific show
        try {
            return restTemplate.getForObject("https://api.tvmaze.com/shows/" + id, MovieDto.class);
        } catch (Exception e) {
            return null;
        }
    }

    public List<MovieDto> searchMovies(String query) {
        if (query == null || query.isBlank())
            return getAllMovies();
        String lowerQuery = query.toLowerCase();
        return getAllMovies().stream()
                .filter(m -> m.getTitle().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<MovieDto> filterAndSortMovies(String genre, String sort) {
        List<MovieDto> movies = new ArrayList<>(getAllMovies());

        // Filter
        if (genre != null && !genre.isBlank() && !genre.equals("All")) {
            movies = movies.stream()
                    .filter(m -> m.getGenres() != null && m.getGenres().contains(genre))
                    .collect(Collectors.toList());
        }

        // Sort
        if (sort != null) {
            switch (sort) {
                case "rating_desc":
                    movies.sort(Comparator.comparing(MovieDto::getAverageRating).reversed());
                    break;
                case "rating_asc":
                    movies.sort(Comparator.comparing(MovieDto::getAverageRating));
                    break;
                case "year_desc":
                    movies.sort(Comparator.comparing(MovieDto::getYear).reversed());
                    break;
                case "year_asc":
                    movies.sort(Comparator.comparing(MovieDto::getYear));
                    break;
                case "title_asc":
                    movies.sort(Comparator.comparing(MovieDto::getTitle));
                    break;
            }
        }

        return movies;
    }

    public List<MovieDto> getTopRatedMovies(int limit) {
        return getAllMovies().stream()
                .sorted(Comparator.comparing(MovieDto::getAverageRating).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getLatestMovies(int limit) {
        return getAllMovies().stream()
                .filter(m -> m.getYear() > 0)
                .sorted(Comparator.comparing(MovieDto::getYear).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    public List<MovieDto> getMoviesByGenre(String genre) {
        if (genre == null || genre.equals("All"))
            return getAllMovies();
        return getAllMovies().stream()
                .filter(m -> m.getGenres() != null && m.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    public List<String> getAllGenres() {
        return getAllMovies().stream()
                .map(MovieDto::getGenres)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    private List<MovieDto> getFallbackMovies() {
        List<MovieDto> fallback = new ArrayList<>();

        fallback.add(createMovie(1, "The Shawshank Redemption", "1994", 9.3, "Drama",
                "https://upload.wikimedia.org/wikipedia/en/8/81/ShawshankRedemptionMoviePoster.jpg",
                "Two imprisoned men bond over a number of years..."));
        fallback.add(createMovie(2, "The Godfather", "1972", 9.2, "Crime",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                "The aging patriarch of an organized crime dynasty..."));
        fallback.add(createMovie(3, "The Dark Knight", "2008", 9.0, "Action",
                "https://upload.wikimedia.org/wikipedia/en/1/1c/Godfather_ver1.jpg",
                "When the menace known as the Joker wreaks havoc...")); // Placeholder image
        fallback.add(createMovie(4, "Pulp Fiction", "1994", 8.9, "Crime",
                "https://upload.wikimedia.org/wikipedia/en/3/3b/Pulp_Fiction_%281994%29_poster.jpg",
                "The lives of two mob hitmen, a boxer, a gangster and his wife..."));
        fallback.add(createMovie(5, "Inception", "2010", 8.8, "Sci-Fi",
                "https://upload.wikimedia.org/wikipedia/en/2/2e/Inception_%282010%29_theatrical_poster.jpg",
                "A thief who steals corporate secrets through dream-sharing..."));
        fallback.add(createMovie(6, "Fight Club", "1999", 8.8, "Drama",
                "https://upload.wikimedia.org/wikipedia/en/f/fc/Fight_Club_poster.jpg",
                "An insomniac office worker and a devil-may-care soapmaker..."));
        fallback.add(createMovie(7, "Forrest Gump", "1994", 8.8, "Drama",
                "https://upload.wikimedia.org/wikipedia/en/6/67/Forrest_Gump_poster.jpg",
                "The presidencies of Kennedy and Johnson, the Vietnam War..."));
        fallback.add(createMovie(8, "The Matrix", "1999", 8.7, "Sci-Fi",
                "https://upload.wikimedia.org/wikipedia/en/c/c1/The_Matrix_Poster.jpg",
                "A computer hacker learns from mysterious rebels about the true nature of his reality..."));
        fallback.add(createMovie(9, "Goodfellas", "1990", 8.7, "Biography",
                "https://upload.wikimedia.org/wikipedia/en/7/7b/Goodfellas.jpg",
                "The story of Henry Hill and his life in the mob..."));
        fallback.add(createMovie(10, "Seven Samurai", "1954", 8.6, "Action",
                "https://upload.wikimedia.org/wikipedia/commons/b/b5/Seven_Samurai_poster.jpg",
                "A poor village under attack by bandits recruits seven unemployed samurai..."));
        fallback.add(createMovie(11, "Se7en", "1995", 8.6, "Crime",
                "https://upload.wikimedia.org/wikipedia/en/6/68/Seven_%28movie%29_poster.jpg",
                "Two detectives, a rookie and a veteran, hunt a serial killer..."));
        fallback.add(createMovie(12, "City of God", "2002", 8.6, "Crime",
                "https://upload.wikimedia.org/wikipedia/en/1/10/City_of_God_film_poster.jpg",
                "In the slums of Rio, two kids' paths diverge as one struggles to become a photographer..."));
        fallback.add(createMovie(13, "Interstellar", "2014", 8.6, "Sci-Fi",
                "https://upload.wikimedia.org/wikipedia/en/b/bc/Interstellar_film_poster.jpg",
                "A team of explorers travel through a wormhole in space..."));
        fallback.add(createMovie(14, "Spirited Away", "2001", 8.6, "Animation",
                "https://upload.wikimedia.org/wikipedia/en/d/db/Spirited_Away_Japanese_poster.png",
                "During her family's move to the suburbs, a sullen 10-year-old girl wanders into a world ruled by gods..."));
        fallback.add(createMovie(15, "Parasite", "2019", 8.5, "Thriller",
                "https://upload.wikimedia.org/wikipedia/en/5/53/Parasite_%282019_film%29.png",
                "Greed and class discrimination threaten the newly formed symbiotic relationship..."));
        fallback.add(createMovie(16, "The Green Mile", "1999", 8.6, "Fantasy",
                "https://upload.wikimedia.org/wikipedia/en/e/e2/The_Green_Mile_%28movie_poster%29.jpg",
                "The lives of guards on Death Row are affected by one of their charges..."));
        fallback.add(createMovie(17, "Gladiator", "2000", 8.5, "Action",
                "https://upload.wikimedia.org/wikipedia/en/f/fb/Gladiator_%282000_film_poster%29.png",
                "A former Roman General sets out to exact vengeance against the corrupt emperor..."));
        fallback.add(createMovie(18, "The Lion King", "1994", 8.5, "Animation",
                "https://upload.wikimedia.org/wikipedia/en/3/3d/The_Lion_King_poster.jpg",
                "Lion prince Simba and his father are targeted by his bitter uncle..."));
        fallback.add(createMovie(19, "The Prestige", "2006", 8.5, "Mystery",
                "https://upload.wikimedia.org/wikipedia/en/d/d2/Prestige_poster.jpg",
                "After a tragic accident, two stage magicians engage in a battle to create the ultimate illusion..."));
        fallback.add(createMovie(20, "Whiplash", "2014", 8.5, "Music",
                "https://upload.wikimedia.org/wikipedia/en/0/01/Whiplash_poster.jpg",
                "A promising young drummer enrolls at a cut-throat music conservatory..."));
        fallback.add(createMovie(21, "The Departed", "2006", 8.5, "Crime",
                "https://upload.wikimedia.org/wikipedia/en/5/50/Departed2.jpg",
                "An undercover cop and a mole in the police attempt to identify each other..."));

        return fallback;
    }

    private MovieDto createMovie(int id, String title, String date, double rating, String genre, String poster,
            String plot) {
        MovieDto m = new MovieDto();
        m.setId(id);
        m.setTitle(title);
        m.setReleaseDate(date);
        m.setRating(rating);
        m.setImage(Collections.singletonMap("original", poster));
        m.setPlot(plot);
        m.setGenres(Collections.singletonList(genre));
        return m;
    }
}
