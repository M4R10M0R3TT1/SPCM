package it.unicas.sensiplusConfigurationManager.model.dao.mySql;


import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOSensingElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by  on 21/11/2017.
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

        ArrayList<SensingElement> lista = new ArrayList<>();

        try {
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT * FROM SensingElement";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new SensingElement(rs.getString("IdSensingElement")/*,
                        rs.getInt("rSense"),
                        rs.getInt("inGain"),
                        rs.getInt("outGain"),
                        rs.getString("contacts"),
                        rs.getInt("frequency"),
                        rs.getString("harmonic"),
                        rs.getInt("dcBias"),
                        rs.getString("model"),
                        rs.getString("measureTechnique"),
                        rs.getString("measureType"),
                        rs.getInt("filter"),
                        rs.getString("phaseShiftMode"),
                        rs.getInt("phaseShift"),
                        rs.getString("iq"),
                        rs.getInt("conversionRate"),
                        rs.getString("inPortADC"),
                        rs.getInt("nData"),
                        rs.getString("measureUnit")*/));

            }
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }

        return lista;
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
