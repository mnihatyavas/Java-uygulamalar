// J2c_10.java: HelloWorldAnonymousClasses (SelamDünyaAnonimSýnýflarý) örneði.

public class J2c_10 {
    interface SelamDünya {
        public void selamla(); // gövdesiz soyut metod...
        public void birisiniSelamla (String birisi);
    } // SelamDünya arayüzü sonu...
  
    public void merhabaDe() {
        class ÝngilizceSelamlama implements SelamDünya {
            String isim = "world";
            public void selamla() {birisiniSelamla ("world");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Hello, " + isim + "!");}
        } // ÝngilizceSelamlama sýnýfý sonu...
      
        SelamDünya ingilizceSelamla = new ÝngilizceSelamlama();
        
        SelamDünya fransýzcaSelamla = new SelamDünya() {
            String isim = "tout le monde";
            public void selamla() {birisiniSelamla ("tout le monde");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Salut, " + isim + "!");}
        }; // Anonim Sýnýflý fransýzcaSelamla ifadesinin sonu...
        
        SelamDünya ispanyolcaSelamla = new SelamDünya() {
            String isim = "mundo";
            public void selamla() {birisiniSelamla ("mundo");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Hola, " + isim + "!");}
        }; // Anonim Sýnýflý ispanyolcaSelamla ifadesinin sonu...

        SelamDünya türkçeSelamla = new SelamDünya() {
            String isim = "dünya";
            public void selamla() {birisiniSelamla ("dünya");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Merhaba, " + isim + "!");}
        }; // Anonim Sýnýflý türkçeSelamla ifadesinin sonu...

        ingilizceSelamla.selamla();
        fransýzcaSelamla.birisiniSelamla ("Fred");
        ispanyolcaSelamla.selamla();
        türkçeSelamla.birisiniSelamla ("Nihat");
    } // merhabaDe() metodu sonu...

    public static void main (String... args) {
        J2c_10 uygulamam = new J2c_10();
        uygulamam.merhabaDe();
    } // main(..) metodu sonu...
} // J2c_10 sýnýfý sonu...

/* Çýktý:
**  >java J2c_10  **
Hello, world!
Salut, Fred!
Hola, mundo!
Merhaba, Nihat!
*/