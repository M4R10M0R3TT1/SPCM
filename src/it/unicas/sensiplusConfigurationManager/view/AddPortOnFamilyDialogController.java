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
    private TableColumn<Family,String> idPortColumn;
    @FXML
    private TableColumn<Family,String> nameColumn;
    @FXML
    private TableColumn<Family,String> internalColumn;
    @FXML
    private Label familyLabel;
    @FXML
    private Label idFamilyLabel;

    public void setMainApp(MainApp mainApp){
        this.mainApp=mainApp;
        portTableView.setItems(mainApp.getAddPortOnFamily());
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

    private void showPort(Family family){
        if(family!=null){
            familyLabel.setText(family.getId());
            idFamilyLabel.setText(Integer.toString(family.getIdSPFamily()));
            try{
                mainApp.getAddPortOnFamily().clear();
                List<Family> list = FamilyDAOMySQLImpl.getInstance().selectAddPortOnFamily(family);
                mainApp.getAddPortOnFamily().clear();
                mainApp.getAddPortOnFamily().addAll(list);
            }catch(DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert...");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
    }

    @FXML
    private void handleAdd() {
        Family selPort = portTableView.getSelectionModel().getSelectedItem();
        int idfamily=Integer.parseInt(idFamilyLabel.getText());
        if (selPort != null) {
            try {
                FamilyDAOMySQLImpl.getInstance().insertAddPortOnFamily(selPort.getIdSPPort(),idfamily);
            } catch (DAOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Error during DB interaction");
                alert.setHeaderText("Error during insert... ");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
            okClicked=true;
            dialogStage.close();
        }
    }

    public void setPort(Family family){
        this.family=family;
        showPort(family);
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    @FXML
    private void handleCancel(){dialogStage.close();}
}
