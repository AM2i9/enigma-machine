package ml.am2i9.enigma;

import java.io.IOException;
import java.util.HashMap;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FXMLController {

    @FXML private VBox keyboard;
    @FXML private VBox lightboard;
    @FXML private ListView<String> rotorl;
    @FXML private ListView<String> rotorm;
    @FXML private ListView<String> rotorr;
    @FXML private HBox rotors;

    @FXML private ListView<Plug> plugboardlist;

    private HashMap<String, StackPane> lightMap;

    private Machine machine;

    @FXML
    private void rotateRotorLeft(ActionEvent event) {
        event.consume();
        machine.left.advance();
    }

    @FXML
    private void rotateRotorMid(ActionEvent event) {
        event.consume();
        machine.mid.advance();
    }

    @FXML
    private void rotateRotorRight(ActionEvent event) {
        event.consume();
        machine.right.advance();
    }

    @FXML
    private void addPlug(ActionEvent event) throws IOException{
        event.consume();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Plug");
        dialog.setGraphic(null);
        dialog.setHeaderText("Enter the first letter to connect the plug to");

        String a = "";

        while (a.length() < 1 || a.length() > 1) {
            dialog.showAndWait();
            a = dialog.getEditor().getText();

            if (a.length() < 1 || a.length() > 1) {
                dialog.setHeaderText("Please enter a single letter");
            }
        }

        a = a.toUpperCase();
        dialog.getEditor().setText("");

        dialog.setHeaderText("Enter the second letter to connect the plug to");

        String b = "";

        while (b.length() < 1 || b.length() > 1) {
            dialog.showAndWait();
            b = dialog.getEditor().getText();

            if (b.length() < 1 || b.length() > 1) {
                dialog.setHeaderText("Please enter a single letter");
            }
        }

        b = b.toUpperCase();

        machine.plugboard.addPlug(a, b);
        
    }

    @FXML
    private void removePlug(ActionEvent event) {
        event.consume();

        MultipleSelectionModel<Plug> select = plugboardlist.getSelectionModel();
        if (select.getSelectedIndex() >= 0) {
            machine.plugboard.removePlug(select.getSelectedIndex());
        }
    }

    protected void handleKeyboardButtonPress(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String key = btn.getText();
        turnOnLight(machine.passLetter(key));
        event.consume();
    }

    private void turnOnLight(String letter) {
        StackPane light = lightMap.get(letter);

        if (light != null) {
            light.getChildren().get(0).setStyle("-fx-fill: yellow");
            light.getChildren().get(1).setStyle("-fx-fill: black");
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {Thread.sleep(1000);}
                    catch (InterruptedException e) {}
                    return null;
                }
            };
            sleeper.setOnSucceeded(event -> {
                light.getChildren().get(0).setStyle("-fx-fill: black");
                light.getChildren().get(1).setStyle("-fx-fill: white");
            });
            new Thread(sleeper).start();
        }
    }

    public void initialize() {

        machine = new Machine();

        lightMap = new HashMap<String, StackPane>();

        String[] keys = {
            "QWERTYUIOP",
            "ASDFGHJKL",
            "ZXCVBNM"
        };

        Font font = new Font(16);

        for (int r = 0; r < keys.length; r++){
            HBox row = new HBox(5);
            HBox lightRow = new HBox();
            for(int c = 0; c < keys[r].length(); c++){

                Button btn = new Button(keys[r].substring(c, c + 1));
                btn.setOnAction(this::handleKeyboardButtonPress);
                btn.setFont(font);

                row.getChildren().add(btn);

                StackPane light = new StackPane();
                light.setPrefSize(30, 30);
                
                Circle circ = new Circle();
                circ.setRadius(15);

                Text letter = new Text(keys[r].substring(c, c + 1));
                letter.setFont(font);
                letter.setTextAlignment(TextAlignment.CENTER);
                letter.setFill(Color.WHITE);

                light.getChildren().addAll(circ, letter);
                lightRow.getChildren().add(light);

                lightMap.put(keys[r].substring(c, c + 1), light);
            }
            row.setAlignment(Pos.CENTER);
            keyboard.getChildren().add(row);

            lightRow.setAlignment(Pos.CENTER);
            lightRow.setSpacing(5);
            lightboard.getChildren().add(lightRow);
        }
        keyboard.setSpacing(5);
        keyboard.setPadding(new Insets(5));

        lightboard.setSpacing(5);
        lightboard.setPadding(new Insets(5));

        rotorl.setItems(machine.left.alphabet);
        rotorl.setMaxWidth(150);
        rotorm.setItems(machine.mid.alphabet);
        rotorm.setMaxWidth(150);
        rotorr.setItems(machine.right.alphabet);
        rotorr.setMaxWidth(150);

        plugboardlist.setItems(machine.plugboard.plugs);
        plugboardlist.setMaxWidth(150);

        rotors.setAlignment(Pos.CENTER);
    }
}