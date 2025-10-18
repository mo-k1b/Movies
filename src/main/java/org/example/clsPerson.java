package org.example;

public class clsPerson {
    private int personID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String dateOfBirth;


    //default constructor
    public clsPerson() {}

    //parameterized constructor
    public clsPerson(int personID, String firstName, String middleName, String lastName, String dateOfBirth) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    //setters and getters
    public int getPersonID() { return personID; }
    public void setPersonID(int personID) { this.personID = personID; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getMiddleName() { return middleName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(String dateOfBirth) { this.dateOfBirth = dateOfBirth; }




    public String fullName() {
        return (middleName != null && !middleName.isEmpty())
                ? firstName + " " + middleName + " " + lastName
                : firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return fullName() + " (DOB: " + dateOfBirth + ")";
    }
}
