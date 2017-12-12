package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ClusterOverviewController {

    @FXML
    private TableView<Cluster> clusterTableView;
    @FXML
    private TableColumn<Cluster,String> clusterColumn;
    @FXML
    private TableColumn<Cluster,String> calibrationColumn; //<-----------ATT!!!
    @FXML
    private TableView<Cluster> configurationTableView;
    @FXML
    private TableColumn<Cluster,String> configurationColumn;
    @FXML
    private TableColumn<Cluster,String> driverColumn;
    @FXML
    private TableColumn<Cluster,String> hostControllerColumn;
    @FXML
    private TableColumn<Cluster,String> apiOwnerColumn;
    @FXML
    private TableColumn<Cluster,String> MCUColumn;
    @FXML
    private TableColumn<Cluster,String> protocolColumn;
    @FXML
    private TableColumn<Cluster,String> addressTypeColumn;
    @FXML
    private TableView<Chip>  chipTableView;
    @FXML
    private TableColumn<Chip,String> chipColumn;
    @FXML
    private TableColumn<Chip,String> familyColumn;
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,String> portColumn;
    @FXML
    private TableColumn<Family,String> occupiedByColumn;
    @FXML
    private TableColumn<Family,String> mColumn;
    @FXML
    private TableColumn<Family,String> nColumn;
    @FXML
    private TableColumn<Family,String> measureTechniqueColumn;


    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }
    @FXML
    private void initialize(){

    }

}
