package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.*;

public class Chip {

    private StringProperty idSPChip;
    private StringProperty id; //id of Family table in DB
    private StringProperty cluster;
    private IntegerProperty m;
    private IntegerProperty n;
    private IntegerProperty idCalibration;
    private StringProperty nameCalibration;

    //Default constructor
    public Chip(){this(null,null);}

    public Chip(String idSPChip,String id){
        this.idSPChip=new SimpleStringProperty(idSPChip);
        this.id=new SimpleStringProperty(id);
        this.cluster=new SimpleStringProperty(null);
        this.m=new SimpleIntegerProperty(0);
        this.n=new SimpleIntegerProperty(0);
        this.idCalibration=new SimpleIntegerProperty(0);
        this.nameCalibration=new SimpleStringProperty(null);
    }

    public Chip(Integer idCalibration, String nameCalibration, Integer m, Integer n){
        this.idCalibration= new SimpleIntegerProperty(idCalibration);
        this.nameCalibration= new SimpleStringProperty(nameCalibration);
        this.m= new SimpleIntegerProperty(m);
        this.n= new SimpleIntegerProperty(n);
    }

    public Chip(String idChip, String se, Integer m, Integer n) {
        this.idSPChip = new SimpleStringProperty(idChip);
        this.id = new SimpleStringProperty(se);
        this.m = new SimpleIntegerProperty(m);
        this.n = new SimpleIntegerProperty(n);
    }

    /*//Chip NEW Constructor
    public Chip(String idSPChip, String id){
        this.idSPChip= new SimpleStringProperty(idSPChip);
        this.id= new SimpleStringProperty(id);
    }*/


    public String getIdSPChip() {
        return idSPChip.get();
    }

    public StringProperty idSPChipProperty() {
        return idSPChip;
    }

    public void setIdSPChip(String idSPChip) {
        this.idSPChip.set(idSPChip);
    }

    public String getId() {
        return id.get();
    }

    public StringProperty id() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getCluster() {
        return cluster.get();
    }

    public StringProperty clusterProperty() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster.set(cluster);
    }

    public int getM() {
        return m.get();
    }

    public IntegerProperty mProperty() {
        return m;
    }

    public void setM(int m) {
        this.m.set(m);
    }

    public int getN() {
        return n.get();
    }

    public IntegerProperty nProperty() {
        return n;
    }

    public void setN(int n) {
        this.n.set(n);
    }

    public Integer getIdCalibration() {
        return idCalibration.get();
    }

    public IntegerProperty idCalibrationProperty() {
        return idCalibration;
    }

    public void setIdCalibration(Integer idCalibration) {
        this.idCalibration.set(idCalibration);
    }

    public String getNameCalibration() {
        return nameCalibration.get();
    }

    public StringProperty nameCalibrationProperty() {
        return nameCalibration;
    }

    public void setNameCalibration(String nameCalibration) {
        this.nameCalibration.set(nameCalibration);
    }

}
