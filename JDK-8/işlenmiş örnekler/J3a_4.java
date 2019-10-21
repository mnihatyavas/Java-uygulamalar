// J3a_4.java: ListOfNumbersTest (SayýlarListesiDenemesi) örneði.

// Gereken dosya: J3a_4x.java=ListOfNumbersDeclared
public class J3a_4 {
    public static void main (String[] args) throws java.io.IOException {
        String dosyaAdý = args.length > 0? args[0] : "mny1.txt";
        // J3a_4x=ListOfNumbersDeclared/SayýlarListesiBeyaný...
        J3a_4x liste = new J3a_4x (dosyaAdý);
    } // main(..) metodu sonu...
} // J3a_4 sýnýfý sonu...

/* Çýktý:
**  >java J3a_4 **
[mny1.txt] kayýtlarý okunup alt-alta yazdýrýlýyor:
1.ci eleman: 306
2.ci eleman: 212
3.ci eleman: 878
4.ci eleman: 296
5.ci eleman: 403
6.ci eleman: 908
7.ci eleman: 361
8.ci eleman: 729
9.ci eleman: 170
10.ci eleman: 107
*/