// J5c_19.java: FrameDemo2 (�er�eveG�sterimi2) �rne�i.

import java.awt.Container;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;

import java.awt.event.WindowAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

// resim/FD.jpg dosyas�n� kullanmaktad�r...
public class J5c_19 extends WindowAdapter implements ActionListener {
    private Point sonKonum = null;
    private int b�y�kX = 500;
    private int b�y�kY = 500;

    // Ana pencerenin varsay�l� d��mesi...
    private static JButton varsay�l�D��me = null;

    // Aksiyon komutlar� i�in sabit dizgeler...
    protected final static String S�SS�Z = "s�ss�z";
    protected final static String BH_S�S� = "bvh_s�s�";
    protected final static String PS_S�S� = "ps_s�s�";
    protected final static String PENCERE_YARAT = "yeni_pencere";
    protected final static String VARSAYILI_�KON = "varsay�l�_ikon";
    protected final static String DOSYADAN_�KON = "dosya_ikonu";
    protected final static String BOYAMA_�KON = "grafik_ikonu";

    // Yarat�lacak birsonraki pencere s�ss�z olacaksa "true"...
    protected boolean s�ss�zM� = false;

    // Yarat�lacak birsonraki pencere setIconImage/resim�konunuKoy'u �a��racaksa "true"...
    protected boolean ikonBelirlensinMi = false;

    // Yarat�lacak birsonraki pencere grafik�konuYarat'� �a��racaksa "true"...
    protected boolean grafik�konuYarat�ls�nM� = false;

    // Kurucuyla baz� ilk-de�erler atayal�m...
    public J5c_19() {
        Dimension ekranEbat� = Toolkit.getDefaultToolkit().getScreenSize();
        b�y�kX = ekranEbat�.width - 50;
        b�y�kY = ekranEbat�.height - 50;
    } // J5c_19() kurucusu sonu...

    // Ana penceredeki yeni pencere yaratma kontrollerini yaratal�m...
    protected JComponent tercihKontrolleriniYarat() {
        JLabel etiket1 = new JLabel ("Ard���k yarat�lacak pencereler i�in s�s tercihleri:");
        ButtonGroup d��meGrubu1 = new ButtonGroup();
        JLabel etiket2 = new JLabel ("�kon tercihleri:");
        ButtonGroup d��meGrubu2 = new ButtonGroup();

        // 6 adet radyo butonunu yarat�p s�s ve ikon grubuna kural�m...
        // S�s grubu...
        JRadioButton radyoD��mesi1 = new JRadioButton();
        radyoD��mesi1.setText ("Bak ve hisset s�sl�");
        radyoD��mesi1.setActionCommand (BH_S�S�);
        radyoD��mesi1.addActionListener (this); // Dinleyiciye duyarl� k�lal�m...
        radyoD��mesi1.setSelected (true); // S�s grubunda ilk anda bu se�ili...
        d��meGrubu1.add (radyoD��mesi1);

        JRadioButton radyoD��mesi2 = new JRadioButton();
        radyoD��mesi2.setText ("Pencere sistemi s�sl�");
        radyoD��mesi2.setActionCommand (PS_S�S�);
        radyoD��mesi2.addActionListener (this);
        d��meGrubu1.add (radyoD��mesi2);

        JRadioButton radyoD��mesi3 = new JRadioButton();
        radyoD��mesi3.setText ("S�ss�z");
        radyoD��mesi3.setActionCommand (S�SS�Z);
        radyoD��mesi3.addActionListener (this);
        d��meGrubu1.add (radyoD��mesi3);

        // �kon grubu...
        JRadioButton radyoD��mesi4 = new JRadioButton();
        radyoD��mesi4.setText ("Varsay�l� ikon");
        radyoD��mesi4.setActionCommand (VARSAYILI_�KON);
        radyoD��mesi4.addActionListener (this);
        radyoD��mesi4.setSelected (true); // �kon grubunda ilk anda bu se�ili...
        d��meGrubu2.add (radyoD��mesi4);

        JRadioButton radyoD��mesi5 = new JRadioButton();
        radyoD��mesi5.setText ("JPEG resim dosyas� ikonu");
        radyoD��mesi5.setActionCommand (DOSYADAN_�KON);
        radyoD��mesi5.addActionListener (this);
        d��meGrubu2.add (radyoD��mesi5);

        JRadioButton radyoD��mesi6 = new JRadioButton();
        radyoD��mesi6.setText ("Boyal� grafiksel ikon");
        radyoD��mesi6.setActionCommand (BOYAMA_�KON);
        radyoD��mesi6.addActionListener (this);
        d��meGrubu2.add (radyoD��mesi6);

        // Etiket ve d��meleri bir i�erik kutusuna ekleyelim...
        Box i�erikKutusu = Box.createVerticalBox();
        i�erikKutusu.add (etiket1);
        i�erikKutusu.add (Box.createVerticalStrut (5)); // Etiket1'le ilk radyo d��mesi aras� dikey bo�luk...
        i�erikKutusu.add (radyoD��mesi1);
        i�erikKutusu.add (radyoD��mesi2);
        i�erikKutusu.add (radyoD��mesi3);

        i�erikKutusu.add (Box.createVerticalStrut (15)); // 3.radyo d��mesiyle etiket2 aras� dikey bo�luk...
        i�erikKutusu.add (etiket2);
        i�erikKutusu.add (Box.createVerticalStrut (5)); // Etiket2'le 4.radyo d��mesi aras� dikey bo�luk...
        i�erikKutusu.add (radyoD��mesi4);
        i�erikKutusu.add (radyoD��mesi5);
        i�erikKutusu.add (radyoD��mesi6);

        radyoD��mesi1.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoD��mesi2.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoD��mesi3.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoD��mesi4.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoD��mesi5.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoD��mesi6.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // ��erik kutusuna �epe�evre soluklanma bo�lu�u b�rakal�m...
        i�erikKutusu.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        return i�erikKutusu;
    } // tercihKontrolleriniYarat() metodu sonu...

