// J6b_3q1.java: ListTrim (ListeK�rpma) �rne�i.

import java.util.List;
import java.util.ListIterator;
import java.util.Arrays;

public class J6b_3q1 {
    static void listeBo�luklar�n�K�rp (List<String> lst) {
        for (ListIterator<String> listeTaray�c�s� = lst.listIterator(); listeTaray�c�s�.hasNext();)
            listeTaray�c�s�.set (listeTaray�c�s�.next().trim()); // trim() haz�r metoduyla �n-arka bo�luklar� k�rpma...
    } // listeBo�luklar�n�K�rp(..) metodu sonu...

    public static void main(String[] args) {
        List<String> liste = Arrays.asList ("  K�rm�z�   ", " Beyaz    ", "  Mavi ", "Ye�il", "Sar� ", "     Siyah     ");
        System.out.println ("Listenin orijinali (yanyana): " + liste);

        listeBo�luklar�n�K�rp (liste);
        System.out.println ("\nListenin bo�luklar�ndan ar�nd�r�lm��� (yanyana): " + liste);
        System.out.println ("\nListenin bo�luklar�ndan ar�nd�r�lm��� (\" aras� ve altalta)==>");
        for (String dizge : liste) System.out.format ("\"%s\"%n", dizge);
    } // main(..) metodu sonu...
} // J6b_3q1 s�n�f� sonu...

/* ��kt�:
**  >java J6b_3q1  **
Listenin orijinali (yanyana): [  K�rm�z�   ,  Beyaz    ,   Mavi , Ye�il, Sar� ,     Siyah     ]

Listenin bo�luklar�ndan ar�nd�r�lm��� (yanyana): [K�rm�z�, Beyaz, Mavi, Ye�il, Sar�, Siyah]

Listenin bo�luklar�ndan ar�nd�r�lm��� (" aras� ve altalta)==>
"K�rm�z�"
"Beyaz"
"Mavi"
"Ye�il"
"Sar�"
"Siyah"
*/