package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}
