package org.example.database;

public interface SQLQueries {

    String SELECT_ALL = "SELECT * FROM employees.employees";
    String INSERT = "insert into employees.employees(emp_id, name_prefix, first_name, middle_name, last_name, gender, e_mail, dob, date_of_joining, salary) values(?,?,?,?,?,?,?,?,?);";

}
