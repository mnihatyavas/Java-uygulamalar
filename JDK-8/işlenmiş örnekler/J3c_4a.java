// J3c_4a.java: HelloRunnable (SelamKo�turulabilir) �rne�i.

public class J3c_4a implements Runnable {// i�letir Ko�turulabilir aray�z�n�...
    public void run() {System.out.println ("Bir Runnable/Ko�turulabilir aray�z� siciminden Selamlar!");}
    public static void main (String args[]) {(new Thread (new J3c_4a())).start();}
} // J3c_4a s�n�f� sonu...

/* ��kt�:
**  >java J3c_4a  **
Bir Runnable/Ko�turulabilir aray�z� siciminden Selamlar!
*/