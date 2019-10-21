// J5e_10.java: InputVerificationDialogDemo (VerigiriþiGeçerlilemeDiyaloðuGösterisi) örneði.

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

/* Bir önceki J5e_9.java gibidir.
 * Farký javax.swing.InputVerifier'lý Geçerlileyicim içinde, hatalý veri giriþinde
 * kullanýcýyý uyaran ikaz diyaloðunun bulunmasýdýr.
 */
public class J5e_10 extends JPanel {
    // Varsayýlý baþlangýç deðerleri...
    private static double VARSAYILI_MEBLAÐ = 100000;
    private static double VARSAYILI_ORAN = 7.5;  // Yýllýk %7.5
    private static int VARSAYILI_YIL = 30;

    // Etiket null deðiþkenleri...
    private JLabel meblaðEtiketi;
    private JLabel oranEtiketi;
    private JLabel yýlEtiketi;
    private JLabel ödemeEtiketi;

    // Etiket metinleri...
    private static String meblaðMetni = "Borç Alýnan Meblað-TL (10,000 - 10,000,000): ";
    private static String oranMetni = "Yýllýk Faiz Oraný-% (>= 0): ";
    private static String yýlMetni = "Geri Ödeme Süresi-YIL (1-40): ";
    private static String ödemeMetni = "Aylýk Geri Ödenen-TL: ";

    // Metin satýrý null deðiþkenleri...
    private JTextField meblaðMetinsatýrý;
    private JTextField oranMetinsatýrý;
    private JTextField yýlMetinsatýrý;
    private JTextField ödemeMetinsatýrý;

    // Metinsatýrý verigiriþ rakamlarýný sayýsala çevirme ve biçimleme null deðiþkenleri...
    private NumberFormat meblaðBiçimi;
    private NumberFormat oranBiçimi;
    private DecimalFormat yýlBiçimi;
    private DecimalFormat ödemeBiçimi;
    private NumberFormat tamsayýBiçimi;
    private Geçerlileyicim geçerlileyici = new Geçerlileyicim(); // Sýnýf kKurucusunu çaðýrýr...

    public J5e_10() {// Kurucu...
        super (new BorderLayout());
        biçimleriKur();
        double ödeme = ödemeyiHesapla (VARSAYILI_MEBLAÐ, VARSAYILI_ORAN, VARSAYILI_YIL);

        // Etiketleri içerik merin baþlýklarýyla yaratalým...
        meblaðEtiketi = new JLabel (meblaðMetni);
        oranEtiketi = new JLabel (oranMetni);
        yýlEtiketi = new JLabel (yýlMetni);
        ödemeEtiketi = new JLabel (ödemeMetni);

        // Metin satýrlarýný geçerlileyici filitreli varsayýlý deðerleriyle yaratalým...
        meblaðMetinsatýrý = new JTextField (meblaðBiçimi.format (VARSAYILI_MEBLAÐ), 10);
        meblaðMetinsatýrý.setInputVerifier (geçerlileyici);

        oranMetinsatýrý = new JTextField (oranBiçimi.format (VARSAYILI_ORAN), 10);
        oranMetinsatýrý.setInputVerifier (geçerlileyici);

        yýlMetinsatýrý = new JTextField (yýlBiçimi.format (VARSAYILI_YIL), 10);
        yýlMetinsatýrý.setInputVerifier (geçerlileyici);

        ödemeMetinsatýrý = new JTextField (ödemeBiçimi.format (ödeme), 10);
        ödemeMetinsatýrý.setInputVerifier (geçerlileyici);
        ödemeMetinsatýrý.setEditable (false); // Müdahalesiz...
        ödemeMetinsatýrý.setFocusable (false); // Tab odaklanma döngüsüne etkisiz...
        ödemeMetinsatýrý.setForeground (Color.red); // Kýrmýzý renkle...

        // Veri giriþli metin satýrlarýnýn herbirini geçerlileyici süzgeciyle dinleyiciye duyarlayalým...
        meblaðMetinsatýrý.addActionListener (geçerlileyici);
        oranMetinsatýrý.addActionListener (geçerlileyici);
        yýlMetinsatýrý.addActionListener (geçerlileyici);

        // Etiketleri ait olduklarý metin satýrlarýyla ilintileyelim...
        meblaðEtiketi.setLabelFor (meblaðMetinsatýrý);
        oranEtiketi.setLabelFor (oranMetinsatýrý);
        yýlEtiketi.setLabelFor (yýlMetinsatýrý);
        ödemeEtiketi.setLabelFor (ödemeMetinsatýrý);

        // Etiket panelini serimleyelim...
        JPanel etiketPaneli = new JPanel (new GridLayout (0,1));
        etiketPaneli.add (meblaðEtiketi);
        etiketPaneli.add (oranEtiketi);
        etiketPaneli.add (yýlEtiketi);
        etiketPaneli.add (ödemeEtiketi);

        // Metin satýrý panelini serimleyelim...
        JPanel metinsatýrýPaneli = new JPanel (new GridLayout (0,1));
        metinsatýrýPaneli.add (meblaðMetinsatýrý);
        metinsatýrýPaneli.add (oranMetinsatýrý);
        metinsatýrýPaneli.add (yýlMetinsatýrý);
        metinsatýrýPaneli.add (ödemeMetinsatýrý);

        // Panelleri sollu saðlý çevresi siyah çerçeveli içerik panosuna ekleyelim...
        setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        add (etiketPaneli, BorderLayout.CENTER);
        add (metinsatýrýPaneli, BorderLayout.LINE_END);
        setBackground (Color.BLACK);
    } // J5e_10() kurucusu sonu...

