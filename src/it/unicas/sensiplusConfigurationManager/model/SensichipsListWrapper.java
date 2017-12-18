package it.unicas.sensiplusConfigurationManager.model;

import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLCluster;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLConfiguration;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLFamily;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLPort;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Sensichips")
public class SensichipsListWrapper {

    private List<XMLConfiguration> configuration;
    private List<XMLCluster> cluster;
    private List<XMLFamily> family;
    private List<SensingElement> sensingElement;
    private List<XMLPort> port;

    @XmlElementWrapper(name="CONFIGURATIONS")
    @XmlElement(name = "CONFIGURATION")
    public List<XMLConfiguration> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<XMLConfiguration> configuration) {
        this.configuration=configuration;
    }

    @XmlElementWrapper(name="CLUSTERS")
    @XmlElement(name = "CLUSTER")
    public List<XMLCluster> getCluster() {
        return cluster;
    }

    public void setCluster(List<XMLCluster> cluster) {
        this.cluster=cluster;
    }

    @XmlElementWrapper(name="FAMILIES")
    @XmlElement(name = "FAMILY")
    public  List<XMLFamily> getFamily(){return family;}

    public void setFamily(List<XMLFamily> family) {this.family=family; }

    @XmlElementWrapper(name="SENSINGELEMENTS")
    @XmlElement(name = "SENSINGELEMENT")
    public List<SensingElement> getSensingElement() {return sensingElement; }

    public void setSensingElement(List<SensingElement> sensingElement) {this.sensingElement=sensingElement;}

    @XmlElementWrapper(name="PORTS")
    @XmlElement(name = "PORT")
    public List<XMLPort> getPort() {
        return port;
    }

    public void setPort(List<XMLPort> port) {
        this.port=port;
    }
}

