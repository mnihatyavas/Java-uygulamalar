// J8d_5.java: InternationalizedMortgageCalculator (UluslararasýlaþtýrýlmýþÝpotekHesaplayýcýsý) örneði.

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
    private static JFrame çerçeve;
    protected Action
            ingilizceABDAksiyonu, ingilizceBKAksiyonu,
            fransýzcaFransaAksiyonu, fransýzcaKanadaAksiyonu,
            arapçaSuudiAksiyonu,
            türkçeTürkiyeAksiyonu;
    private static ResourceBundle yerelEtiketler;

    // Hesaplayýcýdaki ilk deðerler...
    private double tutar = 100000;
    private double oran = 7.5; // 7.5%
    private int yýl = 30;

    // Deðerleri tanýmlayan etiketler...
    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel yýlEtiketi;
    private JLabel ödemeEtiketi;

    // Deðerlerin alanlarý...
    private JFormattedTextField tutarAlaný;
    private JFormattedTextField oranAlaný;
    private JFormattedTextField yýlAlaný;
    private JFormattedTextField ödemeAlaný;

    // Sayýlarý biçimleme...
    private static Currency paraTipi;
    private NumberFormat tutarBiçimi;
    private NumberFormat yüzdeBiçimi;
    private NumberFormat ödemeBiçimi;

    public J8d_5 (Locale aktüelYerel) {// Kurucu...
        paraTipi = Currency.getInstance (aktüelYerel);

        ingilizceABDAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "English, United States locale, en-US",
                "This is the English locale",
                new Integer (KeyEvent.VK_U),
                new Locale.Builder().setLanguage ("en").setRegion ("US").build());

        ingilizceBKAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "English, United Kingdom locale, en-UK",
                "This is the English locale",
                new Integer (KeyEvent.VK_G),
                new Locale.Builder().setLanguage ("en").setRegion ("GB").build());

        fransýzcaFransaAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "French, France locale, fr-FR",
                "This is the French locale",
                new Integer (KeyEvent.VK_F),
                new Locale.Builder().setLanguage ("fr").setRegion ("FR").build());

        fransýzcaKanadaAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "French, Canada locale, fr-CA",
                "This is the French locale",
                new Integer (KeyEvent.VK_C),
                new Locale.Builder().setLanguage ("fr").setRegion ("CA").build());

        arapçaSuudiAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "Arabic, Saudi Arabia locale, ar-SA",
                "This is the Arabic locale",
                new Integer (KeyEvent.VK_S),
                new Locale.Builder().setLanguage ("ar").setRegion ("SA").build());

        türkçeTürkiyeAksiyonu = new YerelAksiyonuDeðiþtir (// Sýnýf kurucusunu çaðýrýr...
                "Türkçe, Türkiye yereli, tr-TR",
                "Bu Türkiye yerelidir",
                new Integer (KeyEvent.VK_T),
                new Locale.Builder().setLanguage ("tr").setRegion ("TR").build());

        biçimleriKur (aktüelYerel);

        double geriÖdeme = ödemeyiHesapla (tutar, oran, yýl);

        // Etiketleri yaratalým...
        yerelEtiketler = ResourceBundle.getBundle ("J8d_5x", aktüelYerel);

        tutarEtiketi = new JLabel (MessageFormat.format (
                yerelEtiketler.getString ("AMOUNT_STRING"),
                paraTipi.getDisplayName (aktüelYerel),
                paraTipi.getSymbol (aktüelYerel)));
        oranEtiketi = new JLabel (yerelEtiketler.getString ("RATE_STRING"));
        yýlEtiketi = new JLabel (yerelEtiketler.getString ("NUM_PERIODS_STRING"));
        ödemeEtiketi = new JLabel (yerelEtiketler.getString ("PAYMENT_STRING"));

        // Veri alanlarýný kurup dinleyiciye duyarlayalým (geri ödeme hariç)...
        tutarAlaný = new JFormattedTextField (tutarBiçimi);
        tutarAlaný.setValue (new Double (tutar));
        tutarAlaný.setColumns (10);
        tutarAlaný.addPropertyChangeListener ("value", this);

        oranAlaný = new JFormattedTextField (yüzdeBiçimi);
        oranAlaný.setValue (new Double (oran));
        oranAlaný.setColumns (10);
        oranAlaný.addPropertyChangeListener ("value", this);

        yýlAlaný = new JFormattedTextField();
        yýlAlaný.setValue (new Integer (yýl));
        yýlAlaný.setColumns (10);
        yýlAlaný.addPropertyChangeListener ("value", this);

        ödemeAlaný = new JFormattedTextField (ödemeBiçimi);
        ödemeAlaný.setValue (new Double (geriÖdeme));
        ödemeAlaný.setColumns (10);
        ödemeAlaný.setEditable (false); // Diðer alanlar gibi deðiþtirilemez...
        ödemeAlaný.setForeground (Color.red);

        // Etiketleri ilgili verilerle iliþkilendir...
        tutarEtiketi.setLabelFor (tutarAlaný);
        oranEtiketi.setLabelFor (oranAlaný);
        yýlEtiketi.setLabelFor (yýlAlaný);
        ödemeEtiketi.setLabelFor (ödemeAlaný);

        // Etiket ve verileri GridLayout/IzgaraSerimleme'ye ekleyelim...
        JPanel hesaplayýcýPaneli = new JPanel (new GridLayout (4, 2));
        hesaplayýcýPaneli.add (tutarEtiketi);
        hesaplayýcýPaneli.add (tutarAlaný);
        hesaplayýcýPaneli.add (oranEtiketi);
        hesaplayýcýPaneli.add (oranAlaný);
        hesaplayýcýPaneli.add (yýlEtiketi);
        hesaplayýcýPaneli.add (yýlAlaný);
        hesaplayýcýPaneli.add (ödemeEtiketi);
        hesaplayýcýPaneli.add (ödemeAlaný);

        setBorder (BorderFactory.createEmptyBorder (5, 5, 5, 5));
        add (hesaplayýcýPaneli, BorderLayout.CENTER);
    } // J8d_5(..) kurucusu sonu...

    public class YerelAksiyonuDeðiþtir extends AbstractAction {
        private String aksiyonÝzahý;
        private Locale aktüelYerel;

        public YerelAksiyonuDeðiþtir (
                String metinArgümaný,
                String izahArgümaný,
                Integer altTuþArgümaný,
                Locale yerelArgümaný) {
            super (metinArgümaný);
            aksiyonÝzahý = izahArgümaný;
            aktüelYerel = yerelArgümaný;
            putValue (SHORT_DESCRIPTION, izahArgümaný);
            putValue (MNEMONIC_KEY, altTuþArgümaný);
        } // YerelAksiyonuDeðiþtir(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {yaratVeGösterGUI (aktüelYerel);}
    } // YerelAksiyonuDeðiþtir sýnýfý sonu...

    private void biçimleriKur (Locale aktüelYerel) {
        tutarBiçimi = NumberFormat.getNumberInstance();

        yüzdeBiçimi = NumberFormat.getNumberInstance();
        yüzdeBiçimi.setMinimumFractionDigits (2);

        ödemeBiçimi = NumberFormat.getCurrencyInstance (aktüelYerel);
        ödemeBiçimi.setCurrency (paraTipi);
    } // biçimleriKur(..) metodu sonu...

    double ödemeyiHesapla (double borç, double faiz, int yýl) {
        double payda;

        yýl *= 12; // yýlý ay adedine dönüþtür...
        if (faiz > 0.01) {
            faiz = faiz / 100.0 / 12.0; // aylýk ondalýk faiz bulunur...
            payda = (1 - Math.pow ((1 + faiz), (0.0 - yýl))) / faiz;
        }else // faiz ~= 0
            payda = yýl;

        return (-1 * borç) / payda;
    } // ödemeyiHesapla(..) metodu sonu...

    // Deðiþtirilebilir deðer alanlarýndaki deðiþikliðe duyarlýdýr...
    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarAlaný) tutar = ((Number)tutarAlaný.getValue()).doubleValue();
        else if (kaynak == oranAlaný) oran = ((Number)oranAlaný.getValue()).doubleValue();
        else if (kaynak == yýlAlaný) yýl = ((Number)yýlAlaný.getValue()).intValue();

        double ödeme = ödemeyiHesapla (tutar, oran, yýl);
        ödemeAlaný.setValue (new Double (ödeme));
    } // propertyChange(..) hazýr metodu sonu...

    public JMenuBar menüÇubuðunuYarat() {
        JMenuItem menüBirimi = null;
        JMenuBar menüÇubuðu = new JMenuBar();
        JMenu anaMenü = new JMenu (yerelEtiketler.getString ("LOCALE"));

        Action[] aksiyonlar = {
                ingilizceABDAksiyonu, ingilizceBKAksiyonu,
                fransýzcaFransaAksiyonu, fransýzcaKanadaAksiyonu,
                arapçaSuudiAksiyonu,
                türkçeTürkiyeAksiyonu
        }; // aksiyonlar dizisi sonu...

        for (int i = 0; i < aksiyonlar.length; i++) {
            menüBirimi = new JMenuItem (aksiyonlar[i]);
            menüBirimi.setIcon (null); // ikonsuz...
            anaMenü.add (menüBirimi);
        } // for döngüsü sonu...

        menüÇubuðu.add (anaMenü);
        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    private static void yaratVeGösterGUI (Locale þimdikiYerel) {
        J8d_5 ipotek = new J8d_5 (þimdikiYerel); // Kurucuyu çaðýrýr...

        if (çerçeve == null) çerçeve = new JFrame (yerelEtiketler.getString ("WINDOW_TITLE"));
        else {çerçeve.getContentPane().removeAll();
            çerçeve.setTitle (yerelEtiketler.getString ("WINDOW_TITLE"));
        } // else kararý sonu...

        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (ipotek);
        çerçeve.setJMenuBar (ipotek.menüÇubuðunuYarat());
        çerçeve.applyComponentOrientation (ComponentOrientation.getOrientation (þimdikiYerel));
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    Locale aktüelYerel = new Locale.Builder().setLanguage ("tr").setRegion ("TR").build();
                    UIManager.put ("swing.boldMetal", Boolean.FALSE);
                    yaratVeGösterGUI (aktüelYerel);
                } // run() hazýr ip metodu sonu...
            } // new.. sýnýf bloðu sonu...
        ); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J8d_5 sýnýfý sonu...