    class Geçerlileyicim extends InputVerifier implements ActionListener {
        double ASGARÝ_MEBLAÐ = 10000;
        double AZAMÝ_MEBLAÐ = 10000000;
        double ASGARÝ_ORAN = 0.0;
        int ASGARÝ_YIL = 1;
        int AZAMÝ_YIL = 40;
        String diyalogMesajý = null;

        // Bu sýnýfýn özel bir kurucusu yok, verigiriþlerine duyarlý dinleyicisi var...
        public void actionPerformed (ActionEvent olay) {
            JTextField verigiriþMetinsatýrý = (JTextField)olay.getSource();
            shouldYieldFocus (verigiriþMetinsatýrý);
            verigiriþMetinsatýrý.selectAll();
        } // actionPerformed(..) hazýr metodu sonu...

        public boolean shouldYieldFocus (JComponent veriGiriþi) {
            boolean veriGiriþiTamamMý = verify (veriGiriþi);
            düzelt (veriGiriþi);
            ödemeyiGüncelle();

            if (veriGiriþiTamamMý) return true;

            // Verigiriþi hatalýysa...
            diyalogMesajý += ".\nLütfen tekrar deneyin.";
            JOptionPane.showMessageDialog (
                    null, // Kendi özel penceremiz kullanýlmýyor...
                    diyalogMesajý, // Gösterilecek hata mesajý...
                    "Geçersiz Deðer", // Ýkaz diyalok baþlýðý...
                    JOptionPane.WARNING_MESSAGE); // Ýkaz mesajý ikonu...

            // Önceki varsayýlý deðeri koy...
            veriGiriþi.setInputVerifier (this);

            // Sesli ikaz...
            Toolkit.getDefaultToolkit().beep();
            return false;
        } // shouldYieldFocus(..) hazýr metodu sonu...

        public boolean verify (JComponent veriGiriþi) {return metinsatýrýnýKontrolet (veriGiriþi, false);} // Hazýr metod...
        protected void düzelt (JComponent veriGiriþi) {metinsatýrýnýKontrolet (veriGiriþi, true);}

        protected boolean metinsatýrýnýKontrolet (JComponent veriGiriþi, boolean deðiþsinMi) {
            if (veriGiriþi == meblaðMetinsatýrý) return ödemeyiKontrolet (deðiþsinMi);
            else if (veriGiriþi == oranMetinsatýrý) return oranýKontrolet (deðiþsinMi);
            else if (veriGiriþi == yýlMetinsatýrý) return yýlýKontrolet (deðiþsinMi);
            else return true; // Olmayacak ihtimal!..
        } // metinsatýrýnýKontrolet(..) metodu sonu...

