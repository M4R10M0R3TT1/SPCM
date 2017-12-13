package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ClusterDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
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
    private TableView<Cluster>  chipTableView;
    @FXML
    private TableColumn<Cluster,String> chipColumn;
    @FXML
    private TableColumn<Cluster,String> familyColumn;
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
        chipTableView.setItems(mainApp.getChipOfCalibrationOnCLusterData());
        portTableView.setItems(mainApp.getChipDetailsOnClusterData());
        showCluster();

    }
    @FXML
    private void initialize(){
        clusterColumn.setCellValueFactory(cellData->cellData.getValue().idClusterProperty());
        clusterTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showClusterDetails(newValue)));

        calibrationColumn.setCellValueFactory(cellData->cellData.getValue().nameCalibrationProperty());
        idCalibrationColumn.setCellValueFactory(cellData->cellData.getValue().idCalibrationProperty().asString());
        calibrationTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showChipDetailsOnCluster(newValue)));

        chipColumn.setCellValueFactory(cellData->cellData.getValue().aProperty());
        familyColumn.setCellValueFactory(cellData->cellData.getValue().bProperty());
        chipTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPort(calibrationTableView.getSelectionModel().getSelectedItem().getIdCalibration(),newValue.getA())));

        idConfigurationColumn.setCellValueFactory(cellData->cellData.getValue().idConfigurationProperty().asString());
        driverColumn.setCellValueFactory(cellData->cellData.getValue().driverProperty());
        hostControllerColumn.setCellValueFactory(cellData->cellData.getValue().hostControllerProperty());
        apiOwnerColumn.setCellValueFactory(cellData->cellData.getValue().apiOwnerProperty());
        MCUColumn.setCellValueFactory(cellData->cellData.getValue().mcuProperty());
        protocolColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());
        addressTypeColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());

        portColumn.setCellValueFactory(cellData->cellData.getValue().portNameProperty());
        occupiedByColumn.setCellValueFactory(cellData->cellData.getValue().occupiedByProperty());
        nColumn.setCellValueFactory(cellData->cellData.getValue().aProperty().asString());
        mColumn.setCellValueFactory(celldata->celldata.getValue().bProperty().asString());

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
                List<Cluster> lista = ClusterDAOMySQLImpl.getInstance().selectConfiguration(cluster);
                mainApp.getConfigurationOnClusterData().clear();
                mainApp.getConfigurationOnClusterData().addAll(lista);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction  ");
                alert.setHeaderText("Error during search ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
            try {
                List<Cluster> lista = ClusterDAOMySQLImpl.getInstance().selectCalibration(cluster);
                mainApp.getCalibrationOnClusterData().clear();
                mainApp.getCalibrationOnClusterData().addAll(lista);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle(" Error during DB interaction  ");
                alert.setHeaderText("Error during search ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    private void showChipDetailsOnCluster(Cluster calibration){
        if(calibration!=null){
            try {
                List<Cluster> lista=ClusterDAOMySQLImpl.getInstance().selectChip(calibration);
                mainApp.getChipOfCalibrationOnCLusterData().clear();
                mainApp.getChipOfCalibrationOnCLusterData().addAll(lista);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle(" Error during DB interaction  ");
                alert.setHeaderText(" Error during search ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    private void showPort(Integer idCalibration, String idChip)
    {
        if(idCalibration!=null){
            List<Family> lista= null;
            try {
                lista = FamilyDAOMySQLImpl.getInstance().selectPortOfChipOnCluster(idCalibration,idChip);
                mainApp.getChipDetailsOnClusterData().clear();
                mainApp.getChipDetailsOnClusterData().addAll(lista);
            } catch (DAOException e) {
                e.printStackTrace();
            }

        }
    }
}

