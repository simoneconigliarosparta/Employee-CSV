# Employee-CSV
## Description
The program reads a list of employees data from a CSV file, filters any duplicates and inserts the data into a MySQL database using concurrency.

## Installation
1. Clone the repository:  
git clone https://github.com/simoneconigliarosparta/Employee-CSV.git

## Setup for MySQL workbench
1. Create a new schema employees.  
2. Create a new table employees with these columns:
<img src="https://github.com/simoneconigliarosparta/Employee-CSV/blob/main/employee_table.jpg">

## Before running the program:
1. Create a myJdbc.properties file in the resource folder with these keys:  
`url=` `username=` `password=`  
take their values from your MySQL database and add them to the properties file.
2. Make sure you don't have any record in the employees table:  
`DELETE FROM employees WHERE emp_id > 0;`

## Changing number of threads:
In Loader.java:  
`List<DatabaseThread> threadsToRun = ThreadManager.setThread(<number of thread you want>, filteredList);`
