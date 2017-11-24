package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

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

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public SEOverviewController(){}

    @FXML
    private void initialize() {

        seColumn.setCellValueFactory(cellData -> cellData.getValue().idSensingElementProperty());

        /*SensingElement tempSe=new SensingElement("");
        try{
            List<SensingElement> list= SensingElementDAOMySQLImpl.getInstance().select(tempSe);
            mainApp.getSeData().clear();
            mainApp.getSeData().addAll(list);
            /*for (SensingElement item : list){
                System.out.println(""+mainApp.getSeData()+"\n");
            }
        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during search ...");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }*/
    }

    private void showSEDetails(SensingElement sensingElement){
        if (sensingElement!= null){
            rSenseLabel.setText(Integer.toString(sensingElement.getrSense()));
            inGainLabel.setText(Integer.toString(sensingElement.getInGain()));
            outGainLabel.setText(Integer.toString(sensingElement.getOutGain()));
            contactsLabel.setText(sensingElement.getContacts());
            frequencyLabel.setText(Integer.toString(sensingElement.getFrequency()));
            harmonicLabel.setText(sensingElement.getHarmonic());
            dcBiasLabel.setText(Integer.toString(sensingElement.getDcBias()));
            modeVILabel.setText(sensingElement.getModeVI());
            measureTechniqueLabel.setText(sensingElement.getMeasureTecnique());
            measureTypeLabel.setText(sensingElement.getMeasureType());
            filterLabel.setText(Integer.toString(sensingElement.getFilter()));
            phaseShiftModeLabel.setText(sensingElement.getPhaseShiftMode());
            phaseShiftLabel.setText(Integer.toString(sensingElement.getPhaseShift()));
            IQLabel.setText(sensingElement.getIq());
            conversionRateLabel.setText(Integer.toString(sensingElement.getConversionRate()));
            inPortADCLabel.setText(sensingElement.getInPortADC());
            nDataLabel.setText(Integer.toString(sensingElement.getnData()));
            measureUnitLabel.setText(sensingElement.getMeasureUnit());
            idSELabel.setText(sensingElement.getIdSensingElement());
        }
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new Sensing Element.
     */
    @FXML
    private void handleNewSensingElement() {
        SensingElement tempColleghi = new SensingElement();
        boolean okClicked = mainApp.showSEEditDialog(tempColleghi, true);

        if (okClicked) {
            try {
                SensingElementDAOMySQLImpl.getInstance().insert(tempColleghi);
                mainApp.getSeData().add(tempColleghi);
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
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected Sensing Element.
     */
    /*@FXML
    private void handleEditColleghi() {
        SensingElement selectedSensingElement = seTableView.getSelectionModel().getSelectedItem();
        if (selectedSensingElement != null) {
            boolean okClicked = mainApp.showSEEditDialog(selectedSensingElement,true);
            if (okClicked) {
                try {
                    SensingElementDAOMySQLImpl.getInstance().update(selectedSensingElement);
                    showSensingElementDetails(selectedSensingElement);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Colleghi Selected");
            alert.setContentText("Please select a Colleghi in the table.");

            alert.showAndWait();
        }
    }*/
}
