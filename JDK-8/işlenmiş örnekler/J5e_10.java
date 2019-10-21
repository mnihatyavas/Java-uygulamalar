// J5e_10.java: InputVerificationDialogDemo (Verigiri�iGe�erlilemeDiyalo�uG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane; // Diyalog panosu...
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.InputVerifier;
import javax.swing.UnsupportedLookAndFeelException;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.ParseException;

/* Bir �nceki J5e_9.java gibidir.
 * Fark� javax.swing.InputVerifier'l� Ge�erlileyicim i�inde, hatal� veri giri�inde
 * kullan�c�y� uyaran ikaz diyalo�unun bulunmas�d�r.
 */
public class J5e_10 extends JPanel {
    // Varsay�l� ba�lang�� de�erleri...
    private static double VARSAYILI_MEBLA� = 100000;
    private static double VARSAYILI_ORAN = 7.5;  // Y�ll�k %7.5
    private static int VARSAYILI_YIL = 30;

    // Etiket null de�i�kenleri...
    private JLabel mebla�Etiketi;
    private JLabel oranEtiketi;
    private JLabel y�lEtiketi;
    private JLabel �demeEtiketi;

    // Etiket metinleri...
    private static String mebla�Metni = "Bor� Al�nan Mebla�-TL (10,000 - 10,000,000): ";
    private static String oranMetni = "Y�ll�k Faiz Oran�-% (>= 0): ";
    private static String y�lMetni = "Geri �deme S�resi-YIL (1-40): ";
    private static String �demeMetni = "Ayl�k Geri �denen-TL: ";

    // Metin sat�r� null de�i�kenleri...
    private JTextField mebla�Metinsat�r�;
    private JTextField oranMetinsat�r�;
    private JTextField y�lMetinsat�r�;
    private JTextField �demeMetinsat�r�;

    // Metinsat�r� verigiri� rakamlar�n� say�sala �evirme ve bi�imleme null de�i�kenleri...
    private NumberFormat mebla�Bi�imi;
    private NumberFormat oranBi�imi;
    private DecimalFormat y�lBi�imi;
    private DecimalFormat �demeBi�imi;
    private NumberFormat tamsay�Bi�imi;
    private Ge�erlileyicim ge�erlileyici = new Ge�erlileyicim(); // S�n�f kKurucusunu �a��r�r...

    public J5e_10() {// Kurucu...
        super (new BorderLayout());
        bi�imleriKur();
        double �deme = �demeyiHesapla (VARSAYILI_MEBLA�, VARSAYILI_ORAN, VARSAYILI_YIL);

        // Etiketleri i�erik merin ba�l�klar�yla yaratal�m...
        mebla�Etiketi = new JLabel (mebla�Metni);
        oranEtiketi = new JLabel (oranMetni);
        y�lEtiketi = new JLabel (y�lMetni);
        �demeEtiketi = new JLabel (�demeMetni);

        // Metin sat�rlar�n� ge�erlileyici filitreli varsay�l� de�erleriyle yaratal�m...
        mebla�Metinsat�r� = new JTextField (mebla�Bi�imi.format (VARSAYILI_MEBLA�), 10);
        mebla�Metinsat�r�.setInputVerifier (ge�erlileyici);

        oranMetinsat�r� = new JTextField (oranBi�imi.format (VARSAYILI_ORAN), 10);
        oranMetinsat�r�.setInputVerifier (ge�erlileyici);

        y�lMetinsat�r� = new JTextField (y�lBi�imi.format (VARSAYILI_YIL), 10);
        y�lMetinsat�r�.setInputVerifier (ge�erlileyici);

        �demeMetinsat�r� = new JTextField (�demeBi�imi.format (�deme), 10);
        �demeMetinsat�r�.setInputVerifier (ge�erlileyici);
        �demeMetinsat�r�.setEditable (false); // M�dahalesiz...
        �demeMetinsat�r�.setFocusable (false); // Tab odaklanma d�ng�s�ne etkisiz...
        �demeMetinsat�r�.setForeground (Color.red); // K�rm�z� renkle...

        // Veri giri�li metin sat�rlar�n�n herbirini ge�erlileyici s�zgeciyle dinleyiciye duyarlayal�m...
        mebla�Metinsat�r�.addActionListener (ge�erlileyici);
        oranMetinsat�r�.addActionListener (ge�erlileyici);
        y�lMetinsat�r�.addActionListener (ge�erlileyici);

        // Etiketleri ait olduklar� metin sat�rlar�yla ilintileyelim...
        mebla�Etiketi.setLabelFor (mebla�Metinsat�r�);
        oranEtiketi.setLabelFor (oranMetinsat�r�);
        y�lEtiketi.setLabelFor (y�lMetinsat�r�);
        �demeEtiketi.setLabelFor (�demeMetinsat�r�);

        // Etiket panelini serimleyelim...
        JPanel etiketPaneli = new JPanel (new GridLayout (0,1));
        etiketPaneli.add (mebla�Etiketi);
        etiketPaneli.add (oranEtiketi);
        etiketPaneli.add (y�lEtiketi);
        etiketPaneli.add (�demeEtiketi);

        // Metin sat�r� panelini serimleyelim...
        JPanel metinsat�r�Paneli = new JPanel (new GridLayout (0,1));
        metinsat�r�Paneli.add (mebla�Metinsat�r�);
        metinsat�r�Paneli.add (oranMetinsat�r�);
        metinsat�r�Paneli.add (y�lMetinsat�r�);
        metinsat�r�Paneli.add (�demeMetinsat�r�);

        // Panelleri sollu sa�l� �evresi siyah �er�eveli i�erik panosuna ekleyelim...
        setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        add (etiketPaneli, BorderLayout.CENTER);
        add (metinsat�r�Paneli, BorderLayout.LINE_END);
        setBackground (Color.BLACK);
    } // J5e_10() kurucusu sonu...

