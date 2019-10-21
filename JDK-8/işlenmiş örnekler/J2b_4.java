// J2b_4.java: BitDemo (�kiliG�steri) �rne�i.

class J2b_4 {
    public static void main (String[] args) {
        int say�1 = 0x000F; // Onalt�l�k->Ondal�k=F(15) * 16^0=15...
        int say�2 = 0x2222; // Onalt�l�k->Ondal�k=2*16^3 + 2*16^2 + 2*16^1 + 2*16^0=8738

        System.out.println ("int say�1 = 0x000F: [" + say�1 + "]");
        System.out.println ("int say�2 = 0x2222: [" + say�2 + "]");
        System.out.println ("say�1 &/ve say�2: [" + (say�1 & say�2) + "]"); // 0010 0010 0010 0010 & 0000 0000 0000 1111 = 0010=>2...
    } // main(..) metodu sonu...
} // J2b_4 s�n�f� sonu...

/* ��kt�:
**  >java J2b_4  **
int say�1 = 0x000F: [15]
int say�2 = 0x2222: [8738]
say�1 &/ve say�2: [2]
*/