package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.data.entity.Customer;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PetRepository petRepository;

    public Pet save(Pet pet, Long ownerId){
        Customer owner = customerRepository.findById(ownerId).orElseThrow(EntityNotFoundException::new);
        pet.setOwner(owner);
        Pet newPet = petRepository.save(pet);

        // update owner
        List<Pet> pets = owner.getPets();
        if (pets == null) {
            pets = new ArrayList<>();
        }
        if (!pets.contains(newPet)) {
            pets.add(newPet);
            owner.setPets(pets);
        }
        return newPet;
    }

    public List<Pet> list(){
        return petRepository.findAll();
    }

    public Pet findById(Long id) throws ObjectNotFoundException {
        return petRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Pet> findByOwnerId(Long ownerId){
        Customer owner = customerRepository.findById(ownerId).orElseThrow(EntityNotFoundException::new);
        return petRepository.findByOwner(owner);
    }
}
