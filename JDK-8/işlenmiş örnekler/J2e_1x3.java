// J2e_1x3.java: RoadBike (YolBisikleti) alt-örneði.

public class J2e_1x3 extends J2e_1x1 {
    private int tekerGeniþliði; // mm...

    public J2e_1x3 (int ilkAçý, int ilkHýz, int ilkVites, int yeniTekerGeniþliði) {
        super (ilkAçý, ilkHýz, ilkVites);
        this.tekerGeniþliðiKoy (yeniTekerGeniþliði);
    } // J2e_1x3(..) kurucu sonu...

    public int tekerGeniþliðiAl() {return this.tekerGeniþliði;}
    public void tekerGeniþliðiKoy (int yeniTekerGeniþliði) {this.tekerGeniþliði = yeniTekerGeniþliði;}

    public void açýklamaYaz() {
        super.açýklamaYaz();
        System.out.println ("Yol bisikletimin: [" + tekerGeniþliðiAl() + "] mm geniþlikte lastik tekerleri var.");
    } // açýklamaYaz() metodu sonu...
} // J2e_1x3 sýnýfý sonu...