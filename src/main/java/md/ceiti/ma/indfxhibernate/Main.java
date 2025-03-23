package md.ceiti.ma.indfxhibernate;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(
                Main.class.getResource("/md/ceiti/ma/indfxhibernate/css/main-window-style.css")
                        .toExternalForm());
        stage.setTitle("Școala");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}