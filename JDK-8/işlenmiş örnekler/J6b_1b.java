// J6b_1b.java: Anagrams (Anagramlar) �rne�i.

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import java.io.File;
import java.io.IOException;

/* Asl�nda anagram bir kelime harflerinin kar��t�r�larak yeni kelimelerin
 * t�retilmasidir. Ancak bu program �yle yapm�yor. Girilen metin dosyas�ndaki
 * kelimelerin, en �ok tekrardan, yine girilen enaz tekrar say�s�na kadar olan
 * kelimeleri bulup bunlar� �oktan-aza s�ralayarak listeler halinde d�k�yor.
*/
public class J6b_1b {
    public static void main (String[] arg�man) {
        if (arg�man.length < 2) {
            System.out.println ("Mutlaka 2 arg�man [1:Metin dosyas�; 2:Enk���k grup ebat�] girmelisiniz!");
            System.exit (0);
        } // if karar� sonu...

        int enk���kGrupEbat� = 0;
        try {enk���kGrupEbat� = Integer.parseInt (arg�man[1]);}catch (Exception ist) {enk���kGrupEbat� = 2;}
        if (enk���kGrupEbat� < 2) enk���kGrupEbat� = 2;

        // Kelimeleri dosyadan oku v sim�leli �oklu haritaya koy...
        Map<String, List<String>> harita = new HashMap<String, List<String>>();
        try {Scanner oku = new Scanner (new File (arg�man[0]));
            while (oku.hasNext()) {String kelime = oku.next();
                String s�ral�Kelime = kelimeHarfleriniS�rala (kelime);
                List<String> liste = harita.get (s�ral�Kelime);
                if (liste == null) harita.put (s�ral�Kelime, liste= new ArrayList<String>());
                liste.add (kelime);
            } // while �ng�s� sonu...
        }catch (IOException ist) {System.err.println (ist); System.exit (0);}

        // Ayn� kelimeleri listede grupland�r�r...
        List<List<String>> gruplar = new ArrayList<List<String>>();
        for (List<String> liste : harita.values())
            if (liste.size() >= enk���kGrupEbat�)
                gruplar.add (liste);

        // Farkl� ebatlar�na g�re gruplanmay� olu�turur...
        Collections.sort (gruplar, new Comparator<List<String>>() {
            public int compare (List<String> eleman1, List<String> eleman2) {
                return eleman2.size() - eleman1.size();
        }}); // Coll.. ifadesi sonu...

        // Farkl� ebatl� grupland�r�lmalar� adet say�s�na g�re b�y�kten k����e d�kelim...
        int k = 0;
        for (List<String> liste : gruplar ) {
            if (k != liste.size()) {k = liste.size(); System.out.println();}
            System.out.println (liste.size() + ": " + liste);}
    } // main(..) metodu sonu...

    private static String kelimeHarfleriniS�rala (String dizge) {
        char[] a = dizge.toCharArray();
        Arrays.sort (a);
        return new String (a);
    } // kelimeHarfleriniS�rala(..) metodu sonu...
} // J6b_1b s�n�f� sonu...

