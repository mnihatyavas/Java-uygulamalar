// J2cq_1.java: Class1 (Sınıf1) örneği.

// Gereken dosya: J2cq_1x.java; Class2
public class J2cq_1 {
    protected İçSınıf1 içSınıf;

    public J2cq_1() {içSınıf = new İçSınıf1();}

    public void dizgeleriGöster() {
        System.out.println (içSınıf.dizgeyiAl() + ".");
        System.out.println (içSınıf.diğerDizgeyiAl() + ".");
    } // dizgeleriGöster() metodu sonu...

    protected class İçSınıf1 {
        public String dizgeyiAl() {return "İçSınıf1: dizgeyiAl() metodu yürütüldü";}
        public String diğerDizgeyiAl() {return "İçSınıf1: diğerDizgeyiAl() metodu yürütüldü";}
    } // İçSınıf1 sınıfı sonu...

    static public void main (String[] args) {
        J2cq_1 sınıf1 = new J2cq_1();
        sınıf1.dizgeleriGöster();
    } // main(..) metodu sonu...
} // J2cq_1 sınıfı sonu...

/* Çıktı:
**  >java J2cq_1  **
İçSınıf1: dizgeyiAl() metodu yürütüldü.
İçSınıf1: diğerDizgeyiAl() metodu yürütüldü.
*/