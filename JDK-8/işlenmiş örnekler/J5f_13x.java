// J5f_13x.java: SpringUtilities (KaynakKullanýmlýklarý) alt-örneði.

import java.awt.Container;
import java.awt.Component;

import javax.swing.Spring;
import javax.swing.SpringLayout;

// SpringLayout/KaynakSerilim ile form ve ýzgara tarzý yerleþtirmeler yapar...
public class J5f_13x {
    public static void ýzgaraYap (Container kab, int satýrSayýsý, int kolonSayýsý,
                int konumX, int konumY, int xAralýk, int yAralýk) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab SpringLayout kullanmalý!"); return;}

        Spring xAralýkSpr = Spring.constant (xAralýk);
        Spring yAralýkSpr = Spring.constant (yAralýk);
        Spring konumXSpr = Spring.constant (konumX);
        Spring konumYSpr = Spring.constant (konumY);
        int azamiParçaSayýsý = satýrSayýsý * kolonSayýsý;

        // Azami en/boy hesabýyla, her parça hücresi ayný ebatta olacaktýr...
        Spring azamiEnSpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getWidth();
        Spring azamiBoySpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getHeight();
        for (int i = 1; i < azamiParçaSayýsý; i++) {
            SpringLayout.Constraints sýnýrlayýcýlar = kaynakSerilim.getConstraints (kab.getComponent (i));
            azamiEnSpr = Spring.max (azamiEnSpr, sýnýrlayýcýlar.getWidth());
            azamiBoySpr = Spring.max (azamiBoySpr, sýnýrlayýcýlar.getHeight());
        } // for döngüsü sonu...

        for (int i = 0; i < azamiParçaSayýsý; i++) {
            SpringLayout.Constraints sýnýrlayýcýlar = kaynakSerilim.getConstraints (kab.getComponent (i));
            sýnýrlayýcýlar.setWidth (azamiEnSpr);
            sýnýrlayýcýlar.setHeight (azamiBoySpr);
        } // for döngüsü sonu...

        SpringLayout.Constraints sonSýnýrlayýcýlar = null;
        SpringLayout.Constraints sonSatýrSýnýrlayýcýlarý = null;
        for (int i = 0; i < azamiParçaSayýsý; i++) {
            SpringLayout.Constraints sýnýrlayýcýlar = kaynakSerilim.getConstraints (kab.getComponent (i));
            if (i % kolonSayýsý == 0) {// Bir sonraki satýra geçer...
                sonSatýrSýnýrlayýcýlarý = sonSýnýrlayýcýlar;
                sýnýrlayýcýlar.setX (konumXSpr);
            }else // x konumu bir önceki kolona görelidir...
                sýnýrlayýcýlar.setX (Spring.sum (sonSýnýrlayýcýlar.getConstraint (SpringLayout.EAST), xAralýkSpr));

            if (i / kolonSayýsý == 0) // Ýlk satýr...
                sýnýrlayýcýlar.setY (konumYSpr);
            else // y konumu bir önceki satýra görelidir...
                sýnýrlayýcýlar.setY (Spring.sum (sonSatýrSýnýrlayýcýlarý.getConstraint (SpringLayout.SOUTH),yAralýkSpr));

            sonSýnýrlayýcýlar = sýnýrlayýcýlar;
        } // for döngüsü sonu...

        // Kab'ýn ebatý kuruluyor...
        SpringLayout.Constraints kabSýnýrlayýcýlarý = kaynakSerilim.getConstraints(kab);
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.SOUTH, // Dikey ebat...
                Spring.sum (Spring.constant (yAralýk), sonSýnýrlayýcýlar.getConstraint (SpringLayout.SOUTH)));
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.EAST, // Yatay ebat...
                Spring.sum (Spring.constant (xAralýk), sonSýnýrlayýcýlar.getConstraint (SpringLayout.EAST)));
    } // ýzgaraYap(..) metodu sonu...

    public static void kesifIzgaraYap (Container kab, int satýrSayýsý, int kolonSayýsý,
            int konumX, int konumY, int xAralýk, int yAralýk) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab, SpringLayout kullanmalý!"); return;}

        // Her bir parça tek tek azami geniþiliði ve aralýðýyla yatay hizalandýrýlýyor...
        Spring x = Spring.constant (konumX);
        for (int k = 0; k < kolonSayýsý; k++) {
            Spring en = Spring.constant (0);
            for (int s = 0; s < satýrSayýsý; s++) en = Spring.max (en, hücrelerÝçinSýnýrlayýcýlarýAl (s, k, kab, kolonSayýsý).getWidth());
            for (int s = 0; s < satýrSayýsý; s++) {
                SpringLayout.Constraints sýnýrlayýcýlar = hücrelerÝçinSýnýrlayýcýlarýAl (s, k, kab, kolonSayýsý);
                sýnýrlayýcýlar.setX (x);
                sýnýrlayýcýlar.setWidth (en);
            } // Ýç-for döngüsü sonu...
            x = Spring.sum (x, Spring.sum (en, Spring.constant (xAralýk)));
        } // Dýþ-for döngüsü sonu...

        // Her bir parça tek tek azami yüksekliði ve aralýðýyla dikey hizalandýrýlýyor...
        Spring y = Spring.constant (konumY);
        for (int s = 0; s < satýrSayýsý; s++) {
            Spring boy = Spring.constant (0);
            for (int k = 0; k < kolonSayýsý; k++) boy = Spring.max (boy, hücrelerÝçinSýnýrlayýcýlarýAl (s, k, kab, kolonSayýsý).getHeight());
            for (int k = 0; k < kolonSayýsý; k++) {
                SpringLayout.Constraints sýnýrlayýcýlar = hücrelerÝçinSýnýrlayýcýlarýAl (s, k, kab, kolonSayýsý);
                sýnýrlayýcýlar.setY (y);
                sýnýrlayýcýlar.setHeight (boy);
            } // Ýç for döngüsü sonu...
            y = Spring.sum (y, Spring.sum (boy, Spring.constant (yAralýk)));
        } // Dýþ- for döngüsü sonu...

        // Kab'ýn ebatý kuruluyor...
        SpringLayout.Constraints kabSýnýrlayýcýlarý = kaynakSerilim.getConstraints (kab);
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.SOUTH, y);
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.EAST, x);
    } // kesifIzgaraYap(..) metodu sonu...

    private static SpringLayout.Constraints hücrelerÝçinSýnýrlayýcýlarýAl (
                int satýrNo, int kolonNo, Container kab, int parçaSayýsý) {
        SpringLayout kaynakSerilim = (SpringLayout)kab.getLayout();
        Component parça = kab.getComponent (satýrNo * parçaSayýsý + kolonNo);
        return kaynakSerilim.getConstraints (parça);
    } // hücrelerÝçinSýnýrlayýcýlarýAl(..) metodu sonu...

    // (Debug için) komponentlerin tercihi, asgari ve azami ebatlarýný dos penceresine yazar...
    public static void ebatlarýYaz (Component parça) {
        System.out.println ("Asgari ebat = " + parça.getMinimumSize());
        System.out.println ("Tercihi ebat = " + parça.getPreferredSize());
        System.out.println ("Azami ebat = " + parça.getMaximumSize());
    } // ebatlarýYaz(..) metodu sonu...
} // J5f_13x sýnýfý sonu...