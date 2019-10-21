// J5f_10x.java: GraphPaperLayout (GrafikKa��d�Serilimi) alt-�rne�i.

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.LayoutManager2;

import java.util.Hashtable;

public class J5f_10x implements LayoutManager2 {
    int yatayAral�k;
    int dikeyAral�k;
    Dimension �zgaraEbat�; // Toplam �zgara ebat�n� (5,5) b�lmeli ald�k...
    Hashtable<Component, Rectangle> komponentTablosu;

    // Varsay�l� (1,1) ebatl� ve tamponsuz bir grafik ka��d� serilimi yarat�r...
    public J5f_10x() {this (new Dimension (1,1));} // Parametresiz kurucu...

    // Parametreyle girilen (x,y) ebatl� ve tamponsuz bir grafik ka��d� serilimi yarat�r...
    public J5f_10x (Dimension �zgaraEbat�) {this (�zgaraEbat�, 0, 0);} // Tek parametreli kurucu...

    // Ebatl� ve tamponlu bir grafik ka��d� serilimi yarat�r...
    public J5f_10x (Dimension �zgaraEbat�, int yatayAral�k, int dikeyAral�k) {
        if ((�zgaraEbat�.width <= 0) || (�zgaraEbat�.height <= 0)) // java.lang.IllegalArgumentException...
            throw new IllegalArgumentException ("HATA. Izgara ebatlar� s�f�rdan b�y�k verilmelidir!");

        this.�zgaraEbat� = new Dimension (�zgaraEbat�);
        this.yatayAral�k = yatayAral�k;
        this.dikeyAral�k = dikeyAral�k;
        komponentTablosu = new Hashtable<Component, Rectangle>();
    } // J5f_10x(..) 3 parametreli kurucusu sonu...

    // LayoutManager2 haz�r esge�me metodlar�n�n i�eriklerini kendimizce �ekillendirelim...
    public Dimension getGridSize() {return new Dimension (�zgaraEbat�);}
    public void setGridSize (Dimension ebat) {setGridSize (ebat.width, ebat.height);}
    public void setGridSize (int en, int boy) {�zgaraEbat� = new Dimension (en, boy);}
    public void setConstraints (Component komponent, Rectangle alan�) {komponentTablosu.put (komponent, new Rectangle (alan�));}
    public void addLayoutComponent (String ad, Component komponent) {} // Bo�ge�...
    public void removeLayoutComponent (Component komponent) {komponentTablosu.remove (komponent);}
    public Dimension preferredLayoutSize (Container kab) {return getLayoutSize (kab, true);}
    public Dimension minimumLayoutSize(Container kab) {return getLayoutSize (kab, false);}
    public Dimension maximumLayoutSize (Container kab) {return new Dimension (Integer.MAX_VALUE, Integer.MAX_VALUE);}
    public float getLayoutAlignmentX (Container kab) {return 0.5f;} // Ortalans�n...
    public float getLayoutAlignmentY (Container kab) {return 0.5f;} // Ortalans�n...
    public void invalidateLayout (Container kab) {} // Bo�ge�...

    protected Dimension getLayoutSize (Container kab, boolean tercihiMi) {
        Dimension azamiEbat = getLargestCellSize(kab, tercihiMi);
        Insets tamponlar = kab.getInsets();
        azamiEbat.width = (azamiEbat.width * �zgaraEbat�.width) + (yatayAral�k * (�zgaraEbat�.width + 1)) + tamponlar.left + tamponlar.right;
        azamiEbat.height = (azamiEbat.height * �zgaraEbat�.height) + (dikeyAral�k * (�zgaraEbat�.height + 1)) + tamponlar.top + tamponlar.bottom;
        return azamiEbat;
    } // getLayoutSize(..) haz�r metodu sonu...

    protected Dimension getLargestCellSize (Container kab, boolean tercihiMi) {
        int komponentSay�s� = kab.getComponentCount();
        Dimension azamiH�creEbat� = new Dimension (0,0);
        for (int i = 0; i < komponentSay�s�; i++ ) {
            Component komp = kab.getComponent(i);
            Rectangle dikd�rtgen = komponentTablosu.get (komp);
            if (komp != null && dikd�rtgen != null ) {
                Dimension komponentEbat�;
                if (tercihiMi) komponentEbat� = komp.getPreferredSize();
                else komponentEbat� = komp.getMinimumSize();

                azamiH�creEbat�.width = Math.max (azamiH�creEbat�.width, komponentEbat�.width / dikd�rtgen.width);
                azamiH�creEbat�.height = Math.max (azamiH�creEbat�.height, komponentEbat�.height / dikd�rtgen.height);
            } // if karar� sonu...
        } // for d�ng�s� sonu...
        return azamiH�creEbat�;
    } // getLargestCellSize(..) haz�r metodu sonu...

    public void layoutContainer (Container kab) {
        synchronized (kab.getTreeLock()) {
            Insets tamponlar = kab.getInsets();
            int komponentSay�s� = kab.getComponentCount();
            if (komponentSay�s� == 0) return;

            Dimension kabEbat� = kab.getSize();
            int toplamEn = kabEbat�.width - (tamponlar.left + tamponlar.right);
            int toplamBoy = kabEbat�.height - (tamponlar.top + tamponlar.bottom);

            int toplamH�creEni = toplamEn / �zgaraEbat�.width;
            int toplamH�creBoyu = toplamBoy / �zgaraEbat�.height;

            // Aral�ks�z h�cre ebatlar�...
            int h�creEni = (toplamEn - ((�zgaraEbat�.width + 1) * yatayAral�k)) / �zgaraEbat�.width;
            int h�creBoyu = (toplamBoy - ((�zgaraEbat�.height + 1) * dikeyAral�k)) / �zgaraEbat�.height;

            for ( int i = 0; i < komponentSay�s�; i++ ) {
                Component komp = kab.getComponent (i);
                Rectangle dikd�rtgen = komponentTablosu.get (komp);
                if (dikd�rtgen != null) {
                    int x = tamponlar.left + (toplamH�creEni * dikd�rtgen.x) + yatayAral�k;
                    int y = tamponlar.top + (toplamH�creBoyu * dikd�rtgen.y) + dikeyAral�k;
                    int en = (h�creEni * dikd�rtgen.width) - yatayAral�k;
                    int boy = (h�creBoyu * dikd�rtgen.height) - dikeyAral�k;
                    komp.setBounds (x, y, en, boy);
                } // if karar� sonu...
            } // for d�ng�s� sonu...
        } // synchronized(..) blo�u sonu...
    } // layoutContainer(..) haz�r metodu sonu...

    public void addLayoutComponent (Component komponent, Object alan�) {
        if (alan� instanceof Rectangle) {
            Rectangle dikd�rtgen = (Rectangle)alan�;
            if (dikd�rtgen.width <= 0 || dikd�rtgen.height <= 0 ) throw new IllegalArgumentException ("HATA: Serilime eklenemez: dikd�rtgen en ve boyu pozitif olmal�d�r!");
            if ( dikd�rtgen.x < 0 || dikd�rtgen.y < 0 ) throw new IllegalArgumentException ("HATA: Serilime eklenemez: dikd�rtgen (x&y konumu >= 0) olmal�d�r!");
            setConstraints (komponent, dikd�rtgen);
        }else if (alan� != null) throw new IllegalArgumentException ("HATA: Serilime eklenemez: alan bir dikd�rtgrn olmal�d�r!");
    } // addLayoutComponent(..) haz�r metodu sonu...
} // J5f_10x s�n�f� sonu...