package it.unicas.sensiplusConfigurationManager.model.xmlModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Antonio on 16/12/2017.
 */
public class XMLFamily {

    private StringProperty id;
    private IntegerProperty idSPFamily;
    private StringProperty famName;
    private StringProperty hwVersion;
    private StringProperty sysclock;
    private StringProperty osctrim;

    public XMLFamily(Integer idSPFamily,String famName,String id,String hwVersion,String sysclock,String osctrim){
        this.idSPFamily=new SimpleIntegerProperty(idSPFamily);
        this.famName=new SimpleStringProperty(famName);
        this.id=new SimpleStringProperty(id);
        this.hwVersion=new SimpleStringProperty(hwVersion);
        this.sysclock=new SimpleStringProperty(sysclock);
        this.osctrim=new SimpleStringProperty(osctrim);
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
        return idSPFamily.get();
    }

    public IntegerProperty idSPFamilyProperty() {
        return idSPFamily;
    }

    public void setIdSPFamily(int idSPFamily) {
        this.idSPFamily.set(idSPFamily);
    }

    public String getFamName() {
        return famName.get();
    }

    public StringProperty famNameProperty() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName.set(famName);
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
