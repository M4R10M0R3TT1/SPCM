package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOFamily;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by Antonio on 26/11/2017.
 */
public class FamilyDAOMySQLImpl implements DAOFamily<Family> {

    private FamilyDAOMySQLImpl(){}

    private static DAOFamily dao = null;
    private static Logger logger = null;
    public static DAOFamily getInstance(){
        if (dao == null){
            dao = new FamilyDAOMySQLImpl();
            logger = Logger.getLogger(FamilyDAOMySQLImpl.class.getName());
        }
        return dao;
    }

    @Override
    public List<Family> select(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        try{
            Statement st = DAOMySQLSettings.getStatement();

            String sql = "SELECT * FROM spfamily";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                lista.add(new Family(rs.getInt("idSPFamily"),
                        rs.getString("name"),
                        rs.getString("id"),
                        rs.getString("hwVersion"),
                        rs.getString("sysclock"),
                        rs.getString("osctrim")));
            }
            DAOMySQLSettings.closeStatement(st);
        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }
        return lista;
    }

    @Override
    public List<Family> selectPort(Family a) throws DAOException {
        ArrayList<Family> lista = new ArrayList<>();
        Integer id = a.getIdSPFamily();
        System.out.println("Fam_selected: "+ id.toString());
        try{
            Statement st = DAOMySQLSettings.getStatement();
            String sql = "SELECT p.* FROM spport p, spfamilytemplate ft, spfamily f" +
                    "WHERE f.idSPFamily=ft.SPFamily_idSPFamily" +
                    "AND ft.SPPort_idSPPort=p.idSPPort" +
                    "AND f.idSPFamily='"+id+"'";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                lista.add(new Family(
                        rs.getInt("idSPPort"),
                        rs.getBoolean("internal"),
                        rs.getString("name")));
            }
        } catch (SQLException sq) {
            throw new DAOException("In select(): " + sq.getMessage());
        }
        return lista;
    }
}

