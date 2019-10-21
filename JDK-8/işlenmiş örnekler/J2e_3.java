// J2e_3.java: Card2 (Vanti2) �rne�i.

public class J2e_3 {
    private int say�;
    private int tak�m;

    // Tak�m �e�itleri...
    public final static int KARO = 1;
    public final static int MA�A    = 2;
    public final static int KUPA   = 3;
    public final static int S�NEK   = 4;

    // Say� �e�itleri...
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

    public J2e_3 (int say�, int tak�m) {
        this.say� = say�;
        this.tak�m = tak�m;
    } // J2e_3() kurucu sonu...

    public int tak�mAl() {return tak�m;}
    public int say�Al() {return say�;}
    public static boolean ge�erliSay�M� (int say�) {return AS <= say� && say� <= PAPAZ;}
    public static boolean ge�erliTak�mM� (int tak�m) {return KARO <= tak�m && tak�m <= S�NEK;}
    public int k�ymaKodu() {return ((tak�m - 1) * 13) + say�;}
    public String toString() {return tak�mdanDizgeye (this.tak�m) + say�danDizgeye (this.say�);}

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
            default: // Ya null d�nd�r�lmeli , ya da istisna f�rlat�lmal�d�r...
                throw new IllegalArgumentException ("Ge�ersiz say�: " + say�);
        } // switch-case blo�u sonu...
    } // say�danDizgeye(..) metodu sonu...

    public static String tak�mdanDizgeye (int tak�m) {
        switch (tak�m) {
            case KARO: return "Karo";
            case MA�A: return "Ma�a";
            case KUPA: return "Kupa";
            case S�NEK: return "Sinek";
            default: throw new IllegalArgumentException ("Ge�ersiz tak�m: " + tak�m);
        } // switch-case blo�u sonu...
    } // tak�mdanDizgeye(..) metodu sonu...

    public boolean e�ittir (Object nesne) {
        if (nesne instanceof J2e_3) {
            if (((J2e_3)nesne).say�Al() == this.say� && ((J2e_3)nesne).tak�mAl() == this.tak�m) return true;
            else return false;
        }else return false;
    } // e�ittir(..) nesnesi sonu...

    public static void main (String... args) {
        System.out.println ("Karo As'�: [" + new J2e_3 (AS, KARO).k�ymaKodu() + "]");
        System.out.println ("Ma�a O�lan'�: [" + new J2e_3 (O�LAN, MA�A).k�ymaKodu() + "]");
        System.out.println ("Kupa Be�'i: [" + new J2e_3 (BE�, KUPA).k�ymaKodu() + "]");
        System.out.println ("Sinek Papaz'�: [" + new J2e_3 (PAPAZ, S�NEK).k�ymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2e_3 s�n�f� sonu...

/* ��kt�:
C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama>java J2e_3
Karo As'�: [1]
Ma�a O�lan'�: [24]
Kupa Be�'i: [31]
Sinek Papaz'�: [52]
*/