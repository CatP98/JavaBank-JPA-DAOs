package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.model.transaction.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class TransactionDaoImpl {

        private EntityManagerFactory emf;

        public List<Transaction> findAllByAccountId(int accountId) throws AccountNotFoundException {
            EntityManager em = emf.createEntityManager();
            try {
                String jpql = "SELECT t FROM Transaction t WHERE t.accountId = :accountId";
                List<Transaction> transactions = em.createQuery(jpql, Transaction.class)
                        .setParameter("accountId", accountId)
                        .getResultList();

                if (transactions.isEmpty()) {
                    throw new AccountNotFoundException();
                }

                return transactions;
            } finally {
                em.close();
            }
        }
    }