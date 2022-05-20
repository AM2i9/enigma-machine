package ml.am2i9.enigma;

import java.util.ArrayList;

public class Plugboard {
    private ArrayList<Plug> plugs = new ArrayList<Plug>();

    public Plugboard(){}

    public String passLetter(String letter) {
        for (Plug plug : plugs) {
            if (plug.equals(letter)) {
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
}
