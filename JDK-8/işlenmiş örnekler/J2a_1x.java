// J2a_1x.java: Bicycle (Bisiklet) alt-�rne�i.

class J2a_1x {
    int a�� = 90;
    int h�z = 0;
    int vites = 1;

    void a��y�De�i�tir (int yeniDe�er) {a�� = yeniDe�er;}
    void vitesDe�i�tir (int yeniDe�er) {vites = yeniDe�er;}
    void h�zlan (int art��) {h�z += art��;}
    void frenle (int azal��) {h�z -= azal��;}
    void durumlar�Yaz() {System.out.println ("V�cut a��s�: [" + a�� + "] derece\nH�z: [" + h�z + "] km/s\nVites: [" + vites + "]\n");}
} // J2a_1x s�n�f� sonu...