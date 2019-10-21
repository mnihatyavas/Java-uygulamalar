// J2b_10a.java: ContinueDemo (DevamG�sterimi) �rne�i.

class J2b_10a {
    public static void main (String[] args) {
        char aranan = args.length > 0? args[0].charAt(0) : 'a';

        String c�mle = "Bu c�mle i�indeki herhangi bir karakterin" + " ka� kez ge�ti�ini tespit edebilirsiniz.";
        int uzunluk = c�mle.length();
        int saya� = 0;

        for (int i = 0; i < uzunluk; i++) {
            if (c�mle.charAt (i) != aranan) continue; // arad���m�z karakter de�ilse devam et (kalan kodlar� sege�)...
            saya�++; // Ara�t�rd���m�z karakterse saya�'� bir art�r...
        } // for d�ng�s� sonu...

        System.out.println ("[" + c�mle + "] c�mlesi i�inde tespit edilen [" + aranan + "] karakteri say�s�: " + saya�);
    } // main(..) metodu sonu...
}  // J2b_10a s�n�f� sonu...

/* ��kt�:
**  >java J2b_10a  **
[Bu c�mle i�indeki herhangi bir karakterin ka� kez ge�ti�ini tespit edebilirsiniz.] c�mlesi i�inde tespit edilen [a] karakteri say�s�: 4

**  >java J2b_10a i  ** TEKRAR
[Bu c�mle i�indeki herhangi bir karakterin ka� kez ge�ti�ini tespit edebilirsiniz.] c�mlesi i�inde tespit edilen [i] karakteri say�s�: 14

**  >java J2b_10a elma  ** TEKRAR
[Bu c�mle i�indeki herhangi bir karakterin ka� kez ge�ti�ini tespit edebilirsiniz.] c�mlesi i�inde tespit edilen [e] karakteri say�s�: 9
*/