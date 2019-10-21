// J6b_3b.java: ArrayDequeSample (DiziKuyruðuNumunesi) örneði.

import java.util.ArrayDeque;
import java.util.Iterator;

public class J6b_3b {
    public static void main (String[] args) {
        ArrayDeque<String> kuyrukluListe = new ArrayDeque<>();    

        kuyrukluListe.addFirst ("çay");
        kuyrukluListe.addFirst ("kahve");
        kuyrukluListe.addLast ("kola");
        kuyrukluListe.addLast ("gazoz");
        kuyrukluListe.addLast ("meyvesuyu");
        kuyrukluListe.addLast ("þerbet");
        kuyrukluListe.addLast ("limonata");
        kuyrukluListe.addLast ("süt");
        kuyrukluListe.addLast ("ayran");
        kuyrukluListe.addLast ("þalgamsuyu");
        kuyrukluListe.addLast ("turþusuyu");
        kuyrukluListe.addLast ("domatessuyu");

        System.out.println ("Ýlk 3'ü 'first' ilaveli "+ kuyrukluListe.size() + " ebatlý listemiz==>");
        for (Iterator tarayýcý =  kuyrukluListe.iterator(); tarayýcý.hasNext();) System.out.println (tarayýcý.next());
 
        kuyrukluListe.addFirst ("pekmez");  
        kuyrukluListe.addLast ("bal");
        kuyrukluListe.addLast ("puding");
        kuyrukluListe.addLast ("sütlaç");
        kuyrukluListe.addLast ("yoðurt");
        kuyrukluListe.addLast ("meyveliyoðurt");
        kuyrukluListe.addLast ("mayonez");
        kuyrukluListe.addLast ("keççap");

        System.out.println ("\nÝlki 'first' ilaveli " + kuyrukluListe.size() + " ebatlý yeni listemiz==>");
        for (Iterator tarayýcý =  kuyrukluListe.iterator(); tarayýcý.hasNext();) System.out.println (tarayýcý.next());

        System.out.println ("\nÝlk Element: " + kuyrukluListe.getFirst());    
        System.out.println ("SonElement: " + kuyrukluListe.getLast());    

        System.out.println ("\nÝlk Element Silindi: " + kuyrukluListe.removeFirst());  
        System.out.println ("Son Element Silindi: " + kuyrukluListe.removeLast());    
        System.out.println ("Enüstten/Ýlk Pop/Aþýrýlan Element: " + kuyrukluListe.pop());    

        System.out.println ("\nSon kalan " + kuyrukluListe.size() + " ebatlý listemiz==>");   
        for (Iterator tarayýcý =  kuyrukluListe.iterator(); tarayýcý.hasNext();) System.out.println (tarayýcý.next());
    } // main(..) metodu sonu...
} // J6b_3b sýnýfý sonu...

/* Çýktý:
**  >java J6b_3b  **
Ýlk 3'ü 'first' ilaveli 12 ebatlý listemiz==>
kahve
çay
kola
gazoz
meyvesuyu
þerbet
limonata
süt
ayran
þalgamsuyu
turþusuyu
domatessuyu

Ýlki 'first' ilaveli 20 ebatlý yeni listemiz==>
pekmez
kahve
çay
kola
gazoz
meyvesuyu
þerbet
limonata
süt
ayran
þalgamsuyu
turþusuyu
domatessuyu
bal
puding
sütlaç
yoðurt
meyveliyoðurt
mayonez
keççap

Ýlk Element: pekmez
SonElement: keççap

Ýlk Element Silindi: pekmez
Son Element Silindi: keççap
Enüstten/Ýlk Pop/Aþýrýlan Element : kahve

Son kalan 17 ebatlý listemiz==>
çay
kola
gazoz
meyvesuyu
þerbet
limonata
süt
ayran
þalgamsuyu
turþusuyu
domatessuyu
bal
puding
sütlaç
yoðurt
meyveliyoðurt
mayonez
*/