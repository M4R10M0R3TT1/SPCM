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

    //Parte spPort
    private IntegerProperty idSPPort;
    private BooleanProperty internal;
    private StringProperty portName;
    private StringProperty occupiedBy;

    //Parte spMeasureTechnique
    private StringProperty type;
    private IntegerProperty idSPMeasureTechnique;

    //parte Template
    private IntegerProperty idSPFamilyTemplate;



    //Default constructor
    public Family(){
        this(0,null);
    }

    public Family(Integer idSPFamily,String name,String id,String hwVersion,String sysclock,String osctrim){
        this.idSPFamily=new SimpleIntegerProperty(idSPFamily);
        this.name=new SimpleStringProperty(name);
        this.id=new SimpleStringProperty(id);
        this.hwVersion=new SimpleStringProperty(hwVersion);
        this.sysclock=new SimpleStringProperty(sysclock);
        this.osctrim=new SimpleStringProperty(osctrim);
    }

    //costruttore select
    public Family(Integer idSPFamily,String id){
        this.idSPFamily=new SimpleIntegerProperty(idSPFamily.intValue());
        this.id=new SimpleStringProperty(id);
        this.name=new SimpleStringProperty(""/*"MEASURING_INSTRUMENT"*/);
        this.hwVersion=new SimpleStringProperty(""/*"RUN4"*/);
        this.sysclock=new SimpleStringProperty(""/*"10000000"*/);
        this.osctrim = new SimpleStringProperty(""/*"0x06"*/);
    }

    //costruttore spPort
    public Family(Integer idSPPort,Boolean internal,String portName, String se){
        this.idSPPort=new SimpleIntegerProperty(idSPPort.intValue());
        this.internal=new SimpleBooleanProperty(internal);
        this.portName=new SimpleStringProperty(portName);
        this.occupiedBy=new SimpleStringProperty(se);
    }

    //costruttore spMeasureTechnique
    public Family(String type){
        this.type=new SimpleStringProperty(type);
    }

    //Stavano in Sensing Element----------------------------------------

    //costruttore Family e Port
    public Family(String id,String name,String portName,Boolean internal,Integer idFamilyTemplate){
        this.id=new SimpleStringProperty(id);
        this.name=new SimpleStringProperty(name);
        this.portName=new SimpleStringProperty(portName);
        this.internal=new SimpleBooleanProperty(internal);
        this.idSPFamilyTemplate=new SimpleIntegerProperty(idFamilyTemplate);
    }

    //costruttore availablePort e AddPortOnFamily
    public Family(Integer id, String name, Boolean internal){
        this.idSPPort= new SimpleIntegerProperty(id);
        this.portName= new SimpleStringProperty(name);
        this.internal= new SimpleBooleanProperty(internal);
    }

    //costruttore addTechniqueOnFamily
    public Family(String type,Integer idSPMeasureTechnique){
        this.idSPMeasureTechnique= new SimpleIntegerProperty(idSPMeasureTechnique);
        this.type= new SimpleStringProperty(type);
    }

    //Constructor for SEonFamily
    public Family(Integer id, String family_id, String family_Name){
        this.idSPFamily=new SimpleIntegerProperty(id);
        this.id=new SimpleStringProperty(family_id);
        this.name=new SimpleStringProperty(family_Name);
    }

    //-------------------------------------------------------------------


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
            idSPPort=new SimpleIntegerProperty();
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public int getIdSPFamilyTemplate() {
        return idSPFamilyTemplate.get();
    }

    public IntegerProperty idSPFamilyTemplateProperty() {
        return idSPFamilyTemplate;
    }

    public void setIdSPFamilyTemplate(int idSPFamilyTemplate) {
        this.idSPFamilyTemplate.set(idSPFamilyTemplate);
    }

    public String getOccupiedBy() {
        return occupiedBy.get();
    }

    public StringProperty occupiedByProperty() {
        return occupiedBy;
    }

    public void setOccupiedBy(String occupiedBy) {
        this.occupiedBy.set(occupiedBy);
    }

    public int getIdSPMeasureTechnique() {
        return idSPMeasureTechnique.get();
    }

    public IntegerProperty idSPMeasureTechniqueProperty() {
        return idSPMeasureTechnique;
    }

    public void setIdSPMeasureTechnique(int idSPMeasureTechnique) {
        this.idSPMeasureTechnique.set(idSPMeasureTechnique);
    }
}
