module org.example.productsshop {
    requires javafx.controls;
    requires javafx.fxml;
    requires StoreAPI;


    opens org.example.productsshop to javafx.fxml;
    exports org.example.productsshop;
}