module org.example.drachlab_291 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.drachlab_291 to javafx.fxml;
    exports org.example.drachlab_291;
}