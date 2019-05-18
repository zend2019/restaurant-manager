package main.java.BL.Contract;

public class AccountManager extends User {

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    private String officeName;
}
