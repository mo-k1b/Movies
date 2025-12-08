package com.example.movies.e2e;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoviesE2ETest {

    @LocalServerPort
    private int port;

    private static Playwright playwright;
    private static Browser browser;

    @BeforeAll
    static void setUp() {
        playwright = Playwright.create();
        // Use chromium for testing
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
    }

    @AfterAll
    static void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    void testHomePageTitle() {
        try (BrowserContext context = browser.newContext()) {
            Page page = context.newPage();
            page.navigate("http://localhost:" + port + "/");
            
            // Check title - assuming default Spring Boot title or specific if set
            // In Thymeleaf we usually set title, let's verify if 'Movies' is in title
            // Based on index.html usually having <title>Movies</title> or similar
            // We'll perform a broad check or print it first if unsure, but let's assume "Movies"
            String title = page.title();
            assertTrue(title.contains("Movies") || title.contains("movies"), "Title should contain 'Movies'");
        }
    }

    @Test
    void testSearchFlow() {
        try (BrowserContext context = browser.newContext()) {
            Page page = context.newPage();
            page.navigate("http://localhost:" + port + "/browse");
            
            // Fill search input
            // Assuming there is an input with name='search'
            page.fill("input[name='search']", "Barbie");
            page.press("input[name='search']", "Enter");
            
            // Verify results
            // Expect to find "Barbie" in the page content
            // Wait for results to load if necessary (implicit in Playwright auto-wait)
            // Checking for specific element presence
            assertTrue(page.content().contains("Barbie"));
        }
    }
}
