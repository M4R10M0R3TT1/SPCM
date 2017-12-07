package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TabPaneOverviewController {

    @FXML
    private AnchorPane seAnchorPane;
    @FXML
    private AnchorPane familyAnchorPane;
    @FXML
    private AnchorPane chipAnchorPane;


    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showSEOverview();
        showFamilyOverview();
        showChipOverview();
    }

   public void showSEOverview(){
        try {
            // Load SEOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SEOverview.fxml"));
            AnchorPane SEOverview = (AnchorPane) loader.load();

            // Set SEOverview into the center of root layout.
            //setCenter(SEOverview);
            seAnchorPane.getChildren().add(SEOverview);



            // Give the controller access to the main app.
            // SEOverviewController controller = loader.getController();
            SEOverviewController controller=loader.getController();
            controller.setMainApp(this.mainApp);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void showFamilyOverview(){
        try {
            // Load FamilyOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/FamilyOverview.fxml"));
            AnchorPane FamilyOverview = (AnchorPane) loader.load();

            // Set FamilyOverview into the center of root layout.
            //setCenter(FamilyOverview);
            familyAnchorPane.getChildren().add(FamilyOverview);



            // Give the controller access to the main app.

            FamilyOverviewController controller=loader.getController();
            controller.setMainApp(this.mainApp);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showChipOverview(){
        try {
            // Load ChipOverview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ChipOverview.fxml"));
            AnchorPane ChipOverview = (AnchorPane) loader.load();

            // Set ChipOverview into the center of root layout.
            //setCenter(ChipOverview);
            chipAnchorPane.getChildren().add(ChipOverview);



            // Give the controller access to the main app.

            ChipOverviewController controller=loader.getController();
            controller.setMainApp(this.mainApp);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
