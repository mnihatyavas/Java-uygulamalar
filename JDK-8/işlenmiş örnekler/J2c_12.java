// J2c_12.java: LocalClassExample (YerelS�n�f�rne�i) �rne�i.

public class J2c_12 {
    static String regExp = "[^0-9]"; // kal�p testi: Sadece 0-9 rakamlar olmal�...

    public static void telefonNumaras�n�Ge�erlile (String telNo1, String telNo2) {
        final int telNoUzunluk = 10;

        class TelefonNumaras� {
            String formatl�TelefonNumaras� = null;

            TelefonNumaras� (String telNo) {
                String akt�elNumara = telNo.replaceAll (regExp, "");
                if (akt�elNumara.length() == telNoUzunluk) formatl�TelefonNumaras� = akt�elNumara;
                else formatl�TelefonNumaras� = null;
            } // TelefonNumaras�(..) kurucusu sonu...

            public String numaray�Al() {return formatl�TelefonNumaras�;}
        } // TelefonNumaras� s�n�f� sonu...

        TelefonNumaras� numaram1 = new TelefonNumaras� (telNo1);
        TelefonNumaras� numaram2 = new TelefonNumaras� (telNo2);
        
        if (numaram1.numaray�Al() == null) System.out.println ("�lk telefon numaras� \"" + telNo1 + "\" ge�ersiz!");
        else System.out.println ("�lk ge�erli telefon numaras�: [" + numaram1.numaray�Al() + "]");

        if (numaram2.numaray�Al() == null) System.out.println ("�kinci telefon numaras� \"" + telNo2 + "\" ge�ersiz!");
        else System.out.println ("�kinci ge�erli telefon numaras�: [" + numaram2.numaray�Al() + "]");
    } // telefonNumaras�n�Ge�erlile(..) metodu sonu...

    public static void main (String... args) {
        telefonNumaras�n�Ge�erlile ("551-555-9464", "991-8460"); // ikincisi: 505-991-8460 olmal�yd�...
    } // main(..) metodu sonu...
} // J2c_12 s�n�f� sonu...

/* ��kt�:
**  >java J2c_12  **
�lk ge�erli telefon numaras�: [5515559464]
�kinci telefon numaras� "991-8460" ge�ersiz!
*/
