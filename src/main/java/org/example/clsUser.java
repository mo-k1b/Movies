package org.example;

public class clsUser extends clsPerson {
    private int userID;
    private String email;
    private String phone;


    //default constructor
    public clsUser() {}

    //parametrized constructor
    public clsUser(int userID, int personID, String firstName, String middleName, String lastName,
                   String dateOfBirth, String email, String phone) {
        super(personID, firstName, middleName, lastName, dateOfBirth);
        this.userID = userID;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters
    public int getUserID() { return userID; }
    public void setUserID(int userID) { this.userID = userID; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    // Operations
    public String userDetails() {
        return "User ID: " + userID + ", Name: " + fullName() + ", Email: " + email + ", Phone: " + phone;
    }

    @Override
    public String toString() {
        return userDetails();
    }
}

