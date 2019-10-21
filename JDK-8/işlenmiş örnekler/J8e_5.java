// J8e_5.java: KeysDemo (AnahtarlarG�sterisi) �rne�i.

import java.util.Locale;

import java.text.Collator;
import java.text.CollationKey;

public class J8e_5  {
    public static void diziyiS�rala (CollationKey[] k�yasAnahtarlar�) {
        CollationKey arac�;

        for (int i = 0; i < k�yasAnahtarlar�.length; i++) {
            for (int j = i + 1; j < k�yasAnahtarlar�.length; j++) {
                // Ard���k ikili anahtarlar k�yaslan�r...
                if( k�yasAnahtarlar�[i].compareTo (k�yasAnahtarlar�[j]) > 0 ) {
                    // K����� �nceye al�n�r (artan bubble/ikili/baloncuk s�ralama)...
                    arac� = k�yasAnahtarlar�[i];
                    k�yasAnahtarlar�[i] = k�yasAnahtarlar�[j];
                    k�yasAnahtarlar�[j] = arac�;
                } // if karar� sonu...
            } // for-j d�ng�s� sonu...
        } // for-i d�ng�s� sonu...
    } // diziyiS�rala(..) metodu sonu...

    static void kelimeleriG�ster (CollationKey[] k�yasAnahtarlar�) {
        for (int i = 0; i < k�yasAnahtarlar�.length; i++) System.out.println (k�yasAnahtarlar�[i].getSourceString());
    } // kelimeleriG�ster(..) metodu sonu...

    static public void main (String[] arg�man) {
      Collator k�yaslay�c� = arg�man.length > 0?
                Collator.getInstance (new Locale (arg�man[0])) // 'tr' girmek yeterli...
                : Collator.getInstance (new Locale ("en", "US"));
  
        String [] kelimeler = {"i�de", "�hlamur", "�eftali", "kay�s�", "��yar", "�am f�st���", "�k�z g�z�", "f�nd�k", "armut", "zeytin", "�z�m", "limon"};

        CollationKey[] k�yasAnahtarlar� = new CollationKey[kelimeler.length];
        for (int k = 0; k < k�yasAnahtarlar�.length; k ++) k�yasAnahtarlar�[k] = k�yaslay�c�.getCollationKey (kelimeler[k]);
 
        diziyiS�rala (k�yasAnahtarlar�);
        kelimeleriG�ster (k�yasAnahtarlar�);
    } // main(..) metodu sonu...
} // J8e_5 s�n�f� sonu...

/* ��kt�:
**  >java J8e_5  **
armut
�am f�st���
f�nd�k
��yar
i�de
kay�s�
limon
�k�z g�z�
�eftali
�z�m
zeytin
�hlamur

**  >java J8e_5 tr  ** TEKRAR
armut
�am f�st���
f�nd�k
��yar
�hlamur
i�de
kay�s�
limon
�k�z g�z�
�eftali
�z�m
zeytin
*/