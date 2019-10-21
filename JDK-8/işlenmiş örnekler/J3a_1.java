// J3a_1.java: InputFileTest (GirdiDosyasýTesti) örneði.

// Gereken dosya: J3a_1x.java=InputFileDeclared
public class J3a_1 {
    public static void main (String[] args) throws java.io.IOException {
        String dosyaAdý = args.length > 0? args[0] : "Deneniyor";

        J3a_1x dosya = new J3a_1x (dosyaAdý); // J3a_1x=InputFileDeclared/GirdiDosyasýBeyaný

        System.out.println (dosya.kelimeAl()); // Mevcut .txt dosyasýndaki sadece ilk kelimeyi alýr...
        System.out.println (dosya.kelimeAl()); // 2.kelime...
        System.out.println (dosya.kelimeAl());
        System.out.println (dosya.kelimeAl());
        System.out.println (dosya.kelimeAl()); // 5.kelime...
    } // main(..) metodu sonu...
} // J3a_1 sýnýfý sonu...

/* Çýktý:
**  >java J3a_1  **
Exception in thread "main" java.io.FileNotFoundException: Deneniyor (Sistem beli
rtilen dosyayý bulamýyor)
        at java.io.FileInputStream.open0(Native Method)
        at java.io.FileInputStream.open(Unknown Source)
        at java.io.FileInputStream.<init>(Unknown Source)
        at java.io.FileInputStream.<init>(Unknown Source)
        at java.io.FileReader.<init>(Unknown Source)
        at J3a_1x.<init>(J3a_1x.java:11)
        at J3a_1.main(J3a_1.java:7)

**  >java J3a_1 J3a_1.java  **
//
J3a_1.java:
InputFileTest
(GirdiDosyasýTesti)
örneði.
*/