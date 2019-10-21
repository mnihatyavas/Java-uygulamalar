// J5e_9.java: InputVerificationDemo (VerigiriþiGeçerlilemeGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.InputVerifier;
import javax.swing.BorderFactory;
import javax.swing.UnsupportedLookAndFeelException;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 * Bu faizli geri ödeme programý da J5c-16/17.java gibidir.
 * Ancak burada veri giriþ kontrolu JFormattedTextField ile deðil
 * extends javax.swing.InputVerifier'lý Geçerlileyicim sýnýfýmýzla saðlanmaktadýr.
 */
public class J5e_9 extends JPanel {
    // Baþlangýç deðerler...
    private static double VARSAYILI_MEBLAÐ = 100000;
    private static double VARSAYILI_ORAN = 7.5; //7.5%
    private static int VARSAYILI_YIL = 30;

    // Metin satýrý etiket deðiþkenleri...
    private JLabel meblaðEtiketi;
    private JLabel oranEtiketi;
    private JLabel yýlEtiketi;
    private JLabel ödemeEtiketi;
    
    // Etiket içeriði metinleri...
    private static String meblaðMetni = "Borç Alýnan TL (10,000-10,000,000): ";
    private static String oranMetni = "Yýllýk Faiz Oraný % (>= 0): ";
    private static String yýlMetni = "Geri Ödeme Süresi YIL (1-40): ";
    private static String ödemeMetni = "Aylýk Geri Ödeme TL: ";

    // Metin satýrý deðiþkenleri...
    private JTextField meblaðMetinsatýrý;
    private JTextField oranMetinsatýrý;
    private JTextField yýlMetinsatýrý;
    private JTextField ödemeMetinsatýrý;
    
    // Girilecek metin satýrý verilerini sayýsala çevirip biçimleyecek deðiþkenler...
    private NumberFormat meblaðBiçimi;
    private NumberFormat oranBiçimi;
    private DecimalFormat yýlBiçimi;
    private DecimalFormat ödemeBiçimi;
    private Geçerlileyicim geçerlileyici = new Geçerlileyicim();

    public J5e_9() {//Kurucu...
        super (new BorderLayout());
        biçimleriKur();
        double ödeme = ödemeyiHesapla (VARSAYILI_MEBLAÐ, VARSAYILI_ORAN, VARSAYILI_YIL);

        // Metin satýrlarý ön baþlýklý etiketleri yaratalým...
        meblaðEtiketi = new JLabel (meblaðMetni);
        oranEtiketi = new JLabel (oranMetni);
        yýlEtiketi = new JLabel (yýlMetni);
        ödemeEtiketi = new JLabel (ödemeMetni);

        // Metin satýrlarýný geçerlileyici kontrollu kuralým...
        meblaðMetinsatýrý = new JTextField (meblaðBiçimi.format (VARSAYILI_MEBLAÐ), 10);
        meblaðMetinsatýrý.setInputVerifier (geçerlileyici);

        oranMetinsatýrý = new JTextField (oranBiçimi.format (VARSAYILI_ORAN), 10);
        oranMetinsatýrý.setInputVerifier (geçerlileyici);

        yýlMetinsatýrý = new JTextField (yýlBiçimi.format (VARSAYILI_YIL), 10);
        yýlMetinsatýrý.setInputVerifier (geçerlileyici);

        ödemeMetinsatýrý = new JTextField (ödemeBiçimi.format (ödeme), 10);
        ödemeMetinsatýrý.setInputVerifier (geçerlileyici);
        ödemeMetinsatýrý.setEditable (false); // Müdahalesiz...
        ödemeMetinsatýrý.setFocusable (false); // Tab odaklanma döngüsüne kapalý...
        ödemeMetinsatýrý.setForeground (Color.red); // Aylýk geri ödeme tutarlarý kýrmýzý yansýyacak...

        // Veri giriþli metin satýrlarýnýn herbirini geçerlileyici kontrollü dinleyiciye duyarlayalým...
        meblaðMetinsatýrý.addActionListener (geçerlileyici);
        oranMetinsatýrý.addActionListener (geçerlileyici);
        yýlMetinsatýrý.addActionListener (geçerlileyici);

        // Etiketleri ilgili metin satýrlarýna ilintileyelim...
        meblaðEtiketi.setLabelFor (meblaðMetinsatýrý);
        oranEtiketi.setLabelFor (oranMetinsatýrý);
        yýlEtiketi.setLabelFor (yýlMetinsatýrý);
        ödemeEtiketi.setLabelFor (ödemeMetinsatýrý);

        // Etiketleri GridLayout serimlemeli bir panele kolonvari üstüste ekleyelim...
        JPanel etiketPaneli = new JPanel (new GridLayout (0,1));
        etiketPaneli.add (meblaðEtiketi);
        etiketPaneli.add (oranEtiketi);
        etiketPaneli.add (yýlEtiketi);
        etiketPaneli.add (ödemeEtiketi);

        // Metin satýrlarýný da GridLayout serimlemeli kolonvari üstüste ekleyelim...
        JPanel metinsatýrýPaneli = new JPanel (new GridLayout (0,1));
        metinsatýrýPaneli.add (meblaðMetinsatýrý);
        metinsatýrýPaneli.add (oranMetinsatýrý);
        metinsatýrýPaneli.add (yýlMetinsatýrý);
        metinsatýrýPaneli.add (ödemeMetinsatýrý);

        // Bu iki paneli BorderLayout serimlemeli, içerik panosuna sol-sað ekleyelim...
        add (etiketPaneli, BorderLayout.CENTER);
        add (metinsatýrýPaneli, BorderLayout.LINE_END);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        setBackground (Color.GREEN); // BorderFactory çevre boþluðunu yeþile boyayalým...
    } // J5e_9() kurucusu sonu...

