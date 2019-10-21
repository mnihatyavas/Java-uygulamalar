// J2e_6x3.java: PlayingCard (OyunKartý) alt-örneði.

public class J2e_6x3 implements J2e_6x1 {// J2e_6x1=J2e_6x1/Kart arayüzü...
    private J2e_6x1.Sayý sayý;
    private J2e_6x1.Takým takým;

    public J2e_6x3 (J2e_6x1.Sayý sayý, J2e_6x1.Takým takým) {
        this.sayý = sayý;
        this.takým = takým;
    } // J2e_6x3(..) kurucu sonu...

    public J2e_6x1.Takým takýmAl() {return takým;}
    public J2e_6x1.Sayý sayýAl() {return sayý;}
    public int kýymaKodu() {return ((takým.deðerAl() - 1) * 13) + sayý.deðerAl();}
    public int compareTo (J2e_6x1 nesne) {return this.kýymaKodu() - nesne.kýymaKodu();}
    public String toString() {return this.takým.metinAl() + " " + this.sayý.metinAl();}

    public boolean eþittir (Object nesne) {
        if (nesne instanceof J2e_6x1)
            if (((J2e_6x1)nesne).sayýAl() == this.sayý &&
                ((J2e_6x1)nesne).takýmAl() == this.takým) return true;
            else return false;
        else return false;
    } // eþittir(..) metodu sonu...

    public static void main (String... args) {
        System.out.println ("Karo as: [" + new J2e_6x3 (Sayý.AS, Takým.KARO).kýymaKodu() + "]");
        System.out.println ("Maça altý: [" + new J2e_6x3 (Sayý.ALTI, Takým.MAÇA).kýymaKodu() + "]");
        System.out.println ("Kupa kýz: [" + new J2e_6x3 (Sayý.KIZ, Takým.KUPA).kýymaKodu() + "]");
        System.out.println ("Sinek papaz: [" + new J2e_6x3 (Sayý.PAPAZ, Takým.SÝNEK).kýymaKodu() + "]");
    } // main(..) metodu sonu...
} // J2e_6x3 sýnýfý sonu...

/* Çýktý:
**  >java J2e_6x3  **
Karo as: [1]
Maça altý: [19]
Kupa kýz: [38]
Sinek papaz: [52]
*/