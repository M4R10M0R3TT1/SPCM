package it.unicas.sensiplusConfigurationManager.model.xmlModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class XMLCluster{
    private StringProperty idCluster;
    private StringProperty idFam;
    private StringProperty idChip;
    private StringProperty idSensingElement;
    private IntegerProperty m;
    private IntegerProperty n;

    public XMLCluster(String idCluster,String idFam,String idChip,String idSensingElement,Integer m,Integer n){
        this.idCluster= new SimpleStringProperty(idCluster);
        this.idFam= new SimpleStringProperty(idFam);
        this.idChip=new SimpleStringProperty(idChip);
        this.idSensingElement=new SimpleStringProperty(idSensingElement);
        this.m=new SimpleIntegerProperty(m);
        this.n=new SimpleIntegerProperty(n);
    }

    public String getIdCluster() {
        return idCluster.get();
    }

    public StringProperty idClusterProperty() {
        return idCluster;
    }

    public void setIdCluster(String idCluster) {
        this.idCluster.set(idCluster);
    }

    public String getIdFam() {
        return idFam.get();
    }

    public StringProperty idFamProperty() {
        return idFam;
    }

    public void setIdFam(String idFam) {
        this.idFam.set(idFam);
    }

    public String getIdChip() {
        return idChip.get();
    }

    public StringProperty idChipProperty() {
        return idChip;
    }

    public void setIdChip(String idChip) {
        this.idChip.set(idChip);
    }

    public String getIdSensingElement() {
        return idSensingElement.get();
    }

    public StringProperty idSensingElementProperty() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement.set(idSensingElement);
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
}

