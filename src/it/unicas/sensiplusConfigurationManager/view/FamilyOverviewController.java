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

import java.util.List;

public class FamilyOverviewController {

    @FXML
    private TableView<Family> familyTableView;
    @FXML
    private TableColumn<Family,String> idTableColumn;
    @FXML
    private TableColumn<Family,String> familyTableColumn;
    @FXML
    private Label idLabel;
    @FXML
    private Label nameLabel;
    @FXML
    private Label hwVersionLabel;
    @FXML
    private Label sysclockLabel;
    @FXML
    private Label osctrimLabel;


    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {

                this.mainApp = mainApp;

        familyTableView.setItems(mainApp.getFamilyData());
        handleReadDB();
    }

    public FamilyOverviewController(){

    }

    @FXML
    private void initialize(){

        idTableColumn.setCellValueFactory(cellData->cellData.getValue().idSPFamilyProperty().asString());
        familyTableColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());

        showFamilyDetails(null);
        familyTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showFamilyDetails(newValue)) );

    }

    private void showFamilyDetails(Family family){
        if(family!=null){
            idLabel.setText(family.getId());
            nameLabel.setText(family.getName());
            hwVersionLabel.setText(family.getHwVersion());
            sysclockLabel.setText(family.getSysclock());
            osctrimLabel.setText(family.getOsctrim());
        }
    }

    @FXML
    private void handleReadDB() {
        Family tempFam = new Family(0,"","","","","");

        try {
            List<Family> list = FamilyDAOMySQLImpl.getInstance().select(tempFam);
            mainApp.getFamilyData().clear();
            mainApp.getFamilyData().addAll(list);
            showFamilyDetails(null);

        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during search ...");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
    }


}
