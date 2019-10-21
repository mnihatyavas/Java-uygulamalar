// J2c_10.java: HelloWorldAnonymousClasses (SelamD�nyaAnonimS�n�flar�) �rne�i.

public class J2c_10 {
    interface SelamD�nya {
        public void selamla(); // g�vdesiz soyut metod...
        public void birisiniSelamla (String birisi);
    } // SelamD�nya aray�z� sonu...
  
    public void merhabaDe() {
        class �ngilizceSelamlama implements SelamD�nya {
            String isim = "world";
            public void selamla() {birisiniSelamla ("world");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Hello, " + isim + "!");}
        } // �ngilizceSelamlama s�n�f� sonu...
      
        SelamD�nya ingilizceSelamla = new �ngilizceSelamlama();
        
        SelamD�nya frans�zcaSelamla = new SelamD�nya() {
            String isim = "tout le monde";
            public void selamla() {birisiniSelamla ("tout le monde");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Salut, " + isim + "!");}
        }; // Anonim S�n�fl� frans�zcaSelamla ifadesinin sonu...
        
        SelamD�nya ispanyolcaSelamla = new SelamD�nya() {
            String isim = "mundo";
            public void selamla() {birisiniSelamla ("mundo");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Hola, " + isim + "!");}
        }; // Anonim S�n�fl� ispanyolcaSelamla ifadesinin sonu...

        SelamD�nya t�rk�eSelamla = new SelamD�nya() {
            String isim = "d�nya";
            public void selamla() {birisiniSelamla ("d�nya");}
            public void birisiniSelamla (String birisi) {isim = birisi; System.out.println ("Merhaba, " + isim + "!");}
        }; // Anonim S�n�fl� t�rk�eSelamla ifadesinin sonu...

        ingilizceSelamla.selamla();
        frans�zcaSelamla.birisiniSelamla ("Fred");
        ispanyolcaSelamla.selamla();
        t�rk�eSelamla.birisiniSelamla ("Nihat");
    } // merhabaDe() metodu sonu...

    public static void main (String... args) {
        J2c_10 uygulamam = new J2c_10();
        uygulamam.merhabaDe();
    } // main(..) metodu sonu...
} // J2c_10 s�n�f� sonu...

/* ��kt�:
**  >java J2c_10  **
Hello, world!
Salut, Fred!
Hola, mundo!
Merhaba, Nihat!
*/