package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;

public class AddCalibrationOnClusterDialogController {
    @FXML
    private TableView<Chip> calibrationTableView;
    @FXML
    private TableColumn<Chip,String> idColumn;
    @FXML
    private TableColumn<Chip,String> calibrationColumn;

    private Stage dialogStage;
    private Boolean okClicked=false;
    private MainApp mainApp;
    private Cluster idCal;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        calibrationTableView.setItems(mainApp.getCalibration());
        showCalibration();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    public void showCalibration(){
        try {
            List<Chip> list = ChipDAOMySQLImpl.getInstance().selectCalibration();
            mainApp.getCalibration().clear();
            mainApp.getCalibration().addAll(list);
            calibrationTableView.getSelectionModel().selectFirst();

        } catch (DAOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("Error during DB interaction");
            alert.setHeaderText("Error during  showCalibration ...");
            alert.setContentText(e.getMessage());

            alert.showAndWait();

        }

    }

    public void setCalibration(Cluster calibration){
        this.idCal=calibration;
    }

    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(cellData->cellData.getValue().idCalibrationProperty().asString());
        calibrationColumn.setCellValueFactory((cellDeta->cellDeta.getValue().nameCalibrationProperty()));
    }
    @FXML
    private void handleAddButton(){
        idCal.setIdCalibration(calibrationTableView.getSelectionModel().getSelectedItem().getIdCalibration());
        okClicked=true;
        dialogStage.close();
    }




}
