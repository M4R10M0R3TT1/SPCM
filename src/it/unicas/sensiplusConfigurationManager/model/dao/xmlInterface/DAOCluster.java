package it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface;

import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.util.List;

public interface DAOCluster<T> {
    List<T> selectCluster(int a) throws DAOException;
}
