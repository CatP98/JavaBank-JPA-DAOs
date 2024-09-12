package com.codeforall.online.javabank.dao;


import com.codeforall.online.javabank.model.Establishment;

import java.util.List;

public interface EstablishmentDao {
    //methods for CRUD operations //!!DOES IT MAKE MORE SENSE FOR THE METHODS TO RETRIEVE A BOOLEAN INFORMING IF THE OPERATIONS OCCURED SUCCESSFUKLY?
    //CREATE
    boolean create(Establishment establishment);
    //READ
    Establishment findById(int id);
    List<Establishment> findAllEstablishments();
    //UPDATE
    Establishment updateAndSave(Establishment establishment);
    //DELETE
    boolean delete(Establishment establishment);
    boolean deleteById(Integer id);
}
