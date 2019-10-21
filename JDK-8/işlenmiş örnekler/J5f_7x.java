// J5f_7x.java: DiagonalLayout (�aprazSerilim) alt-�rne�i.

import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;

public class J5f_7x implements LayoutManager {
    private int dikeyAral�k;
    private int asgariEn = 0, asgariBoy = 0;
    private int tercihiEn = 0, tercihiBoy = 0;
    private boolean ebatBelirsizMi = true;

    public J5f_7x() {this (15);} // Parametreli kurucuyu �a��r�r...
    public J5f_7x (int v) {dikeyAral�k = v;} // Parametreli kurucu...

    // LayoutManager/SerilimY�neticisi haz�r metodlar�:
    public void addLayoutComponent (String ad, Component komponent) {}
    public void removeLayoutComponent (Component komponent) {}

    public Dimension preferredLayoutSize (Container kab) {
        Dimension boyut = new Dimension (0, 0);
        int komponentSay�s� = kab.getComponentCount();

        ebatlar�Belirle (kab);

        Insets tamponlar = kab.getInsets();
        boyut.width = tercihiEn + tamponlar.left + tamponlar.right;
        boyut.height = tercihiBoy + tamponlar.top + tamponlar.bottom;

        ebatBelirsizMi = false;

        return boyut;
    } // preferredLayoutSize(..) haz�r metodu sonu...

    public Dimension minimumLayoutSize (Container kab) {
        Dimension boyut = new Dimension (0, 0);
        int komponentSay�s� = kab.getComponentCount();

        Insets tamponlar = kab.getInsets();
        boyut.width = asgariEn + tamponlar.left + tamponlar.right;
        boyut.height = asgariBoy + tamponlar.top + tamponlar.bottom;

        ebatBelirsizMi = false;

        return boyut;
    } // minimumLayoutSize(..) haz�r metodu sonu...

    // Pencere ilk g�sterildi�inde ve her de�i�ti�inde LayoutManager otomatikman �a��r�r...
    public void layoutContainer (Container kab) {
        Insets tamponlar = kab.getInsets();
        int azamiEn = kab.getWidth() - (tamponlar.left + tamponlar.right);
        int azamiBoy = kab.getHeight() - (tamponlar.top + tamponlar.bottom);
        int komponentSay�s� = kab.getComponentCount();
        int �ncekiEn = 0, �ncekiBoy = 0;
        int x = 0, y = tamponlar.top;
        int sat�rBoyu = 0, ilk = 0;
        int xTa�ma = 0, yTa�ma = 0;
        boolean tekKolonMu = false;

        if (ebatBelirsizMi) ebatlar�Belirle (kab);
        if (azamiEn <= asgariEn) tekKolonMu = true;
        if (azamiEn != tercihiEn) xTa�ma = (azamiEn - tercihiEn)/(komponentSay�s� - 1);
        if (azamiBoy > tercihiBoy) yTa�ma = (azamiBoy - tercihiBoy)/(komponentSay�s� - 1);

        for (int i = 0 ; i < komponentSay�s� ; i++) {
            Component k = kab.getComponent(i);
            if (k.isVisible()) {
                Dimension alan = k.getPreferredSize();

                if (i > 0) {
                    if (!tekKolonMu) x += �ncekiEn/2 + xTa�ma;
                    y += �ncekiBoy + dikeyAral�k + yTa�ma;
                } // if karar� sonu...

                if ((!tekKolonMu) && (x + alan.width) > (kab.getWidth() - tamponlar.right))
                    x = kab.getWidth() - tamponlar.bottom - alan.width;

                //if ((y + alan.height) > (kab.getHeight() - tamponlar.bottom)) {// ald�rma}

                k.setBounds (x, y, alan.width, alan.height);

                �ncekiEn = alan.width;
                �ncekiBoy = alan.height;
            } // d��-if karar� sonu...
        } // for d�ng�s� sonu...
    } // layoutContainer(..) haz�r metodu sonu...

    private void ebatlar�Belirle (Container kab) {
        int komponentSay�s� = kab.getComponentCount();
        Dimension alan = null;

        tercihiEn = 0;
        tercihiBoy = 0;
        asgariEn = 0;
        asgariBoy = 0;

        for (int i = 0; i < komponentSay�s�; i++) {
            Component k = kab.getComponent(i);
            if (k.isVisible()) {
                alan = k.getPreferredSize();

                if (i > 0) {
                    tercihiEn += alan.width/2;
                    tercihiBoy += dikeyAral�k;
                } else tercihiEn = alan.width;

                tercihiBoy += alan.height;
                asgariEn = Math.max (k.getMinimumSize().width, asgariEn);
                asgariBoy = tercihiBoy;
            } // d��-if karar� sonu...
        } // for d�ng�s� sonu...
    } // ebatlar�Belirle(..) metodu sonu...

    //public String toString() {String dizge = ""; return getClass().getName() + "[dikeyAral�k=" + dikeyAral�k + dizge + "]";}
} // J5f_7x s�n�f� sonu...