    class Ge�erlileyicim extends InputVerifier implements ActionListener {
        double ASGAR�_MEBLA� = 10000;
        double AZAM�_MEBLA� = 10000000;
        double ASGAR�_ORAN = 0.0;
        int ASGAR�_YIL = 1;
        int AZAM�_YIL = 40;
        String diyalogMesaj� = null;

        // Bu s�n�f�n �zel bir kurucusu yok, verigiri�lerine duyarl� dinleyicisi var...
        public void actionPerformed (ActionEvent olay) {
            JTextField verigiri�Metinsat�r� = (JTextField)olay.getSource();
            shouldYieldFocus (verigiri�Metinsat�r�);
            verigiri�Metinsat�r�.selectAll();
        } // actionPerformed(..) haz�r metodu sonu...

        public boolean shouldYieldFocus (JComponent veriGiri�i) {
            boolean veriGiri�iTamamM� = verify (veriGiri�i);
            d�zelt (veriGiri�i);
            �demeyiG�ncelle();

            if (veriGiri�iTamamM�) return true;

            // Verigiri�i hatal�ysa...
            diyalogMesaj� += ".\nL�tfen tekrar deneyin.";
            JOptionPane.showMessageDialog (
                    null, // Kendi �zel penceremiz kullan�lm�yor...
                    diyalogMesaj�, // G�sterilecek hata mesaj�...
                    "Ge�ersiz De�er", // �kaz diyalok ba�l���...
                    JOptionPane.WARNING_MESSAGE); // �kaz mesaj� ikonu...

            // �nceki varsay�l� de�eri koy...
            veriGiri�i.setInputVerifier (this);

            // Sesli ikaz...
            Toolkit.getDefaultToolkit().beep();
            return false;
        } // shouldYieldFocus(..) haz�r metodu sonu...

        public boolean verify (JComponent veriGiri�i) {return metinsat�r�n�Kontrolet (veriGiri�i, false);} // Haz�r metod...
        protected void d�zelt (JComponent veriGiri�i) {metinsat�r�n�Kontrolet (veriGiri�i, true);}

        protected boolean metinsat�r�n�Kontrolet (JComponent veriGiri�i, boolean de�i�sinMi) {
            if (veriGiri�i == mebla�Metinsat�r�) return �demeyiKontrolet (de�i�sinMi);
            else if (veriGiri�i == oranMetinsat�r�) return oran�Kontrolet (de�i�sinMi);
            else if (veriGiri�i == y�lMetinsat�r�) return y�l�Kontrolet (de�i�sinMi);
            else return true; // Olmayacak ihtimal!..
        } // metinsat�r�n�Kontrolet(..) metodu sonu...

