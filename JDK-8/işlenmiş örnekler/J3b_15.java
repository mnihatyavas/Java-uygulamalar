// J3b_15.java: Root (K�k) �rne�i.

public class J3b_15 {
    public static void main (String[] args) {
        int i = 2;
        double r = Math.sqrt (i);
        System.out.print ("Karek�k <");
        System.out.print (i);
        System.out.print ("> e�ittir <");
        System.out.print (r);
        System.out.println (">.");

        i = 5;
        r = Math.sqrt (i);
        System.out.println ("Karek�k <" + i + "> e�ittir <" + r + ">.");
    } // main(..) metodu sonu...
} // J3b_15 s�n�f� sonu...

/* ��kt�:
**  >java J3b_15  **
Karek�k <2> e�ittir <1.4142135623730951>.
Karek�k <5> e�ittir <2.23606797749979>.
*/