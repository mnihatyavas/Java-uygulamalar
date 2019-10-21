// J2e_2.java: Card (Vanti) örneği.

public class J2e_2 {
    private final int sayı;
    private final int takım;

    // Takım çeşitleri...
    public final static int KARO = 1;
    public final static int MAÇA    = 2;
    public final static int KUPA   = 3;
    public final static int SİNEK   = 4;

    // Sayı çeşitleri...
    public final static int AS   = 1;
    public final static int İKİ = 2;
    public final static int ÜÇ = 3;
    public final static int DÖRT  = 4;
    public final static int BEŞ  = 5;
    public final static int ALTI   = 6;
    public final static int YEDİ = 7;
    public final static int SEKİZ = 8;
    public final static int DOKUZ  = 9;
    public final static int ON   = 10;
    public final static int OĞLAN  = 11;
    public final static int KIZ = 12;
    public final static int PAPAZ  = 13;

    public J2e_2 (int sayı, int takım) {
        assert geçerliSayıMı (sayı);
        assert geçerliTakımMı (takım);
        this.sayı = sayı;
        this.takım = takım;
    } // J2e_2() kurucu sonu...

    public int takımAl() {return takım;}
    public int sayıAl() {return sayı;}
    public static boolean geçerliSayıMı (int sayı) {return AS <= sayı && sayı <= PAPAZ;}
    public static boolean geçerliTakımMı (int takım) {return KARO <= takım && takım <= SİNEK;}

    public static String sayıdanDizgeye (int sayı) {
        switch (sayı) {
            case AS: return "As";
            case İKİ: return "İki";
            case ÜÇ: return "Üç";
            case DÖRT: return "Dört";
            case BEŞ: return "Beş";
            case ALTI: return "Altı";
            case YEDİ: return "Yedi";
            case SEKİZ: return "Sekiz";
            case DOKUZ: return "Dokuz";
            case ON: return "On";
            case OĞLAN: return "Oğlan";
            case KIZ: return "Kız";
            case PAPAZ: return "Papaz";
            default: // Ya null döndürülmeli , ya da istisna fırlatılmalıdır...
                return null;
        } // switch-case bloğu sonu...
    } // sayıdanDizgeye(..) metodu sonu...

    public static String takımdanDizgeye (int takım) {
        switch (takım) {
            case KARO: return "Karo";
            case MAÇA: return "Maça";
            case KUPA: return "Kupa";
            case SİNEK: return "Sinek";
            default: return null;
        } // switch-case bloğu sonu...
    } // takımdanDizgeye(..) metodu sonu...

    public static void main (String[] args) {
        assert sayıdanDizgeye (AS) == "As";
        assert sayıdanDizgeye (İKİ) == "İki";
        assert sayıdanDizgeye (ÜÇ) == "Üç";
        assert sayıdanDizgeye (DÖRT) == "Dört";
        assert sayıdanDizgeye (BEŞ) == "Beş";
        assert sayıdanDizgeye (ALTI) == "Altı";
        assert sayıdanDizgeye (YEDİ) == "Yedi";
        assert sayıdanDizgeye (SEKİZ) == "Sekiz";
        assert sayıdanDizgeye (DOKUZ) == "Dokuz";
        assert sayıdanDizgeye (ON) == "On";
        assert sayıdanDizgeye (OĞLAN) == "Oğlan";
        assert sayıdanDizgeye (KIZ) == "Kız";
        assert sayıdanDizgeye (PAPAZ) == "Papaz";

        assert takımdanDizgeye (KARO) == "Karo";
        assert takımdanDizgeye (MAÇA) == "Maça";
        assert takımdanDizgeye (KUPA) == "Kupa";
        assert takımdanDizgeye (SİNEK) == "Sinek";

        System.out.println ("sayıdanDizgeye(AS) == 'As': [" + (sayıdanDizgeye (AS) == "As") + "]");
        System.out.println ("sayıdanDizgeye(PAPAZ) == 'Papaz': [" + (sayıdanDizgeye (PAPAZ) == "Papaz") + "]");
        System.out.println ("takımdanDizgeye(KARO) == 'Karo': [" + (takımdanDizgeye (KARO) == "Karo") + "]");
        System.out.println ("takımdanDizgeye(SİNEK) == 'Sinek': [" + (takımdanDizgeye (SİNEK) == "Sinek") + "]");
    } // main(..) metodu sonu...
} // J2e_2 sınıfı sonu...

/* Çıktı: assert ifadesini kullanabilmek için ">java -ea J2e_2" -ea bayraklı koşturulmalıdır. Dese de inanma!
**  >java J2e_2  **
sayıdanDizgeye(AS) == 'As': [true]
sayıdanDizgeye(PAPAZ) == 'Papaz': [true]
takımdanDizgeye(KARO) == 'Karo': [true]
takımdanDizgeye(SİNEK) == 'Sinek': [true]
*/