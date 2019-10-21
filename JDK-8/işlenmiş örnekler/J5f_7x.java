// J5f_7x.java: DiagonalLayout (ÇaprazSerilim) alt-örneði.

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class J5f_7x implements LayoutManager {
    private int dikeyAralýk;
    private int asgariEn = 0, asgariBoy = 0;
    private int tercihiEn = 0, tercihiBoy = 0;
    private boolean ebatBelirsizMi = true;

    public J5f_7x() {this (15);} // Parametreli kurucuyu çaðýrýr...
    public J5f_7x (int v) {dikeyAralýk = v;} // Parametreli kurucu...

    // LayoutManager/SerilimYöneticisi hazýr metodlarý:
    public void addLayoutComponent (String ad, Component komponent) {}
    public void removeLayoutComponent (Component komponent) {}

    public Dimension preferredLayoutSize (Container kab) {
        Dimension boyut = new Dimension (0, 0);
        int komponentSayýsý = kab.getComponentCount();

        ebatlarýBelirle (kab);

        Insets tamponlar = kab.getInsets();
        boyut.width = tercihiEn + tamponlar.left + tamponlar.right;
        boyut.height = tercihiBoy + tamponlar.top + tamponlar.bottom;

        ebatBelirsizMi = false;

        return boyut;
    } // preferredLayoutSize(..) hazýr metodu sonu...

    public Dimension minimumLayoutSize (Container kab) {
        Dimension boyut = new Dimension (0, 0);
        int komponentSayýsý = kab.getComponentCount();

        Insets tamponlar = kab.getInsets();
        boyut.width = asgariEn + tamponlar.left + tamponlar.right;
        boyut.height = asgariBoy + tamponlar.top + tamponlar.bottom;

        ebatBelirsizMi = false;

        return boyut;
    } // minimumLayoutSize(..) hazýr metodu sonu...

    // Pencere ilk gösterildiðinde ve her deðiþtiðinde LayoutManager otomatikman çaðýrýr...
    public void layoutContainer (Container kab) {
        Insets tamponlar = kab.getInsets();
        int azamiEn = kab.getWidth() - (tamponlar.left + tamponlar.right);
        int azamiBoy = kab.getHeight() - (tamponlar.top + tamponlar.bottom);
        int komponentSayýsý = kab.getComponentCount();
        int öncekiEn = 0, öncekiBoy = 0;
        int x = 0, y = tamponlar.top;
        int satýrBoyu = 0, ilk = 0;
        int xTaþma = 0, yTaþma = 0;
        boolean tekKolonMu = false;

        if (ebatBelirsizMi) ebatlarýBelirle (kab);
        if (azamiEn <= asgariEn) tekKolonMu = true;
        if (azamiEn != tercihiEn) xTaþma = (azamiEn - tercihiEn)/(komponentSayýsý - 1);
        if (azamiBoy > tercihiBoy) yTaþma = (azamiBoy - tercihiBoy)/(komponentSayýsý - 1);

        for (int i = 0 ; i < komponentSayýsý ; i++) {
            Component k = kab.getComponent(i);
            if (k.isVisible()) {
                Dimension alan = k.getPreferredSize();

                if (i > 0) {
                    if (!tekKolonMu) x += öncekiEn/2 + xTaþma;
                    y += öncekiBoy + dikeyAralýk + yTaþma;
                } // if kararý sonu...

                if ((!tekKolonMu) && (x + alan.width) > (kab.getWidth() - tamponlar.right))
                    x = kab.getWidth() - tamponlar.bottom - alan.width;

                //if ((y + alan.height) > (kab.getHeight() - tamponlar.bottom)) {// aldýrma}

                k.setBounds (x, y, alan.width, alan.height);

                öncekiEn = alan.width;
                öncekiBoy = alan.height;
            } // dýþ-if kararý sonu...
        } // for döngüsü sonu...
    } // layoutContainer(..) hazýr metodu sonu...

    private void ebatlarýBelirle (Container kab) {
        int komponentSayýsý = kab.getComponentCount();
        Dimension alan = null;

        tercihiEn = 0;
        tercihiBoy = 0;
        asgariEn = 0;
        asgariBoy = 0;

        for (int i = 0; i < komponentSayýsý; i++) {
            Component k = kab.getComponent(i);
            if (k.isVisible()) {
                alan = k.getPreferredSize();

                if (i > 0) {
                    tercihiEn += alan.width/2;
                    tercihiBoy += dikeyAralýk;
                } else tercihiEn = alan.width;

                tercihiBoy += alan.height;
                asgariEn = Math.max (k.getMinimumSize().width, asgariEn);
                asgariBoy = tercihiBoy;
            } // dýþ-if kararý sonu...
        } // for döngüsü sonu...
    } // ebatlarýBelirle(..) metodu sonu...

    //public String toString() {String dizge = ""; return getClass().getName() + "[dikeyAralýk=" + dikeyAralýk + dizge + "]";}
} // J5f_7x sýnýfý sonu...