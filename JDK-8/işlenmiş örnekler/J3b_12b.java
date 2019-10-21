// J3b_12b.java: PrintfStuff (FormatlýYazýlacaklar) örneði.

public class J3b_12b {
    public static void main (String[] args) {
        char[] dokuz = {'d', 'o', 'k', 'u', 'z'};
        System.out.printf ("b: %b %nc: %c %ns: %s %ns: %s %nd: %d %nd: %d %nd: %d %ng: %g %ng: %g %ng: %g %nf: %f %ng: %g %nf: %f %ns: %s%n",
            !false, '9', new String (dokuz), "Dokuz", 9,
            Long.MAX_VALUE, Long.MIN_VALUE, Double.MAX_VALUE, Double.MIN_VALUE,
            Math.PI, Math.PI, Math.E, Math.E, new Object());
    } // main(..) metodu sonu...
} // J3b_12b sýnýfý sonu...

/* Çýktý:
**  >java J3b_12b  **
b: true
c: 9
s: dokuz
s: Dokuz
d: 9
d: 9223372036854775807
d: -9223372036854775808
g: 1,79769e+308
g: 4,90000e-324
g: 3,14159
f: 3,141593
g: 2,71828
f: 2,718282
s: java.lang.Object@ad8086
*/