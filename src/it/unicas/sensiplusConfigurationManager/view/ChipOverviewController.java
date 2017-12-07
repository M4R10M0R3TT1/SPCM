package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;
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
    private TableView<Chip> portTableView;
    @FXML
    private TableColumn<Chip,String> idPortColumn;
    @FXML
    private TableColumn<Chip,String> namePortColumn;
    @FXML
    private TableColumn<Chip,String> internalColumn;
    @FXML
    private TableColumn<Chip,String> idSensingElementColumn;
    @FXML
    private TableView<Chip> calibrationTableView;
    @FXML
    private TableColumn<Chip,Integer> idCalibrationColumn;
    @FXML
    private TableColumn<Chip,String> nameCalibrationColumn;
    @FXML
    private TableColumn<Chip,Integer> mColumn;
    @FXML
    private TableColumn<Chip,Integer> nColumn;
    @FXML
    private TableView<String> clusterTableView;
    @FXML
    private TableColumn<String,String> clusterColumn;

    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;
        chipTableView.setItems(mainApp.getChipData());
        portTableView.setItems(mainApp.getPortAndSEData());
        handleReadDB();

    }

    public ChipOverviewController(){}

    @FXML
    private void initialize(){

        chipColumn.setCellValueFactory(cellData ->cellData.getValue().idSPChipProperty());

        clickOnChip(null);
        chipTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> clickOnChip(newValue)));

        idPortColumn.setCellValueFactory(cellData -> cellData.getValue().idSPPortProperty().asString());
        namePortColumn.setCellValueFactory(cellData -> cellData.getValue().portNameProperty());
        internalColumn.setCellValueFactory(cellData -> cellData.getValue().internalProperty().asString());
        idSensingElementColumn.setCellValueFactory(cellData -> cellData.getValue().sensingElementProperty());



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
            alert.setHeaderText("Error during search ... ");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }

    private void clickOnChip(Chip chip) {
        if (chip != null) {
            chipLabel.setText(chip.getIdSPChip());


            //Port and SensingElement
            try {
                List<Chip> list = ChipDAOMySQLImpl.getInstance().selectPortAndChip(chip);
                mainApp.getPortAndSEData().clear();
                mainApp.getPortAndSEData().addAll(list);

            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during select ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

}
