package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Antonio on 21/11/2017.
 */
public class Cluster {

    private StringProperty IdCluster; //Id o id?
    private StringProperty _idSerial;
    private StringProperty _seName;

    //Default constructor
    public Cluster(){this(null,null,null);}

    public Cluster(String IdCluster,String _idSerial,String _seName){
        this.IdCluster=new SimpleStringProperty(IdCluster);
        this._idSerial=new SimpleStringProperty(_idSerial);
        this._seName=new SimpleStringProperty(_seName);
    }

    public String getIdCluster() {
        return IdCluster.get();
    }

    public StringProperty idClusterProperty() {
        return IdCluster;
    }

    public void setIdCluster(String idCluster) {
        this.IdCluster.set(idCluster);
    }

    public String get_idSerial() {
        return _idSerial.get();
    }

    public StringProperty _idSerialProperty() {
        return _idSerial;
    }

    public void set_idSerial(String _idSerial) {
        this._idSerial.set(_idSerial);
    }

    public String get_seName() {
        return _seName.get();
    }

    public StringProperty _seNameProperty() {
        return _seName;
    }

    public void set_seName(String _seName) {
        this._seName.set(_seName);
    }
}
