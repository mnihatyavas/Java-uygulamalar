// J2cq_1x.java: Class2 (S�n�f2) alt-�rne�i.

public class J2cq_1x extends J2cq_1 {
    public J2cq_1x() {i�S�n�f = new ��S�n�f2();} // kurucu...

    protected class ��S�n�f2 extends ��S�n�f1 {
        public String di�erDizgeyiAl() {
            return "di�erDizgeyiAl() metodunun ��S�n�f2 s�r�m� y�r�t�ld�";
        } // di�erDizgeyiAl() metodu sonu...
    } // ��S�n�f2 s�n�f� sonu...

    static public void main (String[] args) {
        J2cq_1x s�n�f2 = new J2cq_1x();
        s�n�f2.dizgeleriG�ster();
    } // main(..) metodu sonu...
} // J2cq_1x s�n�f� sonu...

/* ��kt�:
**  >java J2cq_1x  **
��S�n�f1: dizgeyiAl() metodu y�r�t�ld�.
di�erDizgeyiAl() metodunun ��S�n�f2 s�r�m� y�r�t�ld�.
*/