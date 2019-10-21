// J2c_16.java: ShadowTest (G�lgeTesti) �rne�i.

public class J2c_16 {
    public int x = 0;

    class �lkSeviye {
        public int x = 1;

        void ilkSeviyeMetodu (int x) {
            System.out.println ("ilkSeviyeMetodu arg�man� , x = " + x); // 23...
            System.out.println ("��s�n�f tip de�i�keni, this.x = " + this.x); // 1..
            System.out.println ("J2c_16.this.x = " + J2c_16.this.x); // 0..
        } // ilkSeviyeMetodu(..) sonu...
    } // �lkSeviye s�n�f� sonu...

    public static void main (String... args) {
        J2c_16 nesne1 = new J2c_16();
        J2c_16.�lkSeviye nesne2 = nesne1.new �lkSeviye();

        nesne2.ilkSeviyeMetodu (23);
    } // main(..) metodu sonu...
} // J2c_16 s�n�f� sonu...

/* ��kt�:
**  >java J2c_16  **
ilkSeviyeMetodu arg�man� , x = 23
��s�n�f tip de�i�keni, this.x = 1
J2c_16.this.x = 0
*/