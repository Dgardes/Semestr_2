package org.example.drachlab_291;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class HelloController {

    @FXML
    private ImageView imageView;

    private static int currentImageIndex = 0;

    @FXML
    public void initialize() {
        int imgNum = currentImageIndex + 1;
        String path = "/org/example/drachlab_291/" + imgNum + ".png";
        java.net.URL imgUrl = getClass().getResource(path);

        if (imgUrl == null) {
            path = "/org/example/drachlab_291/" + imgNum + ".jpg";
            imgUrl = getClass().getResource(path);
        }

        if (imgUrl != null) {
            imageView.setImage(new Image(imgUrl.toExternalForm()));
        } else {
            System.out.println("Критична помилка: Не вдалося знайти картинку " + imgNum + " в ресурсах пакета!");
        }
    }

    @FXML
    private void onNextClick() throws IOException {
        Stage currentStage = (Stage) imageView.getScene().getWindow();
        currentStage.close();
        currentImageIndex = (currentImageIndex + 1) % 4;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        Stage newStage = new Stage();
        newStage.setTitle("Перегляд зображень: " + (currentImageIndex + 1));
        newStage.setScene(scene);
        newStage.show();
    }
}