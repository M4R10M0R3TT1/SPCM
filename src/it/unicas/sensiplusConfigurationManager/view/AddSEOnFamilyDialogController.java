package it.unicas.sensiplusConfigurationManager.view;


import com.sun.org.apache.xpath.internal.operations.Bool;
import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

/**
 * Created by Antonio on 29/11/2017.
 */
public class AddSEOnFamilyDialogController {

    private Stage dialogStage;
    private boolean verifyLen=true;
    private SensingElement sensingElement;
    private boolean okClicked=false;
    private MainApp mainApp;


    @FXML
    private TableView<Family> addFamilyTableView;
    @FXML
    private TableColumn<Family,String> familyIDColumn;
    @FXML
    private TableColumn<Family,String> idAutoColumn;
    @FXML
    private TableColumn<Family,String> familyNameColumn;
    @FXML
    private Label idSensingElementLabel;
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,String> idPortTableColumn;
    @FXML
    private TableColumn<Family,String> portNameTableColumn;
    @FXML
    private TableColumn<Family,String> portInternalTableColumn;
    @FXML
    private Button addButton;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        addFamilyTableView.setItems(mainApp.getAddSeFamData());
        portTableView.setItems(mainApp.getAddSeFamPortData());
    }

    @FXML
    private void initialize(){
        familyIDColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
        idAutoColumn.setCellValueFactory(cellData->cellData.getValue().idSPFamilyProperty().asString());
        familyNameColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());


        portNameTableColumn.setCellValueFactory(cellData->cellData.getValue().portNameProperty());
        idPortTableColumn.setCellValueFactory(cellData->cellData.getValue().idSPPortProperty().asString());
        portInternalTableColumn.setCellValueFactory(cellData->cellData.getValue().internalProperty().asString());


       // showPort(null);
        activationAddButton(null);
        addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPort(newValue));
        portTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> activationAddButton(newValue)));
    }

    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public void showPort(Family family) {
        addButton.setDisable(true);
        if (family != null)
            try {
                List<Family> list = FamilyDAOMySQLImpl.getInstance().availablePort(family,sensingElement.getIdSensingElement().toString());
                mainApp.getAddSeFamPortData().clear();
                mainApp.getAddSeFamPortData().addAll(list);

            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction ");
                alert.setHeaderText("Error during insert ... ");
                alert.setContentText(e.getMessage());

                alert.showAndWait();

            }
        }

        public void activationAddButton(Family family){
            if(family!=null){
                if(family.getIdSPPort()>=0){
                    addButton.setDisable(false);
                }
            }
        }



   public void showSEOnFamily(List<Family> list,SensingElement sensingElement){
       this.sensingElement=sensingElement;
        if(sensingElement!=null){
           // String selection = sensingElement.getIdSensingElement().toString();
            //try{
                mainApp.getAddSeFamPortData().clear();
                //List<Family> list= FamilyDAOMySQLImpl.getInstance().selectAddSEOnFamily(selection);
                mainApp.getAddSeFamData().clear();
                mainApp.getAddSeFamData().addAll(list);
                idSensingElementLabel.setText(sensingElement.getIdSensingElement());

           /* } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }*/
        }
    }

   /* public void setAddFamily(List<Family> list,SensingElement sensingElement){
        this.sensingElement=sensingElement;
        showSEOnFamily(list,sensingElement);
    }*/

    @FXML
    private void handleAdd() {
        Family selPort = portTableView.getSelectionModel().getSelectedItem();
        Family selFamily =addFamilyTableView.getSelectionModel().getSelectedItem();
        boolean mt=false;
        if(selFamily!=null) {
            try {
                mt = FamilyDAOMySQLImpl.getInstance().measureControl(idSensingElementLabel.getText(), selFamily.getIdSPFamily());
            } catch (DAOException e) {
                e.printStackTrace();
            }
            if (selPort != null && mt == true) {
                try {
                    FamilyDAOMySQLImpl.getInstance().insertFamilyonSE(selFamily.getIdSPFamily(), selPort.getIdSPPort(), idSensingElementLabel.getText());
                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction");
                    alert.setHeaderText("Error during insert ...");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                okClicked = true;
                dialogStage.close();
            } else if (selPort != null) {
                //--------ADD CONFIRMATION DIALOG--------
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Are you sure?");
                //---To add an icon to the alert
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("file:resources/images/favicon.png"));
                //---
                alert.setHeaderText("WARNING:\n" +
                        "Read carefully before continue!");
                alert.setContentText("You are about to automatically add a new measure technique into the selected family, are you sure to continue?");

                ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

                Optional<ButtonType> result = alert.showAndWait();
                //---------------------------------------------
                if (result.get() == buttonTypeOne) {
                    try {
                        int idMeasure = FamilyDAOMySQLImpl.getInstance().measureSearch(idSensingElementLabel.getText());
                        FamilyDAOMySQLImpl.getInstance().insertMeasure(idMeasure, selFamily.getIdSPFamily());
                        FamilyDAOMySQLImpl.getInstance().insertFamilyonSE(selFamily.getIdSPFamily(), selPort.getIdSPPort(), idSensingElementLabel.getText());
                    } catch (DAOException e) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.initOwner(mainApp.getPrimaryStage());
                        alert.setTitle("Error during DB interaction");
                        alert.setHeaderText("Error during insert ...");
                        alert.setContentText(e.getMessage());
                        alert.showAndWait();
                    }
                    okClicked = true;
                    dialogStage.close();
                }
            } else {
                // Nothing selected.
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("No Selection");
                alert.setHeaderText("No Port Selected");
                alert.setContentText("Please select a Port in the table.");

                alert.showAndWait();
            }
        }else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Family Selected");
            alert.setContentText("Please select a Family in the table.");

            alert.showAndWait();
        }


    }



    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    public boolean isOkClicked(){
        return okClicked;
    }

}
