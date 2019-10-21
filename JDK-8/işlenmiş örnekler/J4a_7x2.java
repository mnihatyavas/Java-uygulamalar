// J4a_7x2.java: MenuItem (Men�Birimi) alt-�rne�i.

public class J4a_7x2 {
    public String ad = null;
    public String tip = null;
    public String izah = null;
    public String resminYolu = null;
    public int fiyat = 0;
    public int sipari�Miktar� = 0;

    public static J4a_7x2[] men�y�Ba�lat() {
        J4a_7x2 [] birimler = new J4a_7x2[4];

        J4a_7x2 men�Birimi = new J4a_7x2();
        men�Birimi.ad = "Tuskan Bruschetta's�";
        men�Birimi.tip = "�n Yemek";
        men�Birimi.izah = "Geleneksel Roma domates dilimleri, fesle�en ve �ok taze zeytinya��, mayal� ekmek tostu �zerinde servis yap�l�r.";
        men�Birimi.resminYolu = "bruschetta.jpg";
        men�Birimi.fiyat = 4;
        birimler[0] = men�Birimi;

        men�Birimi = new J4a_7x2();
        men�Birimi.ad = "�talyan A��kekme�i";
        men�Birimi.tip = "�n Yemek";
        men�Birimi.izah = "Mozzarella peniri, dilimli domates ve fesle�en gevrek a��kekmek �zerinde, sarm�sak ve ac�biber serpintili.";
        men�Birimi.resminYolu = "flatbread.jpg";
        men�Birimi.fiyat = 5;
        birimler[1] = men�Birimi;

        men�Birimi = new J4a_7x2();
        men�Birimi.ad = "Pili�li Gno��i";
        men�Birimi.tip = "�orba";
        men�Birimi.izah = "Pili� k�zartmas�, �talyan meyve tatl�s� ve fesle�en karmal� kremal� �orba. Salata ve �ubuk ekmekle servis edilir.";
        men�Birimi.resminYolu = "chicken_gnocchi_soup.jpg";
        men�Birimi.fiyat = 7;
        birimler[2] = men�Birimi;

        men�Birimi = new J4a_7x2();
        men�Birimi.ad = "Minestrone";
        men�Birimi.tip = "�orba";
        men�Birimi.izah = "Ha�lanm�� taze mahalli sebzeler, fas�lye ve kekle kar���k domates �orbas�. Salata ve �ubuk ekmekle servis edilir.";
        men�Birimi.resminYolu = "minestrone.jpg";
        men�Birimi.fiyat = 8;
        birimler[3] = men�Birimi;

        return birimler;
    } // men�y�Ba�lat() metodu sonu...
} // J4a_7x2 s�n�f� sonu...