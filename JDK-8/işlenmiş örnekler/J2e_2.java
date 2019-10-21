// J2e_2.java: Card (Vanti) �rne�i.

public class J2e_2 {
    private final int say�;
    private final int tak�m;

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

    public J2e_2 (int say�, int tak�m) {
        assert ge�erliSay�M� (say�);
        assert ge�erliTak�mM� (tak�m);
        this.say� = say�;
        this.tak�m = tak�m;
    } // J2e_2() kurucu sonu...

    public int tak�mAl() {return tak�m;}
    public int say�Al() {return say�;}
    public static boolean ge�erliSay�M� (int say�) {return AS <= say� && say� <= PAPAZ;}
    public static boolean ge�erliTak�mM� (int tak�m) {return KARO <= tak�m && tak�m <= S�NEK;}

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
                return null;
        } // switch-case blo�u sonu...
    } // say�danDizgeye(..) metodu sonu...

    public static String tak�mdanDizgeye (int tak�m) {
        switch (tak�m) {
            case KARO: return "Karo";
            case MA�A: return "Ma�a";
            case KUPA: return "Kupa";
            case S�NEK: return "Sinek";
            default: return null;
        } // switch-case blo�u sonu...
    } // tak�mdanDizgeye(..) metodu sonu...

    public static void main (String[] args) {
        assert say�danDizgeye (AS) == "As";
        assert say�danDizgeye (�K�) == "�ki";
        assert say�danDizgeye (��) == "��";
        assert say�danDizgeye (D�RT) == "D�rt";
        assert say�danDizgeye (BE�) == "Be�";
        assert say�danDizgeye (ALTI) == "Alt�";
        assert say�danDizgeye (YED�) == "Yedi";
        assert say�danDizgeye (SEK�Z) == "Sekiz";
        assert say�danDizgeye (DOKUZ) == "Dokuz";
        assert say�danDizgeye (ON) == "On";
        assert say�danDizgeye (O�LAN) == "O�lan";
        assert say�danDizgeye (KIZ) == "K�z";
        assert say�danDizgeye (PAPAZ) == "Papaz";

        assert tak�mdanDizgeye (KARO) == "Karo";
        assert tak�mdanDizgeye (MA�A) == "Ma�a";
        assert tak�mdanDizgeye (KUPA) == "Kupa";
        assert tak�mdanDizgeye (S�NEK) == "Sinek";

        System.out.println ("say�danDizgeye(AS) == 'As': [" + (say�danDizgeye (AS) == "As") + "]");
        System.out.println ("say�danDizgeye(PAPAZ) == 'Papaz': [" + (say�danDizgeye (PAPAZ) == "Papaz") + "]");
        System.out.println ("tak�mdanDizgeye(KARO) == 'Karo': [" + (tak�mdanDizgeye (KARO) == "Karo") + "]");
        System.out.println ("tak�mdanDizgeye(S�NEK) == 'Sinek': [" + (tak�mdanDizgeye (S�NEK) == "Sinek") + "]");
    } // main(..) metodu sonu...
} // J2e_2 s�n�f� sonu...

/* ��kt�: assert ifadesini kullanabilmek i�in ">java -ea J2e_2" -ea bayrakl� ko�turulmal�d�r. Dese de inanma!
**  >java J2e_2  **
say�danDizgeye(AS) == 'As': [true]
say�danDizgeye(PAPAZ) == 'Papaz': [true]
tak�mdanDizgeye(KARO) == 'Karo': [true]
tak�mdanDizgeye(S�NEK) == 'Sinek': [true]
*/