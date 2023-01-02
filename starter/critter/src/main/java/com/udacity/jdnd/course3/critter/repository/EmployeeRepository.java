package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.EmployeeSkill;
import com.udacity.jdnd.course3.critter.data.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, CrudRepository<Employee, Long> {
    @Query("select e from Employee e where :dayOfWeek MEMBER OF e.daysAvailable")
    List<Employee> findEmployeesByDaysAvailableContains(DayOfWeek dayOfWeek);
}
