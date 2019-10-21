// J5c_12.java: DialogDemo (DiyalogGösterimi) örneði.

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JFrame;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Gereken dosyalar:
 * J5c_12x.java/CustomDialog.java
 *   resim/orta.gif
 */
public class J5c_12 extends JPanel {
    JLabel etiket;
    ImageIcon ikon = resimÝkonuYarat ("resim/orta.gif");
    JFrame çerçeve;
    String basitDiyalogÝzahý = "4 adet basit mesaj diyaloglarý";
    String dahaçokDiyalogÝzahý = "5 adet daha mesaj diyaloglarý";
    String ikonÝzahý = "JOptionPane'in 6 farklý ikonu";
    J5c_12x gelenekselDiyalog;

    // Kurucu çerçevenin içerik panosunda gösterilecek olan GUI'yi yaratýr...
    public J5c_12 (JFrame çerçeve) {// Kurucu...
        super (new BorderLayout());
        this.çerçeve = çerçeve;
        gelenekselDiyalog = new J5c_12x (çerçeve, "yavaþ", this);
        gelenekselDiyalog.pack();

        // Komponentleri yaratalým...
        JPanel basitPaneli = basitDiyalogKutusunuYarat();
        JPanel dahaçokPaneli = dahaçokDiyalogKutusunuYarat();
        JPanel ikonPaneli = ikonDiyalogKutusunuYarat();
        etiket = new JLabel ("Seçili dayaloðu getirmek için  \"Onu Göster!\" düðmesini TIKLA.", JLabel.CENTER);

        // Yaratýlan komponentleri yerleþtirelim...
        Border içBoþluk = BorderFactory.createEmptyBorder (20,20,5,20); // üst,sað,alt,sol...
        basitPaneli.setBorder (içBoþluk);
        dahaçokPaneli.setBorder (içBoþluk);
        ikonPaneli.setBorder (içBoþluk);

        JTabbedPane fiþliPano = new JTabbedPane();
        fiþliPano.addTab ("Basit Diyaloglar", null, basitPaneli, basitDiyalogÝzahý);
        fiþliPano.addTab ("Dahaçok Diyaloglar", null, dahaçokPaneli, dahaçokDiyalogÝzahý);
        fiþliPano.addTab ("Diyalog Ýkonlarý", null, ikonPaneli, ikonÝzahý);

        add (fiþliPano, BorderLayout.CENTER);
        add (etiket, BorderLayout.PAGE_END);
        etiket.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5c_12(..) kurucusu sonu...

    // Çerçeve altýnda gösterilen etiket metnini deðiþtirir...
    void etiketiKoy (String yeniMetin) {etiket.setText (yeniMetin);}

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_12.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    // Ýlk fiþle göstericek olan JOptionPane'li paneli yaratalým...
    private JPanel basitDiyalogKutusunuYarat() {
        final int butonSayýsý = 4;
        JRadioButton[] radyoButonlarý = new JRadioButton[butonSayýsý];
        final ButtonGroup grup = new ButtonGroup();

        JButton onuGösterButonu = null;

        final String varsayýlýMesajKomutu = "varsayýlý";
        final String evetHayýrKomutu = "evethayýr";
        final String heYoðKomutu = "heyoð";
        final String ehiKomutu = "ehi"; //EvetHayýrÝptal...

        radyoButonlarý[0] = new JRadioButton ("OK (sokak diliyle)");
        radyoButonlarý[0].setActionCommand (varsayýlýMesajKomutu);

        radyoButonlarý[1] = new JRadioButton ("Yes/No (sokak diliyle)");
        radyoButonlarý[1].setActionCommand (evetHayýrKomutu);

        radyoButonlarý[2] = new JRadioButton ("Yes/No (programcý diliyle)");
        radyoButonlarý[2].setActionCommand (heYoðKomutu);

        radyoButonlarý[3] = new JRadioButton ("Yes/No/Cancel (programcý diliyle)");
        radyoButonlarý[3].setActionCommand (ehiKomutu);

        for (int i = 0; i < butonSayýsý; i++) grup.add(radyoButonlarý[i]);

        radyoButonlarý[0].setSelected (true);

        onuGösterButonu = new JButton ("Göster onu!");

        onuGösterButonu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // OK diyaloðu (sokak diliyle)...
                if (komut == varsayýlýMesajKomutu) JOptionPane.showMessageDialog (çerçeve, "Yumurtalarýn yeþil olmalarý gerekmez.");

                // Yes/No diyaloðu (sokak diliyle)...
                else if (komut == evetHayýrKomutu) {
                    int n = JOptionPane.showConfirmDialog (çerçeve, "Yeþil yumurtalar ve jambon ister miydiniz?", "Tuhaf Bir Soru", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) etiketiKoy ("Ýðrennnnç!");
                    else if (n == JOptionPane.NO_OPTION)  etiketiKoy ("Ben almayayým!");
                    else etiketiKoy ("Hadi ama -- söyle bana!");

                // Yes/No (programcý diliyle)...
                }else if (komut == heYoðKomutu) {
                    Object[] seçenekler = {"Evet, lütfen", "Söz konusu olamaz!"};
                    int n = JOptionPane.showOptionDialog (çerçeve, "Yeþil yumurtalar ve jambon ister miydiniz?", "Aptalca Bir Soru", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, seçenekler, seçenekler[0]);
                    if (n == JOptionPane.YES_OPTION)  etiketiKoy ("Þaka yapýyorsun!");
                    else if (n == JOptionPane.NO_OPTION) etiketiKoy ("Zaten ben de sevmiyordum.");
                    else etiketiKoy ("Hadi ama -- býktýrdýn!");

                // Yes/No/Cancel (programcý diliyle)
                }else if (komut == ehiKomutu) {
                    Object[] seçenekler = {"Evet, lütfen", "Hayýr, teþekkürler", "Yumurta yok, jambon yok!"};
                    int n = JOptionPane.showOptionDialog (çerçeve, "Jambonla beraber birkaç yeþil yumurta alýr mýydýnýz?", "Aptalca Bir Soru", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, seçenekler, seçenekler[2]);
                    if (n == JOptionPane.YES_OPTION)  etiketiKoy ("Ýþte geldi: yeþil yumurtalar ve jambonunuz!");
                    else if (n == JOptionPane.NO_OPTION) etiketiKoy ("TAMAM, sadece jambon, o halde.");
                    else if (n == JOptionPane.CANCEL_OPTION) etiketiKoy ("Ehm, ben de onlarý kesinlikle yemezdim!");
                    else etiketiKoy ("Lütfen ama, ne istediðini söyle bana!");
                } // if-else kararý sonu...
                return;
        }}); // onu.. ifadesi sonu...

        return panoyuYarat (basitDiyalogÝzahý + ":", radyoButonlarý, onuGösterButonu);
    } // basitDiyalogKutusunuYarat() metodu sonu...

    // Ýkinci fiþle gösterilecek olan JOptionPane.showInputDialog'lu paneli yaratalým...
    private JPanel dahaçokDiyalogKutusunuYarat() {
        final int butonSayýsý = 5;
        JRadioButton[] radyoButonlarý = new JRadioButton[butonSayýsý];
        final ButtonGroup grup = new ButtonGroup();

        JButton onuGösterButonu = null;

        final String biriniSeçKomutu = "biriniseç";
        final String metinGiriþiKomutu = "metinsatýrý";
        final String otomotiksizKomut = "otomotiksiztercih";
        final String gelenekselTercihKomutu = "gelenekseltercih";
        final String modülersizKomut = "modülersiz";

        radyoButonlarý[0] = new JRadioButton ("Tercihlerden birini alýn");
        radyoButonlarý[0].setActionCommand (biriniSeçKomutu);

        radyoButonlarý[1] = new JRadioButton ("Tercihinizi kendiniz yazýn");
        radyoButonlarý[1].setActionCommand (metinGiriþiKomutu);

        radyoButonlarý[2] = new JRadioButton ("Otomatik-kapanmayan diyalog");
        radyoButonlarý[2].setActionCommand (otomotiksizKomut);

        radyoButonlarý[3] = new JRadioButton ("Girilecek cevabý geçerlileme diyaloðu");
        radyoButonlarý[3].setActionCommand (gelenekselTercihKomutu);

        radyoButonlarý[4] = new JRadioButton ("Modüler-olmayan diyalog");
        radyoButonlarý[4].setActionCommand (modülersizKomut);

        for (int i = 0; i < butonSayýsý; i++) grup.add (radyoButonlarý[i]);

        radyoButonlarý[0].setSelected (true);

        onuGösterButonu = new JButton ("Göster onu!");

        onuGösterButonu.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // Tercihlerden birini alýn...
                if (komut == biriniSeçKomutu) {
                    Object[] ihtimaller = {"jambon", "dil peyniri", "tatlý patates"};
                    String dizge = (String)JOptionPane.showInputDialog (çerçeve, "Cümleyi tamamla:\n\"Yeþil yumurtalar ve...\"", "Geleneksel Diyalog", JOptionPane.PLAIN_MESSAGE, ikon, ihtimaller, "jambon");

                    // Eðer seçilen bir dizge döndürüldüyse onu ekle...
                    if ((dizge != null) && (dizge.length() > 0)) {etiketiKoy ("Yeþil yumurtalar ve... " + dizge + "!"); return;}

                    // Eðer dönen null veya iptalse...
                    etiketiKoy ("Hadi ama, bir tercihle cümleyi tamamlayýverseydin!");

                // Tercihinizi kendiniz yazýn
                }else if (komut == metinGiriþiKomutu) {
                    String dizge = (String)JOptionPane.showInputDialog (çerçeve, "Cümleyi tamamla:\n\"Yeþil yumurtalar ve...\"", "Geleneksel Diyalog", JOptionPane.PLAIN_MESSAGE, ikon, null, "jambon");

                    // Eðer girilen bir dizge döndürüldüyse onu ekle...
                    if ((dizge != null) && (dizge.length() > 0)) {etiketiKoy ("Yeþil yumurtalar ve... " + dizge + "!"); return;}

                    // Eðer dönen null veya iptalse...
                    etiketiKoy ("Hadi ama, bir giriþle cümleyi tamamlayýverseydin!");

                // Otomatik-kapanmayan diyalog...
                }else if (komut == otomotiksizKomut) {
                    final JOptionPane tercihPanosu = new JOptionPane ("Bu diyaloðu kapatmanýn tek yolu aþaðýdaki düðmelerden birine basmaktýr.\nAnladýn mý?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
                    final JDialog diyalog = new JDialog (çerçeve, "Bir düðmeyi týklayýn", true);
                    diyalog.setContentPane (tercihPanosu);
                    diyalog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);

                    diyalog.addWindowListener (new WindowAdapter() {
                        public void windowClosing (WindowEvent olay) {
                            etiketiKoy ("Beyhuda pencereyi cevapsýz kapatmaya uðraþma.");
                    }}); // diy.. ifadesi sonu...

                    tercihPanosu.addPropertyChangeListener (
                        new PropertyChangeListener() {
                            public void propertyChange (PropertyChangeEvent olay) {
                                String özellik = olay.getPropertyName();
                                if (diyalog.isVisible() && (olay.getSource() == tercihPanosu) && (JOptionPane.VALUE_PROPERTY.equals (özellik))) diyalog.setVisible (false);
                    }}); // ter.. ifadesi sonu...

                    diyalog.pack();
                    diyalog.setLocationRelativeTo (çerçeve);
                    diyalog.setVisible (true);

                    int deðer = ((Integer)tercihPanosu.getValue()).intValue();
                    if (deðer == JOptionPane.YES_OPTION) etiketiKoy ("Aferin.");
                    else if (deðer == JOptionPane.NO_OPTION)  etiketiKoy ("Anlamadýysan, tekrar bu yazýyý oku ve 'Evet anladým'a bas!");
                    else etiketiKoy ("Pencere kaçýnýlamazcasýna kapatýldý (ESC?).");

                // Girilecek cevabý geçerlileme diyaloðu...
                }else if (komut == gelenekselTercihKomutu) {
                    gelenekselDiyalog.setLocationRelativeTo (çerçeve);
                    gelenekselDiyalog.setVisible (true);

                    String dizge = gelenekselDiyalog.geçerliMetniAl();
                    if (dizge != null) /* Geçerliyse */ etiketiKoy ("Tebrikler!  Girdiðiniz doðru cevap \""+ dizge + "\".");

                // Modüler-olmayan diyalog...
                }else if (komut == modülersizKomut) {
                    final JDialog diyalog = new JDialog (çerçeve, "Modüler-Olmayan Bir Diyalog");

                    JLabel etiket = new JLabel ("<html><p align=center>Bu bir modüler-olmayan diyalog'dur.<br>Bunlardan bir veya daha çoðunu kullanabilir<br>ve hala ana pencereyi kullanabilirsiniz.");
                    etiket.setHorizontalAlignment (JLabel.CENTER);
                    Font fon = etiket.getFont();
                    etiket.setFont (etiket.getFont().deriveFont (fon.PLAIN, 14.0f));

                    JButton kapatmaDüðmesi = new JButton("Close");

                    kapatmaDüðmesi.addActionListener (new ActionListener() {
                        public void actionPerformed (ActionEvent olay) {
                            diyalog.setVisible (false);
                            diyalog.dispose();
                    }}); // kap.. ifadesi sonu...

                    JPanel kapatmaPaneli = new JPanel();
                    kapatmaPaneli.setLayout (new BoxLayout (kapatmaPaneli, BoxLayout.LINE_AXIS));
                    kapatmaPaneli.add (Box.createHorizontalGlue());
                    kapatmaPaneli.add (kapatmaDüðmesi);
                    kapatmaPaneli.setBorder (BorderFactory. createEmptyBorder (0,0,5,5));

                    JPanel içerikPanosu = new JPanel (new BorderLayout());
                    içerikPanosu.add (etiket, BorderLayout.CENTER);
                    içerikPanosu.add (kapatmaPaneli, BorderLayout.PAGE_END);
                    içerikPanosu.setOpaque (true);
                    diyalog.setContentPane (içerikPanosu);

                    diyalog.setSize (new Dimension (300, 150));
                    diyalog.setLocationRelativeTo (çerçeve);
                    diyalog.setVisible (true);
                } // if-else kararý sonu...
        }}); // onu.. ifadesi sonu...

        return panoyuYarat (dahaçokDiyalogÝzahý + ":", radyoButonlarý, onuGösterButonu);
    } // dahaçokDiyalogKutusunuYarat() metodu sonu...

    // 3.fiþle gösterilecek olan JOptionPane.showMessageDialog'lu ikon panelini yaratalým...
    private JPanel ikonDiyalogKutusunuYarat() {
        JButton onuGösterButonu = null;

        final int butonSayýsý = 6;
        JRadioButton[] radyoButonlarý = new JRadioButton[butonSayýsý];
        final ButtonGroup grup = new ButtonGroup();

        final String sadeKomutu = "sade";
        final String bilgiKomutu = "bilgi";
        final String soruKomutu = "soru";
        final String hataKomutu = "hata";
        final String ikazKomutu = "ikaz";
        final String gelenekselKomut = "geleneksel";

        radyoButonlarý[0] = new JRadioButton ("Sade (ikonsuz)");
        radyoButonlarý[0].setActionCommand (sadeKomutu);

        radyoButonlarý[1] = new JRadioButton ("Bilgi ikonu");
        radyoButonlarý[1].setActionCommand (bilgiKomutu);

        radyoButonlarý[2] = new JRadioButton ("Soru ikonu");
        radyoButonlarý[2].setActionCommand (soruKomutu);

        radyoButonlarý[3] = new JRadioButton ("Hata ikonu");
        radyoButonlarý[3].setActionCommand (hataKomutu);

        radyoButonlarý[4] = new JRadioButton ("Ýkaz ikonu");
        radyoButonlarý[4].setActionCommand (ikazKomutu);

        radyoButonlarý[5] = new JRadioButton ("Geleneksel ikon");
        radyoButonlarý[5].setActionCommand (gelenekselKomut);

        for (int i = 0; i < butonSayýsý; i++) grup.add (radyoButonlarý[i]);

        radyoButonlarý[0].setSelected (true);

        onuGösterButonu = new JButton ("Göster onu!");

        onuGösterButonu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // Sade (ikonsuz)...
                if (komut == sadeKomutu) JOptionPane.showMessageDialog (çerçeve, "Yumurtalarýn yeþil olmalarý gerekmiyor.", "Sade bir mesaj", JOptionPane.PLAIN_MESSAGE);

                // Bilgi ikonu...
                else if (komut == bilgiKomutu) JOptionPane.showMessageDialog (çerçeve, "Yumurtalarýn yeþil olmalarý gerekmiyor.", "Tuhaf bir bilgisel diyalog", JOptionPane.INFORMATION_MESSAGE);

                // Soru ikonu...
                else if (komut == soruKomutu) JOptionPane.showMessageDialog (çerçeve, "Bir soru için böylesi bir mesaj diyaloðu\nkullanmamalýsýn, TAMAM MI?", "Gülünç bir soru", JOptionPane.QUESTION_MESSAGE);

                // Hata ikonu...
                else if (komut == hataKomutu) JOptionPane.showMessageDialog (çerçeve, "Yumurtalarýn yeþil olmalarý gerekmiyor.", "Saçma bir hata", JOptionPane.ERROR_MESSAGE);

                // Ýkaz ikonu...
                else if (komut == ikazKomutu) JOptionPane.showMessageDialog (çerçeve,"Yumurtalarýn yeþil olmalarý þart deðil.", "Amaçsýz bir ikaz", JOptionPane.WARNING_MESSAGE);

                // Geleneksel ikon...
                else if (komut == gelenekselKomut) JOptionPane.showMessageDialog (çerçeve, "Yumurtalarýn yeþil olmalarý gerekmiyor.", "Gülünç bir geleneksel diyalog", JOptionPane.INFORMATION_MESSAGE, ikon);
        }}); // onu.. ifadesi sonu...

        return ikiKolonluPanoyuYarat (ikonÝzahý + ":", radyoButonlarý, onuGösterButonu);
    } // ikonDiyalogKutusunuYarat() metodu sonu...

    // basitDiyalogKutusunuYarat() ve dahaçokDiyalogKutusunuYarat() metodlarýnca kullanýlacak olan
    // bir açýklama, tek sütunluk radyo butonlarý ve "Göster onu!" düðmesini içeren bir pano yaratýr...
    private JPanel panoyuYarat (String açýklama, JRadioButton[] radyoButonlarý, JButton gösterButonu) {
        int tercihSayýsý = radyoButonlarý.length;
        JPanel kutu = new JPanel();
        JLabel etiket = new JLabel (açýklama);

        kutu.setLayout (new BoxLayout (kutu, BoxLayout.PAGE_AXIS));
        kutu.add (etiket);

        for (int i = 0; i < tercihSayýsý; i++) kutu.add (radyoButonlarý[i]);

        JPanel pano = new JPanel (new BorderLayout());
        pano.add (kutu, BorderLayout.PAGE_START);
        pano.add (gösterButonu, BorderLayout.PAGE_END);
        return pano;
    } // panoyuYarat(..) metodu sonu...

     private JPanel ikiKolonluPanoyuYarat (String açýklama, JRadioButton[] radyoButonlarý, JButton gösterButonu) {
        JLabel etiket = new JLabel (açýklama);
        int herbirSütundakiRadyo = radyoButonlarý.length / 2;

        JPanel ýzgara = new JPanel (new GridLayout (0, 2));
        for (int i = 0; i < herbirSütundakiRadyo; i++) {
            ýzgara.add (radyoButonlarý[i]);
            ýzgara.add (radyoButonlarý[i + herbirSütundakiRadyo]);
        } // for döngüsü sonu...

        JPanel kutu = new JPanel();
        kutu.setLayout (new BoxLayout (kutu, BoxLayout.PAGE_AXIS));
        kutu.add (etiket);
        ýzgara.setAlignmentX (0.0f);
        kutu.add (ýzgara);

        JPanel pano = new JPanel (new BorderLayout());
        pano.add (kutu, BorderLayout.PAGE_START);
        pano.add (gösterButonu, BorderLayout.PAGE_END);

        return pano;
    } // ikiKolonluPanoyuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Diyalog Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_12 yeniÝçerikPanosu = new J5c_12 (çerçeve);
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_12 sýnýfý sonu...