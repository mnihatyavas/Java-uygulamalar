// J2e_1x1.java: Bicycle (Bisiklet) alt-örneði.

public class J2e_1x1 {
    // J2e_1x1=Bicycle sýnýfýnýn 3 alaný (tip deðiþkeni) vardýr:
    public int açý;
    public int vites;
    public int hýz;

    public J2e_1x1() {super();} // Argümansýz kurucu...
   
    public J2e_1x1 (int ilkAçý, int ilkHýz, int ilkVites) {
        vites = ilkVites;
        açý = ilkAçý;
        hýz = ilkHýz;
    } // J2e_1x1(..) argümanlý kurucu sonu...

    public void açýKoy (int deðer) {açý = deðer;}
    public void vitesKoy (int deðer) {vites = deðer;}
    public void frenle (int azalýþ) {hýz -= azalýþ;}
    public void hýzlan (int artýþ) {hýz += artýþ;}
    public void açýklamaYaz() {System.out.println ("\nBisikletimin vitesi: [" + this.vites + "], vücut açým: [" + this.açý + "] ve süratim: [" + this.hýz + "]");}
} // J2e_1x1 sýnýfý sonu...