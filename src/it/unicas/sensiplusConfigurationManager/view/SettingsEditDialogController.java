package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SettingsEditDialogController {

    @FXML
    private TextField driverNameField;
    @FXML
    private TextField hostField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField schemaField;


    private Stage dialogStage;
    private DAOMySQLSettings settings;
    private boolean okClicked = false;
    private boolean verifyLen;

    @FXML
    private void initialize() {
        driverNameField.setText(DAOMySQLSettings.DRIVERNAME);
        hostField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        schemaField.setText("");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/settings.png"));
    }

    public void setSettings(DAOMySQLSettings settings) {
        this.settings = settings;
        hostField.setText(settings.getHost());
        usernameField.setText(settings.getUserName());
        passwordField.setText(settings.getPwd());
        schemaField.setText(settings.getSchema());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            settings.setHost(hostField.getText());
            settings.setUserName(usernameField.getText());
            settings.setPwd(passwordField.getText());
            settings.setSchema(schemaField.getText());

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

        if (hostField.getText() == null || hostField.getText().length() == 0) {
            errorMessage += "No valid host!\n";
        }
        if (usernameField.getText() == null || usernameField.getText().length() == 0) {
            errorMessage += "No valid username!\n";
        }
        /*if (passwordField.getText() == null || passwordField.getText().length() == 0) {
            errorMessage += "No valid password!\n";
        }*/
        if (schemaField.getText() == null || schemaField.getText().length() ==0 ){
            errorMessage += "No valid schema!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}





