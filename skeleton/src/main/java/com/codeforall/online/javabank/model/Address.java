package com.codeforall.online.javabank.model;

import jakarta.persistence.Embeddable;

import java.util.Set;
@Embeddable
public class Address {
    //private Set<Customer> customers_id; //Since an Address can have more than one customer, each address will contain a set list of the customer ids thar\t share the address -> since addrss it's not an entity, it's not necessary
    private String street;
    private String city;
    private String zipcode;


    /**
     * Get the street
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Get the city
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the zip code
     * @return the zip code
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Set the street to the given street
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Set the city to the given city
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Set the zip code to the given zip code
     * @param zipcode
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
