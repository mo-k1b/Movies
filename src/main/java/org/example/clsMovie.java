package org.example;

public class clsMovie {
    private int movieID;
    private String title;
    private float rating;
    private String publishDate;
    private int genreID;

    //default constructor
    public clsMovie() {}

    //default constructor
    public clsMovie(int movieID, String title, float rating, String publishDate, int genreID) {
        this.movieID = movieID;
        this.title = title;
        this.rating = rating;
        this.publishDate = publishDate;
        this.genreID = genreID;
    }

    // Getters and setters
    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }

    public String getPublishDate() { return publishDate; }
    public void setPublishDate(String publishDate) { this.publishDate = publishDate; }

    public int getGenreID() { return genreID; }
    public void setGenreID(int genreID) { this.genreID = genreID; }

    public String getMovieDetails() {
        return "Movie ID: " + movieID + ", Title: " + title + ", Rating: " + rating + ", Published: " + publishDate;
    }

    @Override
    public String toString() {
        return getMovieDetails();
    }
}
