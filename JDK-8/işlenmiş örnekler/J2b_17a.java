// J2b_17a.java: StringSwitchDemo (DizgeSwitchG�sterimi) �rne�i.

public class J2b_17a {
    public static int aySay�s�n�Al (String ayStr) {
        int ay = 0;

        if (ayStr == null) return ay; // Gereksiz kontrol...

        switch (ayStr.toLowerCase()) {
            case "ocak":
                ay = 1;
                break;
            case "�ubat":
                ay = 2;
                break;
            case "mart":
                ay = 3;
                break;
            case "nisan":
                ay = 4;
                break;
            case "may�s":
                ay = 5;
                break;
            case "haziran":
                ay = 6;
                break;
            case "temmuz":
                ay = 7;
                break;
            case "a�ustos":
                ay = 8;
                break;
            case "eyl�l":
                ay = 9;
                break;
            case "ekim":
                ay = 10;
                break;
            case "kas�m":
                ay = 11;
                break;
            case "aral�k":
                ay = 12;
                break;
            default: // null olmayan ay dizgesi yukardakilerden ba�ka ise...
                ay = 0;
                break;
        } // switch-case blo�u sonu...

        return ay;
    } // aySay�s�n�Al(..) metodu sonu...

    public static void main (String[] args) {
        String ay = args.length > 0? args[0] : "Nisan";
         
        int ay�nt = J2b_17a.aySay�s�n�Al (ay);

        if (ay�nt == 0) System.out.println ("Ge�ersiz ay ismi girdiniz!");
        else System.out.println (ay + " ay� rakam�: " + ay�nt);
    } // main(..) metodu sonu...
} // J2b_17a s�n�f� sonu...

/* ��kt�:
**  >java J2b_17a  **
Nisan ay� rakam�: 4

**  >java J2b_17a EK�M  ** TEKRAR
EK�M ay� rakam�: 10

**  >java J2b_17a ocak  ** TEKRAR
ocak ay� rakam�: 1

**  >java J2b_17a ocakk  ** TEKRAR
Ge�ersiz ay ismi girdiniz!
*/