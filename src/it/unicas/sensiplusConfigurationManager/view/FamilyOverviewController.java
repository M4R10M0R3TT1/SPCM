package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.dao.DAOException;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.FamilyDAOMySQLImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class FamilyOverviewController {

    @FXML
    private TableView<Family> familyTableView;
    @FXML
    private TableColumn<Family,String> idTableColumn;
    @FXML
    private TableColumn<Family,String> familyColumn;


    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        familyTableView.setItems(mainApp.getFamilyData());
        familyTableView.setItems(mainApp.getFamilyData());
        handleReadDB();
    }

    @FXML
    private void handleReadDB() {
        // SensingElement tempSe = new SensingElement("");
        Family tempSe = new Family(0,"");

        try {
            List<Family> list = FamilyDAOMySQLImpl.getInstance().select(tempSe);
            mainApp.getFamilyData().clear();
            mainApp.getFamilyData().addAll(list);
           // showSEDetails(null);

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
