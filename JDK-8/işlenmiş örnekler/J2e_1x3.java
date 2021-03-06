// J2e_1x3.java: RoadBike (YolBisikleti) alt-örneği.

public class J2e_1x3 extends J2e_1x1 {
    private int tekerGenişliği; // mm...

    public J2e_1x3 (int ilkAçı, int ilkHız, int ilkVites, int yeniTekerGenişliği) {
        super (ilkAçı, ilkHız, ilkVites);
        this.tekerGenişliğiKoy (yeniTekerGenişliği);
    } // J2e_1x3(..) kurucu sonu...

    public int tekerGenişliğiAl() {return this.tekerGenişliği;}
    public void tekerGenişliğiKoy (int yeniTekerGenişliği) {this.tekerGenişliği = yeniTekerGenişliği;}

    public void açıklamaYaz() {
        super.açıklamaYaz();
        System.out.println ("Yol bisikletimin: [" + tekerGenişliğiAl() + "] mm genişlikte lastik tekerleri var.");
    } // açıklamaYaz() metodu sonu...
} // J2e_1x3 sınıfı sonu...