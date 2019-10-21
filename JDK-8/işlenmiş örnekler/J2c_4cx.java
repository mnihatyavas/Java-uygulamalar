// J2c_4cx.java: Deck (Deste) alt-�rne�i.

public class J2c_4cx {
    public static int tak�mAdeti = 4;
    public static int say�Adeti = 13;
    public static int kartlar�nAdeti = tak�mAdeti * say�Adeti;

    private J2c_4a[][] kartlar; // J2c_4a.java=Card...

    public J2c_4cx() {
        kartlar = new J2c_4a[tak�mAdeti][say�Adeti];
        for (int tak�m = J2c_4a.KARO; tak�m <= J2c_4a.S�NEK; tak�m++) {
            for (int say� = J2c_4a.AS; say� <= J2c_4a.PAPAZ; say�++) {
                kartlar[tak�m-1][say�-1] = new J2c_4a (say�, tak�m);
            } // for-say� d�ng�s� sonu...
        } // for-tak�m d�ng�s� sonu...
    } // J2c_4cx() kurucusu sonu...

    public J2c_4a kartAl (int tak�m, int say�) {return kartlar[tak�m-1][say�-1];}
} // J2c_4cx s�n�f� sonu...