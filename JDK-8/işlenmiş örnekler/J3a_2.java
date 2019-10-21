// J3a_2.java: ListOfNumbersTest (SayýlarListesiDenemesi) örneði.

// Gereken dosya: J3a_2x.java=ListOfNumbers
public class J3a_2 {
    public static void main (String[] args) {
        String dosyaAdý = args.length > 0? args[0] : "mny1.txt";
        J3a_2x liste = new J3a_2x(dosyaAdý); // J3a_2x=ListOfNumbers/SayýlarListesi...
        liste.listeyiYaz();
        liste.listeyiOku();
    } // main(..) metodu sonu...
} // J3a_2 sýnýfý sonu...

/* Çýktý:
**  >java J3a_2  **
mny1.txt'nýn PrintWriter'ý kapatýlýyor!

[mny1.txt] kayýtlarý okunup alt-alta yazdýrýlýyor:
1.ci eleman: 159
2.ci eleman: 917
3.ci eleman: 259
4.ci eleman: 374
5.ci eleman: 664
6.ci eleman: 517
7.ci eleman: 930
8.ci eleman: 56
9.ci eleman: 493
10.ci eleman: 850

**  >java J3a_2 mny2.mtn  ** TEKRAR
mny2.mtn'nýn PrintWriter'ý kapatýlýyor!

[mny2.mtn] kayýtlarý okunup alt-alta yazdýrýlýyor:
1.ci eleman: 222
2.ci eleman: 635
3.ci eleman: 772
4.ci eleman: 976
5.ci eleman: 127
6.ci eleman: 260
7.ci eleman: 551
8.ci eleman: 312
9.ci eleman: 285
10.ci eleman: 696
*/