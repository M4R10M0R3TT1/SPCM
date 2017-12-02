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
    private TableColumn<Family,String> nameTableColumn;
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

    //Parte spPort
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,String> portNameTableColumn;
    @FXML
    private TableColumn<Family,String> portTypeTableColumn;

    //Parte Measure Technique
    @FXML
    private TableView<Family> measureTechniqueTableView;
    @FXML
    private TableColumn<Family,String> measureTechniqueTableColumn;



    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;
        familyTableView.setItems(mainApp.getFamilyData());
        portTableView.setItems(mainApp.getFamilyPortData());
        measureTechniqueTableView.setItems(mainApp.getFamilyMeasureTechniqueData());
        handleReadDB();
    }

    public FamilyOverviewController(){

    }

    @FXML
    private void initialize(){

        idTableColumn.setCellValueFactory(cellData->cellData.getValue().idSPFamilyProperty().asString());
        familyTableColumn.setCellValueFactory(cellData->cellData.getValue().idProperty());
        nameTableColumn.setCellValueFactory(cellData->cellData.getValue().nameProperty());

        showFamilyDetails(null);
        familyTableView.getSelectionModel().selectedItemProperty().addListener(
                ((observable, oldValue, newValue) -> showFamilyDetails(newValue)) );
        //spPort
        portNameTableColumn.setCellValueFactory(cellData->cellData.getValue().portNameProperty());
        portTypeTableColumn.setCellValueFactory(cellData->cellData.getValue().internalProperty().asString());
        //spMeasureTechnique
        measureTechniqueTableColumn.setCellValueFactory(cellData->cellData.getValue().typeProperty());


    }

    private void showFamilyDetails(Family family){
        if(family!=null) {
            idLabel.setText(family.getId());
            nameLabel.setText(family.getName());
            hwVersionLabel.setText(family.getHwVersion());
            sysclockLabel.setText(family.getSysclock());
            osctrimLabel.setText(family.getOsctrim());


            //parte spPort
            try {
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectPort(family);
                mainApp.getFamilyPortData().clear();
                mainApp.getFamilyPortData().addAll(list);

            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during select ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }

            //parte spMeasureTechnique
            try{
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectMeasureTechnique(family);
                mainApp.getFamilyMeasureTechniqueData().clear();
                mainApp.getFamilyMeasureTechniqueData().addAll(list);

            }catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during select ...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
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

    @FXML
    private void handleEditFamily(){
        Family selectedFamily=familyTableView.getSelectionModel().getSelectedItem();
        if(selectedFamily!= null){
            boolean okClicked=mainApp.showFamilyEditDialog(selectedFamily,true);
            if (okClicked) {
                try {
                    FamilyDAOMySQLImpl.getInstance().update(selectedFamily);
                    showFamilyDetails(selectedFamily);
                } catch (DAOException e) {
                    e.printStackTrace();
                }
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



}
