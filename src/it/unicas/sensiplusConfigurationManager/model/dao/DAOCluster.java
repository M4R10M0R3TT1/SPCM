package it.unicas.sensiplusConfigurationManager.model.dao;

import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;

import java.util.List;

/**
 * Created by Di Tano Fernando e Damiano on 13/12/2017.
 */
public interface DAOCluster<T> {
    List<T> select(T a) throws DAOException;
    List<T> selectConfiguration(T a) throws DAOException;
    List<T> selectChip(T a) throws DAOException;
    void delete(T a) throws DAOException;
    void insertConfiguration(T a) throws DAOException;
    void updateConfiguration(T a) throws DAOException;
    void deleteConfiguration(T a) throws DAOException;
    List<String> selectCalInDialog() throws  DAOException;
    void insertCluster(T a) throws DAOException;
    void removeCalibrationOnCluster(T a) throws DAOException;
}
