package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class AddPortOnFamilyController {

    private Stage dialogStage;
    private boolean verifyLen=true;
    private Family family;
    private boolean okClicked=false;
    private MainApp mainApp;

    @FXML
    private TableView<Family> addPortTableView;
    @FXML
    private TableColumn<Family,String> idPortColumn;
    @FXML
    private TableColumn<Family,String> nameColumn;
    @FXML
    private TableColumn<Family,String> internalColumn;

    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
        addPortTableView.setItems(mainApp.getAddPortOnFamily());
    }

    @FXML
    private void initialize(){
        idPortColumn.setCellValueFactory(cellData -> cellData.getValue().idSPPortProperty().asString());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().portNameProperty());
        internalColumn.setCellValueFactory(cellData -> cellData.getValue().internalProperty().asString());
        showPort(null);

    }

    public void setDialogStage(Stage dialogStage, boolean verifylen){
        this.dialogStage=dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public void showPort(Family family){
        if(family!=null){

            try{
                mainApp.getAddPortOnFamily().clear();
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectAddPortOnFamily(family);
                mainApp.getAddPortOnFamily().clear();
                mainApp.getAddPortOnFamily().addAll(list);
            }catch(DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    public boolean isOkClicked(){
        return okClicked;
    }
}
