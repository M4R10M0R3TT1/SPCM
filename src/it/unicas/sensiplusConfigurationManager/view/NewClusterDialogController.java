package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ClusterDAOMySQLImpl;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Antonio on 14/12/2017.
 */
public class NewClusterDialogController {

    @FXML
    private ComboBox calibrationComboBox;
    @FXML
    private TextField clusterTextField;
    @FXML
    private Button addButton;

    private Stage dialogStage;
    private Boolean okClicked=false;
    private MainApp mainApp;
    private Cluster cluster;

    @FXML
    private void initialize(){
        calibrationComboBox.getItems().addAll("No Calibration");
        calibrationComboBox.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue)->newValue.toString());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public boolean isOkClicked(){return okClicked;}

    public void setCluster(Cluster cluster){
        this.cluster = cluster;
    }

    @FXML
    private void handleOk(){
        cluster.setIdCluster(clusterTextField.getText());
        cluster.setNameCalibration(calibrationComboBox.getValue().toString());
        okClicked=true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){dialogStage.close();}

    public void showCalibration(){
        try {
            List<String> list = ClusterDAOMySQLImpl.getInstance().selectCalInDialog();

            calibrationComboBox.getItems().addAll(list);
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
    private void activationButton(){
        if(clusterTextField.getLength()==0||calibrationComboBox.getValue()==null){
            addButton.setDisable(true);
        }
        else{
            addButton.setDisable(false);
        }
    }




}
