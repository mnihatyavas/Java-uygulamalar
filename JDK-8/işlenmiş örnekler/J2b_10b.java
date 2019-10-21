// J2b_10b.java: ContinueWithLabelDemo (EtiketliDevamG�sterimi) �rne�i.

class J2b_10b {
    public static void main (String[] args) {
        String c�mle = "Bu c�mle i�indeki herhangi ibarenin mevcudiyeti sorgulanacakt�r.";
        String ibare = args.length > 0? args[0] : "ibare";
        boolean bulundu = false;

        int uzunluk = c�mle.length() - ibare.length();

        etiket:
        for (int i = 0; i <= uzunluk; i++) {
            int n = ibare.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (c�mle.charAt (j++) != ibare.charAt (k++)) continue etiket;
            } // while d�ng�s� sonu...
            bulundu = true;
            break etiket;
        } // for d�ng�s� sonu...

        System.out.println (bulundu ? (ibare + " BULUNDU!") : (ibare + " BULUNAMADI!"));
    } // main(..) metodu sonu...
} // J2b_10b s�n�f� sonu...

/* ��kt�:
**  >java J2b_10b  **
ibare BULUNDU!

**  >java J2b_10b C�mle  ** TEKRAR
C�mle BULUNAMADI!

**  >java J2b_10b c�mle  ** TEKRAR
c�mle BULUNDU!
*/