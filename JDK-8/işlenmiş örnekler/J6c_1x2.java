// J6c_1x2.java: Person (Ki�i) alt-�rne�i.

import java.util.List;
import java.util.ArrayList;

import java.time.chrono.IsoChronology;
import java.time.LocalDate;

public class J6c_1x2 {
    public enum Cinsiyet {ERKEK, KADIN}
    String isim; 
    LocalDate do�umTarihi;
    Cinsiyet cinsi;
    String eposta;
  
    J6c_1x2 (String isimArg, LocalDate do�umTarihiArg, Cinsiyet cinsiArg, String epostaArg) {// Kurucu...
        isim = isimArg;
        do�umTarihi = do�umTarihiArg;
        cinsi = cinsiArg;
        eposta = epostaArg;
    } // J6c_1x2(..) kurucusu sonu...

    public Cinsiyet cinsiAl() {return cinsi;}
    public String ismiAl() {return isim;}
    public String epostay�Al() {return eposta;}
    public LocalDate do�umTarihiniAl() {return do�umTarihi;}
    public int ya��Al() {return do�umTarihi.until (IsoChronology.INSTANCE.dateNow()).getYears();}
    public static int do�umTarihineG�reKar��la�t�r (J6c_1x2 a, J6c_1x2 b) {return a.do�umTarihi.compareTo (b.do�umTarihi);}
    public void ki�iyiYaz() {System.out.println (isim + ", " + this.ya��Al() + ", " + cinsi + ", " + eposta);}

    public static List<J6c_1x2> listeyiYarat() {
        List<J6c_1x2> liste = new ArrayList<>();

        liste.add (new J6c_1x2 (
            "Fred",
            IsoChronology.INSTANCE.date (1980, 6, 20),
            J6c_1x2.Cinsiyet.ERKEK,
            "fred@gmail.com"));
        liste.add (new J6c_1x2 (
            "Hilal",
            IsoChronology.INSTANCE.date (1986, 7, 1),
            J6c_1x2.Cinsiyet.KADIN,
            "hilal@hotmail.com"));
        liste.add (new J6c_1x2 (
            "Jane",
            IsoChronology.INSTANCE.date (1990, 7, 15),
            J6c_1x2.Cinsiyet.KADIN,
            "jane@gmail.com"));
        liste.add (new J6c_1x2 (
            "George",
            IsoChronology.INSTANCE.date (1991, 8, 13),
            J6c_1x2.Cinsiyet.ERKEK,
            "george@gmail.com"));
        liste.add (new J6c_1x2 (
            "Bob",
            IsoChronology.INSTANCE.date (2000, 9, 12),
            J6c_1x2.Cinsiyet.ERKEK,
            "bob@gmail.com"));
        liste.add (new J6c_1x2 (
            "Nihat",
            IsoChronology.INSTANCE.date (1957, 4, 17),
            J6c_1x2.Cinsiyet.ERKEK,
            "mnyavas@hotmail.com"));
        liste.add (new J6c_1x2 (
            "Metin",
            IsoChronology.INSTANCE.date (1998, 4, 28),
            J6c_1x2.Cinsiyet.ERKEK,
            "metin@hotmail.com"));
        liste.add (new J6c_1x2 (
            "Belk�s",
            IsoChronology.INSTANCE.date (1984, 10, 16),
            J6c_1x2.Cinsiyet.KADIN,
            "belkis@hotmail.com"));
        liste.add (new J6c_1x2 (
            "Ersin",
            IsoChronology.INSTANCE.date (1996, 11, 16),
            J6c_1x2.Cinsiyet.ERKEK,
            "ersin@hotmail.com"));

        return liste;
    } // listeyiYarat() metodu sonu...
} // J6c_1x2 s�n�f� sonu...