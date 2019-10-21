// J3b_17c.java: Standard (Standart) örneði.

import java.io.InputStreamReader; // Karakter okuyucu...
import java.io.BufferedReader; // Satýr okuyucu...
import java.io.IOException;

public class J3b_17c {
    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
        String sayýStr;
        double toplam = 0;

        System.out.print ("Veri gir [q:çýk]: ");
        while ((sayýStr = br.readLine()) != null) {
            if (sayýStr.equals ("q")) break;
            try {System.out.println ("Okunan veri: [" + sayýStr + "]");
                toplam += Double.parseDouble (sayýStr);
            }catch (NumberFormatException ist) {System.err.println ("Geçersiz sayýsal veri!");
            } // try-catch bloðu sonu...
            System.out.print ("\nVeri gir: ");
        } // while döngüsü sonu...
        //if (br != null) br.close();

        System.out.println ("=============================\nToplam: " + toplam);
    } // main(..) metodu sonu...
} // J3b_17c sýnýfý sonu...