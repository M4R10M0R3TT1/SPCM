package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by  on 21/11/2017.
 */
public class Chip {

    private StringProperty idSPChip;
    private IntegerProperty idFamily;

    //Default constructor
    public Chip(){this(null);}

    public Chip(String idSPChip){
        this.idSPChip=new SimpleStringProperty(idSPChip);

    }

    public String getIdSPChip() {
        return idSPChip.get();
    }

    public StringProperty idSPChipProperty() {
        return idSPChip;
    }

    public void setIdSPChip(String idSPChip) {
        this.idSPChip.set(idSPChip);
    }

    public int getIdFamily() {
        return idFamily.get();
    }

    public IntegerProperty idFamilyProperty() {
        return idFamily;
    }

    public void setIdFamily(int idFamily) {
        this.idFamily.set(idFamily);
    }
}
