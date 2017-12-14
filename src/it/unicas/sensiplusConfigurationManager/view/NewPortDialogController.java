package it.unicas.sensiplusConfigurationManager.view;

import it.unicas.sensiplusConfigurationManager.MainApp;
import it.unicas.sensiplusConfigurationManager.model.Family;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Created by Antonio on 14/12/2017.
 */
public class NewPortDialogController {

    @FXML
    private CheckBox internalCheckBox;
    @FXML
    private TextField portTextField;
    @FXML
    private Button createButton;

    private Stage dialogStage;
    private Boolean okClicked=false;
    private MainApp mainApp;
    private Family port;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/pencil-lapis-128.png"));
    }

    public boolean isOkClicked(){return okClicked;}

    public void setPort(Family port){
        this.port=port;
    }

    @FXML
    private void handleOk(){
        port.setName(portTextField.getText());
        port.setInternal(internalCheckBox.isSelected());
        okClicked=true;
        dialogStage.close();
    }

    @FXML
    private void activationButton(){
        if(portTextField.getLength()!=0){
            createButton.setDisable(false);
        }else
            createButton.setDisable(true);
    }
}


