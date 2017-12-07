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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio on 07/12/2017.
 */
public class AddSEOnPortDialogController {

    private Stage dialogStage;
    private boolean verifyLen=true;
    private SensingElement sensingElement;
    private boolean okClicked=false;
    private MainApp mainApp;

    @FXML
    private TableView<SensingElement> seTableView;
    @FXML
    private TableColumn<SensingElement,String> seIdColumn;



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        seTableView.setItems(mainApp.getSeData());

    }

    @FXML
    private void initialize(){
        seIdColumn.setCellValueFactory(cellData -> cellData.getValue().idSensingElementProperty());


    }

    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public void showSE(){

        mainApp.getSeData();
    }

    public boolean isOkClicked(){
        return okClicked;
    }

}
