package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.model.transaction.Transaction;

import java.util.List;

public interface TransactionDao {

    public List<Transaction> findAllByAccountId(int accountId) throws AccountNotFoundException;

}