    // Ana pencere alt�ndaki butonu yarat�p dinleyiciye duyarlay�p bir panoya ekleyelim...
    protected JComponent d��mePanosunuYarat() {
        JButton d��me = new JButton ("Yeni Pencere");
        d��me.setActionCommand (PENCERE_YARAT);
        d��me.addActionListener (this); // Dinleyiciye duyarl�...
        varsay�l�D��me = d��me; // yaratVeG�sterGUI()'de �er�eve'nin varsay�l� d��mesi yap�lmakta...

        // D��memizi etraf� biraz bo�luklu bir pano'ya ortalay�p ekleyelim...
        JPanel pano = new JPanel(); // Varsay�l� FlowLayout yerle�im...
        pano.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        pano.add (d��me);
        pano.setBackground (new Color ((int)(Math.random()*150), (int)(Math.random()*150), (int)(Math.random()*150)));

        return pano;
    } // d��mePanosunuYarat() metodu sonu...

    // Buton ve s�s-ikon tercihlerini dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        // "Yeni pencere" d��mesini y�netelim...
        if (PENCERE_YARAT.equals (komut)) {
            yeniPencereyiG�ster();

        // S�s etiketli radyo d��meleri ilk gubunu y�netelim...
        }else if (BH_S�S�.equals (komut)) {
            s�ss�zM� = false;
            JFrame.setDefaultLookAndFeelDecorated (true);
        }else if (PS_S�S�.equals (komut)) {
            s�ss�zM� = false;
            JFrame.setDefaultLookAndFeelDecorated (false);
        }else if (S�SS�Z.equals (komut)) {
            s�ss�zM� = true; // Yeni pencere �st sat�r s�ss�z (ikon, ba�l�k, k���lt, b�y�t, kapat yok) bo� olacak...
            JFrame.setDefaultLookAndFeelDecorated (false);

        // S�s etiketli radyo d��meleri ikinci gubunu y�netelim...
        }else if (VARSAYILI_�KON.equals (komut)) {
            ikonBelirlensinMi = false;
        }else if (DOSYADAN_�KON.equals (komut)) {
            ikonBelirlensinMi = true;
            grafik�konuYarat�ls�nM� = false;
        }else if (BOYAMA_�KON.equals (komut)) {
            ikonBelirlensinMi = true;
            grafik�konuYarat�ls�nM� = true;
        } // Ard���k 7 if-else.. karar� blo�u sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    // Yeni bir �er�evem nesnesi yarat�p g�sterelim...
    public void yeniPencereyiG�ster() {
        JFrame �er�eve = new �er�evem();

        // �zellikle (g�steri ama�l�) gerekmedikce s�ss�z pencere sunulmaz...
        if (s�ss�zM�) �er�eve.setUndecorated (true);

        // Yeni pencerenin konumu bir�ncekinin 40-65 alt-sa��nda olacak...
        // Ayr�ca ekran sonunda tekrar ekran�n sol-�st�ne gelecek...
        if (sonKonum != null) {
            sonKonum.translate (65, 40);
            if ((sonKonum.x > b�y�kX) || (sonKonum.y > b�y�kY)) sonKonum.setLocation (0, 0);
            �er�eve.setLocation (sonKonum);
        }else sonKonum = �er�eve.getLocation();

        // Pencere alta k���lt�ld���nde normalen atanm�� ikon g�r�nmelidir.
        // Ancak �o�u pencere sistemlerinde java kahve ikonu g�r�n�r...
        if (ikonBelirlensinMi) {
            if (grafik�konuYarat�ls�nM�) �er�eve.setIconImage (grafik�konuYarat());
            else �er�eve.setIconImage (dosya�konuAl());
        } // d��-if karar� sonu...

        // Yeni k���k s�s ve ikon tercihli penceremisi g�r�n�r k�lal�m...
        �er�eve.setSize (new Dimension (170, 100));
        �er�eve.setVisible (true);
        �er�eve.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
    } // yeniPencereyiG�ster() metodu sonu...

