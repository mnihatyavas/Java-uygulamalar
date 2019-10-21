// J3a_4x.java: ListOfNumbersDeclared (ListeliSayýlarBeyaný) alt-örneði.

import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;

public class J3a_4x {
    private List<String> liste;
    private static final int EBAT = 10;
    private String dosya;

    public J3a_4x (String dosya) throws IOException, FileNotFoundException {
        this.dosya = dosya;
        liste = new ArrayList<String>(EBAT);
        for (int i = 0; i < EBAT; i++) liste.add ((i+1) + ".ci eleman: " + (int)(Math.random() * 1000));
        this.listeyiYaz();
        this.listeyiOku();
    } // J3a_4x() kurucu sonu...

    public void listeyiYaz() throws IOException, ArrayIndexOutOfBoundsException {
        PrintWriter pw = null;
        pw = new PrintWriter (new FileWriter (dosya));
        for (int i = 0; i < liste.size(); i++) pw.println (liste.get (i));
        pw.close();
    } // listeyiYaz() metodu sonu...

    public void listeyiOku () throws FileNotFoundException, IOException {
        int k;
        System.out.println ("[" + dosya + "] kayýtlarý okunup alt-alta yazdýrýlýyor:");
        FileReader fr = new FileReader (dosya);
        while ((k = fr.read()) != -1) {System.out.print ((char)k);}
    } // listeyiOku(..) metodu sonu...
} // J3a_4x sýnýfý sonu...