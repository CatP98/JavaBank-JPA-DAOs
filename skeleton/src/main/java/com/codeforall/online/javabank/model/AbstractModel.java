package com.codeforall.online.javabank.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/*
    Maps each concrete class to its own table
    For sharing state and mapping info between entities
    Model classes that will be extended from AbstractModel: Customer, Account, Recients, Establishments, purchases and Transdactions
 */

@MappedSuperclass //this class is not going to be a table, but it has subclasses that are going to have different tables
public abstract class AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Version
    private Integer version;

    @CreationTimestamp
    private Date creationTime;

    @UpdateTimestamp
    private Date updateTime;


    // Getters and setters
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}