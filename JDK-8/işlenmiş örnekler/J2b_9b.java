// J2b_9b.java: ConditionalDemo2 (ÞartlýGösterim2) örneði.

class J2b_9b {
    public static void main(String[] args){
        int deðer1;
        int deðer2;

        try {deðer1 = Integer.valueOf (args[0]); }catch (Exception ist) {deðer1 = 1;}
        try {deðer2 = Integer.valueOf (args[1]); }catch (Exception ist) {deðer2 = 2;}

        int sonuç;
        boolean birÞart = true;
        sonuç = birÞart ? deðer1 : deðer2;

        System.out.println ("Þart doðruysa ilk deðer: " + sonuç);
    } // main(..) metodu sonu...
} // J2b_9b sýnýfý sonu...

/* Çýktý:
**  >java J2b_9b  **
Þart doðruysa ilk deðer: 1

**  >java J2b_9b -15 8  ** TEKRAR
Þart doðruysa ilk deðer: -15
*/