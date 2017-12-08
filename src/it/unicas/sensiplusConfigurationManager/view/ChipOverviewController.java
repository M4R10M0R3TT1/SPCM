package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


import java.util.List;

public class ChipOverviewController {

    @FXML
    private Label chipLabel;
    @FXML
    private Label familyLabel;
    @FXML
    private TableView<Chip> chipTableView;
    @FXML
    private TableColumn<Chip,String> chipColumn;
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,String> idPortColumn;
    @FXML
    private TableColumn<Family,String> namePortColumn;
    @FXML
    private TableColumn<Family,String> internalColumn;
    @FXML
    private TableColumn<Family,String> idSensingElementColumn;
    @FXML
    private TableView<Chip> calibrationTableView;
    @FXML
    private TableColumn<Chip,String> idCalibrationColumn;
    @FXML
    private TableColumn<Chip,String> nameCalibrationColumn;
    @FXML
    private TableColumn<Chip,String> mColumn;
    @FXML
    private TableColumn<Chip,String> nColumn;
    @FXML
    private TableView<Chip> clusterTableView;
    @FXML
    private TableColumn<Chip,String> clusterColumn;

    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;
        chipTableView.setItems(mainApp.getChipData());
        portTableView.setItems(mainApp.getPortAndSEData());
        clusterTableView.setItems(mainApp.getClusterChip());
        calibrationTableView.setItems(mainApp.getCalibrationChip());
        handleReadDB();

    }

    public ChipOverviewController(){}

    @FXML
    private void initialize(){

        chipColumn.setCellValueFactory(cellData ->cellData.getValue().idSPChipProperty());

        showChipDetails(null);
        chipTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showChipDetails(newValue)));

        idPortColumn.setCellValueFactory(cellData -> cellData.getValue().idSPPortProperty().asString());
        namePortColumn.setCellValueFactory(cellData -> cellData.getValue().portNameProperty());
        internalColumn.setCellValueFactory(cellData -> cellData.getValue().internalProperty().asString());
        idSensingElementColumn.setCellValueFactory(cellData -> cellData.getValue().occupiedByProperty());
        clusterColumn.setCellValueFactory(cellData->cellData.getValue().idSPChipProperty());
        portTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showCalibrationDetails(newValue)));

        idCalibrationColumn.setCellValueFactory(cellData -> cellData.getValue().idCalibrationProperty().asString());
        nameCalibrationColumn.setCellValueFactory(cellData -> cellData.getValue().nameCalibrationProperty());
        mColumn.setCellValueFactory(cellData -> cellData.getValue().mProperty().asString());
        nColumn.setCellValueFactory(cellData -> cellData.getValue().nProperty().asString());


    }

    @FXML
    private void handleReadDB(){
        Chip tempChip = new Chip("");
        try{
            List<Chip> list = ChipDAOMySQLImpl.getInstance().select(tempChip);
            mainApp.getChipData().clear();
            mainApp.getChipData().addAll(list);
        }catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during search ...  ");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    private void showChipDetails(Chip chip) {
        if (chip != null) {
            chipLabel.setText(chip.getIdSPChip());
            try{
                familyLabel.setText(ChipDAOMySQLImpl.getInstance().selectFamilyofChip(chip));
            } catch (DAOException e) {
                e.printStackTrace();
            }

            //Port and SensingElement
            try {
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectPortOnChip(chip.getIdSPChip());
                mainApp.getPortAndSEData().clear();
                mainApp.getPortAndSEData().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during select ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
            try {
                List<Chip> list = ChipDAOMySQLImpl.getInstance().selectClusterChip(chip);
                mainApp.getClusterChip().clear();
                mainApp.getClusterChip().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during select ...  ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    private void showCalibrationDetails(Family family) {
        Chip selChip=chipTableView.getSelectionModel().getSelectedItem();
        if (family != null) {
            try {
                List<Chip> list = ChipDAOMySQLImpl.getInstance().selectCalibrationChip(selChip,family);
                mainApp.getCalibrationChip().clear();
                mainApp.getCalibrationChip().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction ");
                alert.setHeaderText("Error during select ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }

        }
    }



}
