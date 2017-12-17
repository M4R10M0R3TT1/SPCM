package it.unicas.sensiplusConfigurationManager.model.dao;

import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;

import java.util.List;

/**
 * Created by  on 21/11/2017.
 */
public interface DAOSensingElement <T> {
    List<T> select(T a) throws DAOException;
    void update(T a) throws DAOException;
    void insert(T a) throws DAOException;
    void delete(T a) throws DAOException;
    void AddSEOnPort(int port,int family,String sensingElement) throws DAOException;
    List<T> selectIntern(Boolean a) throws  DAOException;
    boolean measureControl(String se, int f) throws DAOException;
    int measureSearch(String se) throws DAOException;
    void insertMeasure(int m, int f) throws DAOException;
}
