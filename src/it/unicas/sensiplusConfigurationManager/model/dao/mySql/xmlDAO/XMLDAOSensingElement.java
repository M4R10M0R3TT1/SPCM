package it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO;

import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.model.dao.xmlInterface.DAOSensingElement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Antonio on 17/12/2017.
 */
public class XMLDAOSensingElement implements DAOSensingElement <SensingElement>{

    private XMLDAOSensingElement(){

    }

    private static DAOSensingElement dao = null;
    private static Logger logger = null;

    public static DAOSensingElement getInstance() {
        if (dao == null) {
            dao = new XMLDAOSensingElement();
            logger = Logger.getLogger(XMLDAOConfiguration.class.getName());
        }
        return dao;
    }

    @Override
    public List<SensingElement> selectSensingElement(int a) throws DAOException {
        ArrayList<SensingElement> lista = new ArrayList<>();
        try{
            Statement st= DAOMySQLSettings.getStatement();
            String sql = "SELECT DISTINCT se.idSPSensingElement,se.rSense,se.inGain,se.outGain,se.contacts,se.frequency," +
                    "se.harmonic,se.DCBias,se.modeVI,se.measureTechnique,se.measureType,se.filter,se.phaseShiftMode," +
                    "  se.phaseShift,se.IQ,se.conversionRate,se.inPortADC,se.nData,se.name,se.rangeMin,se.rangeMax," +
                    " se.defaultAlarmThreshold,se.multiplier,se.measureUnit" +
                    " FROM spsensingelement se,spsensingelementonfamily sf,spsensingelementonchip sc,spcalibration cal,spcluster c,spconfiguration conf" +
                    " WHERE se.idSPSensingElement=sf.SPSensingElement_idSPSensingElement" +
                    " AND sf.idSPSensingElementOnFamily=sc.SPSensingElementOnFamily_idSPSensingElementOnFamily" +
                    " AND sc.SPCalibration_idSPCalibration = cal.idSPCalibration" +
                    " AND cal.idSPCalibration=c.SPCalibration_idSPCalibration" +
                    " AND c.idCluster=conf.idCluster AND conf.idSPConfiguration="+a;

            ResultSet rs=st.executeQuery(sql);

            while(rs.next()){
                lista.add(new SensingElement(rs.getString("idSPSensingElement"),
                        rs.getInt("rSense"),
                        rs.getInt("inGain"),
                        rs.getInt("outGain"),
                        rs.getString("contacts"),
                        rs.getInt("frequency"),
                        rs.getString("harmonic"),
                        rs.getInt("dcBias"),
                        rs.getString("modeVI"),
                        rs.getString("measureTechnique"),
                        rs.getString("measureType"),
                        rs.getInt("filter"),
                        rs.getString("phaseShiftMode"),
                        rs.getInt("phaseShift"),
                        rs.getString("iq"),
                        rs.getInt("conversionRate"),
                        rs.getString("inPortADC"),
                        rs.getInt("nData"),
                        rs.getString("name"),
                        rs.getDouble("rangeMin"),
                        rs.getDouble("rangeMax"),
                        rs.getDouble("defaultAlarmThreshold"),
                        rs.getInt("multiplier"),
                        rs.getString("measureUnit")
                ));
            }

            DAOMySQLSettings.closeStatement(st);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
