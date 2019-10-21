// J3b_3a.java: CardWriter (VantiYaz�c�) �rne�i.

// Gereken dosya: J3b_3x.java=Cards3
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class J3b_3a {
    public static void main (String[] args) {
        int tak�m, say�;
        try {tak�m = Integer.valueOf (args[0]).intValue();}catch (Exception ist) {tak�m = 4;}
        try {say� = Integer.valueOf (args[1]).intValue();}catch (Exception ist) {say� = 12;}

        J3b_3x kart = new J3b_3x (say�, tak�m); // J3b_3x=Card3/Vanti3...
        System.out.println ("Dosyaya yaz�lacak kart: [" + kart + "]");

        try {FileOutputStream yaz = new FileOutputStream ("kart.ser");
            ObjectOutputStream oos = new ObjectOutputStream (yaz);
            oos.writeObject (kart); // Tek kay�t yaz�lmakta...
            oos.flush();
        }catch (Exception ist) {System.err.println ("Serile�tirme hatas�: " + ist); System.exit (-1);
        } // try-catch blo�u sonu...
        System.out.println ("[" + kart + "] verisi [kart.ser] dosyas�na kaydedilmi�tir.");
    } // main(..) metodu sonu...
} // J3b_3a s�n�f� sonu...

/* ��kt�:
**  >java J3b_3a  **
Dosyaya yaz�lacak kart: [Sinek K�z]
[Sinek K�z] verisi [kart.ser] dosyas�na kaydedilmi�tir.

**  >java J3b_3a 3 10  ** TEKRAR
Dosyaya yaz�lacak kart: [Kupa On]
[Kupa On] verisi [kart.ser] dosyas�na kaydedilmi�tir.
*/