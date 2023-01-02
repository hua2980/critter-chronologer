package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.data.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.data.EmployeeSkill;
import com.udacity.jdnd.course3.critter.data.entity.Employee;
import com.udacity.jdnd.course3.critter.data.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void addAvailability(Set<DayOfWeek> daysAvailable, Long id){
        Employee employee = employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        employee.setDaysAvailable(daysAvailable);
        save(employee);
    }

    public List<Employee> findAvailableEmployee(LocalDate date, Set<EmployeeSkill> skills){
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        List<Employee> employees = employeeRepository.findEmployeesByDaysAvailableContains(dayOfWeek);
        return employees.stream().
                filter(employee -> employee.getSkills().containsAll(skills))
                .collect(Collectors.toList());
    }

}
