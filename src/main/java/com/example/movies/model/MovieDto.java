package com.example.movies.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object (DTO) for Movie information.
 * <p>
 * This class represents a movie entity with comprehensive details including
 * basic information (title, year, genres), ratings, images, and metadata.
 * It supports JSON deserialization from multiple API sources with flexible
 * field mapping using Jackson annotations.
 * </p>
 * <p>
 * The class handles data from both TVMaze API and YTS API formats, providing
 * computed fields for easier access to commonly used data like year and poster
 * URLs.
 * </p>
 *
 * @author Movies Application
 * @version 1.0
 * @since 1.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieDto {
    private int id;

    @JsonAlias("name")
    private String title;

    private List<String> genres;

    @JsonProperty("premiered")
    private String releaseDate;

    private Map<String, Object> rating;
    private Map<String, String> image;

    @JsonAlias("summary")
    private String plot;

    // Computed fields
    private int year;
    private Double averageRating;
    private String posterUrl;

    // New fields (snake_case for JSON compatibility)
    private String background_image;
    private String large_cover_image;
    private String medium_cover_image;
    private String description_full;
    private String imdb_code;
    private String yt_trailer_code;
    private int runtime;
    private List<String> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        if (releaseDate != null && releaseDate.length() >= 4) {
            try {
                this.year = Integer.parseInt(releaseDate.substring(0, 4));
            } catch (NumberFormatException e) {
                this.year = 0;
            }
        }
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        // Remove HTML tags
        this.plot = plot != null ? plot.replaceAll("<[^>]*>", "") : "";
    }

    public void setRating(Map<String, Object> rating) {
        this.rating = rating;
        if (rating != null && rating.get("average") != null) {
            Object avg = rating.get("average");
            if (avg instanceof Number) {
                this.averageRating = ((Number) avg).doubleValue();
            }
        }
    }

    public void setImage(Map<String, String> image) {
        this.image = image;
        if (image != null) {
            this.posterUrl = image.get("original");
        }
    }

    public int getYear() {
        return year;
    }

    public Double getAverageRating() {
        return averageRating != null ? averageRating : 0.0;
    }

    public String getPoster() {
        if (medium_cover_image != null)
            return medium_cover_image;
        if (posterUrl != null)
            return posterUrl;
        return image != null ? image.get("original") : null;
    }

    // Getters for specific fields (snake_case)
    public String getBackground_image() {
        return background_image;
    }

    public void setBackground_image(String background_image) {
        this.background_image = background_image;
    }

    public String getLarge_cover_image() {
        return large_cover_image;
    }

    public void setLarge_cover_image(String large_cover_image) {
        this.large_cover_image = large_cover_image;
    }

    public String getMedium_cover_image() {
        return medium_cover_image;
    }

    public void setMedium_cover_image(String medium_cover_image) {
        this.medium_cover_image = medium_cover_image;
    }

    public String getDescription_full() {
        return description_full;
    }

    public void setDescription_full(String description_full) {
        this.description_full = description_full;
    }

    public String getImdb_code() {
        return imdb_code;
    }

    public void setImdb_code(String imdb_code) {
        this.imdb_code = imdb_code;
    }

    public String getYt_trailer_code() {
        return yt_trailer_code;
    }

    public void setYt_trailer_code(String yt_trailer_code) {
        this.yt_trailer_code = yt_trailer_code;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    @JsonProperty("rating")
    public void setRatingObject(Object ratingObj) {
        if (ratingObj instanceof Number) {
            this.averageRating = ((Number) ratingObj).doubleValue();
        } else if (ratingObj instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) ratingObj;
            if (map.get("average") instanceof Number) {
                this.averageRating = ((Number) map.get("average")).doubleValue();
            }
        }
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setRating(double rating) {
        this.averageRating = rating;
    }

    public List<String> getCast() {
        return cast;
    }

    public void setCast(List<String> cast) {
        this.cast = cast;
    }

    public void setPoster(String poster) {
        this.posterUrl = poster;
    }
}
