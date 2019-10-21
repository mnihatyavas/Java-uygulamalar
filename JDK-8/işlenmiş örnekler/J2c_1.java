// J2c_1.java: AbstractClassTest (SoyutS�n�fTesti) �rne�i.

/* Gerekli dosyalar:
 *   J2c_1x1.java=AlienCreatureOne.java
 *   J2c_1x2.java=AlienCreatureTwo.java
 */
public class J2c_1 {
    public static void main (String[] args) {
        // Yabanc� bir (J2c_1x1) s�n�f�n� kontrol edelim...
        J2c_1x1 nesne1 = new J2c_1x1();
        System.out.format ("%s%n", nesne1.ya�amBa�lar());
        System.out.format ("%s%n", nesne1.ya�ar());
        System.out.format ("%s%n%n", nesne1.ya�amSonlan�r());
        
        // Yabanc� iki (J2c_1x2) s�n�f�n� kontrol edelim...
        J2c_1x2 nesne2 = new J2c_1x2();
        System.out.format ("%s%n", nesne2.ya�amBa�lar());
        System.out.format ("%s%n", nesne2.ya�ar());
        System.out.format ("%s%n", nesne2.ya�amSonlan�r());
    } // main(..) metodu sonu...
} // J2c_1 s�n�f� sonu...

/* ��kt�:
**  >java J2c_1  **
Merhaba, ben buraday�m.
Biz yabanc� birler yabanc� bir besinleriyle mesut mutlu ya��yoruz.
Hey hay�r, her �ey bitiyor - g�le g�le.

Merhaba, ben buraday�m.
Biz yabanc� ikiler yabanc� iki besinleriyle mutlu mesut ya��yoruz.
Hey hay�r, her �ey bitiyor - g�le g�le.
*/