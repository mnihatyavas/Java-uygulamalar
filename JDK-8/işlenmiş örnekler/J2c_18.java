// J2c_18.java: StackTest (Y���nTesti) �rne�i.

// Gereken dosya: J2c_18x.java; Stack
public class J2c_18 {
    public static void main (String[] args) {
        J2c_18x y���nNesnesi = new J2c_18x (3);
        y���nNesnesi.ekle ("Selam!");
        y���nNesnesi.ekle ("Merhaba!");
        y���nNesnesi.ekle ("�yi G�nler!");

        System.out.format ("%s%n", y���nNesnesi.��kar());
        System.out.format ("%s%n", y���nNesnesi.��kar());
        System.out.format ("%s%n", y���nNesnesi.��kar());
    } // main(..) metodu sonu...
} // J2c_18 s�n�f� sonu...

/* ��kt�:
**  >java J2c_18  **
�yi G�nler!
Merhaba!
Selam!
*/