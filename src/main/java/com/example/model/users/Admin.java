package com.example.model.users;

import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class Admin extends Person {
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString() + "]";
    }
}