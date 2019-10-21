// J2c_12.java: LocalClassExample (YerelSýnýfÖrneði) örneði.

public class J2c_12 {
    static String regExp = "[^0-9]"; // kalýp testi: Sadece 0-9 rakamlar olmalý...

    public static void telefonNumarasýnýGeçerlile (String telNo1, String telNo2) {
        final int telNoUzunluk = 10;

        class TelefonNumarasý {
            String formatlýTelefonNumarasý = null;

            TelefonNumarasý (String telNo) {
                String aktüelNumara = telNo.replaceAll (regExp, "");
                if (aktüelNumara.length() == telNoUzunluk) formatlýTelefonNumarasý = aktüelNumara;
                else formatlýTelefonNumarasý = null;
            } // TelefonNumarasý(..) kurucusu sonu...

            public String numarayýAl() {return formatlýTelefonNumarasý;}
        } // TelefonNumarasý sýnýfý sonu...

        TelefonNumarasý numaram1 = new TelefonNumarasý (telNo1);
        TelefonNumarasý numaram2 = new TelefonNumarasý (telNo2);
        
        if (numaram1.numarayýAl() == null) System.out.println ("Ýlk telefon numarasý \"" + telNo1 + "\" geçersiz!");
        else System.out.println ("Ýlk geçerli telefon numarasý: [" + numaram1.numarayýAl() + "]");

        if (numaram2.numarayýAl() == null) System.out.println ("Ýkinci telefon numarasý \"" + telNo2 + "\" geçersiz!");
        else System.out.println ("Ýkinci geçerli telefon numarasý: [" + numaram2.numarayýAl() + "]");
    } // telefonNumarasýnýGeçerlile(..) metodu sonu...

    public static void main (String... args) {
        telefonNumarasýnýGeçerlile ("551-555-9464", "991-8460"); // ikincisi: 505-991-8460 olmalýydý...
    } // main(..) metodu sonu...
} // J2c_12 sýnýfý sonu...

/* Çýktý:
**  >java J2c_12  **
Ýlk geçerli telefon numarasý: [5515559464]
Ýkinci telefon numarasý "991-8460" geçersiz!
*/
