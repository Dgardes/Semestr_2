package org.example.drachlab_292;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InputController {
    @FXML private TextField nameField;
    @FXML private TextField qtyField;
    @FXML private TextField priceField;
    @FXML private TextField packField;
    @FXML private Label statusLabel;

    private static final ObservableList<Product> productList = FXCollections.observableArrayList();

    @FXML
    private void onAddProduct() {
        try {
            String name = nameField.getText();
            int qty = Integer.parseInt(qtyField.getText());
            double price = Double.parseDouble(priceField.getText());
            int pack = Integer.parseInt(packField.getText());

            productList.add(new Product(name, qty, price, pack));
            statusLabel.setText("Товар додано! Всього: " + productList.size());

            nameField.clear(); qtyField.clear(); priceField.clear(); packField.clear();
        } catch (Exception e) {
            statusLabel.setText("Помилка введення даних!");
        }
    }

    @FXML
    private void onShowInvoice() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("invoice-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 550, 400);

            InvoiceController controller = fxmlLoader.getController();
            controller.setTableData(productList);

            Stage stage = new Stage();
            stage.setTitle("Підсумкова накладна");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}