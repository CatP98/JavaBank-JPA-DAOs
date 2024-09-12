package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.model.Address;
import com.codeforall.online.javabank.model.Customer;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
implementation of the CustomerDao interface using EntityManager
 */
@Repository //Marks CustomerDaoImpl as a DAO component, making it eligible for component scanning and dependency injection.
@Transactional // all database operations within the methods of this class are executed within a transaction context. If any method throws an exception, the transaction will be rolled back.
public class CustomerDaoImpl implements CustomerDao {

    @PersistenceContext //Injects the EntityManager instance into the emf, allowing the class to interact with the database using JPA.
    private EntityManager em;

/*
    public CustomerDaoImpl(EntityManagerFactory emf){
        this.emf = emf;
    }
 */

    @Override
    public void create(Customer customer) {

        //EntityManager em = emf.createEntityManager();

        try{
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        } catch(RollbackException e){
            em.getTransaction().rollback();
        } finally {
            if( em != null){
                em.close();
            }
        }
    }

    @Override
    public List<Customer> findAll() {
        // open a new connection to the database
       // EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Customer findById(int id) {
        // open a new connection to the database
        //EntityManager em = emf.createEntityManager();

        //Bc it's just a read method, there's no need to start a transaction
        try {
            // fetch the customer, by id
            Customer customer = em.find(Customer.class, id);

            if(customer == null){
                new CustomerNotFoundException();
            }
            return customer;

        } finally {
            // make sure to close the database connection
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public Customer update(Customer customer) {
       // EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin(); // open transaction
            Customer savedCustomer = em.merge(customer);
            em.getTransaction().commit(); // close transaction
            return savedCustomer;

        } catch (RollbackException ex) {
            // something went wrong, make sure DB is consistent
            em.getTransaction().rollback();
            return null;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        //Customer customer = findById(id); //-> Is it good practice to call findById() method? since em is in that method scope, and we will need the EntutyManager to start a transaction in order to delete the customer?
        //EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Customer customer = em.find(Customer.class, id);
            if (customer != null) {
                em.remove(customer);
            } else {
                em.getTransaction().rollback();
                new CustomerNotFoundException();
            }
            em.getTransaction().commit();
        } catch (RollbackException e) {
            em.getTransaction().rollback();
            throw e;
        }
        finally {
            if (em != null){
                em.close();
            }
        }
    }

    //using jpql -> a simple query that doesn’t need to be dynamic, names probably won't change at a runtime
    @Override
    public Customer findByName(String firstName, String lastName) {
       // EntityManager em = emf.createEntityManager();
        //JPQL query tells Hibernate that "c" is the alias, the instance of the entity Customer
        String jpqlQuery = "SELECT c FROM Customer c WHERE c.firstName = :firstName AND c.lastName = :lastName";

        try{
            return em.createQuery(jpqlQuery, Customer.class)
                    .setParameter("firstName", firstName)
                    .setParameter("lastName", lastName)
                    .getSingleResult();

        } catch (NoResultException e) {
           return null;
        }
        finally {
            if( em != null){
                em.close();
            }
        }
    }

    //using criteria query -> building more complex queries that depend on runtime changing conditions (probably not this case, but just to try)
    @Override
    public Customer findByEmail(String email) {
        //EntityManager em = emf.createEntityManager();

        //create CriteriaBuilder instance from the EntityManager + make the builder create the cretiria query obj, specifuing the result type -> we want a Customer
        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Customer> criteriaQuery = builder.createQuery(Customer.class);

        //Define the root entity (entity from which all query starts)
        Root<Customer> customer = criteriaQuery.from(Customer.class);

        //customer.get("email"): accesses the email attribute of the Customer entity, returns a Path<String> representing the attribute.
        //builder.equal(customer.get("email"), email): creates a Predicate that represents the condition email = :email.
        //criteriaQuery.where(...): adds the Predicate to the CriteriaQuery, specifying the WHERE clause of the query.
        criteriaQuery.where(builder.equal(customer.get("email"), email));

        //em.createQuery(criteriaQuery): creates a TypedQuery<Customer> from the CriteriaQuery that was constructed. The TypedQuery is used to execute the query+ retrieve the results.
        List<Customer> results = em.createQuery(criteriaQuery).getResultList();

        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }

    @Override
    public List<Customer> findByAddress(Address address) {
       // EntityManager em = emf.createEntityManager();
        //Getr CriteriaBuilder and CriteriaQuery

        try {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Customer> query = builder.createQuery(Customer.class);

        //Define the root entity
        Root<Customer> customer = query.from(Customer.class);

        // Define the condition. Comparing the entire address object
            Predicate addressPredicate = builder.equal(customer.get("address"), address);

            // Set the condition in the query
            query.where(addressPredicate);

        TypedQuery<Customer> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();

        } finally {
            if (em != null) {
             em.close();
            }
        }
    }

}
