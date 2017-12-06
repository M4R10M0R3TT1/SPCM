package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Family;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ChipOverviewController {

    @FXML
    private Label chipLabel;
    @FXML
    private Label familyLabel;
    @FXML
    private TableView<Chip> chipTableView;
    @FXML
    private TableColumn<Chip,String> chipColumn;
    @FXML
    private TableView<Family> portTableView;
    @FXML
    private TableColumn<Family,Integer> idPortColumn;
    @FXML
    private TableColumn<Family,String> namePortColumn;
    @FXML
    private TableColumn<Family,Boolean> internalPort;
    @FXML
    private TableColumn<Family,String> idSensingElementColumn;
    @FXML
    private TableView<Chip> calibrationTableView;
    @FXML
    private TableColumn<Chip,Integer> idCalibrationColumn;
    @FXML
    private TableColumn<Chip,String> nameCalibrationColumn;
    @FXML
    private TableColumn<Chip,Integer> mColumn;
    @FXML
    private TableColumn<Chip,Integer> nColumn;
    @FXML
    private TableView<String> clusterTableView;
    @FXML
    private TableColumn<String,String> clusterColumn;

    // Reference to the main application
    private MainApp mainApp;
    public void setMainApp(MainApp mainApp) {

        this.mainApp = mainApp;

    }
}
