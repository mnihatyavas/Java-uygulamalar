// J6b_2.java: FileList (DosyaListesi) �rne�i.

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Girdi�iniz dosyan�n t�m sat�rlar�n� okur; belirtti�iniz adet kadar geli�ig�zel sat�r se�ip d�ker...
public class J6b_2 {
    public static void main (String[] arg�man) {
        if (arg�man.length < 2) {System.out.println ("2 arg�man [D�k�mlenecek dosya ad� ve sat�r adedi] girmelisiniz!"); System.exit (0);}

        //final int farzedilenSat�rUzunlu�u = 50;
        File dosya = new File (arg�man[0]);
        List<String> dosyaListesi =  new ArrayList<String>(); // (int)(dosya.length() / farzedilenSat�rUzunlu�u) * 2
        BufferedReader tamponOkuyucu = null;
        int sat�rSay�s� = 0;
        try {tamponOkuyucu = new BufferedReader (new FileReader (dosya));
            // Dosyan�n t�m sat�rlar�n� (EOF/null'a dek) okur, listeye ekler, okudu�u toplam sat�r say�s�n� tespit eder...
            System.out.println ("Dosyan�n t�m sat�rlar�n�n altalta s�ral� d�k�m�==>");
            for (String sat�r = tamponOkuyucu.readLine(); sat�r != null; sat�r = tamponOkuyucu.readLine()) {
                dosyaListesi.add (sat�r);
                sat�rSay�s�++;
                System.out.println (sat�rSay�s� + ".sat�r: " + sat�r);
            } // for d�ng�s� sonu...
        }catch (IOException ist) {System.err.format ("Metin dosyas�n� okuyam�yor: %s: %s%n", dosya, ist); System.exit (1);
        }finally {
            if (tamponOkuyucu != null) {try {tamponOkuyucu.close();}catch (IOException ist){}}
        } // try-catch-finally blo�u sonu...

        int d�ng�Adedi = 0;
        try {d�ng�Adedi = Integer.parseInt (arg�man[1]);}catch (Exception ist){d�ng�Adedi= 5;}
        Random tesad�fi = new Random();
        System.out.println ("\nGirdi�iniz adet kadar tesad�fi se�ilen sat�rlar�n altalta d�k�m�==>");
        for (int i = 0; i < d�ng�Adedi; i++) {
            System.out.format ("%d: %s%n", i+1, dosyaListesi.get (tesad�fi.nextInt (sat�rSay�s� - 1)));
        } // for d�ng�s� sonu...
    } // main(..9 metodu sonu...
} // J6b_2 s�n�f� sonu...

/* ��kt�:
**  >java J6b_2 html/rehber.txt 2  **
Dosyan�n t�m sat�rlar�n�n altalta s�ral� d�k�m�==>
1.sat�r: �stiyorsan�z ba�ka bir ba�l�k ve sonluk metinleri girebilirsiniz. Sayfa sonu numaraland�rmas� i�in {0} sembolle birlikte herhangi bir metni kullanabilirsiniz.
2.sat�r:
3.sat�r: Yazd�rma a�amalar�n� takip etmek isterseniz "Yaz Ad�mlar� G�ster" kutusu �entiklenmeli, iptal i�in ise kutu �entiksizlenmelidir.
4.sat�r:
5.sat�r: Yazd�rma i�lemi s�rerken pencereyle etkile�imi s�rd�rmek isterseniz "Arkaplanda Yazd�r" �entiklenmeli, yazd�rma tamamlan�ncaya kadar i�erikle etkile�imi dondurmak i�in ise �entiksizlenmelidir.
6.sat�r:
7.sat�r: M�dahaleli (d�zenleme ve de�i�iklikler m�mk�n) metin alan� i�eriklerini son �ekliyle yazd�rmak i�in de ikonlu "Yazd�r!" d��mesini t�klaman�z yeterlidir.

Girdi�iniz adet kadar tesad�fi se�ilen sat�rlar�n altalta d�k�m�==>
1: �stiyorsan�z ba�ka bir ba�l�k ve sonluk metinleri girebilirsiniz. Sayfa sonunumaraland�rmas� i�in {0} sembolle birlikte herhangi bir metni kullanabilirsiniz.
2: Yazd�rma a�amalar�n� takip etmek isterseniz "Yaz Ad�mlar� G�ster" kutusu �entiklenmeli, iptal i�in ise kutu �entiksizlenmelidir.
*/