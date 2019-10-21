// J2c_4a.java: Card (Vanti) örneği.

public class J2c_4a {
    private final int sayı; // final değişmez değerler...
    private final int takım;

    // 4 takım ve değerleri...
    public final static int KARO = 1;
    public final static int MAÇA    = 2;
    public final static int KUPA   = 3;
    public final static int SİNEK   = 4;

    // Herbir takımda 14 [1->13] kart sayısı...
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

    public J2c_4a (int sayı, int takım) {// Sınıf kurucusu...
        assert geçerliSayıMı (sayı);
        assert geçerliTakımMı (takım);
        this.sayı = sayı; // tip değişkenlerine ata...
        this.takım = takım;
    } // J2c_4a() kurucusu sonu...

    public int takımAl() {return takım;}
    public int sayıAl() {return sayı;}
    public static boolean geçerliSayıMı (int sayı) {return ((AS <= sayı) && (sayı <= PAPAZ));}
    public static boolean geçerliTakımMı (int takım) {return ((KARO <= takım) && (takım <= SİNEK));}

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
            default: return null; // Ya catch ile Exception/istisna yönetimi yapılmalı ya da null atanmalı...
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
        // assert ifadeleri için program (java -ea ..) bayraklı koşturulmalıdır...
        System.out.println ("sayıdanDizgeye (AS) == \"As\": [" + (sayıdanDizgeye (AS) == "As") + "]");
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

        System.out.println ("takımdanDizgeye (KARO) == \"Karo\": [" + (takımdanDizgeye (KARO) == "Karo") + "]");
        assert takımdanDizgeye (MAÇA) == "Maça";
        assert takımdanDizgeye (KUPA) == "Kupa";
        assert takımdanDizgeye (SİNEK) == "Sinek";
    } // main(..) metodu sonu...
} // J2c_4a sınıfı sonu...

/* Çıktı:
**  >java -ea J2c_4a  **
sayıdanDizgeye (AS) == "As": [true]
takımdanDizgeye (KARO) == "Karo": [true]
*/