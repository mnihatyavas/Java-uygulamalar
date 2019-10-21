// J2c_7.java: CreateObjectDemo (YaratNesneGösterimi) örneği.

// Gereken dosyalar: J2c_7x1.java, J2c_7x2.java; Point and Rectangle
public class J2c_7 {
    public static void main (String[] args) {
        // Bir nokta ve iki dikdörtgen nesnelerini beyan edelim ve yaratalım...
        J2c_7x1 orijinBir = new J2c_7x1 (23, 94);
        J2c_7x2 dikdörtgenBir = new J2c_7x2 (orijinBir, 100, 200);
        J2c_7x2 dikdörtgenİki = new J2c_7x2 (50, 100);
		
        // dikdörtgenBir'in genişlik, yükseklik ve alanını gösterelim...
        System.out.println ("dikdörtgenBir'in genişliği: [" + dikdörtgenBir.genişlik + "]");
        System.out.println ("dikdörtgenBir'in yüksekliği: [" + dikdörtgenBir.yükseklik + "]");
        System.out.println ("dikdörtgenBir'in alanı: [" + dikdörtgenBir.alanıAl() + "]");

        // dikdörtgenİki'nin genişlik, yükseklik ve alanını gösterelim...
        System.out.println ("\ndikdörtgenİki'nin genişliği: [" + dikdörtgenİki.genişlik + "]");
        System.out.println ("dikdörtgenİki'nin yüksekliği: [" + dikdörtgenİki.yükseklik + "]");
        System.out.println ("dikdörtgenİki'nin alanı: [" + dikdörtgenİki.alanıAl() + "]");

        // dikdörtgenBir'in konumunu gösterelim...
        System.out.println ("\ndikdörtgenBir'in X konumu: [" + dikdörtgenBir.orijin.x + "]");
        System.out.println ("dikdörtgenBir'in Y konumu: [" + dikdörtgenBir.orijin.y + "]");

        // dikdörtgenİki'nin ilk konumunu gösterelim...
        System.out.println ("\ndikdörtgenİki'nin X konumu: [" + dikdörtgenİki.orijin.x + "]");
        System.out.println ("dikdörtgenİki'nin Y konumu: [" + dikdörtgenİki.orijin.y + "]");

        // dikdörtgenİki'nin yeni konumunu ayarlayalım...
        dikdörtgenİki.orijin = orijinBir;
		
        // dikdörtgenİki'nin yeni konumunu gösterelim...
        System.out.println ("\ndikdörtgenİki'nin yeni X konumu: [" + dikdörtgenİki.orijin.x + "]");
        System.out.println ("dikdörtgenİki'nin yeni Y konumu: [" + dikdörtgenİki.orijin.y + "]");
		
        // dikdörtgenİki'yi başka bir konuma taşıyıp yeni konumunu da gösterelim...
        dikdörtgenİki.taşı (40, 72);
        System.out.println ("\ndikdörtgenİki'nin son X konumu: [" + dikdörtgenİki.orijin.x + "]");
        System.out.println ("dikdörtgenİki'nin son Y konumu: [" + dikdörtgenİki.orijin.y + "]");
    } // main(..) metodu sonu...
} // J2c_7 sınıfı sonu...

/* Çıktı:
**  >java J2c_7  **
dikdörtgenBir'in genişliği: [100]
dikdörtgenBir'in yüksekliği: [200]
dikdörtgenBir'in alanı: [20000]

dikdörtgenİki'nin genişliği: [50]
dikdörtgenİki'nin yüksekliği: [100]
dikdörtgenİki'nin alanı: [5000]

dikdörtgenBir'in X konumu: [23]
dikdörtgenBir'in Y konumu: [94]

dikdörtgenİki'nin X konumu: [0]
dikdörtgenİki'nin Y konumu: [0]

dikdörtgenİki'nin yeni X konumu: [23]
dikdörtgenİki'nin yeni Y konumu: [94]

dikdörtgenİki'nin son X konumu: [40]
dikdörtgenİki'nin son Y konumu: [72]
*/