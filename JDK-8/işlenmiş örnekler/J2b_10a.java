// J2b_10a.java: ContinueDemo (DevamGösterimi) örneði.

class J2b_10a {
    public static void main (String[] args) {
        char aranan = args.length > 0? args[0].charAt(0) : 'a';

        String cümle = "Bu cümle içindeki herhangi bir karakterin" + " kaç kez geçtiðini tespit edebilirsiniz.";
        int uzunluk = cümle.length();
        int sayaç = 0;

        for (int i = 0; i < uzunluk; i++) {
            if (cümle.charAt (i) != aranan) continue; // aradýðýmýz karakter deðilse devam et (kalan kodlarý segeç)...
            sayaç++; // Araþtýrdýðýmýz karakterse sayaç'ý bir artýr...
        } // for döngüsü sonu...

        System.out.println ("[" + cümle + "] cümlesi içinde tespit edilen [" + aranan + "] karakteri sayýsý: " + sayaç);
    } // main(..) metodu sonu...
}  // J2b_10a sýnýfý sonu...

/* Çýktý:
**  >java J2b_10a  **
[Bu cümle içindeki herhangi bir karakterin kaç kez geçtiðini tespit edebilirsiniz.] cümlesi içinde tespit edilen [a] karakteri sayýsý: 4

**  >java J2b_10a i  ** TEKRAR
[Bu cümle içindeki herhangi bir karakterin kaç kez geçtiðini tespit edebilirsiniz.] cümlesi içinde tespit edilen [i] karakteri sayýsý: 14

**  >java J2b_10a elma  ** TEKRAR
[Bu cümle içindeki herhangi bir karakterin kaç kez geçtiðini tespit edebilirsiniz.] cümlesi içinde tespit edilen [e] karakteri sayýsý: 9
*/