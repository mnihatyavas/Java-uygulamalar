/* J2hq_1.java: CharSequenceDemo (KarakterDizisiG�sterimi) �rne�i.
 *
 * CharSequence aray�z� y�r�t�ld���nden onun 3 metodu override/esge�me
 * yap�lmal�d�r: length(), charAt(..) ve charSequence(..) metodlar�...
 */

// J2hq_1 program� bir dizge sunar, tersler, geli�ig�zel ibarelerini ay�r�r, onlar� da tersler...
public class J2hq_1 implements CharSequence {
    private String dizge;

    public J2hq_1 (String dizge) {// Kurucu...
        // Verilen dizgeyi en ba�ta kurucuda terslemek verimili olurdu, ama daha az e�lenceli!
        this.dizge = dizge; // Kurucu dizgesi, s�n�f tip de�i�keninne atan�yor...
    } // J2hq_1(..) kurucu sonu...

    private int sondan (int i) {return dizge.length() - 1 - i;} // E�er dizge tersse sonu ba�lang�c�d�r...
    public int length() {return dizge.length();}
    private static int rasgele (int tamsay�) {return (int) Math.round (Math.random() * tamsay� + 0.5);} // [0->tamsay�] rasgele say� �retir...
    public String toString() {StringBuilder dizge = new StringBuilder (this.dizge); return dizge.reverse().toString();}

    public char charAt (int i) {
        if ((i < 0) || (i >= dizge.length())) throw new StringIndexOutOfBoundsException (i);
        return dizge.charAt (sondan (i));
    } // charAt(..) metodu sonu...

    public CharSequence subSequence (int ilk, int son) {
        if (ilk < 0) throw new StringIndexOutOfBoundsException (ilk);
        if (son > dizge.length()) throw new StringIndexOutOfBoundsException (son);
        if (ilk > son) throw new StringIndexOutOfBoundsException (ilk - son);
        StringBuilder alt = new StringBuilder (dizge.subSequence (sondan (son), sondan (ilk)));
        return alt.reverse();
    } // altDizge(..) metodu sonu...

    public static void main (String[] args) {
        J2hq_1 dizge = new J2hq_1 (
            "java.lang paketinde bulunan CharSequence aray�z�n� y�r�ten bir s�n�f program� yaz�n.");

        System.out.println ("Orijinal dizge: [" + new StringBuilder (dizge).reverse().toString() + "]");
        System.out.print ("charAt() metoduyla Ters �evrilen dizge: [");
        for (int i = 0; i < dizge.length(); i++) System.out.print (dizge.charAt (i));
        System.out.println ("]\nreverse() metoduyla ters �evrilen dizge: [" + dizge + "]");

        int ilk = rasgele (dizge.length() - 1);
        int son = rasgele (dizge.length() - 1 - ilk) + ilk;
        System.out.println ("[" + ilk + ", " + son + ") aras� rasgele ters altDizge: [" + dizge.subSequence (ilk, son) + "]");
    } // main(..) metodu sonu...
} // J2hq_1 s�n�f� sonu...

/* ��kt�:
**  >java J2hq_1  **
Orijinal dizge: [java.lang paketinde bulunan CharSequence aray�z�n� y�r�ten bir s�n�f program� yaz�n.]
charAt() metoduyla Ters �evrilen dizge: [.n�zay �margorp f�n�s rib net�r�y �n�z�yara ecneuqeSrahC nanulub ednitekap gnal.avaj]
reverse() metoduyla ters �evrilen dizge: [.n�zay �margorp f�n�s rib net�r�y �n�z�yara ecneuqeSrahC nanulub ednitekap gnal.avaj]
[2, 44) aras� rasgele ters altDizge: [zay �margorp f�n�s rib net�r�y �n�z�yara e]

**  >java J2hq_1  **
Orijinal dizge: [java.lang paketinde bulunan CharSequence aray�z�n� y�r�ten bir s�n�f program� yaz�n.]
charAt() metoduyla Ters �evrilen dizge: [.n�zay �margorp f�n�s rib net�r�y �n�z�yara ecneuqeSrahC nanulub ednitekap gnal.avaj]
reverse() metoduyla ters �evrilen dizge: [.n�zay �margorp f�n�s rib net�r�y �n�z�yara ecneuqeSrahC nanulub ednitekap gnal.avaj]
[16, 41) aras� rasgele ters altDizge: [�n�s rib net�r�y �n�z�yar]
*/