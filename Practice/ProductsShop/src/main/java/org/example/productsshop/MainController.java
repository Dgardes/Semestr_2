package org.example.productsshop;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import store.objects.WeightProduct;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");

    }


}
