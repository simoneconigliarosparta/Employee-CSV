# Employee-CSV
## Description
The program reads a list of employees data from a CSV file, filters any duplicates and inserts the data into a MySQL database using concurrency.

## Installation
1. Clone the repository:  
git clone https://github.com/simoneconigliarosparta/Employee-CSV.git

## Setup for MySQL workbench
1. Create a new schema employees.  
2. Create a new table employees with these columns:
- emp_id int PK 
- name_prefix varchar(10) 
- first_name varchar(50) 
- middle_name varchar(50) 
- last_name varchar(50) 
- gender varchar(10) 
- e_mail varchar(100) 
- dob date 
- date_of_joining date 
- salary float  
3. Disable safe mode:  
SET SQL_SAFE_UPDATES = 0;
4. Reconnect Database

## Before running the program:
1. Add url, username and password of your MySQL database in the myJdbc.properties file.
2. Make sure you don't have any record in the employees table:  
delete from employees;

## Changing number of threads:
In Loader.java:  
List<DatabaseThread> threadsToRun = ThreadManager.setThread(<number of thread you want>, filteredList);