        public boolean ödemeyiKontrolet (boolean deðiþtir) {
            boolean geçerliMi = true;
            double meblað = VARSAYILI_MEBLAÐ;

            try {meblað = meblaðBiçimi.parse (meblaðMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {ist.printStackTrace();
                diyalogMesajý = "HATA: Borç verigiriþinde geçersiz double parasal biçim";
                return false; // Dön, alta devam etme...
            } // try-catch bloðu sonu...

            // Double borç rakamý alt/üst sýnýr dýþý...
            if ((meblað < ASGARÝ_MEBLAÐ) || (meblað > AZAMÝ_MEBLAÐ)) {
                geçerliMi = false;
                if (meblað < ASGARÝ_MEBLAÐ) diyalogMesajý = "Girilen borç meblaðý < " + tamsayýBiçimi.format (ASGARÝ_MEBLAÐ);
                else diyalogMesajý = "Girilen borç meblaðý > " + tamsayýBiçimi.format (AZAMÝ_MEBLAÐ);
            } // if kararý sonu...

            // Meblað küçük de olsa büyük de, biçimli yansýtalým...
            if (deðiþtir) {
                meblaðMetinsatýrý.setText (meblaðBiçimi.format (meblað));
                meblaðMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // ödemeyiKontrolet(..) metodu sonu...

        public boolean oranýKontrolet (boolean deðiþtir) {
            boolean geçerliMi = true;
            double oran = VARSAYILI_ORAN;

            try {oran = oranBiçimi.parse (oranMetinsatýrý.getText()).doubleValue();
            } catch (ParseException ist) {ist.printStackTrace();
                diyalogMesajý = "HATA: Faiz verigiriþinde geçersiz double oran biçimi";
                return false;
            } // try-catch bloðu sonu...

            // Oran 0'dan küçük girilmiþse...
            if (oran < ASGARÝ_ORAN) {
                geçerliMi = false;
                diyalogMesajý = "Girilen faiz oraný < " + ASGARÝ_ORAN;
            } // if kararý sonu...

            if (deðiþtir) {
                oranMetinsatýrý.setText (oranBiçimi.format (oran));
                oranMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // oranýKontrolet(..) metodu sonu...

        public boolean yýlýKontrolet (boolean deðiþtir) {
            boolean geçerliMi = true;
            int yýl = VARSAYILI_YIL;

            try {yýl = yýlBiçimi.parse (yýlMetinsatýrý.getText()).intValue();
            }catch (ParseException ist) {ist.printStackTrace();
                diyalogMesajý = "HATA: Süre verigiriþinde geçersiz tamsayý yýl biçimi";
                return false;
            } // try-catch bloðu sonu...

            // Girilen yýl asgari/azami sýnýrlarý dýþýnda...
            if (yýl < ASGARÝ_YIL) {geçerliMi = false;
                diyalogMesajý = "Girilen yýl süresi < " + tamsayýBiçimi.format (ASGARÝ_YIL);
            }else if (yýl > AZAMÝ_YIL) {geçerliMi = false;
                diyalogMesajý = "Girilen yýl süresi > " + tamsayýBiçimi.format (AZAMÝ_YIL);
            } // if kararý sonu...

            if (deðiþtir) {
                yýlMetinsatýrý.setText (yýlBiçimi.format (yýl));
                yýlMetinsatýrý.selectAll();
            } // if kararý sonu...

            return geçerliMi;
        } // yýlýKontrolet(..) metodu sonu...

        protected void ödemeyiGüncelle() {
            double meblað = VARSAYILI_MEBLAÐ;
            double oran = VARSAYILI_ORAN;
            int yýl = VARSAYILI_YIL;
            double ödeme = 0.0;

            try {meblað = meblaðBiçimi.parse (meblaðMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {}
            try {oran = oranBiçimi.parse (oranMetinsatýrý.getText()).doubleValue();
            }catch (ParseException ist) {}
            try {yýl = yýlBiçimi.parse (yýlMetinsatýrý.getText()).intValue();
            }catch (ParseException ist) {}

            ödeme = ödemeyiHesapla (meblað, oran, yýl);
            ödemeMetinsatýrý.setText (ödemeBiçimi.format (ödeme));
        } // ödemeyiGüncelle() metodu sonu...
    } // Geçerlileyicim sýnýfý sonu...

    double ödemeyiHesapla (double meblað, double oran, int yýl) {
        double aylýkFaiz, payda;

        yýl *= 12;
        if (oran > 0.01) {  
            aylýkFaiz = oran / 100.0 / 12.0;
            payda = (1 - Math.pow ((1 + aylýkFaiz), (0.0 - yýl))) / aylýkFaiz;
        } else //oran ~= 0
            payda = oran;

        return (-1 * meblað) / payda;
    } // ödemeyiHesapla(..) metodu sonu...

    private void biçimleriKur() {
        meblaðBiçimi = NumberFormat.getNumberInstance();
        meblaðBiçimi.setParseIntegerOnly (true);

        oranBiçimi = NumberFormat.getNumberInstance();
        oranBiçimi.setMinimumFractionDigits (3);

        yýlBiçimi = (DecimalFormat)NumberFormat.getNumberInstance();
        yýlBiçimi.setParseIntegerOnly (true);

        ödemeBiçimi = (DecimalFormat)NumberFormat.getNumberInstance();
        ödemeBiçimi.setMaximumFractionDigits (2);
        ödemeBiçimi.setNegativePrefix ("(");
        ödemeBiçimi.setNegativeSuffix (")");

        tamsayýBiçimi = NumberFormat.getIntegerInstance();
    } // biçimleriKur() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Verigiriþi Geçerlileme Diyaloðu Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_10(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_10 sýnýfý sonu...