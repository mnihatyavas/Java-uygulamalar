// J2b_9a.java: ConditionalDemo1 (�artl�G�sterim1) �rne�i.

class J2b_9a {
    public static void main (String[] args) {
        int de�er1;
        int de�er2;

        try {de�er1 = Integer.valueOf (args[0]); }catch (Exception ist) {de�er1 = 1;}
        try {de�er2 = Integer.valueOf (args[1]); }catch (Exception ist) {de�er2 = 2;}

        if (((de�er1 % 2) == 0) && ((de�er2 % 2) == 0))
            System.out.println ("de�er1 AND/VE de�er2 ��FT SAYIDIR.");
        if (((de�er1 % 2) != 0) || ((de�er2 % 2) != 0))
            System.out.println ("de�er1 OR/VEYA de�er2 TEK SAYIDIR.");
    } // main(..) metodu sonu...
}  // J2b_9a s�n�f� sonu...

/* ��kt�:
**  >java J2b_9a  **
de�er1 OR/VEYA de�er2 TEK SAYIDIR.

**  >java J2b_9a 4 8  ** TEKRAR
de�er1 AND/VE de�er2 ��FT SAYIDIR.
*/