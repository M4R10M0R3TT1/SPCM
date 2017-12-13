package it.unicas.sensiplusConfigurationManager.model.dao;

import java.util.List;

/**
 * Created by Antonio on 13/12/2017.
 */
public interface DAOCluster<T> {
    List<T> select(T a) throws DAOException;
}
