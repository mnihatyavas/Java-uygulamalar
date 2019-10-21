// J2c_13ax.java: Person (Ki�i) alt-�rne�i.

import java.util.List;
import java.util.ArrayList;
import java.time.chrono.IsoChronology;
import java.time.LocalDate;

public class J2c_13ax {
    public enum Cinsiyet {ERKEK, KADIN}
  
    String isim; 
    LocalDate do�umTarihi;
    Cinsiyet cinsi;
    String eposta;
  
    J2c_13ax (String isimArg, LocalDate do�umTarihiArg, Cinsiyet cinsiArg, String epostaArg) {
        isim = isimArg;
        do�umTarihi = do�umTarihiArg;
        cinsi = cinsiArg;
        eposta = epostaArg;
    } // J2c_13ax(..) kurucusu sonu...

    public Cinsiyet cinsiAl() {return cinsi;}
    public String ismiAl() {return isim;}
    public String epostay�Al() {return eposta;}
    public LocalDate do�umTarihiniAl() {return do�umTarihi;}
    public int ya��Al() {return do�umTarihi.until (IsoChronology.INSTANCE.dateNow()).getYears();}
    public static int do�umTarihineG�reKar��la�t�r (J2c_13ax a, J2c_13ax b) {return a.do�umTarihi.compareTo (b.do�umTarihi);}
    public void ki�iyiYaz() {System.out.println (isim + ", " + this.ya��Al() + ", " + cinsi + ", " + eposta);}

    public static List<J2c_13ax> listeyiYarat() {
        List<J2c_13ax> liste = new ArrayList<>();

        liste.add (new J2c_13ax (
            "Fred",
            IsoChronology.INSTANCE.date (1980, 6, 20),
            J2c_13ax.Cinsiyet.ERKEK,
            "fred@gmail.com"));
        liste.add (new J2c_13ax (
            "Jane",
            IsoChronology.INSTANCE.date (1990, 7, 15),
            J2c_13ax.Cinsiyet.KADIN,
            "jane@gmail.com"));
        liste.add (new J2c_13ax (
            "George",
            IsoChronology.INSTANCE.date (1991, 8, 13),
            J2c_13ax.Cinsiyet.ERKEK,
            "george@gmail.com"));
        liste.add (new J2c_13ax (
            "Bob",
            IsoChronology.INSTANCE.date (2000, 9, 12),
            J2c_13ax.Cinsiyet.ERKEK,
            "bob@gmail.com"));
        liste.add (new J2c_13ax (
            "Nihat",
            IsoChronology.INSTANCE.date (1957, 4, 17),
            J2c_13ax.Cinsiyet.ERKEK,
            "mnyavas@hotmail.com"));
        liste.add (new J2c_13ax (
            "Metin",
            IsoChronology.INSTANCE.date (1998, 4, 28),
            J2c_13ax.Cinsiyet.ERKEK,
            "metin@hotmail.com"));
        liste.add (new J2c_13ax (
            "Ersin",
            IsoChronology.INSTANCE.date (1996, 11, 16),
            J2c_13ax.Cinsiyet.ERKEK,
            "ersin@hotmail.com"));

        return liste;
    } // listeyiYarat() metodu sonu...
} // J2c_13ax s�n�f� sonu...