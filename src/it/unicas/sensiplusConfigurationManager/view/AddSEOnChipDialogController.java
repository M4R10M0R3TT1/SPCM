package it.unicas.sensiplusConfigurationManager.view;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class AddSEOnChipDialogController {
    private Stage dialogStage;
    private boolean verifyLen=true;
    private boolean okClicked=false;
    private MainApp mainApp;
    private Chip chip=new Chip(null,null);

    @FXML
    private TextField seTextField;
    @FXML
    private TextField mTextField;
    @FXML
    private TextField nTextField;
    @FXML
    private ComboBox<String> calibrationComboBox;
    @FXML
    private Label idSEOnFamilyLabel;
    @FXML
    private Label idChipLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Button addButton;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        calibrationComboBox.setItems(mainApp.getAddCalibrationOnChip());
    }

    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public boolean isOkClicked(){
        return okClicked;
    }
    @FXML
    private void initialize() {
        mTextField.setText("1");
        nTextField.setText("0");
    }

    public void showAddSEOnChip(Family f,String id, String idChip) {
        Family family=null;
        try {
            family=FamilyDAOMySQLImpl.getInstance().selectSEOnPort(f,id);
            idSEOnFamilyLabel.setText(Integer.toString(family.getIdSPPort()));
            seTextField.setText(family.getOccupiedBy());
            idChipLabel.setText(idChip);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        try {
            List<String> list = ChipDAOMySQLImpl.getInstance().selectAddCalibrationOnChip(null,null);
            mainApp.getAddCalibrationOnChip().clear();
            mainApp.getAddCalibrationOnChip().addAll(list);
        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during select... ");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void showAddCalibration(Family port,String id, Chip chip) {
        Family family=null;
        try {
            family=FamilyDAOMySQLImpl.getInstance().selectSEOnPort(port,id);
            idSEOnFamilyLabel.setText(Integer.toString(family.getIdSPPort()));
            seTextField.setText(family.getOccupiedBy());
            idChipLabel.setText(chip.getIdSPChip());
            titleLabel.setText("Add calibration");
        } catch (DAOException e) {
            e.printStackTrace();
        }
        try {
            List<String> list = ChipDAOMySQLImpl.getInstance().selectAddCalibrationOnChip(chip,port);
            mainApp.getAddCalibrationOnChip().clear();
            mainApp.getAddCalibrationOnChip().addAll(list);
        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction ");
            alert.setHeaderText("Error during select... ");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    public void showEditCalibration(Family port,String id, Chip chip,Chip calibration) {
        Family family=null;
        try {
            family=FamilyDAOMySQLImpl.getInstance().selectSEOnPort(port,id);
            idSEOnFamilyLabel.setText(Integer.toString(family.getIdSPPort()));
            seTextField.setText(family.getOccupiedBy());
            idChipLabel.setText(chip.getIdSPChip());
            titleLabel.setText("Edit calibration");
            addButton.setText("Edit");
            calibrationComboBox.setValue(calibration.getNameCalibration());
            calibrationComboBox.setDisable(true);
            mTextField.setText(Integer.toString(calibration.getM()));
            nTextField.setText(Integer.toString(calibration.getN()));

        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    private boolean isInputValid(boolean verifyLen) {
        String errorMessage = "";

        if (mTextField.getText() == null || (verifyLen && mTextField.getText().length() == 0)) {
            errorMessage += "No valid parameters!\n";
        }
        if (nTextField.getText() == null || (verifyLen && nTextField.getText().length() == 0)) {
            errorMessage += "No valid parameters!\n";
        }
        if (calibrationComboBox.getValue()==null){
            errorMessage += "No valid parameters!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields ");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    @FXML
    private void handleOk() {
        if (isInputValid(verifyLen)) {
            chip.setM(Integer.parseInt(mTextField.getText()));
            chip.setN(Integer.parseInt(nTextField.getText()));
            chip.setNameCalibration(calibrationComboBox.getValue());
            chip.setIdSPChip(idChipLabel.getText());
            if(addButton.getText()!="Edit") {
                try {
                    ChipDAOMySQLImpl.getInstance().insertSEOnChip(chip, Integer.parseInt(idSEOnFamilyLabel.getText()));

                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction ");
                    alert.setHeaderText("Error during insert  ...");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }else{
                try {
                    ChipDAOMySQLImpl.getInstance().editCalibrationOnChip(chip, idChipLabel.getText(),Integer.parseInt(idSEOnFamilyLabel.getText()));
                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction  ");
                    alert.setHeaderText("Error during insert  ...");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
            }
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
