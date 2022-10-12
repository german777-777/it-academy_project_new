package com.example.model.users;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Entity;

@Getter
@EqualsAndHashCode(callSuper = true)
@Entity
public class Admin extends Person {
    @Override
    public String toString() {
        return super.toString() + "]";
    }
}