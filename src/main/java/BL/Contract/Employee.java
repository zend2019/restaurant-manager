package main.java.BL.Contract;

import java.util.Date;

public class Employee extends User {
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    private Date hireDate;

}
