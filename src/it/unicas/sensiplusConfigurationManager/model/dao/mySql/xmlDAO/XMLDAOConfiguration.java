package it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO;

import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface.DAOConfiguration;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class XMLDAOConfiguration implements DAOConfiguration<XMLConfiguration>{
    private XMLDAOConfiguration() {
    }

    private static DAOConfiguration dao = null;
    private static Logger logger = null;

    public static DAOConfiguration getInstance() {
        if (dao == null) {
            dao = new XMLDAOConfiguration();
            logger = Logger.getLogger(XMLDAOConfiguration.class.getName());
        }
        return dao;
    }

    @Override
    public List<XMLConfiguration> selectConf(int selectedConf) throws DAOException {
        ArrayList<XMLConfiguration> lista=new ArrayList<>();
        try {
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT driver,hostController,apiOwner,mcu,protocol,addressingType,idCluster FROM SPConfiguration WHERE idSPConfiguration="+selectedConf;

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new XMLConfiguration(rs.getString("driver"),
                        rs.getString("hostController"),
                        rs.getString("apiOwner"),
                        rs.getString("mcu"),
                        rs.getString("protocol"),
                        rs.getString("addressingType"),
                        rs.getString("idCluster")
                ));
            }
            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException sq) {
            sq.getStackTrace();
        }

        return lista;
    }
}
