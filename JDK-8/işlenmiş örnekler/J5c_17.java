// J5c_17.java: FormatterFactoryDemo (BiçimleyiciÜretimiGösterimi) örneði.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.BorderFactory;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.text.NumberFormat;
import java.text.ParseException;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class J5c_17 extends JPanel implements PropertyChangeListener {
    private double tutar = 100000;
    private double oran = 0.075;
    private int yýl = 30;

    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel yýlEtiketi;
    private JLabel ödemeEtiketi;

    private static String tutarStr = "Borç Tutarý (TL): ";
    private static String oranStr = "Yýllýk faiz (%): ";
    private static String yýlStr = "Yýl Sayýsý: ";
    private static String ödemeStr = "Aylýk Geri Ödeme: ";

    private JFormattedTextField tutarGiriþi;
    private JFormattedTextField oranGiriþi;
    private JFormattedTextField yýlGiriþi;
    private JFormattedTextField ödemeGiriþi;

    private NumberFormat tutarGösterBiçimi;
    private NumberFormat tutarDeðiþtirBiçimi;
    private NumberFormat oranGösterBiçimi;
    private NumberFormat oranDeðiþtirBiçimi;
    private NumberFormat ödemeGösterBiçimi;

    public J5c_17() {// Kurucu..
        super (new BorderLayout());
        biçimleriKur();
        double ödeme = ödemeyiHesapla (tutar, oran, yýl);

        tutarEtiketi = new JLabel (tutarStr);
        oranEtiketi = new JLabel (oranStr);
        yýlEtiketi = new JLabel (yýlStr);
        ödemeEtiketi = new JLabel (ödemeStr);

        tutarGiriþi = new JFormattedTextField (
                            new DefaultFormatterFactory (
                            new NumberFormatter (tutarGösterBiçimi),
                            new NumberFormatter(tutarGösterBiçimi),
                            new NumberFormatter (tutarDeðiþtirBiçimi)));
        tutarGiriþi.setValue (new Double (tutar));
        tutarGiriþi.setColumns (10);
        tutarGiriþi.addPropertyChangeListener ("value", this);

        NumberFormatter oranDeðiþtirBiçimleyici = new NumberFormatter (oranDeðiþtirBiçimi) {
            public String valueToString (Object nesne) throws ParseException {
                Number sayý = (Number)nesne;
                if (sayý != null) {double duble = sayý.doubleValue() * 100.0;
                    sayý = new Double (duble);
                } // if kararý sonu...
                return super.valueToString (sayý);
            } // valueToString(..) metodu sonu...
            public Object stringToValue (String dizge) throws ParseException {
                Number sayý = (Number)super.stringToValue (dizge);
                if (sayý != null) {double duble = sayý.doubleValue() / 100.0;
                    sayý = new Double (duble);
                } // if kararý sonu...
                return sayý;
            } // stringToValue(..) metodu sonu...
        }; // Num.. ifadesi sonu..

        oranGiriþi = new JFormattedTextField (
                            new DefaultFormatterFactory (
                            new NumberFormatter (oranGösterBiçimi),
                            new NumberFormatter (oranGösterBiçimi),
                            oranDeðiþtirBiçimleyici));
        oranGiriþi.setValue (new Double (oran));
        oranGiriþi.setColumns (10);
        oranGiriþi.addPropertyChangeListener ("value", this);

        yýlGiriþi = new JFormattedTextField();
        yýlGiriþi.setValue (new Integer (yýl));
        yýlGiriþi.setColumns (10);
        yýlGiriþi.addPropertyChangeListener ("value", this);

        ödemeGiriþi = new JFormattedTextField (ödemeGösterBiçimi);
        ödemeGiriþi.setValue (new Double (ödeme));
        ödemeGiriþi.setColumns (10);
        ödemeGiriþi.setEditable (false);
        ödemeGiriþi.setForeground (Color.red);

        tutarEtiketi.setLabelFor (tutarGiriþi);
        oranEtiketi.setLabelFor (oranGiriþi);
        yýlEtiketi.setLabelFor (yýlGiriþi);
        ödemeEtiketi.setLabelFor (ödemeGiriþi);

        JPanel etiketPanosu = new JPanel (new GridLayout (0,1));
        etiketPanosu.add (tutarEtiketi);
        etiketPanosu.add (oranEtiketi);
        etiketPanosu.add (yýlEtiketi);
        etiketPanosu.add (ödemeEtiketi);
        etiketPanosu.setBackground (new Color (5,200,150));

        JPanel giriþPanosu = new JPanel (new GridLayout (0,1));
        giriþPanosu.add (tutarGiriþi);
        giriþPanosu.add (oranGiriþi);
        giriþPanosu.add (yýlGiriþi);
        giriþPanosu.add (ödemeGiriþi);

        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        add (etiketPanosu, BorderLayout.CENTER);
        add (giriþPanosu, BorderLayout.LINE_END);
        setBackground (new Color (180,50,5));
    } // J5c_17() kurucusu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarGiriþi) tutar = ((Number)tutarGiriþi.getValue()).doubleValue();
        else if (kaynak == oranGiriþi) oran = ((Number)oranGiriþi.getValue()).doubleValue();
        else if (kaynak == yýlGiriþi) yýl = ((Number)yýlGiriþi.getValue()).intValue();

        double ödeme = ödemeyiHesapla (tutar, oran, yýl);
        ödemeGiriþi.setValue (new Double (ödeme));
    } // propertyChange(..) metodu sonu...

    double ödemeyiHesapla (double borç, double oran, int yýl) {
        double payda, cevap;

        yýl *= 12;
        if (oran > 0.001) {
            oran /= 12.0;
            payda = (1 - Math.pow ((1 + oran), -yýl)) / oran;
        }else payda = yýl; // ~= 0

        cevap = -borç / payda;
        return cevap;
    } // ödemeyiHesapla(..) metodu sonu...

    private void biçimleriKur() {
        tutarGösterBiçimi = NumberFormat.getCurrencyInstance();
        tutarGösterBiçimi.setMinimumFractionDigits (0);
        tutarDeðiþtirBiçimi = NumberFormat.getNumberInstance();

        oranGösterBiçimi = NumberFormat.getPercentInstance();
        oranGösterBiçimi.setMinimumFractionDigits (2);
        oranDeðiþtirBiçimi = NumberFormat.getNumberInstance();
        oranDeðiþtirBiçimi.setMinimumFractionDigits (2);

        ödemeGösterBiçimi = NumberFormat.getCurrencyInstance();
    } // biçimleriKur() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Biçimleyici Üretimi Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_17());
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_17 sýnýfý sonu...