package com.codeforall.online.javabank.dao;

import com.codeforall.online.javabank.exceptions.CustomerNotFoundException;
import com.codeforall.online.javabank.exceptions.JavaBankException;
import com.codeforall.online.javabank.model.Customer;
import com.codeforall.online.javabank.model.Establishment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.RollbackException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class EstablishmentDaoImpl implements EstablishmentDao{

    @PersistenceContext
    private EntityManager em;
    //EntityManagerFactory emf


    /*
    using @Transactional annotation to manage transactions automatically. This removes the need to manually begin, commit, or rollback transactions.
           @PersistenceContext manages the lifecycle of the EntityManager. You don't need to manually close it; the container will handle that.

    @Override
    @Transactional
    public boolean create(Establishment establishment) {
        try {
            em.persist(establishment);
            return true;
        } catch (Exception e) {
            // Log exception
            return false;
        }
    }
     */
    @Override
    public boolean create(Establishment establishment) {
        //EntityManager em = emf.createEntityManasger()
        try{
            em.getTransaction().begin();
            em.persist(establishment);
            em.getTransaction().commit();

            return true;

        } catch (RollbackException e){
            em.getTransaction().rollback();
            return false;

        } finally {
            if( em != null){
                em.close();
            }
        }
    }

    @Override
    public Establishment findById(int id) {
        try {
            // fetch the customer, by id
            Establishment establishment = em.find(Establishment.class, id);

            if (establishment == null) {
                new JavaBankException("Error deleting establishment: Establishment not found");
            }
            return establishment;

        } finally {
            if (em != null) {
                em.close();
            }
            return null;
        }
    }

    @Override
    public List<Establishment> findAllEstablishments() {
        // EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT e FROM Establishment e", Establishment.class).getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /*
    @Override
    @Transactional
    public boolean updateAndSave(Establishment establishment) {
        try {
            em.merge(establishment);
            return true;
        } catch (Exception e) {
            // Log exception
            return false;
        }
    }
     */

    @Override
    public Establishment updateAndSave(Establishment establishment) {
        try {
            em.getTransaction().begin(); // open transaction
            Establishment savedEstablishment = em.merge(establishment);
            em.getTransaction().commit(); // close transaction
            return savedEstablishment;

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

    /*
    @Override
    @Transactional
    public boolean delete(Establishment establishment) {
        try {
            Establishment toBeRemoved = em.find(Establishment.class, establishment.getId());
            if (toBeRemoved != null) {
                em.remove(toBeRemoved);
                return true;
            }
            return false;
        } catch (Exception e) {
            // Log exception
            return false;
        }
    }

     @Override
    @Transactional
    public boolean deleteById(Integer id) {
        try {
            Establishment toBeRemoved = em.find(Establishment.class, id);
            if (toBeRemoved != null) {
                em.remove(toBeRemoved);
                return true;
            }
            return false;
        } catch (Exception e) {
            // Log exception
            return false;
        }
    }
    }
     */

    @Override
    public boolean delete(Establishment establishment) {
        //EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Establishment establishmentToBeRemoved = em.find(Establishment.class, establishment.getId());
            if (establishmentToBeRemoved != null) {
                em.remove(establishmentToBeRemoved);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                new JavaBankException("Establishment not Found. Operation cancelled");
                return false;
            }
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

    @Override
    public boolean deleteById(Integer id) {
        try {
            em.getTransaction().begin();
            Establishment establishmentToBeRemoved = em.find(Establishment.class, id);
            if (establishmentToBeRemoved != null) {
                em.remove(establishmentToBeRemoved);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                new JavaBankException("Establishment not Found. Operation cancelled");
                return false;
            }

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
}
