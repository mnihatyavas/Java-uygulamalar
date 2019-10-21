// J2b_2a.java: ArrayCopyDemo (DizinKopyalamaG�sterisi) �rne�i.

class J2b_2a {
    public static void main (String[] args) {
        char[] dizindenKopyala = {'k', 'a', 'f', 'e', 'i', 'n', 's', 'i', 'z', 'l', 'e', '�', 't', 'i', 'r', 'i', 'l', 'm', 'i', '�', 't', 'i', 'r'};
        char[] dizineKopyala = new char[11];

        System.arraycopy (dizindenKopyala, 4, dizineKopyala, 0, 11); // �lk endeks:4, kopyalanacak karakter say�s�:11...
        System.out.println ("�lk dizin: [" + new String (dizindenKopyala) + "]");
        System.out.println ("Kopya dizin: [" + new String (dizineKopyala) + "]");
    } // main(..) metodu sonu...
} // J2b_2a s�n�f� sonu...

/* ��kt�:
**  >java J2b_2a  **
�lk dizin: [kafeinsizle�tirilmi�tir]
Kopya dizin: [insizle�tir]
*/