package org.example;

/**
 * Represents a movie genre in the system.
 * This class manages genre information including a unique identifier and genre name,
 * allowing categorization of movies by their type (e.g., Action, Comedy, Drama).
 *
 *
 * @version 1.0
 */
public class clsGenre {
    /** Unique identifier for this genre */
    private int genreID;

    /** Name of the genre (e.g., "Action", "Comedy", "Drama") */
    private String genreName;

    /**
     * Default constructor that creates an empty Genre object.
     * The genreID is initialized to 0 and genreName is initialized to null.
     */
    public clsGenre() {}

    /**
     * Parameterized constructor that creates a Genre object with specified values.
     *
     * @param genreID the unique identifier for this genre
     * @param genreName the name of the genre
     */
    public clsGenre(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    /**
     * Gets the genre ID.
     *
     * @return the unique identifier for this genre
     */
    public int getGenreID() { return genreID; }

    /**
     * Sets the genre ID.
     *
     * @param genreID the unique identifier to set for this genre
     */
    public void setGenreID(int genreID) { this.genreID = genreID; }

    /**
     * Gets the genre name.
     *
     * @return the name of the genre
     */
    public String getGenreName() { return genreName; }

    /**
     * Sets the genre name.
     *
     * @param genreName the name to set for this genre
     */
    public void setGenreName(String genreName) { this.genreName = genreName; }

    /**
     * Retrieves a formatted string containing all genre details.
     *
     * @return a string representation of the genre including genre ID and name
     */
    public String GenreDetails() {
        return "Genre ID: " + genreID + ", Name: " + genreName;
    }
}