package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.model.Chip;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ChipEditDialogController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField idChipTextField;
    @FXML
    private TextField familyTextField;

    private Stage dialogStage;
    private Chip chip;
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
     * Sets the chip to be edited in the diaolg
     *
     * @param chip
     */
    public void setChip(Chip chip) {
        this.chip = chip;

        idChipTextField.setPromptText("0X0123456789");
        //idChipTextField.setText(chip.getIdSPChip());
        familyTextField.setPromptText("0X00");
        //familyTextField.setText(chip.getId());
        /*if (chip.getIdSPChip() != null) {
            titleLabel.setText("Edit " + chip.getIdSPChip());
            idChipTextField.setDisable(true);
        } else {*/
            titleLabel.setText("Insert a new Chip");
        //}

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
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            chip.setIdSPChip(idChipTextField.getText());
            chip.setId(familyTextField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid(boolean verifyLen) {
        String errorMessage = "";

        if (idChipTextField.getText() == null || verifyLen && idChipTextField.getText().length() == 0) {
            errorMessage += "No valid Chip!\n";
        }
        /*if (familyTextField.getText() == null || verifyLen && familyTextField.getText().length() == 0) {
            errorMessage += "No valid Family ID!\n";
        }*/
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
