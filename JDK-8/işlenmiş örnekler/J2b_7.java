// J2b_7.java: ComparisonDemo (Kar��la�t�rmaG�sterimi) �rne�i.

class J2b_7 {
    public static void main (String[] args) {
        int de�er1 = 1, de�er2 = 2;

        try {de�er1 = Integer.valueOf (args[0]); }catch (Exception ist) {de�er1 = -1;}
        try {de�er2 = Integer.valueOf (args[1]); }catch (Exception ist) {de�er2 = -2;}

        System.out.println ("de�er1=" + de�er1 + " ve de�er2=" + de�er2);
        if (de�er1 == de�er2) System.out.println ("de�er1 == de�er2");
        if (de�er1 != de�er2) System.out.println ("de�er1 != de�er2");
        if (de�er1 > de�er2) System.out.println ("de�er1 > de�er2");
        if (de�er1 < de�er2) System.out.println ("de�er1 < de�er2");
        if (de�er1 <= de�er2) System.out.println ("de�er1 <= de�er2");
        if (de�er1 >= de�er2) System.out.println ("de�er1 >= de�er2");
    } // main(..) metodu sonu...
} // J2b_7 s�n�f� sonu...

/* ��kt�:
**  >java J2b_7  **
de�er1=-1 ve de�er2=-2
de�er1 != de�er2
de�er1 > de�er2
de�er1 >= de�er2

**  >java J2b_7 -2  ** TEKRAR
de�er1=-2 ve de�er2=-2
de�er1 == de�er2
de�er1 <= de�er2
de�er1 >= de�er2

**  >java J2b_7 3 9  ** TEKRAR
de�er1=3 ve de�er2=9
de�er1 != de�er2
de�er1 < de�er2
de�er1 <= de�er2
*/