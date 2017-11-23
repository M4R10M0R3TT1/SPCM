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
        this.verifyLen = verifyLen;

        sensingElementNameTextField.setText(sensingElement.getIdSensingElement());

        if(sensingElement.getIdSensingElement()!=null)
            seNameLabel.setText(sensingElement.getIdSensingElement());
        else
            seNameLabel.setText("Insert a new SensingElement");

        /*rSenseTextField.setText(sensingElement.getRSense());
        inGainTextField.setText(sensingElement.getInGain());
        ..................
        ..................
         */
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
            /*...........................
            .............................
            colleghi.setCognome(cognomeField.getText());
            colleghi.setTelefono(telefonoField.getText());
            colleghi.setEmail(emailField.getText());
            if (compleannoField.getText() != null){
                colleghi.setCompleanno(DateUtil.parse(compleannoField.getText()));
            }*/
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
        /*.............................
        ...............................
        if (cognomeField.getText() == null || (verifyLen && cognomeField.getText().length() == 0)) {
            errorMessage += "No valid last name!\n";
        }
        if (telefonoField.getText() == null || (verifyLen && telefonoField.getText().length() == 0)) {
            errorMessage += "No valid street!\n";
        }

        if (emailField.getText() == null || (verifyLen && emailField.getText().length() == 0)) {
            errorMessage += "No valid postal code!\n";
        }
        if (compleannoField.getText() == null && verifyLen){
            errorMessage += "No valid birthday!\n";
        }

        if (compleannoField.getText() != null){
            if (compleannoField.getText().length() == 0){
                errorMessage += "No valid birthday!\n";
            }
            if (!DateUtil.validDate(compleannoField.getText())) {
                errorMessage += "No valid birthday. Use the format dd-mm-yyyy!\n";
            }
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


    // Reference to the main application
    //private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    /*public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }*/

}
