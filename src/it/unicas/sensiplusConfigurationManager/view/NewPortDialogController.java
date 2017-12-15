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
import java.util.Optional;

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
        selection(null);
        portTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> selection(newValue))
        );
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
        String text = createButton.getText();
        if(text.equals("Create")) {
            port.setPortName(portTextField.getText());
            port.setInternal(internalCheckBox.isSelected());

            try {
                FamilyDAOMySQLImpl.getInstance().insertPort(port);
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectAllPort(port);
                getPortList().clear();
                getPortList().addAll(list);
                portTextField.clear();
                if(internalCheckBox.isSelected())
                    internalCheckBox.setSelected(false);

            } catch (DAOException e) {
                e.printStackTrace();
            }
        }else{
            Family selected = portTableView.getSelectionModel().getSelectedItem();
            try {
                //--------DELETION CONFIRMATION DIALOG--------
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Are you sure?");
                //---To add an icon to the alert
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:resources/images/favicon.png"));
                //---
                alert.setHeaderText("WARNING:\n" +
                        "Read carefully before continue!");
                alert.setContentText("The selected Port could be associated to a Family, do you want to continue?");

                ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                //---------------------------------------------
                if (result.get() == buttonTypeOne) {
                    FamilyDAOMySQLImpl.getInstance().deletePort(selected);
                    setPort(port);

                     okClicked = true;
                }
            } catch(DAOException e1){
                e1.printStackTrace();
            }
        }

    }

    @FXML
    private void activationButton(){

            if (portTextField.getLength() != 0) {
                createButton.setDisable(false);
            } else
                createButton.setDisable(true);

    }

    @FXML
    private void showTextField(){
            createButton.setText("Create");
        if (portTextField.getLength() != 0) {
            createButton.setDisable(false);
        } else
            createButton.setDisable(true);

    }

    @FXML
    private void selection(Family p){
        if(p!=null) {
            createButton.setText("Delete");
            createButton.setDisable(false);
        }
    }



    @FXML
    private void handleCancel(){
        dialogStage.close();
    }
}


