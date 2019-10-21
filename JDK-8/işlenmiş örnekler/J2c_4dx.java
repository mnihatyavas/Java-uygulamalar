// J2c_4dx.java: Deck2 (Deste2) alt-�rne�i.

public class J2c_4dx {
    public static int tak�mAdeti = 4;
    public static int say�Adeti = 13;
    public static int kartAdeti = tak�mAdeti * say�Adeti;

    private J2c_4b[][] kartlar; // J2c_4b.java=Card2...

    public J2c_4dx() {
        kartlar = new J2c_4b[tak�mAdeti][say�Adeti];
        for (int tak�m = J2c_4b.KARO; tak�m <= J2c_4b.S�NEK; tak�m++) {
            for (int say� = J2c_4b.AS; say� <= J2c_4b.PAPAZ; say�++) {
                kartlar[tak�m-1][say�-1] = new J2c_4b (say�, tak�m);
            } // for-say� d�ng�s� sonu...
        } // for-tak�m d�ng�s� sonu...
    } // J2c_4dx() kurucusu sonu...

    public J2c_4b kartAl (int tak�m, int say�) {return kartlar[tak�m-1][say�-1];}
} // J2c_4dx s�n�f� sonu...