package ml.am2i9.enigma;

public class Reflector {

    public static String alphabet = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
    private static String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String relect(String letter) {
        int i = alpha.indexOf(letter);
        return alphabet.substring(i, i+1);
    }
    
}
