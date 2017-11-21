package it.unicas.sensiplusConfigurationManager.model.dao.mySql;


import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOSensingElement;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Antonio on 21/11/2017.
 */
public class SensingElementDAOMySQLImpl implements DAOSensingElement<SensingElement> {

    private SensingElementDAOMySQLImpl(){}

    private static DAOSensingElement dao = null;
    private static Logger logger = null;
    public static DAOSensingElement getInstance(){
        if (dao == null){
            dao = new SensingElementDAOMySQLImpl();
            logger = Logger.getLogger(SensingElementDAOMySQLImpl.class.getName());

        }
        return dao;
    }



    @Override
    public List<SensingElement> select(SensingElement a) throws DAOException {
        return null;
    }

    @Override
    public void update(SensingElement a) throws DAOException {

    }

    @Override
    public void insert(SensingElement a) throws DAOException {

    }

    @Override
    public void delete(SensingElement a) throws DAOException {

    }
}
