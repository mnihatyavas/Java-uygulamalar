// J2b_6b.java: BreakWithLabelDemo (EtiketliKýrmaGösterisi) örneði.

class J2b_6b {
    public static void main (String[] args) {
        int[][] dizinÝnt = {// (0,0)-->(2,3) endeks konumlarý...
            {32, -87, 3, 589},
            {12, 1076, 2000, -8},
            {-622, 127, 77, 955}
        }; // 3x4'lük dizin tanýmý sonu...
        int aranan;

        try {aranan = Integer.valueOf (args[0]);
        }catch (Exception ist) {aranan = 12;}

        int i;
        int j = 0;
        boolean bulundu = false;

        Ara:
        for (i = 0; i < dizinÝnt.length; i++) // for-i döngü bloðuna gerek yok...
            for (j = 0; j < dizinÝnt[i].length; j++) {
                if (dizinÝnt[i][j] == aranan) {
                    bulundu = true;
                    break Ara; // for-i döngü baþýna kýrar ve döngüyü esgeçer...
                } // if kararý sonu...
            } // for-j döngüsü sonu...

        if (bulundu) System.out.println ("Aranan: " + aranan + " sayýsý (" + i + "," + j + ").endekste BULUNDU!");
        else System.out.println ("Aranan: " + aranan + " sayýsý dizinde BULUNAMADI!");
    } // main(..) metodu sonu...
} // J2b_6b sýnýfý sonu...

/* Çýktý:
**  >java J2b_6b abc  **
Aranan: 12 sayýsý (1,0).endekste BULUNDU!

**  >java J2b_6b -622  ** TEKRAR
Aranan: -622 sayýsý (2,0).endekste BULUNDU!

**  >java J2b_6b 98  ** TEKRAR
Aranan: 98 sayýsý dizinde BULUNAMADI!
*/