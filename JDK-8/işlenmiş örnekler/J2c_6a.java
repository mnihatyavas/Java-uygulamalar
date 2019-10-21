// J2c_6a.java: Class1 (S�n�f1) �rne�i.

public class J2c_6a {
    protected ��S�n�f1 i�S�n�f;

    public J2c_6a() {i�S�n�f = new ��S�n�f1();}

    public void dizgeleriG�ster() {
        System.out.format ("%s.%n", i�S�n�f.dizgeyiAl());
        System.out.format ("%s.%n", i�S�n�f.di�erDizgeyiAl());
    } // dizgeleriG�ster() metodu sonu...

    static public void main (String[] args) {
        J2c_6a s�n�f1Nesnesi = new J2c_6a();
        s�n�f1Nesnesi.dizgeleriG�ster();
    } // main(..) s�n�f� sonu...

    protected class ��S�n�f1 {
        public String dizgeyiAl() {return "��S�n�f1: dizgeyiAl() metodu y�r�t�ld�";}
        public String di�erDizgeyiAl() {return "��S�n�f1: di�erDizgeyiAl() metodu y�r�t�ld�";}
    } // ��S�n�f1 s�n�f� sonu...
} // J2c_6a s�n�f� sonu...

/* ��kt�:
**  >java J2c_6a  **
��S�n�f1: dizgeyiAl() metodu y�r�t�ld�.
��S�n�f1: di�erDizgeyiAl() metodu y�r�t�ld�.
*/