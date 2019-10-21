// J2cq_3.java: IdentifyMyParts (Tan�mlaPar�alar�m�) �rne�i.

public class J2cq_3 {
    public static int x = 7;
    public int y = 3;

    static public void main (String[] args) {
        J2cq_3 par�a1 = new J2cq_3();
        J2cq_3 par�a2 = new J2cq_3();
        par�a1.y = 5;
        par�a2.y = 6;
        par�a1.x = 1;
        par�a2.x = 2;

        System.out.println ("J2cq_3.x = " + J2cq_3.x); // 2...
        //System.out.println ("J2cq_3.y = " + J2cq_3.y); // kurals�z...
        System.out.println ("par�a1.y = " + par�a1.y); // 5...
        System.out.println ("par�a2.y = " + par�a2.y); // 6...
        System.out.println ("par�a1.x = " + par�a1.x); // 2...
        System.out.println ("par�a2.x = " + par�a2.x); // 2..
    } // main(..) metodu sonu...
} // J2cq_3 s�n�f� sonu...

/* ��kt�:
**  >java J2cq_3  **
J2cq_3.x = 2
par�a1.y = 5
par�a2.y = 6
par�a1.x = 2
par�a2.x = 2
*/