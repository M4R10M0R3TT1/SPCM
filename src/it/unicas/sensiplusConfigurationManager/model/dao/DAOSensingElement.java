package it.unicas.sensiplusConfigurationManager.model.dao;

import java.util.List;

/**
 * Created by Antonio on 21/11/2017.
 */
public interface DAOSensingElement <T> {
    List<T> select(T a) throws DAOException;
    void update(T a) throws DAOException;
    void insert(T a) throws DAOException;
    void delete(T a) throws DAOException;
}
