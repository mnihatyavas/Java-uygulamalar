// J2e_1.java: TestBikes (DeneBisikletleri) �rne�i.

/* Gereken dosyalar:
 *   J2e_1x1.java=Bicycle
 *   J2e_1x2.java=MountainBike
 *   J2e_1x3.java=RoadBike
 */
public class J2e_1 {
    public static void main (String[] args) {
        J2e_1x1 bike1, bike2, bike3; // J2e_1x1 = Bicycle...

        bike1 = new J2e_1x1 (20, 10, 1);
        bike2 = new J2e_1x2 (20, 10, 5, "Dual"); // J2e_1x2 = MountainBike...
        bike3 = new J2e_1x3 (40, 20, 8, 23); // J2e_1x3 = RoadBike...

        bike1.a��klamaYaz(); // bike=bayk=bisiklet...
        bike2.a��klamaYaz();
        bike3.a��klamaYaz();
    } // main(..) metodu sonu...
} // J2e_1 s�n�f� sonu...

/* ��kt�:
**  >java J2e_1  **
Bisikletimin vitesi: [1], v�cut a��m: [20] ve s�ratim: [10]

Bisikletimin vitesi: [5], v�cut a��m: [20] ve s�ratim: [10]
Da� bisikletimin: [Dual] tipli suspansiyonu mevcuttur.

Bisikletimin vitesi: [8], v�cut a��m: [40] ve s�ratim: [20]
Yol bisikletimin: [23] mm geni�likte lastik tekerleri var.
*/