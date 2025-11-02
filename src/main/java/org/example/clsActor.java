package org.example;

/**
 * Represents an actor in the system, extending the base person information
 * with actor-specific details and functionality.
 * This class inherits general personal information from {@link clsPerson} and adds
 * actor-specific attributes such as a unique actor identifier.
 *
 *
 * @version 1.0
 * @since 1.0
 * @see clsPerson
 */
public class clsActor extends clsPerson {

    /**
     * Unique identifier for this actor.
     * This ID is used to distinguish actors in the database and throughout the system.
     */
    private int actorID;


    /**
     * Default constructor that creates an empty Actor object.
     * All fields are initialized to their default values (0 for integers, null for Strings).
     */
    public clsActor() {}


    /**
     * Parameterized constructor that creates an Actor object with specified values.
     * Initializes both the inherited person attributes and the actor-specific ID.
     *
     * @param actorID the unique identifier for this actor
     * @param personID the unique identifier for the person (inherited from {@link clsPerson})
     * @param firstName the first name of the actor, should be non-null and non-empty
     * @param middleName the middle name of the actor, can be null or empty
     * @param lastName the last name of the actor, should be non-null and non-empty
     * @param dateOfBirth the date of birth of the actor in the format expected by {@link clsPerson}
     * @since 1.0
     */
    public clsActor(int actorID, int personID, String firstName, String middleName, String lastName, String dateOfBirth) {
        super(personID, firstName, middleName, lastName, dateOfBirth);
        this.actorID = actorID;
    }


    /**
     * Gets the actor ID.
     *
     * @return the unique identifier for this actor
     * @since 1.0
     */
    public int getActorID() { return actorID; }

    /**
     * Sets the actor ID.
     *
     * @param actorID the unique identifier to set for this actor
     * @since 1.0
     */
    public void setActorID(int actorID) { this.actorID = actorID; }

    /**
     * Retrieves a formatted string containing key actor details.
     * Provides a convenient one-line summary of the actor's information including
     * their ID and full name.
     *
     * @return a string containing the actor ID and full name
     * @since 1.0
     */
    public String actorDetails() {
        return "Actor ID: " + actorID + ", Name: " + fullName();
    }

    /**
     * Returns a string representation of this actor.
     * Currently returns the same information as {@link #actorDetails()}, but may be
     * enhanced in future versions to include additional actor information.
     *
     * @return a string representation containing actor ID and full name
     * @since 1.0
     */
    @Override
    public String toString() {
        return actorDetails();
    }
}