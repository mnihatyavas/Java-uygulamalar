// J2b_18.java: UnaryDemo (TekliGösterim) örneði.

class J2b_18 {
    public static void main (String[] args) {
        int sonuç = +1;
        System.out.println ("Sonuç=" + sonuç);

        sonuç--;
        System.out.println ("Sonuç=" + sonuç);

        sonuç++;
        System.out.println ("Sonuç=" + sonuç);

        sonuç = -sonuç;
        System.out.println ("Sonuç=" + sonuç);

        boolean baþarýlý = false;
        System.out.println ("Baþarýlý mý? " + baþarýlý);
        System.out.println ("Baþarýlý deðil mi? " + !baþarýlý);
    } // main(..) metodu sonu...
} // J2b_18 sýnýfý sonu...

/* Çýktý:
**  >java J2b_18  **
Sonuç=1
Sonuç=0
Sonuç=1
Sonuç=-1
Baþarýlý mý? false
Baþarýlý deðil mi? true
*/