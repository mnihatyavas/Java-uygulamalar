// J2b_17a.java: StringSwitchDemo (DizgeSwitchGösterimi) örneði.

public class J2b_17a {
    public static int aySayýsýnýAl (String ayStr) {
        int ay = 0;

        if (ayStr == null) return ay; // Gereksiz kontrol...

        switch (ayStr.toLowerCase()) {
            case "ocak":
                ay = 1;
                break;
            case "þubat":
                ay = 2;
                break;
            case "mart":
                ay = 3;
                break;
            case "nisan":
                ay = 4;
                break;
            case "mayýs":
                ay = 5;
                break;
            case "haziran":
                ay = 6;
                break;
            case "temmuz":
                ay = 7;
                break;
            case "aðustos":
                ay = 8;
                break;
            case "eylül":
                ay = 9;
                break;
            case "ekim":
                ay = 10;
                break;
            case "kasým":
                ay = 11;
                break;
            case "aralýk":
                ay = 12;
                break;
            default: // null olmayan ay dizgesi yukardakilerden baþka ise...
                ay = 0;
                break;
        } // switch-case bloðu sonu...

        return ay;
    } // aySayýsýnýAl(..) metodu sonu...

    public static void main (String[] args) {
        String ay = args.length > 0? args[0] : "Nisan";
         
        int ayÝnt = J2b_17a.aySayýsýnýAl (ay);

        if (ayÝnt == 0) System.out.println ("Geçersiz ay ismi girdiniz!");
        else System.out.println (ay + " ayý rakamý: " + ayÝnt);
    } // main(..) metodu sonu...
} // J2b_17a sýnýfý sonu...

/* Çýktý:
**  >java J2b_17a  **
Nisan ayý rakamý: 4

**  >java J2b_17a EKÝM  ** TEKRAR
EKÝM ayý rakamý: 10

**  >java J2b_17a ocak  ** TEKRAR
ocak ayý rakamý: 1

**  >java J2b_17a ocakk  ** TEKRAR
Geçersiz ay ismi girdiniz!
*/