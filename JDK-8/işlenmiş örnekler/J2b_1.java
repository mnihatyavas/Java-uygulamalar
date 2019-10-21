// J2b_1.java: ArithmeticDemo (AritmetikGösteri) örneği.

class J2b_1 {
    public static void main (String[] args) {
        int sonuç = 1 + 2; // Şimdi sonuç 3'tür...
        System.out.println ("1 + 2 = [" + sonuç + "]");
        int önceki_sonuç = sonuç;

        sonuç = sonuç - 1; // Şimdi sonuç 2'dir...
        System.out.println (önceki_sonuç + " - 1 = [" + sonuç + "]");
        önceki_sonuç = sonuç;

        sonuç = sonuç * 2; // Şimdi sonuç 4'tür...
        System.out.println (önceki_sonuç + " * 2 = [" + sonuç + "]");
        önceki_sonuç = sonuç;

        sonuç = sonuç / 2; // Şimdi sonuç 2'dir...
        System.out.println (önceki_sonuç + " / 2 = [" + sonuç + "]");
        önceki_sonuç = sonuç;

        sonuç = sonuç + 8; // Şimdi sonuç 10'dur...
        System.out.println (önceki_sonuç + " + 8 = [" + sonuç + "]");
        önceki_sonuç = sonuç;

        sonuç = sonuç % 7; // Şimdi sonuç 3'tür...
        System.out.println (önceki_sonuç + " % 7 = [" + sonuç + "]");
    } // main(..) metodu sonu...
} // J2b_1

/* Çıktı:
**  >java J2b_1 **
1 + 2 = [3]
3 - 1 = [2]
2 * 2 = [4]
4 / 2 = [2]
2 + 8 = [10]
10 % 7 = [3]
*/