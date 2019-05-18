package main.java.BL.Contract;

import java.util.Date;

public class Employee extends User {
    public Date getHireDate() {
        return HireDate;
    }

    public void setHireDate(Date hireDate) {
        HireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private Date HireDate;
    private Department department;

}
