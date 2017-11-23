package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Antonio on 21/11/2017.
 */
public class SensingElement {

    private StringProperty idSensingElement;

    //Default constructor
    public SensingElement(){

        this(null);
    }

    public SensingElement(String idSensingElement){
        this.idSensingElement=new SimpleStringProperty(idSensingElement);
    }

    public String getIdSensingElement() {
        return idSensingElement.get();
    }

    public StringProperty idSensingElementProperty() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement.set(idSensingElement);
    }

    @Override
    public String toString(){
        return idSensingElement.getValue();
    }
}
