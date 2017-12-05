package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Created by  on 23/11/2017.
 */
public class SEOverviewController {

    @FXML
    private TableView<SensingElement> seTableView;
    @FXML
    private TableColumn<SensingElement, String> seColumn;
    @FXML
    private Label rSenseLabel;
    @FXML
    private Label inGainLabel;
    @FXML
    private Label outGainLabel;
    @FXML
    private Label contactsLabel;
    @FXML
    private Label frequencyLabel;
    @FXML
    private Label harmonicLabel;
    @FXML
    private Label dcBiasLabel;
    @FXML
    private Label modeVILabel;
    @FXML
    private Label measureTechniqueLabel;
    @FXML
    private Label measureTypeLabel;
    @FXML
    private Label filterLabel;
    @FXML
    private Label phaseShiftModeLabel;
    @FXML
    private Label phaseShiftLabel;
    @FXML
    private Label IQLabel;
    @FXML
    private Label conversionRateLabel;
    @FXML
    private Label inPortADCLabel;
    @FXML
    private Label nDataLabel;
    @FXML
    private Label measureUnitLabel;
    @FXML
    private Label idSELabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label rangeMinLabel;
    @FXML
    private Label rangeMaxLabel;
    @FXML
    private Label defaultAlarmThresholdLabel;
    @FXML
    private Label multiplerLabel;
    @FXML
    private TableView familyTableView;
   /* @FXML
    private TableColumn<SensingElement, String> idFamilyColumn;
    @FXML
    private TableColumn<SensingElement, String> familyNameColumn;
    @FXML
    private TableColumn<SensingElement, String> portNameColumn;
    @FXML
    private TableColumn<SensingElement, String> portTypeColumn;*/

    @FXML
    private TableColumn<Family, String> idFamilyColumn;
    @FXML
    private TableColumn<Family, String> familyNameColumn;
    @FXML
    private TableColumn<Family, String> portNameColumn;
    @FXML
    private TableColumn<Family, String> portTypeColumn;



