// J2e_1x1.java: Bicycle (Bisiklet) alt-�rne�i.

public class J2e_1x1 {
    // J2e_1x1=Bicycle s�n�f�n�n 3 alan� (tip de�i�keni) vard�r:
    public int a��;
    public int vites;
    public int h�z;

    public J2e_1x1() {super();} // Arg�mans�z kurucu...
   
    public J2e_1x1 (int ilkA��, int ilkH�z, int ilkVites) {
        vites = ilkVites;
        a�� = ilkA��;
        h�z = ilkH�z;
    } // J2e_1x1(..) arg�manl� kurucu sonu...

    public void a��Koy (int de�er) {a�� = de�er;}
    public void vitesKoy (int de�er) {vites = de�er;}
    public void frenle (int azal��) {h�z -= azal��;}
    public void h�zlan (int art��) {h�z += art��;}
    public void a��klamaYaz() {System.out.println ("\nBisikletimin vitesi: [" + this.vites + "], v�cut a��m: [" + this.a�� + "] ve s�ratim: [" + this.h�z + "]");}
} // J2e_1x1 s�n�f� sonu...