    class Geçerlileyicim extends InputVerifier implements ActionListener {
        double ASGARÝ_MEBLAÐ = 10000.0;
        double AZAMÝ_MEBLAÐ = 10000000.0;
        double ASGARÝ_ORAN = 0.0;
        int ASGARÝ_YIL = 1;
        int AZAMÝ_YIL = 40;

        public void actionPerformed (ActionEvent olay) {
            JTextField kaynak = (JTextField)olay.getSource();
            shouldYieldFocus (kaynak);
            kaynak.selectAll();
        } // actionPerformed(..) hazýr metodu sonu...

        public boolean shouldYieldFocus (JComponent veriGiriþi) {
            boolean giriþTamamMý = verify (veriGiriþi);
            düzelt (veriGiriþi);
            ödemeyiGüncelle();

            if (giriþTamamMý) return true;
            else {Toolkit.getDefaultToolkit().beep(); return false;}
        } // shouldYieldFocus(..) hazýr metodu sonu...

        public boolean verify (JComponent veriGiriþi) {return kontrol (veriGiriþi, false);} // verify(..) hazýr metodu...
        protected void düzelt (JComponent veriGiriþi) {kontrol (veriGiriþi, true);}

        protected boolean kontrol (JComponent veriGiriþi, boolean deðiþsinMi) {
            if (veriGiriþi == meblaðMetinsatýrý) return meblaðKontrolü (deðiþsinMi);
            else if (veriGiriþi == oranMetinsatýrý) return oranKontrolü (deðiþsinMi);
            else if (veriGiriþi == yýlMetinsatýrý) return yýlKontrolü (deðiþsinMi);
            else return true; // Olmaz!..
        } // kontrol(..) metodu sonu...

