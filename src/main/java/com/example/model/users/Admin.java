package com.example.model.users;

import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Admin extends Person {
    @Override
    public String toString() {
        return super.toString() + "]";
    }
}