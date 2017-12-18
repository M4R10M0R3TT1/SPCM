package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ClusterDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.xmlDAO.*;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLCluster;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLConfiguration;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLFamily;
import it.unicas.sensiplusConfigurationManager.model.xmlModel.XMLPort;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;
import java.util.Optional;


public class ClusterOverviewController {

    @FXML
    private TableView<Cluster> clusterTableView;
    @FXML
    private TableColumn<Cluster,String> clusterColumn;
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
    private Button addConfigurationButton;
    @FXML
    private Button editConfigurationButton;
    @FXML
    private Button deleteConfigurationButton;
    @FXML
    private Button deleteClusterButton;
    @FXML
    private Button calibrationButton;
    @FXML
    private Button xmlButton;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        clusterTableView.setItems(mainApp.getClusterData());
        configurationTableView.setItems(mainApp.getConfigurationOnClusterData());
        chipTableView.setItems(mainApp.getChipOnClusterData());
        portTableView.setItems(mainApp.getChipDetailsOnClusterData());
        showCluster();
    }

    @FXML
    private void initialize(){
        clusterColumn.setCellValueFactory(cellData->cellData.getValue().idClusterProperty());
        calibrationColumn.setCellValueFactory(cellData->cellData.getValue().nameCalibrationProperty());
        idCalibrationColumn.setCellValueFactory(cellData->cellData.getValue().idCalibrationProperty().asString());
        clusterTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showClusterDetails(newValue)));



        chipColumn.setCellValueFactory(cellData->cellData.getValue().aProperty());
        familyColumn.setCellValueFactory(cellData->cellData.getValue().bProperty());
        chipTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showPort(newValue)));

        idConfigurationColumn.setCellValueFactory(cellData->cellData.getValue().idConfigurationProperty().asString());
        driverColumn.setCellValueFactory(cellData->cellData.getValue().driverProperty());
        hostControllerColumn.setCellValueFactory(cellData->cellData.getValue().hostControllerProperty());
        apiOwnerColumn.setCellValueFactory(cellData->cellData.getValue().apiOwnerProperty());
        MCUColumn.setCellValueFactory(cellData->cellData.getValue().mcuProperty());
        protocolColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());
        addressTypeColumn.setCellValueFactory(cellData->cellData.getValue().protocolProperty());
        configurationTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> setButtonConfiguration(newValue)));

        portColumn.setCellValueFactory(cellData->cellData.getValue().portNameProperty());
        occupiedByColumn.setCellValueFactory(cellData->cellData.getValue().occupiedByProperty());
        nColumn.setCellValueFactory(cellData->cellData.getValue().aProperty().asString());
        mColumn.setCellValueFactory(celldata->celldata.getValue().bProperty().asString());

    }

    @FXML
    private void handleNewCluster(){
        Cluster temp= new Cluster();
        boolean okClicked=mainApp.showNewClusterDialog(temp);
        if(okClicked){
            try {
                ClusterDAOMySQLImpl.getInstance().insertCluster(temp);
                temp.setNameCalibration(null);
                clusterTableView.getItems().add(temp);
                clusterTableView.getSelectionModel().selectLast();
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during 'new cluster'");
                alert.setHeaderText("A cluster with this identifier already exists!");
                alert.showAndWait();
            }

        }
    }
    @FXML
    private void handleDeleteCluster(){
        Cluster tempCluster=clusterTableView.getSelectionModel().getSelectedItem();
        int selIndex=clusterTableView.getSelectionModel().getSelectedIndex();
        if (selIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("WARNING:\n");
            alert.setContentText("DELETE the Cluster and the relative details?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
            if (result.get() == buttonTypeOne) {
                try {
                    ClusterDAOMySQLImpl.getInstance().delete(tempCluster);
                    clusterTableView.getItems().remove(selIndex);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void handleNewConfiguration() {
        Cluster tempCluster = new Cluster();
        tempCluster.setIdCluster(clusterTableView.getSelectionModel().getSelectedItem().getIdCluster());
        boolean okClicked = mainApp.showNewConfigurationDialog(tempCluster);

        if (okClicked) {
            try {
                ClusterDAOMySQLImpl.getInstance().insertConfiguration(tempCluster);
                mainApp.getConfigurationOnClusterData().add(tempCluster);
                showClusterDetails(clusterTableView.getSelectionModel().getSelectedItem());
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction ");
                alert.setHeaderText(" Error during insert ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
        configurationTableView.getSelectionModel().select(tempCluster);
    }
    @FXML
    private void handleEditConfiguration() {
        Cluster tempCluster=configurationTableView.getSelectionModel().getSelectedItem();
        int selIndex=mainApp.getConfigurationOnClusterData().indexOf(tempCluster);
        boolean okClicked = mainApp.showEditConfigurationDialog(tempCluster);
        if (okClicked) {
            try {
                ClusterDAOMySQLImpl.getInstance().updateConfiguration(tempCluster);
                mainApp.getConfigurationOnClusterData().set(selIndex,tempCluster);
                showClusterDetails(clusterTableView.getSelectionModel().getSelectedItem());
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction ");
                alert.setHeaderText("Error during update...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }
    @FXML
    private void handleDeleteConfiguration(){
        Cluster tempCluster=configurationTableView.getSelectionModel().getSelectedItem();
        int selIndex=configurationTableView.getSelectionModel().getSelectedIndex();
        if (selIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("WARNING:\n");
            alert.setContentText("DELETE the Cluster configuration?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
            if (result.get() == buttonTypeOne) {
                try {
                    ClusterDAOMySQLImpl.getInstance().deleteConfiguration(tempCluster);
                    configurationTableView.getItems().remove(selIndex);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @FXML
    private void handleCalibrationButton(){
        Cluster selCluster=clusterTableView.getSelectionModel().getSelectedItem();
        int selIndex=clusterTableView.getSelectionModel().getFocusedIndex();
        if(selCluster.getNameCalibration()!=null){
            try {
                ClusterDAOMySQLImpl.getInstance().removeCalibrationOnCluster(selCluster);
                showCluster();
                clusterTableView.getSelectionModel().select(selIndex);
                showPort(null);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else {
            boolean okClicked = mainApp.showAddCalibrationOnClusterDialog(selCluster);
            if (okClicked){
                try {
                    ClusterDAOMySQLImpl.getInstance().addCalibrationOnCluster(selCluster);
                    showCluster();
                    clusterTableView.getSelectionModel().select(selIndex);
                    showClusterDetails(selCluster);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        }
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
        configurationTableView.getSelectionModel().selectFirst();
    }

    private void showClusterDetails(Cluster cluster){
        if(cluster!=null){
            try {
                List<Cluster> lista = ClusterDAOMySQLImpl.getInstance().selectConfiguration(cluster);
                mainApp.getConfigurationOnClusterData().clear();
                mainApp.getConfigurationOnClusterData().addAll(lista);
            } catch (DAOException e) {
              e.getStackTrace();
            }

            try {
                List<Cluster> lista=ClusterDAOMySQLImpl.getInstance().selectChip(cluster);
                mainApp.getChipOnClusterData().clear();
                mainApp.getChipOnClusterData().addAll(lista);
            } catch (DAOException e) {
                e.getStackTrace();
            }
            deleteClusterButton.setDisable(false);
            calibrationButton.setDisable(false);
            addConfigurationButton.setDisable(false);
            if(cluster.getNameCalibration()==null){
                calibrationButton.setText("Add Calibration");
            }else{
                calibrationButton.setText("Remove Calibration");
            }
        }else{
            addConfigurationButton.setDisable(true);
            deleteConfigurationButton.setDisable(true);
            deleteClusterButton.setDisable(true);
            calibrationButton.setDisable(true);
        }
        configurationTableView.getSelectionModel().selectFirst();
        chipTableView.getSelectionModel().selectFirst();
    }

    private void showPort(Cluster chip){
        int  idCalibration=clusterTableView.getSelectionModel().getSelectedItem().getIdCalibration();
        if(chip!=null){
            List<Family> lista= null;
            try {
                lista = FamilyDAOMySQLImpl.getInstance().selectPortOfChipOnCluster(idCalibration,chip.getA());
                mainApp.getChipDetailsOnClusterData().clear();
                mainApp.getChipDetailsOnClusterData().addAll(lista);
            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else{
            mainApp.getChipDetailsOnClusterData().clear();
        }
    }

    private void setButtonConfiguration(Cluster configuration){
        if(configuration!=null){
            editConfigurationButton.setDisable(false);
            deleteConfigurationButton.setDisable(false);
            xmlButton.setDisable(false);
        }else{
            editConfigurationButton.setDisable(true);
            deleteConfigurationButton.setDisable(true);
            xmlButton.setDisable(true);
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveSensichipsToFile(file);
        }
    }

    @FXML
    private void xmlGenerator(){
        Cluster tempConf=configurationTableView.getSelectionModel().getSelectedItem();
        int selConf=tempConf.getIdConfiguration();
        try {
            List<XMLConfiguration> listConf = XMLDAOConfiguration.getInstance().selectConf(selConf);
            mainApp.getXmlConfigurationData().clear();
            mainApp.getXmlConfigurationData().addAll(listConf);

            List<XMLCluster> listCluster=XMLDAOCluster.getInstance().selectCluster(selConf);
            mainApp.getXmlClusterData().clear();
            mainApp.getXmlClusterData().addAll(listCluster);

            List<XMLFamily> listFamily = XMLDAOFamily.getInstance().selectFamily(selConf);
            mainApp.getXmlFamilyData().clear();
            mainApp.getXmlFamilyData().addAll(listFamily);

            List<SensingElement> listSensingElement = XMLDAOSensingElement.getInstance().selectSensingElement(selConf);
            mainApp.getXmlSensingElementData().clear();
            mainApp.getXmlSensingElementData().addAll(listSensingElement);

            List<XMLPort> listPort = XMLDAOPort.getInstance().selectPort(selConf);
            mainApp.getXmlPortData().clear();
            mainApp.getXmlPortData().addAll(listPort);



        } catch (DAOException e) {
            e.printStackTrace();
        }
        handleSaveAs();
    }


}

