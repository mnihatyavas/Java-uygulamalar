// J2b_9a.java: ConditionalDemo1 (ÞartlýGösterim1) örneði.

class J2b_9a {
    public static void main (String[] args) {
        int deðer1;
        int deðer2;

        try {deðer1 = Integer.valueOf (args[0]); }catch (Exception ist) {deðer1 = 1;}
        try {deðer2 = Integer.valueOf (args[1]); }catch (Exception ist) {deðer2 = 2;}

        if (((deðer1 % 2) == 0) && ((deðer2 % 2) == 0))
            System.out.println ("deðer1 AND/VE deðer2 ÇÝFT SAYIDIR.");
        if (((deðer1 % 2) != 0) || ((deðer2 % 2) != 0))
            System.out.println ("deðer1 OR/VEYA deðer2 TEK SAYIDIR.");
    } // main(..) metodu sonu...
}  // J2b_9a sýnýfý sonu...

/* Çýktý:
**  >java J2b_9a  **
deðer1 OR/VEYA deðer2 TEK SAYIDIR.

**  >java J2b_9a 4 8  ** TEKRAR
deðer1 AND/VE deðer2 ÇÝFT SAYIDIR.
*/