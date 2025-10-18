package org.example;

public class clsFavouriteActor {
    private int favouriteID;
    private int userID;
    private int actorID;

    public clsFavouriteActor() {}
    public clsFavouriteActor(int favouriteID, int userID, int actorID) {
        this.favouriteID = favouriteID;
        this.userID = userID;
        this.actorID = actorID;
    }

    public int getFavouriteID() { return favouriteID; }
    public void setFavouriteID(int favouriteID) { this.favouriteID = favouriteID; }

    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public int getActorID() { return actorID; }
    public void setActorID(int actorID) { this.actorID = actorID; }

    // Operations
    public String favouriteActorDetails() {
        return "FavouriteActor [ID: " + favouriteID + ", User: " + userID + ", Actor: " + actorID + "]";
    }

    public String listFavouriteActorMovies() {
        return "User " + userID + " likes Actor " + actorID;
    }
}
