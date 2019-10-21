// J4a_7x2.java: MenuItem (MenüBirimi) alt-örneği.

public class J4a_7x2 {
    public String ad = null;
    public String tip = null;
    public String izah = null;
    public String resminYolu = null;
    public int fiyat = 0;
    public int siparişMiktarı = 0;

    public static J4a_7x2[] menüyüBaşlat() {
        J4a_7x2 [] birimler = new J4a_7x2[4];

        J4a_7x2 menüBirimi = new J4a_7x2();
        menüBirimi.ad = "Tuskan Bruschetta'sı";
        menüBirimi.tip = "Ön Yemek";
        menüBirimi.izah = "Geleneksel Roma domates dilimleri, fesleğen ve çok taze zeytinyağı, mayalı ekmek tostu üzerinde servis yapılır.";
        menüBirimi.resminYolu = "bruschetta.jpg";
        menüBirimi.fiyat = 4;
        birimler[0] = menüBirimi;

        menüBirimi = new J4a_7x2();
        menüBirimi.ad = "İtalyan Açıkekmeği";
        menüBirimi.tip = "Ön Yemek";
        menüBirimi.izah = "Mozzarella peniri, dilimli domates ve fesleğen gevrek açıkekmek üzerinde, sarmısak ve acıbiber serpintili.";
        menüBirimi.resminYolu = "flatbread.jpg";
        menüBirimi.fiyat = 5;
        birimler[1] = menüBirimi;

        menüBirimi = new J4a_7x2();
        menüBirimi.ad = "Piliçli Gnoççi";
        menüBirimi.tip = "Çorba";
        menüBirimi.izah = "Piliç kızartması, İtalyan meyve tatlısı ve fesleğen karmalı kremalı çorba. Salata ve çubuk ekmekle servis edilir.";
        menüBirimi.resminYolu = "chicken_gnocchi_soup.jpg";
        menüBirimi.fiyat = 7;
        birimler[2] = menüBirimi;

        menüBirimi = new J4a_7x2();
        menüBirimi.ad = "Minestrone";
        menüBirimi.tip = "Çorba";
        menüBirimi.izah = "Haşlanmış taze mahalli sebzeler, fasülye ve kekle karışık domates çorbası. Salata ve çubuk ekmekle servis edilir.";
        menüBirimi.resminYolu = "minestrone.jpg";
        menüBirimi.fiyat = 8;
        birimler[3] = menüBirimi;

        return birimler;
    } // menüyüBaşlat() metodu sonu...
} // J4a_7x2 sınıfı sonu...