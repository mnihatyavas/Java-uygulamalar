// J3cq_1.java: BadThreads (K�t�Sicimler) �rne�i.

public class J3cq_1 {
    private static String mesaj;

    private static class D�zelticiSicim extends Thread {
        public void run() {
            try {sleep (1000); 
            }catch (InterruptedException ist) {}

            mesaj = "K�sraklar hakikaten yulaf yerler."; // Anahtar ifade 2...
            System.out.println (mesaj);
        } // run() metodu sonu...
    } // D�zelticiSicim s�n�f� sonu...

    public static void main (String args[]) throws InterruptedException {
        mesaj = "K�sraklar yulaf yemezler."; // Anahtar ifade 1...
        System.out.println (mesaj);

        Thread.sleep (2000); // 2 sn bekle...
        new D�zelticiSicim().start();
    } // main(..) metodu sonu...
} // J3cq_1 s�n�f� sonu...

/* ��kt�:
**  >java J3cq_1  **
K�sraklar yulaf yemezler.
K�sraklar hakikaten yulaf yerler.
*/