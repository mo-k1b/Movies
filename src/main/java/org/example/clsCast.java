package org.example;

/**
 * Represents a cast member in a movie, linking an actor to a specific role in a film.
 * This class manages the relationship between actors and movies, storing information
 * about the roles they play.
 *
 *
 * @version 1.0
 */
public class clsCast {
    /** Unique identifier for this cast entry */
    private int castID;

    /** Identifier of the actor in this cast entry */
    private int actorID;

    /** Identifier of the movie in this cast entry */
    private int movieID;

    /** The role or character name played by the actor */
    private String role;

    /**
     * Default constructor that creates an empty Cast object.
     * All integer fields are initialized to 0 and role is initialized to null.
     */
    public clsCast() {}

    /**
     * Parameterized constructor that creates a Cast object with specified values.
     *
     * @param castID the unique identifier for this cast entry
     * @param actorID the identifier of the actor
     * @param movieID the identifier of the movie
     * @param role the role or character name played by the actor
     */
    public clsCast(int castID, int actorID, int movieID, String role) {
        this.castID = castID;
        this.actorID = actorID;
        this.movieID = movieID;
        this.role = role;
    }

    /**
     * Gets the cast ID.
     *
     * @return the unique identifier for this cast entry
     */
    public int getCastID() { return castID; }

    /**
     * Sets the cast ID.
     *
     * @param castID the unique identifier to set for this cast entry
     */
    public void setCastID(int castID) { this.castID = castID; }

    /**
     * Gets the actor ID.
     *
     * @return the identifier of the actor
     */
    public int getActorID() { return actorID; }

    /**
     * Sets the actor ID.
     *
     * @param actorID the identifier of the actor to set
     */
    public void setActorID(int actorID) { this.actorID = actorID; }

    /**
     * Gets the movie ID.
     *
     * @return the identifier of the movie
     */
    public int getMovieID() { return movieID; }

    /**
     * Sets the movie ID.
     *
     * @param movieID the identifier of the movie to set
     */
    public void setMovieID(int movieID) { this.movieID = movieID; }

    /**
     * Gets the role name.
     *
     * @return the role or character name played by the actor
     */
    public String getRole() { return role; }

    /**
     * Sets the role name.
     *
     * @param role the role or character name to set
     */
    public void setRole(String role) { this.role = role; }

    /**
     * Retrieves a formatted string containing all cast details.
     *
     * @return a string representation of all cast information including
     *         cast ID, actor ID, movie ID, and role
     */
    public String getCastDetails() {
        return "Cast ID: " + castID + ", ActorID: " + actorID + ", MovieID: " + movieID + ", Role: " + role;
    }

    /**
     * Generates a human-readable description of the actor's appearance in the movie.
     *
     * @return a formatted string describing which actor appears in which movie
     *         and what role they play
     */
    public String listMovieActors() {
        return "Actor " + actorID + " appears in Movie " + movieID + " as " + role;
    }
}