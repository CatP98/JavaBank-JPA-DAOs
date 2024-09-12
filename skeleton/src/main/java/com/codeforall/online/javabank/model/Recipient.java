package com.codeforall.online.javabank.model;

import jakarta.persistence.*;

/**
 * A class with represents a recipient
 * A Customer can have multiple Recipients, but each Recipient is associated with only one Customer.
 * ManyToOne relationship from Recipient to Customer.
 */
@Entity
@Table(name="recipients")
public class Recipient extends AbstractModel{

    private String name;
    private Integer accountNumber;
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "customer_id", nullable = false) // Foreign key column
    private Customer customer;


    /**
     * Get the account number
     * @return the account number
     */
    public Integer getAccountNumber() {
        return accountNumber;
    }

    /**
     * Get the recipient name
     * @return the recipient name
     */
    public String getName() {
        return name;
    }

    /**
     * Get the description
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the account number to the given number
     * @param accountNumber
     */
    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * Set the recipient name to the given name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the description to the given description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
