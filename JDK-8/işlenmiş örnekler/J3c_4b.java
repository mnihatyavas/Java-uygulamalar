// J3c_4b.java: HelloThread (MerhabaSicim) �rne�i.

public class J3c_4b extends Thread {// miraslar Sicim s�n�f�n�...
    public void run() {System.out.println ("Bir Thread/�p s�n�f� siciminden Selamlar!");}
    public static void main (String args[]) {(new J3c_4b()).start();}
} // J3c_4b s�n�f� sonu...

/* ��kt�:
**  >java J3c_4b  **
Bir Thread/�p s�n�f� siciminden Selamlar!
*/