// J2b_2a.java: ArrayCopyDemo (DizinKopyalamaGösterisi) örneði.

class J2b_2a {
    public static void main (String[] args) {
        char[] dizindenKopyala = {'k', 'a', 'f', 'e', 'i', 'n', 's', 'i', 'z', 'l', 'e', 'þ', 't', 'i', 'r', 'i', 'l', 'm', 'i', 'þ', 't', 'i', 'r'};
        char[] dizineKopyala = new char[11];

        System.arraycopy (dizindenKopyala, 4, dizineKopyala, 0, 11); // Ýlk endeks:4, kopyalanacak karakter sayýsý:11...
        System.out.println ("Ýlk dizin: [" + new String (dizindenKopyala) + "]");
        System.out.println ("Kopya dizin: [" + new String (dizineKopyala) + "]");
    } // main(..) metodu sonu...
} // J2b_2a sýnýfý sonu...

/* Çýktý:
**  >java J2b_2a  **
Ýlk dizin: [kafeinsizleþtirilmiþtir]
Kopya dizin: [insizleþtir]
*/