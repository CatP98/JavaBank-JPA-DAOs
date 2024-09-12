package com.codeforall.online.javabank.model;

import com.codeforall.online.javabank.model.account.Account;
import jakarta.persistence.*;
import jdk.jfr.Name;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An entity class representing the database table.
 * The parent/owner of the relationship customers/account
 */
@Entity
@Table(name = "customers")
public class Customer extends  AbstractModel{

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    @Column(name = "photo_url")
    private String photoURL;
    @Column(name = "total_balance")
    private double totalBalance;

    @Embedded
    private Address address;   //How to establish a Many-to-one relationship, case we don't want Address to be a table?— multiple customers can share the same address, but each customer cannot have more than one address.

    @ManyToMany(mappedBy = "customers") // Many to many: A customer can have more than one account, more than one customer can have the same account
    private Set<Account> accounts = new HashSet<>();

    // Universally Unique Identifier --> watch video Raquel
    @OneToMany(mappedBy = "customers")
    private Set<Recipient> recipients = new HashSet<>();


    //getters and setters  -> How Hibernate accesses the data from the db

    /**
     * Get the customer first name
     * @return the customer first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get the customer last name
     * @return the customer last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get the customer email
     * @return the customer email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the customer phone
     * @return the customer phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Get the customer photo URL
     * @return the customer photo URL
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     * Get the total balance
     * @return the total balance
     */
    public double getTotalBalance() {
        return totalBalance;
    }

    /**
     * Get the customer's address
     * @return the customer's address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * List all customer accounts
     * @return List of accounts
     */
    public Set<Account> getAccounts() {
        return accounts;
    }

    /**
     * List all customer recipients
     * @return List of recipients
     */
    public Set<Recipient> getRecipients() {
        return recipients;
    }

    /**
     * Set the customer first name to the given name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Set the customer last name to the given name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Set the customer email to the given email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the customer phone to the given phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Set the customer photoURL to the given URL
     * @param photoURL given
     */
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    /**
     * Set the totalBalance to the given balance
     * @param balance
     */
    public void setTotalBalance(double balance) {
        this.totalBalance = balance;
    }

    /**
     * Set the customer address to the given address
     * @param address given
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Set the accounts to the given account list
     * @param accounts
     */
    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    /**
     * Set the recipients to the given recipient list
     * @param recipients
     */
    public void setRecipients(Set<Recipient> recipients) {
        this.recipients = recipients;
    }

    public void addAccount(Account account){
        if(account != null){
            this.accounts.add(account);
        }
    }

}



