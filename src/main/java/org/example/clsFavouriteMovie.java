package org.example;

public class clsFavouriteMovie {
    private int favouriteID;
    private int userID;
    private int movieID;

    public clsFavouriteMovie() {}
    public clsFavouriteMovie(int favouriteID, int userID, int movieID) {
        this.favouriteID = favouriteID;
        this.userID = userID;
        this.movieID = movieID;
    }

    public int getFavouriteID() { return favouriteID; }
    public void setFavouriteID(int favouriteID) { this.favouriteID = favouriteID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public String favouriteMovieDetails() {
        return "FavouriteMovie [ID: " + favouriteID + ", User: " + userID + ", Movie: " + movieID + "]";
    }
}
