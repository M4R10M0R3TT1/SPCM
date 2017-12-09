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
    private Integer familyID,portID;

    private MainApp mainApp;

    @FXML
    private TableView<SensingElement> seTableView;
    @FXML
    private TableColumn<SensingElement,String> seIdColumn;



    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        seTableView.setItems(mainApp.getSeOnPort());

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

    public void showSE(boolean type,Integer port,Integer family) {
        portID=port;
        familyID=family;
        try {
            List<SensingElement> list = SensingElementDAOMySQLImpl.getInstance().selectIntern(type);
            mainApp.getSeOnPort().clear();
            mainApp.getSeOnPort().addAll(list);
        } catch (DAOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void handleAdd(){
        SensingElement sensingElement = seTableView.getSelectionModel().getSelectedItem();

        if(sensingElement!=null){
            String se = sensingElement.getIdSensingElement();
            try {
                SensingElementDAOMySQLImpl.getInstance().AddSEOnPort(portID, familyID, se);
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
        }else{
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Sensing Element Selected");
            alert.setContentText("Please select a Sensing Element in the table.");

            alert.showAndWait();
        }


    }

    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
