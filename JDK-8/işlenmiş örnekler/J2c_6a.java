// J2c_6a.java: Class1 (Sınıf1) örneği.

public class J2c_6a {
    protected İçSınıf1 içSınıf;

    public J2c_6a() {içSınıf = new İçSınıf1();}

    public void dizgeleriGöster() {
        System.out.format ("%s.%n", içSınıf.dizgeyiAl());
        System.out.format ("%s.%n", içSınıf.diğerDizgeyiAl());
    } // dizgeleriGöster() metodu sonu...

    static public void main (String[] args) {
        J2c_6a sınıf1Nesnesi = new J2c_6a();
        sınıf1Nesnesi.dizgeleriGöster();
    } // main(..) sınıfı sonu...

    protected class İçSınıf1 {
        public String dizgeyiAl() {return "İçSınıf1: dizgeyiAl() metodu yürütüldü";}
        public String diğerDizgeyiAl() {return "İçSınıf1: diğerDizgeyiAl() metodu yürütüldü";}
    } // İçSınıf1 sınıfı sonu...
} // J2c_6a sınıfı sonu...

/* Çıktı:
**  >java J2c_6a  **
İçSınıf1: dizgeyiAl() metodu yürütüldü.
İçSınıf1: diğerDizgeyiAl() metodu yürütüldü.
*/