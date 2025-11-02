package org.example;

/**
 * Represents a user's favorite movie relationship in the system.
 * This class manages the association between users and their favorite movies,
 * allowing the system to track which movies each user has marked as favorites.
 *
 *
 * @version 1.0
 */
public class clsFavouriteMovie {
    /** Unique identifier for this favorite movie entry */
    private int favouriteID;

    /** Identifier of the user who marked this movie as favorite */
    private int userID;

    /** Identifier of the movie marked as favorite */
    private int movieID;

    /**
     * Default constructor that creates an empty FavouriteMovie object.
     * All integer fields are initialized to 0.
     */
    public clsFavouriteMovie() {}

    /**
     * Parameterized constructor that creates a FavouriteMovie object with specified values.
     *
     * @param favouriteID the unique identifier for this favorite movie entry
     * @param userID the identifier of the user
     * @param movieID the identifier of the favorite movie
     */
    public clsFavouriteMovie(int favouriteID, int userID, int movieID) {
        this.favouriteID = favouriteID;
        this.userID = userID;
        this.movieID = movieID;
    }

    /**
     * Gets the favorite ID.
     *
     * @return the unique identifier for this favorite movie entry
     */
    public int getFavouriteID() { return favouriteID; }

    /**
     * Sets the favorite ID.
     *
     * @param favouriteID the unique identifier to set for this favorite movie entry
     */
    public void setFavouriteID(int favouriteID) { this.favouriteID = favouriteID; }

    /**
     * Gets the user ID.
     *
     * @return the identifier of the user who marked this movie as favorite
     */
    public int getUserID() { return userID; }

    /**
     * Sets the user ID.
     *
     * @param userID the identifier of the user to set
     */
    public void setUserID(int userID) { this.userID = userID; }

    /**
     * Gets the movie ID.
     *
     * @return the identifier of the favorite movie
     */
    public int getMovieID() { return movieID; }

    /**
     * Sets the movie ID.
     *
     * @param movieID the identifier of the movie to set
     */
    public void setMovieID(int movieID) { this.movieID = movieID; }

    /**
     * Retrieves a formatted string containing all favorite movie details.
     *
     * @return a string representation of the favorite movie entry including
     *         favorite ID, user ID, and movie ID
     */
    public String favouriteMovieDetails() {
        return "FavouriteMovie [ID: " + favouriteID + ", User: " + userID + ", Movie: " + movieID + "]";
    }
}