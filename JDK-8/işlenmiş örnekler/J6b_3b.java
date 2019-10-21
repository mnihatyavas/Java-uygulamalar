// J6b_3b.java: ArrayDequeSample (DiziKuyru�uNumunesi) �rne�i.

import java.util.ArrayDeque;
import java.util.Iterator;

public class J6b_3b {
    public static void main (String[] args) {
        ArrayDeque<String> kuyrukluListe = new ArrayDeque<>();    

        kuyrukluListe.addFirst ("�ay");
        kuyrukluListe.addFirst ("kahve");
        kuyrukluListe.addLast ("kola");
        kuyrukluListe.addLast ("gazoz");
        kuyrukluListe.addLast ("meyvesuyu");
        kuyrukluListe.addLast ("�erbet");
        kuyrukluListe.addLast ("limonata");
        kuyrukluListe.addLast ("s�t");
        kuyrukluListe.addLast ("ayran");
        kuyrukluListe.addLast ("�algamsuyu");
        kuyrukluListe.addLast ("tur�usuyu");
        kuyrukluListe.addLast ("domatessuyu");

        System.out.println ("�lk 3'� 'first' ilaveli "+ kuyrukluListe.size() + " ebatl� listemiz==>");
        for (Iterator taray�c� =  kuyrukluListe.iterator(); taray�c�.hasNext();) System.out.println (taray�c�.next());
 
        kuyrukluListe.addFirst ("pekmez");  
        kuyrukluListe.addLast ("bal");
        kuyrukluListe.addLast ("puding");
        kuyrukluListe.addLast ("s�tla�");
        kuyrukluListe.addLast ("yo�urt");
        kuyrukluListe.addLast ("meyveliyo�urt");
        kuyrukluListe.addLast ("mayonez");
        kuyrukluListe.addLast ("ke��ap");

        System.out.println ("\n�lki 'first' ilaveli " + kuyrukluListe.size() + " ebatl� yeni listemiz==>");
        for (Iterator taray�c� =  kuyrukluListe.iterator(); taray�c�.hasNext();) System.out.println (taray�c�.next());

        System.out.println ("\n�lk Element: " + kuyrukluListe.getFirst());    
        System.out.println ("SonElement: " + kuyrukluListe.getLast());    

        System.out.println ("\n�lk Element Silindi: " + kuyrukluListe.removeFirst());  
        System.out.println ("Son Element Silindi: " + kuyrukluListe.removeLast());    
        System.out.println ("En�stten/�lk Pop/A��r�lan Element: " + kuyrukluListe.pop());    

        System.out.println ("\nSon kalan " + kuyrukluListe.size() + " ebatl� listemiz==>");   
        for (Iterator taray�c� =  kuyrukluListe.iterator(); taray�c�.hasNext();) System.out.println (taray�c�.next());
    } // main(..) metodu sonu...
} // J6b_3b s�n�f� sonu...

/* ��kt�:
**  >java J6b_3b  **
�lk 3'� 'first' ilaveli 12 ebatl� listemiz==>
kahve
�ay
kola
gazoz
meyvesuyu
�erbet
limonata
s�t
ayran
�algamsuyu
tur�usuyu
domatessuyu

�lki 'first' ilaveli 20 ebatl� yeni listemiz==>
pekmez
kahve
�ay
kola
gazoz
meyvesuyu
�erbet
limonata
s�t
ayran
�algamsuyu
tur�usuyu
domatessuyu
bal
puding
s�tla�
yo�urt
meyveliyo�urt
mayonez
ke��ap

�lk Element: pekmez
SonElement: ke��ap

�lk Element Silindi: pekmez
Son Element Silindi: ke��ap
En�stten/�lk Pop/A��r�lan Element : kahve

Son kalan 17 ebatl� listemiz==>
�ay
kola
gazoz
meyvesuyu
�erbet
limonata
s�t
ayran
�algamsuyu
tur�usuyu
domatessuyu
bal
puding
s�tla�
yo�urt
meyveliyo�urt
mayonez
*/