package ml.am2i9.enigma;

public class Machine {
    Rotor left;
    Rotor mid;
    Rotor right;

    public Machine() {
        left = new Rotor("EKMFLGDQVZNTOWYHXUSPAIBRCJ");
        mid = new Rotor("AJDKSIRUXBLHWTMCQGZNPYFVOE");
        right = new Rotor("BDFHJLCPRTXVZNYEIWGAKMUSQO");
    }

    public void run() {
        String message = "GG";
        for (int i = 0; i < message.length(); i++) {
            String one = right.pass(message.substring(i, i+1));
            String two = mid.pass(one);
            String three = left.pass(two);
            String refed = Reflector.relect(three);
            String four = left.invPass(refed);
            String five = mid.invPass(four);
            String six = right.invPass(five);
            right.advance();
            if (right.isAt("W")) {
                mid.advance();
                if (mid.isAt("F")) {
                    left.advance();
                }
            }
            System.out.print(six);
        }
    }
}
