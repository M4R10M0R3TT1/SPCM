package it.unicas.sensiplusConfigurationManager.model.dao;

import java.util.List;

/**
 * Created by Antonio on 26/11/2017.
 */
public interface DAOFamily <T>{
    List<T> select(T a) throws DAOException;
    List<T> selectPort(T a) throws DAOException;
    List<T> selectMeasureTechnique(T a) throws  DAOException;
}
