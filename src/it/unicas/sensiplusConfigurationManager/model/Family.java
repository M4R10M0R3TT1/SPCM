package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by La Famigghia on 21/11/2017.
 */
public class Family {

    private StringProperty idFamily;
    private StringProperty family_Name;
    private StringProperty port;

    //Default constructor
    public Family(){this(null);}


    public Family(String idFamily,String family_Name,String port){
        this.idFamily=new SimpleStringProperty(idFamily);
        this.family_Name=new SimpleStringProperty(family_Name);
        this.port=new SimpleStringProperty(port);
    }

    public Family(String idFamily){
        this.idFamily=new SimpleStringProperty(idFamily);
        this.family_Name=new SimpleStringProperty("");
        this.port=new SimpleStringProperty("");

    }

    public String getIdFamily() {
        return idFamily.get();
    }

    public StringProperty idFamilyProperty() {
        return idFamily;
    }

    public void setIdFamily(String idFamily) {
        this.idFamily.set(idFamily);
    }

    public String getFamily_Name() {
        return family_Name.get();
    }

    public StringProperty family_NameProperty() {
        return family_Name;
    }

    public void setFamily_Name(String family_Name) {
        this.family_Name.set(family_Name);
    }

    public String getPort() {
        return port.get();
    }

    public StringProperty portProperty() {
        return port;
    }

    public void setPort(String port) {
        this.port.set(port);
    }
}