/* ��kt�:
**  >java J6b_1b html/chapter1.html  **
Mutlaka 2 arg�man [1:Metin dosyas�; 2:Enk���k grup ebat�] girmelisiniz!

**  >java J6b_1b html/chapter1.html 3  **

89: [the, the, the, the, the, the, the, the, the, the, the, the, the, the, the,
the, the, the, the, the, the, the, the, the, the, the, the, the, the, the, the,
the, the, the, the, the, the, the, the, the, the, the, the, the, the, the, the,
the, the, the, the, the, the, the, the, the, the, the, the, the, the, the, the,
the, the, the, the, the, the, the, the, the, the, the, the, the, the, the, the,
the, the, the, the, the, the, the, the, the, the]

73: [she, she, she, she, she, she, she, she, she, she, she, she, she, she, she,
she, she, she, she, she, she, she, she, she, she, she, she, she, she, she, she,
she, she, she, she, she, she, she, she, she, she, she, she, she, she, she, she,
she, she, she, she, she, she, she, she, she, she, she, she, she, she, she, she,
she, she, she, she, she, she, she, she, she, she]

71: [to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to,
 to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to,
 to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to, to,
 to, to, to, to, to, to, to, to, to, to, to, to]

56: [and, and, and, and, and, and, and, and, and, and, and, and, and, and, and,
and, and, and, and, and, and, and, and, and, and, and, and, and, and, and, and,
and, and, and, and, and, and, and, and, and, and, and, and, and, and, and, and,
and, and, and, and, and, and, and, and, and]

52: [a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a,
a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a, a]

52: [was, was, was, was, was, was, was, was, was, was, saw, was, was, was, was,
was, was, was, was, was, was, was, was, was, was, was, was, was, was, was, was,
was, was, was, was, was, was, was, was, was, was, was, was, was, was, was, was,
was, was, was, was, was]

41: [of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of,
 of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of, of,
 of, of]
41: [it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it,
 it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it, it,
 it, it]

24: [her, her, her, her, her, her, her, her, her, her, her, her, her, her, her,
her, her, her, her, her, her, her, her, her]
24: [in, in, in, in, in, in, in, in, in, in, in, in, in, in, in, in, in, in, in,
 in, in, in, in, in]

23: [I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I, I]
23: [on, no, on, no, no, no, no, on, on, no, on, on, no, on, on, on, on, no, no,
 on, on, on, on]

22: [&nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nb
sp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nbsp;, &nb
sp;, &nbsp;, &nbsp;]

21: [that, that, that, that, that, that, that, that, that, that, that, that, tha
t, that, that, that, that, that, that, that, that]

19: [had, had, had, had, had, had, had, had, had, had, had, had, had, had, had,
had, had, had, had]
19: [very, very, very, very, very, very, very, very, very, very, very, very, ver
y, very, very, very, very, very, very]

17: [Alice, Alice, Alice, Alice, Alice, Alice, Alice, Alice, Alice, Alice, Alice
, Alice, Alice, Alice, Alice, Alice, Alice]
17: [for, for, for, for, for, for, for, for, for, for, for, for, for, for, for,
for, for]
17: [you, you, you, you, you, you, you, you, you, you, you, you, you, you, you,
you, you]

16: [&nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;,
&nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp
;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nbsp;, &nbsp;*&nb
sp;]
16: [but, but, but, but, but, but, but, but, but, but, but, but, but, but, but,
but]

15: [little, little, little, little, little, little, little, little, little, lit
tle, little, little, little, little, little]

14: [not, not, not, not, not, not, not, not, not, not, not, not, not, not]
14: [down, down, down, down, down, down, down, down, down, down, down, down, dow
n, down]

13: [be, be, be, be, be, be, be, be, be, be, be, be, be]

12: [*&nbsp;, &nbsp;*, *&nbsp;, &nbsp;*, *&nbsp;, &nbsp;*, *&nbsp;, &nbsp;*, *&n
bsp;, &nbsp;*, *&nbsp;, &nbsp;*]
12: [as, as, as, as, as, as, as, as, as, as, as, as]
12: [or, or, or, or, or, or, or, or, or, or, or, or]
12: [so, so, so, so, so, so, so, so, so, so, so, so]

11: [like, like, like, like, like, like, like, like, like, like, like]
11: [it,, it,, it,, it,, it,, it,, it,, it,, it,, it,, it,]
11: [at, at, at, at, at, at, at, at, at, at, at]
11: [up, up, up, up, up, up, up, up, up, up, up]
11: [with, with, with, with, with, with, with, with, with, with, with]

10: [all, all, all, all, all, all, all, all, all, all]
10: [if, if, if, if, if, if, if, if, if, if]
10: [this, this, this, this, this, this, this, this, this, this]
10: [out, out, out, out, out, out, out, out, out, out]

9: [what, what, what, what, what, what, what, what, what]
9: [when, when, when, when, when, when, when, when, when]
9: [into, into, into, into, into, into, into, into, into]

8: [one, one, one, one, one, one, one, one]
8: [were, were, were, were, were, were, were, were]
8: [think, think, think, think, think, think, think, think]
8: [thought, thought, thought, thought, thought, thought, thought, thought]
8: [<br>, <br>, <br>, <br>, <br>, <br>, <br>, <br>]
8: [me, me, me, me, me, me, me, me]
8: [about, about, about, about, about, about, about, about]

7: [get, get, get, get, get, get, get]
7: [eat, eat, eat, eat, eat, eat, ate]
7: [found, found, found, found, found, found, found]
7: [how, how, how, how, how, how, who]
7: [said, said, said, said, said, said, said]
7: [went, went, went, went, went, went, went]
7: [tired, tried, tried, tried, tried, tried, tired]
7: [nothing, nothing, nothing, nothing, nothing, nothing, nothing]
7: [herself, herself, herself, herself, herself, herself, herself]

6: [time, time, time, time, time, time]
6: [going, going, going, going, going, going]
6: [could, could, could, could, could, could]
6: [way, way, way, way, way, way]
6: [by, by, by, by, by, by]
6: [much, much, much, much, much, much]
6: ['I, 'I, 'I, 'I, 'I, 'I]
6: [herself,, herself,, herself,, herself,, herself,, herself,]
6: [see, see, see, see, see, see]

5: [use, use, use, use, use]
5: [wonder, wonder, wonder, wonder, wonder]
5: [which, which, which, which, which]
5: [shall, shall, shall, shall, shall]
5: [is, is, is, is, is]
5: [Rabbit, Rabbit, Rabbit, Rabbit, Rabbit]
5: [my, my, my, my, my]
5: [too, too, too, too, too]

4: [marked, marked, marked, marked]
4: [would, would, would, would]
4: [down,, down,, down,, down,]
4: [got, got, got, got]
4: [golden, golden, longed, golden]
4: [<i>very</i>, <i>very</i>, <i>very</i>, <i>very</i>]
4: [here, here, here, here]
4: [there, there, there, there]
4: [either, either, either, either]
4: [any, any, any, any]
4: [quite, quite, quite, quite]
4: [say, say, say, say]
4: [ever, ever, ever, ever]
4: [own, now, now, own]
4: ['and, 'and, 'and, 'and]
4: [looked, looked, looked, looked]
4: [off, off, off, off]
4: [poor, poor, poor, poor]
4: [She, She, She, She]
4: [fall, fall, fall, fall]
4: [then, then, then, then]
4: [fell, fell, fell, fell]
4: [right, right, right, right]
4: [they, they, they, they]
4: [might, might, might, might]
4: [bottle, bottle, bottle, bottle]
4: [things, things, things, things]
4: [never, never, never, never]
4: [upon, upon, upon, upon]

3: [see,, see,, see,]
3: [words, words, words]
3: [<p>There, <p>There, <p>There]
3: [came, came, came]
3: [key, key, key]
3: [table,, table,, table,]
3: [pictures, pictures, pictures]
3: [once, once, once]
3: [rather, rather, rather]
3: [began, began, began]
3: [I'll, I'll, I'll]
3: [cats, cats, cats]
3: [even, even, even]
3: [dear!, dear!, dear!]
3: [Alice,, Alice,, Alice,]
3: [after, after, after]
3: [another, another, another]
3: [it's, it's, it's]
3: [There, There, There]
3: [<p>Alice, <p>Alice, <p>Alice]
3: [door, door, door]
3: [remember, remember, remember]
3: [through, through, through]
3: [did, did, did]
3: [head, head, head]
3: [How, How, How]
3: [hand, hand, hand]
3: [well, well, well]
3: [hall,, hall,, hall,]
3: [do, do, do]
3: [suddenly, suddenly, suddenly]
3: [having, having, having]
3: [from, from, from]
3: [dark, dark, dark]
3: [moment, moment, moment]
3: [good, good, good]
3: [such, such, such]
3: [just, just, just]
3: [felt, felt, left]
3: [before, before, before]
3: [seemed, seemed, seemed]
3: [under, under, under]
3: [look, look, look]
3: [door,, door,, door,]
3: [soon, soon, soon]
3: [sort, sort, sort]

**  >java J6b_1b html/rehber.txt 0  **

4: [i�in, i�in, i�in, i�in]

2: [etkile�imi, etkile�imi]
2: [bir, bir]
2: [metni, metin]
2: [Yazd�rma, Yazd�rma]
2: [isterseniz, isterseniz]
2: [�entiksizlenmelidir., �entiksizlenmelidir.]
2: [ve, ve]
2: [�entiklenmeli,, �entiklenmeli,]
2: [ise, ise]
*/