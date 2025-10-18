package org.example;

public class clsGenre {
    private int genreID;
    private String genreName;

    public clsGenre() {}
    public clsGenre(int genreID, String genreName) {
        this.genreID = genreID;
        this.genreName = genreName;
    }

    public int getGenreID() { return genreID; }
    public void setGenreID(int genreID) { this.genreID = genreID; }

    public String getGenreName() { return genreName; }
    public void setGenreName(String genreName) { this.genreName = genreName; }

    public String GenreDetails() {
        return "Genre ID: " + genreID + ", Name: " + genreName;
    }
}
