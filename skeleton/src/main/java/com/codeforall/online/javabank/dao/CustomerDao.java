package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.model.Address;
import com.codeforall.online.javabank.model.Customer;

import java.util.List;

/*
interface defining CRUD operations
 */
public interface CustomerDao {

    // basic crud methods
    void create(Customer customer); //CREATE
    List<Customer> findAll(); //READ
    Customer findById(int id); //READ
    Customer update(Customer customer); //UPDATE
    void delete(Integer id); //DELETE

    // additional methods
    Customer findByName(String firstname, String lastName);
    Customer findByEmail(String email);
    List<Customer> findByAddress(Address address);  // given an address we can fetch the customer(s) with that address, since more than one customer can have the same address
}
