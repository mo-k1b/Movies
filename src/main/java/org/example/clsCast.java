package org.example;

public class clsCast {
    private int castID;
    private int actorID;
    private int movieID;
    private String role;

    public clsCast() {}
    public clsCast(int castID, int actorID, int movieID, String role) {
        this.castID = castID;
        this.actorID = actorID;
        this.movieID = movieID;
        this.role = role;
    }

    public int getCastID() { return castID; }
    public void setCastID(int castID) { this.castID = castID; }

    public int getActorID() { return actorID; }
    public void setActorID(int actorID) { this.actorID = actorID; }

    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    // Operations
    public String getCastDetails() {
        return "Cast ID: " + castID + ", ActorID: " + actorID + ", MovieID: " + movieID + ", Role: " + role;
    }

    public String listMovieActors() {
        return "Actor " + actorID + " appears in Movie " + movieID + " as " + role;
    }
}
