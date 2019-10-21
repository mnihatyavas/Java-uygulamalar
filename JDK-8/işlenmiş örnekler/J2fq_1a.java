// J2fq_1a.java: Adder (Toplayýcý) örneði.

public class J2fq_1a {
    public static void main (String[] args) {
        int sayýArgümanlarý = args.length;

        // Bu program komut iletisinden enaz 2 tamsayý argüman giriþi gerektirir...
        if (sayýArgümanlarý < 2) System.out.println ("Bu program komut iletisinden enaz 2 tamsayý argüman giriþi gerektirir...");
        else {int argümanlarToplamý = 0;

            for (int i = 0; i < sayýArgümanlarý; i++) {
                try {argümanlarToplamý += Integer.valueOf (args[i]).intValue();
                }catch (Exception ist) {argümanlarToplamý += 0;}
            } // for döngüsü sonu...

            // Tamsayý argümanlar toplamýný yansýtalým...
            System.out.println (sayýArgümanlarý + " adet tamsayý argümanlarýn toplamý: " + argümanlarToplamý + "'dir.");
        } // else kararý sonu...
    } // main(..) metodu sonu...
} // J2fq_1a sýnýfý sonu...

/* Çýktý:
**  >java J2fq_1a  **
Bu program komut iletisinden enaz 2 tamsayý argüman giriþi gerektirir...

**  >java J2fq_1a 15 26  ** TEKRAR
2 adet tamsayý argümanlarýn toplamý: 41'dir.

**  >java J2fq_1a 15 26 45 -3 87  ** TEKRAR
5 adet tamsayý argümanlarýn toplamý: 170'dir.

**  >java J2fq_1a 15 26 45 -3 87 MNYavaþ  ** TEKRAR
6 adet tamsayý argümanlarýn toplamý: 170'dir.

**  >java J2fq_1a bir iki üç dört beþ altý yedi  ** TEKRAR
7 adet tamsayý argümanlarýn toplamý: 0'dir.
*/