package ml.am2i9.enigma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Plugboard {
    public ObservableList<Plug> plugs;

    public Plugboard(){
        this.plugs = FXCollections.observableArrayList();
    }

    public String passLetter(String letter) {
        for (Plug plug : plugs) {
            if (plug.connectsWith(letter)) {
                return plug.getOpposite(letter);
            }
        }
        return letter;
    }

    public void addPlug(String a, String b) {
        addPlug(new Plug(a, b));
    }

    public void addPlug(Plug p) {
        plugs.add(p);
    }

    public void removePlug(int i) {
        plugs.remove(i);
    }

    public boolean isPlugged(String l) {
        for (Plug plug: plugs) {
            if (plug.connectsWith(l)) {
                return true;
            }
        }
        return false;
    }
}
