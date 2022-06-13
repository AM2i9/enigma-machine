package ml.am2i9.enigma;

public class Machine {
    // yeah might be bad
    private Rotor left;
    private Rotor mid;
    private Rotor right;
    private Plugboard plugboard;

    public Machine() {
        left = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        mid = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        right = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
        plugboard = new Plugboard();
    }

    public String passLetter(String letter) {
        String plugged = plugboard.passLetter(letter);
        String one = right.pass(plugged);
        String two = mid.pass(one);
        String three = left.pass(two);
        String refed = Reflector.relect(three);
        String four = left.invPass(refed);
        String five = mid.invPass(four);
        String six = right.invPass(five);
        plugged = plugboard.passLetter(six);
        right.advance();
        if (right.isAt("W")) {
            mid.advance();
            if (mid.isAt("F")) {
                left.advance();
            }
        }
        return plugged;
    }

    public Rotor getLeftRotor() {
        return left;
    }

    public Rotor getMiddleRotor() {
        return mid;
    }

    public Rotor getRightRotor() {
        return right;
    }

    public Plugboard getPlugboard() {
        return plugboard;
    }
}
