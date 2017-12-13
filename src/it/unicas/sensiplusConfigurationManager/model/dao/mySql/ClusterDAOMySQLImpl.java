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
            String sql ="SELECT c.*,cal.* FROM spcluster c, spcalibration cal WHERE c.SPCalibration_idSPCalibration=cal.idSPCalibration";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(rs.getString("idCluster"),
                        rs.getInt("SPCalibration_idSPCalibration"),
                        rs.getString("name"),
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
            String sql ="SELECT conf.* FROM spcluster c, spconfiguration conf WHERE c.idCluster=conf.idCluster";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(null,
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
}
