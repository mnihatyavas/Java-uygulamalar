// J2c_11.java: LambdaScopeTest (LambdaKapsamTesti) �rne�i.

import java.util.function.Consumer;

public class J2c_11 {
    public int x = 0;

    class BirinciSeviye {
        public int x = 1;

        void metodBirinciSeviye (int x) {
            /* A�a��daki �fade-A i�in derleyici hatas� olu�acakt�r, gerekce de:
             * "local variables referenced from a lambda expression must be final or effectively final"
             * yani "lambda ifadesindeki yerel de�i�ken final veya etkisel final olmal�d�r". */

            Consumer<Integer> t�keticim = (y) ->
            {
                System.out.println ("x = " + x); // �fade-A
                System.out.println ("y = " + y);
                System.out.println ("this.x = " + this.x);
                System.out.println ("J2c_11/LambdaScopeTest.this.x = " + J2c_11.this.x);
            }; // t�keticim Lambda ifadesi sonu...

            t�keticim.accept (x);
        } // metodBirinciSeviye(..) metodu sonu...
    } // BirinciSeviye s�n�f� sonu...

    public static void main (String... args) {
        J2c_11 kapsamTesti = new J2c_11();
        J2c_11.BirinciSeviye birinciSeviye = kapsamTesti.new BirinciSeviye();
        birinciSeviye.metodBirinciSeviye (23);
    } // main(..) metodu sonu...
}// J2c__11 s�n�f� sonu...

/* ��kt�:
**  >java J2c_11 **
x = 23
y = 23
this.x = 1
J2c_11/LambdaScopeTest.this.x = 0
*/