package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SEEditDialogController {

    @FXML
    private TextField sensingElementNameTextField;
    @FXML
    private TextField frequencyTextField;
    @FXML
    private TextField dcBiasTextField;
    @FXML
    private TextField filterTextField;
    @FXML
    private TextField phaseShiftTextField;
    @FXML
    private TextField conversionTextField;
    @FXML
    private TextField nDataTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField rangeMinTextField;
    @FXML
    private TextField rangeMaxTextField;
    @FXML
    private TextField defaultAlarmThresholdTextField;
    @FXML
    private TextField multiplerTextField;
    @FXML
    private ComboBox rSenseComboBox;
    @FXML
    private ComboBox inGainComboBox;
    @FXML
    private ComboBox outGainComboBox;
    @FXML
    private ComboBox contactsComboBox;
    @FXML
    private ComboBox harmonicComboBox;
    @FXML
    private ComboBox modeVIComboBox;
    @FXML
    private ComboBox measureTechniqueComboBox;
    @FXML
    private ComboBox measureTypeComboBox;
    @FXML
    private ComboBox phaseShiftModeComboBox;
    @FXML
    private ComboBox IQComboBox;
    @FXML
    private ComboBox inPortADCComboBox;
    @FXML
    private ComboBox measureUnitComboBox;


    @FXML
    private Label seNameLabel;

    private Stage dialogStage;
    private SensingElement sensingElement;
    private boolean okClicked = false;
    private boolean verifyLen = true;

    @FXML
    private void initialize() {
        //rSense ComboBox
        rSenseComboBox.getItems().addAll(50,500,5000,50000);
        rSenseComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //inGain ComboBox
        inGainComboBox.getItems().addAll(1,12,20,40);
        inGainComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //outGain ComboBox
        outGainComboBox.getItems().addAll(0,1,2,3,4,5,6,7);
        outGainComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //contacts ComboBox
        contactsComboBox.getItems().addAll("TWO","FOUR");
        contactsComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //harmonic ComboBox
        harmonicComboBox.getItems().addAll("FIRST_HARMONIC","SECOND_HARMONIC","TIRD_HARMONIC");
        harmonicComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //modeVI ComboBox
        modeVIComboBox.getItems().addAll("VOUT_IIN","VIN_IIN","VOUT_VIN","VOUT_VOUT");
        modeVIComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //measureTechnique ComboBox
        measureTechniqueComboBox.getItems().addAll("DIRECT", "EIS","POT","ENERGY_SPECTROSCOPY","ULTRASOUND");
        measureTechniqueComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //measureType ComboBox
        measureTypeComboBox.getItems().addAll("IN-PHASE","QUADRATURE","MODULE","PHASE","RESISTANCE","CAPACITANCE","INDUCTANCE");
        measureTypeComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //phaseShiftMode ComboBox
        phaseShiftModeComboBox.getItems().addAll("QUADRANT","COARSE","FINE");
        phaseShiftModeComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //IQ ComboBox
        IQComboBox.getItems().addAll("IN_PHASE","QUADRATURE");
        IQComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //inPortADC ComboBox
        inPortADCComboBox.getItems().addAll("IA","VA");
        inPortADCComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

        //measureUnit ComboBox
        measureUnitComboBox.getItems().addAll("O","F","H","C","%","V","A","L","t");
        measureUnitComboBox.valueProperty().addListener((ObservableValue observable,Object oldValue,Object newValue)->newValue.toString());

    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage, boolean verifyLen) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }


    /**
     * Sets the sensingElement to be edited in the dialog.
     *
     * @param sensingElement
     */
    public void setSensingElement(SensingElement sensingElement) {
        this.sensingElement = sensingElement;

        sensingElementNameTextField.setText(sensingElement.getIdSensingElement());

        rSenseComboBox.setValue(sensingElement.getrSense());
        inGainComboBox.setValue(sensingElement.getInGain());
        outGainComboBox.setValue(sensingElement.getOutGain());
        contactsComboBox.setValue(sensingElement.getContacts());
        frequencyTextField.setText(Integer.toString(sensingElement.getFrequency()));
        harmonicComboBox.setValue(sensingElement.getHarmonic());
        dcBiasTextField.setText(Integer.toString(sensingElement.getDcBias()));
        modeVIComboBox.setValue(sensingElement.getModeVI());
        measureTechniqueComboBox.setValue(sensingElement.getmeasureTechnique());
        measureTypeComboBox.setValue(sensingElement.getMeasureType());
        filterTextField.setText(Integer.toString(sensingElement.getFilter()));
        phaseShiftModeComboBox.setValue(sensingElement.getPhaseShiftMode());
        phaseShiftTextField.setText(Integer.toString(sensingElement.getPhaseShift()));
        IQComboBox.setValue(sensingElement.getIq());
        conversionTextField.setText(Integer.toString(sensingElement.getConversionRate()));
        inPortADCComboBox.setValue(sensingElement.getInPortADC());
        nDataTextField.setText(Integer.toString(sensingElement.getnData()));
        nameTextField.setText(sensingElement.getName());
        rangeMinTextField.setText(Double.toString(sensingElement.getRangeMin()));
        rangeMaxTextField.setText(Double.toString(sensingElement.getRangeMax()));
        defaultAlarmThresholdTextField.setText((Double.toString(sensingElement.getDefaultAlarmThreshold())));
        multiplerTextField.setText(Integer.toString(sensingElement.getMultiplier()));
        measureUnitComboBox.setValue(sensingElement.getMeasureUnit());

        if(sensingElement.getIdSensingElement()!=null) {
            seNameLabel.setText("Edit");
            sensingElementNameTextField.setDisable(true);
            disableParamter();
        }
        else
            seNameLabel.setText("Insert a new SensingElement");

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    public void disableParamter() {

        if (measureTechniqueComboBox.getValue().toString()=="DIRECT")
        {
            rSenseComboBox.setDisable(true);
            inGainComboBox.setDisable(true);
            outGainComboBox.setDisable(true);
            contactsComboBox.setDisable(true);
            frequencyTextField.setDisable(true);
            harmonicComboBox.setDisable(true);
            dcBiasTextField.setDisable(true);
            modeVIComboBox.setDisable(true);
            measureTypeComboBox.setDisable(true);
            phaseShiftModeComboBox.setDisable(true);
            phaseShiftTextField.setDisable(true);
            IQComboBox.setDisable(true);
        }
        else {
            rSenseComboBox.setDisable(false);
            inGainComboBox.setDisable(false);
            outGainComboBox.setDisable(false);
            contactsComboBox.setDisable(false);
            frequencyTextField.setDisable(false);
            harmonicComboBox.setDisable(false);
            dcBiasTextField.setDisable(false);
            modeVIComboBox.setDisable(false);
            measureTypeComboBox.setDisable(false);
            phaseShiftModeComboBox.setDisable(false);
            phaseShiftTextField.setDisable(false);
            IQComboBox.setDisable(false);
        }

    }
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            sensingElement.setIdSensingElement(sensingElementNameTextField.getText());
            sensingElement.setrSense(Integer.parseInt(rSenseComboBox.getValue().toString()));
            sensingElement.setInGain(Integer.parseInt(inGainComboBox.getValue().toString()));
            sensingElement.setOutGain(Integer.parseInt(outGainComboBox.getValue().toString()));
            sensingElement.setContacts(contactsComboBox.getValue().toString());
            sensingElement.setFrequency(Integer.parseInt(frequencyTextField.getText()));
            sensingElement.setHarmonic(harmonicComboBox.getValue().toString());
            sensingElement.setDcBias(Integer.parseInt(dcBiasTextField.getText()));
            sensingElement.setModeVI(modeVIComboBox.getValue().toString());
            sensingElement.setmeasureTechnique(measureTechniqueComboBox.getValue().toString());
            sensingElement.setMeasureType(measureTypeComboBox.getValue().toString());
            sensingElement.setFilter(Integer.parseInt(frequencyTextField.getText()));
            sensingElement.setPhaseShiftMode(phaseShiftModeComboBox.getValue().toString());
            sensingElement.setPhaseShift(Integer.parseInt(phaseShiftTextField.getText()));
            sensingElement.setIq(IQComboBox.getValue().toString());
            sensingElement.setConversionRate(Integer.parseInt(conversionTextField.getText()));
            sensingElement.setInPortADC(inPortADCComboBox.getValue().toString());
            sensingElement.setnData(Integer.parseInt(nDataTextField.getText()));
            sensingElement.setName(nameTextField.getText());
            sensingElement.setRangeMin(Double.parseDouble(rangeMinTextField.getText()));
            sensingElement.setRangeMax(Double.parseDouble(rangeMaxTextField.getText()));
            sensingElement.setDefaultAlarmThreshold(Double.parseDouble(defaultAlarmThresholdTextField.getText()));
            sensingElement.setMultiplier(Integer.parseInt(multiplerTextField.getText()));
            sensingElement.setMeasureUnit(measureUnitComboBox.getValue().toString());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid(boolean verifyLen) {
        String errorMessage = "";

        if (sensingElementNameTextField.getText() == null || (verifyLen && sensingElementNameTextField.getText().length() == 0)) {
            errorMessage += "No valid Sensing Element Name!\n";
        }
        if (Double.parseDouble(frequencyTextField.getText()) < 0 || Double.parseDouble(frequencyTextField.getText()) > 5000000) {
            errorMessage += "No valid frequency! [0,5000000]\n";
        }
        if(Integer.parseInt(dcBiasTextField.getText()) < -2048 || Integer.parseInt(dcBiasTextField.getText()) > 2048){
            errorMessage += "No valid dcBias! [-2048,2048]\n";
        }
        if(Integer.parseInt(filterTextField.getText()) < 1 || Integer.parseInt(filterTextField.getText()) > 256){
            errorMessage += "No valid filter! [1,256]\n";
        }
        if(Integer.parseInt(phaseShiftTextField.getText()) < 0 || Integer.parseInt(phaseShiftTextField.getText()) > 360){
            errorMessage += "No valid phaseShift! [0,360]\n";
        }
        if(Integer.parseInt(conversionTextField.getText()) < 1 || Integer.parseInt(conversionTextField.getText()) > 100000){
            errorMessage += "No valid conversionRate! [1,100000]\n";
        }
        if(Integer.parseInt(nDataTextField.getText()) < 1 || Integer.parseInt(nDataTextField.getText()) > 16){
            errorMessage += "No valid nData! [1,16]\n";
        }
        if(Integer.parseInt(multiplerTextField.getText()) < -21 || Integer.parseInt(multiplerTextField.getText()) > 21){
            errorMessage += "No valid multipler! [-21,21]\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }




}