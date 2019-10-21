// J3b_12a.java: Format (biçimleme) örneði.

public class J3b_12a {
    public static void main (String[] args) {
        System.out.format ("1.biçimleme: [%f]%n2.biçimleme: [%1$+020.10f]%n3.biçimleme: [%1$+030.20f]%n", Math.PI);
    } // main(..) metodu sonu...
} // J3b_12a sýnýfý sonu...

/* Çýktý:
**  >java J3b_12a  **
1.biçimleme: [3,141593]
2.biçimleme: [+00000003,1415926536]
3.biçimleme: [+00000003,14159265358979300000]
*/