package model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;

/**
 * Created by Antonio on 21/11/2017.
 */
public class SensingElement {

    private StringProperty SE_name;

    //Default constructor
    public SensingElement(){
        this.SE_name=null;
    }

    public SensingElement(String SE_name){
        this.SE_name=new SimpleStringProperty(SE_name);
    }

    public String getSE_name() {
        return SE_name.get();
    }

    public StringProperty SE_nameProperty() {
        return SE_name;
    }

    public void setSE_name(String SE_name) {
        this.SE_name.set(SE_name);
    }
}
