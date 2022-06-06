package ml.am2i9.enigma;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Blocks key events that produce "blank" keys, such as spacebar, tab, enter, etc.
        // Prevents buttons being triggered by pressing enter or space while typing
        scene.addEventFilter(KeyEvent.ANY, event -> {
            if (event.getText().isBlank()) {
                event.consume();
            }
        });

        stage.setTitle("Enigma Machine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}