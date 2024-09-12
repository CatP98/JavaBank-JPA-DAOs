package com.codeforall.online.javabank.services;


import com.codeforall.online.javabank.dao.AccountDao;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.exceptions.TransactionInvalidException;
import com.codeforall.online.javabank.model.account.Account;

public class AccountServiceImpl  implements AccountService{
    AccountDao dao;


    @Override
    public Account add(Account account) throws TransactionInvalidException {
        if(account == null){
            throw new TransactionInvalidException();
        }
        dao.create(account);
        return account;
    }

    @Override
    public void deposit(int id, double amount) throws AccountNotFoundException {
        Account account = dao.findById(id);

        if(account == null ){
            throw new AccountNotFoundException();
        }

        account.setBalance(account.getBalance() + amount);
        dao.updateAndSave(account);
    }

    @Override
    public void withdraw(int id, double amount) throws AccountNotFoundException, TransactionInvalidException {
        if(amount <= 0) {
            throw  new TransactionInvalidException();
        }
        Account account = dao.findById(id);
        if(account == null){
            throw new AccountNotFoundException();
        }
        if(account.getBalance() < amount){
            throw new TransactionInvalidException();
        }
        account.setBalance(account.getBalance() - amount);
        dao.updateAndSave(account);

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) throws AccountNotFoundException, TransactionInvalidException {
        if(amount<= 0){
            throw  new TransactionInvalidException();
        }
        Account srcAccount = dao.findById(srcId);
        Account dstAccount = dao.findById(dstId);
        if(srcAccount == null){
            throw new AccountNotFoundException();
        }
        if(dstAccount == null){
            throw new AccountNotFoundException();
        }
        if(srcAccount.getBalance() < amount){
            throw new TransactionInvalidException();
        }

        srcAccount.setBalance(srcAccount.getBalance() - amount);
        dstAccount.setBalance(dstAccount.getBalance() + amount);
        dao.updateAndSave(srcAccount);
        dao.updateAndSave(dstAccount);

    }

    @Override
    public Account get(int id) throws TransactionInvalidException, AccountNotFoundException {

        Account account = dao.findById(id);
        if(account == null){
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Override
    public void delete(Account accountToRemove) throws AccountNotFoundException {
        try {

            dao.delete(accountToRemove);
        }catch (Exception e){
        throw new AccountNotFoundException();
        }


    }

    /* we need to create some sort of checking expressions for each type of account, but where?
    public boolean canDebit(double amount){

        return true;
    }

    public boolean canWithdraw(){
        return true;
    }

     */
}


