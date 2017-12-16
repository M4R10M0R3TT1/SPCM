package it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface;

import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.util.List;


public interface DAOFamily <T>{
    List<T> selectFamily(int a) throws DAOException;
}
