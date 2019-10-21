// J8e_5.java: KeysDemo (AnahtarlarGösterisi) örneði.

import java.util.Locale;

import java.text.Collator;
import java.text.CollationKey;

public class J8e_5  {
    public static void diziyiSýrala (CollationKey[] kýyasAnahtarlarý) {
        CollationKey aracý;

        for (int i = 0; i < kýyasAnahtarlarý.length; i++) {
            for (int j = i + 1; j < kýyasAnahtarlarý.length; j++) {
                // Ardýþýk ikili anahtarlar kýyaslanýr...
                if( kýyasAnahtarlarý[i].compareTo (kýyasAnahtarlarý[j]) > 0 ) {
                    // Küçüðü önceye alýnýr (artan bubble/ikili/baloncuk sýralama)...
                    aracý = kýyasAnahtarlarý[i];
                    kýyasAnahtarlarý[i] = kýyasAnahtarlarý[j];
                    kýyasAnahtarlarý[j] = aracý;
                } // if kararý sonu...
            } // for-j döngüsü sonu...
        } // for-i döngüsü sonu...
    } // diziyiSýrala(..) metodu sonu...

    static void kelimeleriGöster (CollationKey[] kýyasAnahtarlarý) {
        for (int i = 0; i < kýyasAnahtarlarý.length; i++) System.out.println (kýyasAnahtarlarý[i].getSourceString());
    } // kelimeleriGöster(..) metodu sonu...

    static public void main (String[] argüman) {
      Collator kýyaslayýcý = argüman.length > 0?
                Collator.getInstance (new Locale (argüman[0])) // 'tr' girmek yeterli...
                : Collator.getInstance (new Locale ("en", "US"));
  
        String [] kelimeler = {"iðde", "ýhlamur", "þeftali", "kayýsý", "ðýyar", "çam fýstýðý", "öküz gözü", "fýndýk", "armut", "zeytin", "üzüm", "limon"};

        CollationKey[] kýyasAnahtarlarý = new CollationKey[kelimeler.length];
        for (int k = 0; k < kýyasAnahtarlarý.length; k ++) kýyasAnahtarlarý[k] = kýyaslayýcý.getCollationKey (kelimeler[k]);
 
        diziyiSýrala (kýyasAnahtarlarý);
        kelimeleriGöster (kýyasAnahtarlarý);
    } // main(..) metodu sonu...
} // J8e_5 sýnýfý sonu...

/* Çýktý:
**  >java J8e_5  **
armut
çam fýstýðý
fýndýk
ðýyar
iðde
kayýsý
limon
öküz gözü
þeftali
üzüm
zeytin
ýhlamur

**  >java J8e_5 tr  ** TEKRAR
armut
çam fýstýðý
fýndýk
ðýyar
ýhlamur
iðde
kayýsý
limon
öküz gözü
þeftali
üzüm
zeytin
*/