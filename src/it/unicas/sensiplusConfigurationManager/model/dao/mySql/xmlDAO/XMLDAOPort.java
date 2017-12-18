package it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO;

import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface.DAOPort;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLPort;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class XMLDAOPort implements DAOPort<XMLPort> {

    private XMLDAOPort(){

    }

    private static DAOPort dao = null;
    private static Logger logger = null;

    public static DAOPort getInstance() {
        if (dao == null) {
            dao = new XMLDAOPort();
            logger = Logger.getLogger(XMLDAOConfiguration.class.getName());
        }
        return dao;
    }

    @Override
    public List<XMLPort> selectPort(int a) {
        ArrayList<XMLPort> list=new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT DISTINCT p.* FROM spport p,spcluster c,spcalibration cal,spsensingelementonchip sc,spchip ch,spfamily f,spfamilytemplate ft,spconfiguration conf,spsensingelementonfamily sf" +
                    " WHERE conf.idSPConfiguration="+a+" AND conf.idCluster=c.idCluster AND c.SPCalibration_idSPCalibration=cal.idSPCalibration" +
                    " AND cal.idSPCalibration=sc.SPCalibration_idSPCalibration AND sc.SPSensingElementOnFamily_idSPSensingElementOnFamily=sf.idSPSensingElementOnFamily" +
                    " AND sf.SPFamilyTemplate_idSPFamilyTemplate=ft.idSPFamilyTemplate AND sc.SPChip_idSPChip=ch.idSPChip" +
                    " AND ch.SPFamily_idSPFamily=f.idSPFamily AND f.idSPFamily=ft.SPFamily_idSPFamily AND ft.SPPort_idSPPort=p.idSPPort";

            ResultSet rs=st.executeQuery(sql);
            while(rs.next()){
                list.add(new XMLPort(rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name")));
            }

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
