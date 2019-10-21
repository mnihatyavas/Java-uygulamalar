// J5e_9.java: InputVerificationDemo (Verigiri�iGe�erlilemeG�sterisi) �rne�i.

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
 * Bu faizli geri �deme program� da J5c-16/17.java gibidir.
 * Ancak burada veri giri� kontrolu JFormattedTextField ile de�il
 * extends javax.swing.InputVerifier'l� Ge�erlileyicim s�n�f�m�zla sa�lanmaktad�r.
 */
public class J5e_9 extends JPanel {
    // Ba�lang�� de�erler...
    private static double VARSAYILI_MEBLA� = 100000;
    private static double VARSAYILI_ORAN = 7.5; //7.5%
    private static int VARSAYILI_YIL = 30;

    // Metin sat�r� etiket de�i�kenleri...
    private JLabel mebla�Etiketi;
    private JLabel oranEtiketi;
    private JLabel y�lEtiketi;
    private JLabel �demeEtiketi;
    
    // Etiket i�eri�i metinleri...
    private static String mebla�Metni = "Bor� Al�nan TL (10,000-10,000,000): ";
    private static String oranMetni = "Y�ll�k Faiz Oran� % (>= 0): ";
    private static String y�lMetni = "Geri �deme S�resi YIL (1-40): ";
    private static String �demeMetni = "Ayl�k Geri �deme TL: ";

    // Metin sat�r� de�i�kenleri...
    private JTextField mebla�Metinsat�r�;
    private JTextField oranMetinsat�r�;
    private JTextField y�lMetinsat�r�;
    private JTextField �demeMetinsat�r�;
    
    // Girilecek metin sat�r� verilerini say�sala �evirip bi�imleyecek de�i�kenler...
    private NumberFormat mebla�Bi�imi;
    private NumberFormat oranBi�imi;
    private DecimalFormat y�lBi�imi;
    private DecimalFormat �demeBi�imi;
    private Ge�erlileyicim ge�erlileyici = new Ge�erlileyicim();

    public J5e_9() {//Kurucu...
        super (new BorderLayout());
        bi�imleriKur();
        double �deme = �demeyiHesapla (VARSAYILI_MEBLA�, VARSAYILI_ORAN, VARSAYILI_YIL);

        // Metin sat�rlar� �n ba�l�kl� etiketleri yaratal�m...
        mebla�Etiketi = new JLabel (mebla�Metni);
        oranEtiketi = new JLabel (oranMetni);
        y�lEtiketi = new JLabel (y�lMetni);
        �demeEtiketi = new JLabel (�demeMetni);

        // Metin sat�rlar�n� ge�erlileyici kontrollu kural�m...
        mebla�Metinsat�r� = new JTextField (mebla�Bi�imi.format (VARSAYILI_MEBLA�), 10);
        mebla�Metinsat�r�.setInputVerifier (ge�erlileyici);

        oranMetinsat�r� = new JTextField (oranBi�imi.format (VARSAYILI_ORAN), 10);
        oranMetinsat�r�.setInputVerifier (ge�erlileyici);

        y�lMetinsat�r� = new JTextField (y�lBi�imi.format (VARSAYILI_YIL), 10);
        y�lMetinsat�r�.setInputVerifier (ge�erlileyici);

        �demeMetinsat�r� = new JTextField (�demeBi�imi.format (�deme), 10);
        �demeMetinsat�r�.setInputVerifier (ge�erlileyici);
        �demeMetinsat�r�.setEditable (false); // M�dahalesiz...
        �demeMetinsat�r�.setFocusable (false); // Tab odaklanma d�ng�s�ne kapal�...
        �demeMetinsat�r�.setForeground (Color.red); // Ayl�k geri �deme tutarlar� k�rm�z� yans�yacak...

        // Veri giri�li metin sat�rlar�n�n herbirini ge�erlileyici kontroll� dinleyiciye duyarlayal�m...
        mebla�Metinsat�r�.addActionListener (ge�erlileyici);
        oranMetinsat�r�.addActionListener (ge�erlileyici);
        y�lMetinsat�r�.addActionListener (ge�erlileyici);

        // Etiketleri ilgili metin sat�rlar�na ilintileyelim...
        mebla�Etiketi.setLabelFor (mebla�Metinsat�r�);
        oranEtiketi.setLabelFor (oranMetinsat�r�);
        y�lEtiketi.setLabelFor (y�lMetinsat�r�);
        �demeEtiketi.setLabelFor (�demeMetinsat�r�);

        // Etiketleri GridLayout serimlemeli bir panele kolonvari �st�ste ekleyelim...
        JPanel etiketPaneli = new JPanel (new GridLayout (0,1));
        etiketPaneli.add (mebla�Etiketi);
        etiketPaneli.add (oranEtiketi);
        etiketPaneli.add (y�lEtiketi);
        etiketPaneli.add (�demeEtiketi);

        // Metin sat�rlar�n� da GridLayout serimlemeli kolonvari �st�ste ekleyelim...
        JPanel metinsat�r�Paneli = new JPanel (new GridLayout (0,1));
        metinsat�r�Paneli.add (mebla�Metinsat�r�);
        metinsat�r�Paneli.add (oranMetinsat�r�);
        metinsat�r�Paneli.add (y�lMetinsat�r�);
        metinsat�r�Paneli.add (�demeMetinsat�r�);

        // Bu iki paneli BorderLayout serimlemeli, i�erik panosuna sol-sa� ekleyelim...
        add (etiketPaneli, BorderLayout.CENTER);
        add (metinsat�r�Paneli, BorderLayout.LINE_END);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        setBackground (Color.GREEN); // BorderFactory �evre bo�lu�unu ye�ile boyayal�m...
    } // J5e_9() kurucusu sonu...

