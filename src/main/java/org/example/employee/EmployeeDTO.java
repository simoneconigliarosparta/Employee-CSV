package org.example.employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EmployeeDTO {

    private int empID;
    private String namePrefix;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String gender;
    private String email;
    private LocalDate dob;
    private LocalDate dateOfJoining;
    private float salary;


    public EmployeeDTO(int empID, String namePrefix, String firstName, String middleInitial, String lastName, String gender, String email, String dob, String dateOfJoining, float salary) {
        this.empID = empID;
        this.namePrefix = namePrefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dob = addDate(dob);
        this.dateOfJoining = addDate(dateOfJoining);
        this.salary = salary;
    }

    public EmployeeDTO(String[] employee) {
        this.empID = Integer.parseInt(employee[0]);
        this.namePrefix = employee[1];
        this.firstName = employee[2];
        this.middleInitial = employee[3];
        this.lastName = employee[4];
        this.gender = employee[5];
        this.email = employee[6];
        this.dob = addDate(employee[7]);
        this.dateOfJoining = addDate(employee[8]);
        this.salary = Float.parseFloat(employee[9]);
    }

    public int getEmpID() {
        return empID;
    }

    public String getNamePrefix() {
        return namePrefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleInitial() {
        return middleInitial;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public LocalDate getDateOfJoining() {
        return dateOfJoining;
    }

    public float getSalary() {
        return salary;
    }

    public LocalDate addDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        return LocalDate.parse(date, formatter);
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "empID=" + empID +
                ", namePrefix='" + namePrefix + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleInitial='" + middleInitial + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", dateOfJoining=" + dateOfJoining +
                ", salary=" + salary +
                '}';
    }
}
