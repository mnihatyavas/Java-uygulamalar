// J3b_15.java: Root (Kök) örneði.

public class J3b_15 {
    public static void main (String[] args) {
        int i = 2;
        double r = Math.sqrt (i);
        System.out.print ("Karekök <");
        System.out.print (i);
        System.out.print ("> eþittir <");
        System.out.print (r);
        System.out.println (">.");

        i = 5;
        r = Math.sqrt (i);
        System.out.println ("Karekök <" + i + "> eþittir <" + r + ">.");
    } // main(..) metodu sonu...
} // J3b_15 sýnýfý sonu...

/* Çýktý:
**  >java J3b_15  **
Karekök <2> eþittir <1.4142135623730951>.
Karekök <5> eþittir <2.23606797749979>.
*/