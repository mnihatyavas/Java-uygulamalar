// J2e_3.java: Card2 (Vanti2) örneði.

public class J2e_3 {
    private int sayý;
    private int takým;

    // Takým çeþitleri...
    public final static int KARO = 1;
    public final static int MAÇA    = 2;
    public final static int KUPA   = 3;
    public final static int SÝNEK   = 4;

    // Sayý çeþitleri...
    public final static int AS   = 1;
    public final static int ÝKÝ = 2;
    public final static int ÜÇ = 3;
    public final static int DÖRT  = 4;
    public final static int BEÞ  = 5;
    public final static int ALTI   = 6;
    public final static int YEDÝ = 7;
    public final static int SEKÝZ = 8;
    public final static int DOKUZ  = 9;
    public final static int ON   = 10;
    public final static int OÐLAN  = 11;
    public final static int KIZ = 12;
    public final static int PAPAZ  = 13;

    public J2e_3 (int sayý, int takým) {
        this.sayý = sayý;
        this.takým = takým;
    } // J2e_3() kurucu sonu...

    public int takýmAl() {return takým;}
    public int sayýAl() {return sayý;}
    public static boolean geçerliSayýMý (int sayý) {return AS <= sayý && sayý <= PAPAZ;}
    public static boolean geçerliTakýmMý (int takým) {return KARO <= takým && takým <= SÝNEK;}
    public int kýymaKodu() {return ((takým - 1) * 13) + sayý;}
    public String toString() {return takýmdanDizgeye (this.takým) + sayýdanDizgeye (this.sayý);}

    public static String sayýdanDizgeye (int sayý) {
        switch (sayý) {
            case AS: return "As";
            case ÝKÝ: return "Ýki";
            case ÜÇ: return "Üç";
            case DÖRT: return "Dört";
            case BEÞ: return "Beþ";
            case ALTI: return "Altý";
            case YEDÝ: return "Yedi";
            case SEKÝZ: return "Sekiz";
            case DOKUZ: return "Dokuz";
            case ON: return "On";
            case OÐLAN: return "Oðlan";
            case KIZ: return "Kýz";
            case PAPAZ: return "Papaz";
            default: // Ya null döndürülmeli , ya da istisna fýrlatýlmalýdýr...
                throw new IllegalArgumentException ("Geçersiz sayý: " + sayý);
        } // switch-case bloðu sonu...
    } // sayýdanDizgeye(..) metodu sonu...

    public static String takýmdanDizgeye (int takým) {
        switch (takým) {
            case KARO: return "Karo";
            case MAÇA: return "Maça";
            case KUPA: return "Kupa";
            case SÝNEK: return "Sinek";
            default: throw new IllegalArgumentException ("Geçersiz takým: " + takým);
        } // switch-case bloðu sonu...
    } // takýmdanDizgeye(..) metodu sonu...

    public boolean eþittir (Object nesne) {
        if (nesne instanceof J2e_3) {
            if (((J2e_3)nesne).sayýAl() == this.sayý && ((J2e_3)nesne).takýmAl() == this.takým) return true;
            else return false;
        }else return false;
    } // eþittir(..) nesnesi sonu...

    public static void main (String... args) {
        System.out.println ("Karo As'ý: [" + new J2e_3 (AS, KARO).kýymaKodu() + "]");
        System.out.println ("Maça Oðlan'ý: [" + new J2e_3 (OÐLAN, MAÇA).kýymaKodu() + "]");
        System.out.println ("Kupa Beþ'i: [" + new J2e_3 (BEÞ, KUPA).kýymaKodu() + "]");
        System.out.println ("Sinek Papaz'ý: [" + new J2e_3 (PAPAZ, SÝNEK).kýymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2e_3 sýnýfý sonu...

/* Çýktý:
C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama>java J2e_3
Karo As'ý: [1]
Maça Oðlan'ý: [24]
Kupa Beþ'i: [31]
Sinek Papaz'ý: [52]
*/