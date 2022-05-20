package ml.am2i9.enigma;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class FXMLController {

    @FXML private GridPane keyboard;
    @FXML private ListView rotor1;

    private Machine machine;

    protected void handleKeyboardButtonPress(ActionEvent event) {
        Button btn = (Button) event.getSource();
        String key = btn.getText();
        System.out.println(machine.passLetter(key));
    }

    public void initialize() {

        machine = new Machine();

        String[] keys = {
            "QWERTYUIOP",
            "ASDFGHJKL",
            "ZXCVBNM"
        };

        for (int r = 0; r < keys.length; r++){
            for(int c = 0; c < keys[r].length(); c++){
                Button btn = new Button(keys[r].substring(c, c + 1));
                btn.setOnAction(this::handleKeyboardButtonPress);
                keyboard.add(btn, c, r);
            }
        }
    }
}