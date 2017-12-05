package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class AddPortOnFamilyDialogController {

    private Stage dialogStage;
    private boolean verifyLen=true;
    private Family family;
    private boolean okClicked=false;
    private MainApp mainApp;

    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,Integer> idPortcolumn;
    @FXML
    private TableColumn<Family,String> nameColumn;
    @FXML
    private TableColumn<Family,Boolean> internalColumn;
    @FXML
    private Label familyLabel;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
       portTableView.setItems(mainApp.getAddPortFamData());
    }
    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;
        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

 /*   public void setAddFamily(Family family){
        this.family=family;
        showPortOnFamily(family);
    }*/




    
    public boolean isOkClicked(){
        return okClicked;
    }
}
