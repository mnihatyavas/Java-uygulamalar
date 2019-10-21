// J5c_16.java: FormattedTextFieldDemo (BiçimliMetinSatýrýGösterimi) örneði.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.BorderFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.text.NumberFormat;

public class J5c_16 extends JPanel implements PropertyChangeListener {
    // Metin satýrlarý için ilk deðerler...
    private double tutar = 100000; // Alýnan borç ana parasý...
    private double oran = 7.5;  // Borcun yýllýk faiz oraný...
    private int yýlSayýsý = 30; // Borçun ödeneceði yýl sayýsý...

    // Metin satýrlarýný tanýmlayacak etiketler...
    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel yýlSayýsýEtiketi;
    private JLabel aylýkGeriÖdemeEtiketi;

    // Etiketler için içerik dizgeleri...
    private static String tutarStr = "Borç Anaparasý (TL): ";
    private static String oranStr = "Yýllýk Faiz (%): ";
    private static String yýlSayýsýStr = "Yýl Sayýsý: ";
    private static String aylýkGeriÖdemeStr = "Aylýk Geri Ödeme: ";

    // Veri giriþ biçimli metin satýlarý...
    private JFormattedTextField tutarMetinSatýrý;
    private JFormattedTextField oranMetinSatýrý;
    private JFormattedTextField yýlSayýsýMetinSatýrý;
    private JFormattedTextField aylýkGeriÖdemeMetinSatýrý;

    // Metin satýrýna girilen Str deðeri sayýsala çevirip biçimleyecek nesneler...
    private NumberFormat tutarBiçimleyici;
    private NumberFormat oranBiçimleyici;
    private NumberFormat aylýkGeriÖdemeBiçimleyici;

    public J5c_16() {// Kurucu...
        super (new BorderLayout());
        biçimleyicileriKur();
        double aylýkGeriÖdeme = aylýkGeriÖdemeyiHesapla (tutar, oran, yýlSayýsý);

        // Metin satýrý açýklama etiketlerini yaratalým...
        tutarEtiketi = new JLabel (tutarStr);
        oranEtiketi = new JLabel (oranStr);
        yýlSayýsýEtiketi = new JLabel (yýlSayýsýStr);
        aylýkGeriÖdemeEtiketi = new JLabel (aylýkGeriÖdemeStr);

        // Metin satýrý biçimli içeriklerini yaratýp deðerleri atayalým...
        tutarMetinSatýrý = new JFormattedTextField (tutarBiçimleyici);
        tutarMetinSatýrý.setValue (new Double (tutar));
        tutarMetinSatýrý.setColumns (10);
        tutarMetinSatýrý.addPropertyChangeListener ("value", this);

        oranMetinSatýrý = new JFormattedTextField (oranBiçimleyici);
        oranMetinSatýrý.setValue (new Double (oran));
        oranMetinSatýrý.setColumns (10);
        oranMetinSatýrý.addPropertyChangeListener ("value", this);

        yýlSayýsýMetinSatýrý = new JFormattedTextField();
        yýlSayýsýMetinSatýrý.setValue (new Integer (yýlSayýsý));
        yýlSayýsýMetinSatýrý.setColumns (10);
        yýlSayýsýMetinSatýrý.addPropertyChangeListener ("value", this);

        aylýkGeriÖdemeMetinSatýrý = new JFormattedTextField (aylýkGeriÖdemeBiçimleyici);
        aylýkGeriÖdemeMetinSatýrý.setValue (new Double (aylýkGeriÖdeme));
        aylýkGeriÖdemeMetinSatýrý.setColumns (10);
        aylýkGeriÖdemeMetinSatýrý.setEditable (false);
        aylýkGeriÖdemeMetinSatýrý.setForeground (Color.red);

        // Etiketlere ait olduklarý metin satýrlarýný kuralým/ekleyelim...
        tutarEtiketi.setLabelFor (tutarMetinSatýrý);
        oranEtiketi.setLabelFor (oranMetinSatýrý);
        yýlSayýsýEtiketi.setLabelFor (yýlSayýsýMetinSatýrý);
        aylýkGeriÖdemeEtiketi.setLabelFor (aylýkGeriÖdemeMetinSatýrý);

        // Etiketleri bir panele yerleþtirelim...
        JPanel etiketPanosu = new JPanel (new GridLayout (0,1));
        etiketPanosu.add (tutarEtiketi);
        etiketPanosu.add (oranEtiketi);
        etiketPanosu.add (yýlSayýsýEtiketi);
        etiketPanosu.add (aylýkGeriÖdemeEtiketi);
        etiketPanosu.setBackground (new Color(10,220,10));

        // Metin satýrlarýný da ayrý bir panele yerleþtirelim...
        JPanel metinSatýrýPanosu = new JPanel (new GridLayout (0,1));
        metinSatýrýPanosu.add (tutarMetinSatýrý);
        metinSatýrýPanosu.add (oranMetinSatýrý);
        metinSatýrýPanosu.add (yýlSayýsýMetinSatýrý);
        metinSatýrýPanosu.add (aylýkGeriÖdemeMetinSatýrý);

        // Bu 2 paneli "this" super panele (etiket solda, metin saðda) ekleyelim...
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        add (etiketPanosu, BorderLayout.CENTER);
        add (metinSatýrýPanosu, BorderLayout.LINE_END);
        setBackground (new Color(200,100,10));
    } // J5c_16() kurucusu sonu...

    // Metin satýrý deðer ("value") deðiþimine duyarlýdýr...
    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarMetinSatýrý) tutar = ((Number)tutarMetinSatýrý.getValue()).doubleValue();
        else if (kaynak == oranMetinSatýrý) oran = ((Number)oranMetinSatýrý.getValue()).doubleValue();
        else if (kaynak == yýlSayýsýMetinSatýrý) yýlSayýsý = ((Number)yýlSayýsýMetinSatýrý.getValue()).intValue();

        double aylýkGeriÖdeme = aylýkGeriÖdemeyiHesapla (tutar, oran, yýlSayýsý);
        aylýkGeriÖdemeMetinSatýrý.setValue (new Double (aylýkGeriÖdeme));
    } // propertyChange(..) hazýr metodu sonu...

    double aylýkGeriÖdemeyiHesapla (double borçAnaparasý, double oran, int yýlSayýsý) {
        double aylýkFaiz, ara, payda, cevap;

        yýlSayýsý *= 12;
        if (oran > 0.01) {
            aylýkFaiz = oran / 100.0 / 12.0;
            ara = Math.pow ((1 + aylýkFaiz), (0 - yýlSayýsý));
            payda = (1 - ara) / aylýkFaiz;
        }else payda = yýlSayýsý; // oran=0;

        cevap = (-1 * borçAnaparasý) / payda;
        return cevap;
    } // aylýkGeriÖdemeyiHesapla(..) metodu sonu...

    private void biçimleyicileriKur() {
        tutarBiçimleyici = NumberFormat.getNumberInstance(); // Kesirsiz...

        oranBiçimleyici = NumberFormat.getNumberInstance();
        oranBiçimleyici.setMinimumFractionDigits (3); // 3 kesirli...

        aylýkGeriÖdemeBiçimleyici = NumberFormat.getCurrencyInstance(); // 2 kesirli...
    } // biçimleyicileriKur() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Biçimli Metin Satýrý Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_16());
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_16 sýnýfý sonu...