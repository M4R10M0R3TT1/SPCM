package it.unicas.sensiplusConfigurationManager.model.dao;

import it.unicas.sensiplusConfigurationManager.model.Family;

import java.util.List;

/**
 * Created by Antonio on 26/11/2017.
 */
public interface DAOFamily <T>{
    List<T> select(T a) throws DAOException;  //return family Details in FamilyOverview
    List<T> selectPort(T a) throws DAOException;   //return list Port in FamilyOverview
    List<T> selectFamilyAndPort(String a) throws DAOException;
    List<T> selectAddSEOnFamily(String a) throws DAOException;
    List<T> availablePort(T a) throws DAOException;
    List<T> selectMeasureTechnique(T a) throws  DAOException;
    List<T> selectAddPortOnFamily(T a) throws  DAOException;
    List<T> selectAddTechniqueOnFamily(T a) throws DAOException;
    List<T> selectPortOnChip(String a) throws DAOException;
    void insertAddTechniqueOnFamily(Integer t, Integer f) throws DAOException;
    void insertAddPortOnFamily(Integer a,Integer f) throws DAOException;
    void insertFamilyonSE(int f, int p, String se) throws DAOException;
    void deleteFamilyonSE(int t) throws DAOException;
    void deletePortOnFamily(int p, int f) throws  DAOException;
    void deleteTechniqueOnFamily(String mt,int f) throws DAOException;
    void update(T a) throws DAOException;
    void insert(T a) throws  DAOException;
    void delete(T a) throws DAOException;
    boolean measureControl(String se, int f) throws DAOException;
    int measureSearch(String se,int f) throws DAOException;
    void insertMeasure(int m, int f) throws DAOException;
    Family selectSEOnPort(T a, String id) throws DAOException;
}