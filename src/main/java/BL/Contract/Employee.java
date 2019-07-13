package main.java.BL.Contract;

import java.util.Date;

public class Employee extends User {
    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    private Date hireDate;
    private Department department;

}
