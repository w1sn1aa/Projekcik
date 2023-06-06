package com.example.ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class EmployeeManager {
    private final HashMap<Integer, Employee> employees;

    public HashMap<Integer, Employee> getEmployees() {
        return this.employees;
    }

    public EmployeeManager(HashMap<Integer, Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(String firstName, String lastName, String position, int salary) {
        Employee employee = new Employee(firstName, lastName, position, salary);
        Integer keyEmployee = employee.getEmployeeID();
        this.employees.put(keyEmployee, employee);
    }

    public void removeEmployee(Employee employee) {
        if (this.employees.containsKey(employee.getEmployeeID())) {
            this.employees.remove(employee.getEmployeeID());
        } else {
            System.out.println("Pracownik o takim ID nie istnieje");
        }

    }

    public void updateEmployee(int employeeID, String newPosition, int newSalary) {
        if (searchEmployeeByID(employeeID) != null){
            Employee e = searchEmployeeByID(employeeID);
            e.setPosition(newPosition);
            e.setSalary(newSalary);
        } else {
            System.out.println("Pracownik o takim ID nie istnieje");
        }

    }

    public Employee searchEmployeeByID(int employeeID) {
        if (this.employees.containsKey(employeeID)) {
            return (Employee)this.employees.get(employeeID);
        } else {
            System.out.println("Nie znaleziono pracownika o danym ID");
            return null;
        }
    }

    public ArrayList<Employee> listAllEmployees() {
        return new ArrayList(this.employees.values());
    }

    public HashSet<Employee> searchEmployeesByLastName(String lastName) {
        HashSet<Employee> employeeList = new HashSet(this.employees.values());
        Iterator<Employee> employeeIterator = employeeList.iterator();

        while(employeeIterator.hasNext()) {
            Employee employee = (Employee)employeeIterator.next();
            if (!employee.getLastName().equals(lastName)) {
                employeeIterator.remove();
            }
        }

        return employeeList;
    }
}