        public boolean �demeyiKontrolet (boolean de�i�tir) {
            boolean ge�erliMi = true;
            double mebla� = VARSAYILI_MEBLA�;

            try {mebla� = mebla�Bi�imi.parse (mebla�Metinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();
                diyalogMesaj� = "HATA: Bor� verigiri�inde ge�ersiz double parasal bi�im";
                return false; // D�n, alta devam etme...
            } // try-catch blo�u sonu...

            // Double bor� rakam� alt/�st s�n�r d���...
            if ((mebla� < ASGAR�_MEBLA�) || (mebla� > AZAM�_MEBLA�)) {
                ge�erliMi = false;
                if (mebla� < ASGAR�_MEBLA�) diyalogMesaj� = "Girilen bor� mebla�� < " + tamsay�Bi�imi.format (ASGAR�_MEBLA�);
                else diyalogMesaj� = "Girilen bor� mebla�� > " + tamsay�Bi�imi.format (AZAM�_MEBLA�);
            } // if karar� sonu...

            // Mebla� k���k de olsa b�y�k de, bi�imli yans�tal�m...
            if (de�i�tir) {
                mebla�Metinsat�r�.setText (mebla�Bi�imi.format (mebla�));
                mebla�Metinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // �demeyiKontrolet(..) metodu sonu...

        public boolean oran�Kontrolet (boolean de�i�tir) {
            boolean ge�erliMi = true;
            double oran = VARSAYILI_ORAN;

            try {oran = oranBi�imi.parse (oranMetinsat�r�.getText()).doubleValue();
            } catch (ParseException ist) {ist.printStackTrace();
                diyalogMesaj� = "HATA: Faiz verigiri�inde ge�ersiz double oran bi�imi";
                return false;
            } // try-catch blo�u sonu...

            // Oran 0'dan k���k girilmi�se...
            if (oran < ASGAR�_ORAN) {
                ge�erliMi = false;
                diyalogMesaj� = "Girilen faiz oran� < " + ASGAR�_ORAN;
            } // if karar� sonu...

            if (de�i�tir) {
                oranMetinsat�r�.setText (oranBi�imi.format (oran));
                oranMetinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // oran�Kontrolet(..) metodu sonu...

        public boolean y�l�Kontrolet (boolean de�i�tir) {
            boolean ge�erliMi = true;
            int y�l = VARSAYILI_YIL;

            try {y�l = y�lBi�imi.parse (y�lMetinsat�r�.getText()).intValue();
            }catch (ParseException ist) {ist.printStackTrace();
                diyalogMesaj� = "HATA: S�re verigiri�inde ge�ersiz tamsay� y�l bi�imi";
                return false;
            } // try-catch blo�u sonu...

            // Girilen y�l asgari/azami s�n�rlar� d���nda...
            if (y�l < ASGAR�_YIL) {ge�erliMi = false;
                diyalogMesaj� = "Girilen y�l s�resi < " + tamsay�Bi�imi.format (ASGAR�_YIL);
            }else if (y�l > AZAM�_YIL) {ge�erliMi = false;
                diyalogMesaj� = "Girilen y�l s�resi > " + tamsay�Bi�imi.format (AZAM�_YIL);
            } // if karar� sonu...

            if (de�i�tir) {
                y�lMetinsat�r�.setText (y�lBi�imi.format (y�l));
                y�lMetinsat�r�.selectAll();
            } // if karar� sonu...

            return ge�erliMi;
        } // y�l�Kontrolet(..) metodu sonu...

        protected void �demeyiG�ncelle() {
            double mebla� = VARSAYILI_MEBLA�;
            double oran = VARSAYILI_ORAN;
            int y�l = VARSAYILI_YIL;
            double �deme = 0.0;

            try {mebla� = mebla�Bi�imi.parse (mebla�Metinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {}
            try {oran = oranBi�imi.parse (oranMetinsat�r�.getText()).doubleValue();
            }catch (ParseException ist) {}
            try {y�l = y�lBi�imi.parse (y�lMetinsat�r�.getText()).intValue();
            }catch (ParseException ist) {}

            �deme = �demeyiHesapla (mebla�, oran, y�l);
            �demeMetinsat�r�.setText (�demeBi�imi.format (�deme));
        } // �demeyiG�ncelle() metodu sonu...
    } // Ge�erlileyicim s�n�f� sonu...

    double �demeyiHesapla (double mebla�, double oran, int y�l) {
        double ayl�kFaiz, payda;

        y�l *= 12;
        if (oran > 0.01) {  
            ayl�kFaiz = oran / 100.0 / 12.0;
            payda = (1 - Math.pow ((1 + ayl�kFaiz), (0.0 - y�l))) / ayl�kFaiz;
        } else //oran ~= 0
            payda = oran;

        return (-1 * mebla�) / payda;
    } // �demeyiHesapla(..) metodu sonu...

    private void bi�imleriKur() {
        mebla�Bi�imi = NumberFormat.getNumberInstance();
        mebla�Bi�imi.setParseIntegerOnly (true);

        oranBi�imi = NumberFormat.getNumberInstance();
        oranBi�imi.setMinimumFractionDigits (3);

        y�lBi�imi = (DecimalFormat)NumberFormat.getNumberInstance();
        y�lBi�imi.setParseIntegerOnly (true);

        �demeBi�imi = (DecimalFormat)NumberFormat.getNumberInstance();
        �demeBi�imi.setMaximumFractionDigits (2);
        �demeBi�imi.setNegativePrefix ("(");
        �demeBi�imi.setNegativeSuffix (")");

        tamsay�Bi�imi = NumberFormat.getIntegerInstance();
    } // bi�imleriKur() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Verigiri�i Ge�erlileme Diyalo�u G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_10(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_10 s�n�f� sonu...