package it.unicas.sensiplusConfigurationManager.model;

import javax.sound.sampled.Port;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="SensiChips")
public class SensingElementListWrapper {
    private List<SensingElement> SE;
    private List<Family> port;
    private int porta;


    @XmlElement(name="SENSING_ELEMENT")
    public List<SensingElement> getSE(){return  SE;}
    public void setSE(List<SensingElement> SE) {
        this.SE = SE;
    }

    @XmlElement (name="PORT")
    public List<Family> getPort(){return port;}
    public void setPort(List<Family> port) {
        this.port = port;
    }

    @XmlElement(name = "porta")
    public Integer getPorta(){return porta; }
    public void setPorta(Integer porta){this.porta=(porta);}
}


