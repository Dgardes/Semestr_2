package org.example.drachlab_271;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField inputField;

    @FXML
    private ChoiceBox<String> conversionChoice;

    @FXML
    private Button convertButton;

    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        conversionChoice.getItems().add("м/с -> км/год");
        conversionChoice.getItems().add("км/год -> м/с");
        conversionChoice.setValue("м/с -> км/год");
    }

    @FXML
    private void onConvertClick() {
        try {
            double inputValue = Double.parseDouble(inputField.getText());
            String selectedOption = conversionChoice.getValue();
            double result;

            if (selectedOption.equals("м/с -> км/год")) {
                result = inputValue * 3.6;
                resultLabel.setText("Результат: " + result + " км/год");
            } else {
                result = inputValue / 3.6;
                resultLabel.setText("Результат: " + result + " м/с");
            }
        } catch (NumberFormatException e) {
            resultLabel.setText("Помилка: введіть число!");
        }
    }
}