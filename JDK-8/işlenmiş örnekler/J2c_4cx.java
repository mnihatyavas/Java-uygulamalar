// J2c_4cx.java: Deck (Deste) alt-örneği.

public class J2c_4cx {
    public static int takımAdeti = 4;
    public static int sayıAdeti = 13;
    public static int kartlarınAdeti = takımAdeti * sayıAdeti;

    private J2c_4a[][] kartlar; // J2c_4a.java=Card...

    public J2c_4cx() {
        kartlar = new J2c_4a[takımAdeti][sayıAdeti];
        for (int takım = J2c_4a.KARO; takım <= J2c_4a.SİNEK; takım++) {
            for (int sayı = J2c_4a.AS; sayı <= J2c_4a.PAPAZ; sayı++) {
                kartlar[takım-1][sayı-1] = new J2c_4a (sayı, takım);
            } // for-sayı döngüsü sonu...
        } // for-takım döngüsü sonu...
    } // J2c_4cx() kurucusu sonu...

    public J2c_4a kartAl (int takım, int sayı) {return kartlar[takım-1][sayı-1];}
} // J2c_4cx sınıfı sonu...