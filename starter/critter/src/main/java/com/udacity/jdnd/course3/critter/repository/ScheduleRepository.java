package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.entity.Employee;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.data.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>, CrudRepository<Schedule, Long> {
    @Query("select s from Schedule s where :pet MEMBER OF s.pets")
    List<Schedule> findSchedulesByPetIn(Pet pet);

    @Query("select s from Schedule s where :employee MEMBER OF s.employees")
    List<Schedule> findSchedulesByEmployeeIn(Employee employee);
}
