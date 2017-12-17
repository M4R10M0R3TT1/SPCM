package it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface;

import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.util.List;

/**
 * Created by Antonio on 17/12/2017.
 */
public interface DAOSensingElement<T> {
    List<T> selectSensingElement(int a) throws DAOException;
}
