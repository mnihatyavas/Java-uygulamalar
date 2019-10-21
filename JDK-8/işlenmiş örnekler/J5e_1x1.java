// J5e_1x1.java: Rule (Cetvel) alt-örneði.

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
    public static final int ÝNÇ = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int YATAY = 0;
    public static final int DÝKEY = 1;
    public static final int EBAT = 35;

    public int yatýkDik;
    public boolean metrikMi;
    private int artýþ;
    private int santimdekiNoktalar;

    public J5e_1x1 (int duruþ, boolean ölçüBirimi) {
        yatýkDik = duruþ;
        metrikMi = ölçüBirimi;
        artýþýVeBirimleriKur();
    } // J5e_1x1(..) kurucusu sonu...

    private void artýþýVeBirimleriKur() {
        if (metrikMi) {
            santimdekiNoktalar = (int)((double)ÝNÇ / (double)2.54); // Herbir santimdeki nokta sayýsý (px)...
            artýþ = santimdekiNoktalar;
        }else {
            santimdekiNoktalar = ÝNÇ;
            artýþ = santimdekiNoktalar / 2;
        } // if-else kararý sonu...
    } // artýþýVeBirimleriKur() metodu sonu...

    public void cetveliKur (boolean metrikMi) {
        if (accessibleContext != null && this.metrikMi != metrikMi) {
            if (metrikMi) {
                accessibleContext.firePropertyChange (
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    EriþilebilirCetvelDurumu.ÝNÇLER,
                    EriþilebilirCetvelDurumu.SANTÝMLER);
            } else {
                accessibleContext.firePropertyChange (
                    AccessibleContext.ACCESSIBLE_STATE_PROPERTY,
                    EriþilebilirCetvelDurumu.SANTÝMLER,
                    EriþilebilirCetvelDurumu.ÝNÇLER);
            } // if-else kararý sonu...
        } // dýþ-if kararý sonu...
        this.metrikMi = metrikMi;
        artýþýVeBirimleriKur();
        repaint(); // painComponent(..) metodunu çaðýrýr...
    } // cetveliKur(..) metodu sonu...

    protected void paintComponent (Graphics g) {
        Rectangle cetvelDikdörtgeni = g.getClipBounds();

        // Cetvel alanýný kirli kahve-portakal rengiyle dolduralým...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (cetvelDikdörtgeni.x, cetvelDikdörtgeni.y, cetvelDikdörtgeni.width, cetvelDikdörtgeni.height);

        // Cetvel etiketleri yazý fonunu siyah renkte 10 punto ebatlý yapalým...
        g.setFont (new Font ("SansSerif", Font.PLAIN, 10));
        g.setColor (Color.black);

        int son = 0;
        int ilk = 0;
        int kertikUzunluðu = 0;
        String smÝnçMetni = null;

        // Cetveldeki ilk ve son kertik konumlarýný hesaplayalým...
        if (yatýkDik == YATAY) {
            ilk = (cetvelDikdörtgeni.x / artýþ) * artýþ;
            son = (((cetvelDikdörtgeni.x + cetvelDikdörtgeni.width) / artýþ) + 1) * artýþ;
        }else {// DÝKEY
            ilk = (cetvelDikdörtgeni.y / artýþ) * artýþ;
            son = (((cetvelDikdörtgeni.y + cetvelDikdörtgeni.height) / artýþ) + 1) * artýþ;
        } // if-else kararý sonu...

        // Cetvel baþýna kertikli "0 sm" veya "0 inç" açýklamasý gelecek...
        if (ilk == 0) {
            smÝnçMetni = Integer.toString (0) + (metrikMi ? " sm" : " inç");
            kertikUzunluðu = 10;
            if (yatýkDik == YATAY) {
                g.drawLine (0, EBAT-1, 0, EBAT-kertikUzunluðu-1);
                g.drawString (smÝnçMetni, 2, 21);
            } else {// DÝKEY
                g.drawLine (EBAT-1, 0, EBAT-kertikUzunluðu-1, 0);
                g.drawString (smÝnçMetni, 9, 10);
            } // if-else kararý sonu...
            smÝnçMetni = null;
            ilk = artýþ;
        } // dýþ-if kararý sonu...

        // Cetvelin sm-inç kertik ve rakamlarýný konduralým...
        for (int i = ilk; i < son; i += artýþ) {
            if (i % santimdekiNoktalar == 0)  {
                kertikUzunluðu = 10;
                smÝnçMetni = Integer.toString (i / santimdekiNoktalar);
            } else {
                kertikUzunluðu = 7;
                smÝnçMetni = null;
            } // if-else kararý sonu...

            if (kertikUzunluðu != 0) {
                if (yatýkDik == YATAY) {
                    g.drawLine (i, EBAT-1, i, EBAT-kertikUzunluðu-1);
                    if (smÝnçMetni != null) g.drawString (smÝnçMetni, i-3, 21);
                }else {// DÝKEY
                    g.drawLine (EBAT-1, i, EBAT-kertikUzunluðu-1, i);
                    if (smÝnçMetni != null) g.drawString (smÝnçMetni, 9, i+3);
                } // if-else kararý sonu...
            } // dýþ-if kararý sonu...
        } // for döngüsü sonu...
    } // paintComponent(..) hazýr metodu sonu...

    public int artýþýAl() {return artýþ;}
    public void tercihiYüksekliðiKur (int boy) {setPreferredSize (new Dimension (EBAT, boy));}
    public void tercihiGeniþliðiKur (int en) {setPreferredSize (new Dimension (en, EBAT));}

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) accessibleContext = new EriþilebilirCetvel();
        return accessibleContext;
    } // getAccessibleContext() hazýr metodu sonu...

    protected class EriþilebilirCetvel extends AccessibleJComponent {
        public AccessibleRole getAccessibleRole() {return EriþilebilirCetvelRolü.CETVEL;}

        public AccessibleStateSet getAccessibleStateSet() {
            AccessibleStateSet statüler = super.getAccessibleStateSet();
            if (yatýkDik == DÝKEY) statüler.add (AccessibleState.VERTICAL);
            else statüler.add (AccessibleState.HORIZONTAL);

            if (metrikMi) statüler.add (EriþilebilirCetvelDurumu.SANTÝMLER);
            else statüler.add (EriþilebilirCetvelDurumu.ÝNÇLER);

            return statüler;
        } // getAccessibleStateSet() hazýr metodu sonu...
    } // EriþilebilirCetvel sýnýfý sonu...
} // J5e_1x1 sýnýfý sonu...

class EriþilebilirCetvelRolü extends AccessibleRole {
    public static final EriþilebilirCetvelRolü CETVEL = new EriþilebilirCetvelRolü ("cetvel");

    protected EriþilebilirCetvelRolü (String anahtar) {super (anahtar);}
    public String toDisplayString (String kaynakPaketAdý, Locale yerel) {return key;}
} // EriþilebilirCetvelRolü sýnýfý sonu...

class EriþilebilirCetvelDurumu extends AccessibleState {
    public static final EriþilebilirCetvelDurumu ÝNÇLER = new EriþilebilirCetvelDurumu ("inç");
    public static final EriþilebilirCetvelDurumu SANTÝMLER = new EriþilebilirCetvelDurumu ("santim");

    protected EriþilebilirCetvelDurumu (String anahtar) {super (anahtar);} // Kurucu...
    public String toDisplayString (String kaynakPaketAdý, Locale yerel) {return key;}
} // EriþilebilirCetvelDurumu sýnýfý sonu...