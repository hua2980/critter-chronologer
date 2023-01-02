package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.entity.Customer;
import com.udacity.jdnd.course3.critter.data.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends CrudRepository<Pet, Long>, JpaRepository<Pet, Long>{
    List<Pet> findByOwner(Customer owner);
}
