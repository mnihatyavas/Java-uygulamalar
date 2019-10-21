// J2c_4dx.java: Deck2 (Deste2) alt-örneği.

public class J2c_4dx {
    public static int takımAdeti = 4;
    public static int sayıAdeti = 13;
    public static int kartAdeti = takımAdeti * sayıAdeti;

    private J2c_4b[][] kartlar; // J2c_4b.java=Card2...

    public J2c_4dx() {
        kartlar = new J2c_4b[takımAdeti][sayıAdeti];
        for (int takım = J2c_4b.KARO; takım <= J2c_4b.SİNEK; takım++) {
            for (int sayı = J2c_4b.AS; sayı <= J2c_4b.PAPAZ; sayı++) {
                kartlar[takım-1][sayı-1] = new J2c_4b (sayı, takım);
            } // for-sayı döngüsü sonu...
        } // for-takım döngüsü sonu...
    } // J2c_4dx() kurucusu sonu...

    public J2c_4b kartAl (int takım, int sayı) {return kartlar[takım-1][sayı-1];}
} // J2c_4dx sınıfı sonu...