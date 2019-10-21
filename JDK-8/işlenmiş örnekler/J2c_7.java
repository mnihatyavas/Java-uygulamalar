// J2c_7.java: CreateObjectDemo (YaratNesneG�sterimi) �rne�i.

// Gereken dosyalar: J2c_7x1.java, J2c_7x2.java; Point and Rectangle
public class J2c_7 {
    public static void main (String[] args) {
        // Bir nokta ve iki dikd�rtgen nesnelerini beyan edelim ve yaratal�m...
        J2c_7x1 orijinBir = new J2c_7x1 (23, 94);
        J2c_7x2 dikd�rtgenBir = new J2c_7x2 (orijinBir, 100, 200);
        J2c_7x2 dikd�rtgen�ki = new J2c_7x2 (50, 100);
		
        // dikd�rtgenBir'in geni�lik, y�kseklik ve alan�n� g�sterelim...
        System.out.println ("dikd�rtgenBir'in geni�li�i: [" + dikd�rtgenBir.geni�lik + "]");
        System.out.println ("dikd�rtgenBir'in y�ksekli�i: [" + dikd�rtgenBir.y�kseklik + "]");
        System.out.println ("dikd�rtgenBir'in alan�: [" + dikd�rtgenBir.alan�Al() + "]");

        // dikd�rtgen�ki'nin geni�lik, y�kseklik ve alan�n� g�sterelim...
        System.out.println ("\ndikd�rtgen�ki'nin geni�li�i: [" + dikd�rtgen�ki.geni�lik + "]");
        System.out.println ("dikd�rtgen�ki'nin y�ksekli�i: [" + dikd�rtgen�ki.y�kseklik + "]");
        System.out.println ("dikd�rtgen�ki'nin alan�: [" + dikd�rtgen�ki.alan�Al() + "]");

        // dikd�rtgenBir'in konumunu g�sterelim...
        System.out.println ("\ndikd�rtgenBir'in X konumu: [" + dikd�rtgenBir.orijin.x + "]");
        System.out.println ("dikd�rtgenBir'in Y konumu: [" + dikd�rtgenBir.orijin.y + "]");

        // dikd�rtgen�ki'nin ilk konumunu g�sterelim...
        System.out.println ("\ndikd�rtgen�ki'nin X konumu: [" + dikd�rtgen�ki.orijin.x + "]");
        System.out.println ("dikd�rtgen�ki'nin Y konumu: [" + dikd�rtgen�ki.orijin.y + "]");

        // dikd�rtgen�ki'nin yeni konumunu ayarlayal�m...
        dikd�rtgen�ki.orijin = orijinBir;
		
        // dikd�rtgen�ki'nin yeni konumunu g�sterelim...
        System.out.println ("\ndikd�rtgen�ki'nin yeni X konumu: [" + dikd�rtgen�ki.orijin.x + "]");
        System.out.println ("dikd�rtgen�ki'nin yeni Y konumu: [" + dikd�rtgen�ki.orijin.y + "]");
		
        // dikd�rtgen�ki'yi ba�ka bir konuma ta��y�p yeni konumunu da g�sterelim...
        dikd�rtgen�ki.ta�� (40, 72);
        System.out.println ("\ndikd�rtgen�ki'nin son X konumu: [" + dikd�rtgen�ki.orijin.x + "]");
        System.out.println ("dikd�rtgen�ki'nin son Y konumu: [" + dikd�rtgen�ki.orijin.y + "]");
    } // main(..) metodu sonu...
} // J2c_7 s�n�f� sonu...

/* ��kt�:
**  >java J2c_7  **
dikd�rtgenBir'in geni�li�i: [100]
dikd�rtgenBir'in y�ksekli�i: [200]
dikd�rtgenBir'in alan�: [20000]

dikd�rtgen�ki'nin geni�li�i: [50]
dikd�rtgen�ki'nin y�ksekli�i: [100]
dikd�rtgen�ki'nin alan�: [5000]

dikd�rtgenBir'in X konumu: [23]
dikd�rtgenBir'in Y konumu: [94]

dikd�rtgen�ki'nin X konumu: [0]
dikd�rtgen�ki'nin Y konumu: [0]

dikd�rtgen�ki'nin yeni X konumu: [23]
dikd�rtgen�ki'nin yeni Y konumu: [94]

dikd�rtgen�ki'nin son X konumu: [40]
dikd�rtgen�ki'nin son Y konumu: [72]
*/