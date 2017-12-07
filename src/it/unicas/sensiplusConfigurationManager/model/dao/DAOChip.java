package it.unicas.sensiplusConfigurationManager.model.dao;

import java.util.List;

/**
 * Created by Antonio on 06/12/2017.
 */
public interface DAOChip<T>{
    List<T> select (T a) throws  DAOException;
    String selectFamilyofChip (T a) throws DAOException;
}
