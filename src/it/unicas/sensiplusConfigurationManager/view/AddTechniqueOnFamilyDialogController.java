package it.unicas.sensiplusConfigurationManager.view;
import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class AddTechniqueOnFamilyDialogController {

        private Stage dialogStage;
        private boolean verifyLen=true;
        private Family family;
        private boolean okClicked=false;
        private MainApp mainApp;

        @FXML
        private TableView<Family> measureTechniqueTableView;
        @FXML
        private TableColumn<Family,String> idMeasureTechniqueColumn;
        @FXML
        private TableColumn<Family,String> typeColumn;
        @FXML
        private Label idFamilyLabel;
        @FXML
        private Button addButton;

        public void setMainApp(MainApp mainApp){
            this.mainApp=mainApp;
            measureTechniqueTableView.setItems(mainApp.getAddTechniqueOnFamily());
        }

        @FXML
        private void initialize(){
            idMeasureTechniqueColumn.setCellValueFactory(cellData -> cellData.getValue().idSPMeasureTechniqueProperty().asString());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
            showMeasureTechnique(null);
            activationButton(null);
            measureTechniqueTableView.getSelectionModel().selectedItemProperty().addListener(
                    ((observable, oldValue, newValue) -> activationButton(newValue))
            );

        }

        public void setDialogStage(Stage dialogStage, boolean verifylen){
            this.dialogStage=dialogStage;

            // Set the dialog icon.
            this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
        }

        public void activationButton(Family tec){
            if(tec!=null)
                addButton.setDisable(false);
        }

        private void showMeasureTechnique(Family family){
            if(family!=null){
                try{
                    idFamilyLabel.setText(Integer.toString(family.getIdSPFamily()));
                    List<Family> list = FamilyDAOMySQLImpl.getInstance().selectAddTechniqueOnFamily(family);
                    mainApp.getAddTechniqueOnFamily().clear();
                    mainApp.getAddTechniqueOnFamily().addAll(list);
                }catch(DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction ");
                    alert.setHeaderText("Error during insert... ");
                    alert.setContentText(e.getMessage());

                    alert.showAndWait();
                }
            }
        }

        public void setTechnique(Family family){
            this.family=family;
            showMeasureTechnique(family);
        }

        public boolean isOkClicked(){
            return okClicked;
        }

        @FXML
        private void handleCancel(){dialogStage.close();}
}


