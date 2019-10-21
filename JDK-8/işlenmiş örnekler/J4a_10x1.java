// J4a_10x1.java: Calculator (Hesaplayıcı) alt-örneği.

public class J4a_10x1 {
    private int a = 0;
    private int b = 0; // b > a farzedelim...

    public void sayılarıKoy (int sayıA, int sayıB) {
        a = sayıA;
        b = sayıB;
    } // sayılarıKoy(..) metodu sonu...

    public int topla() {return a + b;}

    public int [] farkKadarSayıAl() {    
        int x = a;
        int uzunluk = (b - a) + 1;
        int [] kapsam = new int [uzunluk];
        for (int i = 0; i < uzunluk; i++) {
            kapsam[i]= x++;
            System.out.println ("kapsam[" + i + "]: " + kapsam[i]);
        } // for döngüsü sonu...
        return kapsam;
    } // farkKadarSayıAl() metodu sonu...
} // J4a_10x1 sınıfı sonu...