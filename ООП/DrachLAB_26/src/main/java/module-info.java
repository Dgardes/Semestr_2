module org.example.drachlab_26 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.drachlab_26 to javafx.fxml;
    exports org.example.drachlab_26;
}