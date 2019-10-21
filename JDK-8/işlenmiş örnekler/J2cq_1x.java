// J2cq_1x.java: Class2 (Sınıf2) alt-örneği.

public class J2cq_1x extends J2cq_1 {
    public J2cq_1x() {içSınıf = new İçSınıf2();} // kurucu...

    protected class İçSınıf2 extends İçSınıf1 {
        public String diğerDizgeyiAl() {
            return "diğerDizgeyiAl() metodunun İçSınıf2 sürümü yürütüldü";
        } // diğerDizgeyiAl() metodu sonu...
    } // İçSınıf2 sınıfı sonu...

    static public void main (String[] args) {
        J2cq_1x sınıf2 = new J2cq_1x();
        sınıf2.dizgeleriGöster();
    } // main(..) metodu sonu...
} // J2cq_1x sınıfı sonu...

/* Çıktı:
**  >java J2cq_1x  **
İçSınıf1: dizgeyiAl() metodu yürütüldü.
diğerDizgeyiAl() metodunun İçSınıf2 sürümü yürütüldü.
*/