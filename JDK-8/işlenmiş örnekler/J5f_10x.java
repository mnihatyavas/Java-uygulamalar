// J5f_10x.java: GraphPaperLayout (GrafikKaðýdýSerilimi) alt-örneði.

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.LayoutManager2;

import java.util.Hashtable;

public class J5f_10x implements LayoutManager2 {
    int yatayAralýk;
    int dikeyAralýk;
    Dimension ýzgaraEbatý; // Toplam ýzgara ebatýný (5,5) bölmeli aldýk...
    Hashtable<Component, Rectangle> komponentTablosu;

    // Varsayýlý (1,1) ebatlý ve tamponsuz bir grafik kaðýdý serilimi yaratýr...
    public J5f_10x() {this (new Dimension (1,1));} // Parametresiz kurucu...

    // Parametreyle girilen (x,y) ebatlý ve tamponsuz bir grafik kaðýdý serilimi yaratýr...
    public J5f_10x (Dimension ýzgaraEbatý) {this (ýzgaraEbatý, 0, 0);} // Tek parametreli kurucu...

    // Ebatlý ve tamponlu bir grafik kaðýdý serilimi yaratýr...
    public J5f_10x (Dimension ýzgaraEbatý, int yatayAralýk, int dikeyAralýk) {
        if ((ýzgaraEbatý.width <= 0) || (ýzgaraEbatý.height <= 0)) // java.lang.IllegalArgumentException...
            throw new IllegalArgumentException ("HATA. Izgara ebatlarý sýfýrdan büyük verilmelidir!");

        this.ýzgaraEbatý = new Dimension (ýzgaraEbatý);
        this.yatayAralýk = yatayAralýk;
        this.dikeyAralýk = dikeyAralýk;
        komponentTablosu = new Hashtable<Component, Rectangle>();
    } // J5f_10x(..) 3 parametreli kurucusu sonu...

    // LayoutManager2 hazýr esgeçme metodlarýnýn içeriklerini kendimizce þekillendirelim...
    public Dimension getGridSize() {return new Dimension (ýzgaraEbatý);}
    public void setGridSize (Dimension ebat) {setGridSize (ebat.width, ebat.height);}
    public void setGridSize (int en, int boy) {ýzgaraEbatý = new Dimension (en, boy);}
    public void setConstraints (Component komponent, Rectangle alaný) {komponentTablosu.put (komponent, new Rectangle (alaný));}
    public void addLayoutComponent (String ad, Component komponent) {} // Boþgeç...
    public void removeLayoutComponent (Component komponent) {komponentTablosu.remove (komponent);}
    public Dimension preferredLayoutSize (Container kab) {return getLayoutSize (kab, true);}
    public Dimension minimumLayoutSize(Container kab) {return getLayoutSize (kab, false);}
    public Dimension maximumLayoutSize (Container kab) {return new Dimension (Integer.MAX_VALUE, Integer.MAX_VALUE);}
    public float getLayoutAlignmentX (Container kab) {return 0.5f;} // Ortalansýn...
    public float getLayoutAlignmentY (Container kab) {return 0.5f;} // Ortalansýn...
    public void invalidateLayout (Container kab) {} // Boþgeç...

    protected Dimension getLayoutSize (Container kab, boolean tercihiMi) {
        Dimension azamiEbat = getLargestCellSize(kab, tercihiMi);
        Insets tamponlar = kab.getInsets();
        azamiEbat.width = (azamiEbat.width * ýzgaraEbatý.width) + (yatayAralýk * (ýzgaraEbatý.width + 1)) + tamponlar.left + tamponlar.right;
        azamiEbat.height = (azamiEbat.height * ýzgaraEbatý.height) + (dikeyAralýk * (ýzgaraEbatý.height + 1)) + tamponlar.top + tamponlar.bottom;
        return azamiEbat;
    } // getLayoutSize(..) hazýr metodu sonu...

    protected Dimension getLargestCellSize (Container kab, boolean tercihiMi) {
        int komponentSayýsý = kab.getComponentCount();
        Dimension azamiHücreEbatý = new Dimension (0,0);
        for (int i = 0; i < komponentSayýsý; i++ ) {
            Component komp = kab.getComponent(i);
            Rectangle dikdörtgen = komponentTablosu.get (komp);
            if (komp != null && dikdörtgen != null ) {
                Dimension komponentEbatý;
                if (tercihiMi) komponentEbatý = komp.getPreferredSize();
                else komponentEbatý = komp.getMinimumSize();

                azamiHücreEbatý.width = Math.max (azamiHücreEbatý.width, komponentEbatý.width / dikdörtgen.width);
                azamiHücreEbatý.height = Math.max (azamiHücreEbatý.height, komponentEbatý.height / dikdörtgen.height);
            } // if kararý sonu...
        } // for döngüsü sonu...
        return azamiHücreEbatý;
    } // getLargestCellSize(..) hazýr metodu sonu...

    public void layoutContainer (Container kab) {
        synchronized (kab.getTreeLock()) {
            Insets tamponlar = kab.getInsets();
            int komponentSayýsý = kab.getComponentCount();
            if (komponentSayýsý == 0) return;

            Dimension kabEbatý = kab.getSize();
            int toplamEn = kabEbatý.width - (tamponlar.left + tamponlar.right);
            int toplamBoy = kabEbatý.height - (tamponlar.top + tamponlar.bottom);

            int toplamHücreEni = toplamEn / ýzgaraEbatý.width;
            int toplamHücreBoyu = toplamBoy / ýzgaraEbatý.height;

            // Aralýksýz hücre ebatlarý...
            int hücreEni = (toplamEn - ((ýzgaraEbatý.width + 1) * yatayAralýk)) / ýzgaraEbatý.width;
            int hücreBoyu = (toplamBoy - ((ýzgaraEbatý.height + 1) * dikeyAralýk)) / ýzgaraEbatý.height;

            for ( int i = 0; i < komponentSayýsý; i++ ) {
                Component komp = kab.getComponent (i);
                Rectangle dikdörtgen = komponentTablosu.get (komp);
                if (dikdörtgen != null) {
                    int x = tamponlar.left + (toplamHücreEni * dikdörtgen.x) + yatayAralýk;
                    int y = tamponlar.top + (toplamHücreBoyu * dikdörtgen.y) + dikeyAralýk;
                    int en = (hücreEni * dikdörtgen.width) - yatayAralýk;
                    int boy = (hücreBoyu * dikdörtgen.height) - dikeyAralýk;
                    komp.setBounds (x, y, en, boy);
                } // if kararý sonu...
            } // for döngüsü sonu...
        } // synchronized(..) bloðu sonu...
    } // layoutContainer(..) hazýr metodu sonu...

    public void addLayoutComponent (Component komponent, Object alaný) {
        if (alaný instanceof Rectangle) {
            Rectangle dikdörtgen = (Rectangle)alaný;
            if (dikdörtgen.width <= 0 || dikdörtgen.height <= 0 ) throw new IllegalArgumentException ("HATA: Serilime eklenemez: dikdörtgen en ve boyu pozitif olmalýdýr!");
            if ( dikdörtgen.x < 0 || dikdörtgen.y < 0 ) throw new IllegalArgumentException ("HATA: Serilime eklenemez: dikdörtgen (x&y konumu >= 0) olmalýdýr!");
            setConstraints (komponent, dikdörtgen);
        }else if (alaný != null) throw new IllegalArgumentException ("HATA: Serilime eklenemez: alan bir dikdörtgrn olmalýdýr!");
    } // addLayoutComponent(..) hazýr metodu sonu...
} // J5f_10x sýnýfý sonu...