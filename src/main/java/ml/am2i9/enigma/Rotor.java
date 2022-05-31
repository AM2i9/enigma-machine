package ml.am2i9.enigma;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Enigma I
 * Rotor # | ABCDEFGHIJKLMNOPQRSTUVWXYZ
 *    1    | EKMFLGDQVZNTOWYHXUSPAIBRCJ
 *    2	   | AJDKSIRUXBLHWTMCQGZNPYFVOE
 *    3    | BDFHJLCPRTXVZNYEIWGAKMUSQO
 * https://en.wikipedia.org/wiki/Enigma_rotor_details#Rotor_offset
 */
public class Rotor {
    
    public ObservableList<String> alphabet;
    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Rotor (String alphabet) {
        this.alphabet = FXCollections.observableArrayList();
        for (char ch: alphabet.toCharArray()) {
            this.alphabet.add(String.valueOf(ch));
        }
    }

    public boolean isAt(String letter) {
        return alphabet.get(0).equals(letter);
    }

    public String pass(String letter) {
        int i = alpha.indexOf(letter);
        String c = alphabet.get(i);
        return c;
    }

    public String invPass(String letter) {
        int i = alphabet.indexOf(letter);
        String c = alpha.substring(i, i+1);
        return c;
    }

    public void advance() {
        alphabet.add(alphabet.get(0));
        alphabet.remove(0);
    }

    public void advanceBack() {
        int len = alphabet.size();
        alphabet.add(0, alphabet.get(len - 1));
        alphabet.remove(len);
    }
}
