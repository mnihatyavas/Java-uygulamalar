// J2cq_5.java: SomethingIsRight (BuDo�rudur) �rne�i.

// Gereken dosya: J2cq_5x.java; Rectangle
public class J2cq_5 {
    public static void main (String[] args) {
        J2cq_5x dikd�rtgenim = new J2cq_5x();
        dikd�rtgenim.geni�lik = 140;
        dikd�rtgenim.y�kseklik = 50;
        System.out.println ("dikd�rtgenim'in alan� = (" +
            dikd�rtgenim.geni�lik + " * " + dikd�rtgenim.y�kseklik + ") = " +
            dikd�rtgenim.alan�() + " birim^2.");
    } // main(..) metodu sonu...
} // J2cq_5 s�n�f� sonu...

/* ��kt�:
**  >java J2cq_5  **
dikd�rtgenim'in alan� = (140 * 50) = 7000 birim^2.
*/