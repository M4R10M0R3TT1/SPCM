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
 * Created by on 21/11/2017.
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
                lista.add(new SensingElement(rs.getString("IdSensingElement"),
                        rs.getInt("rSense"),
                        rs.getInt("inGain"),
                        rs.getInt("outGain"),
                        rs.getString("contacts"),
                        rs.getInt("frequency"),
                        rs.getString("harmonic"),
                        rs.getInt("dcBias"),
                        rs.getString("modeVI"),
                        rs.getString("measureTechnique"),
                        rs.getString("measureType"),
                        rs.getInt("filter"),
                        rs.getString("phaseShiftMode"),
                        rs.getInt("phaseShift"),
                        rs.getString("iq"),
                        rs.getInt("conversionRate"),
                        rs.getString("inPortADC"),
                        rs.getInt("nData"),
                        rs.getString("measureUnit")));
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
        //idSensingElement, rSense, inGain, outGain, contacts, frequency, harmonic, dcBias, modeVI,
        // measureTechnique,measureType, filter, phaseShiftMode, phaseShift, iq, conversionRate, inPortADC, nData, measureUnit

        if (a == null || a.getIdSensingElement() == null){
            throw new DAOException("If you want to insert a new SensingElement, the field idSensingElement cannot be null!");
        }

//('OFFCHIP_VOC',500,40,7,'TWO',78125,'FIRST_HARMONIC',0,'VOUT_IIN','EIS','CAPACITANCE',1,'Quadrants',0,'IN_PHASE',50,'IA',1,'%')
        String sql = "INSERT INTO SensingElement (idSensingElement, rSense, inGain, outGain, contacts, frequency, harmonic, dcBias, modeVI, measureTechnique, measureType, filter, phaseShiftMode, phaseShift, iq, conversionRate, inPortADC, nData, measureUnit) VALUES" +
                "  ('" + a.getIdSensingElement() + "', " + a.getrSense() + ", " +
                a.getInGain() + ", " + a.getOutGain() + ", '" +
                a.getContacts() + "', " + a.getFrequency() + ", '" +
                a.getHarmonic() + "', " + a.getDcBias() + ", '" +
                a.getModeVI() + "', '" + a.getmeasureTechnique() + "', '"+
                a.getMeasureType() + "', " + a.getFilter() + ", '" +
                a.getPhaseShiftMode() + "', " + a.getPhaseShift() + ", '" +
                a.getIq() + "', " + a.getConversionRate() + ", '" +
                a.getInPortADC() + "', " + a.getnData() + ", '" +
                a.getMeasureUnit() + "')";


        logger.info("SQL: " + sql);

        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insert(): " + e.getMessage());
        }
    }

    @Override
    public void delete(SensingElement a) throws DAOException {
        /*if (a == null || a.getIdSensingElement() == null){
            throw new DAOException("In delete you have to specify at least the idSensingElement field!");
        }*/
        String sql = "DELETE FROM SensingElement WHERE IdSensingElement='" + a.getIdSensingElement() + "';";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In delete(): " + e.getMessage());
        }
    }
}
