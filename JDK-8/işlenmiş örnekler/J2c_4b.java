// J2c_4b.java: Card2 (Vanti2) örneği.

public class J2c_4b {
    private int sayı;
    private int takım;

    // 4 takım ve değerleri...
    public final static int KARO = 1; // final değişmez değerler...
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

    public J2c_4b (int sayı, int takım) {// Sınıf kurucusu...
        this.sayı = sayı; // tip değişkenlerine ata...
        this.takım = takım;
    } // J2c_4b() kurucusu sonu...

    public int takımAl() {return takım;}
    public int sayıAl() {return sayı;}
    public static boolean geçerliSayıMı (int sayı) {return ((AS <= sayı) && (sayı <= PAPAZ));}
    public static boolean geçerliTakımMı (int takım) {return ((KARO <= takım) && (takım <= SİNEK));}
    public int kıymaKodu() {return (((takım - 1) * 13) + sayı);}
    public String toString() {return (sayıdanDizgeye (this.sayı) + " nın " + takımdanDizgeye (this.takım));}

    public boolean eşittir (Object nesne) {
        if (nesne instanceof J2c_4b) {
            if (((J2c_4b)nesne).sayıAl() == this.sayı &&
                ((J2c_4b)nesne).takımAl() == this.takım) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } // eşittir(..) metodu sonu...

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
            default: throw new IllegalArgumentException ("Geçersiz takım: " + takım); //return null;
        } // switch-case bloğu sonu...
    } // takımdanDizgeye(..) metodu sonu...

    public static void main (String[] args) {
        System.out.println ("Karo Ası: [" + new J2c_4b (AS, KARO).kıymaKodu() + "]");
        System.out.println ("Maça Yedilisi: [" + new J2c_4b (YEDİ, MAÇA).kıymaKodu() + "]");
        System.out.println ("Kupa Kızı: [" + new J2c_4b (KIZ, KUPA).kıymaKodu() + "]");
        System.out.println ("Sinek Papazı: [" + new J2c_4b (PAPAZ, SİNEK).kıymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2c_4b sınıfı sonu...

/* Çıktı:
**  >java J2c_4b  **
Karo Ası: [1]
Maça Yedilisi: [20]
Kupa Kızı: [38]
Sinek Papazı: [52]
*/