    class Ge�erlileyicim extends InputVerifier implements ActionListener {
        double ASGAR�_MEBLA� = 10000.0;
        double AZAM�_MEBLA� = 10000000.0;
        double ASGAR�_ORAN = 0.0;
        int ASGAR�_YIL = 1;
        int AZAM�_YIL = 40;

        public void actionPerformed (ActionEvent olay) {
            JTextField kaynak = (JTextField)olay.getSource();
            shouldYieldFocus (kaynak);
            kaynak.selectAll();
        } // actionPerformed(..) haz�r metodu sonu...

        public boolean shouldYieldFocus (JComponent veriGiri�i) {
            boolean giri�TamamM� = verify (veriGiri�i);
            d�zelt (veriGiri�i);
            �demeyiG�ncelle();

            if (giri�TamamM�) return true;
            else {Toolkit.getDefaultToolkit().beep(); return false;}
        } // shouldYieldFocus(..) haz�r metodu sonu...

        public boolean verify (JComponent veriGiri�i) {return kontrol (veriGiri�i, false);} // verify(..) haz�r metodu...
        protected void d�zelt (JComponent veriGiri�i) {kontrol (veriGiri�i, true);}

        protected boolean kontrol (JComponent veriGiri�i, boolean de�i�sinMi) {
            if (veriGiri�i == mebla�Metinsat�r�) return mebla�Kontrol� (de�i�sinMi);
            else if (veriGiri�i == oranMetinsat�r�) return oranKontrol� (de�i�sinMi);
            else if (veriGiri�i == y�lMetinsat�r�) return y�lKontrol� (de�i�sinMi);
            else return true; // Olmaz!..
        } // kontrol(..) metodu sonu...

