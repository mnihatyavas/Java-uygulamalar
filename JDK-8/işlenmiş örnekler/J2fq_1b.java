// J2fq_1b.java: FPAdder (FloatFointToplayýcý) örneði.

import java.text.DecimalFormat;

public class J2fq_1b {
    public static void main (String[] args) {
        int sayýArgümanlarý = args.length;

        // Bu program komut iletisinden enaz 2 double sayý argüman giriþi gerektirir...
        if (sayýArgümanlarý < 2) System.out.println ("Bu program komut iletisinden enaz 2 double sayý argüman giriþi gerektirir...");
        else {double argümanlarToplamý = 0;

            for (int i = 0; i < sayýArgümanlarý; i++) {
                try {argümanlarToplamý += Double.valueOf (args[i]).doubleValue();
                }catch (Exception ist) {argümanlarToplamý += 0;}
            } // for döngüsü sonu...

            // double sayý argümanlar toplamýný yansýtalým...
            DecimalFormat biçimleyicim = new DecimalFormat ("###,###.##");
            String çýktý = biçimleyicim.format (argümanlarToplamý);
            System.out.println (sayýArgümanlarý + " adet double sayý argümanlarýn toplamý: " + çýktý + "'dir.");
        } // else kararý sonu...
    } // main(..) metodu sonu...
} // J2fq_1b sýnýfý sonu...

/* Çýktý:
**  >java J2fq_1b  **
Bu program komut iletisinden enaz 2 double sayý argüman giriþi gerektirir...

**  >java J2fq_1b 12.87 65  ** TEKRAR
2 adet double sayý argümanlarýn toplamý: 77,87'dir.

**  >java J2fq_1b 12.87 65 -9 34.87654  ** TEKRAR
4 adet double sayý argümanlarýn toplamý: 103,75'dir.

**  >java J2fq_1b 12.87 65 -9 34.87654 Nihat  ** TEKRAR
5 adet double sayý argümanlarýn toplamý: 103,75'dir.

**  >java J2fq_1b bir iki üç dört beþ 17  ** TEKRAR
6 adet double sayý argümanlarýn toplamý: 17'dir.
*/