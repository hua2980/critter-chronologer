package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long> {
}
