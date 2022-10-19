package com.example.model.users;

import lombok.Getter;

import javax.persistence.Entity;

@Getter
@Entity
public class Admin extends Person {
    @Override
    public String toString() {
        return super.toString() + "]";
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}