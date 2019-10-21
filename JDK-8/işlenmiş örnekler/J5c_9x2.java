// J5c_9x2.java: Unit (Birim) alt-örneði.

public class J5c_9x2 {
    String izah;
    double çarpan;

    J5c_9x2 (String izah, double çarpan) {
        super();
        this.izah = izah;
        this.çarpan = çarpan;
    } // J5c_9x2() kurucusu sonu...

    public String toString() {
        String s = "Metre / " + izah + " = " + çarpan;
        return s;
    } // toString() override metodu sonu...
} // J5c_9x2 sýnýfý sonu...