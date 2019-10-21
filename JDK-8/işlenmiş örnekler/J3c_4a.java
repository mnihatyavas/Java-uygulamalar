// J3c_4a.java: HelloRunnable (SelamKoþturulabilir) örneði.

public class J3c_4a implements Runnable {// iþletir Koþturulabilir arayüzünü...
    public void run() {System.out.println ("Bir Runnable/Koþturulabilir arayüzü siciminden Selamlar!");}
    public static void main (String args[]) {(new Thread (new J3c_4a())).start();}
} // J3c_4a sýnýfý sonu...

/* Çýktý:
**  >java J3c_4a  **
Bir Runnable/Koþturulabilir arayüzü siciminden Selamlar!
*/