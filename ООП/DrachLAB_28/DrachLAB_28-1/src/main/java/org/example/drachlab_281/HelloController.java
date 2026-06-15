package org.example.drachlab_281;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HelloController {

    @FXML
    private TextArea infoTextArea;

    @FXML
    private void showEncapsulation() {
        infoTextArea.setText("Інкапсуляція – це приховування внутрішньої реалізації об'єкта від прямого доступу ззовні та надання доступу через публічні методи (геттери та сеттери).");
    }

    @FXML
    private void showInheritance() {
        infoTextArea.setText("Наслідування – це механізм, який дозволяє одному класу (нащадку) успадковувати властивості та методи іншого класу (предка), уникаючи дублювання коду.");
    }

    @FXML
    private void showPolymorphism() {
        infoTextArea.setText("Поліморфізм – це здатність об'єктів різних класів з однаковим інтерфейсом виконувати дії по-різному (наприклад, через перевизначення методів).");
    }

    @FXML
    private void showAbstraction() {
        infoTextArea.setText("Абстракція – це виділення головних характеристик об'єкта і відкидання другорядних, що реалізується за допомогою абстрактних класів та інтерфейсів.");
    }
}