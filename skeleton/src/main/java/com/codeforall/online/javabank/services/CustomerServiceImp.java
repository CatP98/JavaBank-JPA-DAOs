package com.codeforall.online.javabank.services;

import com.codeforall.online.javabank.dao.CustomerDaoImpl;
import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.exceptions.TransactionInvalidException;
import com.codeforall.online.javabank.model.Address;
import com.codeforall.online.javabank.model.Customer;
import com.codeforall.online.javabank.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerDaoImpl dao ;

    @Autowired
    private AccountService accountService;

    @Override
    public Customer get(int customerId) throws CustomerNotFoundException {
    Customer customer = dao.findById(customerId);
    if(customer == null){
        throw new CustomerNotFoundException();
    }
    return customer;
    }

    @Override
    public List<Customer> list() throws CustomerNotFoundException {
       List <Customer> customers = dao.findAll();

        if(customers == null || customers.isEmpty()){
            throw new CustomerNotFoundException();
        }
        return customers;
    }

    @Override
    public double getBalance(int customerId) throws CustomerNotFoundException {
        Customer customer = get(customerId);
        double totalBalance = 0;

        for(Account account : customer.getAccounts()) {
            totalBalance += account.getBalance();
        }
        return totalBalance;
    }

    @Override
    public boolean addAccount(Account account) {
        try {
            Account existingAccount = accountService.get(account.getId());

            if (existingAccount != null) {

                for(Customer customer : existingAccount.getCustomers()) {
                    customer.getAccounts().remove(existingAccount);
                    dao.update(customer);
                    return true;
                }
            }
            return false;

        }catch (AccountNotFoundException | TransactionInvalidException e) {
            throw new RuntimeException(e);
    }
    }

    @Override
    public boolean removeAccount(Account account) throws AccountNotFoundException, TransactionInvalidException {
        try {

            Account accountToRemove = accountService.get(account.getId());
            if (accountToRemove == null) {
                return false;
            }

            for (Customer customer : accountToRemove.getCustomers()) {
                customer.getAccounts().remove(accountToRemove);
                dao.update(customer);
            }
            accountService.delete(accountToRemove);
            return true;
        } catch (AccountNotFoundException e) {
            return false;
        } catch (TransactionInvalidException e) {
            throw new TransactionInvalidException();
        }
    }


    /**
     * Add a given customer to customer list
     *
     * @param customer the customer to add
     * @return the customer
     */
    @Override
    public Customer addCustomer(Customer customer, Address address) {
        customer.setAddress(address);
        dao.create(customer); ;

        return dao.update(customer);
    }

    @Override
    public void openAccount(Integer id, Account account) throws CustomerNotFoundException, TransactionInvalidException {

        Customer customer = get(id);
        if(customer == null){
            throw  new CustomerNotFoundException();
        }
        accountService.add(account);
        customer.addAccount(account);
        dao.update(customer);

    }

    @Override
    public int getCustomerIdFromAccount(int accountId) throws TransactionInvalidException, AccountNotFoundException {
        Account account = accountService.get(accountId);
        if(account == null){
          throw new AccountNotFoundException();
        }
        for(Customer customer : account.getCustomers()){
            return customer.getId();
        }
        throw new AccountNotFoundException();
    }

    @Override
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
