// J2fq_5.java: ThisHappens (B�yleOlur) �rne�i.

public class J2fq_5 {
    public static void main (String[] args) {
        StringBuffer[] dizinTampon = new StringBuffer[10];

/* Derleyici Hatas�==>
        for (int i = 0; i < dizinTampon.length; i ++)
            dizinTampon[i].append ("StringBuffer at index " + i);
*/
        for (int i = 0; i < dizinTampon.length; i ++) {// Hatas�z �al���r...
            dizinTampon[i] = new StringBuffer();
            dizinTampon[i].append (i + ".endeksteki StringBuffer");
            System.out.println (dizinTampon[i]);
        } // for d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J2fq_5 s�n�f� sonu...

/* ��kt�:
**  >java J2fq_5  **
0.endeksteki StringBuffer
1.endeksteki StringBuffer
2.endeksteki StringBuffer
3.endeksteki StringBuffer
4.endeksteki StringBuffer
5.endeksteki StringBuffer
6.endeksteki StringBuffer
7.endeksteki StringBuffer
8.endeksteki StringBuffer
9.endeksteki StringBuffer
*/