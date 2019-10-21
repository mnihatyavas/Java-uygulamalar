// J2c_6b.java: Class2 (Sınıf2) örneği.

public class J2c_6b extends J2c_6a {
    public J2c_6b() {içSınıf = new İçSınıf2();}

    static public void main (String[] args) {
        J2c_6b sınıf2Nesnesi = new J2c_6b();

        sınıf2Nesnesi.dizgeleriGöster();
    } // main(..) metodu sonu...

    protected class İçSınıf2 extends İçSınıf1 {
        public String diğerDizgeyiAl() {return "diğerDizgeyiAl() metodunun İçSınıf2 sürümü yürütüldü";}
    } // İçSınıf2 sınıfı sonu...
} // J2c_6b sınıfı sonu...

/* Çıktı:
**  >java J2c_6b  **
İçSınıf1: dizgeyiAl() metodu yürütüldü.
diğerDizgeyiAl() metodunun İçSınıf2 sürümü yürütüldü.
*/