package main.java.BL.Contract;

import java.util.Date;

public class User {

    private int id;
    private String FirstName;
    private String LastName;
    private Date DateOfBirth;
    private String UserName;
    private String Password;
    private String PhoneNumber;
    private int IsManager;
    private String HireDate;

    /* 1 - Id */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /* 2 - First Name */
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    /* 3 - Last Name */
    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    /* 4 - Date Of Birth */
    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    /* 5 - Username */
    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    /* 6 - Password */
    public String getPassword() {
        return Password;
    }
    public void setPassword(String password) {
        Password = password;
    }

    /* 7 - Phone Number */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNmuber) {
        this.PhoneNumber = phoneNmuber;
    }

    /* 8 - Is Manager */
    public int getIsManager() {
        return IsManager;
    }

    public void setIsManager(int is_manager) {
        this.IsManager = is_manager;
    }

}