    // �kon de�erinde boyal� bir resim grafi�i tasla�� yarat�r...
    protected static Image grafik�konuYarat() {
        // 16x16 piksel/noktal�k bir resim yaratal�m...
        BufferedImage resim�konu = new BufferedImage (16, 16, BufferedImage.TYPE_INT_RGB);

        // Siyah �er�eve i�i k�rm�z� oval yapal�m...
        Graphics gr = resim�konu.getGraphics();
        gr.setColor (Color.BLACK);
        gr.fillRect (0, 0, 15, 15);
        gr.setColor (Color.RED);
        gr.fillOval (5, 3, 6, 6);

        // Grafik nesnesini temizleyelim...
        gr.dispose();

        return resim�konu;
    } // grafik�konuYarat() metodu sonu...

    // Bir resim ikonu veya null d�nd�r�r...
    protected static Image dosya�konuAl() {
        java.net.URL resimYureli = J5c_19.class.getResource ("resim/FD.jpg");
        if (resimYureli != null) return new ImageIcon (resimYureli).getImage();
        else return null;
    } // dosya�konuAl() metodu sonu...

    class �er�evem extends JFrame implements ActionListener {
        // "Pencereyi kapa" d��mesine duyarl� yeni bir pencere yarat�r...
        public �er�evem() {// Kurucu...
            super ("Bir pencere");
            setDefaultCloseOperation (DISPOSE_ON_CLOSE); // Sa�-�st kapat/X d��mesi...

            // Bu d��me s�ss�z bir pencereyi bile kapat�r...
            JButton d��me = new JButton ("Pencereyi kapat");
            d��me.addActionListener (this);

            // D��memizi i�erik panosuyla pencerenin alt-ortas�na serimleyelim...
            Container i�erikPanosu = getContentPane();
            i�erikPanosu.setLayout (new BoxLayout (i�erikPanosu, BoxLayout.PAGE_AXIS));
            i�erikPanosu.add (Box.createVerticalGlue());
            i�erikPanosu.add (d��me);
            d��me.setAlignmentX (Component.CENTER_ALIGNMENT);
            i�erikPanosu.add (Box.createVerticalStrut (5)); // D��meyle dip aras� bo�luk...
            i�erikPanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
        } // �er�evem() kurucusu sonu...

        // Buton kapatmas�, varsay�l� (DISPOSE_ON_CLOSE) gibi olsun...
        public void actionPerformed (ActionEvent olay) {
            setVisible (false);
            dispose();
        } // actionPerformed(..) metodu sonu...
    } // �er�evem s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        // javax.swing.UIManager ile "g�r ve hisset"i kullanal�m...
        try {UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception ist) {}

        // Pencerelerimizdeki sevimli "bak ve hisset"i true'yla garantileyelim...
        JFrame.setDefaultLookAndFeelDecorated (true);
        JDialog.setDefaultLookAndFeelDecorated (true);

        // Ana penceremizi yaratal�m...
        JFrame �er�eve = new JFrame ("Pencere G�sterimi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Bu s�n�f nesnesiyle i�erik panosunu yarat�p kural�m...
        J5c_19 g�sterim = new J5c_19();

        // Komponentleri (s�s ve ikon tercihleri radyo butonlar�, yeni pencere butonu) ekleyelim...
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.add (g�sterim.tercihKontrolleriniYarat(), BorderLayout.CENTER);
        i�erikPanosu.add (g�sterim.d��mePanosunuYarat(), BorderLayout.PAGE_END);
        �er�eve.getRootPane().setDefaultButton (varsay�l�D��me);
        i�erikPanosu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // Pencereyi paketleyip g�sterelim...
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null); // Kurucunun ilk de�erleriyle ortalan�r...
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_19 s�n�f� sonu...