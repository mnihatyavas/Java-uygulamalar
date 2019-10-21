// J2cq_3.java: IdentifyMyParts (TanýmlaParçalarýmý) örneði.

public class J2cq_3 {
    public static int x = 7;
    public int y = 3;

    static public void main (String[] args) {
        J2cq_3 parça1 = new J2cq_3();
        J2cq_3 parça2 = new J2cq_3();
        parça1.y = 5;
        parça2.y = 6;
        parça1.x = 1;
        parça2.x = 2;

        System.out.println ("J2cq_3.x = " + J2cq_3.x); // 2...
        //System.out.println ("J2cq_3.y = " + J2cq_3.y); // kuralsýz...
        System.out.println ("parça1.y = " + parça1.y); // 5...
        System.out.println ("parça2.y = " + parça2.y); // 6...
        System.out.println ("parça1.x = " + parça1.x); // 2...
        System.out.println ("parça2.x = " + parça2.x); // 2..
    } // main(..) metodu sonu...
} // J2cq_3 sýnýfý sonu...

/* Çýktý:
**  >java J2cq_3  **
J2cq_3.x = 2
parça1.y = 5
parça2.y = 6
parça1.x = 2
parça2.x = 2
*/