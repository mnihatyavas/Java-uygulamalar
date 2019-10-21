// J3cq_1.java: BadThreads (KötüSicimler) örneği.

public class J3cq_1 {
    private static String mesaj;

    private static class DüzelticiSicim extends Thread {
        public void run() {
            try {sleep (1000); 
            }catch (InterruptedException ist) {}

            mesaj = "Kısraklar hakikaten yulaf yerler."; // Anahtar ifade 2...
            System.out.println (mesaj);
        } // run() metodu sonu...
    } // DüzelticiSicim sınıfı sonu...

    public static void main (String args[]) throws InterruptedException {
        mesaj = "Kısraklar yulaf yemezler."; // Anahtar ifade 1...
        System.out.println (mesaj);

        Thread.sleep (2000); // 2 sn bekle...
        new DüzelticiSicim().start();
    } // main(..) metodu sonu...
} // J3cq_1 sınıfı sonu...

/* Çıktı:
**  >java J3cq_1  **
Kısraklar yulaf yemezler.
Kısraklar hakikaten yulaf yerler.
*/