package it.unicas.sensiplusConfigurationManager.model.dao;

import com.sun.corba.se.impl.presentation.rmi.DynamicAccessPermission;
import it.unicas.sensiplusConfigurationManager.model.Family;

import java.util.ArrayList;
import java.util.List;

public interface DAOChip<T>{
    List<T> select (T a) throws  DAOException;
    String selectFamilyofChip (T a) throws DAOException;
    List<T> selectClusterChip (T a) throws DAOException;
    List<T> selectCalibrationChip (T a, Family b) throws DAOException;
    void insert(T a) throws  DAOException;
    void delete(T a) throws DAOException;
    List<String> selectFam() throws  DAOException;
    List<String> selectAddCalibrationOnChip (T a, Family b) throws DAOException;
    void insertSEOnChip(T a,Integer idSF)throws DAOException;
    void removeSEOnChip(T a, String se) throws DAOException;
    void deleteCalibrationOnChip(T a, String idChip,int idPort) throws DAOException;
    void deassociate(T a) throws DAOException;
    void editCalibrationOnChip(T a, String idChip, int s) throws DAOException;
    List<T> selectCalibration()throws DAOException;
    void insertCalibration(String a)throws DAOException;
    void updateCalibration(String a, Integer b)throws DAOException;
    void deleteCalibration(Integer id) throws DAOException;
}
