package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by  on 21/11/2017.
 */
public class Cluster {

    private StringProperty idCluster;
    private IntegerProperty idCalibration;
    private StringProperty nameCalibration;
    private IntegerProperty idConfiguration;
    private StringProperty driver;
    private StringProperty hostController;
    private StringProperty apiOwner;
    private StringProperty mcu;
    private StringProperty protocol;
    private StringProperty addressingType;


    //Default constructor
    public Cluster(){this(null,0,null,0,null,null,null,null,null,null);}

    public Cluster(String IdCluster,Integer idCalibration,String nameCalibration, Integer idConfiguration,String driver,
                   String hostController, String apiOwner, String mcu, String protocol, String addressingType){
        this.idCluster=new SimpleStringProperty(IdCluster);
        this.idCalibration= new SimpleIntegerProperty(idCalibration);
        this.nameCalibration= new SimpleStringProperty(nameCalibration);
        this.idConfiguration= new SimpleIntegerProperty(idConfiguration);
        this.driver= new SimpleStringProperty(driver);
        this.hostController= new SimpleStringProperty(hostController);
        this.apiOwner= new SimpleStringProperty(apiOwner);
        this.mcu=new SimpleStringProperty(mcu);
        this.protocol=new SimpleStringProperty(protocol);
        this.addressingType=new SimpleStringProperty(addressingType);
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

    public int getIdCalibration() {
        return idCalibration.get();
    }

    public IntegerProperty idCalibrationProperty() {
        return idCalibration;
    }

    public void setIdCalibration(int idCalibration) {
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

    public int getIdConfiguration() {
        return idConfiguration.get();
    }

    public IntegerProperty idConfigurationProperty() {
        return idConfiguration;
    }

    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration.set(idConfiguration);
    }

    public String getDriver() {
        return driver.get();
    }

    public StringProperty driverProperty() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver.set(driver);
    }

    public String getHostController() {
        return hostController.get();
    }

    public StringProperty hostControllerProperty() {
        return hostController;
    }

    public void setHostController(String hostController) {
        this.hostController.set(hostController);
    }

    public String getApiOwner() {
        return apiOwner.get();
    }

    public StringProperty apiOwnerProperty() {
        return apiOwner;
    }

    public void setApiOwner(String apiOwner) {
        this.apiOwner.set(apiOwner);
    }

    public String getMcu() {
        return mcu.get();
    }

    public StringProperty mcuProperty() {
        return mcu;
    }

    public void setMcu(String mcu) {
        this.mcu.set(mcu);
    }

    public String getProtocol() {
        return protocol.get();
    }

    public StringProperty protocolProperty() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol.set(protocol);
    }

    public String getAddressingType() {
        return addressingType.get();
    }

    public StringProperty addressingTypeProperty() {
        return addressingType;
    }

    public void setAddressingType(String addressingType) {
        this.addressingType.set(addressingType);
    }
}
