package com.codeforall.online.javabank.model;

import jakarta.persistence.*;

/**
 * A class with represents an establishment
 */
@Entity
@Table(name = "customers")
public class Establishment extends AbstractModel{

    private String name;

    /**
     * Get the establishment name
     * @return the establishment name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the establishment name to the given name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
