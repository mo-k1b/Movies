package com.example.movies.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class representing a registered user in the Movies application.
 * <p>
 * This JPA entity maps to the "users" table in the database and stores
 * user authentication and profile information. Users can register, login,
 * and maintain a list of favorite movies.
 * </p>
 *
 * @author Movies Application
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "users")
public class User {

    /** Unique identifier for the user (auto-generated). */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Username for display purposes. */
    private String username;

    /** User's email address (used for login). */
    private String email;

    /** Encrypted password for authentication. */
    private String password;

    /** User's phone number. */
    private String phoneNumber;

    /**
     * Default constructor required by JPA.
     */
    public User() {
    }

    /**
     * Constructs a new User with the specified details.
     *
     * @param username    the username for display
     * @param email       the user's email address
     * @param password    the user's password (should be encrypted before storage)
     * @param phoneNumber the user's phone number
     */
    public User(String username, String email, String password, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
