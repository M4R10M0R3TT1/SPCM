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
 * Created by Antonio on 13/12/2017.
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
            String sql ="SELECT * FROM spcluster";

            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                lista.add(new Cluster(rs.getString("idCluster"),
                        rs.getInt("SPCalibration_idSPCalibration"),
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
}
