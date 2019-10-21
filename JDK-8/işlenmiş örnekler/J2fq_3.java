// J2fq_3.java: ComputeInitials (SeçBaþharfleri) örneði

public class J2fq_3 {
    public static void main (String[] args) {
        String isim = args.length > 0? args[0] : "M.Nihat Yavaþ";

        StringBuffer baþHarfler = new StringBuffer();
        int uzunluk = isim.length();

        for (int i = 0; i < uzunluk; i++)
            if (Character.isUpperCase (isim.charAt (i)))
                baþHarfler.append (isim.charAt(i));
        System.out.println ("Ad ve soyadýnýn tam yazýlýmý: [" + isim + "]");
        System.out.println ("Sadece baþ harfleri: [" + baþHarfler + "]");
    } // main(..) metodu sonu...
} // J2fq_3 sýnýfý sonu...

/* Çýktý:
**  >java J2fq_3  **
Ad ve soyadýnýn tam yazýlýmý: [M.Nihat Yavaþ]
Sadece baþ harfleri: [MNY]

**  >java J2fq_3 "Z.Nihal Yavaþ Candan"  ** TEKRAR
Ad ve soyadýnýn tam yazýlýmý: [Z.Nihal Yavaþ Candan]
Sadece baþ harfleri: [ZNYC]
*/