// J2a_1.java: BicycleDemo (BisikletG�sterisi) �rne�i.

class J2a_1 {
    public static void main (String[] args) {
        // 3 farkl� bisiklet nesnesi yaratal�m...
        J2a_1x s�r��1 = new J2a_1x(); // �lk bisiklet...
        J2a_1x s�r��2 = new J2a_1x(); // �kinci bisiklet...
        J2a_1x s�r��3 = new J2a_1x(); // ���nc� bisiklet...

        // Metodlar� 3 nesneye de uygulayal�m...
        s�r��1.a��y�De�i�tir (50);
        s�r��1.h�zlan (20);
        s�r��1.vitesDe�i�tir (10); // Vites: 1-21...
        s�r��1.durumlar�Yaz();

        s�r��2.a��y�De�i�tir (45);
        s�r��2.h�zlan (30);
        s�r��2.vitesDe�i�tir (10);
        s�r��2.a��y�De�i�tir (30);
        s�r��2.h�zlan (40);
        s�r��2.vitesDe�i�tir (15);
        s�r��2.durumlar�Yaz();

        s�r��3.a��y�De�i�tir (20);
        s�r��3.h�zlan (100);
        s�r��3.vitesDe�i�tir (21);
        s�r��3.durumlar�Yaz();
    } // main(..) metodu sonu...
} // J2a_1 s�n�f� sonu...

/* ��kt�:
**  >java J2a_1  **
V�cut a��s�: [50] derece
H�z: [20] km/s
Vites: [10]

V�cut a��s�: [30] derece
H�z: [70] km/s
Vites: [15]

V�cut a��s�: [20] derece
H�z: [100] km/s
Vites: [21]
*/