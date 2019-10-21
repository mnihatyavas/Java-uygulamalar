// J3c_5a.java: SimpleThreads (BasitSicimler) örneði.

public class J3c_5a {
    // Aktüel sicimin adý ve takiben de bir mesajý yansýtýr...
    static void sicimMesajý (String mesaj) {
        String sicimAdý = Thread.currentThread().getName();
        System.out.format ("%s: %s%n", sicimAdý, mesaj);
    } // sicimMesajý(..) metodu sonu...

    private static class MesajDöngüsü implements Runnable {
        public void run() {
            String bilgi[] = {
                "Kýsraklar yulaf yer,",
                "Marallar da yulaf yer.",
                "Kuzular yemlik yer,",
                "Bir çocuk da yemlik yer."
            };

            try {
                for (int i = 0; i < bilgi.length; i++) {
                    Thread.sleep(4000); // 4 saniye bekler...
                    sicimMesajý (bilgi[i]); // Sýradaki bilgi mesajýný yansýtýr...
                } // for döngüsü sonu...
            }catch (InterruptedException ist) {sicimMesajý ("Tamamlayamadan kýrýldým!");
            } // try-catch bloðu sonu...
        } // run() metodu sonu...
    } // MesajDöngüsü sýnýfý sonu...

    public static void main (String args[]) throws InterruptedException {
        /* MesajDöngüsü sicimi'ni kýrmadan önceki
         * milisaniye geciktirmesi (varsayýlý 1 saat). */
        long sebat = 1000 * 60 * 60;

        // Komut satýrý argümaný girilmiþse sebat saniyesini belirtmeli...
        if (args.length > 0) {
            try {sebat = Long.parseLong (args[0]) * 1000;
            }catch (NumberFormatException ist) {
                System.err.println ("Argüman bir tamsayý long olmalý!");
                System.exit (1);
            } // try-catch bloðu sonu...
        } // if kararý sonu...

        sicimMesajý ("MesajDöngüsü sicimi baþlatýlýyor.");
        long ilkZaman = System.currentTimeMillis();
        Thread ip = new Thread (new MesajDöngüsü());
        ip.start();

        sicimMesajý ("MesajDöngüsü siciminin mesaj yansýtmalarýný tamamlamasý bekleniyor.");
        while (ip.isAlive()) {
            sicimMesajý ("Hala beklemede...");
            // MesajDöngüsü siciminin herbir bilgi yansýtmasý için azami 2 saniye bekletelim...
            ip.join (2000);
            if (((System.currentTimeMillis() - ilkZaman) > sebat) && ip.isAlive()) {
                sicimMesajý ("Beklemekten yoruldum, kýracaðým!");
                ip.interrupt();
                // Þimdi belirsizce bekle bakalým...
                ip.join();
            } // if kararý sonu...
        } // while döngüsü sonu...

        sicimMesajý ("Nihayet Tamamladým!");
    } // main(..) metodu sonu...
} // J3c_5a sýnýfý sonu...

/* Çýktý:
**  >java J3c_5a  **
main: MesajDöngüsü sicimi baþlatýlýyor.
main: MesajDöngüsü siciminin mesaj yansýtmalarýný tamamlamasý bekleniyor.
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Kýsraklar yulaf yer,
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Marallar da yulaf yer.
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Kuzular yemlik yer,
main: Hala beklemede...
main: Hala beklemede...
Thread-0: Bir çocuk da yemlik yer.
main: Nihayet Tamamladým!

**  >java J3c_5a 10s  ** TEKRAR
Argüman bir tamsayý long olmalý!

**  >java J3c_5a 1  ** TEKRAR
main: MesajDöngüsü sicimi baþlatýlýyor.
main: MesajDöngüsü siciminin mesaj yansýtmalarýný tamamlamasý bekleniyor.
main: Hala beklemede...
main: Beklemekten yoruldum, kýracaðým!
Thread-0: Tamamlayamadan kýrýldým!
main: Nihayet Tamamladým!
*/