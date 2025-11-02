package org.example;

/**
 * Represents a person in the system with basic personal information.
 * This class serves as a base class for other person-related entities (e.g., actors, users)
 * and manages fundamental personal details such as name and date of birth.
 *
 * @author org.example
 * @version 1.0
 */
public class clsPerson {
    /** Unique identifier for this person */
    private int personID;

    /** First name of the person */
    private String firstName;

    /** Middle name of the person (optional) */
    private String middleName;

    /** Last name of the person */
    private String lastName;

    /** Date of birth of the person */
    private String dateOfBirth;


    /**
     * Default constructor that creates an empty Person object.
     * All integer fields are initialized to 0 and Strings to null.
     */
    public clsPerson() {}

    /**
     * Parameterized constructor that creates a Person object with specified values.
     *
     * @param personID the unique identifier for this person
     * @param firstName the first name of the person
     * @param middleName the middle name of the person (can be null or empty)
     * @param lastName the last name of the person
     * @param dateOfBirth the date of birth of the person
     */
    public clsPerson(int personID, String firstName, String middleName, String lastName, String dateOfBirth) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets the person ID.
     *
     * @return the unique identifier for this person
     */
    public int getPersonID() { return personID; }

    /**
     * Sets the person ID.
     *
     * @param personID the unique identifier to set for this person
     */
    public void setPersonID(int personID) { this.personID = personID; }

    /**
     * Gets the first name.
     *
     * @return the first name of the person
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set for this person
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the middle name.
     *
     * @return the middle name of the person, or null if not set
     */
    public String getMiddleName() { return middleName; }

    /**
     * Sets the middle name.
     *
     * @param middleName the middle name to set for this person (can be null or empty)
     */
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    /**
     * Gets the last name.
     *
     * @return the last name of the person
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set for this person
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the date of birth.
     *
     * @return the date of birth of the person
     */
    public String getDateOfBirth() { return dateOfBirth; }

    /**
     * Sets the date of birth.
     *
     * @param dateOfBirth the date of birth to set for this person
     */
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }




    /**
     * Constructs and returns the full name of the person.
     * If a middle name is provided and not empty, it includes the middle name
     * in the format "FirstName MiddleName LastName". Otherwise, returns
     * "FirstName LastName".
     *
     * @return the full name of the person, including middle name if available
     */
    public String fullName() {
        return (middleName != null && !middleName.isEmpty())
                ? firstName + " " + middleName + " " + lastName
                : firstName + " " + lastName;
    }

    /**
     * Returns a string representation of this person.
     * Includes the full name and date of birth in the format:
     * "FirstName [MiddleName] LastName (DOB: dateOfBirth)".
     *
     * @return a string representation containing the person's full name and date of birth
     */
    @Override
    public String toString() {
        return fullName() + " (DOB: " + dateOfBirth + ")";
    }
}