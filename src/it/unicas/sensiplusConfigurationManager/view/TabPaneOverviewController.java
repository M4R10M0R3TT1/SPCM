package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class TabPaneOverviewController {

    @FXML
    private Tab seTab;
    @FXML
    private AnchorPane seAnchorPane;
    @FXML
    private Pane sePane;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        showSEOverview();
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
}