        protected boolean mebla�Kontrol� (boolean de�i�tir) {
            boolean ge�erliMi = true;
            double mebla� = VARSAYILI_MEBLA�;

            try {mebla� = mebla�Bi�imi.parse (mebla�Metinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {ge�erliMi = false; ist.printStackTrace();}

            if ((mebla� < ASGAR�_MEBLA�) || (mebla� > AZAM�_MEBLA�)) {
                ge�erliMi = false;
                if (de�i�tir) {
                    if (mebla� < ASGAR�_MEBLA�) mebla� = ASGAR�_MEBLA�;
                    else mebla� = AZAM�_MEBLA�;
                } // i�-if karar� sonu...
            } // d��-if karar� sonu...

            if (de�i�tir) {
                mebla�Metinsat�r�.setText (mebla�Bi�imi.format (mebla�));
                mebla�Metinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // mebla�Kontrol�(..) metodu sonu...

        protected boolean oranKontrol� (boolean de�i�tir) {
            boolean ge�erliMi = true;
            double oran = VARSAYILI_ORAN;

            try {oran = oranBi�imi.parse (oranMetinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {ge�erliMi = false; ist.printStackTrace();}

            if (oran < ASGAR�_ORAN) {
                ge�erliMi = false;
                if (de�i�tir) oran = ASGAR�_ORAN;
            } // i karar� sonu...

            if (de�i�tir) {
                oranMetinsat�r�.setText (oranBi�imi.format (oran));
                oranMetinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // oranKontrol�(..) metodu sonu...

        protected boolean y�lKontrol� (boolean de�i�tir) {
            boolean ge�erliMi = true;
            int y�l = VARSAYILI_YIL;

            try {y�l = y�lBi�imi.parse (y�lMetinsat�r�.getText()).intValue();
            }catch (ParseException ist) {ge�erliMi = false;ist.printStackTrace();}

            if ((y�l < ASGAR�_YIL) || (y�l > AZAM�_YIL)) {
                ge�erliMi = false;
                if (de�i�tir) {
                    if (y�l < ASGAR�_YIL) y�l = ASGAR�_YIL;
                    else y�l = AZAM�_YIL;
                } // i�-if karar� sonu...
            } // d��-if karar� sonu...

            if (de�i�tir) {
                y�lMetinsat�r�.setText (y�lBi�imi.format (y�l));
                y�lMetinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // y�lKontrol�(..) metodu sonu...

        protected void �demeyiG�ncelle() {
            double mebla� = VARSAYILI_MEBLA�;
            double oran = VARSAYILI_ORAN;
            int y�l = VARSAYILI_YIL;
            double �deme = 0.0;

            try {mebla� = mebla�Bi�imi.parse (mebla�Metinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();}
            try {oran = oranBi�imi.parse (oranMetinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();}
            try {y�l = y�lBi�imi.parse (y�lMetinsat�r�.getText()).intValue();
            }catch (ParseException ist) {ist.printStackTrace();}

            �deme = �demeyiHesapla (mebla�, oran, y�l);
            �demeMetinsat�r�.setText (�demeBi�imi.format (�deme));
        } // �demeyiG�ncelle() metodu sonu...
    } // Ge�erlileyicim s�n�f� sonu...

    private void bi�imleriKur() {
        mebla�Bi�imi = NumberFormat.getNumberInstance();
        mebla�Bi�imi.setParseIntegerOnly (true); // Bor� ana para double, ama k�s�rats�z yans�t�ls�n...

        oranBi�imi = NumberFormat.getNumberInstance();
        oranBi�imi.setMinimumFractionDigits (3);

        y�lBi�imi = (DecimalFormat)NumberFormat.getNumberInstance();
        y�lBi�imi.setParseIntegerOnly (true);

        �demeBi�imi = (DecimalFormat)NumberFormat.getNumberInstance();
        �demeBi�imi.setMaximumFractionDigits (2);
        �demeBi�imi.setNegativePrefix ("(");
        �demeBi�imi.setNegativeSuffix (")");
    } // bi�imleriKur() metodu sonu...

    double �demeyiHesapla (double bor�, double oran, int y�l) {
        double ayl�kOran, payda;

        y�l *= 12;
        if (oran > 0.01) {
            ayl�kOran = oran / 100.0 / 12.0;
            payda = (1 - Math.pow((1 + ayl�kOran), (0.0 - y�l))) / ayl�kOran;
        } else // oran ~= 0
            payda = y�l;

        return (-1 * bor�) / payda;
    } // �demeyiHesapla(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Verigiri�i Ge�erlileme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_9(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

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
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_9 s�n�f� sonu...