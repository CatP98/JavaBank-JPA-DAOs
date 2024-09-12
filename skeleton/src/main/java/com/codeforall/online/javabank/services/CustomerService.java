package com.codeforall.online.javabank.services;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.exceptions.TransactionInvalidException;
import com.codeforall.online.javabank.model.Address;
import com.codeforall.online.javabank.model.Customer;
import com.codeforall.online.javabank.model.account.Account;

import java.util.List;

/**
 * Common interface for account services, provides methods to manage accounts
 */
public interface CustomerService {

    /**
     * Get the customer with the given id
     * @param customerId the customer id
     * @return the customer
     */
    Customer get(int customerId) throws CustomerNotFoundException;

    /**
     * List all customers
     * @return a list of customers
     */
    List<Customer> list() throws CustomerNotFoundException, TransactionInvalidException, AccountNotFoundException;

    /**
     * Get the total balance of a given customer accounts
     * @param customerId the customer id
     * @return the balance of all accounts of the given customer
     */
    double getBalance(int customerId) throws CustomerNotFoundException;

    /**
     * Add a given customer to customer list
     *
     * @param customer the customer to add
     * @return the customer
     */
    Customer addCustomer(Customer customer, Address adress);

    // ----------- bc Customer is the parent of the customer/account relationship
    boolean addAccount(Account account);
    boolean removeAccount(Account account) throws AccountNotFoundException, TransactionInvalidException;


    /**
     * Add given account to a customer and account service lists
     * @param id the customer id to associate the account with
     * @param account the account to be added
     */
    void openAccount(Integer id, Account account) throws TransactionInvalidException, CustomerNotFoundException;

    /**
     * Get the if of the customer related to that account
     * @param accountId to search for
     * @return the id of the customer
     */
    public int getCustomerIdFromAccount(int accountId) throws TransactionInvalidException, AccountNotFoundException;

    /**
     * Sets the account service
     * @param accountService
     */
    public void setAccountService(AccountService accountService);
}