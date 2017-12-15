package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import com.mysql.cj.api.mysqla.result.Resultset;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOFamily;

import javax.xml.transform.Result;
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
                        rs.getString("SPSensingElement_idSPSensingElement"),
                        0,
                        0));
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectPort(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Family> selectAllPort(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();


        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT * FROM spport";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name"),
                        null,
                        0,
                        0));
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectAllPort(): " + sq.getMessage());
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
                        rs.getString("type"),
                        0));
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
            throw new DAOException("In insert():  " + e.getMessage());
        }
    }

    @Override
    public void delete(Family a) throws DAOException{

        try {
            Statement st = null;
            String sql0="UPDATE SPChip SET " +
                    "SPChip.SPFamily_idSPFamily=null " +
                    "WHERE spchip.SPFamily_idSPFamily="+a.getIdSPFamily()+"";
            st = DAOMySQLSettings.getStatement();
            st.executeUpdate(sql0);

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
           e.getStackTrace();
        }
        try{
            Statement st = null;
            String sql = "DELETE spfamily.* FROM SPFamily WHERE idSPFamily='" + a.getIdSPFamily() + "'";
            st = DAOMySQLSettings.getStatement();
            st.executeUpdate(sql);
            logger.info("SQL: " + sql);

            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.getStackTrace();
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

            String sql="SELECT DISTINCT f.idSPFamily,f.name,f.id FROM SPFamily f INNER JOIN SPFamilyTemplate ft, SPSensingElementOnFamily sf\n" +
                    "WHERE ft.SPFamily_idSPFamily=ALL(select distinct ft.SPFamily_idSPFamily from spfamily f, spfamilytemplate ft, spsensingelementonfamily sf\n" +
                    "where sf.SPSensingElement_idSPSensingElement='"+a+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate)\n" +
                    "\n" +
                    "AND ft.idSPFamilyTemplate=ALL(select distinct ft.idSPFamilyTemplate from spfamily f, spfamilytemplate ft, spsensingelementonfamily sf\n" +
                    "where sf.SPSensingElement_idSPSensingElement='"+a+"' AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate)\n" +
                    "\n" +
                    "AND f.idSPFamily!=ft.SPFamily_idSPFamily";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPFamily"),
                        rs.getString("name"),
                        rs.getString("id"),
                        null,
                        null,
                        null
                       ));
            }
            if(lista.size()==0) {
                int vacant=0;
                String sql0="SELECT DISTINCT idSPSensingElementOnFamily FROM SPSensingElementOnFamily";
                Statement st0=DAOMySQLSettings.getStatement();
                ResultSet rs0=st0.executeQuery(sql0);
                while(rs0.next()){
                    vacant=rs0.getInt("idSPSensingElementOnFamily");
                }

                if(vacant==0) {
                    Statement st1 = DAOMySQLSettings.getStatement();
                    String sql1 = "SELECT DISTINCT ft.SPFamily_idSPFamily,f.id,f.name FROM SPFamily f, spfamilytemplate ft " +
                                  "WHERE f.idSPFamily=ft.SPFamily_idSPFamily";

                    ResultSet rs1 = st1.executeQuery(sql1);
                    while (rs1.next()) {
                        lista.add(new Family(
                                rs1.getInt("SPFamily_idSPFamily"),
                                rs1.getString("id"),
                                rs1.getString("name"),
                                null,
                                null,
                                null));
                    }
                }
            }
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.getStackTrace();
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
    public List<Family> availablePort(Family a,String se) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer famSelected = a.getIdSPFamily();
        String mt=null;

        try{
            Statement st=DAOMySQLSettings.getStatement();
            //MEASURE CONTROL
            String sql0="SELECT s.measureTechnique FROM spsensingelement s " +
                    "WHERE s.idSPSensingElement='"+se+"'";
            ResultSet rs0=st.executeQuery(sql0);
            while(rs0.next()) {
                mt = rs0.getString("measureTechnique");
            }
            //DAOMySQLSettings.closeStatement(st0);

            st=DAOMySQLSettings.getStatement();
            String sql=null;
            if(mt.equals("DIRECT")) {
                sql = "SELECT DISTINCT p.idSPPort,p.internal,p.name FROM SPPort p,SPFamilyTemplate ft, spsensingelement s, spsensingelementonfamily sf " +
                        "WHERE ft.SPFamily_idSPFamily=" + famSelected + " AND ft.idSPFamilyTemplate!=ALL(SELECT ft.idSPFamilyTemplate " +
                        "FROM SPFamilyTemplate ft,SPSensingElementOnFamily sf " +
                        "WHERE ft.SPFamily_idSPFamily=" + famSelected + " " +
                        "AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate) AND p.idSPPort=ft.SPPort_idSPPort " +
                        "AND p.internal=true";
            }
            else{
                sql = "SELECT DISTINCT p.idSPPort,p.internal,p.name FROM SPPort p,SPFamilyTemplate ft " +
                        "WHERE ft.SPFamily_idSPFamily=" + famSelected + " AND ft.idSPFamilyTemplate!=ALL(SELECT ft.idSPFamilyTemplate " +
                        "FROM SPFamilyTemplate ft,SPSensingElementOnFamily sf " +
                        "WHERE ft.SPFamily_idSPFamily=" + famSelected + " " +
                        "AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate) AND p.idSPPort=ft.SPPort_idSPPort " +
                        "AND p.internal!=true";
            }
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name"),
                        null,0,0));
            }
            if(lista.size()==0)
                lista.add(null);
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  lista;


    }

    @Override
    public void deleteFamilyonSE(int t) throws DAOException {
        Statement st = null;
        String mt=null;
        int i=0;
        try{
            String sql0="SELECT DISTINCT s.measureTechnique FROM spsensingelement s, spsensingelementonfamily sf " +
                    "WHERE sf.SPSensingElement_idSPSensingElement=s.idSPSensingElement " +
                    "AND sf.SPFamilyTemplate_idSPFamilyTemplate='"+t+"'";
            st = DAOMySQLSettings.getStatement();
            ResultSet rs0=st.executeQuery(sql0);
            while(rs0.next()){
                mt=rs0.getString("measureTechnique");
            }
            st=null;
            String sql1="SELECT sf.SPSensingElement_idSPSensingElement FROM spsensingelement s, spsensingelementonfamily sf, " +
                    "spfamilytemplate ft, spfamily f, spfamily_has_spmeasuretechnique fm, spmeasuretechnique m " +
                    "WHERE m.type='"+mt+"' AND m.idSPMeasureTechnique=fm.SPMeasureTechnique_idSPMeasureTechnique " +
                    "AND fm.SPFamily_idSPFamily=f.idSPFamily AND ft.SPFamily_idSPFamily=f.idSPFamily " +
                    "AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate AND s.measureTechnique=m.type " +
                    "AND s.idSPSensingElement=sf.SPSensingElement_idSPSensingElement";

            st = DAOMySQLSettings.getStatement();
            ResultSet rs1=st.executeQuery(sql1);
            while(rs1.next()){
                rs1.getString("SPSensingElement_idSPSensingElement");
                i++;
            }

            if(i==1) {
                String sql2="DELETE fm.* FROM spfamily_has_spmeasuretechnique fm, spfamily f, spsensingelementonfamily sf, spfamilytemplate ft, spmeasuretechnique m " +
                        "WHERE ft.idSPFamilyTemplate="+t+" AND f.idSPFamily=ft.SPFamily_idSPFamily AND f.idSPFamily=fm.SPFamily_idSPFamily " +
                        "AND fm.SPMeasureTechnique_idSPMeasureTechnique=m.idSPMeasureTechnique AND m.type='"+mt+"'";
                st = DAOMySQLSettings.getStatement();
                st.executeUpdate(sql2);

                String sql = "DELETE FROM SPSensingElementOnFamily WHERE SPFamilyTemplate_idSPFamilyTemplate=" + t;
                st = DAOMySQLSettings.getStatement();
                st.executeUpdate(sql);
            }
            else{
                String sql = "DELETE FROM SPSensingElementOnFamily WHERE SPFamilyTemplate_idSPFamilyTemplate=" + t;
                st = DAOMySQLSettings.getStatement();
                st.executeUpdate(sql);
            }
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.getStackTrace();
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
            throw new DAOException("In deletePortOnFamily():  " + e.getMessage());
        }
    }

    @Override
    public List<Family> selectAddPortOnFamily(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer famSelected = a.getIdSPFamily();
        try{
            Statement st=DAOMySQLSettings.getStatement();

            String sql="SELECT p.idSPPort,p.internal,p.name FROM SPPort p "+
                       "WHERE p.idSPPort!=ALL(SELECT ft.SPPort_idSPPort FROM SPFamilyTemplate ft "+
                       "WHERE ft.SPFamily_idSPFamily='"+famSelected+"')GROUP BY p.idSPPort,p.name,p.internal";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name"),
                        null,0,0
                        ));
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
            throw new DAOException("In insertMeasure(): " + e.getMessage());
        }
    }

    @Override
    public List<Family> selectPortOnChip(String a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT DISTINCT p.*, sel.SPSensingElement_idSPSensingElement FROM spfamily f, spchip c, spport p,"+
                    " spfamilytemplate ft LEFT JOIN (SELECT sf.SPFamilyTemplate_idSPFamilyTemplate, "+
                    "sf.SPSensingElement_idSPSensingElement FROM  spchip c, spsensingelementonchip sc,spsensingelementonfamily sf "+
                    "WHERE c.idSPChip=sc.SPChip_idSPChip AND sc.SPSensingElementOnFamily_idSPSensingElementOnFamily=sf.idSPSensingElementOnFamily "+
                    "AND c.idSPChip='"+a+"') sel ON sel.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate "+
                    "WHERE f.idSPFamily=ft.SPFamily_idSPFamily AND ft.SPPort_idSPPort=p.idSPPort AND f.idSPFamily=c.SPFamily_idSPFamily AND "+
                    "c.idSPChip='"+a+"'";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name"),
                        rs.getString("SPSensingElement_idSPSensingElement"),
                        0,
                        0));
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectPortonChip(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public Family selectSEOnPort(Family a, String id) throws DAOException {
        Family f = null;
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT sf.SPSensingElement_idSPSensingElement, sf.idSPSensingElementOnFamily "+
                    "FROM spfamilytemplate ft, spsensingelementonfamily sf, spfamily f " +
                    "WHERE ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate "+
                    "AND f.idSPFamily=ft.SPFamily_idSPFamily AND ft.SPPort_idSPPort="+a.getIdSPPort()+
                    " AND f.id='"+id+"'";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                f= new Family(rs.getInt("idSPSensingElementOnFamily"),
                        Boolean.FALSE,
                        null,
                        rs.getString("SPSensingElement_idSPSensingElement"),
                        0,
                        0);
            }
        } catch (SQLException sq) {
            throw new DAOException("In selectSEOnPort(): " + sq.getMessage());
        }
        return f;
    }

    @Override
    public List<Family> selectPortOfChipOnCluster(Integer a, String idChip) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
            try {
                Statement st = DAOMySQLSettings.getStatement();
                String sql = "SELECT DISTINCT  p.name, sf.SPSensingElement_idSPSensingElement,sc.m,sc.n FROM spport p, spsensingelementonfamily sf, spsensingelementonchip sc, spchip c, spfamilytemplate ft" +
                        " WHERE sc.SPCalibration_idSPCalibration="+a+" AND sf.idSPSensingElementOnFamily=sc.SPSensingElementOnFamily_idSPSensingElementOnFamily" +
                        " AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate" +
                        " AND ft.SPPort_idSPPort=p.idSPPort AND sc.SPChip_idSPChip='"+idChip+"'";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    lista.add(new Family(0,
                            true,
                            rs.getString("name"),
                            rs.getString("SPSensingElement_idSPSensingElement"),
                            rs.getInt("m"),
                            rs.getInt("n")));
                }
                DAOMySQLSettings.closeStatement(st);

            } catch (SQLException sq) {
                throw new DAOException("In selectChip(Cluster a): " + sq.getMessage());
            }
            return lista;
        }

    @Override
    public void insertPort(Family a) throws DAOException {
        String sql="INSERT INTO spport VALUE (0,"+a.isInternal()+",'"+a.getPortName()+"')";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertPort: " + e.getMessage());
        }
    }
}

