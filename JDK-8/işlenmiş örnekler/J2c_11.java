// J2c_11.java: LambdaScopeTest (LambdaKapsamTesti) örneði.

import java.util.function.Consumer;

public class J2c_11 {
    public int x = 0;

    class BirinciSeviye {
        public int x = 1;

        void metodBirinciSeviye (int x) {
            /* Aþaðýdaki Ýfade-A için derleyici hatasý oluþacaktýr, gerekce de:
             * "local variables referenced from a lambda expression must be final or effectively final"
             * yani "lambda ifadesindeki yerel deðiþken final veya etkisel final olmalýdýr". */

            Consumer<Integer> tüketicim = (y) ->
            {
                System.out.println ("x = " + x); // Ýfade-A
                System.out.println ("y = " + y);
                System.out.println ("this.x = " + this.x);
                System.out.println ("J2c_11/LambdaScopeTest.this.x = " + J2c_11.this.x);
            }; // tüketicim Lambda ifadesi sonu...

            tüketicim.accept (x);
        } // metodBirinciSeviye(..) metodu sonu...
    } // BirinciSeviye sýnýfý sonu...

    public static void main (String... args) {
        J2c_11 kapsamTesti = new J2c_11();
        J2c_11.BirinciSeviye birinciSeviye = kapsamTesti.new BirinciSeviye();
        birinciSeviye.metodBirinciSeviye (23);
    } // main(..) metodu sonu...
}// J2c__11 sýnýfý sonu...

/* Çýktý:
**  >java J2c_11 **
x = 23
y = 23
this.x = 1
J2c_11/LambdaScopeTest.this.x = 0
*/