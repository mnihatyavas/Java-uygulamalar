// J5c_19.java: FrameDemo2 (ÇerçeveGösterimi2) örneði.

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

// resim/FD.jpg dosyasýný kullanmaktadýr...
public class J5c_19 extends WindowAdapter implements ActionListener {
    private Point sonKonum = null;
    private int büyükX = 500;
    private int büyükY = 500;

    // Ana pencerenin varsayýlý düðmesi...
    private static JButton varsayýlýDüðme = null;

    // Aksiyon komutlarý için sabit dizgeler...
    protected final static String SÜSSÜZ = "süssüz";
    protected final static String BH_SÜSÜ = "bvh_süsü";
    protected final static String PS_SÜSÜ = "ps_süsü";
    protected final static String PENCERE_YARAT = "yeni_pencere";
    protected final static String VARSAYILI_ÝKON = "varsayýlý_ikon";
    protected final static String DOSYADAN_ÝKON = "dosya_ikonu";
    protected final static String BOYAMA_ÝKON = "grafik_ikonu";

    // Yaratýlacak birsonraki pencere süssüz olacaksa "true"...
    protected boolean süssüzMü = false;

    // Yaratýlacak birsonraki pencere setIconImage/resimÝkonunuKoy'u çaðýracaksa "true"...
    protected boolean ikonBelirlensinMi = false;

    // Yaratýlacak birsonraki pencere grafikÝkonuYarat'ý çaðýracaksa "true"...
    protected boolean grafikÝkonuYaratýlsýnMý = false;

    // Kurucuyla bazý ilk-deðerler atayalým...
    public J5c_19() {
        Dimension ekranEbatý = Toolkit.getDefaultToolkit().getScreenSize();
        büyükX = ekranEbatý.width - 50;
        büyükY = ekranEbatý.height - 50;
    } // J5c_19() kurucusu sonu...

