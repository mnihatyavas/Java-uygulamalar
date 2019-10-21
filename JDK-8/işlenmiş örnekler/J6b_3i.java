// J6b_3i.java: OysterMonths (�stiridyeAylar�) �rne�i.

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.text.DateFormatSymbols;

public class J6b_3i {
    Collection<String> istiridyeAv�Aylar�;
    static String i�erik = null;

    public Collection<String> filitrele (Collection<String> koleksiyon) {
        Collection<String> filitrelenenKoleksiyonListesi = new ArrayList<String>();
        for (Iterator<String> taray�c� = koleksiyon.iterator(); taray�c�.hasNext();) {
            String dizge = taray�c�.next();
            if (�art (dizge)) filitrelenenKoleksiyonListesi.add (dizge);
        } // for d�ng�s� sonu...
        return filitrelenenKoleksiyonListesi;
    } // filitrele(..) metodu sonu...

    public boolean �art (String dizge) {return dizge.contains (i�erik);} // �art: r harfi i�eren T�rk�e ay sembolleri...

    public static void main (String[] args) {
        i�erik = args.length > 0 ? args[0] : "r";
        J6b_3i s�n�fNesnesi = new J6b_3i(); // Varsay�l� kurucuyu �a��r�r...
        DateFormatSymbols tarihBi�imiSembolleri = new DateFormatSymbols();
        String[] aylarDizisi = tarihBi�imiSembolleri.getMonths();
        Collection<String> aylar = Arrays.asList (aylarDizisi);
        s�n�fNesnesi.istiridyeAv�Aylar� = s�n�fNesnesi.filitrele (aylar);
        System.out.println ("�stiridye av�n�n serbest oldu�u " + i�erik + "'li aylar: " + s�n�fNesnesi.istiridyeAv�Aylar�);
    } // main(..) metodu sonu...
} // J6b_3i s�n�f� sonu...

/* ��kt�:
**  >java J6b_3i  **
�stiridye av�n�n serbest oldu�u r'li aylar: [Mart, Haziran, Aral�k]

**  >java J6b_3i a  ** TEKRAR
�stiridye av�n�n serbest oldu�u a'li aylar: [Ocak, �ubat, Mart, Nisan, May�s, Haziran, Kas�m, Aral�k]

**  >java J6b_3i ar  ** TEKRAR
�stiridye av�n�n serbest oldu�u ar'li aylar: [Mart]
*/