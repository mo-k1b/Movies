package org.example;

/**
 * Represents a user in the system, extending the base person information
 * with user-specific details and contact information.
 * This class inherits general personal information from {@link clsPerson} and adds
 * user-specific attributes such as a unique user identifier, email, and phone number.
 *
 * @author org.example
 * @version 1.0
 * @see clsPerson
 */
public class clsUser extends clsPerson {
    /** Unique identifier for this user */
    private int userID;

    /** Email address of the user */
    private String email;

    /** Phone number of the user */
    private String phone;


    /**
     * Default constructor that creates an empty User object.
     * All fields are initialized to their default values (0 for integers, null for Strings).
     */
    public clsUser() {}

    /**
     * Parameterized constructor that creates a User object with specified values.
     * Initializes both the inherited person attributes and the user-specific information.
     *
     * @param userID the unique identifier for this user
     * @param personID the unique identifier for the person (inherited from {@link clsPerson})
     * @param firstName the first name of the user
     * @param middleName the middle name of the user (can be null or empty)
     * @param lastName the last name of the user
     * @param dateOfBirth the date of birth of the user
     * @param email the email address of the user
     * @param phone the phone number of the user
     */
    public clsUser(int userID, int personID, String firstName, String middleName, String lastName,
                   String dateOfBirth, String email, String phone) {
        super(personID, firstName, middleName, lastName, dateOfBirth);
        this.userID = userID;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Gets the user ID.
     *
     * @return the unique identifier for this user
     */
    public int getUserID() { return userID; }

    /**
     * Sets the user ID.
     *
     * @param userID the unique identifier to set for this user
     */
    public void setUserID(int userID) { this.userID = userID; }

    /**
     * Gets the email address.
     *
     * @return the email address of the user
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address.
     *
     * @param email the email address to set for this user
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the phone number.
     *
     * @return the phone number of the user
     */
    public String getPhone() { return phone; }

    /**
     * Sets the phone number.
     *
     * @param phone the phone number to set for this user
     */
    public void setPhone(String phone) { this.phone = phone; }

    /**
     * Retrieves a formatted string containing all user details.
     * Includes the user ID, full name, email address, and phone number.
     *
     * @return a string representation of the user's information
     */
    public String userDetails() {
        return "User ID: " + userID + ", Name: " + fullName() + ", Email: " + email + ", Phone: " + phone;
    }

    /**
     * Returns a string representation of this user.
     * Delegates to {@link #userDetails()} to provide a formatted summary
     * of the user's information.
     *
     * @return a string representation containing user ID, full name, email, and phone number
     */
    @Override
    public String toString() {
        return userDetails();
    }
}