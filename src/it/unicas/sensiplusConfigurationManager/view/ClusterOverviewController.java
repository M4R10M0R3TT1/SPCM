package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ClusterDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;


public class ClusterOverviewController {

    @FXML
    private TableView<Cluster> clusterTableView;
    @FXML
    private TableColumn<Cluster,String> clusterColumn;
    @FXML
    private TableView<Cluster> calibrationTableView;
    @FXML
    private TableColumn<Cluster,String> calibrationColumn;
    @FXML
    private TableColumn<Cluster,String> idCalibrationColumn;
    @FXML
    private TableView<Cluster> configurationTableView;
    @FXML
    private TableColumn<Cluster,String> idConfigurationColumn;
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
        clusterTableView.setItems(mainApp.getClusterData());
        configurationTableView.setItems(mainApp.getConfigurationOnClusterData());
        calibrationTableView.setItems(mainApp.getCalibrationOnClusterData());
        showCluster();

    }
    @FXML
    private void initialize(){
        clusterColumn.setCellValueFactory(cellData->cellData.getValue().idClusterProperty());
        clusterTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showClusterDetails(newValue)));

        calibrationColumn.setCellValueFactory(cellData->cellData.getValue().nameCalibrationProperty());
        idCalibrationColumn.setCellValueFactory(cellData->cellData.getValue().idCalibrationProperty().asString());



        idConfigurationColumn.setCellValueFactory(cellData->cellData.getValue().idConfigurationProperty().asString());
        driverColumn.setCellValueFactory(cellData->cellData.getValue().driverProperty());
        hostControllerColumn.setCellValueFactory(cellData->cellData.getValue().hostControllerProperty());
        apiOwnerColumn.setCellValueFactory(cellData->cellData.getValue().apiOwnerProperty());
        MCUColumn.setCellValueFactory(cellData->cellData.getValue().mcuProperty());
        protocolColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());
        addressTypeColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());

    }

    private void showCluster(){
        Cluster tempCluster = new Cluster();
        try {
            List<Cluster> list = ClusterDAOMySQLImpl.getInstance().select(tempCluster);
            mainApp.getClusterData().clear();
            mainApp.getClusterData().addAll(list);
           // showClusterDetails(null);

        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction ");
            alert.setHeaderText("Error during search... ");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
        clusterTableView.getSelectionModel().selectFirst();
    }

    private void showClusterDetails(Cluster cluster){
        if(cluster!=null){
            try {
                List<Cluster> list = ClusterDAOMySQLImpl.getInstance().selectConfiguration(cluster);
                mainApp.getConfigurationOnClusterData().clear();
                mainApp.getConfigurationOnClusterData().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction  ");
                alert.setHeaderText("Error during search ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
            try {
                List<Cluster> list = ClusterDAOMySQLImpl.getInstance().selectCalibration(cluster);
                mainApp.getConfigurationOnClusterData().clear();
                mainApp.getConfigurationOnClusterData().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle(" Error during DB interaction  ");
                alert.setHeaderText("Error during search ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
       // clusterTableView.getSelectionModel().selectFirst();
    }
}

