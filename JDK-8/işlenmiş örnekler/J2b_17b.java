// J2b_17b.java: SwitchDemo (SwitchGösterimi) örneði.

public class J2b_17b {
    public static void main (String[] args) {
        int ayÝnt;
        String ayStr;
        try {ayÝnt = Integer.valueOf (args[0]);} catch (Exception ist) {ayÝnt = -1;}

        switch (ayÝnt) {
            case 1:
                ayStr = "Ocak";
                break;
            case 2:
                ayStr = "Þubat";
                break;
            case 3:
                ayStr = "Mart";
                break;
            case 4:
                ayStr = "Nisan";
                break;
            case 5:
                ayStr = "Mayýs";
                break;
            case 6:
                ayStr = "Haziran";
                break;
            case 7:
                ayStr = "Temmuz";
                break;
            case 8:
                ayStr = "Aðustos";
                break;
            case 9:
                ayStr = "Eylül";
                break;
            case 10:
                ayStr = "Ekim";
                break;
            case 11:
                ayStr = "Kasým";
                break;
            case 12:
                ayStr = "Aralýk";
                break;
            default: // Eðer ay=[1-12] harici baþkaca girilmiþse...
                ayStr = "Geçersiz ay rakamý giriþi!";
                break;
        } // switc-case bloðu sonu...

        System.out.println ("Girilen: " + ayÝnt + " rakamýnýn karþýlýðý: " + ayStr);
    } // main(..) metodu sonu...
} // J2b_17b sýnýfý sonu...

/* Çýktý:
**  >java J2b_17b  **
Girilen: -1 rakamýnýn karþýlýðý: Geçersiz ay rakamý giriþi!

**  >java J2b_17b 120  ** TEKRAR
Girilen: 120 rakamýnýn karþýlýðý: Geçersiz ay rakamý giriþi!

**  >java J2b_17b 11  ** TEKRAR
Girilen: 11 rakamýnýn karþýlýðý: Kasým

**  >java J2b_17b ocak  ** TEKRAR
Girilen: -1 rakamýnýn karþýlýðý: Geçersiz ay rakamý giriþi!
*/