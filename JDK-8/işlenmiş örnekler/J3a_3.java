// J3a_3.java: ListOfNumbers2 (ListeliSay�lar2) �rne�i.

import java.io.RandomAccessFile;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Vector;

public class J3a_3 {
    private Vector<Integer> vekt�r;
    private static final int EBAT = 100;

    public J3a_3() {
        vekt�r = new Vector<Integer>(EBAT);
        for (int i = 0; i < EBAT; i++) vekt�r.addElement ((int)(Math.random() * 1000));
        this.listeyiYaz();
        this.listeyiOku ("mny1.txt");
    } // J3a_3() kurucu sonu...

    public void listeyiYaz() {
        PrintWriter pw = null;

        try {pw = new PrintWriter (new FileWriter ("mny1.txt"));
            for (int i = 0; i < vekt�r.size(); i++) pw.println (vekt�r.elementAt (i));
        }catch (ArrayIndexOutOfBoundsException ist) {System.err.println ("ArrayIndexOutOfBoundsException/DizinEndeksiS�n�rA��m��stisnas� yakaland�: " + ist.getMessage());
        }catch (IOException ist) {System.err.println ("IOException/OkumaYazma�stisnas� yakaland�: " + ist.getMessage());
        }finally {
            if (pw != null) {System.out.println ("mny1.txt dosyas�n�n PrintWriter'� kapat�l�yor!"); pw.close();
            }else System.out.println ("PrintWriter a��k de�il!");
        } // try-catch..-finally blo�u sonu...
    } // listeyiYaz() metodu sonu...

    public void listeyiOku (String dosyaAd�) {
        String sat�r = null;
        System.out.println ("\n[" + dosyaAd� + "] kay�tlar� okunup yan-yana yazd�r�l�yor:");
        try {RandomAccessFile raf = new RandomAccessFile (dosyaAd�, "r");
            while ((sat�r = raf.readLine()) != null) {
                Integer i = new Integer (Integer.parseInt (sat�r));
                System.out.print (i + "\t");
            } // while d�ng�s� sonu...
        }catch (FileNotFoundException ist) {System.err.println ("[" + dosyaAd� + "] dosyas� bulunamad�!");
        }catch (IOException ist) {System.err.println (ist.toString());
        } // try-catc-catch blo�u sonu...
    } // listeyiOku(..) metodu sonu...

    public static void main (String[] args) {new J3a_3();}
} // J3a_3 s�n�f� sonu...

/* ��kt�:
**  >java J3a_3  **
mny1.txt dosyas�n�n PrintWriter'� kapat�l�yor!

[mny1.txt] kay�tlar� okunup yan-yana yazd�r�l�yor:
666     748     275     483     829     239     694     675     346     626
703     48      792     168     853     467     431     330     733     873
202     676     445     14      369     39      759     349     728     216
365     369     708     936     489     684     244     723     683     147
63      593     772     199     879     233     791     410     79      418
237     846     641     936     115     540     398     240     70      810
292     357     356     598     895     323     522     315     334     588
470     910     715     346     972     987     285     733     834     867
876     181     627     394     609     9       319     169     448     482
200     622     966     170     210     14      590     425     136     9
*/