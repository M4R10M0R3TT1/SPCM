package it.unicas.sensiplusConfigurationManager.model;

import javafx.beans.property.*;

/**
 * Created by  on 21/11/2017.
 */
public class Chip {

    private StringProperty idSPChip;
    private StringProperty idFamily;



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

    public String getIdFamily() {
        return idFamily.get();
    }

    public StringProperty idFamilyProperty() {
        return idFamily;
    }

    public void setIdFamily(String idFamily) {
        this.idFamily.set(idFamily);
    }


}
