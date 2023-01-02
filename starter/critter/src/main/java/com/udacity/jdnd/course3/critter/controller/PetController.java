package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import com.udacity.jdnd.course3.critter.service.PetService;
import javassist.tools.rmi.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = petService.save(convertPetDTOToPet(petDTO), petDTO.getOwnerId());
        return convertPetToPetDTO(pet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId){
        try {
            Pet pet = petService.findById(petId);
            return convertPetToPetDTO(pet);
        } catch (ObjectNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.list();
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets) {
            petDTOS.add(convertPetToPetDTO(pet));
        }
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findByOwnerId(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();
        for (Pet pet : pets) {
            petDTOS.add(convertPetToPetDTO(pet));
        }
        return petDTOS;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        return pet;
    }
}
