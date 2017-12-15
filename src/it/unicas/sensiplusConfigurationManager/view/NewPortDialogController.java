package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Antonio on 14/12/2017.
 */
public class NewPortDialogController {

    @FXML
    private CheckBox internalCheckBox;
    @FXML
    private TextField portTextField;
    @FXML
    private Button createButton;
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,String> idPortColumn;
    @FXML
    private TableColumn<Family,String> namePortColumn;
    @FXML
    private TableColumn<Family,String> internalColumn;

    private Stage dialogStage;
    private Boolean okClicked=false;
    private MainApp mainApp;
    private Family port;
    private ObservableList<Family> portList = FXCollections.observableArrayList();


    public ObservableList<Family> getPortList(){return  portList;}

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        portTableView.setItems(getPortList());
    }

    @FXML
    private void initialize(){
        idPortColumn.setCellValueFactory(cellData-> cellData.getValue().idSPPortProperty().asString());
        namePortColumn.setCellValueFactory(cellData-> cellData.getValue().portNameProperty());
        internalColumn.setCellValueFactory(cellData->cellData.getValue().internalProperty().asString());
    }



    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public boolean isOkClicked(){return okClicked;}

    public void setPort(Family fam){
        this.port=fam;
        try{
            List<Family> list = FamilyDAOMySQLImpl.getInstance().selectAllPort(fam);
            getPortList().clear();
            getPortList().addAll(list);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleOk(){
        port.setPortName(portTextField.getText());
        port.setInternal(internalCheckBox.isSelected());
        okClicked=true;
        dialogStage.close();
    }

    @FXML
    private void activationButton(){
        if(portTextField.getLength()!=0){
            createButton.setDisable(false);
        }else
            createButton.setDisable(true);
    }

    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
}


