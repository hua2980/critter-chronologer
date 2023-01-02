package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Employee;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.data.entity.Schedule;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ScheduleService {

    @Autowired
    PetRepository petRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    public Schedule save(Schedule schedule, List<Long> employeeIds, List<Long> petIds){
        List<Employee> employees = employeeIds
                .stream()
                .map(id -> employeeRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList());
        List<Pet> pets = petIds
                .stream()
                .map(id -> petRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toList());

        schedule.setEmployees(employees);
        schedule.setPets(pets);
        return scheduleRepository.save(schedule);
    }

    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    public List<Schedule> findScheduleForPet(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(EntityNotFoundException::new);
        return scheduleRepository.findSchedulesByPetIn(pet);
    }

    public List<Schedule> findScheduleForEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(EntityNotFoundException::new);
        return scheduleRepository.findSchedulesByEmployeeIn(employee);
    }

    public List<Schedule> findScheduleForCustomer(Long customerId){
        List<Pet> pets = petRepository.findByOwner(
                customerRepository.findById(customerId)
                        .orElseThrow(EntityNotFoundException::new));
        List<Schedule> allSchedules = new ArrayList<>();
        for (Pet pet : pets) {
            List<Schedule> schedules = scheduleRepository.findSchedulesByPetIn(pet);
            List<Schedule> mergedSchedules = Stream.of(allSchedules, schedules)
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
            allSchedules = mergedSchedules;
        }
        return allSchedules;
    }
}

