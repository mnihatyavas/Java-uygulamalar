// J6b_3i.java: OysterMonths (ÝstiridyeAylarý) örneði.

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.text.DateFormatSymbols;

public class J6b_3i {
    Collection<String> istiridyeAvýAylarý;
    static String içerik = null;

    public Collection<String> filitrele (Collection<String> koleksiyon) {
        Collection<String> filitrelenenKoleksiyonListesi = new ArrayList<String>();
        for (Iterator<String> tarayýcý = koleksiyon.iterator(); tarayýcý.hasNext();) {
            String dizge = tarayýcý.next();
            if (þart (dizge)) filitrelenenKoleksiyonListesi.add (dizge);
        } // for döngüsü sonu...
        return filitrelenenKoleksiyonListesi;
    } // filitrele(..) metodu sonu...

    public boolean þart (String dizge) {return dizge.contains (içerik);} // Þart: r harfi içeren Türkçe ay sembolleri...

    public static void main (String[] args) {
        içerik = args.length > 0 ? args[0] : "r";
        J6b_3i sýnýfNesnesi = new J6b_3i(); // Varsayýlý kurucuyu çaðýrýr...
        DateFormatSymbols tarihBiçimiSembolleri = new DateFormatSymbols();
        String[] aylarDizisi = tarihBiçimiSembolleri.getMonths();
        Collection<String> aylar = Arrays.asList (aylarDizisi);
        sýnýfNesnesi.istiridyeAvýAylarý = sýnýfNesnesi.filitrele (aylar);
        System.out.println ("Ýstiridye avýnýn serbest olduðu " + içerik + "'li aylar: " + sýnýfNesnesi.istiridyeAvýAylarý);
    } // main(..) metodu sonu...
} // J6b_3i sýnýfý sonu...

/* Çýktý:
**  >java J6b_3i  **
Ýstiridye avýnýn serbest olduðu r'li aylar: [Mart, Haziran, Aralýk]

**  >java J6b_3i a  ** TEKRAR
Ýstiridye avýnýn serbest olduðu a'li aylar: [Ocak, Þubat, Mart, Nisan, Mayýs, Haziran, Kasým, Aralýk]

**  >java J6b_3i ar  ** TEKRAR
Ýstiridye avýnýn serbest olduðu ar'li aylar: [Mart]
*/