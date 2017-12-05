package it.unicas.sensiplusConfigurationManager.view;


import it.unicas.sensiplusConfigurationManager.model.Family;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.image.Image;

import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Antonio on 02/12/2017.
 */
public class FamilyEditDialogController {

    @FXML
    private Label titleLabel;
    @FXML
    private ListView<String> familyListView;

    @FXML
    private TextField familyTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField hwVersionTextField;
    @FXML
    private TextField sysclockTextField;
    @FXML
    private TextField osctrimTextField;

    private ObservableList<String> familylistob;
    private Stage dialogStage;
    private Stage exStage;
    private Family family;
    private boolean okClicked = false;
    private boolean verifyLen = true;

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
     * Sets the family to be edited in the diaolg
     *
     * @param family
     */
    public void setFamily(Family family){
        this.family=family;

        familyTextField.setPromptText("0X00");
        familyTextField.setText(family.getId());
        nameTextField.setPromptText("MEASURING_INSTRUMENTS");
        nameTextField.setText(family.getName());
        hwVersionTextField.setPromptText("RUN0");
        hwVersionTextField.setText(family.getHwVersion());
        sysclockTextField.setPromptText("10000000");
        sysclockTextField.setText(family.getSysclock());
        osctrimTextField.setPromptText("0x00");
        osctrimTextField.setText(family.getOsctrim());
        if(family.getId()!=null){
            titleLabel.setText("Edit "+ family.getId());
            familyTextField.setDisable(true);
        }
        else{
            titleLabel.setText("Insert a new Family");
        }

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk(){
        if(isInputValid(verifyLen)){
            family.setId(familyTextField.getText());
            family.setName(nameTextField.getText());
            family.setHwVersion(hwVersionTextField.getText());
            family.setSysclock(sysclockTextField.getText());
            family.setOsctrim(osctrimTextField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }



    @FXML
    private void handleCancel(){dialogStage.close();}

    private boolean isInputValid(boolean verifyLen) {
        String errorMessage = "";

        if (familyTextField.getText() == null || verifyLen && familyTextField.getText().length() == 0) {
            errorMessage += "No valid Family!\n";
        }

        if (nameTextField.getText() == null || verifyLen && nameTextField.getText().length() == 0) {
            errorMessage += "No valid Family name!\n";
        }
        if (hwVersionTextField.getText() == null || verifyLen && hwVersionTextField.getText().length() == 0) {
            errorMessage += "No valid Family hwVersion!\n";
        }
        if (sysclockTextField.getText() == null || verifyLen && sysclockTextField.getText().length() == 0) {
            errorMessage += "No valid Family sysclock!\n";
        }
        if (osctrimTextField.getText() == null || verifyLen && osctrimTextField.getText().length() == 0) {
            errorMessage += "No valid Family osctrim!\n";
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
