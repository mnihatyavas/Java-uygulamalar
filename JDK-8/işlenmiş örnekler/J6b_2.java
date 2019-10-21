// J6b_2.java: FileList (DosyaListesi) örneði.

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Girdiðiniz dosyanýn tüm satýrlarýný okur; belirttiðiniz adet kadar geliþigüzel satýr seçip döker...
public class J6b_2 {
    public static void main (String[] argüman) {
        if (argüman.length < 2) {System.out.println ("2 argüman [Dökümlenecek dosya adý ve satýr adedi] girmelisiniz!"); System.exit (0);}

        //final int farzedilenSatýrUzunluðu = 50;
        File dosya = new File (argüman[0]);
        List<String> dosyaListesi =  new ArrayList<String>(); // (int)(dosya.length() / farzedilenSatýrUzunluðu) * 2
        BufferedReader tamponOkuyucu = null;
        int satýrSayýsý = 0;
        try {tamponOkuyucu = new BufferedReader (new FileReader (dosya));
            // Dosyanýn tüm satýrlarýný (EOF/null'a dek) okur, listeye ekler, okuduðu toplam satýr sayýsýný tespit eder...
            System.out.println ("Dosyanýn tüm satýrlarýnýn altalta sýralý dökümü==>");
            for (String satýr = tamponOkuyucu.readLine(); satýr != null; satýr = tamponOkuyucu.readLine()) {
                dosyaListesi.add (satýr);
                satýrSayýsý++;
                System.out.println (satýrSayýsý + ".satýr: " + satýr);
            } // for döngüsü sonu...
        }catch (IOException ist) {System.err.format ("Metin dosyasýný okuyamýyor: %s: %s%n", dosya, ist); System.exit (1);
        }finally {
            if (tamponOkuyucu != null) {try {tamponOkuyucu.close();}catch (IOException ist){}}
        } // try-catch-finally bloðu sonu...

        int döngüAdedi = 0;
        try {döngüAdedi = Integer.parseInt (argüman[1]);}catch (Exception ist){döngüAdedi= 5;}
        Random tesadüfi = new Random();
        System.out.println ("\nGirdiðiniz adet kadar tesadüfi seçilen satýrlarýn altalta dökümü==>");
        for (int i = 0; i < döngüAdedi; i++) {
            System.out.format ("%d: %s%n", i+1, dosyaListesi.get (tesadüfi.nextInt (satýrSayýsý - 1)));
        } // for döngüsü sonu...
    } // main(..9 metodu sonu...
} // J6b_2 sýnýfý sonu...

/* Çýktý:
**  >java J6b_2 html/rehber.txt 2  **
Dosyanýn tüm satýrlarýnýn altalta sýralý dökümü==>
1.satýr: Ýstiyorsanýz baþka bir baþlýk ve sonluk metinleri girebilirsiniz. Sayfa sonu numaralandýrmasý için {0} sembolle birlikte herhangi bir metni kullanabilirsiniz.
2.satýr:
3.satýr: Yazdýrma aþamalarýný takip etmek isterseniz "Yaz Adýmlarý Göster" kutusu çentiklenmeli, iptal için ise kutu çentiksizlenmelidir.
4.satýr:
5.satýr: Yazdýrma iþlemi sürerken pencereyle etkileþimi sürdürmek isterseniz "Arkaplanda Yazdýr" çentiklenmeli, yazdýrma tamamlanýncaya kadar içerikle etkileþimi dondurmak için ise çentiksizlenmelidir.
6.satýr:
7.satýr: Müdahaleli (düzenleme ve deðiþiklikler mümkün) metin alaný içeriklerini son þekliyle yazdýrmak için de ikonlu "Yazdýr!" düðmesini týklamanýz yeterlidir.

Girdiðiniz adet kadar tesadüfi seçilen satýrlarýn altalta dökümü==>
1: Ýstiyorsanýz baþka bir baþlýk ve sonluk metinleri girebilirsiniz. Sayfa sonunumaralandýrmasý için {0} sembolle birlikte herhangi bir metni kullanabilirsiniz.
2: Yazdýrma aþamalarýný takip etmek isterseniz "Yaz Adýmlarý Göster" kutusu çentiklenmeli, iptal için ise kutu çentiksizlenmelidir.
*/