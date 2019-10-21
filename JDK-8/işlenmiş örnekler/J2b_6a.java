// J2b_6a.java: BreakDemo (K�rmaG�sterisi) �rne�i.

class J2b_6a {
    public static void main (String[] args) {
        int[] dizin�nt = {32, 87, 3, 589, 12, 1076, 2000, 8, 622, 127};
        int aranan;

        try {aranan = Integer.valueOf (args[0]);
        }catch (Exception ist) {aranan = 12;}

        int i;
        boolean bulundu = false;

        for (i = 0; i < dizin�nt.length; i++) {
            if (dizin�nt[i] == aranan) {
                bulundu = true;
                break; // for d�ng�s�nden tamamen ��kar...
            } // if karar� sonu...
        } // for d�ng�s� sonu...

        if (bulundu) System.out.println ("Aranan: " + aranan + " say�s� " + i + ".endekste BULUNDU!");
        else System.out.println ("Aranan: " + aranan + " say�s� dizinde BULUNAMADI!");
    } // main(..) metodu sonu...
} // J2b_6a s�n�f� sonu...

/* ��kt�:
**  >java J2b_6a  **
Aranan: 12 say�s� 4.endekste BULUNDU!

**  >java J2b_6a 622  ** TEKRAR
Aranan: 622 say�s� 8.endekste BULUNDU!

**  >java J2b_6a -54  ** TEKRAR
Aranan: -54 say�s� dizinde BULUNAMADI!
*/