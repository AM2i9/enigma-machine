package ml.am2i9.enigma;

public class Plug {
    
    final String a;
    final String b;

    public Plug(String a, String b) {
        this.a = a;
        this.b = b;
    }

    public String getOpposite(String l) {
        if (l.equals(a)) {
            return b;
        } else {
            return a;
        }
    }

    public boolean connectsWith(String l) {
        return l.equals(this.a) || l.equals(this.b);
    }

    public boolean equals(Plug other) {
        return other.a.equals(this.a) && other.b.equals(this.b);
    }

    public String toString() {
        return this.a + " \u2B0C " + this.b;
    }
}
