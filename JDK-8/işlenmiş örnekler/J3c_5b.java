// J3c_5b.java: SleepMessages (NinniMesajlar�) �rne�i

public class J3c_5b {
    public static void main (String args[]) throws InterruptedException {
        String bilgi[] = {
            "K�sraklar yulaf yerler,",
            "Marallar da yulaf yer.",
            "Kuzular yemlik yerler,",
            "Bir �ocuk da yemlik yer.",
            "Ninni yavrum ninni,",
            "M���l m���l uyusun da b�y�s�n, ninni."
        };

        for (int i = 0; i < bilgi.length; i++) {
            Thread.sleep (2000); // 2 sn'de bir mesaj yans�tal�m...
            System.out.println (bilgi[i]);
        } // for d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3c_5b s�n�f� sonu...

/* ��kt�:
**  >java J3c_5b  **
K�sraklar yulaf yerler,
Marallar da yulaf yer.
Kuzular yemlik yerler,
Bir �ocuk da yemlik yer.
Ninni yavrum ninni,
M���l m���l uyusun da b�y�s�n, ninni.
*/