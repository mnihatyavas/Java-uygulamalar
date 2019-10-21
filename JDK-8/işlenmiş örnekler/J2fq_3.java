// J2fq_3.java: ComputeInitials (Se�Ba�harfleri) �rne�i

public class J2fq_3 {
    public static void main (String[] args) {
        String isim = args.length > 0? args[0] : "M.Nihat Yava�";

        StringBuffer ba�Harfler = new StringBuffer();
        int uzunluk = isim.length();

        for (int i = 0; i < uzunluk; i++)
            if (Character.isUpperCase (isim.charAt (i)))
                ba�Harfler.append (isim.charAt(i));
        System.out.println ("Ad ve soyad�n�n tam yaz�l�m�: [" + isim + "]");
        System.out.println ("Sadece ba� harfleri: [" + ba�Harfler + "]");
    } // main(..) metodu sonu...
} // J2fq_3 s�n�f� sonu...

/* ��kt�:
**  >java J2fq_3  **
Ad ve soyad�n�n tam yaz�l�m�: [M.Nihat Yava�]
Sadece ba� harfleri: [MNY]

**  >java J2fq_3 "Z.Nihal Yava� Candan"  ** TEKRAR
Ad ve soyad�n�n tam yaz�l�m�: [Z.Nihal Yava� Candan]
Sadece ba� harfleri: [ZNYC]
*/