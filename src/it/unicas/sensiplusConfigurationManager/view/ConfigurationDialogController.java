package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ClusterDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class ConfigurationDialogController {

    @FXML
    private TextField driverTextField;
    @FXML
    private TextField hostControllerTextField;
    @FXML
    private TextField apiOwnerTextField;
    @FXML
    private TextField mcuTextField;
    @FXML
    private TextField protocolTextField;
    @FXML
    private TextField addressTypeTextField;
    @FXML
    private Button addButton;


    private Stage dialogStage;
    private Boolean okClicked=false;
    private MainApp mainApp;
    private Cluster cluster;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }
        //for add new configuration
    public void setCluster(Cluster cluster){
        this.cluster=cluster;
    }
        //for edit configuration
    public void setObject(Cluster cluster){
            this.cluster=cluster;
            driverTextField.setText(cluster.getDriver());
            hostControllerTextField.setText(cluster.getHostController());
            apiOwnerTextField.setText(cluster.getApiOwner());
            mcuTextField.setText(cluster.getMcu());
            protocolTextField.setText(cluster.getProtocol());
            addressTypeTextField.setText(cluster.getAddressingType());
            addButton.setText("Confirm");
        }

    @FXML
    private void handleAdd(){
        cluster.setDriver(driverTextField.getText());
        cluster.setHostController(hostControllerTextField.getText());
        cluster.setApiOwner(apiOwnerTextField.getText());
        cluster.setMcu(mcuTextField.getText());
        cluster.setProtocol(protocolTextField.getText());
        cluster.setAddressingType(addressTypeTextField.getText());
        okClicked=true;
        dialogStage.close();
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }

    @FXML
    private void textFieldEmpty(){
        if (driverTextField.getLength()==0|hostControllerTextField.getLength()==0||apiOwnerTextField.getLength()==0
                ||mcuTextField.getLength()==0||protocolTextField.getLength()==0||addressTypeTextField.getLength()==0){
            addButton.setDisable(true);
        }else{
            addButton.setDisable(false);
        }
    }
    public boolean isOkClicked(){
        return okClicked;
    }
}