        protected boolean meblaðKontrolü (boolean deðiþtir) {
            boolean geçerliMi = true;
            double meblað = VARSAYILI_MEBLAÐ;

            try {meblað = meblaðBiçimi.parse (meblaðMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {geçerliMi = false; ist.printStackTrace();}

            if ((meblað < ASGARÝ_MEBLAÐ) || (meblað > AZAMÝ_MEBLAÐ)) {
                geçerliMi = false;
                if (deðiþtir) {
                    if (meblað < ASGARÝ_MEBLAÐ) meblað = ASGARÝ_MEBLAÐ;
                    else meblað = AZAMÝ_MEBLAÐ;
                } // iç-if kararý sonu...
            } // dýþ-if kararý sonu...

            if (deðiþtir) {
                meblaðMetinsatýrý.setText (meblaðBiçimi.format (meblað));
                meblaðMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // meblaðKontrolü(..) metodu sonu...

        protected boolean oranKontrolü (boolean deðiþtir) {
            boolean geçerliMi = true;
            double oran = VARSAYILI_ORAN;

            try {oran = oranBiçimi.parse (oranMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {geçerliMi = false; ist.printStackTrace();}

            if (oran < ASGARÝ_ORAN) {
                geçerliMi = false;
                if (deðiþtir) oran = ASGARÝ_ORAN;
            } // i kararý sonu...

            if (deðiþtir) {
                oranMetinsatýrý.setText (oranBiçimi.format (oran));
                oranMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // oranKontrolü(..) metodu sonu...

        protected boolean yýlKontrolü (boolean deðiþtir) {
            boolean geçerliMi = true;
            int yýl = VARSAYILI_YIL;

            try {yýl = yýlBiçimi.parse (yýlMetinsatýrý.getText()).intValue();
            }catch (ParseException ist) {geçerliMi = false;ist.printStackTrace();}

            if ((yýl < ASGARÝ_YIL) || (yýl > AZAMÝ_YIL)) {
                geçerliMi = false;
                if (deðiþtir) {
                    if (yýl < ASGARÝ_YIL) yýl = ASGARÝ_YIL;
                    else yýl = AZAMÝ_YIL;
                } // iç-if kararý sonu...
            } // dýþ-if kararý sonu...

            if (deðiþtir) {
                yýlMetinsatýrý.setText (yýlBiçimi.format (yýl));
                yýlMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // yýlKontrolü(..) metodu sonu...

        protected void ödemeyiGüncelle() {
            double meblað = VARSAYILI_MEBLAÐ;
            double oran = VARSAYILI_ORAN;
            int yýl = VARSAYILI_YIL;
            double ödeme = 0.0;

            try {meblað = meblaðBiçimi.parse (meblaðMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();}
            try {oran = oranBiçimi.parse (oranMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();}
            try {yýl = yýlBiçimi.parse (yýlMetinsatýrý.getText()).intValue();
            }catch (ParseException ist) {ist.printStackTrace();}

            ödeme = ödemeyiHesapla (meblað, oran, yýl);
            ödemeMetinsatýrý.setText (ödemeBiçimi.format (ödeme));
        } // ödemeyiGüncelle() metodu sonu...
    } // Geçerlileyicim sýnýfý sonu...

    private void biçimleriKur() {
        meblaðBiçimi = NumberFormat.getNumberInstance();
        meblaðBiçimi.setParseIntegerOnly (true); // Borç ana para double, ama küsüratsýz yansýtýlsýn...

        oranBiçimi = NumberFormat.getNumberInstance();
        oranBiçimi.setMinimumFractionDigits (3);

        yýlBiçimi = (DecimalFormat)NumberFormat.getNumberInstance();
        yýlBiçimi.setParseIntegerOnly (true);

        ödemeBiçimi = (DecimalFormat)NumberFormat.getNumberInstance();
        ödemeBiçimi.setMaximumFractionDigits (2);
        ödemeBiçimi.setNegativePrefix ("(");
        ödemeBiçimi.setNegativeSuffix (")");
    } // biçimleriKur() metodu sonu...

    double ödemeyiHesapla (double borç, double oran, int yýl) {
        double aylýkOran, payda;

        yýl *= 12;
        if (oran > 0.01) {
            aylýkOran = oran / 100.0 / 12.0;
            payda = (1 - Math.pow((1 + aylýkOran), (0.0 - yýl))) / aylýkOran;
        } else // oran ~= 0
            payda = yýl;

        return (-1 * borç) / payda;
    } // ödemeyiHesapla(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Verigiriþi Geçerlileme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_9(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {//UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {// javax.swing.
            ist.printStackTrace();
        }catch (IllegalAccessException ist) {// java.lang.
            ist.printStackTrace();
        }catch (InstantiationException ist) {// java.lang.
            ist.printStackTrace();
        }catch (ClassNotFoundException ist) {//java.lang.
            ist.printStackTrace();
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_9 sýnýfý sonu...