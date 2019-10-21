// J2e_1x2.java: MountainBike (Da�Bisikleti) alt-�rne�i.

public class J2e_1x2 extends J2e_1x1 {
    private String suspansiyon; // Oturak alt� yay sistemi=Amortis�r=DarbeEmici...

    public J2e_1x2 (int ilkA��, int ilkH�z, int ilkVites, String suspansiyonTipi) {
        super (ilkA��, ilkH�z, ilkVites);
        this.suspansiyonKoy (suspansiyonTipi);
    } // J2e_1x2(..) kurucu sonu...

    public String suspansiyonAl() {return this.suspansiyon;}
    public void suspansiyonKoy (String suspansiyonTipi) {this.suspansiyon = suspansiyonTipi;}

    public void a��klamaYaz() {
        super.a��klamaYaz();
        System.out.println ("Da� bisikletimin: [" + suspansiyonAl() + "] tipli suspansiyonu mevcut.");
    } // a��klamaYaz() metodu sonu...
} // J2e_1x2 s�n�f� sonu...