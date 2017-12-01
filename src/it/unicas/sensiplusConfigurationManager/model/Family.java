package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.*;

/**
 * Created by La Famigghia on 21/11/2017.
 */
public class Family {

    private StringProperty id;
    private IntegerProperty idSPFamily;
    private StringProperty name;
    private StringProperty hwVersion;
    private StringProperty sysclock;
    private StringProperty osctrim;

    private IntegerProperty idSPPort;
    private BooleanProperty internal;
    private StringProperty portName;


    //Default constructor
    public Family(){
        this(null,null);
    }

    public Family(Integer idSPFamily,String name,String id,String hwVersion,String sysclock,String osctrim){
        this.idSPFamily=new SimpleIntegerProperty(idSPFamily);
        this.name=new SimpleStringProperty(name);
        this.id=new SimpleStringProperty(id);
        this.hwVersion=new SimpleStringProperty(hwVersion);
        this.sysclock=new SimpleStringProperty(sysclock);
        this.osctrim=new SimpleStringProperty(osctrim);
    }

    public Family(Integer idSPFamily,String id){
        this.idSPFamily=new SimpleIntegerProperty(idSPFamily.intValue());
        this.id=new SimpleStringProperty(id);
        this.hwVersion=new SimpleStringProperty("");
        this.sysclock=new SimpleStringProperty("");
        this.osctrim = new SimpleStringProperty("");

    }

    public Family(Integer idSPPort,Boolean internal,String portName){
        this.idSPPort=new SimpleIntegerProperty(idSPPort.intValue());
        this.internal=new SimpleBooleanProperty(internal);
        this.portName=new SimpleStringProperty(portName);
    }




    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public int getIdSPFamily() {
        if(idSPFamily==null){
            idSPFamily=new SimpleIntegerProperty();
        }
        return idSPFamily.get();
    }

    public IntegerProperty idSPFamilyProperty() {
        return idSPFamily;
    }

    public void setIdSPFamily(int idSPFamily) {
        if(this.idSPFamily==null){
            this.idSPFamily=new SimpleIntegerProperty();
        }
        this.idSPFamily.set(idSPFamily);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getHwVersion() {
        return hwVersion.get();
    }

    public StringProperty hwVersionProperty() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion.set(hwVersion);
    }

    public String getSysclock() {
        return sysclock.get();
    }

    public StringProperty sysclockProperty() {
        return sysclock;
    }

    public void setSysclock(String sysclock) {
        this.sysclock.set(sysclock);
    }

    public String getOsctrim() {
        return osctrim.get();
    }

    public StringProperty osctrimProperty() {
        return osctrim;
    }

    public void setOsctrim(String osctrim) {
        this.osctrim.set(osctrim);
    }

    public int getIdSPPort() {
        if(idSPPort==null){
            idSPPort=new SimpleIntegerProperty(0);
        }
        return idSPPort.get();
    }

    public IntegerProperty idSPPortProperty() {
        return idSPPort;
    }

    public void setIdSPPort(int idSPPort) {
        if(this.idSPPort==null){
            this.idSPPort=new SimpleIntegerProperty();
        }
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
}
