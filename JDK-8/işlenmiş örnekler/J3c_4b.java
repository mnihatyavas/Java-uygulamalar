// J3c_4b.java: HelloThread (MerhabaSicim) örneði.

public class J3c_4b extends Thread {// miraslar Sicim sýnýfýný...
    public void run() {System.out.println ("Bir Thread/Ýp sýnýfý siciminden Selamlar!");}
    public static void main (String args[]) {(new J3c_4b()).start();}
} // J3c_4b sýnýfý sonu...

/* Çýktý:
**  >java J3c_4b  **
Bir Thread/Ýp sýnýfý siciminden Selamlar!
*/