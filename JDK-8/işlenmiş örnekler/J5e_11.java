// J5e_11.java: ModalityDemo (Mod�lerlikG�sterisi) �rne�i.

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
    // �lk pencere mod�l�, isim giri� diyalog mod�l�, d�k�man giri� diyalog mod�l�...
    private JFrame pencere1;
    private JDialog diyalog11;
    private JDialog diyalog12;
    // pencere1'in radyo d��meleri...
    JRadioButton rd11, rd12, rd13;
    // diyalog11 etiketi...
    JLabel etiket11;
    // diyalog11 metin sat�r�...
    JTextField metinSat�r�11;
    // diyalog11 metin alan�...
    JTextArea metinAlan�11;

    // �kinci pencere mod�l�, isim giri� diyalog mod�l�, d�k�man giri� diyalog mod�l�...
    private JFrame pencere2;
    private JDialog diyalog21;
    private JDialog diyalog22;
    // pencere2'nin radyo d��meleri...
    JRadioButton rd21, rd22, rd23;
    // diyalog21 etiketi...
    JLabel etiket21;
    // diyalog21 metin sat�r�...
    JTextField metinSat�r�21;
    // diyalog21 metin alan�...
    JTextArea metinAlan�21;

    // ���nc� pencere mod�l�, diyalogsuz...
    private JFrame pencere3;
    // pencere3'�n radyo d��meleri...
    JRadioButton rd31, rd32, rd33;

    // D�rd�nc� pencere mod�l�, evet/hay�r diyalog mod�l�...
    private JFrame pencere4;

    // Genel kullan�ml� komponentler...
    Container kab;
    JLabel etiket;
    JTextField metinSat�r�;
    JTextArea metinAlan�;
    JButton d��me;
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
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                J5e_11 mod�ler = new J5e_11();
                mod�ler.mod�lleriYaratGUI();
                mod�ler.g�ster();
            } // run() haz�r raporlamal� sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    // Pencere mod�llerini g�r�n�me sunar...
    private void g�ster() {
        pencere1.setVisible (true);
        pencere2.setVisible (true);
        pencere3.setVisible (true);
        pencere4.setVisible (true);
    } // g�ster() metodu sonu...

    private void mod�lleriYaratGUI() {
        GraphicsEnvironment grafik�evresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAyg�t� = grafik�evresi.getDefaultScreenDevice();
        GraphicsConfiguration grafik�ekillenmesi = grafikAyg�t�.getDefaultConfiguration();
        Insets i�Bo�luk = Toolkit.getDefaultToolkit().getScreenInsets (grafik�ekillenmesi);
        int br�tGeni�lik = grafik�ekillenmesi.getBounds().width - i�Bo�luk.left - i�Bo�luk.right;
        int br�tY�kseklik = grafik�ekillenmesi.getBounds().height - i�Bo�luk.top - i�Bo�luk.bottom;

        // �lk pencere1

        pencere1 = new JFrame ("Kitap 1 Pencere Mod�l�");
        pencere1.setBounds (32, 32, 300, 200);
        pencere1.addWindowListener (pencereyiKapat);
        // Radyo d��melerini yaratal�m...
        rd11 = new JRadioButton ("Biyografiler", true);
        rd12 = new JRadioButton ("Masallar", false);
        rd13 = new JRadioButton ("�iirler", false);
        rd11.setHorizontalAlignment (SwingConstants.CENTER);
        rd12.setHorizontalAlignment (SwingConstants.CENTER);
        rd13.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo d��melerini gruplayal�m...
        ButtonGroup rdGrubu = new ButtonGroup();
        rdGrubu.add (rd11);
        rdGrubu.add (rd12);
        rdGrubu.add (rd13);
        d��me = new JButton ("Tamam");
        d��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                // Se�ilen radyo d��mesi etiketini alal�m...
                String ba�l�k = null;
                if (rd11.isSelected()) ba�l�k = rd11.getText();
                else if (rd12.isSelected()) ba�l�k = rd12.getText();
                else ba�l�k = rd13.getText();
                // Se�ilen ba�l��� ilgili diyalog ba�l���na koyal�m...
                diyalog11.setTitle (ba�l�k + " Mod�ler �sim Diyalo�u");
                diyalog12.setTitle (ba�l�k + " Mod�ler D�k�man Diyalo�u");
                diyalog11.setVisible (true);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // d��.. ifadesi sonu...
        // Komponentleri serimleyelim...
        kab = pencere1.getContentPane();
        kab.setLayout (new GridLayout (1, 1));
        panel = new JPanel (new GridLayout(4,1));
        panel.add (rd11);
        panel.add (rd12);
        panel.add (rd13);
        JPanel panel1 = new JPanel();
        panel1.setLayout (new FlowLayout());
        panel1.add (d��me);
        panel.add (panel1);
        kab.add (panel);
        
        // pencere1'e ait olan diyalog11
        
        diyalog11 = new JDialog (pencere1);
        diyalog11.setBounds (132, 132, 300, 200);
        diyalog11.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("L�tfen isminizi girin: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        metinSat�r�11 = new JTextField (12);
        metinSat�r�11.setHorizontalAlignment (SwingConstants.CENTER);
        d��me = new JButton ("Tamam");
        d��me.setHorizontalAlignment (SwingConstants.CENTER);
        d��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                etiket11.setText (metinSat�r�11.getText() + " taraf�ndan");
                metinAlan�11.setText ("");
                diyalog12.setVisible (true);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // d��.. ifadesi sonu...
        kab = diyalog11.getContentPane();
        // Tek kolona altalta etiketi, metin sat�r�n� ve d��meyi ekleyelim...
        kab.setLayout (new BorderLayout());
        kab.add (etiket, BorderLayout.NORTH);
        kab.add (metinSat�r�11, BorderLayout.CENTER);
        kab.add (d��me, BorderLayout.SOUTH);

        // pencere1'e ait olan diyalog12

        diyalog12 = new JDialog (diyalog11, "", Dialog.ModalityType.DOCUMENT_MODAL);
        diyalog12.setBounds (232, 232, 300, 200);
        diyalog12.addWindowListener (pencereyiKapat);
        metinAlan�11 = new JTextArea();
        etiket11 = new JLabel();
        etiket11.setHorizontalAlignment (SwingConstants.RIGHT);
        kab = diyalog12.getContentPane();
        kab.setLayout (new BorderLayout());
        kab.add (new JScrollPane (metinAlan�11), BorderLayout.CENTER);
        panel = new JPanel();
        panel.setLayout (new FlowLayout (FlowLayout.RIGHT));
        panel.add (etiket11);
        kab.add (panel, BorderLayout.SOUTH);

        // �kinci pencere2

        pencere2 = new JFrame ("Kitap 2 Pencere Mod�l�");
        pencere2.setBounds (br�tGeni�lik - 300 - 32, 32, 300, 200);
        pencere2.addWindowListener (pencereyiKapat);
        // Radyo d��melerini yaratal�m...
        rd21 = new JRadioButton ("Biyografiler", true);
        rd22 = new JRadioButton ("Masallar", false);
        rd23 = new JRadioButton ("�iirler", false);
        rd21.setHorizontalAlignment (SwingConstants.CENTER);
        rd22.setHorizontalAlignment (SwingConstants.CENTER);
        rd23.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo d��melerini gruplayal�m...
        rdGrubu = new ButtonGroup();
        rdGrubu.add (rd21);
        rdGrubu.add (rd22);
        rdGrubu.add (rd23);
        d��me = new JButton ("Tamam");
        d��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                // Se�ilen radyo d��mesi ba�l���n� alal�m...
                String ba�l�k = null;
                if (rd21.isSelected()) ba�l�k = rd21.getText();
                else if (rd22.isSelected()) ba�l�k = rd22.getText();
                else ba�l�k = rd23.getText();
                // Se�ilen ba�l��� ilgili diyalog ba�l���na kural�m...
                diyalog21.setTitle (ba�l�k + " Mod�ler �sim Diyalo�u");
                diyalog22.setTitle (ba�l�k + " Mod�ler D�k�man Diyalo�u");
                diyalog21.setVisible (true);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // d��.. ifadesi sonu...
        kab = pencere2.getContentPane();
        kab.setLayout (new GridLayout (1, 1));
        panel = new JPanel (new GridLayout (4,1));
        panel.add (rd21);
        panel.add (rd22);
        panel.add (rd23);
        panel1 = new JPanel();
        panel1.setLayout (new FlowLayout());
        panel1.add (d��me);
        panel.add (panel1);
        kab.add (panel);

        // pencere2'ye ait olan diyalog21

        diyalog21 = new JDialog (pencere2);
        diyalog21.setBounds (br�tGeni�lik - 400 - 32, 132, 300, 200);
        diyalog21.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("L�tfen isminizi girin: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        metinSat�r�21 = new JTextField (12);
        metinSat�r�21.setHorizontalAlignment (SwingConstants.CENTER);
        d��me = new JButton ("Tamam");
        d��me.setHorizontalAlignment (SwingConstants.CENTER);
        d��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                etiket21.setText (metinSat�r�21.getText() + " taraf�ndan");
                metinAlan�21.setText ("");
                diyalog22.setVisible (true);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // d��.. ifadesi sonu...
        kab = diyalog21.getContentPane();
        // Komponentleri kolonvari altalta ekleyelim...
        kab.setLayout (new BorderLayout());
        kab.add (etiket, BorderLayout.NORTH);
        kab.add (metinSat�r�21, BorderLayout.CENTER);
        kab.add (d��me, BorderLayout.SOUTH);

        // pencere2'ye ait olan diyalog22

        diyalog22 = new JDialog (diyalog21, "", Dialog.ModalityType.DOCUMENT_MODAL);
        diyalog22.setBounds (br�tGeni�lik - 500 - 32, 232, 300, 200);
        diyalog22.addWindowListener (pencereyiKapat);
        etiket21 = new JLabel();
        etiket21.setHorizontalAlignment (SwingConstants.RIGHT);
        metinAlan�21 = new JTextArea();
        kab = diyalog22.getContentPane();
        kab.setLayout (new BorderLayout());
        kab.add (new JScrollPane (metinAlan�21), BorderLayout.CENTER);
        panel = new JPanel();
        panel.setLayout (new FlowLayout (FlowLayout.RIGHT));
        panel.add (etiket21);
        kab.add (panel, BorderLayout.SOUTH);

        // ���nc� pencere3

        pencere3 = new JFrame ("Klasikler Pencere Mod�l�");
        pencere3.setModalExclusionType (Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        pencere3.setBounds (32, br�tY�kseklik - 200 - 32, 300, 200);
        pencere3.addWindowListener (pencereyiKapat);
        etiket = new JLabel ("�nl� Yazarlar: ");
        etiket.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo d��melerini yaratal�m...
        rd31 = new JRadioButton ("Burns", true);
        rd32 = new JRadioButton ("Dickens", false);
        rd33 = new JRadioButton ("Twain", false);
        rd31.setHorizontalAlignment (SwingConstants.CENTER);
        rd32.setHorizontalAlignment (SwingConstants.CENTER);
        rd33.setHorizontalAlignment (SwingConstants.CENTER);
        // Radyo d��melerini gruplayal�m...
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

        // D�rd�nc� pencere4

        pencere4 = new JFrame ("Rating'li Pencere Mod�l�");
        pencere4.setBounds (br�tGeni�lik - 300 - 32, br�tY�kseklik - 200 - 32, 300, 200);
        pencere4.addWindowListener (pencereyiKapat);
        d��me = new JButton ("Kendine sor");
        d��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showConfirmDialog (null,
                        "Son okudu�um kitaptan ho�land�m m�?",
                        "Kendine Sorulu Mod�ler Uygulama Diyalo�u",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // d��.. ifadesi sonu...
        kab = pencere4.getContentPane();
        kab.setLayout (new FlowLayout (FlowLayout.CENTER, 8, 8));
        kab.add (d��me);
    } // mod�lleriYaratGUI() metodu sonu...
} // J5e_11 s�n�f� sonu...