    // Ana penceredeki yeni pencere yaratma kontrollerini yaratalým...
    protected JComponent tercihKontrolleriniYarat() {
        JLabel etiket1 = new JLabel ("Ardýþýk yaratýlacak pencereler için süs tercihleri:");
        ButtonGroup düðmeGrubu1 = new ButtonGroup();
        JLabel etiket2 = new JLabel ("Ýkon tercihleri:");
        ButtonGroup düðmeGrubu2 = new ButtonGroup();

        // 6 adet radyo butonunu yaratýp süs ve ikon grubuna kuralým...
        // Süs grubu...
        JRadioButton radyoDüðmesi1 = new JRadioButton();
        radyoDüðmesi1.setText ("Bak ve hisset süslü");
        radyoDüðmesi1.setActionCommand (BH_SÜSÜ);
        radyoDüðmesi1.addActionListener (this); // Dinleyiciye duyarlý kýlalým...
        radyoDüðmesi1.setSelected (true); // Süs grubunda ilk anda bu seçili...
        düðmeGrubu1.add (radyoDüðmesi1);

        JRadioButton radyoDüðmesi2 = new JRadioButton();
        radyoDüðmesi2.setText ("Pencere sistemi süslü");
        radyoDüðmesi2.setActionCommand (PS_SÜSÜ);
        radyoDüðmesi2.addActionListener (this);
        düðmeGrubu1.add (radyoDüðmesi2);

        JRadioButton radyoDüðmesi3 = new JRadioButton();
        radyoDüðmesi3.setText ("Süssüz");
        radyoDüðmesi3.setActionCommand (SÜSSÜZ);
        radyoDüðmesi3.addActionListener (this);
        düðmeGrubu1.add (radyoDüðmesi3);

        // Ýkon grubu...
        JRadioButton radyoDüðmesi4 = new JRadioButton();
        radyoDüðmesi4.setText ("Varsayýlý ikon");
        radyoDüðmesi4.setActionCommand (VARSAYILI_ÝKON);
        radyoDüðmesi4.addActionListener (this);
        radyoDüðmesi4.setSelected (true); // Ýkon grubunda ilk anda bu seçili...
        düðmeGrubu2.add (radyoDüðmesi4);

        JRadioButton radyoDüðmesi5 = new JRadioButton();
        radyoDüðmesi5.setText ("JPEG resim dosyasý ikonu");
        radyoDüðmesi5.setActionCommand (DOSYADAN_ÝKON);
        radyoDüðmesi5.addActionListener (this);
        düðmeGrubu2.add (radyoDüðmesi5);

        JRadioButton radyoDüðmesi6 = new JRadioButton();
        radyoDüðmesi6.setText ("Boyalý grafiksel ikon");
        radyoDüðmesi6.setActionCommand (BOYAMA_ÝKON);
        radyoDüðmesi6.addActionListener (this);
        düðmeGrubu2.add (radyoDüðmesi6);

        // Etiket ve düðmeleri bir içerik kutusuna ekleyelim...
        Box içerikKutusu = Box.createVerticalBox();
        içerikKutusu.add (etiket1);
        içerikKutusu.add (Box.createVerticalStrut (5)); // Etiket1'le ilk radyo düðmesi arasý dikey boþluk...
        içerikKutusu.add (radyoDüðmesi1);
        içerikKutusu.add (radyoDüðmesi2);
        içerikKutusu.add (radyoDüðmesi3);

        içerikKutusu.add (Box.createVerticalStrut (15)); // 3.radyo düðmesiyle etiket2 arasý dikey boþluk...
        içerikKutusu.add (etiket2);
        içerikKutusu.add (Box.createVerticalStrut (5)); // Etiket2'le 4.radyo düðmesi arasý dikey boþluk...
        içerikKutusu.add (radyoDüðmesi4);
        içerikKutusu.add (radyoDüðmesi5);
        içerikKutusu.add (radyoDüðmesi6);

        radyoDüðmesi1.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoDüðmesi2.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoDüðmesi3.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoDüðmesi4.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoDüðmesi5.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));
        radyoDüðmesi6.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // Ýçerik kutusuna çepeçevre soluklanma boþluðu býrakalým...
        içerikKutusu.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        return içerikKutusu;
    } // tercihKontrolleriniYarat() metodu sonu...

    // Ana pencere altýndaki butonu yaratýp dinleyiciye duyarlayýp bir panoya ekleyelim...
    protected JComponent düðmePanosunuYarat() {
        JButton düðme = new JButton ("Yeni Pencere");
        düðme.setActionCommand (PENCERE_YARAT);
        düðme.addActionListener (this); // Dinleyiciye duyarlý...
        varsayýlýDüðme = düðme; // yaratVeGösterGUI()'de çerçeve'nin varsayýlý düðmesi yapýlmakta...

        // Düðmemizi etrafý biraz boþluklu bir pano'ya ortalayýp ekleyelim...
        JPanel pano = new JPanel(); // Varsayýlý FlowLayout yerleþim...
        pano.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        pano.add (düðme);
        pano.setBackground (new Color ((int)(Math.random()*150), (int)(Math.random()*150), (int)(Math.random()*150)));

        return pano;
    } // düðmePanosunuYarat() metodu sonu...

    // Buton ve süs-ikon tercihlerini dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        // "Yeni pencere" düðmesini yönetelim...
        if (PENCERE_YARAT.equals (komut)) {
            yeniPencereyiGöster();

        // Süs etiketli radyo düðmeleri ilk gubunu yönetelim...
        }else if (BH_SÜSÜ.equals (komut)) {
            süssüzMü = false;
            JFrame.setDefaultLookAndFeelDecorated (true);
        }else if (PS_SÜSÜ.equals (komut)) {
            süssüzMü = false;
            JFrame.setDefaultLookAndFeelDecorated (false);
        }else if (SÜSSÜZ.equals (komut)) {
            süssüzMü = true; // Yeni pencere üst satýr süssüz (ikon, baþlýk, küçült, büyüt, kapat yok) boþ olacak...
            JFrame.setDefaultLookAndFeelDecorated (false);

        // Süs etiketli radyo düðmeleri ikinci gubunu yönetelim...
        }else if (VARSAYILI_ÝKON.equals (komut)) {
            ikonBelirlensinMi = false;
        }else if (DOSYADAN_ÝKON.equals (komut)) {
            ikonBelirlensinMi = true;
            grafikÝkonuYaratýlsýnMý = false;
        }else if (BOYAMA_ÝKON.equals (komut)) {
            ikonBelirlensinMi = true;
            grafikÝkonuYaratýlsýnMý = true;
        } // Ardýþýk 7 if-else.. kararý bloðu sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    // Yeni bir Çerçevem nesnesi yaratýp gösterelim...
    public void yeniPencereyiGöster() {
        JFrame çerçeve = new Çerçevem();

        // Özellikle (gösteri amaçlý) gerekmedikce süssüz pencere sunulmaz...
        if (süssüzMü) çerçeve.setUndecorated (true);

        // Yeni pencerenin konumu biröncekinin 40-65 alt-saðýnda olacak...
        // Ayrýca ekran sonunda tekrar ekranýn sol-üstüne gelecek...
        if (sonKonum != null) {
            sonKonum.translate (65, 40);
            if ((sonKonum.x > büyükX) || (sonKonum.y > büyükY)) sonKonum.setLocation (0, 0);
            çerçeve.setLocation (sonKonum);
        }else sonKonum = çerçeve.getLocation();

        // Pencere alta küçültüldüðünde normalen atanmýþ ikon görünmelidir.
        // Ancak çoðu pencere sistemlerinde java kahve ikonu görünür...
        if (ikonBelirlensinMi) {
            if (grafikÝkonuYaratýlsýnMý) çerçeve.setIconImage (grafikÝkonuYarat());
            else çerçeve.setIconImage (dosyaÝkonuAl());
        } // dýþ-if kararý sonu...

        // Yeni küçük süs ve ikon tercihli penceremisi görünür kýlalým...
        çerçeve.setSize (new Dimension (170, 100));
        çerçeve.setVisible (true);
        çerçeve.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
    } // yeniPencereyiGöster() metodu sonu...

    // Ýkon deðerinde boyalý bir resim grafiði taslaðý yaratýr...
    protected static Image grafikÝkonuYarat() {
        // 16x16 piksel/noktalýk bir resim yaratalým...
        BufferedImage resimÝkonu = new BufferedImage (16, 16, BufferedImage.TYPE_INT_RGB);

        // Siyah çerçeve içi kýrmýzý oval yapalým...
        Graphics gr = resimÝkonu.getGraphics();
        gr.setColor (Color.BLACK);
        gr.fillRect (0, 0, 15, 15);
        gr.setColor (Color.RED);
        gr.fillOval (5, 3, 6, 6);

        // Grafik nesnesini temizleyelim...
        gr.dispose();

        return resimÝkonu;
    } // grafikÝkonuYarat() metodu sonu...

    // Bir resim ikonu veya null döndürür...
    protected static Image dosyaÝkonuAl() {
        java.net.URL resimYureli = J5c_19.class.getResource ("resim/FD.jpg");
        if (resimYureli != null) return new ImageIcon (resimYureli).getImage();
        else return null;
    } // dosyaÝkonuAl() metodu sonu...

    class Çerçevem extends JFrame implements ActionListener {
        // "Pencereyi kapa" düðmesine duyarlý yeni bir pencere yaratýr...
        public Çerçevem() {// Kurucu...
            super ("Bir pencere");
            setDefaultCloseOperation (DISPOSE_ON_CLOSE); // Sað-üst kapat/X düðmesi...

            // Bu düðme süssüz bir pencereyi bile kapatýr...
            JButton düðme = new JButton ("Pencereyi kapat");
            düðme.addActionListener (this);

            // Düðmemizi içerik panosuyla pencerenin alt-ortasýna serimleyelim...
            Container içerikPanosu = getContentPane();
            içerikPanosu.setLayout (new BoxLayout (içerikPanosu, BoxLayout.PAGE_AXIS));
            içerikPanosu.add (Box.createVerticalGlue());
            içerikPanosu.add (düðme);
            düðme.setAlignmentX (Component.CENTER_ALIGNMENT);
            içerikPanosu.add (Box.createVerticalStrut (5)); // Düðmeyle dip arasý boþluk...
            içerikPanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)));
        } // Çerçevem() kurucusu sonu...

        // Buton kapatmasý, varsayýlý (DISPOSE_ON_CLOSE) gibi olsun...
        public void actionPerformed (ActionEvent olay) {
            setVisible (false);
            dispose();
        } // actionPerformed(..) metodu sonu...
    } // Çerçevem sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        // javax.swing.UIManager ile "gör ve hisset"i kullanalým...
        try {UIManager.setLookAndFeel (UIManager.getCrossPlatformLookAndFeelClassName());
        }catch (Exception ist) {}

        // Pencerelerimizdeki sevimli "bak ve hisset"i true'yla garantileyelim...
        JFrame.setDefaultLookAndFeelDecorated (true);
        JDialog.setDefaultLookAndFeelDecorated (true);

        // Ana penceremizi yaratalým...
        JFrame çerçeve = new JFrame ("Pencere Gösterimi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Bu sýnýf nesnesiyle içerik panosunu yaratýp kuralým...
        J5c_19 gösterim = new J5c_19();

        // Komponentleri (süs ve ikon tercihleri radyo butonlarý, yeni pencere butonu) ekleyelim...
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.add (gösterim.tercihKontrolleriniYarat(), BorderLayout.CENTER);
        içerikPanosu.add (gösterim.düðmePanosunuYarat(), BorderLayout.PAGE_END);
        çerçeve.getRootPane().setDefaultButton (varsayýlýDüðme);
        içerikPanosu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // Pencereyi paketleyip gösterelim...
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null); // Kurucunun ilk deðerleriyle ortalanýr...
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_19 sýnýfý sonu...