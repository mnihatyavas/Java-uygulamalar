// J2e_5x.java: Deck (Deste) alt-örneği.

public class J2e_5x {
    public static int takımAdedi = 4;
    public static int sayıAdedi = 13;
    public static int kartAdedi = takımAdedi * sayıAdedi;
    private J2e_2[][] kartlar; // J2e_2=Card...

    public J2e_5x() {
        kartlar = new J2e_2[takımAdedi][sayıAdedi];
        for (int takım = J2e_2.KARO; takım <= J2e_2.SİNEK; takım++)
            for (int sayı = J2e_2.AS; sayı <= J2e_2.PAPAZ; sayı++)
                kartlar[takım-1][sayı-1] = new J2e_2 (sayı, takım);
    } // J2e_5x() kurucu sonu...

    public J2e_2 kartAl (int takım, int sayı) {return kartlar[takım-1][sayı-1];}
} // J2e_5x sınıfı sonu...