package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.*;

/**
 * Created by  on 21/11/2017.
 */
public class Chip {

    private StringProperty idSPChip;
    private IntegerProperty idFamily;

    //Parte spPort
    private IntegerProperty idSPPort;
    private BooleanProperty internal;
    private StringProperty portName;

    private StringProperty sensingElement;

    //Default constructor
    public Chip(){this(null);}

    public Chip(String idSPChip){
        this.idSPChip=new SimpleStringProperty(idSPChip);

    }

    //Costruttore selectPortAndSensingElement
    public Chip(Integer idPort,String name,Boolean internal,String sE){
        this.idSPPort=new SimpleIntegerProperty(idPort);
        this.portName=new SimpleStringProperty(name);
        this.internal=new SimpleBooleanProperty(internal);
        this.sensingElement= new SimpleStringProperty(sE);
    }

    public String getIdSPChip() {
        return idSPChip.get();
    }

    public StringProperty idSPChipProperty() {
        return idSPChip;
    }

    public void setIdSPChip(String idSPChip) {
        this.idSPChip.set(idSPChip);
    }

    public int getIdFamily() {
        return idFamily.get();
    }

    public IntegerProperty idFamilyProperty() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily.set(idFamily);
    }

    public int getIdSPPort() {
        return idSPPort.get();
    }

    public IntegerProperty idSPPortProperty() {
        return idSPPort;
    }

    public void setIdSPPort(int idSPPort) {
        this.idSPPort.set(idSPPort);
    }

    public boolean isInternal() {
        return internal.get();
    }

    public BooleanProperty internalProperty() {
        return internal;
    }

    public void setInternal(boolean internal) {
        this.internal.set(internal);
    }

    public String getPortName() {
        return portName.get();
    }

    public StringProperty portNameProperty() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName.set(portName);
    }

    public String getSensingElement() {
        return sensingElement.get();
    }

    public StringProperty sensingElementProperty() {
        return sensingElement;
    }

    public void setSensingElement(String sensingElement) {
        this.sensingElement.set(sensingElement);
    }
}
