// J2b_15.java: MultiDimArrayDemo (�okBoyutluDizinG�sterimi) �rne�i.

class J2b_15 {
    public static void main (String[] args) {
        String[][] isimler = {
            {"Bay: ","Bekar Bayan: ","Evli Bayan: "},
            {"M.Nihat Yava�", "Sevim Yava�", "Nihal Yava� Candan"},
            {"Sefer G�kt�rk", "Belk�s Candan", "Hatice Yava� Ka�ar"},
            {"Adil �zbay", "Hilal G�kt�rk", "S�heyla Yava� �zbay"},
            {"Hamza Candan","Nurilh�da K���kbay","Song�l Yava� G�kt�rk"},
            {"Fatih �zbay","Canan Candan","Han�m Emanet Yava�"}};

        for (int i = 1; i < isimler.length; i++) {
            for (int j = 0; j < isimler[0].length; j++)
                System.out.println (isimler[0][j] + isimler[i][j]);
            System.out.println();
        } // for-i d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J2b_15 s�n�f� sonu...

/* ��kt�:
**  >java J2b_15  **
Bay: M.Nihat Yava�
Bekar Bayan: Sevim Yava�
Evli Bayan: Nihal Yava� Candan

Bay: Sefer G�kt�rk
Bekar Bayan: Belk�s Candan
Evli Bayan: Hatice Yava� Ka�ar

Bay: Adil �zbay
Bekar Bayan: Hilal G�kt�rk
Evli Bayan: S�heyla Yava� �zbay

Bay: Hamza Candan
Bekar Bayan: Nurilh�da K���kbay
Evli Bayan: Song�l Yava� G�kt�rk

Bay: Fatih �zbay
Bekar Bayan: Canan Candan
Evli Bayan: Han�m Emanet Yava�
*/