package it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO;

import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface.DAOFamily;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLFamily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Antonio on 16/12/2017.
 */
public class XMLDAOFamily implements DAOFamily<XMLFamily> {

    private XMLDAOFamily(){

     }

    private static DAOFamily dao = null;
    private static Logger logger = null;

    public static DAOFamily getInstance() {
        if (dao == null) {
            dao = new XMLDAOFamily();
            logger = Logger.getLogger(XMLDAOConfiguration.class.getName());
        }
        return dao;
    }

    @Override
    public List<XMLFamily> selectFamily(int a) {
        ArrayList<XMLFamily> list = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT DISTINCT f.idSPFamily,f.name,f.id,f.hwVersion,f.sysclock,f.osctrim" +
                    " FROM spfamily f, spchip ch,spsensingelementonchip sc,spcalibration cal,spcluster c,spconfiguration conf" +
                    " WHERE f.idSPFamily=ch.SPFamily_idSPFamily AND ch.idSPChip=sc.SPChip_idSPChip" +
                    " AND sc.SPCalibration_idSPCalibration = cal.idSPCalibration AND cal.idSPCalibration=c.SPCalibration_idSPCalibration" +
                    " AND c.idCluster=conf.idCluster AND conf.idSPConfiguration="+a;

            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                list.add(new XMLFamily(rs.getInt("idSPFamily"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("hwVersion"),
                        rs.getString("sysclock"),
                        rs.getString("osctrim")));
            }

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
