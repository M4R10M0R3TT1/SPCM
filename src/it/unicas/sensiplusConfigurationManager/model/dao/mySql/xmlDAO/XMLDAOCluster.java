package it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO;

import it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface.DAOCluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLCluster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class XMLDAOCluster implements DAOCluster<XMLCluster> {
    private XMLDAOCluster() {
    }

    private static DAOCluster dao = null;
    private static Logger logger = null;

    public static DAOCluster getInstance() {
        if (dao == null) {
            dao = new XMLDAOCluster();
            logger = Logger.getLogger(XMLDAOCluster.class.getName());
        }
        return dao;
    }

    @Override
    public List<XMLCluster> selectCluster(int selectedConf) throws DAOException {
        ArrayList<XMLCluster> lista=new ArrayList<>();
        try {
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT DISTINCT c.idCluster,f.id,ch.idSPChip,sf.SPSensingElement_idSPSensingElement,sc.m,sc.n " +
                    "FROM spcluster c,spfamily f,spchip ch,spsensingelementonfamily sf,spconfiguration conf,spcalibration cal,spsensingelementonchip sc " +
                    "WHERE c.SPCalibration_idSPCalibration="+selectedConf+" AND c.SPCalibration_idSPCalibration=cal.idSPCalibration " +
                    "AND cal.idSPCalibration=sc.SPCalibration_idSPCalibration AND sc.SPChip_idSPChip=ch.idSPChip " +
                    "AND ch.SPFamily_idSPFamily=f.idSPFamily AND sc.SPSensingElementOnFamily_idSPSensingElementOnFamily=sf.idSPSensingElementOnFamily";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new XMLCluster(rs.getString("idCluster"),
                        rs.getString("id"),
                        rs.getString("idSPChip"),
                        rs.getString("SPSensingElement_idSPSensingElement"),
                        rs.getInt("m"),
                        rs.getInt("n")
                ));
            }
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            sq.getStackTrace();
        }

        return lista;
    }
}

