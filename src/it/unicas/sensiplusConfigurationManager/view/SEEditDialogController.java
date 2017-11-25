package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SEEditDialogController {

    @FXML
    private TextField sensingElementNameTextField;
    @FXML
    private TextField rSenseTextField;
    @FXML
    private TextField inGainTextField;
    @FXML
    private TextField outGainTextField;
    @FXML
    private TextField contactsTextField;
    @FXML
    private TextField frequencyTextField;
    @FXML
    private TextField harmonicTextField;
    @FXML
    private TextField dcBiasTextField;
    @FXML
    private TextField modeVITextField;
    @FXML
    private TextField measureTechniqueTextField;
    @FXML
    private TextField measureTypeTextField;
    @FXML
    private TextField filterTextField;
    @FXML
    private TextField phaseShiftModeTextField;
    @FXML
    private TextField phaseShiftTextField;
    @FXML
    private TextField IQTextField;
    @FXML
    private TextField conversionTextField;
    @FXML
    private TextField inPortADCTextField;
    @FXML
    private TextField nDataTextField;
    @FXML
    private TextField measureUnitTextField;
    @FXML
    private Label seNameLabel;

    private Stage dialogStage;
    private SensingElement sensingElement;
    private boolean okClicked = false;
    private boolean verifyLen = true;

    @FXML
    private void initialize() {
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

        if(sensingElement.getIdSensingElement()!=null)
            seNameLabel.setText(sensingElement.getIdSensingElement());
        else
            seNameLabel.setText("Insert a new SensingElement");

        rSenseTextField.setText(Integer.toString(sensingElement.getrSense()));
        inGainTextField.setText(Integer.toString(sensingElement.getInGain()));
        outGainTextField.setText(Integer.toString(sensingElement.getOutGain()));
        contactsTextField.setText(sensingElement.getContacts());
        frequencyTextField.setText(Integer.toString(sensingElement.getFrequency()));
        harmonicTextField.setText(sensingElement.getHarmonic());
        dcBiasTextField.setText(Integer.toString(sensingElement.getDcBias()));
        modeVITextField.setText(sensingElement.getModeVI());
        measureTechniqueTextField.setText(sensingElement.getmeasureTechnique());
        measureTypeTextField.setText(sensingElement.getMeasureType());
        filterTextField.setText(Integer.toString(sensingElement.getFilter()));
        phaseShiftModeTextField.setText(sensingElement.getPhaseShiftMode());
        phaseShiftTextField.setText(Integer.toString(sensingElement.getPhaseShift()));
        IQTextField.setText(sensingElement.getIq());
        conversionTextField.setText(Integer.toString(sensingElement.getConversionRate()));
        inPortADCTextField.setText(sensingElement.getInPortADC());
        nDataTextField.setText(Integer.toString(sensingElement.getnData()));
        measureUnitTextField.setText(sensingElement.getMeasureUnit());
    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            sensingElement.setIdSensingElement(sensingElementNameTextField.getText());
            sensingElement.setrSense(Integer.parseInt(rSenseTextField.getText()));
            sensingElement.setInGain(Integer.parseInt(inGainTextField.getText()));
            sensingElement.setOutGain(Integer.parseInt(outGainTextField.getText()));
            sensingElement.setContacts(contactsTextField.getText());
            sensingElement.setFrequency(Integer.parseInt(frequencyTextField.getText()));
            sensingElement.setHarmonic(harmonicTextField.getText());
            sensingElement.setDcBias(Integer.parseInt(dcBiasTextField.getText()));
            sensingElement.setModeVI(modeVITextField.getText());
            sensingElement.setmeasureTechnique(measureTechniqueTextField.getText());
            sensingElement.setMeasureType(measureTypeTextField.getText());
            sensingElement.setFilter(Integer.parseInt(frequencyTextField.getText()));
            sensingElement.setPhaseShiftMode(phaseShiftModeTextField.getText());
            sensingElement.setPhaseShift(Integer.parseInt(phaseShiftTextField.getText()));
            sensingElement.setIq(IQTextField.getText());
            sensingElement.setConversionRate(Integer.parseInt(conversionTextField.getText()));
            sensingElement.setInPortADC(inPortADCTextField.getText());
            sensingElement.setnData(Integer.parseInt(nDataTextField.getText()));
            sensingElement.setMeasureUnit(measureUnitTextField.getText());

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
        /*if (rSenseTextField.getText() == null || (verifyLen && rSenseTextField.getText().length() == 0)) {
            errorMessage += "No valid last name!\n";
        }
        if (inGainTextField.getText() == null || (verifyLen && inGainTextField.getText().length() == 0)) {
            errorMessage += "No valid street!\n";
        }
        if (outGainTextField.getText() == null || (verifyLen && outGainTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (contactsTextField.getText() == null || (verifyLen && contactsTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (frequencyTextField.getText() == null || (verifyLen && frequencyTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (harmonicTextField.getText() == null || (verifyLen && harmonicTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (dcBiasTextField.getText() == null || (verifyLen && dcBiasTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (modeVITextField.getText() == null || (verifyLen && modeVITextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (measureTechniqueTextField.getText() == null || (verifyLen && measureTechniqueTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (measureTypeTextField.getText() == null || (verifyLen && measureTypeTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (filterTextField.getText() == null || (verifyLen && filterTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (phaseShiftModeTextField.getText() == null || (verifyLen && phaseShiftModeTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (phaseShiftTextField.getText() == null || (verifyLen && phaseShiftTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (IQTextField.getText() == null || (verifyLen && IQTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (conversionTextField.getText() == null || (verifyLen && conversionTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (inPortADCTextField.getText() == null || (verifyLen && inPortADCTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (nDataTextField.getText() == null || (verifyLen && nDataTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (measureUnitTextField.getText() == null || (verifyLen && measureUnitTextField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }*/
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
