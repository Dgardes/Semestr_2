package org.example.drachlab_292;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InvoiceController {
    @FXML private TableView<Product> tableView;
    @FXML private TableColumn<Product, String> nameCol;
    @FXML private TableColumn<Product, Integer> qtyCol;
    @FXML private TableColumn<Product, Double> priceCol;
    @FXML private TableColumn<Product, Integer> packCol;
    @FXML private TableColumn<Product, Double> totalCol;

    public void setTableData(ObservableList<Product> products) {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        qtyCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        packCol.setCellValueFactory(new PropertyValueFactory<>("packNormal"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        tableView.setItems(products);
    }
}