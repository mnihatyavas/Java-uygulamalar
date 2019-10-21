// J2c_13a.java: MethodReferencesTest (MetodReferans�-ilgi,alaka,ba�-Testi) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.Set;
import java.util.HashSet;
import java.time.chrono.IsoChronology;

// Gereken dosya: J2c_13ax.java; Person
public class J2c_13a {
    // elemanlar�Aktar(..) metodu bir koleksiyon elemanlar�n� di�erine kopyalar...
    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST elemanlar�Aktar (SOURCE kaynakKoleksiyon, Supplier<DEST> hedefKoleksiyon) {
        DEST listem = hedefKoleksiyon.get();
        for (T eleman : kaynakKoleksiyon) listem.add (eleman);
        return listem;
    } // elemanlar�Aktar(..) metodu sonu...
      
    public static void main (String... args) {
        List<J2c_13ax> liste = J2c_13ax.listeyiYarat();        

        System.out.println ("Orijinal liste:");
        for (J2c_13ax ki�i: liste) ki�i.ki�iyiYaz();

        J2c_13ax[] dizinListe = liste.toArray (new J2c_13ax[liste.size()]);

        class Ki�iYa�Kar��la�t�r�c�s� implements Comparator<J2c_13ax> {
            public int compare (J2c_13ax a, J2c_13ax b) {
                return a.do�umTarihiniAl().compareTo (b.do�umTarihiniAl());
            } // kar��la�t�r(..) metodu sonu...
        } // Ki�iYa�Kar��la�t�r�c�s� s�n�f� sonu...

        // Metod referans olmaks�z�n s�ralama...
        Arrays.sort (dizinListe, new Ki�iYa�Kar��la�t�r�c�s�());
        System.out.println ("\nArrays.sort (dizinListe, new Ki�iYa�Kar��la�t�r�c�s�()): Ya�'a g�re s�ral�==>");
        for (J2c_13ax ki�i: dizinListe) ki�i.ki�iyiYaz();


        // Lambda ifadesiyle s�ralama...
        Arrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.do�umTarihiniAl().compareTo (b.do�umTarihiniAl());});
        System.out.println ("\nArrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.do�umTarihiniAl().compareTo (b.do�umTarihiniAl());}): Ya�'a g�re s�ral�==>");
        for (J2c_13ax ki�i: dizinListe) ki�i.ki�iyiYaz();

        // Metod referansla s�ralama...
        Arrays.sort (dizinListe, J2c_13ax::do�umTarihineG�reKar��la�t�r);
        System.out.println ("\nArrays.sort (dizinListe, J2c_13ax::do�umTarihineG�reKar��la�t�r): Ya�'a g�re s�ral�==>");
        for (J2c_13ax ki�i: dizinListe) ki�i.ki�iyiYaz();

        // Bir nesnenin tip metoduna referans yapma...
        class Kar��la�t�r�c� {
            public int ismeG�reKar��la�t�r (J2c_13ax a, J2c_13ax b) {return a.ismiAl().compareTo (b.ismiAl());}
            public int do�umTarihineG�reKar��la�t�r (J2c_13ax a, J2c_13ax b) {return a.do�umTarihiniAl().compareTo (b.do�umTarihiniAl());}
        } // Kar��la�t�r�c� s�n�f� sonu...

        Kar��la�t�r�c� kar��la�t�r�c�m = new Kar��la�t�r�c�();
        Arrays.sort (dizinListe, kar��la�t�r�c�m::ismeG�reKar��la�t�r);
        System.out.println ("\nArrays.sort (dizinListe, kar��la�t�r�c�m::ismeG�reKar��la�t�r): �sme g�re s�ral�==>");
        for (J2c_13ax ki�i: dizinListe) ki�i.ki�iyiYaz();

        String[] dizinStr = { "Barbara", "James", "Mary", "John",
            "Patricia", "Robert", "Michael", "Linda", "Nihat" };
        Arrays.sort (dizinStr, String::compareToIgnoreCase);
        System.out.println ("\nArrays.sort (dizinStr, String::compareToIgnoreCase): �sme g�re s�ral�==>");
        for (String ki�i: dizinStr) System.out.println (ki�i);

        Set<J2c_13ax> listeSetLambda = elemanlar�Aktar (liste, () -> {return new HashSet<>(); });
        Set<J2c_13ax> listeSet = elemanlar�Aktar (liste, HashSet::new);

        System.out.println ("\nListe'den HashSet'e kopyalanan listeSet yazd�r�l�yor==>");
        listeSet.stream().forEach (birey -> birey.ki�iyiYaz());
    } // main(..) metodu sonu...
} // J2c_13a s�n�f� sonu...

/* ��kt�:
**  >java J2c_13a  **
Orijinal liste:
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com

Arrays.sort (dizinListe, new Ki�iYa�Kar��la�t�r�c�s�()): Ya�'a g�re s�ral�==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, (J2c_13ax a, J2c_13ax b) -> {return a.do�umTarihiniAl().compareTo (b.do�umTarihiniAl());}): Ya�'a g�re s�ral�==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, J2c_13ax::do�umTarihineG�reKar��la�t�r): Ya�'a g�re s�ral�==>
Nihat, 61, ERKEK, mnyavas@hotmail.com
Fred, 37, ERKEK, fred@gmail.com
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com

Arrays.sort (dizinListe, kar��la�t�r�c�m::ismeG�reKar��la�t�r): �sme g�re s�ral�==>
Bob, 17, ERKEK, bob@gmail.com
Fred, 37, ERKEK, fred@gmail.com
George, 26, ERKEK, george@gmail.com
Jane, 27, KADIN, jane@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com

Arrays.sort (dizinStr, String::compareToIgnoreCase): �sme g�re s�ral�==>
Barbara
James
John
Linda
Mary
Michael
Nihat
Patricia
Robert

Liste'den HashSet'e kopyalanan listeSet yazd�r�l�yor==>
Jane, 27, KADIN, jane@gmail.com
George, 26, ERKEK, george@gmail.com
Bob, 17, ERKEK, bob@gmail.com
Fred, 37, ERKEK, fred@gmail.com
Nihat, 61, ERKEK, mnyavas@hotmail.com
*/