// J2e_1x3.java: RoadBike (YolBisikleti) alt-�rne�i.

public class J2e_1x3 extends J2e_1x1 {
    private int tekerGeni�li�i; // mm...

    public J2e_1x3 (int ilkA��, int ilkH�z, int ilkVites, int yeniTekerGeni�li�i) {
        super (ilkA��, ilkH�z, ilkVites);
        this.tekerGeni�li�iKoy (yeniTekerGeni�li�i);
    } // J2e_1x3(..) kurucu sonu...

    public int tekerGeni�li�iAl() {return this.tekerGeni�li�i;}
    public void tekerGeni�li�iKoy (int yeniTekerGeni�li�i) {this.tekerGeni�li�i = yeniTekerGeni�li�i;}

    public void a��klamaYaz() {
        super.a��klamaYaz();
        System.out.println ("Yol bisikletimin: [" + tekerGeni�li�iAl() + "] mm geni�likte lastik tekerleri var.");
    } // a��klamaYaz() metodu sonu...
} // J2e_1x3 s�n�f� sonu...