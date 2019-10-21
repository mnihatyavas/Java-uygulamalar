// J3a_2x.java: ListOfNumbers (ListeliSayýlar) alt-örneði.

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
        }catch (ArrayIndexOutOfBoundsException ist) {System.err.println ("ArrayIndexOutOfBoundsException/DizinEndeksiSýnýrAþýmýÝstisnasý yakalandý: " + ist.getMessage());
        }catch (IOException ist) {System.err.println ("IOException/OkumaYazmaÝstisnasý yakalandý: " + ist.getMessage());
        }finally {
            if (pw != null) {System.out.println (dosya + "'nýn PrintWriter'ý kapatýlýyor!"); pw.close();
            }else System.out.println ("PrintWriter açýk deðil!");
        } // try-catch..-finally bloðu sonu...
    } // listeyiYaz() metodu sonu...

    public void listeyiOku () {
        String satýr = null;
        System.out.println ("\n[" + dosya + "] kayýtlarý okunup alt-alta yazdýrýlýyor:");
        try {RandomAccessFile raf = new RandomAccessFile (dosya, "r");
            while ((satýr = raf.readLine()) != null) {
                System.out.println (satýr);
            } // while döngüsü sonu...
        }catch (FileNotFoundException ist) {System.err.println ("[" + dosya + "] dosyasý bulunamadý!");
        }catch (IOException ist) {System.err.println (ist.toString());
        } // try-catc-catch bloðu sonu...
    } // listeyiOku(..) metodu sonu...
} // J3a_2x sýnýfý sonu...