// J3b_17c.java: Standard (Standart) �rne�i.

import java.io.InputStreamReader; // Karakter okuyucu...
import java.io.BufferedReader; // Sat�r okuyucu...
import java.io.IOException;

public class J3b_17c {
    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        String say�Str;
        double toplam = 0;

        System.out.print ("Veri gir [q:��k]: ");
        while ((say�Str = br.readLine()) != null) {
            if (say�Str.equals ("q")) break;
            try {System.out.println ("Okunan veri: [" + say�Str + "]");
                toplam += Double.parseDouble (say�Str);
            }catch (NumberFormatException ist) {System.err.println ("Ge�ersiz say�sal veri!");
            } // try-catch blo�u sonu...
            System.out.print ("\nVeri gir: ");
        } // while d�ng�s� sonu...
        //if (br != null) br.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17c s�n�f� sonu...