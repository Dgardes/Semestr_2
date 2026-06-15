package org.example.drachlab_272;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private TextField num1Field;

    @FXML
    private ChoiceBox<String> operatorChoice;

    @FXML
    private TextField num2Field;

    @FXML
    private Button calculateButton;

    @FXML
    private Label resultLabel;

    @FXML
    public void initialize() {
        operatorChoice.getItems().addAll("+", "-", "*", "/");
        operatorChoice.setValue("+");
    }

    @FXML
    private void onCalculateClick() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            String operator = operatorChoice.getValue();
            double result = 0;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        resultLabel.setText("Помилка: ділення на нуль!");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }
            resultLabel.setText("Результат: " + result);
        } catch (NumberFormatException e) {
            resultLabel.setText("Помилка: введіть коректні числа!");
        }
    }
}