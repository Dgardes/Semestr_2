package org.example.drachlab_282;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class HelloController {

    @FXML
    private ListView<Product> productListView;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField categoryField;

    @FXML
    private TextField statusField;

    @FXML
    public void initialize() {
        ObservableList<Product> products = FXCollections.observableArrayList(
                new Product("Ноутбук ASUS", "35 000 грн", "Електроніка", "В наявності"),
                new Product("Смартфон Samsung", "18 000 грн", "Електроніка", "Закінчується"),
                new Product("Крісло геймерське", "7 500 грн", "Меблі", "В наявності"),
                new Product("Клавіатура механічна", "2 200 грн", "Периферія", "Немає в наявності")
        );

        productListView.setItems(products);

        productListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                nameField.setText(newValue.getName());
                priceField.setText(newValue.getPrice());
                categoryField.setText(newValue.getCategory());
                statusField.setText(newValue.getStatus());
            }
        });
    }
}