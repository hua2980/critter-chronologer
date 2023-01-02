package com.udacity.jdnd.course3.critter.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Customer extends User{
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Pet> pets;

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String notes, List<Pet> pets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public Customer(Long id, String name, String name1, String phoneNumber, String notes, List<Pet> pets) {
        super(id, name);
        this.name = name1;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
