package com.example.movies.controller;

import com.example.movies.model.User;
import com.example.movies.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        // Simple login: check if user exists by email and password
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Invalid email or password.");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam String email,
            @RequestParam String password,
            @RequestParam String phoneNumber,
            Model model) {

        if (userRepository.existsByEmail(email)) {
            model.addAttribute("error", "Email already in use.");
            model.addAttribute("user", new User()); // Clean user for form
            return "register";
        }

        // Auto-generate username
        String username = "MovieFan_" + (1000 + new java.util.Random().nextInt(9000));

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password); // in real app, hash this!
        newUser.setPhoneNumber(phoneNumber);
        newUser.setUsername(username);

        userRepository.save(newUser);

        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
