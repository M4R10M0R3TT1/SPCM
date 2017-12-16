package it.unicas.sensiplusConfigurationManager.model.xmlModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class XMLConfiguration{
    private StringProperty driver;
    private StringProperty hostController;
    private StringProperty apiOwner;
    private StringProperty mcu;
    private StringProperty protocol;
    private StringProperty addressingType;
    private StringProperty idCluster;

    public XMLConfiguration(String driver,
                   String hostController, String apiOwner, String mcu, String protocol, String addressingType, String idCluster){
        this.driver= new SimpleStringProperty(driver);
        this.hostController= new SimpleStringProperty(hostController);
        this.apiOwner= new SimpleStringProperty(apiOwner);
        this.mcu=new SimpleStringProperty(mcu);
        this.protocol=new SimpleStringProperty(protocol);
        this.addressingType=new SimpleStringProperty(addressingType);
        this.idCluster=new SimpleStringProperty(idCluster);
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

    public String getIdCluster() {
        return idCluster.get();
    }

    public StringProperty idClusterProperty() {
        return idCluster;
    }

    public void setIdCluster(String idCluster) {
        this.idCluster.set(idCluster);
    }
}
