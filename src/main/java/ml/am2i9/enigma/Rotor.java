package ml.am2i9.enigma;

/**
 * Enigma I
 * Rotor # | ABCDEFGHIJKLMNOPQRSTUVWXYZ
 *    1    | EKMFLGDQVZNTOWYHXUSPAIBRCJ
 *    2	   | AJDKSIRUXBLHWTMCQGZNPYFVOE
 *    3    | BDFHJLCPRTXVZNYEIWGAKMUSQO
 * https://en.wikipedia.org/wiki/Enigma_rotor_details#Rotor_offset
 */
public class Rotor {
    
    private String alphabet;
    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Rotor (String alphabet) {
        this.alphabet = alphabet;
    }

    public boolean isAt(String letter) {
        return alphabet.substring(0, 1).equals(letter);
    }

    public String pass(String letter) {
        int i = alpha.indexOf(letter);
        String c = alphabet.substring(i, i+1);
        return c;
    }

    public String invPass(String letter) {
        int i = alphabet.indexOf(letter);
        String c = alpha.substring(i, i+1);
        return c;
    }

    public void advance() {
        alphabet = alphabet.substring(1) + alphabet.substring(0, 1);
    }
}
