// J3b_12c.java: PrintStuff (YazÝçerikleri) örneði.

public class J3b_12c {
    public static void main (String[] args) {
        System.out.println (!false); // boolean
        System.out.println ('9'); // char
        char dokuz[] = {'d', 'o', 'k', 'u', 'z'};
        System.out.println (dokuz); // char []
        System.out.println ("Dokuz"); // String
        System.out.println (9); // int
        System.out.println (Long.MAX_VALUE); // Long
        System.out.println (Long.MIN_VALUE); // Long
        System.out.println (Double.MAX_VALUE); // Double
        System.out.println (Double.MIN_VALUE); // Double
        System.out.println (Math.PI); // float-->double
        System.out.println (Math.E); // float-->double
        System.out.println (new Object()); // toString()
    } // main(..) metodu sonu...
}  // J3b_12c sýnýfý sonu...

/* Çýktý:
**  >java J3b_12c  **
true
9
dokuz
Dokuz
9
9223372036854775807
-9223372036854775808
1.7976931348623157E308
4.9E-324
3.141592653589793
2.718281828459045
java.lang.Object@154617c
*/