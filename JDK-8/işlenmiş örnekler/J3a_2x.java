// J3a_2x.java: ListOfNumbers (ListeliSay�lar) alt-�rne�i.

import java.io.RandomAccessFile;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class J3a_2x {
    private List<String> liste;
    private static final int EBAT = 10;
    private String dosya;

    public J3a_2x (String dosya) {
        this.dosya = dosya;
        liste = new ArrayList<String>(EBAT);
        for (int i = 0; i < EBAT; i++) liste.add ((i+1) + ".ci eleman: " + (int)(Math.random() * 1000));
    } // J3a_2x() kurucu sonu...

    public void listeyiYaz() {
        PrintWriter pw = null;
        try {pw = new PrintWriter (new FileWriter (dosya));
            for (int i = 0; i < liste.size(); i++) pw.println (liste.get (i));
        }catch (ArrayIndexOutOfBoundsException ist) {System.err.println ("ArrayIndexOutOfBoundsException/DizinEndeksiS�n�rA��m��stisnas� yakaland�: " + ist.getMessage());
        }catch (IOException ist) {System.err.println ("IOException/OkumaYazma�stisnas� yakaland�: " + ist.getMessage());
        }finally {
            if (pw != null) {System.out.println (dosya + "'n�n PrintWriter'� kapat�l�yor!"); pw.close();
            }else System.out.println ("PrintWriter a��k de�il!");
        } // try-catch..-finally blo�u sonu...
    } // listeyiYaz() metodu sonu...

    public void listeyiOku () {
        String sat�r = null;
        System.out.println ("\n[" + dosya + "] kay�tlar� okunup alt-alta yazd�r�l�yor:");
        try {RandomAccessFile raf = new RandomAccessFile (dosya, "r");
            while ((sat�r = raf.readLine()) != null) {
                System.out.println (sat�r);
            } // while d�ng�s� sonu...
        }catch (FileNotFoundException ist) {System.err.println ("[" + dosya + "] dosyas� bulunamad�!");
        }catch (IOException ist) {System.err.println (ist.toString());
        } // try-catc-catch blo�u sonu...
    } // listeyiOku(..) metodu sonu...
} // J3a_2x s�n�f� sonu...