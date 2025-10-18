package org.example;

public class clsActor extends clsPerson {
    private int actorID;


    //default constructor
    public clsActor() {}

    //parameterized constructor
    public clsActor(int actorID, int personID, String firstName, String middleName, String lastName, String dateOfBirth) {
        super(personID, firstName, middleName, lastName, dateOfBirth);
        this.actorID = actorID;
    }



    public int getActorID() { return actorID; }
    public void setActorID(int actorID) { this.actorID = actorID; }

    public String actorDetails() {
        return "Actor ID: " + actorID + ", Name: " + fullName();
    }

    @Override
    public String toString() {
        return actorDetails();
    }
}
