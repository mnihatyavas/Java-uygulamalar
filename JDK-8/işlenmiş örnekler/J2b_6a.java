// J2b_6a.java: BreakDemo (KýrmaGösterisi) örneði.

class J2b_6a {
    public static void main (String[] args) {
        int[] dizinÝnt = {32, 87, 3, 589, 12, 1076, 2000, 8, 622, 127};
        int aranan;

        try {aranan = Integer.valueOf (args[0]);
        }catch (Exception ist) {aranan = 12;}

        int i;
        boolean bulundu = false;

        for (i = 0; i < dizinÝnt.length; i++) {
            if (dizinÝnt[i] == aranan) {
                bulundu = true;
                break; // for döngüsünden tamamen çýkar...
            } // if kararý sonu...
        } // for döngüsü sonu...

        if (bulundu) System.out.println ("Aranan: " + aranan + " sayýsý " + i + ".endekste BULUNDU!");
        else System.out.println ("Aranan: " + aranan + " sayýsý dizinde BULUNAMADI!");
    } // main(..) metodu sonu...
} // J2b_6a sýnýfý sonu...

/* Çýktý:
**  >java J2b_6a  **
Aranan: 12 sayýsý 4.endekste BULUNDU!

**  >java J2b_6a 622  ** TEKRAR
Aranan: 622 sayýsý 8.endekste BULUNDU!

**  >java J2b_6a -54  ** TEKRAR
Aranan: -54 sayýsý dizinde BULUNAMADI!
*/