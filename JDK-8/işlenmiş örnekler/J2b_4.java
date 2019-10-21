// J2b_4.java: BitDemo (İkiliGösteri) örneği.

class J2b_4 {
    public static void main (String[] args) {
        int sayı1 = 0x000F; // Onaltılık->Ondalık=F(15) * 16^0=15...
        int sayı2 = 0x2222; // Onaltılık->Ondalık=2*16^3 + 2*16^2 + 2*16^1 + 2*16^0=8738

        System.out.println ("int sayı1 = 0x000F: [" + sayı1 + "]");
        System.out.println ("int sayı2 = 0x2222: [" + sayı2 + "]");
        System.out.println ("sayı1 &/ve sayı2: [" + (sayı1 & sayı2) + "]"); // 0010 0010 0010 0010 & 0000 0000 0000 1111 = 0010=>2...
    } // main(..) metodu sonu...
} // J2b_4 sınıfı sonu...

/* Çıktı:
**  >java J2b_4  **
int sayı1 = 0x000F: [15]
int sayı2 = 0x2222: [8738]
sayı1 &/ve sayı2: [2]
*/