// J2e_6x3.java: PlayingCard (OyunKart�) alt-�rne�i.

public class J2e_6x3 implements J2e_6x1 {// J2e_6x1=J2e_6x1/Kart aray�z�...
    private J2e_6x1.Say� say�;
    private J2e_6x1.Tak�m tak�m;

    public J2e_6x3 (J2e_6x1.Say� say�, J2e_6x1.Tak�m tak�m) {
        this.say� = say�;
        this.tak�m = tak�m;
    } // J2e_6x3(..) kurucu sonu...

    public J2e_6x1.Tak�m tak�mAl() {return tak�m;}
    public J2e_6x1.Say� say�Al() {return say�;}
    public int k�ymaKodu() {return ((tak�m.de�erAl() - 1) * 13) + say�.de�erAl();}
    public int compareTo (J2e_6x1 nesne) {return this.k�ymaKodu() - nesne.k�ymaKodu();}
    public String toString() {return this.tak�m.metinAl() + " " + this.say�.metinAl();}

    public boolean e�ittir (Object nesne) {
        if (nesne instanceof J2e_6x1)
            if (((J2e_6x1)nesne).say�Al() == this.say� &&
                ((J2e_6x1)nesne).tak�mAl() == this.tak�m) return true;
            else return false;
        else return false;
    } // e�ittir(..) metodu sonu...

    public static void main (String... args) {
        System.out.println ("Karo as: [" + new J2e_6x3 (Say�.AS, Tak�m.KARO).k�ymaKodu() + "]");
        System.out.println ("Ma�a alt�: [" + new J2e_6x3 (Say�.ALTI, Tak�m.MA�A).k�ymaKodu() + "]");
        System.out.println ("Kupa k�z: [" + new J2e_6x3 (Say�.KIZ, Tak�m.KUPA).k�ymaKodu() + "]");
        System.out.println ("Sinek papaz: [" + new J2e_6x3 (Say�.PAPAZ, Tak�m.S�NEK).k�ymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2e_6x3 s�n�f� sonu...

/* ��kt�:
**  >java J2e_6x3  **
Karo as: [1]
Ma�a alt�: [19]
Kupa k�z: [38]
Sinek papaz: [52]
*/