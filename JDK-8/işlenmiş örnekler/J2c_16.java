// J2c_16.java: ShadowTest (GölgeTesti) örneði.

public class J2c_16 {
    public int x = 0;

    class ÝlkSeviye {
        public int x = 1;

        void ilkSeviyeMetodu (int x) {
            System.out.println ("ilkSeviyeMetodu argümaný , x = " + x); // 23...
            System.out.println ("Ýçsýnýf tip deðiþkeni, this.x = " + this.x); // 1..
            System.out.println ("J2c_16.this.x = " + J2c_16.this.x); // 0..
        } // ilkSeviyeMetodu(..) sonu...
    } // ÝlkSeviye sýnýfý sonu...

    public static void main (String... args) {
        J2c_16 nesne1 = new J2c_16();
        J2c_16.ÝlkSeviye nesne2 = nesne1.new ÝlkSeviye();

        nesne2.ilkSeviyeMetodu (23);
    } // main(..) metodu sonu...
} // J2c_16 sýnýfý sonu...

/* Çýktý:
**  >java J2c_16  **
ilkSeviyeMetodu argümaný , x = 23
Ýçsýnýf tip deðiþkeni, this.x = 1
J2c_16.this.x = 0
*/