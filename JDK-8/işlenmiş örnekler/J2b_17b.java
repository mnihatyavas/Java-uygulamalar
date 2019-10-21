// J2b_17b.java: SwitchDemo (SwitchG�sterimi) �rne�i.

public class J2b_17b {
    public static void main (String[] args) {
        int ay�nt;
        String ayStr;
        try {ay�nt = Integer.valueOf (args[0]);} catch (Exception ist) {ay�nt = -1;}

        switch (ay�nt) {
            case 1:
                ayStr = "Ocak";
                break;
            case 2:
                ayStr = "�ubat";
                break;
            case 3:
                ayStr = "Mart";
                break;
            case 4:
                ayStr = "Nisan";
                break;
            case 5:
                ayStr = "May�s";
                break;
            case 6:
                ayStr = "Haziran";
                break;
            case 7:
                ayStr = "Temmuz";
                break;
            case 8:
                ayStr = "A�ustos";
                break;
            case 9:
                ayStr = "Eyl�l";
                break;
            case 10:
                ayStr = "Ekim";
                break;
            case 11:
                ayStr = "Kas�m";
                break;
            case 12:
                ayStr = "Aral�k";
                break;
            default: // E�er ay=[1-12] harici ba�kaca girilmi�se...
                ayStr = "Ge�ersiz ay rakam� giri�i!";
                break;
        } // switc-case blo�u sonu...

        System.out.println ("Girilen: " + ay�nt + " rakam�n�n kar��l���: " + ayStr);
    } // main(..) metodu sonu...
} // J2b_17b s�n�f� sonu...

/* ��kt�:
**  >java J2b_17b  **
Girilen: -1 rakam�n�n kar��l���: Ge�ersiz ay rakam� giri�i!

**  >java J2b_17b 120  ** TEKRAR
Girilen: 120 rakam�n�n kar��l���: Ge�ersiz ay rakam� giri�i!

**  >java J2b_17b 11  ** TEKRAR
Girilen: 11 rakam�n�n kar��l���: Kas�m

**  >java J2b_17b ocak  ** TEKRAR
Girilen: -1 rakam�n�n kar��l���: Ge�ersiz ay rakam� giri�i!
*/