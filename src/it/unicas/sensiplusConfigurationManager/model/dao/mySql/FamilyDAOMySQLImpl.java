package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOFamily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Antonio on 26/11/2017.
 */
public class FamilyDAOMySQLImpl implements DAOFamily<Family> {

    private FamilyDAOMySQLImpl(){}

    private static DAOFamily dao = null;
    private static Logger logger = null;
    public static DAOFamily getInstance(){
        if (dao == null){
            dao = new FamilyDAOMySQLImpl();
            logger = Logger.getLogger(FamilyDAOMySQLImpl.class.getName());
        }
        return dao;
    }

    @Override
    public List<Family> select(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT * FROM spfamily";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Family(rs.getInt("idSPFamily"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("hwVersion"),
                        rs.getString("sysclock"),
                        rs.getString("osctrim")));
            }
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Family> selectPort(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer id = a.getIdSPFamily();

        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT p.*, sf.SPSensingElement_idSPSensingElement "+
                    "FROM spfamily f, spport p, spfamilytemplate ft LEFT JOIN spsensingelementonfamily sf " +
                    "ON ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate " +
                    "WHERE f.idSPFamily=ft.SPFamily_idSPFamily " +
                    "AND ft.SPPort_idSPPort=p.idSPPort AND f.idSPFamily="+id;

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name"),
                        rs.getString("SPSensingElement_idSPSensingElement")));
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectPort(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Family> selectMeasureTechnique(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer id = a.getIdSPFamily();

        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT m.* FROM spmeasuretechnique m, spfamily_has_spmeasuretechnique fm, spfamily f" +
                    " WHERE f.idSPFamily=fm.SPFamily_idSPFamily" +
                    " AND fm.SPMeasureTechnique_idSPMeasureTechnique=m.idSPMeasureTechnique" +
                    " AND f.idSPFamily="+id;

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lista.add(new Family(
                        rs.getString("type")));
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectMeasureTechnique(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public void update(Family a) throws DAOException {
        String query ="UPDATE spFamily SET " +
                " name = '"+a.getName()+
                "', hwVersion = '"+a.getHwVersion()+
                "', sysclock ='"+a.getSysclock()+
                "', osctrim = '"+a.getOsctrim()+"'" +
                " WHERE id = '"+a.getId()+
                "' AND idSPFamily = "+a.getIdSPFamily();
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(query);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            throw new DAOException("In update():" + sq.getMessage());
        }
    }

    @Override
    public void insert(Family a) throws DAOException {

        String sql = "INSERT INTO spFamily (name,id,hwVersion,sysclock,osctrim) VALUES" +
                "('"+a.getName()+"','"+a.getId()+"','"+a.getHwVersion()+
                "','"+a.getSysclock()+"','"+a.getOsctrim()+"')";

        logger.info("SQL: " + sql);

        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insert():" + e.getMessage());
        }
    }

    @Override
    public void delete(Family a) throws DAOException{
        String sql = "DELETE FROM SPFamily WHERE idSPFamily='" + a.getIdSPFamily() + "'";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In delete():" + e.getMessage());
        }
    }


    @Override
    public List<Family> selectFamilyAndPort(String a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT f.id,f.name,p.name,p.internal, ft.idSPFamilyTemplate FROM SPFamilyTemplate ft,SPFamily f,SPPort p,SPSensingElementOnFamily sf\n" +
                    "WHERE sf.SPSensingElement_idSPSensingElement='"+a+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate AND ft.SPFamily_idSPFamily=f.idSPFamily" +
                    " AND ft.SPPort_idSPPort=p.idSPPort";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                lista.add(new Family(
                       rs.getString("f.id"),
                        rs.getString("f.name"),
                        rs.getString("p.name"),
                        rs.getBoolean("internal"),
                        rs.getInt("idSPFamilyTemplate")));
            }
        }catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Family> selectAddSEOnFamily(String a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
       // String seSelected = a.toString();
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT DISTINCT f.idSPFamily,f.id,f.name FROM SPFamily f INNER JOIN SPFamilyTemplate ft, SPSensingElementOnFamily sf\n" +
                    "WHERE ft.SPFamily_idSPFamily=ALL(select distinct ft.SPFamily_idSPFamily from spfamily f, spfamilytemplate ft, spsensingelementonfamily sf\n" +
                    "where sf.SPSensingElement_idSPSensingElement='"+a+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate)\n" +
                    "\n" +
                    "AND ft.idSPFamilyTemplate=ALL(select distinct ft.idSPFamilyTemplate from spfamily f, spfamilytemplate ft, spsensingelementonfamily sf\n" +
                    "where sf.SPSensingElement_idSPSensingElement='"+a+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate)\n" +
                    "\n" +
                    "AND f.idSPFamily!=ft.SPFamily_idSPFamily;";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPFamily"),
                        rs.getString("id"),
                        rs.getString("name")));
            }
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            throw new DAOException("In selectADDSEOnFamily(): " + e.getMessage());
        }
        return  lista;
    }

    @Override
    public void insertFamilyonSE(int f, int p, String se) throws DAOException {
        String sql="INSERT into spsensingelementonfamily VALUE (null,'"+se+"',(SELECT FT.idSPFamilyTemplate FROM SPFamilyTemplate as FT"+
        " WHERE FT.SPPort_idSPPort="+p+" AND FT.SPFamily_idSPFamily="+f+"),'')";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertFamilyonSE(): " + e.getMessage());
        }

    }

    @Override
    public List<Family> availablePort(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer famSelected = a.getIdSPFamily();
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT p.idSPPort,p.name,p.internal FROM SPPort p,SPFamilyTemplate ft " +
                    "WHERE ft.SPFamily_idSPFamily="+famSelected+" AND ft.idSPFamilyTemplate!=ALL(SELECT ft.idSPFamilyTemplate " +
                    "FROM SPFamilyTemplate ft,SPSensingElementOnFamily sf " +
                    "WHERE ft.SPFamily_idSPFamily="+famSelected+" " +
                    "AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate) AND p.idSPPort=ft.SPPort_idSPPort;";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getString("name"),
                        rs.getBoolean("internal")));
            }

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  lista;


    }

    @Override
    public void deleteFamilyonSE(int t) throws DAOException {
        String sql="DELETE FROM SPSensingElementOnFamily WHERE SPFamilyTemplate_idSPFamilyTemplate="+t;
        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In deleteFamilyonSE(): " + e.getMessage());
        }
    }

    @Override
    public void deletePortOnFamily(int p, int f) throws DAOException {
        String sql="DELETE FROM SPFamilyTemplate WHERE SPFamily_idSPFamily="+f+" AND SPPort_idSPPort="+p;
        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In deletePortOnFamily(): " + e.getMessage());
        }
    }

    @Override
    public void deleteTechniqueOnFamily(String mt, int f) throws DAOException {
        System.out.println(mt + f);

        String sql="DELETE fmt.* "+
                   "FROM spfamily_has_spmeasuretechnique as fmt,SPMeasureTechnique as mt WHERE fmt.SPFamily_idSPFamily="+f+
                   " AND fmt.SPMeasureTechnique_idSPMeasureTechnique=mt.idSPMeasureTechnique AND mt.type='"+mt+"'";
        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In deleteTechniqueOnFamily(): " + e.getMessage());
        }
    }

    @Override
    public List<Family> selectAddPortOnFamily(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer famSelected = a.getIdSPFamily();
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT p.idSPPort,p.name,p.internal FROM SPPort p "+
                       "WHERE p.idSPPort!=ALL(SELECT ft.SPPort_idSPPort FROM SPFamilyTemplate ft "+
                       "WHERE ft.SPFamily_idSPFamily='"+famSelected+"')GROUP BY p.idSPPort,p.name,p.internal";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getString("name"),
                        rs.getBoolean("internal")));
            }

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void insertAddPortOnFamily(Integer a, Integer f) throws DAOException {
        String sql="INSERT INTO spfamilytemplate VALUE (null,"+f+","+a+")";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertAddPOrtOnFamily(): " + e.getMessage());
        }
    }

    @Override
    public List<Family> selectAddTechniqueOnFamily(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer famSelected = a.getIdSPFamily();
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT * FROM spmeasuretechnique mt "+
                    "WHERE mt.idSPMeasureTechnique!=ALL(SELECT fmt.SPMeasureTechnique_idSPMeasureTechnique "+
                    "FROM spfamily_has_spmeasuretechnique fmt WHERE fmt.SPFamily_idSPFamily="+famSelected+")GROUP BY mt.idSPMeasureTechnique";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Family(
                        rs.getString("type"),
                        rs.getInt("idSPMeasureTechnique")));
            }

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void insertAddTechniqueOnFamily(Integer t, Integer f) throws DAOException {
        String sql="INSERT INTO spfamily_has_spmeasuretechnique VALUE ("+f+","+t+")";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertAddTechniqueOnFamily(): " + e.getMessage());
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
    public int measureSearch(String seSelected,int f) throws DAOException {
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
            e.printStackTrace();
        }
    }
}

