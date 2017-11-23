package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by  on 21/11/2017.
 */
public class Chip {

    private StringProperty IdSerial; //non dovrebbe essere idSerial?
    private StringProperty _idFamily;

    //Default constructor
    public Chip(){this(null,null);}

    public Chip(String IdSerial,String _idFamily){
        this.IdSerial=new SimpleStringProperty(IdSerial);
        this._idFamily=new SimpleStringProperty(_idFamily);
    }

    public String getIdSerial() {
        return IdSerial.get();
    }

    public StringProperty idSerialProperty() {
        return IdSerial;
    }

    public void setIdSerial(String idSerial) {
        this.IdSerial.set(idSerial);
    }

    public String get_idFamily() {
        return _idFamily.get();
    }

    public StringProperty _idFamilyProperty() {
        return _idFamily;
    }

    public void set_idFamily(String _idFamily) {
        this._idFamily.set(_idFamily);
    }
}
