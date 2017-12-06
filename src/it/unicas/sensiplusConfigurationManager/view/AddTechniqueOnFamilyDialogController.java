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

        public void setMainApp(MainApp mainApp){
            this.mainApp=mainApp;
            measureTechniqueTableView.setItems(mainApp.getAddTechniqueOnFamily());
        }

        @FXML
        private void initialize(){
            idMeasureTechniqueColumn.setCellValueFactory(cellData -> cellData.getValue().idSPMeasureTechniqueProperty().asString());
            typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
            showMeasureTechnique(null);

        }

        public void setDialogStage(Stage dialogStage, boolean verifylen){
            this.dialogStage=dialogStage;

            // Set the dialog icon.
            this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
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

        @FXML
        private void handleAdd() {
            Family selMeasureTechnique = measureTechniqueTableView.getSelectionModel().getSelectedItem();
            int idfamily=Integer.parseInt(idFamilyLabel.getText());
            if (selMeasureTechnique != null) {
                try {
                    FamilyDAOMySQLImpl.getInstance().insertAddTechniqueOnFamily(selMeasureTechnique.getIdSPMeasureTechnique(),idfamily);
                } catch (DAOException e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initOwner(mainApp.getPrimaryStage());
                    alert.setTitle("Error during DB interaction ");
                    alert.setHeaderText("Error during insert...  ");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                }
                okClicked=true;
                dialogStage.close();
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


