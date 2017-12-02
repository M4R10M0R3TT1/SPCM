package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.model.Family;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Antonio on 02/12/2017.
 */
public class FamilyEditDialogController {

    @FXML
    private Label titleLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label hwVersionLabel;
    @FXML
    private Label sysclockLabel;
    @FXML
    private Label osctrim;

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


    private Stage dialogStage;
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

        familyTextField.setText(family.getId());
        nameTextField.setText(family.getName());
        hwVersionTextField.setText(family.getHwVersion());
        sysclockTextField.setText(family.getSysclock());
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




}
