// J2c_14.java: Planet (Gezegen) örneði.

public enum J2c_14 {
    MERKÜR (3.303e+23, 2.4397e6), // Gezegenin kütlesi/kg ve yarýçapý/m...
    VENÜS   (4.869e+24, 6.0518e6),
    DÜNYA   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JÜPÝTER (1.9e+27,   7.1492e7), // Kütleler ve yarýçaplar ilk 4 grupla son 4 grup birbirlerine yakýn...
    SATÜRN  (5.688e+26, 6.0268e7),
    ÜRANÜS  (8.686e+25, 2.5559e7),
    NEPTÜN (1.024e+26, 2.4746e7);

    private final double kütle; // kilogram...
    private final double yarýçap; // metre...

    J2c_14 (double kütle, double yarýçap) {
        this.kütle = kütle;
        this.yarýçap = yarýçap;
    } // J2c_14(..) kurucusu sonu...

    private double kütle() {return kütle;}
    private double yarýçap() {return yarýçap;}

    // Evrensel yerçekimi sabiti: m^3/(kg*s^2)
    public static final double G = 6.67300E-11;

    double yüzeyYerçekimi() {return G * kütle / (yarýçap * yarýçap);}
    double yüzeyAðýrlýðý (double diðerKütle) {return diðerKütle * yüzeyYerçekimi();}

    public static void main (String[] args) {
        if (args.length != 1) {System.err.println ("Dünyadaki aðýrlýðý girmeyi unuttunuz!"); System.exit (-1);}

        double dünyadakiAðýrlýk;
        try {dünyadakiAðýrlýk = Double.parseDouble (args[0]);} catch (Exception ist) {dünyadakiAðýrlýk = 65;}
        double kütlen = dünyadakiAðýrlýk / DÜNYA.yüzeyYerçekimi();

        for (J2c_14 k: J2c_14.values()) System.out.printf ("%s gezegendeki aðýrlýðýn: [%f] kg'dýr.%n", k, k.yüzeyAðýrlýðý (kütlen));
    } // main(..) metodu sonu...
} // J2c_14 sýnýfý sonu...

/* Çýktý:
**  >java J2c_14  **
Dünyadaki aðýrlýðý girmeyi unuttunuz!

**  >java J2c_14 xx  ** TEKRAR
MERKÜR gezegendeki aðýrlýðýn: [24,554245] kg'dýr.
VENÜS gezegendeki aðýrlýðýn: [58,824941] kg'dýr.
DÜNYA gezegendeki aðýrlýðýn: [65,000000] kg'dýr.
MARS gezegendeki aðýrlýðýn: [24,617917] kg'dýr.
JÜPÝTER gezegendeki aðýrlýðýn: [164,486239] kg'dýr.
SATÜRN gezegendeki aðýrlýðýn: [69,291010] kg'dýr.
ÜRANÜS gezegendeki aðýrlýðýn: [58,833268] kg'dýr.
NEPTÜN gezegendeki aðýrlýðýn: [73,991325] kg'dýr.

**  >java J2c_14 100  ** TEKRAR
MERKÜR gezegendeki aðýrlýðýn: [37,775762] kg'dýr.
VENÜS gezegendeki aðýrlýðýn: [90,499910] kg'dýr.
DÜNYA gezegendeki aðýrlýðýn: [100,000000] kg'dýr.
MARS gezegendeki aðýrlýðýn: [37,873718] kg'dýr.
JÜPÝTER gezegendeki aðýrlýðýn: [253,055753] kg'dýr.
SATÜRN gezegendeki aðýrlýðýn: [106,601554] kg'dýr.
ÜRANÜS gezegendeki aðýrlýðýn: [90,512720] kg'dýr.
NEPTÜN gezegendeki aðýrlýðýn: [113,832807] kg'dýr.
*/