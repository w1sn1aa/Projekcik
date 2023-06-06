package com.example.ab;

import java.util.HashMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LinkController {
    static HashMap<Integer, Employee> mapa = new HashMap<Integer, Employee>();
    static EmployeeManager employeeManager = new EmployeeManager(mapa);

    public static void DodajPracownikow() {
        employeeManager.addEmployee("John", "Doe", "Manager", 75000);
        employeeManager.addEmployee("Jane", "Smith", "Accountant", 60000);
        employeeManager.addEmployee("Michael", "Johnson", "Accountant", 55000);
        employeeManager.addEmployee("Emily", "Jones", "Marketing Specialist", 50000);
    }

    @GetMapping({"/usun/{id}"})
    public String Delete(@PathVariable("id") int id) {
        employeeManager.removeEmployee(employeeManager.searchEmployeeByID(id));
        return "redirect:/";
    }
    @GetMapping({"/edycja/{id}"})
    public String Edit(@PathVariable("id") int id, Model model) {
        Employee employee = employeeManager.searchEmployeeByID(id);
        model.addAttribute("employee", employee);
        return "edycja";
    }
    @GetMapping({"/"})
    public String Index(Model model) {
        model.addAttribute("employeeManager", employeeManager);
        return "index";
    }
    @PostMapping({"/szukaj"})
    public String Search(@RequestParam("lastName") String nazwisko, Model model) {
        model.addAttribute("nazwisko", nazwisko);
        model.addAttribute("employeeManager", employeeManager);
        return "szukaj";
    }
    @GetMapping({"/form"})
    public String Form(Model model) {
        model.addAttribute("Employee", new Employee());
        return "form";
    }
    @PostMapping({"/aktualizuj/{id}"})
    public String Update(@PathVariable("id") int id, @ModelAttribute("employee") Employee zaaktulalizowane) {
        employeeManager.updateEmployee(id, zaaktulalizowane.getPosition(), zaaktulalizowane.getSalary());
        return "redirect:/";
    }
    @PostMapping({"/zapisz"})
    public String Save(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName, @RequestParam("position") String position, @RequestParam("salary") int salary) {
        employeeManager.addEmployee(firstName, lastName, position, salary);
        return "redirect:/form";
    }

}