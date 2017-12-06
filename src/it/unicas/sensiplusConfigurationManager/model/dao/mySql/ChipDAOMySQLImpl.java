package it.unicas.sensiplusConfigurationManager.model.dao.mySql;

import it.unicas.sensiplusConfigurationManager.model.Chip;
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
}
