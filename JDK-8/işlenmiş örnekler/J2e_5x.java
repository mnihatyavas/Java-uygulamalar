// J2e_5x.java: Deck (Deste) alt-�rne�i.

public class J2e_5x {
    public static int tak�mAdedi = 4;
    public static int say�Adedi = 13;
    public static int kartAdedi = tak�mAdedi * say�Adedi;
    private J2e_2[][] kartlar; // J2e_2=Card...

    public J2e_5x() {
        kartlar = new J2e_2[tak�mAdedi][say�Adedi];
        for (int tak�m = J2e_2.KARO; tak�m <= J2e_2.S�NEK; tak�m++)
            for (int say� = J2e_2.AS; say� <= J2e_2.PAPAZ; say�++)
                kartlar[tak�m-1][say�-1] = new J2e_2 (say�, tak�m);
    } // J2e_5x() kurucu sonu...

    public J2e_2 kartAl (int tak�m, int say�) {return kartlar[tak�m-1][say�-1];}
} // J2e_5x s�n�f� sonu...