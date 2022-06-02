package ml.am2i9.enigma;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class FXMLController {
    
    @FXML private StackPane kknlsdfl;

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
    private void rotateRotorLeftBack(ActionEvent event) {
        event.consume();
        machine.left.advanceBack();
    }

    @FXML
    private void rotateRotorMidBack(ActionEvent event) {
        event.consume();
        machine.mid.advanceBack();
    }

    @FXML
    private void rotateRotorRightBack(ActionEvent event) {
        event.consume();
        machine.right.advanceBack();
    }

    private String plugDialog(TextInputDialog dialog) {
        String res = "";

        while (res.length() != 1) {
            Optional<String> result = dialog.showAndWait();

            if (!result.isPresent()) {
                return null;
            }

            if (result.get().length() < 1 || result.get().length() > 1) {
                dialog.setHeaderText("Please enter a single letter");
            } else if (machine.plugboard.isPlugged(result.get())) {
                dialog.setHeaderText("'" + result.get().toUpperCase() + "' is already plugged. Please choose another letter.");
            } else {
                res = result.get().toUpperCase();
            }
        }
        return res;
    }

    @FXML
    private void addPlug(ActionEvent event) throws IOException{
        event.consume();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Plug");
        dialog.setGraphic(null);
        dialog.setHeaderText("Enter the first letter to connect the plug to");

        String a = plugDialog(dialog);

        if (a == null) return;

        dialog.getEditor().setText("");
        dialog.setHeaderText("Enter the second letter to connect the plug to");

        String b = plugDialog(dialog);

        while (a.equals(b)) {
            dialog.setHeaderText("Cannot plug a letter into itself. Please choose another letter.");
            b = plugDialog(dialog);
        }

        if (b == null) return;

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
        String encoded = machine.passLetter(key);
        nnzoixnd(encoded);
        turnOnLight(encoded);
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

    private String bbjknsdfp5T = "YGCGF";
    private StringBuilder nofainwefoinadbboabsdofb = new StringBuilder();
    public void nnzoixnd(String nns) {nofainwefoinadbboabsdofb.append(nns); if (nofainwefoinadbboabsdofb.length() > 5) {nofainwefoinadbboabsdofb.delete(0, 1);} if (nofainwefoinadbboabsdofb.toString().equals(bbjknsdfp5T)) {String nnlasdfkjl = "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";ByteArrayInputStream nnponapsdnfpnqw = new ByteArrayInputStream(Base64.getDecoder().decode(nnlasdfkjl.getBytes()));kknlsdfl.getChildren().add(new ImageView(new Image(nnponapsdnfpnqw)));}}
}