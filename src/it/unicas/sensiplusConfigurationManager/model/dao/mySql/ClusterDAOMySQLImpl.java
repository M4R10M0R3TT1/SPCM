package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import it.unicas.sensiplusConfigurationManager.model.Cluster;

import it.unicas.sensiplusConfigurationManager.model.dao.DAOCluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Fernando e Damiano entrambi Di Tano on 13/12/2017.
 */

public class ClusterDAOMySQLImpl implements DAOCluster<Cluster> {
    private ClusterDAOMySQLImpl(){
    }

    private static DAOCluster dao = null;
    private static Logger logger = null;
    public static DAOCluster getInstance(){
        if (dao == null){
            dao = new ClusterDAOMySQLImpl();
            logger = Logger.getLogger(FamilyDAOMySQLImpl.class.getName());
        }
        return dao;
    }

    @Override
    public List<Cluster> select(Cluster a) throws DAOException {
        ArrayList<Cluster> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql ="SELECT DISTINCT idCluster FROM spcluster ORDER BY idCluster ";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(rs.getString("idCluster"),
                        0,
                        null,
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In select(Cluster a): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Cluster> selectConfiguration(Cluster a) throws DAOException {
        ArrayList<Cluster> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql ="SELECT DISTINCT conf.* FROM  spconfiguration conf WHERE conf.idCluster='"+a.getIdCluster()+"'";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(rs.getString("IdCluster"),
                        0,
                        null,
                        rs.getInt("idSPConfiguration"),
                        rs.getString("driver"),
                        rs.getString("hostController"),
                        rs.getString("apiOwner"),
                        rs.getString("mcu"),
                        rs.getString("protocol"),
                        rs.getString("addressingType")));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectConfiguration(Cluster a): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Cluster> selectChip(Cluster a) throws DAOException {
        ArrayList<Cluster> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql ="SELECT DISTINCT c.idSPChip, f.id FROM spchip c, spfamily f, spsensingelementonchip sc, spcalibration cal" +
                    " WHERE cal.idSPCalibration=sc.SPCalibration_idSPCalibration AND cal.idSPCalibration ="+a.getIdCalibration()+" " +
                    " AND sc.SPChip_idSPChip = c.idSPChip" +
                    " AND c.SPFamily_idSPFamily=f.idSPFamily;";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(rs.getString("idSPChip"),
                        rs.getString("id"))
                        );
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectChip(Cluster a): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Cluster> selectCalibration(Cluster a) throws DAOException {
        ArrayList<Cluster> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql ="SELECT cal.* FROM  spcalibration cal, spcluster c" +
                    " WHERE c.SPCalibration_idSPCalibration=cal.idSPCalibration AND c.idCluster='"+a.getIdCluster()+"'";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(null,
                        rs.getInt("idSPCalibration"),
                        rs.getString("name"),
                        0,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null)
                );
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In selectChip(Cluster a): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public void delete(Cluster c) throws DAOException{
        String sql = "DELETE FROM spcluster WHERE idCluster='" + c.getIdCluster() + "'";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In delete() : " + e.getMessage());
        }
    }

    @Override
    public void insertConfiguration(Cluster a) throws DAOException {
        String sql="INSERT INTO spconfiguration  VALUE (0,'"+a.getDriver()+"','"+a.getHostController()+"','"+a.getApiOwner()+
                "','"+a.getMcu()+"','"+a.getProtocol()+"','"+a.getAddressingType()+"','"+a.getIdCluster()+"')";
        try {
            Statement st = DAOMySQLSettings.getStatement();
            int n = st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In insertConfiguration(): " + e.getMessage());
        }
    }

    @Override
    public void updateConfiguration(Cluster a) throws DAOException {
        try {
            Statement st = DAOMySQLSettings.getStatement();
            String sql="Update  spconfiguration SET driver='"+a.getDriver()+"', hostController='"+a.getHostController()+
                    "', apiOwner='"+a.getApiOwner()+"', mcu='"+a.getMcu()+"', protocol='"+a.getProtocol()+
                    "', addressingType='"+a.getAddressingType()+"' WHERE idSPConfiguration="+a.getIdConfiguration()+"";
            int n = st.executeUpdate(sql);

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            throw new DAOException("In updateConfiguration():" + e.getMessage());
        }
    }

    @Override
    public void deleteConfiguration(Cluster a) throws DAOException {
        String sql = "DELETE FROM spconfiguration WHERE idSPConfiguration='" + a.getIdConfiguration() + "'";
        logger.info("SQL: " + sql);

        Statement st = null;
        try {
            st = DAOMySQLSettings.getStatement();
            st.executeUpdate(sql);
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException e) {
            throw new DAOException("In deleteConfiguration() : " + e.getMessage());
        }
    }

    @Override
    public List<String> selectCalInDialog() throws DAOException {
       ArrayList<String> list = new ArrayList<>();
       try{
           Statement st = DAOMySQLSettings.getStatement();
           String sql = "SELECT name FROM spcalibration";
           ResultSet rs = st.executeQuery(sql);
           while(rs.next()){
               list.add(new String(rs.getString("name")));
           }
           DAOMySQLSettings.closeStatement(st);
       }catch (SQLException e){
           e.getStackTrace();
       }
        return list;
    }

    @Override
    public void insertCluster(Cluster a) throws DAOException {


       // String sql0 = "SELECT idCalibration FROM spcalibration WHERE name='"+a.getNameCalibration()+"'";
        //if(a.getNameCalibration()!="No Calibration")
            String sql = "INSERT INTO spcluster VALUES ('" + a.getIdCluster() + "',(SELECT c.idSPCalibration FROM spcalibration c WHERE c.name='" + a.getNameCalibration() + "'))";
            try {
                Statement st = DAOMySQLSettings.getStatement();
                int n = st.executeUpdate(sql);
                DAOMySQLSettings.closeStatement(st);

            } catch (SQLException e) {
                throw new DAOException("In insertCluster(): " + e.getMessage());
            }


        }

}
