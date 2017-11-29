package it.unicas.sensiplusConfigurationManager.view;


import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.SensingElementDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private TableView addFamilyTableView;
    @FXML
    private TableColumn<SensingElement,String> familyIDColumn;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        addFamilyTableView.setItems(mainApp.getSeData());

    }

    @FXML
    private void initialize(){
        familyIDColumn.setCellValueFactory(cellData->cellData.getValue().family_idProperty());
        showSEOnFamily(null);
        addFamilyTableView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showSEOnFamily(newValue)));
    }



    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;



    }

    private void showSEOnFamily(SensingElement sensingElement){
        if(sensingElement!=null){
            try{
                List<SensingElement> list= SensingElementDAOMySQLImpl.getInstance().
            }
        }
    }

    public void setAddFamily(SensingElement sensingElement){
         this.sensingElement=sensingElement;
         addFamilyTableView.setItems();
    }

    public boolean isOkClicked(){
        return okClicked;
    }

}
