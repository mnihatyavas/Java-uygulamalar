// J2fq_1a.java: Adder (Toplay�c�) �rne�i.

public class J2fq_1a {
    public static void main (String[] args) {
        int say�Arg�manlar� = args.length;

        // Bu program komut iletisinden enaz 2 tamsay� arg�man giri�i gerektirir...
        if (say�Arg�manlar� < 2) System.out.println ("Bu program komut iletisinden enaz 2 tamsay� arg�man giri�i gerektirir...");
        else {int arg�manlarToplam� = 0;

            for (int i = 0; i < say�Arg�manlar�; i++) {
                try {arg�manlarToplam� += Integer.valueOf (args[i]).intValue();
                }catch (Exception ist) {arg�manlarToplam� += 0;}
            } // for d�ng�s� sonu...

            // Tamsay� arg�manlar toplam�n� yans�tal�m...
            System.out.println (say�Arg�manlar� + " adet tamsay� arg�manlar�n toplam�: " + arg�manlarToplam� + "'dir.");
        } // else karar� sonu...
    } // main(..) metodu sonu...
} // J2fq_1a s�n�f� sonu...

/* ��kt�:
**  >java J2fq_1a  **
Bu program komut iletisinden enaz 2 tamsay� arg�man giri�i gerektirir...

**  >java J2fq_1a 15 26  ** TEKRAR
2 adet tamsay� arg�manlar�n toplam�: 41'dir.

**  >java J2fq_1a 15 26 45 -3 87  ** TEKRAR
5 adet tamsay� arg�manlar�n toplam�: 170'dir.

**  >java J2fq_1a 15 26 45 -3 87 MNYava�  ** TEKRAR
6 adet tamsay� arg�manlar�n toplam�: 170'dir.

**  >java J2fq_1a bir iki �� d�rt be� alt� yedi  ** TEKRAR
7 adet tamsay� arg�manlar�n toplam�: 0'dir.
*/