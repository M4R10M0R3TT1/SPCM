package it.unicas.sensiplusConfigurationManager.view;


import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private TableView<SensingElement> addFamilyTableView;
    @FXML
    private TableColumn<SensingElement,String> familyIDColumn;
    @FXML
    private TableColumn<SensingElement,String> idAutoColumn;
    @FXML
    private TableColumn<SensingElement,String> familyNameColumn;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        //showSEOnFamily(sensingElement);
        //addFamilyTableView.setItems(mainApp.getAddSeFamData());

    }

    @FXML
    private void initialize(){
        familyIDColumn.setCellValueFactory(cellData->cellData.getValue().family_idProperty());
        idAutoColumn.setCellValueFactory(cellData->cellData.getValue().idProperty().asString());
        familyNameColumn.setCellValueFactory(cellData->cellData.getValue().family_NameProperty());
        /*showSEOnFamily(null);
        addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSEOnFamily(newValue));*/

        //PROVA PER DEBUG
        /*addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> addfamilyButton(newValue));*/
    }



    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

   public void showSEOnFamily(SensingElement sensingElement){
        if(sensingElement!=null){
            try{
                List<SensingElement> list= SensingElementDAOMySQLImpl.getInstance().selectAddSEOnFamily(sensingElement);
                mainApp.getAddSeFamData().clear();
                mainApp.getAddSeFamData().addAll(list);
                //System.out.println("ADDSEFAMDATA: "+mainApp.getAddSeFamData().toString());

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
        /*if (sensingElement != null) {
            SensingElement tempSeOnFam = new SensingElement();

            if (okClicked) {
                try {
                    SensingElementDAOMySQLImpl.getInstance().insertAddSeOnFamily(tempSeOnFam);
                    mainApp.getSeData().add(tempSeOnFam);
                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction");
                    alert.setHeaderText("Error during insert ...");
                    alert.setContentText(e.getMessage());

                    alert.showAndWait();
                }
            }
        }*/
        System.out.println("Devi prima implementare il metodo handleAdd() che richiama insertAddSeOnFam!");
        okClicked = true;
        dialogStage.close();
    }

  public void setAddFamily(SensingElement sensingElement){
         this.sensingElement=sensingElement;
         showSEOnFamily(sensingElement);
         addFamilyTableView.setItems(mainApp.getAddSeFamData());
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
