// J3b_3x.java: Card3 (Vanti3) alt-örneði.

import java.io.Serializable;

public class J3b_3x implements Serializable {
    public final static int ATANMADI = -1;
    private int takým = ATANMADI;
    private int sayý = ATANMADI;

    public final static int KARO = 1;
    public final static int MAÇA = 2;
    public final static int KUPA = 3;
    public final static int SÝNEK = 4;

    public final static int AS = 1;
    public final static int PAPAZ = 13;

    public J3b_3x (int sayý, int takým) {
        if (geçerliSayýMý (sayý)) this.sayý = sayý;
        if (geçerliTakýmMý (takým)) this.takým = takým;
    } // J3b_3x(..) kurucu sonu...

    public int takýmAl() {return takým;}
    public int sayýAl() {return sayý;}
    public int kýymaKodu() {return takým * 13 + sayý;}
    public String toString() {return takýmdanDizgeye (this.takým) + " " + sayýdanDizgeye (this.sayý);}

    public static boolean geçerliSayýMý (int sayý) {
        if (sayý >= AS && sayý <= PAPAZ) return true;
        else return false;
    } // geçerliSayýMý(..) metodu sonu...

    public static boolean geçerliTakýmMý (int takým) {
        if (takým >= KARO && takým <= SÝNEK) return true;
        else return false;
    } // geçerliTakýmMý(..) metodu sonu...

    public boolean eþittir (Object nesne) {
        if (nesne instanceof J3b_3x) {
            J3b_3x kart = (J3b_3x)nesne;
            if (kart.sayýAl() == this.sayý && kart.takýmAl() == this.takým) return true;
            else return false;
        }else return false;
    } // eþittir(..) metodu sonu...

    public static String sayýdanDizgeye (int sayý) {
        String sonuç = "";
        switch (sayý) {
            case AS: sonuç =  "As"; break;
            case 2: sonuç = "Ýki"; break;
            case 3: sonuç = "Üç"; break;
            case 4: sonuç = "Dört"; break;
            case 5: sonuç = "Beþ"; break;
            case 6: sonuç = "Altý"; break;
            case 7: sonuç = "Yedi"; break;
            case 8: sonuç = "Sekiz"; break;
            case 9: sonuç = "Dokuz"; break;
            case 10: sonuç = "On"; break;
            case 11: sonuç = "Oðlan"; break;
            case 12: sonuç = "Kýz"; break;
            case PAPAZ: sonuç = "Papaz"; break;
            case ATANMADI: sonuç = "Geçersiz Sayý";
        } // switch-case bloðu sonu...
        return sonuç;
    } // sayýdanDizgeye(..) metodu sonu...

    public static String takýmdanDizgeye (int takým) {
        String sonuç = "";
        switch (takým) {
            case KARO: sonuç = "Karo"; break;
            case MAÇA: sonuç = "Maça"; break;
            case KUPA: sonuç = "Kupa"; break;
            case SÝNEK: sonuç = "Sinek"; break;
            case ATANMADI: sonuç = "Geçersiz Takým";
        } // switch-case bloðu sonu...
        return sonuç;
    } // takýmdanDizgeye(..) metodu sonu...
} // J3b_3x sýnýfý sonu...