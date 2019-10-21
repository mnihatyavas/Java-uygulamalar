// J2e_4.java: CharSequenceDemo (KarakterDizilimiG�sterimi) �rne�i.

// Bu program verilen dizini tersten sunar...
public class J2e_4 implements CharSequence {
    private String s;

    public J2e_4 (String s) {this.s = s;} // kurucu...
    private int sondan (int i) {return s.length() - 1 - i;}

    public char charAt (int i) {
        if ((i < 0) || (i >= s.length())) throw new StringIndexOutOfBoundsException(i);
        return s.charAt (sondan(i));
    } // charAt(..) metodu sonu...

    public int length() {return s.length();}

    public CharSequence subSequence (int ilk, int son) {
        if (ilk < 0) throw new StringIndexOutOfBoundsException (ilk);
        if (son > s.length()) throw new StringIndexOutOfBoundsException (son);
        if (ilk > son) throw new StringIndexOutOfBoundsException (ilk - son);
        StringBuilder alt = new StringBuilder (s.subSequence (sondan (son), sondan (ilk)));
        return alt.reverse();
    } // altDizge(..) metodu sonu...

    public String toString() {
        StringBuilder s = new StringBuilder (this.s);
        return s.toString();
    } // toString() metodu sonu...

    private static int rasgele (int enB�y�k) {return (int) Math.round (Math.random() * (enB�y�k + 1));}

    public static void main (String[] args) {
        J2e_4 dizge = new J2e_4 ("java.lang.package paketinde bulunan CharSequence aray�z�n� i�leten bir s�n�f yazal�m.");

        System.out.print ("Dizgemiz tersten: [");
        for (int i = 0; i < dizge.length(); i++) System.out.print (dizge.charAt (i));

        int ilk = rasgele (dizge.length() - 1);
        int son = rasgele (dizge.length() - 1 - ilk) + ilk;
        System.out.print ("]\n\nDizgemizdeki [" + ilk + "," + son + ") aras�ndaki rasgele ters ibare: [");
        System.out.print (dizge.subSequence (ilk, son) + "]");

        System.out.println ("\n\nDizgemiz: [" + dizge + "]");
    } // main(..) metodu sonu...
} // J2e_4 s�n�f� sonu...

/* ��kt�:
**  >java J2e_4  **
Dizgemiz tersten: [.m�lazay f�n�s rib netel�i �n�z�yara ecneuqeSrahC nanulub ednitekap egakcap.gnal.avaj]

Dizgemizdeki [68,76) aras�ndaki rasgele ters ibare: [gakcap.g]

Dizgemiz: [java.lang.package paketinde bulunan CharSequence aray�z�n� i�leten bir s�n�f yazal�m.]
*/