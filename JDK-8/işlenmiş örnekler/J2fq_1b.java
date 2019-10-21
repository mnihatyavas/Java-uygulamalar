// J2fq_1b.java: FPAdder (FloatFointToplay�c�) �rne�i.

import java.text.DecimalFormat;

public class J2fq_1b {
    public static void main (String[] args) {
        int say�Arg�manlar� = args.length;

        // Bu program komut iletisinden enaz 2 double say� arg�man giri�i gerektirir...
        if (say�Arg�manlar� < 2) System.out.println ("Bu program komut iletisinden enaz 2 double say� arg�man giri�i gerektirir...");
        else {double arg�manlarToplam� = 0;

            for (int i = 0; i < say�Arg�manlar�; i++) {
                try {arg�manlarToplam� += Double.valueOf (args[i]).doubleValue();
                }catch (Exception ist) {arg�manlarToplam� += 0;}
            } // for d�ng�s� sonu...

            // double say� arg�manlar toplam�n� yans�tal�m...
            DecimalFormat bi�imleyicim = new DecimalFormat ("###,###.##");
            String ��kt� = bi�imleyicim.format (arg�manlarToplam�);
            System.out.println (say�Arg�manlar� + " adet double say� arg�manlar�n toplam�: " + ��kt� + "'dir.");
        } // else karar� sonu...
    } // main(..) metodu sonu...
} // J2fq_1b s�n�f� sonu...

/* ��kt�:
**  >java J2fq_1b  **
Bu program komut iletisinden enaz 2 double say� arg�man giri�i gerektirir...

**  >java J2fq_1b 12.87 65  ** TEKRAR
2 adet double say� arg�manlar�n toplam�: 77,87'dir.

**  >java J2fq_1b 12.87 65 -9 34.87654  ** TEKRAR
4 adet double say� arg�manlar�n toplam�: 103,75'dir.

**  >java J2fq_1b 12.87 65 -9 34.87654 Nihat  ** TEKRAR
5 adet double say� arg�manlar�n toplam�: 103,75'dir.

**  >java J2fq_1b bir iki �� d�rt be� 17  ** TEKRAR
6 adet double say� arg�manlar�n toplam�: 17'dir.
*/