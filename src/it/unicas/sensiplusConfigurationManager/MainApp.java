package it.unicas.sensiplusConfigurationManager;

import it.unicas.sensiplusConfigurationManager.model.Chip;
import it.unicas.sensiplusConfigurationManager.model.Cluster;
import it.unicas.sensiplusConfigurationManager.model.Family;
import it.unicas.sensiplusConfigurationManager.model.SensingElement;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.ChipDAOMySQLImpl;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import it.unicas.sensiplusConfigurationManager.view.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

import static javafx.application.Application.launch;

/**
 * Created by  on 21/11/2017.
 */
public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    private ObservableList<SensingElement> seData = FXCollections.observableArrayList();
    private ObservableList<SensingElement> seOnPort = FXCollections.observableArrayList();

    private ObservableList<Family> seFamData=FXCollections.observableArrayList();
    private ObservableList<Family> addSeFamData = FXCollections.observableArrayList();
    private ObservableList<Family> addSeFamPortData=FXCollections.observableArrayList();
    private ObservableList<Family> familyData=FXCollections.observableArrayList();
    private ObservableList<Family> familyPortData=FXCollections.observableArrayList();
    private ObservableList<Family> familyMeasureTechniqueData=FXCollections.observableArrayList();
    private ObservableList<Chip> chipData = FXCollections.observableArrayList();
    private ObservableList<Family> portAndSEData = FXCollections.observableArrayList();
    //Lista osservabile delle porte da poter aggiungere in Family
    private ObservableList<Family> addPortOnFamily=FXCollections.observableArrayList();
    //Lista osservabili delle measureTechnique da aggiungere in family
    private ObservableList<Family> addTechniqueOnFamily=FXCollections.observableArrayList();
    private ObservableList<Chip> clusterChip=FXCollections.observableArrayList();
    private ObservableList<Chip> calibrationChip=FXCollections.observableArrayList();

    //Observable List for NEW Dialog of Chip
    private ObservableList<String> addCalibrationOnChip=FXCollections.observableArrayList();
    //Observable List for new Calibration
    private ObservableList<Chip> calibration=FXCollections.observableArrayList();

    //observable List for show Cluster in cluster OverView
    private ObservableList<Cluster> clusterData=FXCollections.observableArrayList();
    //Observable List for show Configuration in ClusterOverview
    private ObservableList<Cluster> configurationOnClusterData=FXCollections.observableArrayList();
    //Observable List for show Calibration of cluster
    private ObservableList<Cluster> calibrationOnClusterData=FXCollections.observableArrayList();
    //Observable List for show Chip of calibration in ClusterOverview
    private ObservableList<Cluster> chipOfCalibrationOnCLusterData=FXCollections.observableArrayList();
    //Observable List for show Chip details in ClusterOverview
    private ObservableList<Family> chipDetailsOnClusterData=FXCollections.observableArrayList();
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sensiplus Configuration Manager");

        // Set the application icon.
        this.primaryStage.getIcons().add(new Image("file:resources/images/favicon.png"));

        initRootLayout();

        showTabPaneOverview();

        this.primaryStage.show();
    }

    /**
     * Returns the data as an observable list of SEs.
     * @return
     */
    public ObservableList<SensingElement> getSeData() {
        return seData;
    }

    public ObservableList<SensingElement> getSeOnPort(){return seOnPort;}

    public ObservableList<Family> getSeFamData(){ return seFamData;}

    public  ObservableList<Family> getAddSeFamData(){return addSeFamData;}

    public ObservableList<Family> getAddSeFamPortData() {return  addSeFamPortData;}

    public ObservableList<Family> getFamilyData() {return familyData;}

    public ObservableList<Family> getFamilyPortData() {return familyPortData;}

    public ObservableList<Family> getFamilyMeasureTechniqueData(){return familyMeasureTechniqueData;}

    public ObservableList<Family> getAddPortOnFamily(){return addPortOnFamily;}

    public ObservableList<Family> getAddTechniqueOnFamily(){return addTechniqueOnFamily;}

    public ObservableList<Chip> getChipData(){return chipData;}

    public ObservableList<Family> getPortAndSEData(){return portAndSEData;}

    public ObservableList<Chip> getClusterChip() {
        return clusterChip;
    }

    public ObservableList<Chip> getCalibrationChip() {
        return calibrationChip;
    }

    public ObservableList<String> getAddCalibrationOnChip() {
        return addCalibrationOnChip;
    }

    public ObservableList<Chip> getCalibration() {
        return calibration;
    }

    public ObservableList<Cluster> getClusterData() {
        return clusterData;
    }

    public ObservableList<Cluster> getConfigurationOnClusterData() {
        return configurationOnClusterData;
    }

    public ObservableList<Cluster> getCalibrationOnClusterData() {
        return calibrationOnClusterData;
    }

    public ObservableList<Cluster> getChipOfCalibrationOnCLusterData() {
        return chipOfCalibrationOnCLusterData;
    }

    public ObservableList<Family> getChipDetailsOnClusterData() {
        return chipDetailsOnClusterData;
    }

    public void initRootLayout(){
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                handleExit();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showTabPaneOverview(){
        try {
            // Load TabPaneoverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/TabPaneOverview.fxml"));

            AnchorPane TabPaneOverview= (AnchorPane) loader.load();

            // Set TabPaneoverview into the center of root layout.
            rootLayout.setCenter(TabPaneOverview);

            // Give the controller access to the main app.
            //SEOverviewController controller = loader.getController();
            TabPaneOverviewController controller =loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Opens a dialog to edit details for the specified sensingElement. If the user
     * clicks OK, the changes are saved into the provided sensingElement object and true
     * is returned.
     *
     * @param sensingElement the SE object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showSEEditDialog(SensingElement sensingElement, boolean verifyLen) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SEEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Sensing Element");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the sensingElement into the controller.
            SEEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage, verifyLen);
            controller.setSensingElement(sensingElement);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showFamilyEditDialog(Family family, boolean verifyLen) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the sensingElement into the controller.
            FamilyEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage, verifyLen);
            controller.setFamily(family);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showChipEditDialog(Chip chip, boolean verifyLen) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Insert a new Chip");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the sensingElement into the controller.
            ChipEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage, verifyLen);
            controller.setChip(chip);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showSEOnFamilyDialog(List<Family> list, SensingElement sensingElement, boolean verifyLen){

            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApp.class.getResource("view/AddSEOnFamilyDialog.fxml"));
                AnchorPane page = (AnchorPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("SensingElement on Family");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the sensingElement into the controller.
                AddSEOnFamilyDialogController controller = loader.getController();
                controller.setMainApp(this);
                controller.setDialogStage(dialogStage, verifyLen);
                controller.showSEOnFamily(list,sensingElement);

                dialogStage.showAndWait();
                return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
    }

    public boolean showAddPortOnFamily(Family family,boolean verifyLen){
        try{
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddPortOnFamilyDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Port on Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddPortOnFamilyDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.setPort(family);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAddTechniqueOnFamily(Family family,boolean verifyLen){
        try{
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddTechniqueOnFamilyDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Measure Technique on Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddTechniqueOnFamilyDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.setTechnique(family);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAddSEOnPort(boolean type,Integer port,Integer family,boolean verifyLen){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddSEOnPortDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Measure Technique on Family");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddSEOnPortDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.showSE(type,port,family);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showSettingsEditDialog(DAOMySQLSettings daoMySQLSettings){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SettingsEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Database Communication Settings");
            dialogStage.initModality((Modality.WINDOW_MODAL));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            SettingsEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setSettings(daoMySQLSettings);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();


            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAddSEOnChipDialog(Family f,String id,String idChip, boolean verifyLen){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddSEOnChipDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add SE on Chip");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddSEOnChipDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.showAddSEOnChip(f, id, idChip);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showAddCalibrationOnChipDialog(Family port,String id,Chip chip, boolean verifyLen){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddSEOnChipDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Calibration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddSEOnChipDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.showAddCalibration(port, id, chip);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEditCalibrationOnChipDialog(Family port,String id,Chip chip,Chip calibration,  boolean verifyLen){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/AddSEOnChipDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Calibration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AddSEOnChipDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage, verifyLen);
            controller.showEditCalibration(port, id, chip,calibration);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean showCalibrationManagementDialog(){
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CalibrationDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Calibration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            CalibrationDialogController controller=loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.showCalibration();

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean showNewConfigurationDialog(Cluster cluster) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfigurationDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("New Configuration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConfigurationDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setCluster(cluster);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showEditConfigurationDialog(Cluster cluster) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfigurationDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Configuration");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ConfigurationDialogController controller = loader.getController();
            controller.setMainApp(this);
            controller.setDialogStage(dialogStage);
            controller.setObject(cluster);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
            return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Closes the application.
     */
    public void handleExit() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Are you sure?");
        //---To set the image on dialog
        Image image = new Image("file:resources/images/exit-res.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        //---To add an icon to the alert
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:resources/images/exit.png"));
        //---
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit?");

        ButtonType buttonTypeOne = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            System.exit(0);
        }
    }



    public MainApp(){
    }



    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) { launch(args); }
}
