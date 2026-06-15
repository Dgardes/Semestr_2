package org.example.drachlab_26;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {

    @FXML
    private Label myLabel;

    @FXML
    private Button myButton;

    @FXML
    private void onButtonClick() {
        myLabel.setText("JavaFX працює");
    }
}