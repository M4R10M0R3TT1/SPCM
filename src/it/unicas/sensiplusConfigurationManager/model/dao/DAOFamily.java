package it.unicas.sensiplusConfigurationManager.model.dao;

import it.unicas.sensiplusConfigurationManager.model.Family;

import java.util.List;

/**
 * Created by Antonio on 26/11/2017.
 */
public interface DAOFamily <T>{
    List<T> select(T a) throws DAOException;
    List<T> selectPort(T a) throws DAOException;
    List<T> selectFamilyAndPort(String a) throws DAOException;
    List<T> selectAddSEOnFamily(String a) throws DAOException;
    List<T> availablePort(T a) throws DAOException;

    List<T> selectMeasureTechnique(T a) throws  DAOException;
    void update(T a) throws DAOException;
    void insert(T a) throws  DAOException;
    void delete(T a) throws DAOException;
}
