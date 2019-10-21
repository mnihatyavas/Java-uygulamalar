// J3c_5b.java: SleepMessages (NinniMesajlarý) örneði

public class J3c_5b {
    public static void main (String args[]) throws InterruptedException {
        String bilgi[] = {
            "Kýsraklar yulaf yerler,",
            "Marallar da yulaf yer.",
            "Kuzular yemlik yerler,",
            "Bir çocuk da yemlik yer.",
            "Ninni yavrum ninni,",
            "Mýþýl mýþýl uyusun da büyüsün, ninni."
        };

        for (int i = 0; i < bilgi.length; i++) {
            Thread.sleep (2000); // 2 sn'de bir mesaj yansýtalým...
            System.out.println (bilgi[i]);
        } // for döngüsü sonu...
    } // main(..) metodu sonu...
} // J3c_5b sýnýfý sonu...

/* Çýktý:
**  >java J3c_5b  **
Kýsraklar yulaf yerler,
Marallar da yulaf yer.
Kuzular yemlik yerler,
Bir çocuk da yemlik yer.
Ninni yavrum ninni,
Mýþýl mýþýl uyusun da büyüsün, ninni.
*/