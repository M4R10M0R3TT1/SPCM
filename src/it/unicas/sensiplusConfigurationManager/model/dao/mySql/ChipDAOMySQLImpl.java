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

/**
 * Created by Antonio on 06/12/2017.
 */
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
                lista.add(new Chip(rs.getString("idSPChip")));
            }
            DAOMySQLSettings.closeStatement(st);

        }catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }

        return lista;
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
                lista.add(new Chip(rs.getString("idCluster")));
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
            String sql = "SELECT DISTINCT * FROM spsensingelementonchip sc, spcalibration cal, spsensingelementonfamily sf\n" +
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
}
