// J5c_16.java: FormattedTextFieldDemo (Bi�imliMetinSat�r�G�sterimi) �rne�i.

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
    // Metin sat�rlar� i�in ilk de�erler...
    private double tutar = 100000; // Al�nan bor� ana paras�...
    private double oran = 7.5;  // Borcun y�ll�k faiz oran�...
    private int y�lSay�s� = 30; // Bor�un �denece�i y�l say�s�...

    // Metin sat�rlar�n� tan�mlayacak etiketler...
    private JLabel tutarEtiketi;
    private JLabel oranEtiketi;
    private JLabel y�lSay�s�Etiketi;
    private JLabel ayl�kGeri�demeEtiketi;

    // Etiketler i�in i�erik dizgeleri...
    private static String tutarStr = "Bor� Anaparas� (TL): ";
    private static String oranStr = "Y�ll�k Faiz (%): ";
    private static String y�lSay�s�Str = "Y�l Say�s�: ";
    private static String ayl�kGeri�demeStr = "Ayl�k Geri �deme: ";

    // Veri giri� bi�imli metin sat�lar�...
    private JFormattedTextField tutarMetinSat�r�;
    private JFormattedTextField oranMetinSat�r�;
    private JFormattedTextField y�lSay�s�MetinSat�r�;
    private JFormattedTextField ayl�kGeri�demeMetinSat�r�;

    // Metin sat�r�na girilen Str de�eri say�sala �evirip bi�imleyecek nesneler...
    private NumberFormat tutarBi�imleyici;
    private NumberFormat oranBi�imleyici;
    private NumberFormat ayl�kGeri�demeBi�imleyici;

    public J5c_16() {// Kurucu...
        super (new BorderLayout());
        bi�imleyicileriKur();
        double ayl�kGeri�deme = ayl�kGeri�demeyiHesapla (tutar, oran, y�lSay�s�);

        // Metin sat�r� a��klama etiketlerini yaratal�m...
        tutarEtiketi = new JLabel (tutarStr);
        oranEtiketi = new JLabel (oranStr);
        y�lSay�s�Etiketi = new JLabel (y�lSay�s�Str);
        ayl�kGeri�demeEtiketi = new JLabel (ayl�kGeri�demeStr);

        // Metin sat�r� bi�imli i�eriklerini yarat�p de�erleri atayal�m...
        tutarMetinSat�r� = new JFormattedTextField (tutarBi�imleyici);
        tutarMetinSat�r�.setValue (new Double (tutar));
        tutarMetinSat�r�.setColumns (10);
        tutarMetinSat�r�.addPropertyChangeListener ("value", this);

        oranMetinSat�r� = new JFormattedTextField (oranBi�imleyici);
        oranMetinSat�r�.setValue (new Double (oran));
        oranMetinSat�r�.setColumns (10);
        oranMetinSat�r�.addPropertyChangeListener ("value", this);

        y�lSay�s�MetinSat�r� = new JFormattedTextField();
        y�lSay�s�MetinSat�r�.setValue (new Integer (y�lSay�s�));
        y�lSay�s�MetinSat�r�.setColumns (10);
        y�lSay�s�MetinSat�r�.addPropertyChangeListener ("value", this);

        ayl�kGeri�demeMetinSat�r� = new JFormattedTextField (ayl�kGeri�demeBi�imleyici);
        ayl�kGeri�demeMetinSat�r�.setValue (new Double (ayl�kGeri�deme));
        ayl�kGeri�demeMetinSat�r�.setColumns (10);
        ayl�kGeri�demeMetinSat�r�.setEditable (false);
        ayl�kGeri�demeMetinSat�r�.setForeground (Color.red);

        // Etiketlere ait olduklar� metin sat�rlar�n� kural�m/ekleyelim...
        tutarEtiketi.setLabelFor (tutarMetinSat�r�);
        oranEtiketi.setLabelFor (oranMetinSat�r�);
        y�lSay�s�Etiketi.setLabelFor (y�lSay�s�MetinSat�r�);
        ayl�kGeri�demeEtiketi.setLabelFor (ayl�kGeri�demeMetinSat�r�);

        // Etiketleri bir panele yerle�tirelim...
        JPanel etiketPanosu = new JPanel (new GridLayout (0,1));
        etiketPanosu.add (tutarEtiketi);
        etiketPanosu.add (oranEtiketi);
        etiketPanosu.add (y�lSay�s�Etiketi);
        etiketPanosu.add (ayl�kGeri�demeEtiketi);
        etiketPanosu.setBackground (new Color(10,220,10));

        // Metin sat�rlar�n� da ayr� bir panele yerle�tirelim...
        JPanel metinSat�r�Panosu = new JPanel (new GridLayout (0,1));
        metinSat�r�Panosu.add (tutarMetinSat�r�);
        metinSat�r�Panosu.add (oranMetinSat�r�);
        metinSat�r�Panosu.add (y�lSay�s�MetinSat�r�);
        metinSat�r�Panosu.add (ayl�kGeri�demeMetinSat�r�);

        // Bu 2 paneli "this" super panele (etiket solda, metin sa�da) ekleyelim...
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        add (etiketPanosu, BorderLayout.CENTER);
        add (metinSat�r�Panosu, BorderLayout.LINE_END);
        setBackground (new Color(200,100,10));
    } // J5c_16() kurucusu sonu...

    // Metin sat�r� de�er ("value") de�i�imine duyarl�d�r...
    public void propertyChange (PropertyChangeEvent olay) {
        Object kaynak = olay.getSource();
        if (kaynak == tutarMetinSat�r�) tutar = ((Number)tutarMetinSat�r�.getValue()).doubleValue();
        else if (kaynak == oranMetinSat�r�) oran = ((Number)oranMetinSat�r�.getValue()).doubleValue();
        else if (kaynak == y�lSay�s�MetinSat�r�) y�lSay�s� = ((Number)y�lSay�s�MetinSat�r�.getValue()).intValue();

        double ayl�kGeri�deme = ayl�kGeri�demeyiHesapla (tutar, oran, y�lSay�s�);
        ayl�kGeri�demeMetinSat�r�.setValue (new Double (ayl�kGeri�deme));
    } // propertyChange(..) haz�r metodu sonu...

    double ayl�kGeri�demeyiHesapla (double bor�Anaparas�, double oran, int y�lSay�s�) {
        double ayl�kFaiz, ara, payda, cevap;

        y�lSay�s� *= 12;
        if (oran > 0.01) {
            ayl�kFaiz = oran / 100.0 / 12.0;
            ara = Math.pow ((1 + ayl�kFaiz), (0 - y�lSay�s�));
            payda = (1 - ara) / ayl�kFaiz;
        }else payda = y�lSay�s�; // oran=0;

        cevap = (-1 * bor�Anaparas�) / payda;
        return cevap;
    } // ayl�kGeri�demeyiHesapla(..) metodu sonu...

    private void bi�imleyicileriKur() {
        tutarBi�imleyici = NumberFormat.getNumberInstance(); // Kesirsiz...

        oranBi�imleyici = NumberFormat.getNumberInstance();
        oranBi�imleyici.setMinimumFractionDigits (3); // 3 kesirli...

        ayl�kGeri�demeBi�imleyici = NumberFormat.getCurrencyInstance(); // 2 kesirli...
    } // bi�imleyicileriKur() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Bi�imli Metin Sat�r� G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_16());
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_16 s�n�f� sonu...