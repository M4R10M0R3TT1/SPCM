package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOChip;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ChipDAOMySQLImpl implements DAOChip<Chip> {

    private ChipDAOMySQLImpl(){}

    private static DAOChip dao = null;
    private static Logger logger = null;
    public static DAOChip getInstance(){
        if (dao == null){
            dao = new ChipDAOMySQLImpl();
            logger = Logger.getLogger(FamilyDAOMySQLImpl.class.getName());
        }
        return dao;
    }

    @Override
    public List<Chip> select(Chip a) throws DAOException {
        ArrayList<Chip> lista= new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = " SELECT * FROM spChip";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Chip(rs.getString("idSPChip"),
                        null));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public void insert(Chip a) throws  DAOException{
        int idfam=0;
        try {
            Statement st = DAOMySQLSettings.getStatement();
            String sql0="SELECT f.idSPFamily FROM SPFamily f WHERE f.id='"+a.getId()+"'";
            ResultSet rs=st.executeQuery(sql0);
            while(rs.next()){
                idfam=rs.getInt("idSPFamily");
            }

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.getStackTrace();
        }

        String sql=null;

        if(a.getId()=="No Family Associated") {
            sql = "INSERT INTO SPChip (idSPChip,SPFamily_idSPFamily) VALUES" +
                    "('" + a.getIdSPChip() + "',null)";
        }
        else {
            sql = "INSERT INTO SPChip (idSPChip,SPFamily_idSPFamily) VALUES" +
                    "('" + a.getIdSPChip() + "'," + idfam + ")";
        }

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
    public void deassociate(Chip a) throws DAOException {
        int idfam=0;
        try {
            Statement st = DAOMySQLSettings.getStatement();
            String sql0="SELECT f.idSPFamily FROM SPFamily f WHERE f.id='"+a.getId()+"'";
            ResultSet rs=st.executeQuery(sql0);
            while(rs.next()){
                idfam = rs.getInt("idSPFamily");
            }

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.getStackTrace();
        }

        String sql=null;

        if(a.getId()=="No Family Associated") {
            sql=" UPDATE spchip SET " +
                    "SPFamily_idSPFamily=null WHERE idSPChip='"+a.getIdSPChip()+"'";
        }
        else {
            sql = " UPDATE spchip SET " +
                    "SPFamily_idSPFamily=" + idfam + " WHERE idSPChip='" + a.getIdSPChip() + "'";
        }
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            throw new DAOException("In update(): " + sq.getMessage());
        }
    }

    @Override
    public void delete(Chip a) throws DAOException{
        String sql = "DELETE FROM SPChip WHERE idSPChip='" + a.getIdSPChip() + "'";
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
    public String selectFamilyofChip(Chip a) throws DAOException {
        String family=null;
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = " SELECT f.id FROM spChip c,spfamily f WHERE c.SPFamily_idSPFamily=f.idSPFamily AND c.idSPChip='"+a.getIdSPChip()+"'";
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                family=rs.getString("id");
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectFamilyofChip(): " + sq.getMessage());
        }

        return family;
    }

    @Override
    public List<Chip> selectClusterChip(Chip a) throws DAOException {
        ArrayList<Chip> lista= new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = " SELECT DISTINCT cl.idCluster FROM spsensingelementonchip sc, spcalibration cal, spcluster cl "+
                    "WHERE cl.SPCalibration_idSPCalibration=cal.idSPCalibration AND cal.idSPCalibration=sc.SPCalibration_idSPCalibration "+
                    "AND sc.SPChip_idSPChip='"+a.getIdSPChip()+"'";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Chip(rs.getString("idCluster"),
                        null));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectClusterChip(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public List<Chip> selectCalibrationChip(Chip a, Family b) throws DAOException {
        ArrayList<Chip> lista= new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT DISTINCT cal.idSPCalibration,cal.name,sc.m,sc.n FROM spsensingelementonchip sc, spcalibration cal, spsensingelementonfamily sf\n" +
                    "WHERE sc.SPChip_idSPChip='"+a.getIdSPChip()+"' AND sc.SPSensingElementOnFamily_idSPSensingElementOnFamily=(SELECT\n" +
                    "sf.idSPSensingElementOnFamily FROM spfamilytemplate ft, spsensingelementonfamily sf, spchip c, spfamily f\n" +
                    "WHERE  c.idSPChip='"+a.getIdSPChip()+"'AND c.SPFamily_idSPFamily=f.idSPFamily AND f.idSPFamily=ft.SPFamily_idSPFamily\n" +
                    "AND ft.SPPort_idSPPort="+b.getIdSPPort()+" AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate)\n" +
                    "AND sf.SPSensingElement_idSPSensingElement='"+b.getOccupiedBy()+"' AND cal.idSPCalibration=sc.SPCalibration_idSPCalibration";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Chip(rs.getInt("idSPCalibration"),
                        rs.getString("name"),
                        rs.getInt("m"),
                        rs.getInt("n")));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectCalibrationChip(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public List<String> selectFam() throws DAOException{
        ArrayList<String> list=new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT f.id FROM SPFamily f";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                list.add(new String(rs.getString("id")));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectFamily(): " + sq.getMessage());
        }
        return list;
    }

    @Override
    public List<String> selectAddCalibrationOnChip(Chip a, Family b) throws DAOException {
        ArrayList<String> lista = new ArrayList<>();
        if (a == null) {
            try {
                Statement st = DAOMySQLSettings.getStatement();
                String sql = "SELECT DISTINCT cal.name FROM spcalibration cal";

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(rs.getString("name"));
                }
                DAOMySQLSettings.closeStatement(st);

            } catch (SQLException sq) {
                throw new DAOException("In selectAddCalibrationOnChip [AddSEOnChip](): " + sq.getMessage());
            }
        }else{
            try {
                Statement st = DAOMySQLSettings.getStatement();
                String sql = "SELECT DISTINCT cal.name FROM spcalibration cal "+
                        "WHERE cal.idSPCalibration!=ALL(SELECT DISTINCT cal.idSPCalibration FROM spsensingelementonchip sc, spcalibration cal, spsensingelementonfamily sf " +
                        "WHERE sc.SPChip_idSPChip='"+a.getIdSPChip()+"' AND sc.SPSensingElementOnFamily_idSPSensingElementOnFamily=(SELECT " +
                        "sf.idSPSensingElementOnFamily FROM spfamilytemplate ft, spsensingelementonfamily sf, spchip c, spfamily f " +
                        "WHERE  c.idSPChip='"+a.getIdSPChip()+"'AND c.SPFamily_idSPFamily=f.idSPFamily AND f.idSPFamily=ft.SPFamily_idSPFamily " +
                        "AND ft.SPPort_idSPPort="+b.getIdSPPort()+" AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate) " +
                        "AND sf.SPSensingElement_idSPSensingElement='"+b.getOccupiedBy()+"' AND cal.idSPCalibration=sc.SPCalibration_idSPCalibration)";

                ResultSet rs = st.executeQuery(sql);
                while (rs.next()) {
                    lista.add(rs.getString("name"));
                }
                DAOMySQLSettings.closeStatement(st);

            } catch (SQLException sq) {
                throw new DAOException("In selectAddCalibrationOnChip(): " + sq.getMessage());
            }
        }
        return lista;
    }

    @Override
    public void insertSEOnChip(Chip a, Integer idSF) throws DAOException {
        String sql="INSERT INTO spsensingelementonchip  VALUE ('"+a.getIdSPChip()+"',"+a.getM()+","+a.getN()+
                ","+idSF+",(SELECT c.idSPCalibration FROM spcalibration c WHERE c.name='"+a.getNameCalibration()+"'))";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertSEOnChip(): " + e.getMessage());
        }
    }

    @Override
    public void removeSEOnChip(Chip a, String se) throws DAOException {
        String sql = "DELETE sc.* FROM spsensingelementonchip sc, spsensingelementonfamily sf WHERE sc.SPChip_idSPChip='"+a.getIdSPChip()+
                "' AND sf.idSPSensingElementOnFamily=sc.SPSensingElementOnFamily_idSPSensingElementOnFamily "+
                "AND sf.SPSensingElement_idSPSensingElement='"+se+"'";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In removeSEOnChip():" + e.getMessage());
        }
    }

    @Override
    public void deleteCalibrationOnChip(Chip a, String idChip, int idPort) throws DAOException {
        String sql = "DELETE sc.* FROM spsensingelementonchip sc, spChip c, spfamilytemplate ft,spsensingelementonfamily sf  WHERE sc.SPChip_idSPChip='"+idChip+
                "' AND sc.m="+a.getM()+" AND sc.n="+a.getN()+" AND sc.SPCalibration_idSPCalibration="+a.getIdCalibration()+
                " AND c.idSPChip=sc.SPChip_idSPChip AND ft.spFamily_idSPFamily=c.spFamily_idSPFamily AND ft.SPPort_idSPPort="+idPort+
                " AND ft.idSPFamilyTemplate=sf.SPFamilyTemplate_idSPFamilyTemplate AND sf.idSPSensingElementOnFamily=sc.SPSensingElementOnFamily_idSPSensingElementOnFamily;";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In deleteCalibrationOnChip(): " + e.getMessage());
        }
    }

    @Override
    public void editCalibrationOnChip(Chip a, String idChip, int idSEonFam) throws DAOException {
        Statement st = null;
        try {
        String sql = "UPDATE spsensingelementonchip SET m="+a.getM()+", n="+a.getN()+" " +
                     "WHERE SPChip_idSPChip='"+idChip+"' AND SPCalibration_idSPCalibration=(Select DISTINCT  c.idspcalibration From SPCalibration c WHERE c.name='"+a.getNameCalibration()+"') " +
                     "AND SPSensingElementOnFamily_idSPSensingElementOnFamily="+idSEonFam+"";
            logger.info("SQL: " + sql);

            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In editCalibrationOnChip():" + e.getMessage());
        }
    }

    @Override
    public List<Chip> selectCalibration() throws DAOException {
        ArrayList<Chip> lista= new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT * FROM spcalibration ORDER By idSPCalibration";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Chip(rs.getInt("idSPCalibration"),
                        rs.getString("name"),
                        0,0));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectClusterChip(): " + sq.getMessage());
        }

        return lista;
    }

    @Override
    public void insertCalibration(String a) throws DAOException {

        try {
            Statement st = DAOMySQLSettings.getStatement();
            String sql="INSERT INTO spcalibration VALUES (null,'"+a+"')";
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertCalibration():" + e.getMessage());
        }
    }

    @Override
    public void updateCalibration(String a, Integer b) throws DAOException {
        try {
            Statement st = DAOMySQLSettings.getStatement();
            String sql="Update  spcalibration SET name='"+a+"' WHERE idSPCalibration="+b+"";
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In updateCalibration():" + e.getMessage());
        }
    }

    @Override
    public void deleteCalibration(Integer id) throws DAOException {
        String sql = "DELETE FROM spcalibration WHERE idSPCalibration = "+id+"";

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In deleteCalibration(): " + e.getMessage());
        }


    }
}
