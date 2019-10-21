// J2b_15.java: MultiDimArrayDemo (ÇokBoyutluDizinGösterimi) örneði.

class J2b_15 {
    public static void main (String[] args) {
        String[][] isimler = {
            {"Bay: ","Bekar Bayan: ","Evli Bayan: "},
            {"M.Nihat Yavaþ", "Sevim Yavaþ", "Nihal Yavaþ Candan"},
            {"Sefer Göktürk", "Belkýs Candan", "Hatice Yavaþ Kaçar"},
            {"Adil Özbay", "Hilal Göktürk", "Süheyla Yavaþ Özbay"},
            {"Hamza Candan","Nurilhüda Küçükbay","Songül Yavaþ Göktürk"},
            {"Fatih Özbay","Canan Candan","Haným Emanet Yavaþ"}};

        for (int i = 1; i < isimler.length; i++) {
            for (int j = 0; j < isimler[0].length; j++)
                System.out.println (isimler[0][j] + isimler[i][j]);
            System.out.println();
        } // for-i döngüsü sonu...
    } // main(..) metodu sonu...
} // J2b_15 sýnýfý sonu...

/* Çýktý:
**  >java J2b_15  **
Bay: M.Nihat Yavaþ
Bekar Bayan: Sevim Yavaþ
Evli Bayan: Nihal Yavaþ Candan

Bay: Sefer Göktürk
Bekar Bayan: Belkýs Candan
Evli Bayan: Hatice Yavaþ Kaçar

Bay: Adil Özbay
Bekar Bayan: Hilal Göktürk
Evli Bayan: Süheyla Yavaþ Özbay

Bay: Hamza Candan
Bekar Bayan: Nurilhüda Küçükbay
Evli Bayan: Songül Yavaþ Göktürk

Bay: Fatih Özbay
Bekar Bayan: Canan Candan
Evli Bayan: Haným Emanet Yavaþ
*/