// J5c_17.java: FormatterFactoryDemo (Bi�imleyici�retimiG�sterimi) �rne�i.

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
    private int y�l = 30;

    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel y�lEtiketi;
    private JLabel �demeEtiketi;

    private static String tutarStr = "Bor� Tutar� (TL): ";
    private static String oranStr = "Y�ll�k faiz (%): ";
    private static String y�lStr = "Y�l Say�s�: ";
    private static String �demeStr = "Ayl�k Geri �deme: ";

    private JFormattedTextField tutarGiri�i;
    private JFormattedTextField oranGiri�i;
    private JFormattedTextField y�lGiri�i;
    private JFormattedTextField �demeGiri�i;

    private NumberFormat tutarG�sterBi�imi;
    private NumberFormat tutarDe�i�tirBi�imi;
    private NumberFormat oranG�sterBi�imi;
    private NumberFormat oranDe�i�tirBi�imi;
    private NumberFormat �demeG�sterBi�imi;

    public J5c_17() {// Kurucu..
        super (new BorderLayout());
        bi�imleriKur();
        double �deme = �demeyiHesapla (tutar, oran, y�l);

        tutarEtiketi = new JLabel (tutarStr);
        oranEtiketi = new JLabel (oranStr);
        y�lEtiketi = new JLabel (y�lStr);
        �demeEtiketi = new JLabel (�demeStr);

        tutarGiri�i = new JFormattedTextField (
                            new DefaultFormatterFactory (
                            new NumberFormatter (tutarG�sterBi�imi),
                            new NumberFormatter(tutarG�sterBi�imi),
                            new NumberFormatter (tutarDe�i�tirBi�imi)));
        tutarGiri�i.setValue (new Double (tutar));
        tutarGiri�i.setColumns (10);
        tutarGiri�i.addPropertyChangeListener ("value", this);

        NumberFormatter oranDe�i�tirBi�imleyici = new NumberFormatter (oranDe�i�tirBi�imi) {
            public String valueToString (Object nesne) throws ParseException {
                Number say� = (Number)nesne;
                if (say� != null) {double duble = say�.doubleValue() * 100.0;
                    say� = new Double (duble);
                } // if karar� sonu...
                return super.valueToString (say�);
            } // valueToString(..) metodu sonu...
            public Object stringToValue (String dizge) throws ParseException {
                Number say� = (Number)super.stringToValue (dizge);
                if (say� != null) {double duble = say�.doubleValue() / 100.0;
                    say� = new Double (duble);
                } // if karar� sonu...
                return say�;
            } // stringToValue(..) metodu sonu...
        }; // Num.. ifadesi sonu..

        oranGiri�i = new JFormattedTextField (
                            new DefaultFormatterFactory (
                            new NumberFormatter (oranG�sterBi�imi),
                            new NumberFormatter (oranG�sterBi�imi),
                            oranDe�i�tirBi�imleyici));
        oranGiri�i.setValue (new Double (oran));
        oranGiri�i.setColumns (10);
        oranGiri�i.addPropertyChangeListener ("value", this);

        y�lGiri�i = new JFormattedTextField();
        y�lGiri�i.setValue (new Integer (y�l));
        y�lGiri�i.setColumns (10);
        y�lGiri�i.addPropertyChangeListener ("value", this);

        �demeGiri�i = new JFormattedTextField (�demeG�sterBi�imi);
        �demeGiri�i.setValue (new Double (�deme));
        �demeGiri�i.setColumns (10);
        �demeGiri�i.setEditable (false);
        �demeGiri�i.setForeground (Color.red);

        tutarEtiketi.setLabelFor (tutarGiri�i);
        oranEtiketi.setLabelFor (oranGiri�i);
        y�lEtiketi.setLabelFor (y�lGiri�i);
        �demeEtiketi.setLabelFor (�demeGiri�i);

        JPanel etiketPanosu = new JPanel (new GridLayout (0,1));
        etiketPanosu.add (tutarEtiketi);
        etiketPanosu.add (oranEtiketi);
        etiketPanosu.add (y�lEtiketi);
        etiketPanosu.add (�demeEtiketi);
        etiketPanosu.setBackground (new Color (5,200,150));

        JPanel giri�Panosu = new JPanel (new GridLayout (0,1));
        giri�Panosu.add (tutarGiri�i);
        giri�Panosu.add (oranGiri�i);
        giri�Panosu.add (y�lGiri�i);
        giri�Panosu.add (�demeGiri�i);

        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        add (etiketPanosu, BorderLayout.CENTER);
        add (giri�Panosu, BorderLayout.LINE_END);
        setBackground (new Color (180,50,5));
    } // J5c_17() kurucusu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarGiri�i) tutar = ((Number)tutarGiri�i.getValue()).doubleValue();
        else if (kaynak == oranGiri�i) oran = ((Number)oranGiri�i.getValue()).doubleValue();
        else if (kaynak == y�lGiri�i) y�l = ((Number)y�lGiri�i.getValue()).intValue();

        double �deme = �demeyiHesapla (tutar, oran, y�l);
        �demeGiri�i.setValue (new Double (�deme));
    } // propertyChange(..) metodu sonu...

    double �demeyiHesapla (double bor�, double oran, int y�l) {
        double payda, cevap;

        y�l *= 12;
        if (oran > 0.001) {
            oran /= 12.0;
            payda = (1 - Math.pow ((1 + oran), -y�l)) / oran;
        }else payda = y�l; // ~= 0

        cevap = -bor� / payda;
        return cevap;
    } // �demeyiHesapla(..) metodu sonu...

    private void bi�imleriKur() {
        tutarG�sterBi�imi = NumberFormat.getCurrencyInstance();
        tutarG�sterBi�imi.setMinimumFractionDigits (0);
        tutarDe�i�tirBi�imi = NumberFormat.getNumberInstance();

        oranG�sterBi�imi = NumberFormat.getPercentInstance();
        oranG�sterBi�imi.setMinimumFractionDigits (2);
        oranDe�i�tirBi�imi = NumberFormat.getNumberInstance();
        oranDe�i�tirBi�imi.setMinimumFractionDigits (2);

        �demeG�sterBi�imi = NumberFormat.getCurrencyInstance();
    } // bi�imleriKur() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Bi�imleyici �retimi G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_17());
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_17 s�n�f� sonu...