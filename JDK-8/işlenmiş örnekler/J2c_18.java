// J2c_18.java: StackTest (YýðýnTesti) örneði.

// Gereken dosya: J2c_18x.java; Stack
public class J2c_18 {
    public static void main (String[] args) {
        J2c_18x yýðýnNesnesi = new J2c_18x (3);
        yýðýnNesnesi.ekle ("Selam!");
        yýðýnNesnesi.ekle ("Merhaba!");
        yýðýnNesnesi.ekle ("Ýyi Günler!");

        System.out.format ("%s%n", yýðýnNesnesi.çýkar());
        System.out.format ("%s%n", yýðýnNesnesi.çýkar());
        System.out.format ("%s%n", yýðýnNesnesi.çýkar());
    } // main(..) metodu sonu...
} // J2c_18 sýnýfý sonu...

/* Çýktý:
**  >java J2c_18  **
Ýyi Günler!
Merhaba!
Selam!
*/