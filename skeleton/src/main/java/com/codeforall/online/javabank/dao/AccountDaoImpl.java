package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.exceptions.AccountNotFoundException;
import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.model.Customer;
import com.codeforall.online.javabank.model.account.Account;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class AccountDaoImpl implements AccountDao{

    @PersistenceContext
    private EntityManager em;

    /*
    EntityManagerFactory emf;

    public AccountDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

     */

    @Override
    public void create(Account account) {
        //EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(account);
            em.getTransaction().commit();

        } catch (RollbackException e){
            em.getTransaction().rollback();
        }
        finally {
            if( em != null){
                em.close();
            }
        }
    }

    @Override
    public Account findById(int id) {
        //EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            Account account = em.find(Account.class, id);
            if( account == null) {
                new AccountNotFoundException();
            }
            return account;
        } catch (RollbackException e){
            em.getTransaction().rollback();
        }
        finally {
            if( em != null){
                em.close();
            }
        }
        return null;
    }

    @Override
    public List<Account> findAllAccountsFromCustomer(Customer customer) {
        if (customer == null) {
            new CustomerNotFoundException();
        }

        //EntityManager em = emf.createEntityManager();
        List<Account> accounts = null;

        //Eventhough it's a read op, we'll start a transaction, since it might be a long process to fetch allk accounts, so there might be updates during the read operation
        try {
            em.getTransaction().begin();
            // Create a JPQL query to find accounts associated with the customer
            String jpqlQuery = "SELECT a FROM Account a JOIN a.customers c WHERE c.id = :customerId";
            accounts = em.createQuery(jpqlQuery, Account.class)
                    .setParameter("customerId", customer.getId()).getResultList();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return accounts;
    }

    @Override
    public void updateAndSave(Account account) {
        if(account == null){
            new AccountNotFoundException();
        }
        //EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.merge(account);
            em.getTransaction().commit();

        } catch (RollbackException e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback in case of failure
            }

        } finally {
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }

    @Override
    public void delete(Account account) {

        if(account == null) {
            new AccountNotFoundException();
        }
        //EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();

            //first we need to find the account from the database to ensure it exists
            Account foundAccount = em.find(Account.class, account.getId());
            if (foundAccount == null) {
                throw new AccountNotFoundException();
            }
            em.remove(foundAccount);
            em.getTransaction().commit();

        } catch (RollbackException e){
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback in case of failure
            }

        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }
}
