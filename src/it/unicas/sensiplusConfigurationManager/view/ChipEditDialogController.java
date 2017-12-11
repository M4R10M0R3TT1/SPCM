package it.unicas.sensiplusConfigurationManager.view;

import com.sun.xml.internal.ws.client.SenderException;
import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class ChipEditDialogController {
    @FXML
    private Label titleLabel;

    @FXML
    private TextField idChipTextField;
    @FXML
    private ComboBox familyComboBox;

    private Stage dialogStage;
    private Chip chip;
    private boolean okClicked = false;
    private boolean verifyLen = true;

    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    @FXML
    private void initialize(){

        //familyComboBox.getItems().addAll();
        familyComboBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue)->newValue.toString());


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
     * Sets the chip to be edited in the diaolg
     *
     * @param chip
     */
    public void setChip(Chip chip) {
        this.chip = chip;

        List<String> list = null;
        try {
            list = ChipDAOMySQLImpl.getInstance().selectFam();
            familyComboBox.getItems().addAll(list);
            familyComboBox.setValue(list.get(0));

        } catch (DAOException e) {
            e.printStackTrace();
        }

        if (chip.getIdSPChip() != null) {
            titleLabel.setText("Select a new Family");
            idChipTextField.setText(chip.getIdSPChip());
            idChipTextField.setDisable(true);
        }else {
            idChipTextField.setPromptText("0X0123456789");
            titleLabel.setText("Insert a new Chip");
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
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            chip.setIdSPChip(idChipTextField.getText());
            chip.setId(familyComboBox.getValue().toString());

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
        if (errorMessage.length() == 0) {
            return true;
        }
        else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields ");
            alert.setHeaderText("Please correct invalid fields ");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
