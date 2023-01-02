package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Customer;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.hibernate.collection.internal.PersistentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer saveWithPets(Customer customer, List<Long> petIds) {
        List<Pet> pets = new ArrayList<>();
        for (Long petId : petIds){
            Pet pet = petRepository.findById(petId).orElseThrow(EntityNotFoundException::new);
            pets.add(pet);
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }

    public List<Customer> list(){
        return customerRepository.findAll();
    }

    public Customer findByPetId(Long petId){
        Pet pet = petRepository.findById(petId).orElseThrow(EntityNotFoundException::new);
        return customerRepository.findById(pet.getOwner().getId())
                .orElseThrow(EntityNotFoundException::new);
    }
}
