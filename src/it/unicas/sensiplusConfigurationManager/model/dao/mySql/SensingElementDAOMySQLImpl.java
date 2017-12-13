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

    @Override
    public List<SensingElement> selectIntern(Boolean a) throws DAOException {
        ArrayList<SensingElement> lista = new ArrayList<>();
        try{
            String sql;
            Statement st=DAOMySQLSettings.getStatement();
            if(a ==true) {
                sql = " SELECT s.idSPSensingElement FROM spsensingelement s" +
                        " WHERE s.measureTechnique='DIRECT'";
            }
            else{
                sql = " SELECT s.idSPSensingElement FROM spsensingelement s" +
                        " WHERE s.measureTechnique!='DIRECT'";
            }
            ResultSet rs=st.executeQuery(sql);
            while (rs.next()){
                lista.add(new SensingElement(rs.getString("idSPSensingElement")));
            }

        }catch (SQLException e) {
            throw new DAOException("In select(): " + e.getMessage());
        }
        return lista;
    }

    @Override
    public void AddSEOnPort(int port,int fam,String se) throws DAOException {
        String sql="INSERT into spsensingelementonfamily VALUE (null,'"+se+"',(SELECT FT.idSPFamilyTemplate FROM SPFamilyTemplate as FT"+
                " WHERE FT.SPPort_idSPPort="+port+" AND FT.SPFamily_idSPFamily="+fam+"),'')";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In AddSEOnPort(): " + e.getMessage());
        }
    }

    @Override
    public boolean measureControl(String seSelected, int f) throws DAOException {
        int idMeas=0;
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT DISTINCT mt.idSPMeasureTechnique, mt.type from SPSensingElement s,SPFamily f, SPFamily_has_SPMeasuretechnique fm, SPMeasuretechnique mt " +
                    "where f.idSPFamily="+f+" and fm.SPFamily_idSPFamily=f.idSPFamily and mt.idSPMeasureTechnique=fm.SPMeasureTechnique_idSPMeasureTechnique " +
                    "and mt.type=(select s.measureTechnique from spsensingelement s where s.idSPSensingElement='"+seSelected+"')";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idMeas=rs.getInt("idSPMeasureTechnique");
                rs.getString("type");
            }
            if(idMeas!=0){
                return true;
            }

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int measureSearch(String seSelected) throws DAOException {
        int m=0;
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT DISTINCT mt.idSPMeasureTechnique from spmeasuretechnique mt " +
                    "where mt.type=(select s.measureTechnique from spsensingelement s where s.idSPSensingElement='"+seSelected+"')";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                m=rs.getInt("idSPMeasureTechnique");
            }

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return m;
    }

    @Override
    public void insertMeasure(int meas,int f) throws DAOException {

        String sql="INSERT INTO spfamily_has_spmeasuretechnique VALUE ("+f+","+meas+")";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertAddTechniqueOnFamily(): " + e.getMessage());
        }
    }


    @Override
    public void deleteSEonPort(String se) throws DAOException {
        Statement st = null;
        int t = 0;
        try {
            String sql0 = "SELECT sf.SPFamilyTemplate_idSPFamilyTemplate FROM spsensingelementonfamily sf " +
                    "WHERE sf.SPSensingElement_idSPSensingElement='"+se+"'";
            st = DAOMySQLSettings.getStatement();
            ResultSet rs0 = st.executeQuery(sql0);
            while (rs0.next()) {
                t = rs0.getInt("SPFamilyTemplate_idSPFamilyTemplate");
            }

            String mt = null;
            int i = 0;
            String sql1 = "SELECT DISTINCT s.measureTechnique FROM spsensingelement s, spsensingelementonfamily sf " +
                    "WHERE sf.SPSensingElement_idSPSensingElement=s.idSPSensingElement " +
                    "AND sf.SPFamilyTemplate_idSPFamilyTemplate='" + t + "'";
            st = DAOMySQLSettings.getStatement();
            ResultSet rs1 = st.executeQuery(sql1);
            while (rs1.next()) {
                mt = rs1.getString("measureTechnique");
            }
            st = null;
            String sql2 = "SELECT sf.SPSensingElement_idSPSensingElement FROM spsensingelement s, spsensingelementonfamily sf, " +
                    "spfamilytemplate ft, spfamily f, spfamily_has_spmeasuretechnique fm, spmeasuretechnique m " +
                    "WHERE m.type='" + mt + "' AND m.idSPMeasureTechnique=fm.SPMeasureTechnique_idSPMeasureTechnique " +
                    "AND fm.SPFamily_idSPFamily=f.idSPFamily AND ft.SPFamily_idSPFamily=f.idSPFamily " +
                    "AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate AND s.measureTechnique=m.type " +
                    "AND s.idSPSensingElement=sf.SPSensingElement_idSPSensingElement";

            st = DAOMySQLSettings.getStatement();
            ResultSet rs2 = st.executeQuery(sql2);
            while (rs2.next()) {
                rs2.getString("SPSensingElement_idSPSensingElement");
                i++;
            }

            if (i == 1) {
                String sql3 = "DELETE fm.* FROM spfamily_has_spmeasuretechnique fm, spfamily f, spsensingelementonfamily sf, spfamilytemplate ft, spmeasuretechnique m " +
                        "WHERE ft.idSPFamilyTemplate=" + t + " AND f.idSPFamily=ft.SPFamily_idSPFamily AND f.idSPFamily=fm.SPFamily_idSPFamily " +
                        "AND fm.SPMeasureTechnique_idSPMeasureTechnique=m.idSPMeasureTechnique AND m.type='" + mt + "'";
                st = DAOMySQLSettings.getStatement();
                st.executeUpdate(sql3);

                String sql = "DELETE FROM spsensingelementonfamily WHERE SPSensingElement_idSPSensingElement='" + se + "'";
                st = DAOMySQLSettings.getStatement();
                int n = st.executeUpdate(sql);
            }

            else{
                String sql = "DELETE FROM spsensingelementonfamily WHERE SPSensingElement_idSPSensingElement='" + se + "'";
                st = DAOMySQLSettings.getStatement();
                int n = st.executeUpdate(sql);
            }
                DAOMySQLSettings.closeStatement(st);

            } catch(SQLException e){
                throw new DAOException("In deletePortOnFamily(): " + e.getMessage());
            }
        }
    }
