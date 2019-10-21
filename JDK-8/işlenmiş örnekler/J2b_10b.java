// J2b_10b.java: ContinueWithLabelDemo (EtiketliDevamGösterimi) örneði.

class J2b_10b {
    public static void main (String[] args) {
        String cümle = "Bu cümle içindeki herhangi ibarenin mevcudiyeti sorgulanacaktýr.";
        String ibare = args.length > 0? args[0] : "ibare";
        boolean bulundu = false;

        int uzunluk = cümle.length() - ibare.length();

        etiket:
        for (int i = 0; i <= uzunluk; i++) {
            int n = ibare.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (cümle.charAt (j++) != ibare.charAt (k++)) continue etiket;
            } // while döngüsü sonu...
            bulundu = true;
            break etiket;
        } // for döngüsü sonu...

        System.out.println (bulundu ? (ibare + " BULUNDU!") : (ibare + " BULUNAMADI!"));
    } // main(..) metodu sonu...
} // J2b_10b sýnýfý sonu...

/* Çýktý:
**  >java J2b_10b  **
ibare BULUNDU!

**  >java J2b_10b Cümle  ** TEKRAR
Cümle BULUNAMADI!

**  >java J2b_10b cümle  ** TEKRAR
cümle BULUNDU!
*/