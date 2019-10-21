// J2e_1x2.java: MountainBike (DaðBisikleti) alt-örneði.

public class J2e_1x2 extends J2e_1x1 {
    private String suspansiyon; // Oturak altý yay sistemi=Amortisör=DarbeEmici...

    public J2e_1x2 (int ilkAçý, int ilkHýz, int ilkVites, String suspansiyonTipi) {
        super (ilkAçý, ilkHýz, ilkVites);
        this.suspansiyonKoy (suspansiyonTipi);
    } // J2e_1x2(..) kurucu sonu...

    public String suspansiyonAl() {return this.suspansiyon;}
    public void suspansiyonKoy (String suspansiyonTipi) {this.suspansiyon = suspansiyonTipi;}

    public void açýklamaYaz() {
        super.açýklamaYaz();
        System.out.println ("Dað bisikletimin: [" + suspansiyonAl() + "] tipli suspansiyonu mevcut.");
    } // açýklamaYaz() metodu sonu...
} // J2e_1x2 sýnýfý sonu...