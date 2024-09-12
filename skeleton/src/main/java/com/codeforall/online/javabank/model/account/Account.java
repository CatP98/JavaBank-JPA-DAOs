package com.codeforall.online.javabank.model.account;

import com.codeforall.online.javabank.model.AbstractModel;
import com.codeforall.online.javabank.model.Customer;
import jakarta.persistence.*;
import java.util.Set;

/**
 * A generic account domain entity to be used as a base for concrete types of accounts
 */
@Entity
@Table(name="accounts")
public abstract class Account extends AbstractModel {

    private double balance;

    @Enumerated(EnumType.STRING)
    private AccountType account_type;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}) // CascadeType is not ALL, since we don't want the account to be deleted if a customer removes an account -> there can be more customer(s) using it
    @JoinTable(
            name="customers_account",
            joinColumns = @JoinColumn(name="account_id"),
            inverseJoinColumns = @JoinColumn(name="customer_id"))
    private Set<Customer> customers; // = new HashSet<>(); //check this


    //getters and setters
    /**
     * Get the account type
     * @return the account type
     */
    public abstract AccountType getAccountType();

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}