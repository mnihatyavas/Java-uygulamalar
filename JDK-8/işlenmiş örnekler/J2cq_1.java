// J2cq_1.java: Class1 (S�n�f1) �rne�i.

// Gereken dosya: J2cq_1x.java; Class2
public class J2cq_1 {
    protected ��S�n�f1 i�S�n�f;

    public J2cq_1() {i�S�n�f = new ��S�n�f1();}

    public void dizgeleriG�ster() {
        System.out.println (i�S�n�f.dizgeyiAl() + ".");
        System.out.println (i�S�n�f.di�erDizgeyiAl() + ".");
    } // dizgeleriG�ster() metodu sonu...

    protected class ��S�n�f1 {
        public String dizgeyiAl() {return "��S�n�f1: dizgeyiAl() metodu y�r�t�ld�";}
        public String di�erDizgeyiAl() {return "��S�n�f1: di�erDizgeyiAl() metodu y�r�t�ld�";}
    } // ��S�n�f1 s�n�f� sonu...

    static public void main (String[] args) {
        J2cq_1 s�n�f1 = new J2cq_1();
        s�n�f1.dizgeleriG�ster();
    } // main(..) metodu sonu...
} // J2cq_1 s�n�f� sonu...

/* ��kt�:
**  >java J2cq_1  **
��S�n�f1: dizgeyiAl() metodu y�r�t�ld�.
��S�n�f1: di�erDizgeyiAl() metodu y�r�t�ld�.
*/