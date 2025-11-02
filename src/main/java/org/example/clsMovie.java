package org.example;

/**
 * Represents a movie in the system.
 * This class manages movie information including identification, title, rating,
 * publication date, and genre classification. It provides methods for accessing
 * and modifying movie details.
 *
 * @author org.example
 * @version 1.0
 */
public class clsMovie {
    /** Unique identifier for this movie */
    private int movieID;

    /** Title of the movie */
    private String title;

    /** Rating of the movie (e.g., 7.5, 9.0) */
    private float rating;

    /** Publication or release date of the movie */
    private String publishDate;

    /** Identifier of the genre this movie belongs to */
    private int genreID;

    /**
     * Default constructor that creates an empty Movie object.
     * All integer fields are initialized to 0, float to 0.0, and Strings to null.
     */
    public clsMovie() {}

    /**
     * Parameterized constructor that creates a Movie object with specified values.
     *
     * @param movieID the unique identifier for this movie
     * @param title the title of the movie
     * @param rating the rating of the movie
     * @param publishDate the publication or release date of the movie
     * @param genreID the identifier of the genre this movie belongs to
     */
    public clsMovie(int movieID, String title, float rating, String publishDate, int genreID) {
        this.movieID = movieID;
        this.title = title;
        this.rating = rating;
        this.publishDate = publishDate;
        this.genreID = genreID;
    }

    /**
     * Gets the movie ID.
     *
     * @return the unique identifier for this movie
     */
    public int getMovieID() { return movieID; }

    /**
     * Sets the movie ID.
     *
     * @param movieID the unique identifier to set for this movie
     */
    public void setMovieID(int movieID) { this.movieID = movieID; }

    /**
     * Gets the movie title.
     *
     * @return the title of the movie
     */
    public String getTitle() { return title; }

    /**
     * Sets the movie title.
     *
     * @param title the title to set for this movie
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Gets the movie rating.
     *
     * @return the rating of the movie
     */
    public float getRating() { return rating; }

    /**
     * Sets the movie rating.
     *
     * @param rating the rating to set for this movie
     */
    public void setRating(float rating) { this.rating = rating; }

    /**
     * Gets the publication date.
     *
     * @return the publication or release date of the movie
     */
    public String getPublishDate() { return publishDate; }

    /**
     * Sets the publication date.
     *
     * @param publishDate the publication or release date to set for this movie
     */
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    /**
     * Gets the genre ID.
     *
     * @return the identifier of the genre this movie belongs to
     */
    public int getGenreID() { return genreID; }

    /**
     * Sets the genre ID.
     *
     * @param genreID the identifier of the genre to set for this movie
     */
    public void setGenreID(int genreID) { this.genreID = genreID; }

    /**
     * Retrieves a formatted string containing key movie details.
     * Includes the movie ID, title, rating, and publication date.
     *
     * @return a string representation of the movie's main information
     */
    public String getMovieDetails() {
        return "Movie ID: " + movieID + ", Title: " + title + ", Rating: " + rating + ", Published: " + publishDate;
    }

    /**
     * Returns a string representation of this movie.
     * Delegates to {@link #getMovieDetails()} to provide a formatted summary
     * of the movie's information.
     *
     * @return a string representation containing movie ID, title, rating, and publication date
     */
    @Override
    public String toString() {
        return getMovieDetails();
    }
}