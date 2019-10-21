// J2c_14.java: Planet (Gezegen) �rne�i.

public enum J2c_14 {
    MERK�R (3.303e+23, 2.4397e6), // Gezegenin k�tlesi/kg ve yar��ap�/m...
    VEN�S   (4.869e+24, 6.0518e6),
    D�NYA   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    J�P�TER (1.9e+27,   7.1492e7), // K�tleler ve yar��aplar ilk 4 grupla son 4 grup birbirlerine yak�n...
    SAT�RN  (5.688e+26, 6.0268e7),
    �RAN�S  (8.686e+25, 2.5559e7),
    NEPT�N (1.024e+26, 2.4746e7);

    private final double k�tle; // kilogram...
    private final double yar��ap; // metre...

    J2c_14 (double k�tle, double yar��ap) {
        this.k�tle = k�tle;
        this.yar��ap = yar��ap;
    } // J2c_14(..) kurucusu sonu...

    private double k�tle() {return k�tle;}
    private double yar��ap() {return yar��ap;}

    // Evrensel yer�ekimi sabiti: m^3/(kg*s^2)
    public static final double G = 6.67300E-11;

    double y�zeyYer�ekimi() {return G * k�tle / (yar��ap * yar��ap);}
    double y�zeyA��rl��� (double di�erK�tle) {return di�erK�tle * y�zeyYer�ekimi();}

    public static void main (String[] args) {
        if (args.length != 1) {System.err.println ("D�nyadaki a��rl��� girmeyi unuttunuz!"); System.exit (-1);}

        double d�nyadakiA��rl�k;
        try {d�nyadakiA��rl�k = Double.parseDouble (args[0]);} catch (Exception ist) {d�nyadakiA��rl�k = 65;}
        double k�tlen = d�nyadakiA��rl�k / D�NYA.y�zeyYer�ekimi();

        for (J2c_14 k: J2c_14.values()) System.out.printf ("%s gezegendeki a��rl���n: [%f] kg'd�r.%n", k, k.y�zeyA��rl��� (k�tlen));
    } // main(..) metodu sonu...
} // J2c_14 s�n�f� sonu...

/* ��kt�:
**  >java J2c_14  **
D�nyadaki a��rl��� girmeyi unuttunuz!

**  >java J2c_14 xx  ** TEKRAR
MERK�R gezegendeki a��rl���n: [24,554245] kg'd�r.
VEN�S gezegendeki a��rl���n: [58,824941] kg'd�r.
D�NYA gezegendeki a��rl���n: [65,000000] kg'd�r.
MARS gezegendeki a��rl���n: [24,617917] kg'd�r.
J�P�TER gezegendeki a��rl���n: [164,486239] kg'd�r.
SAT�RN gezegendeki a��rl���n: [69,291010] kg'd�r.
�RAN�S gezegendeki a��rl���n: [58,833268] kg'd�r.
NEPT�N gezegendeki a��rl���n: [73,991325] kg'd�r.

**  >java J2c_14 100  ** TEKRAR
MERK�R gezegendeki a��rl���n: [37,775762] kg'd�r.
VEN�S gezegendeki a��rl���n: [90,499910] kg'd�r.
D�NYA gezegendeki a��rl���n: [100,000000] kg'd�r.
MARS gezegendeki a��rl���n: [37,873718] kg'd�r.
J�P�TER gezegendeki a��rl���n: [253,055753] kg'd�r.
SAT�RN gezegendeki a��rl���n: [106,601554] kg'd�r.
�RAN�S gezegendeki a��rl���n: [90,512720] kg'd�r.
NEPT�N gezegendeki a��rl���n: [113,832807] kg'd�r.
*/