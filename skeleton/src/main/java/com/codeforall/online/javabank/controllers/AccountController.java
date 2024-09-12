package com.codeforall.online.javabank.controllers;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.exceptions.JavaBankException;
import com.codeforall.online.javabank.exceptions.TransactionInvalidException;
import com.codeforall.online.javabank.services.CustomerService;
import com.codeforall.online.javabank.services.TransactionService;
import com.codeforall.online.javabank.model.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller responsible for rendering {@link Account} related views
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    private TransactionService transactionService;
    private CustomerService customerService;

    /**
     * Render a view with a list of transactions connected to an account
     * @param id the account id
     * @param model the model object
     * @return the view to render
     */

    @RequestMapping(method = RequestMethod.GET, path = {"/{id}"})
    public String listTransactions(@PathVariable Integer id, Model model) throws TransactionInvalidException, AccountNotFoundException {
        model.addAttribute("customerId", customerService.getCustomerIdFromAccount(id));
        model.addAttribute("transactions", transactionService.getAccountStatement(id));
        return "account-statement";

    }

    /**
     * Set the transaction service
     * @param transactionService to set
     */
    @Autowired
    public void setTransactionService(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    /**
     * Set the customer service
     * @param customerService to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
}
