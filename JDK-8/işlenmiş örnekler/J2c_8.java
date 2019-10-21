// J2c_8.java: DataStructure (VeriYap�s�) �rne�i.

public class J2c_8 {
    private final static int EBAT = 15;
    private int[] dizin�nt = new int[EBAT]; // Tamsay� i�erecek bir dizin yaratal�m...
    
    public J2c_8() {for (int i = 0; i < EBAT; i++) dizin�nt[i] = i*i*i;} // S�n�f kurucusu...
    
    interface VeriYap�s�Taray�c�s� extends java.util.Iterator<Integer>{}

    /* �iftTaray�c� s�n�f� VeriYap�s�Taray�c�s� aray�z�n� i�letir/implements,
     * ki o da java.util.Iterator<Integer> aray�z�ne geni�ler/extends.*/
    private class �iftTaray�c� implements VeriYap�s�Taray�c�s� {
        // Dizinimizin ilk endeksinden ba�layal�m...
        private int sonrakiEndeks = 0;

        public boolean hasNext() {// abstract Iterator hasNext() metodunu override yapacak...
            // Mevcut eleman�n dizinimizin son eleman� olup olmad���n� kontrol edelim...
            return (sonrakiEndeks <= EBAT - 1);
        } // hasNext() metodu sonu...

        public Integer next() {// abstract Iterator next() metodunu override yapacak...
            // Dizinin �ift endeksli de�erini alal�m...
            Integer d�n��De�eri = Integer.valueOf (dizin�nt[sonrakiEndeks]);

            // Sonraki �ift endekse art�ral�m...
            sonrakiEndeks += 2;
            return d�n��De�eri;
        } // next() metodu sonu...
    } // �iftTaray�c� s�n�f� sonu...

    public void �iftEndekslileriG�r�nt�le() {
        // Dizinimizin �ift endekslilerini g�r�nt�leyelim...
        VeriYap�s�Taray�c�s� taray�c� = this.new �iftTaray�c�();
        int endeks = 0;
        System.out.println (EBAT + " adet dizi elemanlar�n�n sadece �ift endekslilerin alt-alta d�k�m�==>");
        while (taray�c�.hasNext()) {
            System.out.println (endeks + ".endeks eleman�: [" + taray�c�.next() + "]");
            endeks += 2;
        } // while d�ng�s� sonu...
    } // �iftEndekslileriG�r�nt�le() metodu sonu...

    public static void main (String s[]) {
        // Yarataca��m�z dizinimize tamsay� de�erler doldurup, �ift endekslileri g�r�nt�leyelim...
        J2c_8 dataStructure = new J2c_8();

        dataStructure.�iftEndekslileriG�r�nt�le();
    } // main(..) metodu sonu...
} // J2c_8 s�n�f� sonu...

/* ��kt�:
**  >java J2c_8  **
15 adet dizi elemanlar�n�n sadece �ift endekslilerin alt-alta d�k�m�==>
0.endeks eleman�: [0]
2.endeks eleman�: [8]
4.endeks eleman�: [64]
6.endeks eleman�: [216]
8.endeks eleman�: [512]
10.endeks eleman�: [1000]
12.endeks eleman�: [1728]
14.endeks eleman�: [2744]
*/