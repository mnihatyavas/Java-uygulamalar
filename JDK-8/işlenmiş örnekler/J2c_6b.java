// J2c_6b.java: Class2 (S�n�f2) �rne�i.

public class J2c_6b extends J2c_6a {
    public J2c_6b() {i�S�n�f = new ��S�n�f2();}

    static public void main (String[] args) {
        J2c_6b s�n�f2Nesnesi = new J2c_6b();

        s�n�f2Nesnesi.dizgeleriG�ster();
    } // main(..) metodu sonu...

    protected class ��S�n�f2 extends ��S�n�f1 {
        public String di�erDizgeyiAl() {return "di�erDizgeyiAl() metodunun ��S�n�f2 s�r�m� y�r�t�ld�";}
    } // ��S�n�f2 s�n�f� sonu...
} // J2c_6b s�n�f� sonu...

/* ��kt�:
**  >java J2c_6b  **
��S�n�f1: dizgeyiAl() metodu y�r�t�ld�.
di�erDizgeyiAl() metodunun ��S�n�f2 s�r�m� y�r�t�ld�.
*/