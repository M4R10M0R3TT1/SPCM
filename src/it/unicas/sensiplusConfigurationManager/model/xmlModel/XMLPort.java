package it.unicas.sensiplusConfigurationManager.model.xmlModel;

import javafx.beans.property.*;

public class XMLPort {

    private IntegerProperty idPort;
    private BooleanProperty intern;
    private StringProperty name;

    public XMLPort(Integer idPort, Boolean intern, String name) {
        this.idPort = new SimpleIntegerProperty(idPort);
        this.intern = new SimpleBooleanProperty(intern);
        this.name = new SimpleStringProperty(name);
    }

    public int getIdPort() {
        return idPort.get();
    }

    public IntegerProperty idPortProperty() {
        return idPort;
    }

    public void setIdPort(int idPort) {
        this.idPort.set(idPort);
    }

    public boolean isIntern() {
        return intern.get();
    }

    public BooleanProperty internProperty() {
        return intern;
    }

    public void setIntern(boolean intern) {
        this.intern.set(intern);
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
}
