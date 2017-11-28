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

           // String sql = "SELECT s.*,fc.`_idFamily`,f.family_Name,fc.se_Port FROM SensingElement s,Family f,familyconfig fc WHERE s.IdSensingElement=fc.`_seName` and fc.`_idFamily`=f.IdFamily";

            String sql = "SELECT * FROM SPSensingElement";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new SensingElement(rs.getString("idSPSensingElement"),
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
                        rs.getString("name"),
                        rs.getDouble("rangeMin"),
                        rs.getDouble("rangeMax"),
                        rs.getDouble("defaultAlarmThreshold"),
                        rs.getInt("multiplier"),
                        rs.getString("measureUnit")
                        ));
            }
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public List<SensingElement> selectSeFamily(SensingElement a) throws DAOException {

        ArrayList<SensingElement> lista = new ArrayList<>();
        String se_selected=a.toString();
        try {
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT f.id,f.name,p.name,p.internal FROM SPFamilyTemplate ft,SPFamily f,SPPort p,SPSensingElementOnFamily sf\n" +
                    "WHERE sf.SPSensingElement_idSPSensingElement='"+se_selected+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate AND ft.SPFamily_idSPFamily=f.idSPFamily\n" +
                    "AND ft.SPPort_idSPPort=p.idSPPort";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new SensingElement(
                        rs.getString("id"),
                        rs.getString("f.name"),
                        rs.getString("p.name"),
                        rs.getBoolean("internal")));
            }
            DAOMySQLSettings.closeStatement(st);
            //System.out.println("Valore di a: "+se_selected);
            /*for(SensingElement item : lista) {
                System.out.println("Risultato query: " + item._idFamilyProperty() + "\n");
            }*/

        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public void update(SensingElement a) throws DAOException {
       /* if (a == null || a.getCognome() == null
                || a.getNome() == null
                || a.getEmail() == null
                || a.getCompleanno() == null
                || a.getTelefono() == null){
            throw new DAOException("In select: any field can be null");
        }*/

        String query = "UPDATE SPSensingElement SET " +
                "rSense='"+ a.getrSense().intValue() +
                "', inGain='"+ a.getInGain()+
                "', outGain='"+ a.getOutGain()+
                "', contacts ='"+ a.getContacts()+
                "', frequency ='"+ a.getFrequency()+
                "', harmonic ='"+ a.getHarmonic()+
                "', dcBias ='"+ a.getDcBias()+
                "', modeVI ='"+ a.getModeVI()+
                "', measureTechnique ='"+ a.getmeasureTechnique()+
                "', measureType ='"+ a.getMeasureType()+
                "', filter ='"+ a.getFilter()+
                "', phaseShiftMode ='"+ a.getPhaseShiftMode()+
                "', phaseShift ='"+ a.getPhaseShift()+
                "', iq ='"+ a.getIq()+
                "', conversionRate ='"+ a.getConversionRate()+
                "', inPortADC ='"+ a.getInPortADC()+
                "', nData ='"+ a.getnData()+
                "', name = '"+ a.getName() +
                "', rangeMin = '"+ a.getRangeMin() +
                "', rangeMax = '"+ a.getRangeMax() +
                "', defaultAlarmThreshold = '"+ a.getDefaultAlarmThreshold() +
                "', multiplier = '"+ a.getMultiplier() +
                "', measureUnit ='"+ a.getMeasureUnit()+
                "' WHERE idSPSensingElement='"+a.getIdSensingElement()+"'";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(query);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In update(): " + e.getMessage());
        }
    }



    @Override
    public void insert(SensingElement a) throws DAOException {
        //idSensingElement, rSense, inGain, outGain, contacts, frequency, harmonic, dcBias, modeVI,
        // measureTechnique,measureType, filter, phaseShiftMode, phaseShift, iq, conversionRate, inPortADC, nData, measureUnit

        //('OFFCHIP_VOC',500,40,7,'TWO',78125,'FIRST_HARMONIC',0,'VOUT_IIN','EIS','CAPACITANCE',1,'Quadrants',0,'IN_PHASE',50,'IA',1,'%')
        String sql = "INSERT INTO SPSensingElement (idSPSensingElement, rSense, inGain," +
                " outGain, contacts, frequency, harmonic, dcBias, modeVI, measureTechnique," +
                " measureType, filter, phaseShiftMode, phaseShift, iq, conversionRate," +
                " inPortADC, nData,name,rangeMin,rangeMax,defaultAlarmThreshold,multiplier, measureUnit) VALUES" +
                "('" + a.getIdSensingElement() + "', " + a.getrSense() + ", " +
                a.getInGain() + ", " + a.getOutGain() + ", '" +
                a.getContacts() + "', " + a.getFrequency() + ", '" +
                a.getHarmonic() + "', " + a.getDcBias() + ", '" +
                a.getModeVI() + "', '" + a.getmeasureTechnique() + "', '"+
                a.getMeasureType() + "', " + a.getFilter() + ", '" +
                a.getPhaseShiftMode() + "', " + a.getPhaseShift() + ", '" +
                a.getIq() + "', " + a.getConversionRate() + ", '" +
                a.getInPortADC() + "', " + a.getnData() + ", '" +
                a.getName()+"',"+ a.getRangeMin() + ", "+
                a.getRangeMax()+ ","+ a.getDefaultAlarmThreshold() +"," +
                a.getMultiplier() +",'"+ a.getMeasureUnit() +"')";

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
        String sql = "DELETE FROM spsensingelement WHERE idSPSensingElement='" + a.getIdSensingElement() + "'";
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
