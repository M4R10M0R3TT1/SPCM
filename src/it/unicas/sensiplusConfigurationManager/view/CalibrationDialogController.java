package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;


public class CalibrationDialogController {

    @FXML
    private TextField calibrationTextField;
    @FXML
    private TableView<Chip> calibrationTableView;
    @FXML
    private TableColumn<Chip,String> idColumn;
    @FXML
    private TableColumn<Chip,String> calibrationColumn;
    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    private Stage dialogStage;
    private Boolean okClicked=false;

    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        calibrationTableView.setItems(mainApp.getCalibration());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData->cellData.getValue().idSPChipProperty());
        calibrationColumn.setCellValueFactory((cellDeta->cellDeta.getValue().id()));
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void showCalibration(){
        try {
            List<Chip> list = ChipDAOMySQLImpl.getInstance().selectCalibration();
            mainApp.getCalibration().clear();
            mainApp.getCalibration().addAll(list);
            calibrationTableView.getSelectionModel().selectFirst();

        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during  showCalibration ...");
            alert.setContentText(e.getMessage());

            alert.showAndWait();

        }

    }

    @FXML
    private void handleTextFieldControl(){
        if (calibrationTextField.getLength()==0)
        {
            newButton.setDisable(true);
        }else{
            newButton.setDisable(false);
        }
    }
    @FXML
    private void handleNew(){

        if(newButton.getText()=="Confirm"){
            try {
                ChipDAOMySQLImpl.getInstance().updateCalibration(calibrationTextField.getText(),Integer.parseInt(calibrationTableView.getSelectionModel().getSelectedItem().getIdSPChip()));
            } catch (DAOException e) {
                e.printStackTrace();
            }
            calibrationTextField.setText(null);
            calibrationTextField.setDisable(true);
            newButton.setText("New");
            editButton.setText("Edit");
            deleteButton.setDisable(false);
            calibrationTableView.setDisable(false);
            showCalibration();
        }else if(newButton.getText()=="Add"){
            try {
                ChipDAOMySQLImpl.getInstance().insertCalibration(calibrationTextField.getText());
            } catch (DAOException e) {
                e.printStackTrace();
            }
            calibrationTextField.setText(null);
            calibrationTextField.setDisable(true);
            newButton.setText("New");
            editButton.setText("Edit");
            deleteButton.setDisable(false);
            calibrationTableView.setDisable(false);
            newButton.setDisable(true);
            showCalibration();
        }else{
            calibrationTextField.setDisable(false);
            newButton.setText("Add");
            editButton.setText("Cancel");
            deleteButton.setDisable(true);
            calibrationTableView.setDisable(true);
            newButton.setDisable(true);
        }
    }

    @FXML
    private void handleEdit() {
        if (editButton.getText() == "Cancel") {
            calibrationTextField.setText(null);
            calibrationTextField.setDisable(true);
            newButton.setText("New");
            editButton.setText("Edit");
            deleteButton.setDisable(false);
            calibrationTableView.setDisable(false);
            newButton.setDisable(false);
        } else {
            calibrationTextField.setText(calibrationTableView.getSelectionModel().getSelectedItem().getId());
            calibrationTextField.setDisable(false);
            newButton.setText("Confirm");
            editButton.setText("Cancel");
            deleteButton.setDisable(true);
            calibrationTableView.setDisable(true);
        }

    }

}
