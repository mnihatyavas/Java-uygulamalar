// J3b_14.java: Password (Þifre) örneði.

import java.io.Console;
import java.util.Arrays;
import java.io.IOException;

public class J3b_14 {
    static private String þifreGiriþi;

    public static void main (String args[]) throws IOException {
        Console klavye = System.console();
        if (klavye == null) {System.err.println ("Klavyeniz yok!"); System.exit (1);}

        þifreGiriþi = klavye.readLine ("Þifrenizi tuþlayýnýz: ");
        char [] eskiÞifre = klavye.readPassword ("Þifrenizi onay için tekrar tuþlayýn: ");

        if (onay (þifreGiriþi, eskiÞifre)) {
            System.out.println ("Þifre deðiþikliði yapýlacaktýr==>");
            boolean uymadý;
            do {char [] yeniÞifre1 = klavye.readPassword ("Yeni þifrenizi girin: ");
                char [] yeniÞifre2 = klavye.readPassword ("Yeni þifrenizi tekrar girin: ");
                uymadý = ! Arrays.equals (yeniÞifre1, yeniÞifre2);

                if (uymadý) klavye.format ("Þifreler ayný deðil; tekrar deneyin.%n");
                else {deðiþtir (þifreGiriþi, yeniÞifre1);
                    klavye.format ("[%s] þifreniz yenisiyle deðiþtirildi.%n", þifreGiriþi);
                } // else kararý sonu...

                Arrays.fill (yeniÞifre1, ' ');
                Arrays.fill (yeniÞifre2, ' ');
            }while (uymadý);
        } // if kararý sonu...

        Arrays.fill (eskiÞifre, ' ');
    } // main(..) metodu sonu...
    
    static boolean onay (String þifreGiriþi, char[] þifre) {
        if (þifreGiriþi.equals (new String (þifre))) return true;
        return false;
    } // onay(..) metodu sonu...

    static void deðiþtir (String þifreGiriþi, char[] þifre) {þifreGiriþi = new String (þifre);}
} // J3b_14 sýnýfý sonu...

/* Çýktý:
**  >java J3b_14  **
Þifrenizi tuþlayýnýz: M.Nihat Yavaþ
Þifrenizi onay için tekrar tuþlayýn:
Þifre deðiþikliði yapýlacaktýr==>
Yeni þifrenizi girin:
Yeni þifrenizi tekrar girin:
Þifreler ayný deðil; tekrar deneyin.
Yeni þifrenizi girin:
Yeni þifrenizi tekrar girin:
[M.Nihat Yavaþ] þifreniz yenisiyle deðiþtirildi.
*/