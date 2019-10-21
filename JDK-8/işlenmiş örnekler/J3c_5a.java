// J3c_5a.java: SimpleThreads (BasitSicimler) �rne�i.

public class J3c_5a {
    // Akt�el sicimin ad� ve takiben de bir mesaj� yans�t�r...
    static void sicimMesaj� (String mesaj) {
        String sicimAd� = Thread.currentThread().getName();
        System.out.format ("%s: %s%n", sicimAd�, mesaj);
    } // sicimMesaj�(..) metodu sonu...

    private static class MesajD�ng�s� implements Runnable {
        public void run() {
            String bilgi[] = {
                "K�sraklar yulaf yer,",
                "Marallar da yulaf yer.",
                "Kuzular yemlik yer,",
                "Bir �ocuk da yemlik yer."
            };

            try {
                for (int i = 0; i < bilgi.length; i++) {
                    Thread.sleep(4000); // 4 saniye bekler...
                    sicimMesaj� (bilgi[i]); // S�radaki bilgi mesaj�n� yans�t�r...
                } // for d�ng�s� sonu...
            }catch (InterruptedException ist) {sicimMesaj� ("Tamamlayamadan k�r�ld�m!");
            } // try-catch blo�u sonu...
        } // run() metodu sonu...
    } // MesajD�ng�s� s�n�f� sonu...

    public static void main (String args[]) throws InterruptedException {
        /* MesajD�ng�s� sicimi'ni k�rmadan �nceki
         * milisaniye geciktirmesi (varsay�l� 1 saat). */
        long sebat = 1000 * 60 * 60;

        // Komut sat�r� arg�man� girilmi�se sebat saniyesini belirtmeli...
        if (args.length > 0) {
            try {sebat = Long.parseLong (args[0]) * 1000;
            }catch (NumberFormatException ist) {
                System.err.println ("Arg�man bir tamsay� long olmal�!");
                System.exit (1);
            } // try-catch blo�u sonu...
        } // if karar� sonu...

        sicimMesaj� ("MesajD�ng�s� sicimi ba�lat�l�yor.");
        long ilkZaman = System.currentTimeMillis();
        Thread ip = new Thread (new MesajD�ng�s�());
        ip.start();

        sicimMesaj� ("MesajD�ng�s� siciminin mesaj yans�tmalar�n� tamamlamas� bekleniyor.");
        while (ip.isAlive()) {
            sicimMesaj� ("Hala beklemede...");
            // MesajD�ng�s� siciminin herbir bilgi yans�tmas� i�in azami 2 saniye bekletelim...
            ip.join (2000);
            if (((System.currentTimeMillis() - ilkZaman) > sebat) && ip.isAlive()) {
                sicimMesaj� ("Beklemekten yoruldum, k�raca��m!");
                ip.interrupt();
                // �imdi belirsizce bekle bakal�m...
                ip.join();
            } // if karar� sonu...
        } // while d�ng�s� sonu...

        sicimMesaj� ("Nihayet Tamamlad�m!");
    } // main(..) metodu sonu...
} // J3c_5a s�n�f� sonu...

/* ��kt�:
**  >java J3c_5a  **
main: MesajD�ng�s� sicimi ba�lat�l�yor.
main: MesajD�ng�s� siciminin mesaj yans�tmalar�n� tamamlamas� bekleniyor.
main: Hala beklemede...
main: Hala beklemede...
Thread-0: K�sraklar yulaf yer,
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Marallar da yulaf yer.
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Kuzular yemlik yer,
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Bir �ocuk da yemlik yer.
main: Nihayet Tamamlad�m!

**  >java J3c_5a 10s  ** TEKRAR
Arg�man bir tamsay� long olmal�!

**  >java J3c_5a 1  ** TEKRAR
main: MesajD�ng�s� sicimi ba�lat�l�yor.
main: MesajD�ng�s� siciminin mesaj yans�tmalar�n� tamamlamas� bekleniyor.
main: Hala beklemede...
main: Beklemekten yoruldum, k�raca��m!
Thread-0: Tamamlayamadan k�r�ld�m!
main: Nihayet Tamamlad�m!
*/