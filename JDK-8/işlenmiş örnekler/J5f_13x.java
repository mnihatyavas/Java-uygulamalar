// J5f_13x.java: SpringUtilities (KaynakKullanımlıkları) alt-örneği.

import java.awt.Container;
import java.awt.Component;

import javax.swing.Spring;
import javax.swing.SpringLayout;

// SpringLayout/KaynakSerilim ile form ve ızgara tarzı yerleştirmeler yapar...
public class J5f_13x {
    public static void ızgaraYap (Container kab, int satırSayısı, int kolonSayısı,
                int konumX, int konumY, int xAralık, int yAralık) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab SpringLayout kullanmalı!"); return;}

        Spring xAralıkSpr = Spring.constant (xAralık);
        Spring yAralıkSpr = Spring.constant (yAralık);
        Spring konumXSpr = Spring.constant (konumX);
        Spring konumYSpr = Spring.constant (konumY);
        int azamiParçaSayısı = satırSayısı * kolonSayısı;

        // Azami en/boy hesabıyla, her parça hücresi aynı ebatta olacaktır...
        Spring azamiEnSpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getWidth();
        Spring azamiBoySpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getHeight();
        for (int i = 1; i < azamiParçaSayısı; i++) {
            SpringLayout.Constraints sınırlayıcılar = kaynakSerilim.getConstraints (kab.getComponent (i));
            azamiEnSpr = Spring.max (azamiEnSpr, sınırlayıcılar.getWidth());
            azamiBoySpr = Spring.max (azamiBoySpr, sınırlayıcılar.getHeight());
        } // for döngüsü sonu...

        for (int i = 0; i < azamiParçaSayısı; i++) {
            SpringLayout.Constraints sınırlayıcılar = kaynakSerilim.getConstraints (kab.getComponent (i));
            sınırlayıcılar.setWidth (azamiEnSpr);
            sınırlayıcılar.setHeight (azamiBoySpr);
        } // for döngüsü sonu...

        SpringLayout.Constraints sonSınırlayıcılar = null;
        SpringLayout.Constraints sonSatırSınırlayıcıları = null;
        for (int i = 0; i < azamiParçaSayısı; i++) {
            SpringLayout.Constraints sınırlayıcılar = kaynakSerilim.getConstraints (kab.getComponent (i));
            if (i % kolonSayısı == 0) {// Bir sonraki satıra geçer...
                sonSatırSınırlayıcıları = sonSınırlayıcılar;
                sınırlayıcılar.setX (konumXSpr);
            }else // x konumu bir önceki kolona görelidir...
                sınırlayıcılar.setX (Spring.sum (sonSınırlayıcılar.getConstraint (SpringLayout.EAST), xAralıkSpr));

            if (i / kolonSayısı == 0) // İlk satır...
                sınırlayıcılar.setY (konumYSpr);
            else // y konumu bir önceki satıra görelidir...
                sınırlayıcılar.setY (Spring.sum (sonSatırSınırlayıcıları.getConstraint (SpringLayout.SOUTH),yAralıkSpr));

            sonSınırlayıcılar = sınırlayıcılar;
        } // for döngüsü sonu...

        // Kab'ın ebatı kuruluyor...
        SpringLayout.Constraints kabSınırlayıcıları = kaynakSerilim.getConstraints(kab);
        kabSınırlayıcıları.setConstraint (SpringLayout.SOUTH, // Dikey ebat...
                Spring.sum (Spring.constant (yAralık), sonSınırlayıcılar.getConstraint (SpringLayout.SOUTH)));
        kabSınırlayıcıları.setConstraint (SpringLayout.EAST, // Yatay ebat...
                Spring.sum (Spring.constant (xAralık), sonSınırlayıcılar.getConstraint (SpringLayout.EAST)));
    } // ızgaraYap(..) metodu sonu...

    public static void kesifIzgaraYap (Container kab, int satırSayısı, int kolonSayısı,
            int konumX, int konumY, int xAralık, int yAralık) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab, SpringLayout kullanmalı!"); return;}

        // Her bir parça tek tek azami genişiliği ve aralığıyla yatay hizalandırılıyor...
        Spring x = Spring.constant (konumX);
        for (int k = 0; k < kolonSayısı; k++) {
            Spring en = Spring.constant (0);
            for (int s = 0; s < satırSayısı; s++) en = Spring.max (en, hücrelerİçinSınırlayıcılarıAl (s, k, kab, kolonSayısı).getWidth());
            for (int s = 0; s < satırSayısı; s++) {
                SpringLayout.Constraints sınırlayıcılar = hücrelerİçinSınırlayıcılarıAl (s, k, kab, kolonSayısı);
                sınırlayıcılar.setX (x);
                sınırlayıcılar.setWidth (en);
            } // İç-for döngüsü sonu...
            x = Spring.sum (x, Spring.sum (en, Spring.constant (xAralık)));
        } // Dış-for döngüsü sonu...

        // Her bir parça tek tek azami yüksekliği ve aralığıyla dikey hizalandırılıyor...
        Spring y = Spring.constant (konumY);
        for (int s = 0; s < satırSayısı; s++) {
            Spring boy = Spring.constant (0);
            for (int k = 0; k < kolonSayısı; k++) boy = Spring.max (boy, hücrelerİçinSınırlayıcılarıAl (s, k, kab, kolonSayısı).getHeight());
            for (int k = 0; k < kolonSayısı; k++) {
                SpringLayout.Constraints sınırlayıcılar = hücrelerİçinSınırlayıcılarıAl (s, k, kab, kolonSayısı);
                sınırlayıcılar.setY (y);
                sınırlayıcılar.setHeight (boy);
            } // İç for döngüsü sonu...
            y = Spring.sum (y, Spring.sum (boy, Spring.constant (yAralık)));
        } // Dış- for döngüsü sonu...

        // Kab'ın ebatı kuruluyor...
        SpringLayout.Constraints kabSınırlayıcıları = kaynakSerilim.getConstraints (kab);
        kabSınırlayıcıları.setConstraint (SpringLayout.SOUTH, y);
        kabSınırlayıcıları.setConstraint (SpringLayout.EAST, x);
    } // kesifIzgaraYap(..) metodu sonu...

    private static SpringLayout.Constraints hücrelerİçinSınırlayıcılarıAl (
                int satırNo, int kolonNo, Container kab, int parçaSayısı) {
        SpringLayout kaynakSerilim = (SpringLayout)kab.getLayout();
        Component parça = kab.getComponent (satırNo * parçaSayısı + kolonNo);
        return kaynakSerilim.getConstraints (parça);
    } // hücrelerİçinSınırlayıcılarıAl(..) metodu sonu...

    // (Debug için) komponentlerin tercihi, asgari ve azami ebatlarını dos penceresine yazar...
    public static void ebatlarıYaz (Component parça) {
        System.out.println ("Asgari ebat = " + parça.getMinimumSize());
        System.out.println ("Tercihi ebat = " + parça.getPreferredSize());
        System.out.println ("Azami ebat = " + parça.getMaximumSize());
    } // ebatlarıYaz(..) metodu sonu...
} // J5f_13x sınıfı sonu...