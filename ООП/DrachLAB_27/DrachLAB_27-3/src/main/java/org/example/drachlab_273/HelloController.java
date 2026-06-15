package org.example.drachlab_273;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import java.time.LocalDate;

public class HelloController {

    @FXML
    private DatePicker datePicker;

    @FXML
    private Button checkButton;

    @FXML
    private Label slavicLabel;

    @FXML
    private Label chineseLabel;

    @FXML
    private void onCheckClick() {
        LocalDate selectedDate = datePicker.getValue();

        if (selectedDate == null) {
            slavicLabel.setText("Помилка: Оберіть дату!");
            chineseLabel.setText("");
            return;
        }

        int year = selectedDate.getYear();
        int month = selectedDate.getMonthValue();
        int day = selectedDate.getDayOfMonth();

        Zodiac zodiac = new Zodiac(year, month, day);

        slavicLabel.setText("Слов'янський гороскоп: " + zodiac.getSlavicZodiac());
        chineseLabel.setText("Китайський гороскоп: " + zodiac.getChineseZodiac());
    }
}