// J2c_4b.java: Card2 (Vanti2) �rne�i.

public class J2c_4b {
    private int say�;
    private int tak�m;

    // 4 tak�m ve de�erleri...
    public final static int KARO = 1; // final de�i�mez de�erler...
    public final static int MA�A    = 2;
    public final static int KUPA   = 3;
    public final static int S�NEK   = 4;

    // Herbir tak�mda 14 [1->13] kart say�s�...
    public final static int AS   = 1;
    public final static int �K� = 2;
    public final static int �� = 3;
    public final static int D�RT  = 4;
    public final static int BE�  = 5;
    public final static int ALTI   = 6;
    public final static int YED� = 7;
    public final static int SEK�Z = 8;
    public final static int DOKUZ  = 9;
    public final static int ON   = 10;
    public final static int O�LAN  = 11;
    public final static int KIZ = 12;
    public final static int PAPAZ  = 13;

    public J2c_4b (int say�, int tak�m) {// S�n�f kurucusu...
        this.say� = say�; // tip de�i�kenlerine ata...
        this.tak�m = tak�m;
    } // J2c_4b() kurucusu sonu...

    public int tak�mAl() {return tak�m;}
    public int say�Al() {return say�;}
    public static boolean ge�erliSay�M� (int say�) {return ((AS <= say�) && (say� <= PAPAZ));}
    public static boolean ge�erliTak�mM� (int tak�m) {return ((KARO <= tak�m) && (tak�m <= S�NEK));}
    public int k�ymaKodu() {return (((tak�m - 1) * 13) + say�);}
    public String toString() {return (say�danDizgeye (this.say�) + " n�n " + tak�mdanDizgeye (this.tak�m));}

    public boolean e�ittir (Object nesne) {
        if (nesne instanceof J2c_4b) {
            if (((J2c_4b)nesne).say�Al() == this.say� &&
                ((J2c_4b)nesne).tak�mAl() == this.tak�m) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    } // e�ittir(..) metodu sonu...

    public static String say�danDizgeye (int say�) {
        switch (say�) {
            case AS: return "As";
            case �K�: return "�ki";
            case ��: return "��";
            case D�RT: return "D�rt";
            case BE�: return "Be�";
            case ALTI: return "Alt�";
            case YED�: return "Yedi";
            case SEK�Z: return "Sekiz";
            case DOKUZ: return "Dokuz";
            case ON: return "On";
            case O�LAN: return "O�lan";
            case KIZ: return "K�z";
            case PAPAZ: return "Papaz";
            default: return null; // Ya catch ile Exception/istisna y�netimi yap�lmal� ya da null atanmal�...
        } // switch-case blo�u sonu...
    } // say�danDizgeye(..) metodu sonu...
    
    public static String tak�mdanDizgeye (int tak�m) {
        switch (tak�m) {
            case KARO: return "Karo";
            case MA�A: return "Ma�a";
            case KUPA: return "Kupa";
            case S�NEK: return "Sinek";
            default: throw new IllegalArgumentException ("Ge�ersiz tak�m: " + tak�m); //return null;
        } // switch-case blo�u sonu...
    } // tak�mdanDizgeye(..) metodu sonu...

    public static void main (String[] args) {
        System.out.println ("Karo As�: [" + new J2c_4b (AS, KARO).k�ymaKodu() + "]");
        System.out.println ("Ma�a Yedilisi: [" + new J2c_4b (YED�, MA�A).k�ymaKodu() + "]");
        System.out.println ("Kupa K�z�: [" + new J2c_4b (KIZ, KUPA).k�ymaKodu() + "]");
        System.out.println ("Sinek Papaz�: [" + new J2c_4b (PAPAZ, S�NEK).k�ymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2c_4b s�n�f� sonu...

/* ��kt�:
**  >java J2c_4b  **
Karo As�: [1]
Ma�a Yedilisi: [20]
Kupa K�z�: [38]
Sinek Papaz�: [52]
*/