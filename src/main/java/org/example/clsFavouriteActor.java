package org.example;

/**
 * Represents a user's favorite actor relationship in the system.
 * This class manages the association between users and their favorite actors,
 * allowing the system to track which actors each user has marked as favorites.
 *
 *
 * @version 1.0
 */
public class clsFavouriteActor {
    /** Unique identifier for this favorite actor entry */
    private int favouriteID;

    /** Identifier of the user who marked this actor as favorite */
    private int userID;

    /** Identifier of the actor marked as favorite */
    private int actorID;

    /**
     * Default constructor that creates an empty FavouriteActor object.
     * All integer fields are initialized to 0.
     */
    public clsFavouriteActor() {}

    /**
     * Parameterized constructor that creates a FavouriteActor object with specified values.
     *
     * @param favouriteID the unique identifier for this favorite actor entry
     * @param userID the identifier of the user
     * @param actorID the identifier of the favorite actor
     */
    public clsFavouriteActor(int favouriteID, int userID, int actorID) {
        this.favouriteID = favouriteID;
        this.userID = userID;
        this.actorID = actorID;
    }

    /**
     * Gets the favorite ID.
     *
     * @return the unique identifier for this favorite actor entry
     */
    public int getFavouriteID() { return favouriteID; }

    /**
     * Sets the favorite ID.
     *
     * @param favouriteID the unique identifier to set for this favorite actor entry
     */
    public void setFavouriteID(int favouriteID) { this.favouriteID = favouriteID; }

    /**
     * Gets the user ID.
     *
     * @return the identifier of the user who marked this actor as favorite
     */
    public int getUserID() { return userID; }

    /**
     * Sets the user ID.
     *
     * @param userID the identifier of the user to set
     */
    public void setUserID(int userID) { this.userID = userID; }

    /**
     * Gets the actor ID.
     *
     * @return the identifier of the favorite actor
     */
    public int getActorID() { return actorID; }

    /**
     * Sets the actor ID.
     *
     * @param actorID the identifier of the actor to set
     */
    public void setActorID(int actorID) { this.actorID = actorID; }

    /**
     * Retrieves a formatted string containing all favorite actor details.
     *
     * @return a string representation of the favorite actor entry including
     *         favorite ID, user ID, and actor ID
     */
    public String favouriteActorDetails() {
        return "FavouriteActor [ID: " + favouriteID + ", User: " + userID + ", Actor: " + actorID + "]";
    }

    /**
     * Generates a human-readable description of the user-actor relationship.
     *
     * @return a formatted string indicating which user likes which actor
     */
    public String listFavouriteActorMovies() {
        return "User " + userID + " likes Actor " + actorID;
    }
}