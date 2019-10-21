// J2b_2b.java: ArrayCopyOfDemo (GösterininDizinKopyalamasý) örneði.

class J2b_2b {
    public static void main (String[] args) {
        char[] dizindenKopyala = {'k', 'a', 'f', 'e', 'i', 'n', 's', 'i', 'z', 'l', 'e', 'þ', 't', 'i', 'r', 'i', 'l', 'm', 'i', 'þ', 't', 'i', 'r'};
        char[] dizineKopyala = java.util.Arrays.copyOfRange (dizindenKopyala, 4, 15); // Kopyalama [4, 15)...

        System.out.println ("Ýlk dizin: [" + new String (dizindenKopyala) + "]");
        System.out.println ("Kopya dizin: [" + new String (dizineKopyala) + "]");
    } // main(..) metodu sonu...
} // J2b_2b sýnýfý sonu...

/* Çýktý:
**  >java J2b_2b  **
Ýlk dizin: [kafeinsizleþtirilmiþtir]
Kopya dizin: [insizleþtir]
*/