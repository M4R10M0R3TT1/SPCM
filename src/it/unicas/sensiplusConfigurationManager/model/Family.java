package it.unicas.sensiplusConfigurationManager.model;

/**
 * Created by Antonio on 21/11/2017.
 */
public class Family {

    private String idFamily;
    private String idSensingElement;
    private String port;

    //Default constructor
    public Family(){this(null,null,null);}


    public Family(String idFamily,String idSensingElement,String port){
        this.idFamily=new String(idFamily);
        this.idSensingElement=new String(idSensingElement);
        this.port=new String(port);
    }

    public String getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(String idFamily) {
        this.idFamily = idFamily;
    }

    public String getIdSensingElement() {
        return idSensingElement;
    }

    public void setIdSensingElement(String idSensingElement) {
        this.idSensingElement = idSensingElement;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