    @FXML
    private TableView addFamilyTableView;
    @FXML
    private TableColumn<SensingElement,String> familyIDColumn;

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        seTableView.setItems(mainApp.getSeData());
        familyTableView.setItems(mainApp.getSeFamData());
        handleReadDB();
    }

    public SEOverviewController() {
    }

    @FXML
    private void initialize() {

        seColumn.setCellValueFactory(cellData -> cellData.getValue().idSensingElementProperty());

        showSEDetails(null);
        seTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSEDetails(newValue));

        /*idFamilyColumn.setCellValueFactory(cellData -> cellData.getValue().family_idProperty());
        familyNameColumn.setCellValueFactory(cellData -> cellData.getValue().family_NameProperty());
        portNameColumn.setCellValueFactory(cellData -> cellData.getValue().port_NameProperty());
        portTypeColumn.setCellValueFactory(cellData -> cellData.getValue().port_internalProperty().asObject().asString());*/

        idFamilyColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        familyNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        portNameColumn.setCellValueFactory(cellData -> cellData.getValue().portNameProperty());
        portTypeColumn.setCellValueFactory(cellData -> cellData.getValue().internalProperty().asString());

    }


    private void showSEDetails(SensingElement sensingElement) {
        if (sensingElement != null) {
            idSELabel.setText(sensingElement.getIdSensingElement());
            rSenseLabel.setText(sensingElement.getrSense().toString());
            inGainLabel.setText(sensingElement.getInGain().toString());
            outGainLabel.setText(sensingElement.getOutGain().toString());
            contactsLabel.setText(sensingElement.getContacts());
            frequencyLabel.setText(sensingElement.getFrequency().toString());
            harmonicLabel.setText(sensingElement.getHarmonic());
            dcBiasLabel.setText(sensingElement.getDcBias().toString());
            modeVILabel.setText(sensingElement.getModeVI());
            measureTechniqueLabel.setText(sensingElement.getmeasureTechnique());
            measureTypeLabel.setText(sensingElement.getMeasureType());
            filterLabel.setText(sensingElement.getFilter().toString());
            phaseShiftModeLabel.setText(sensingElement.getPhaseShiftMode());
            phaseShiftLabel.setText(sensingElement.getPhaseShift().toString());
            IQLabel.setText(sensingElement.getIq());
            conversionRateLabel.setText(sensingElement.getConversionRate().toString());
            inPortADCLabel.setText(sensingElement.getInPortADC());
            nDataLabel.setText(sensingElement.getnData().toString());
            nameLabel.setText(sensingElement.getName());
            rangeMinLabel.setText(Double.toString(sensingElement.getRangeMin()));
            rangeMaxLabel.setText(Double.toString(sensingElement.getRangeMax()));
            defaultAlarmThresholdLabel.setText(Double.toString(sensingElement.getDefaultAlarmThreshold()));
            multiplerLabel.setText(Integer.toString(sensingElement.getMultiplier()));
            measureUnitLabel.setText(sensingElement.getMeasureUnit());




            //Parte Famiglia
            try {
                String seSelected = sensingElement.toString();
                System.out.println("Selection: "+seSelected);
               // List<SensingElement> list = SensingElementDAOMySQLImpl.getInstance().selectSeFamily(sensingElement);
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectFamilyAndPort(seSelected);
                mainApp.getSeFamData().clear();
                mainApp.getSeFamData().addAll(list);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Sensing Element.
     */
    @FXML
    private void handleNewSensingElement() {
        SensingElement tempSe = new SensingElement();
        boolean okClicked = mainApp.showSEEditDialog(tempSe, true);

        if (okClicked) {
            try {
                SensingElementDAOMySQLImpl.getInstance().insert(tempSe);
                mainApp.getSeData().add(tempSe);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteSensingElement() {
        int selectedIndex = seTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //--------DELETION CONFIRMATION DIALOG--------
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Are you sure?");
            //---To add an icon to the alert
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("file:resources/images/favicon.png"));
            //---
            alert.setHeaderText("Confirm Deletion");
            alert.setContentText("Are you sure you want to delete the selected element?");

            ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            //---------------------------------------------
                if (result.get() == buttonTypeOne){
                    SensingElement sensingElement = seTableView.getItems().get(selectedIndex);
                    try {
                        SensingElementDAOMySQLImpl.getInstance().delete(sensingElement);
                        seTableView.getItems().remove(selectedIndex);
                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                }
            }
        else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No SensingElement Selected");
            alert.setContentText("Please select a SensingElement in the table.");

            alert.showAndWait();
        }

    }

    @FXML
    private void handleReadDB() {
        // SensingElement tempSe = new SensingElement("");
        SensingElement tempSe = new SensingElement("",0,0,0,"",
                0,"",0,"","",
                "",0,"",0,"",
                0,"",0,"",0.0,
                0.0,0.0,0,"");

        try {
            List<SensingElement> list = SensingElementDAOMySQLImpl.getInstance().select(tempSe);
            mainApp.getSeData().clear();
            mainApp.getSeData().addAll(list);
            showSEDetails(null);

        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during search ...");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }
    // }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Sensing Element.
     */
    @FXML
    private void handleEditSE() {
        SensingElement selectedSensingElement = seTableView.getSelectionModel().getSelectedItem();
        if (selectedSensingElement != null) {
            boolean okClicked = mainApp.showSEEditDialog(selectedSensingElement, true);
            if (okClicked) {
                try {
                    SensingElementDAOMySQLImpl.getInstance().update(selectedSensingElement);
                    showSEDetails(selectedSensingElement);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No SensingElement Selected");
            alert.setContentText("Please select a SensingElement in the table.");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleAddFamily() throws IOException {
        int selectedIndex = seTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0) {
            SensingElement sensingElement = seTableView.getItems().get(selectedIndex);
            boolean okClicked = mainApp.showSEOnFamilyDialog(sensingElement,true);
            /*if (okClicked) {
                try {
                    SensingElementDAOMySQLImpl.getInstance().insertAddSeOnFamily(selectedIndex);
                    showSEDetails(sensingElement);
                } catch (DAOException e) {
                    e.printStackTrace();
                }*/
        }
    }

    private void showFamilyList(SensingElement SE){

    }





}
