package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.dao.mySql.DAOMySQLSettings;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by on 23/11/2017.
 */
public class RootLayoutController {

    // Reference to the main application
    private MainApp mainApp;

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleSettings() {
        DAOMySQLSettings daoMySQLSettings = DAOMySQLSettings.getCurrentDAOMySQLSettings();
        if (mainApp.showSettingsEditDialog(daoMySQLSettings)){
            DAOMySQLSettings.setCurrentDAOMySQLSettings(daoMySQLSettings);
        }

    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About SPCM");
        //---To set the image on dialog
        Image image = new Image("file:resources/images/favicon-about.png");
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        //---
        //---To set the dialog icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:resources/images/info.png"));
        //---
        alert.setHeaderText("SensiPlus Configuration Manager");
        alert.setContentText("Authors:   Fernando Damiano Di Tano\n                Antonio Di Traglia\n                Mario Moretti");

        alert.showAndWait();
    }


    public File getFilePath(){
        Preferences prefs=Preferences.systemNodeForPackage(MainApp.class);
        String filePath=prefs.get("FilePath",null);
        if(filePath!=null) {
            return new File(filePath);
        }else{
            return null;
        }
    }



    @FXML
    private void handleSaveAs(){
        FileChooser fileChooser=new FileChooser();
        FileChooser.ExtensionFilter extFilter=new FileChooser.ExtensionFilter("XML files(*.xml)","*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file=fileChooser.showSaveDialog(mainApp.getPrimaryStage());
        if (file!=null){
            if(!file.getPath().endsWith(".xml"));
            {
                file =new File((file.getPath()+".xml"));
            }
            mainApp.saveSEDataToFile(file);
        }
    }


    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {

        mainApp.handleExit();

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

}
