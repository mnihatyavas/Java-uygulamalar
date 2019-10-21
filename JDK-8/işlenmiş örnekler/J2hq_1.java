/* J2hq_1.java: CharSequenceDemo (KarakterDizisiGösterimi) örneði.
 *
 * CharSequence arayüzü yürütüldüðünden onun 3 metodu override/esgeçme
 * yapýlmalýdýr: length(), charAt(..) ve charSequence(..) metodlarý...
 */

// J2hq_1 programý bir dizge sunar, tersler, geliþigüzel ibarelerini ayýrýr, onlarý da tersler...
public class J2hq_1 implements CharSequence {
    private String dizge;

    public J2hq_1 (String dizge) {// Kurucu...
        // Verilen dizgeyi en baþta kurucuda terslemek verimili olurdu, ama daha az eðlenceli!
        this.dizge = dizge; // Kurucu dizgesi, sýnýf tip deðiþkeninne atanýyor...
    } // J2hq_1(..) kurucu sonu...

    private int sondan (int i) {return dizge.length() - 1 - i;} // Eðer dizge tersse sonu baþlangýcýdýr...
    public int length() {return dizge.length();}
    private static int rasgele (int tamsayý) {return (int) Math.round (Math.random() * tamsayý + 0.5);} // [0->tamsayý] rasgele sayý üretir...
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
            "java.lang paketinde bulunan CharSequence arayüzünü yürüten bir sýnýf programý yazýn.");

        System.out.println ("Orijinal dizge: [" + new StringBuilder (dizge).reverse().toString() + "]");
        System.out.print ("charAt() metoduyla Ters çevrilen dizge: [");
        for (int i = 0; i < dizge.length(); i++) System.out.print (dizge.charAt (i));
        System.out.println ("]\nreverse() metoduyla ters çevrilen dizge: [" + dizge + "]");

        int ilk = rasgele (dizge.length() - 1);
        int son = rasgele (dizge.length() - 1 - ilk) + ilk;
        System.out.println ("[" + ilk + ", " + son + ") arasý rasgele ters altDizge: [" + dizge.subSequence (ilk, son) + "]");
    } // main(..) metodu sonu...
} // J2hq_1 sýnýfý sonu...

/* Çýktý:
**  >java J2hq_1  **
Orijinal dizge: [java.lang paketinde bulunan CharSequence arayüzünü yürüten bir sýnýf programý yazýn.]
charAt() metoduyla Ters çevrilen dizge: [.nýzay ýmargorp fýnýs rib netürüy ünüzüyara ecneuqeSrahC nanulub ednitekap gnal.avaj]
reverse() metoduyla ters çevrilen dizge: [.nýzay ýmargorp fýnýs rib netürüy ünüzüyara ecneuqeSrahC nanulub ednitekap gnal.avaj]
[2, 44) arasý rasgele ters altDizge: [zay ýmargorp fýnýs rib netürüy ünüzüyara e]

**  >java J2hq_1  **
Orijinal dizge: [java.lang paketinde bulunan CharSequence arayüzünü yürüten bir sýnýf programý yazýn.]
charAt() metoduyla Ters çevrilen dizge: [.nýzay ýmargorp fýnýs rib netürüy ünüzüyara ecneuqeSrahC nanulub ednitekap gnal.avaj]
reverse() metoduyla ters çevrilen dizge: [.nýzay ýmargorp fýnýs rib netürüy ünüzüyara ecneuqeSrahC nanulub ednitekap gnal.avaj]
[16, 41) arasý rasgele ters altDizge: [ýnýs rib netürüy ünüzüyar]
*/