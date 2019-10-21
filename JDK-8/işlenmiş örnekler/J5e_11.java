// J5e_11.java: ModalityDemo (ModülerlikGösterisi) örneði.

import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.GraphicsConfiguration;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class J5e_11 {
    // Ýlk pencere modülü, isim giriþ diyalog modülü, döküman giriþ diyalog modülü...
    private JFrame pencere1;
    private JDialog diyalog11;
    private JDialog diyalog12;
    // pencere1'in radyo düðmeleri...
    JRadioButton rd11, rd12, rd13;
    // diyalog11 etiketi...
    JLabel etiket11;
    // diyalog11 metin satýrý...
    JTextField metinSatýrý11;
    // diyalog11 metin alaný...
    JTextArea metinAlaný11;

    // Ýkinci pencere modülü, isim giriþ diyalog modülü, döküman giriþ diyalog modülü...
    private JFrame pencere2;
    private JDialog diyalog21;
    private JDialog diyalog22;
    // pencere2'nin radyo düðmeleri...
    JRadioButton rd21, rd22, rd23;
    // diyalog21 etiketi...
    JLabel etiket21;
    // diyalog21 metin satýrý...
    JTextField metinSatýrý21;
    // diyalog21 metin alaný...
    JTextArea metinAlaný21;

    // Üçüncü pencere modülü, diyalogsuz...
    private JFrame pencere3;
    // pencere3'ün radyo düðmeleri...
    JRadioButton rd31, rd32, rd33;

    // Dördüncü pencere modülü, evet/hayýr diyalog modülü...
    private JFrame pencere4;

    // Genel kullanýmlý komponentler...
    Container kab;
    JLabel etiket;
    JTextField metinSatýrý;
    JTextArea metinAlaný;
    JButton düðme;
    JPanel panel;

    private static WindowListener pencereyiKapat = new WindowAdapter() {
        public void windowClosing (WindowEvent olay) {olay.getWindow().dispose();}
    }; // pri.. ifadesi sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                J5e_11 modüler = new J5e_11();
                modüler.modülleriYaratGUI();
                modüler.göster();
            } // run() hazýr raporlamalý sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    // Pencere modüllerini görünüme sunar...
    private void göster() {
        pencere1.setVisible (true);
        pencere2.setVisible (true);
        pencere3.setVisible (true);
        pencere4.setVisible (true);
    } // göster() metodu sonu...

    private void modülleriYaratGUI() {
        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAygýtý = grafikÇevresi.getDefaultScreenDevice();
        GraphicsConfiguration grafikÞekillenmesi = grafikAygýtý.getDefaultConfiguration();
        Insets içBoþluk = Toolkit.getDefaultToolkit().getScreenInsets (grafikÞekillenmesi);
        int brütGeniþlik = grafikÞekillenmesi.getBounds().width - içBoþluk.left - içBoþluk.right;
        int brütYükseklik = grafikÞekillenmesi.getBounds().height - içBoþluk.top - içBoþluk.bottom;

        // Ýlk pencere1

        pencere1 = new JFrame ("Kitap 1 Pencere Modülü");
        pencere1.setBounds (32, 32, 300, 200);
        pencere1.addWindowListener (pencereyiKapat);
        // Radyo düðmelerini yaratalým...
        rd11 = new JRadioButton ("Biyografiler", true);
        rd12 = new JRadioButton ("Masallar", false);
        rd13 = new JRadioButton ("Þiirler", false);
        rd11.setHorizontalAlignment (SwingConstants.CENTER);
        rd12.setHorizontalAlignment (SwingConstants.CENTER);
        rd13.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo düðmelerini gruplayalým...
        ButtonGroup rdGrubu = new ButtonGroup();
        rdGrubu.add (rd11);
        rdGrubu.add (rd12);
        rdGrubu.add (rd13);
        düðme = new JButton ("Tamam");
        düðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                // Seçilen radyo düðmesi etiketini alalým...
                String baþlýk = null;
                if (rd11.isSelected()) baþlýk = rd11.getText();
                else if (rd12.isSelected()) baþlýk = rd12.getText();
                else baþlýk = rd13.getText();
                // Seçilen baþlýðý ilgili diyalog baþlýðýna koyalým...
                diyalog11.setTitle (baþlýk + " Modüler Ýsim Diyaloðu");
                diyalog12.setTitle (baþlýk + " Modüler Döküman Diyaloðu");
                diyalog11.setVisible (true);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // düð.. ifadesi sonu...
        // Komponentleri serimleyelim...
        kab = pencere1.getContentPane();
        kab.setLayout (new GridLayout (1, 1));
        panel = new JPanel (new GridLayout(4,1));
        panel.add (rd11);
        panel.add (rd12);
        panel.add (rd13);
        JPanel panel1 = new JPanel();
        panel1.setLayout (new FlowLayout());
        panel1.add (düðme);
        panel.add (panel1);
        kab.add (panel);
        
        // pencere1'e ait olan diyalog11
        
        diyalog11 = new JDialog (pencere1);
        diyalog11.setBounds (132, 132, 300, 200);
        diyalog11.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("Lütfen isminizi girin: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        metinSatýrý11 = new JTextField (12);
        metinSatýrý11.setHorizontalAlignment (SwingConstants.CENTER);
        düðme = new JButton ("Tamam");
        düðme.setHorizontalAlignment (SwingConstants.CENTER);
        düðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                etiket11.setText (metinSatýrý11.getText() + " tarafýndan");
                metinAlaný11.setText ("");
                diyalog12.setVisible (true);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // düð.. ifadesi sonu...
        kab = diyalog11.getContentPane();
        // Tek kolona altalta etiketi, metin satýrýný ve düðmeyi ekleyelim...
        kab.setLayout (new BorderLayout());
        kab.add (etiket, BorderLayout.NORTH);
        kab.add (metinSatýrý11, BorderLayout.CENTER);
        kab.add (düðme, BorderLayout.SOUTH);

        // pencere1'e ait olan diyalog12

        diyalog12 = new JDialog (diyalog11, "", Dialog.ModalityType.DOCUMENT_MODAL);
        diyalog12.setBounds (232, 232, 300, 200);
        diyalog12.addWindowListener (pencereyiKapat);
        metinAlaný11 = new JTextArea();
        etiket11 = new JLabel();
        etiket11.setHorizontalAlignment (SwingConstants.RIGHT);
        kab = diyalog12.getContentPane();
        kab.setLayout (new BorderLayout());
        kab.add (new JScrollPane (metinAlaný11), BorderLayout.CENTER);
        panel = new JPanel();
        panel.setLayout (new FlowLayout (FlowLayout.RIGHT));
        panel.add (etiket11);
        kab.add (panel, BorderLayout.SOUTH);

        // Ýkinci pencere2

        pencere2 = new JFrame ("Kitap 2 Pencere Modülü");
        pencere2.setBounds (brütGeniþlik - 300 - 32, 32, 300, 200);
        pencere2.addWindowListener (pencereyiKapat);
        // Radyo düðmelerini yaratalým...
        rd21 = new JRadioButton ("Biyografiler", true);
        rd22 = new JRadioButton ("Masallar", false);
        rd23 = new JRadioButton ("Þiirler", false);
        rd21.setHorizontalAlignment (SwingConstants.CENTER);
        rd22.setHorizontalAlignment (SwingConstants.CENTER);
        rd23.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo düðmelerini gruplayalým...
        rdGrubu = new ButtonGroup();
        rdGrubu.add (rd21);
        rdGrubu.add (rd22);
        rdGrubu.add (rd23);
        düðme = new JButton ("Tamam");
        düðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                // Seçilen radyo düðmesi baþlýðýný alalým...
                String baþlýk = null;
                if (rd21.isSelected()) baþlýk = rd21.getText();
                else if (rd22.isSelected()) baþlýk = rd22.getText();
                else baþlýk = rd23.getText();
                // Seçilen baþlýðý ilgili diyalog baþlýðýna kuralým...
                diyalog21.setTitle (baþlýk + " Modüler Ýsim Diyaloðu");
                diyalog22.setTitle (baþlýk + " Modüler Döküman Diyaloðu");
                diyalog21.setVisible (true);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // düð.. ifadesi sonu...
        kab = pencere2.getContentPane();
        kab.setLayout (new GridLayout (1, 1));
        panel = new JPanel (new GridLayout (4,1));
        panel.add (rd21);
        panel.add (rd22);
        panel.add (rd23);
        panel1 = new JPanel();
        panel1.setLayout (new FlowLayout());
        panel1.add (düðme);
        panel.add (panel1);
        kab.add (panel);

        // pencere2'ye ait olan diyalog21

        diyalog21 = new JDialog (pencere2);
        diyalog21.setBounds (brütGeniþlik - 400 - 32, 132, 300, 200);
        diyalog21.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("Lütfen isminizi girin: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        metinSatýrý21 = new JTextField (12);
        metinSatýrý21.setHorizontalAlignment (SwingConstants.CENTER);
        düðme = new JButton ("Tamam");
        düðme.setHorizontalAlignment (SwingConstants.CENTER);
        düðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                etiket21.setText (metinSatýrý21.getText() + " tarafýndan");
                metinAlaný21.setText ("");
                diyalog22.setVisible (true);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // düð.. ifadesi sonu...
        kab = diyalog21.getContentPane();
        // Komponentleri kolonvari altalta ekleyelim...
        kab.setLayout (new BorderLayout());
        kab.add (etiket, BorderLayout.NORTH);
        kab.add (metinSatýrý21, BorderLayout.CENTER);
        kab.add (düðme, BorderLayout.SOUTH);

        // pencere2'ye ait olan diyalog22

        diyalog22 = new JDialog (diyalog21, "", Dialog.ModalityType.DOCUMENT_MODAL);
        diyalog22.setBounds (brütGeniþlik - 500 - 32, 232, 300, 200);
        diyalog22.addWindowListener (pencereyiKapat);
        etiket21 = new JLabel();
        etiket21.setHorizontalAlignment (SwingConstants.RIGHT);
        metinAlaný21 = new JTextArea();
        kab = diyalog22.getContentPane();
        kab.setLayout (new BorderLayout());
        kab.add (new JScrollPane (metinAlaný21), BorderLayout.CENTER);
        panel = new JPanel();
        panel.setLayout (new FlowLayout (FlowLayout.RIGHT));
        panel.add (etiket21);
        kab.add (panel, BorderLayout.SOUTH);

        // Üçüncü pencere3

        pencere3 = new JFrame ("Klasikler Pencere Modülü");
        pencere3.setModalExclusionType (Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        pencere3.setBounds (32, brütYükseklik - 200 - 32, 300, 200);
        pencere3.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("Ünlü Yazarlar: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo düðmelerini yaratalým...
        rd31 = new JRadioButton ("Burns", true);
        rd32 = new JRadioButton ("Dickens", false);
        rd33 = new JRadioButton ("Twain", false);
        rd31.setHorizontalAlignment (SwingConstants.CENTER);
        rd32.setHorizontalAlignment (SwingConstants.CENTER);
        rd33.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo düðmelerini gruplayalým...
        rdGrubu = new ButtonGroup();
        rdGrubu.add (rd31);
        rdGrubu.add (rd32);
        rdGrubu.add (rd33);
        kab = pencere3.getContentPane();
        kab.setLayout (new GridLayout (4,0));
        kab.add (etiket);
        kab.add (rd31);
        kab.add (rd32);
        kab.add (rd33);

        // Dördüncü pencere4

        pencere4 = new JFrame ("Rating'li Pencere Modülü");
        pencere4.setBounds (brütGeniþlik - 300 - 32, brütYükseklik - 200 - 32, 300, 200);
        pencere4.addWindowListener (pencereyiKapat);
        düðme = new JButton ("Kendine sor");
        düðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showConfirmDialog (null,
                        "Son okuduðum kitaptan hoþlandým mý?",
                        "Kendine Sorulu Modüler Uygulama Diyaloðu",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // düð.. ifadesi sonu...
        kab = pencere4.getContentPane();
        kab.setLayout (new FlowLayout (FlowLayout.CENTER, 8, 8));
        kab.add (düðme);
    } // modülleriYaratGUI() metodu sonu...
} // J5e_11 sýnýfý sonu...