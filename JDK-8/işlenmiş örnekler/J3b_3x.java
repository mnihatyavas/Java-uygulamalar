// J3b_3x.java: Card3 (Vanti3) alt-�rne�i.

import java.io.Serializable;

public class J3b_3x implements Serializable {
    public final static int ATANMADI = -1;
    private int tak�m = ATANMADI;
    private int say� = ATANMADI;

    public final static int KARO = 1;
    public final static int MA�A = 2;
    public final static int KUPA = 3;
    public final static int S�NEK = 4;

    public final static int AS = 1;
    public final static int PAPAZ = 13;

    public J3b_3x (int say�, int tak�m) {
        if (ge�erliSay�M� (say�)) this.say� = say�;
        if (ge�erliTak�mM� (tak�m)) this.tak�m = tak�m;
    } // J3b_3x(..) kurucu sonu...

    public int tak�mAl() {return tak�m;}
    public int say�Al() {return say�;}
    public int k�ymaKodu() {return tak�m * 13 + say�;}
    public String toString() {return tak�mdanDizgeye (this.tak�m) + " " + say�danDizgeye (this.say�);}

    public static boolean ge�erliSay�M� (int say�) {
        if (say� >= AS && say� <= PAPAZ) return true;
        else return false;
    } // ge�erliSay�M�(..) metodu sonu...

    public static boolean ge�erliTak�mM� (int tak�m) {
        if (tak�m >= KARO && tak�m <= S�NEK) return true;
        else return false;
    } // ge�erliTak�mM�(..) metodu sonu...

    public boolean e�ittir (Object nesne) {
        if (nesne instanceof J3b_3x) {
            J3b_3x kart = (J3b_3x)nesne;
            if (kart.say�Al() == this.say� && kart.tak�mAl() == this.tak�m) return true;
            else return false;
        }else return false;
    } // e�ittir(..) metodu sonu...

    public static String say�danDizgeye (int say�) {
        String sonu� = "";
        switch (say�) {
            case AS: sonu� =  "As"; break;
            case 2: sonu� = "�ki"; break;
            case 3: sonu� = "��"; break;
            case 4: sonu� = "D�rt"; break;
            case 5: sonu� = "Be�"; break;
            case 6: sonu� = "Alt�"; break;
            case 7: sonu� = "Yedi"; break;
            case 8: sonu� = "Sekiz"; break;
            case 9: sonu� = "Dokuz"; break;
            case 10: sonu� = "On"; break;
            case 11: sonu� = "O�lan"; break;
            case 12: sonu� = "K�z"; break;
            case PAPAZ: sonu� = "Papaz"; break;
            case ATANMADI: sonu� = "Ge�ersiz Say�";
        } // switch-case blo�u sonu...
        return sonu�;
    } // say�danDizgeye(..) metodu sonu...

    public static String tak�mdanDizgeye (int tak�m) {
        String sonu� = "";
        switch (tak�m) {
            case KARO: sonu� = "Karo"; break;
            case MA�A: sonu� = "Ma�a"; break;
            case KUPA: sonu� = "Kupa"; break;
            case S�NEK: sonu� = "Sinek"; break;
            case ATANMADI: sonu� = "Ge�ersiz Tak�m";
        } // switch-case blo�u sonu...
        return sonu�;
    } // tak�mdanDizgeye(..) metodu sonu...
} // J3b_3x s�n�f� sonu...