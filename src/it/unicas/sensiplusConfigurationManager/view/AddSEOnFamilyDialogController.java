package it.unicas.sensiplusConfigurationManager.view;


import com.sun.org.apache.xpath.internal.operations.Bool;
import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

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

        showSEOnFamily(null);
        addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPort(newValue));
    }

    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public void showPort(Family family) {
        if (sensingElement != null)
            try {
                List<Family> list = FamilyDAOMySQLImpl.getInstance().availablePort(family);
                mainApp.getAddSeFamPortData().clear();
                mainApp.getAddSeFamPortData().addAll(list);

            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();

            }
        }




   public void showSEOnFamily(SensingElement sensingElement){
        if(sensingElement!=null){
            String selection = sensingElement.getIdSensingElement().toString();
            System.out.println("Selection: "+selection);
            try{
                mainApp.getAddSeFamPortData().clear();
                List<Family> list= FamilyDAOMySQLImpl.getInstance().selectAddSEOnFamily(selection);
                mainApp.getAddSeFamData().clear();
                mainApp.getAddSeFamData().addAll(list);
                idSensingElementLabel.setText(sensingElement.getIdSensingElement());

            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleAdd() {
        Family selPort = portTableView.getSelectionModel().getSelectedItem();
        Family selFamily =addFamilyTableView.getSelectionModel().getSelectedItem();
        if (selPort != null) {
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
            okClicked=true;
            dialogStage.close();
        }


    }

  public void setAddFamily(SensingElement sensingElement){
         this.sensingElement=sensingElement;
         showSEOnFamily(sensingElement);
         //addFamilyTableView.setItems(mainApp.getAddSeFamData());
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

    //PROVA PER DEBUG
    /*public void addfamilyButton(SensingElement sensingElement){
        System.out.println("Questo è il contenuto di FamID: "+sensingElement);
    }*/

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    /*private boolean isInputValid(boolean verifyLen) {
        String errorMessage = "";

        if (idAutoColumn.getText() == null || (verifyLen && idAutoColumn.getText().length() == 0)) {
            errorMessage += "No valid Sensing Element Name!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }*/
}
