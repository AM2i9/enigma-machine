package ml.am2i9.enigma;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLController {

    @FXML private GridPane keyboard;
    @FXML private ListView<String> rotorl;
    @FXML private ListView<String> rotorm;
    @FXML private ListView<String> rotorr;

    @FXML private ListView<Plug> plugboardlist;

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
        System.out.println(machine.passLetter(key));
        event.consume();
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

        rotorl.setItems(machine.left.alphabet);
        rotorm.setItems(machine.mid.alphabet);
        rotorr.setItems(machine.right.alphabet);

        plugboardlist.setItems(machine.plugboard.plugs);
    }
}