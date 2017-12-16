package it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface;

import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.util.List;

public interface DAOConfiguration<T> {
    List<T> selectConf(int a) throws DAOException;
}
