package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.model.Customer;
import com.codeforall.online.javabank.model.account.Account;

import java.util.List;

public interface AccountDao {

    //methods for CRUD operations
    //CREATE
    void create(Account account);
    //READ
    Account findById(int id);
    List<Account> findAllAccountsFromCustomer(Customer customer);
    //UPDATE
    void updateAndSave(Account account);
    //DELETE
    void delete(Account account);

}

