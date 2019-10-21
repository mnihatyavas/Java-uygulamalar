// J2b_6b.java: BreakWithLabelDemo (EtiketliK�rmaG�sterisi) �rne�i.

class J2b_6b {
    public static void main (String[] args) {
        int[][] dizin�nt = {// (0,0)-->(2,3) endeks konumlar�...
            {32, -87, 3, 589},
            {12, 1076, 2000, -8},
            {-622, 127, 77, 955}
        }; // 3x4'l�k dizin tan�m� sonu...
        int aranan;

        try {aranan = Integer.valueOf (args[0]);
        }catch (Exception ist) {aranan = 12;}

        int i;
        int j = 0;
        boolean bulundu = false;

        Ara:
        for (i = 0; i < dizin�nt.length; i++) // for-i d�ng� blo�una gerek yok...
            for (j = 0; j < dizin�nt[i].length; j++) {
                if (dizin�nt[i][j] == aranan) {
                    bulundu = true;
                    break Ara; // for-i d�ng� ba��na k�rar ve d�ng�y� esge�er...
                } // if karar� sonu...
            } // for-j d�ng�s� sonu...

        if (bulundu) System.out.println ("Aranan: " + aranan + " say�s� (" + i + "," + j + ").endekste BULUNDU!");
        else System.out.println ("Aranan: " + aranan + " say�s� dizinde BULUNAMADI!");
    } // main(..) metodu sonu...
} // J2b_6b s�n�f� sonu...

/* ��kt�:
**  >java J2b_6b abc  **
Aranan: 12 say�s� (1,0).endekste BULUNDU!

**  >java J2b_6b -622  ** TEKRAR
Aranan: -622 say�s� (2,0).endekste BULUNDU!

**  >java J2b_6b 98  ** TEKRAR
Aranan: 98 say�s� dizinde BULUNAMADI!
*/