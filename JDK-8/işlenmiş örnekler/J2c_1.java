// J2c_1.java: AbstractClassTest (SoyutSınıfTesti) örneği.

/* Gerekli dosyalar:
 *   J2c_1x1.java=AlienCreatureOne.java
 *   J2c_1x2.java=AlienCreatureTwo.java
 */
public class J2c_1 {
    public static void main (String[] args) {
        // Yabancı bir (J2c_1x1) sınıfını kontrol edelim...
        J2c_1x1 nesne1 = new J2c_1x1();
        System.out.format ("%s%n", nesne1.yaşamBaşlar());
        System.out.format ("%s%n", nesne1.yaşar());
        System.out.format ("%s%n%n", nesne1.yaşamSonlanır());
        
        // Yabancı iki (J2c_1x2) sınıfını kontrol edelim...
        J2c_1x2 nesne2 = new J2c_1x2();
        System.out.format ("%s%n", nesne2.yaşamBaşlar());
        System.out.format ("%s%n", nesne2.yaşar());
        System.out.format ("%s%n", nesne2.yaşamSonlanır());
    } // main(..) metodu sonu...
} // J2c_1 sınıfı sonu...

/* Çıktı:
**  >java J2c_1  **
Merhaba, ben buradayım.
Biz yabancı birler yabancı bir besinleriyle mesut mutlu yaşıyoruz.
Hey hayır, her şey bitiyor - güle güle.

Merhaba, ben buradayım.
Biz yabancı ikiler yabancı iki besinleriyle mutlu mesut yaşıyoruz.
Hey hayır, her şey bitiyor - güle güle.
*/