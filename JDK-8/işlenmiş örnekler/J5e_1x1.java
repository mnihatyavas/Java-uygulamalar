// J5e_1x1.java: Rule (Cetvel) alt-�rne�i.

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.JComponent;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleContext;
import javax.accessibility.AccessibleStateSet;

import java.util.Locale;

public class J5e_1x1 extends JComponent implements Accessible {
    public static final int �N� = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int YATAY = 0;
    public static final int D�KEY = 1;
    public static final int EBAT = 35;

    public int yat�kDik;
    public boolean metrikMi;
    private int art��;
    private int santimdekiNoktalar;

    public J5e_1x1 (int duru�, boolean �l��Birimi) {
        yat�kDik = duru�;
        metrikMi = �l��Birimi;
        art���VeBirimleriKur();
    } // J5e_1x1(..) kurucusu sonu...

    private void art���VeBirimleriKur() {
        if (metrikMi) {
            santimdekiNoktalar = (int)((double)�N� / (double)2.54); // Herbir santimdeki nokta say�s� (px)...
            art�� = santimdekiNoktalar;
        }else {
            santimdekiNoktalar = �N�;
            art�� = santimdekiNoktalar / 2;
        } // if-else karar� sonu...
    } // art���VeBirimleriKur() metodu sonu...

    public void cetveliKur (boolean metrikMi) {
        if (accessibleContext != null && this.metrikMi != metrikMi) {
            if (metrikMi) {
                accessibleContext.firePropertyChange (
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    Eri�ilebilirCetvelDurumu.�N�LER,
                    Eri�ilebilirCetvelDurumu.SANT�MLER);
            } else {
                accessibleContext.firePropertyChange (
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    Eri�ilebilirCetvelDurumu.SANT�MLER,
                    Eri�ilebilirCetvelDurumu.�N�LER);
            } // if-else karar� sonu...
        } // d��-if karar� sonu...
        this.metrikMi = metrikMi;
        art���VeBirimleriKur();
        repaint(); // painComponent(..) metodunu �a��r�r...
    } // cetveliKur(..) metodu sonu...

    protected void paintComponent (Graphics g) {
        Rectangle cetvelDikd�rtgeni = g.getClipBounds();

        // Cetvel alan�n� kirli kahve-portakal rengiyle doldural�m...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (cetvelDikd�rtgeni.x, cetvelDikd�rtgeni.y, cetvelDikd�rtgeni.width, cetvelDikd�rtgeni.height);

        // Cetvel etiketleri yaz� fonunu siyah renkte 10 punto ebatl� yapal�m...
        g.setFont (new Font ("SansSerif", Font.PLAIN, 10));
        g.setColor (Color.black);

        int son = 0;
        int ilk = 0;
        int kertikUzunlu�u = 0;
        String sm�n�Metni = null;

        // Cetveldeki ilk ve son kertik konumlar�n� hesaplayal�m...
        if (yat�kDik == YATAY) {
            ilk = (cetvelDikd�rtgeni.x / art��) * art��;
            son = (((cetvelDikd�rtgeni.x + cetvelDikd�rtgeni.width) / art��) + 1) * art��;
        }else {// D�KEY
            ilk = (cetvelDikd�rtgeni.y / art��) * art��;
            son = (((cetvelDikd�rtgeni.y + cetvelDikd�rtgeni.height) / art��) + 1) * art��;
        } // if-else karar� sonu...

        // Cetvel ba��na kertikli "0 sm" veya "0 in�" a��klamas� gelecek...
        if (ilk == 0) {
            sm�n�Metni = Integer.toString (0) + (metrikMi ? " sm" : " in�");
            kertikUzunlu�u = 10;
            if (yat�kDik == YATAY) {
                g.drawLine (0, EBAT-1, 0, EBAT-kertikUzunlu�u-1);
                g.drawString (sm�n�Metni, 2, 21);
            } else {// D�KEY
                g.drawLine (EBAT-1, 0, EBAT-kertikUzunlu�u-1, 0);
                g.drawString (sm�n�Metni, 9, 10);
            } // if-else karar� sonu...
            sm�n�Metni = null;
            ilk = art��;
        } // d��-if karar� sonu...

        // Cetvelin sm-in� kertik ve rakamlar�n� kondural�m...
        for (int i = ilk; i < son; i += art��) {
            if (i % santimdekiNoktalar == 0)  {
                kertikUzunlu�u = 10;
                sm�n�Metni = Integer.toString (i / santimdekiNoktalar);
            } else {
                kertikUzunlu�u = 7;
                sm�n�Metni = null;
            } // if-else karar� sonu...

            if (kertikUzunlu�u != 0) {
                if (yat�kDik == YATAY) {
                    g.drawLine (i, EBAT-1, i, EBAT-kertikUzunlu�u-1);
                    if (sm�n�Metni != null) g.drawString (sm�n�Metni, i-3, 21);
                }else {// D�KEY
                    g.drawLine (EBAT-1, i, EBAT-kertikUzunlu�u-1, i);
                    if (sm�n�Metni != null) g.drawString (sm�n�Metni, 9, i+3);
                } // if-else karar� sonu...
            } // d��-if karar� sonu...
        } // for d�ng�s� sonu...
    } // paintComponent(..) haz�r metodu sonu...

    public int art���Al() {return art��;}
    public void tercihiY�ksekli�iKur (int boy) {setPreferredSize (new Dimension (EBAT, boy));}
    public void tercihiGeni�li�iKur (int en) {setPreferredSize (new Dimension (en, EBAT));}

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) accessibleContext = new Eri�ilebilirCetvel();
        return accessibleContext;
    } // getAccessibleContext() haz�r metodu sonu...

    protected class Eri�ilebilirCetvel extends AccessibleJComponent {
        public AccessibleRole getAccessibleRole() {return Eri�ilebilirCetvelRol�.CETVEL;}

        public AccessibleStateSet getAccessibleStateSet() {
            AccessibleStateSet stat�ler = super.getAccessibleStateSet();
            if (yat�kDik == D�KEY) stat�ler.add (AccessibleState.VERTICAL);
            else stat�ler.add (AccessibleState.HORIZONTAL);

            if (metrikMi) stat�ler.add (Eri�ilebilirCetvelDurumu.SANT�MLER);
            else stat�ler.add (Eri�ilebilirCetvelDurumu.�N�LER);

            return stat�ler;
        } // getAccessibleStateSet() haz�r metodu sonu...
    } // Eri�ilebilirCetvel s�n�f� sonu...
} // J5e_1x1 s�n�f� sonu...

class Eri�ilebilirCetvelRol� extends AccessibleRole {
    public static final Eri�ilebilirCetvelRol� CETVEL = new Eri�ilebilirCetvelRol� ("cetvel");

    protected Eri�ilebilirCetvelRol� (String anahtar) {super (anahtar);}
    public String toDisplayString (String kaynakPaketAd�, Locale yerel) {return key;}
} // Eri�ilebilirCetvelRol� s�n�f� sonu...

class Eri�ilebilirCetvelDurumu extends AccessibleState {
    public static final Eri�ilebilirCetvelDurumu �N�LER = new Eri�ilebilirCetvelDurumu ("in�");
    public static final Eri�ilebilirCetvelDurumu SANT�MLER = new Eri�ilebilirCetvelDurumu ("santim");

    protected Eri�ilebilirCetvelDurumu (String anahtar) {super (anahtar);} // Kurucu...
    public String toDisplayString (String kaynakPaketAd�, Locale yerel) {return key;}
} // Eri�ilebilirCetvelDurumu s�n�f� sonu...