// J2cq_5.java: SomethingIsRight (BuDoðrudur) örneði.

// Gereken dosya: J2cq_5x.java; Rectangle
public class J2cq_5 {
    public static void main (String[] args) {
        J2cq_5x dikdörtgenim = new J2cq_5x();
        dikdörtgenim.geniþlik = 140;
        dikdörtgenim.yükseklik = 50;
        System.out.println ("dikdörtgenim'in alaný = (" +
            dikdörtgenim.geniþlik + " * " + dikdörtgenim.yükseklik + ") = " +
            dikdörtgenim.alaný() + " birim^2.");
    } // main(..) metodu sonu...
} // J2cq_5 sýnýfý sonu...

/* Çýktý:
**  >java J2cq_5  **
dikdörtgenim'in alaný = (140 * 50) = 7000 birim^2.
*/