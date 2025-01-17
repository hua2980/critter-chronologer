package com.udacity.jdnd.course3.critter.data.entity;

import com.udacity.jdnd.course3.critter.data.PetType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table
public class Pet {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private PetType type;

    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private Customer owner;

    private LocalDate birthDate;

    private String notes;

    public Pet() {}

    public Pet(PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Pet(Long id, PetType type, String name, Customer owner, LocalDate birthDate, String notes) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.owner = owner;
        this.birthDate = birthDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
