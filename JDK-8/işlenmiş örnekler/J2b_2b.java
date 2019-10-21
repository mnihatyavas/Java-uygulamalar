// J2b_2b.java: ArrayCopyOfDemo (G�sterininDizinKopyalamas�) �rne�i.

class J2b_2b {
    public static void main (String[] args) {
        char[] dizindenKopyala = {'k', 'a', 'f', 'e', 'i', 'n', 's', 'i', 'z', 'l', 'e', '�', 't', 'i', 'r', 'i', 'l', 'm', 'i', '�', 't', 'i', 'r'};
        char[] dizineKopyala = java.util.Arrays.copyOfRange (dizindenKopyala, 4, 15); // Kopyalama [4, 15)...

        System.out.println ("�lk dizin: [" + new String (dizindenKopyala) + "]");
        System.out.println ("Kopya dizin: [" + new String (dizineKopyala) + "]");
    } // main(..) metodu sonu...
} // J2b_2b s�n�f� sonu...

/* ��kt�:
**  >java J2b_2b  **
�lk dizin: [kafeinsizle�tirilmi�tir]
Kopya dizin: [insizle�tir]
*/