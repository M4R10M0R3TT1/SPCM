package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import java.util.List;
import java.util.Optional;

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
    @FXML
    private Button addSensingElementButton;
    @FXML
    private Button deleteSensingElementButton;
    @FXML
    private Button newCalibrationButton;
    @FXML
    private Button editCalibrationButton;
    @FXML
    private Button deleteCalibrationButton;
    @FXML
    private Button deleteFamilyButton;
    @FXML
    private Label famLabel;

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

        calibrationTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> setCalibrationButtons(newValue)));


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
        chipTableView.getSelectionModel().selectFirst();
        portTableView.getSelectionModel().selectFirst();
       // calibrationTableView.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleNewChip(){
        Chip tempChip = new Chip();
        boolean okClicked=mainApp.showChipEditDialog(tempChip,true);

        if(okClicked)
            try{
                ChipDAOMySQLImpl.getInstance().insert(tempChip);
                mainApp.getChipData().addAll(tempChip);

            }catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert...  ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteChip() {
        int selectedIndex = chipTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("WARNING:\n" +
                    "Read carefully before choosing the action!!!");
            alert.setContentText("You are about to DELETE a Chip with all the associations, are you sure you want to continue?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
            if (result.get() == buttonTypeOne){

                Chip chip = chipTableView.getItems().get(selectedIndex);
                try {
                    ChipDAOMySQLImpl.getInstance().delete(chip);
                    chipTableView.getItems().remove(selectedIndex);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
            chipTableView.getSelectionModel().selectFirst();
            portTableView.getSelectionModel().selectFirst();
            //calibrationTableView.getSelectionModel().selectFirst();
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Chip Selected ");
            alert.setContentText("Please select a Chip in the table.");

            alert.showAndWait();
        }
    }

    private void showChipDetails(Chip chip) {
        if (chip != null) {

            //set button
            addSensingElementButton.setDisable(true);
            deleteSensingElementButton.setDisable(true);
            newCalibrationButton.setDisable(true);
            setCalibrationButtons(null);

            mainApp.getCalibrationChip().clear();
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
            if(familyLabel.getText()!=null){
                deleteFamilyButton.setText("Change Family");
                famLabel.setVisible(true);
            }
            else{
                deleteFamilyButton.setText("Select Family");
                famLabel.setVisible(false);
            }
        }
        portTableView.getSelectionModel().selectFirst();

    }

    private void showCalibrationDetails(Family family) {
        Chip selChip=chipTableView.getSelectionModel().getSelectedItem();

        if (family != null) {
            try {
                List<Chip> list = ChipDAOMySQLImpl.getInstance().selectCalibrationChip(selChip,family);
                mainApp.getCalibrationChip().clear();
                mainApp.getCalibrationChip().addAll(list);
                if (list.size()==0){
                    addSensingElementButton.setDisable(false);
                    deleteSensingElementButton.setDisable(true);
                    newCalibrationButton.setDisable(true);
                    setCalibrationButtons(null);
                }
                else{
                    addSensingElementButton.setDisable(true);
                    deleteSensingElementButton.setDisable(false);
                    newCalibrationButton.setDisable(false);
                    setCalibrationButtons(null);
                }
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

    @FXML
    private void handleAddSEOnChip(){
        Chip selChip=chipTableView.getSelectionModel().getSelectedItem();
        Family selPort=portTableView.getSelectionModel().getSelectedItem();
        int selIndex=portTableView.getSelectionModel().getSelectedIndex();
        if (selPort.getOccupiedBy()==null) {
            try {
                Family family = FamilyDAOMySQLImpl.getInstance().selectSEOnPort(selPort, familyLabel.getText());
                if (family != null) {
                    boolean okClicked = false;
                    if (selPort != null) {
                        okClicked = mainApp.showAddSEOnChipDialog(selPort, familyLabel.getText(), selChip.getIdSPChip(), true);
                    }
                    if (okClicked) {
                        showChipDetails(selChip);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("No Sensing Element on Family ");
                    alert.setHeaderText("No Sending Element is available for this Port.");
                    alert.showAndWait();
                }

            } catch (DAOException e) {
                e.printStackTrace();
            }
        }
        portTableView.getSelectionModel().select(selIndex);
    }

    @FXML
    private void handleAddCalibrationOnChip() {
        Chip selChip = chipTableView.getSelectionModel().getSelectedItem();
        Family selPort = portTableView.getSelectionModel().getSelectedItem();
        boolean okClicked = false;
        if (selPort != null) {
            okClicked = mainApp.showAddCalibrationOnChipDialog(selPort, familyLabel.getText(), selChip, true);
        }
        if (okClicked) {
            showChipDetails(selChip);
        }
    }
    @FXML
    private void handleEditCalibrationOnChip() {
        Chip selChip = chipTableView.getSelectionModel().getSelectedItem();
        Family selPort = portTableView.getSelectionModel().getSelectedItem();
        boolean okClicked = false;
        if (selPort != null) {
            okClicked = mainApp.showEditCalibrationOnChipDialog(selPort, familyLabel.getText(), selChip, calibrationTableView.getSelectionModel().getSelectedItem(), true);
        }
        if (okClicked) {
            showChipDetails(selChip);
        }
    }
    @FXML
    private void handleDelSEOnChip(){
        int selectedIndex = portTableView.getSelectionModel().getSelectedIndex();
        Family selPort= portTableView.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("WARNING:\n" +
                    "Read carefully before choosing the action!!!");
            alert.setContentText("If you remove the SE from the chip all the relevant calibrations will be lost, are you sure you want to continue?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
            if (result.get() == buttonTypeOne){

                Chip chip = chipTableView.getSelectionModel().getSelectedItem();
                try {
                    ChipDAOMySQLImpl.getInstance().removeSEOnChip(chip,selPort.getOccupiedBy());
                    showChipDetails(chip);
                    showCalibrationDetails(null);
                    portTableView.getSelectionModel().select(selectedIndex);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No port Selected ");
            alert.setContentText("Please select a port in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleDeleteCalibrationOnChip(){
        int selectedIndex = portTableView.getSelectionModel().getSelectedIndex();
        Chip calibration = calibrationTableView.getSelectionModel().getSelectedItem();
        Chip chip=chipTableView.getSelectionModel().getSelectedItem();
        int idPort=portTableView.getSelectionModel().getSelectedItem().getIdSPPort();

        if (selectedIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("WARNING:\n" +
                    "Read carefully before choosing the action!!!");
            alert.setContentText("This calibrations will be lost, are you sure you want to continue?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
            if (result.get() == buttonTypeOne){

                try {
                    ChipDAOMySQLImpl.getInstance().deleteCalibrationOnChip(calibration,chip.getIdSPChip(),idPort);
                    showChipDetails(chip);
                    portTableView.getSelectionModel().select(selectedIndex);
                    portTableView.getSelectionModel().selectFirst();
                    calibrationTableView.getSelectionModel().selectFirst();
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Calibration Selected ");
            alert.setContentText("Please select a Calibration in the table.");

            alert.showAndWait();
        }
    }
    private void setCalibrationButtons(Chip chip){
        if (chip!=null){
            editCalibrationButton.setDisable(false);
            deleteCalibrationButton.setDisable(false);
        }else{
            editCalibrationButton.setDisable(true);
            deleteCalibrationButton.setDisable(true);
        }
    }

    @FXML
    private void handleDeassociateChip(){
        Chip chip=chipTableView.getSelectionModel().getSelectedItem();
        int selindex=chipTableView.getSelectionModel().getSelectedIndex();
        if(chip!=null){
            boolean okClicked = mainApp.showChipEditDialog(chip,true);
            if(okClicked)
                try {
                    ChipDAOMySQLImpl.getInstance().deassociate(chip);
                    mainApp.getChipData().addAll(chip);
                    handleReadDB();


                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction  ");
                    alert.setHeaderText("Error during insert...  ");
                    alert.setContentText(e.getMessage());

                    alert.showAndWait();
                }
        }
    }





}
