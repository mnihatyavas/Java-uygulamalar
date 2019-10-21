// J2c_8.java: DataStructure (VeriYapýsý) örneði.

public class J2c_8 {
    private final static int EBAT = 15;
    private int[] dizinÝnt = new int[EBAT]; // Tamsayý içerecek bir dizin yaratalým...
    
    public J2c_8() {for (int i = 0; i < EBAT; i++) dizinÝnt[i] = i*i*i;} // Sýnýf kurucusu...
    
    interface VeriYapýsýTarayýcýsý extends java.util.Iterator<Integer>{}

    /* ÇiftTarayýcý sýnýfý VeriYapýsýTarayýcýsý arayüzünü iþletir/implements,
     * ki o da java.util.Iterator<Integer> arayüzüne geniþler/extends.*/
    private class ÇiftTarayýcý implements VeriYapýsýTarayýcýsý {
        // Dizinimizin ilk endeksinden baþlayalým...
        private int sonrakiEndeks = 0;

        public boolean hasNext() {// abstract Iterator hasNext() metodunu override yapacak...
            // Mevcut elemanýn dizinimizin son elemaný olup olmadýðýný kontrol edelim...
            return (sonrakiEndeks <= EBAT - 1);
        } // hasNext() metodu sonu...

        public Integer next() {// abstract Iterator next() metodunu override yapacak...
            // Dizinin çift endeksli deðerini alalým...
            Integer dönüþDeðeri = Integer.valueOf (dizinÝnt[sonrakiEndeks]);

            // Sonraki çift endekse artýralým...
            sonrakiEndeks += 2;
            return dönüþDeðeri;
        } // next() metodu sonu...
    } // ÇiftTarayýcý sýnýfý sonu...

    public void çiftEndekslileriGörüntüle() {
        // Dizinimizin çift endekslilerini görüntüleyelim...
        VeriYapýsýTarayýcýsý tarayýcý = this.new ÇiftTarayýcý();
        int endeks = 0;
        System.out.println (EBAT + " adet dizi elemanlarýnýn sadece çift endekslilerin alt-alta dökümü==>");
        while (tarayýcý.hasNext()) {
            System.out.println (endeks + ".endeks elemaný: [" + tarayýcý.next() + "]");
            endeks += 2;
        } // while döngüsü sonu...
    } // çiftEndekslileriGörüntüle() metodu sonu...

    public static void main (String s[]) {
        // Yaratacaðýmýz dizinimize tamsayý deðerler doldurup, çift endekslileri görüntüleyelim...
        J2c_8 dataStructure = new J2c_8();

        dataStructure.çiftEndekslileriGörüntüle();
    } // main(..) metodu sonu...
} // J2c_8 sýnýfý sonu...

/* Çýktý:
**  >java J2c_8  **
15 adet dizi elemanlarýnýn sadece çift endekslilerin alt-alta dökümü==>
0.endeks elemaný: [0]
2.endeks elemaný: [8]
4.endeks elemaný: [64]
6.endeks elemaný: [216]
8.endeks elemaný: [512]
10.endeks elemaný: [1000]
12.endeks elemaný: [1728]
14.endeks elemaný: [2744]
*/