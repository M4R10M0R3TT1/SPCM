package it.unicas.sensiplusConfigurationManager.view;


import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
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
    private boolean verifylen=true;
    private SensingElement sensingElement;
    private boolean okClicked=false;
    private MainApp mainApp;

    @FXML
    private TableView<SensingElement> addFamilyTableView;
    @FXML
    private TableColumn<SensingElement,String> familyIDColumn;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        //showSEOnFamily(sensingElement);
        //addFamilyTableView.setItems(mainApp.getAddSeFamData());

    }

    @FXML
    private void initialize(){
        familyIDColumn.setCellValueFactory(cellData->cellData.getValue().family_idProperty());
        /*showSEOnFamily(null);
        addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showSEOnFamily(newValue));*/
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

  public void setAddFamily(SensingElement sensingElement){
         this.sensingElement=sensingElement;
         showSEOnFamily(sensingElement);
         addFamilyTableView.setItems(mainApp.getAddSeFamData());
   }

    public boolean isOkClicked(){
        return okClicked;
    }



}
