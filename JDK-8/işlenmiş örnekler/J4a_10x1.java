// J4a_10x1.java: Calculator (Hesaplay�c�) alt-�rne�i.

public class J4a_10x1 {
    private int a = 0;
    private int b = 0; // b > a farzedelim...

    public void say�lar�Koy (int say�A, int say�B) {
        a = say�A;
        b = say�B;
    } // say�lar�Koy(..) metodu sonu...

    public int topla() {return a + b;}

    public int [] farkKadarSay�Al() {    
        int x = a;
        int uzunluk = (b - a) + 1;
        int [] kapsam = new int [uzunluk];
        for (int i = 0; i < uzunluk; i++) {
            kapsam[i]= x++;
            System.out.println ("kapsam[" + i + "]: " + kapsam[i]);
        } // for d�ng�s� sonu...
        return kapsam;
    } // farkKadarSay�Al() metodu sonu...
} // J4a_10x1 s�n�f� sonu...