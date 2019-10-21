// J2e_1.java: TestBikes (DeneBisikletleri) örneði.

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

        bike1.açýklamaYaz(); // bike=bayk=bisiklet...
        bike2.açýklamaYaz();
        bike3.açýklamaYaz();
    } // main(..) metodu sonu...
} // J2e_1 sýnýfý sonu...

/* Çýktý:
**  >java J2e_1  **
Bisikletimin vitesi: [1], vücut açým: [20] ve süratim: [10]

Bisikletimin vitesi: [5], vücut açým: [20] ve süratim: [10]
Dað bisikletimin: [Dual] tipli suspansiyonu mevcuttur.

Bisikletimin vitesi: [8], vücut açým: [40] ve süratim: [20]
Yol bisikletimin: [23] mm geniþlikte lastik tekerleri var.
*/