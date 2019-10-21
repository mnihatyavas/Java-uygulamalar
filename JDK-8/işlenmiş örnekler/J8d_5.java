// J8d_5.java: InternationalizedMortgageCalculator (Uluslararas�la�t�r�lm���potekHesaplay�c�s�) �rne�i.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.util.Locale;
import java.util.Currency;
import java.util.ResourceBundle;

import java.text.MessageFormat;
import java.text.NumberFormat;

/* Gereken dosyalar:
 *     J8d_5x.properties
 *     J8d_5x_ar.properties
 *     J8d_5x_fr.properties
 *     J8d_5x_tr.properties
 */
public class J8d_5 extends JPanel implements PropertyChangeListener {
    private static JFrame �er�eve;
    protected Action
            ingilizceABDAksiyonu, ingilizceBKAksiyonu,
            frans�zcaFransaAksiyonu, frans�zcaKanadaAksiyonu,
            arap�aSuudiAksiyonu,
            t�rk�eT�rkiyeAksiyonu;
    private static ResourceBundle yerelEtiketler;

    // Hesaplay�c�daki ilk de�erler...
    private double tutar = 100000;
    private double oran = 7.5; // 7.5%
    private int y�l = 30;

    // De�erleri tan�mlayan etiketler...
    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel y�lEtiketi;
    private JLabel �demeEtiketi;

    // De�erlerin alanlar�...
    private JFormattedTextField tutarAlan�;
    private JFormattedTextField oranAlan�;
    private JFormattedTextField y�lAlan�;
    private JFormattedTextField �demeAlan�;

    // Say�lar� bi�imleme...
    private static Currency paraTipi;
    private NumberFormat tutarBi�imi;
    private NumberFormat y�zdeBi�imi;
    private NumberFormat �demeBi�imi;

    public J8d_5 (Locale akt�elYerel) {// Kurucu...
        paraTipi = Currency.getInstance (akt�elYerel);

        ingilizceABDAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "English, United States locale, en-US",
                "This is the English locale",
                new Integer (KeyEvent.VK_U),
                new Locale.Builder().setLanguage ("en").setRegion ("US").build());

        ingilizceBKAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "English, United Kingdom locale, en-UK",
                "This is the English locale",
                new Integer (KeyEvent.VK_G),
                new Locale.Builder().setLanguage ("en").setRegion ("GB").build());

        frans�zcaFransaAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "French, France locale, fr-FR",
                "This is the French locale",
                new Integer (KeyEvent.VK_F),
                new Locale.Builder().setLanguage ("fr").setRegion ("FR").build());

        frans�zcaKanadaAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "French, Canada locale, fr-CA",
                "This is the French locale",
                new Integer (KeyEvent.VK_C),
                new Locale.Builder().setLanguage ("fr").setRegion ("CA").build());

        arap�aSuudiAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "Arabic, Saudi Arabia locale, ar-SA",
                "This is the Arabic locale",
                new Integer (KeyEvent.VK_S),
                new Locale.Builder().setLanguage ("ar").setRegion ("SA").build());

        t�rk�eT�rkiyeAksiyonu = new YerelAksiyonuDe�i�tir (// S�n�f kurucusunu �a��r�r...
                "T�rk�e, T�rkiye yereli, tr-TR",
                "Bu T�rkiye yerelidir",
                new Integer (KeyEvent.VK_T),
                new Locale.Builder().setLanguage ("tr").setRegion ("TR").build());

        bi�imleriKur (akt�elYerel);

        double geri�deme = �demeyiHesapla (tutar, oran, y�l);

        // Etiketleri yaratal�m...
        yerelEtiketler = ResourceBundle.getBundle ("J8d_5x", akt�elYerel);

        tutarEtiketi = new JLabel (MessageFormat.format (
                yerelEtiketler.getString ("AMOUNT_STRING"),
                paraTipi.getDisplayName (akt�elYerel),
                paraTipi.getSymbol (akt�elYerel)));
        oranEtiketi = new JLabel (yerelEtiketler.getString ("RATE_STRING"));
        y�lEtiketi = new JLabel (yerelEtiketler.getString ("NUM_PERIODS_STRING"));
        �demeEtiketi = new JLabel (yerelEtiketler.getString ("PAYMENT_STRING"));

        // Veri alanlar�n� kurup dinleyiciye duyarlayal�m (geri �deme hari�)...
        tutarAlan� = new JFormattedTextField (tutarBi�imi);
        tutarAlan�.setValue (new Double (tutar));
        tutarAlan�.setColumns (10);
        tutarAlan�.addPropertyChangeListener ("value", this);

        oranAlan� = new JFormattedTextField (y�zdeBi�imi);
        oranAlan�.setValue (new Double (oran));
        oranAlan�.setColumns (10);
        oranAlan�.addPropertyChangeListener ("value", this);

        y�lAlan� = new JFormattedTextField();
        y�lAlan�.setValue (new Integer (y�l));
        y�lAlan�.setColumns (10);
        y�lAlan�.addPropertyChangeListener ("value", this);

        �demeAlan� = new JFormattedTextField (�demeBi�imi);
        �demeAlan�.setValue (new Double (geri�deme));
        �demeAlan�.setColumns (10);
        �demeAlan�.setEditable (false); // Di�er alanlar gibi de�i�tirilemez...
        �demeAlan�.setForeground (Color.red);

        // Etiketleri ilgili verilerle ili�kilendir...
        tutarEtiketi.setLabelFor (tutarAlan�);
        oranEtiketi.setLabelFor (oranAlan�);
        y�lEtiketi.setLabelFor (y�lAlan�);
        �demeEtiketi.setLabelFor (�demeAlan�);

        // Etiket ve verileri GridLayout/IzgaraSerimleme'ye ekleyelim...
        JPanel hesaplay�c�Paneli = new JPanel (new GridLayout (4, 2));
        hesaplay�c�Paneli.add (tutarEtiketi);
        hesaplay�c�Paneli.add (tutarAlan�);
        hesaplay�c�Paneli.add (oranEtiketi);
        hesaplay�c�Paneli.add (oranAlan�);
        hesaplay�c�Paneli.add (y�lEtiketi);
        hesaplay�c�Paneli.add (y�lAlan�);
        hesaplay�c�Paneli.add (�demeEtiketi);
        hesaplay�c�Paneli.add (�demeAlan�);

        setBorder (BorderFactory.createEmptyBorder (5, 5, 5, 5));
        add (hesaplay�c�Paneli, BorderLayout.CENTER);
    } // J8d_5(..) kurucusu sonu...

    public class YerelAksiyonuDe�i�tir extends AbstractAction {
        private String aksiyon�zah�;
        private Locale akt�elYerel;

        public YerelAksiyonuDe�i�tir (
                String metinArg�man�,
                String izahArg�man�,
                Integer altTu�Arg�man�,
                Locale yerelArg�man�) {
            super (metinArg�man�);
            aksiyon�zah� = izahArg�man�;
            akt�elYerel = yerelArg�man�;
            putValue (SHORT_DESCRIPTION, izahArg�man�);
            putValue (MNEMONIC_KEY, altTu�Arg�man�);
        } // YerelAksiyonuDe�i�tir(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {yaratVeG�sterGUI (akt�elYerel);}
    } // YerelAksiyonuDe�i�tir s�n�f� sonu...

    private void bi�imleriKur (Locale akt�elYerel) {
        tutarBi�imi = NumberFormat.getNumberInstance();

        y�zdeBi�imi = NumberFormat.getNumberInstance();
        y�zdeBi�imi.setMinimumFractionDigits (2);

        �demeBi�imi = NumberFormat.getCurrencyInstance (akt�elYerel);
        �demeBi�imi.setCurrency (paraTipi);
    } // bi�imleriKur(..) metodu sonu...

    double �demeyiHesapla (double bor�, double faiz, int y�l) {
        double payda;

        y�l *= 12; // y�l� ay adedine d�n��t�r...
        if (faiz > 0.01) {
            faiz = faiz / 100.0 / 12.0; // ayl�k ondal�k faiz bulunur...
            payda = (1 - Math.pow ((1 + faiz), (0.0 - y�l))) / faiz;
        }else // faiz ~= 0
            payda = y�l;

        return (-1 * bor�) / payda;
    } // �demeyiHesapla(..) metodu sonu...

    // De�i�tirilebilir de�er alanlar�ndaki de�i�ikli�e duyarl�d�r...
    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarAlan�) tutar = ((Number)tutarAlan�.getValue()).doubleValue();
        else if (kaynak == oranAlan�) oran = ((Number)oranAlan�.getValue()).doubleValue();
        else if (kaynak == y�lAlan�) y�l = ((Number)y�lAlan�.getValue()).intValue();

        double �deme = �demeyiHesapla (tutar, oran, y�l);
        �demeAlan�.setValue (new Double (�deme));
    } // propertyChange(..) haz�r metodu sonu...

    public JMenuBar men��ubu�unuYarat() {
        JMenuItem men�Birimi = null;
        JMenuBar men��ubu�u = new JMenuBar();
        JMenu anaMen� = new JMenu (yerelEtiketler.getString ("LOCALE"));

        Action[] aksiyonlar = {
                ingilizceABDAksiyonu, ingilizceBKAksiyonu,
                frans�zcaFransaAksiyonu, frans�zcaKanadaAksiyonu,
                arap�aSuudiAksiyonu,
                t�rk�eT�rkiyeAksiyonu
        }; // aksiyonlar dizisi sonu...

        for (int i = 0; i < aksiyonlar.length; i++) {
            men�Birimi = new JMenuItem (aksiyonlar[i]);
            men�Birimi.setIcon (null); // ikonsuz...
            anaMen�.add (men�Birimi);
        } // for d�ng�s� sonu...

        men��ubu�u.add (anaMen�);
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    private static void yaratVeG�sterGUI (Locale �imdikiYerel) {
        J8d_5 ipotek = new J8d_5 (�imdikiYerel); // Kurucuyu �a��r�r...

        if (�er�eve == null) �er�eve = new JFrame (yerelEtiketler.getString ("WINDOW_TITLE"));
        else {�er�eve.getContentPane().removeAll();
            �er�eve.setTitle (yerelEtiketler.getString ("WINDOW_TITLE"));
        } // else karar� sonu...

        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (ipotek);
        �er�eve.setJMenuBar (ipotek.men��ubu�unuYarat());
        �er�eve.applyComponentOrientation (ComponentOrientation.getOrientation (�imdikiYerel));
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    Locale akt�elYerel = new Locale.Builder().setLanguage ("tr").setRegion ("TR").build();
                    UIManager.put ("swing.boldMetal", Boolean.FALSE);
                    yaratVeG�sterGUI (akt�elYerel);
                } // run() haz�r ip metodu sonu...
            } // new.. s�n�f blo�u sonu...
        ); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J8d_5 s�n�f� sonu...