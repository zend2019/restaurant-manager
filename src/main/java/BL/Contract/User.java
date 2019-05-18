package main.java.BL.Contract;

public abstract class User {

    private int id;
    private String FirstName;
    private String LastName;
    private String Age;
    private String DateOfBirth;
    private String UserName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPhoneNmuber() {
        return phoneNmuber;
    }

    public void setPhoneNmuber(String phoneNmuber) {
        this.phoneNmuber = phoneNmuber;
    }

    public String phoneNmuber;
}