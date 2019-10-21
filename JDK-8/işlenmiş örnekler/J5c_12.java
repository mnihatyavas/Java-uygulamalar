// J5c_12.java: DialogDemo (DiyalogG�sterimi) �rne�i.

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
    ImageIcon ikon = resim�konuYarat ("resim/orta.gif");
    JFrame �er�eve;
    String basitDiyalog�zah� = "4 adet basit mesaj diyaloglar�";
    String daha�okDiyalog�zah� = "5 adet daha mesaj diyaloglar�";
    String ikon�zah� = "JOptionPane'in 6 farkl� ikonu";
    J5c_12x gelenekselDiyalog;

    // Kurucu �er�evenin i�erik panosunda g�sterilecek olan GUI'yi yarat�r...
    public J5c_12 (JFrame �er�eve) {// Kurucu...
        super (new BorderLayout());
        this.�er�eve = �er�eve;
        gelenekselDiyalog = new J5c_12x (�er�eve, "yava�", this);
        gelenekselDiyalog.pack();

        // Komponentleri yaratal�m...
        JPanel basitPaneli = basitDiyalogKutusunuYarat();
        JPanel daha�okPaneli = daha�okDiyalogKutusunuYarat();
        JPanel ikonPaneli = ikonDiyalogKutusunuYarat();
        etiket = new JLabel ("Se�ili dayalo�u getirmek i�in  \"Onu G�ster!\" d��mesini TIKLA.", JLabel.CENTER);

        // Yarat�lan komponentleri yerle�tirelim...
        Border i�Bo�luk = BorderFactory.createEmptyBorder (20,20,5,20); // �st,sa�,alt,sol...
        basitPaneli.setBorder (i�Bo�luk);
        daha�okPaneli.setBorder (i�Bo�luk);
        ikonPaneli.setBorder (i�Bo�luk);

        JTabbedPane fi�liPano = new JTabbedPane();
        fi�liPano.addTab ("Basit Diyaloglar", null, basitPaneli, basitDiyalog�zah�);
        fi�liPano.addTab ("Daha�ok Diyaloglar", null, daha�okPaneli, daha�okDiyalog�zah�);
        fi�liPano.addTab ("Diyalog �konlar�", null, ikonPaneli, ikon�zah�);

        add (fi�liPano, BorderLayout.CENTER);
        add (etiket, BorderLayout.PAGE_END);
        etiket.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5c_12(..) kurucusu sonu...

    // �er�eve alt�nda g�sterilen etiket metnini de�i�tirir...
    void etiketiKoy (String yeniMetin) {etiket.setText (yeniMetin);}

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_12.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    // �lk fi�le g�stericek olan JOptionPane'li paneli yaratal�m...
    private JPanel basitDiyalogKutusunuYarat() {
        final int butonSay�s� = 4;
        JRadioButton[] radyoButonlar� = new JRadioButton[butonSay�s�];
        final ButtonGroup grup = new ButtonGroup();

        JButton onuG�sterButonu = null;

        final String varsay�l�MesajKomutu = "varsay�l�";
        final String evetHay�rKomutu = "evethay�r";
        final String heYo�Komutu = "heyo�";
        final String ehiKomutu = "ehi"; //EvetHay�r�ptal...

        radyoButonlar�[0] = new JRadioButton ("OK (sokak diliyle)");
        radyoButonlar�[0].setActionCommand (varsay�l�MesajKomutu);

        radyoButonlar�[1] = new JRadioButton ("Yes/No (sokak diliyle)");
        radyoButonlar�[1].setActionCommand (evetHay�rKomutu);

        radyoButonlar�[2] = new JRadioButton ("Yes/No (programc� diliyle)");
        radyoButonlar�[2].setActionCommand (heYo�Komutu);

        radyoButonlar�[3] = new JRadioButton ("Yes/No/Cancel (programc� diliyle)");
        radyoButonlar�[3].setActionCommand (ehiKomutu);

        for (int i = 0; i < butonSay�s�; i++) grup.add(radyoButonlar�[i]);

        radyoButonlar�[0].setSelected (true);

        onuG�sterButonu = new JButton ("G�ster onu!");

        onuG�sterButonu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // OK diyalo�u (sokak diliyle)...
                if (komut == varsay�l�MesajKomutu) JOptionPane.showMessageDialog (�er�eve, "Yumurtalar�n ye�il olmalar� gerekmez.");

                // Yes/No diyalo�u (sokak diliyle)...
                else if (komut == evetHay�rKomutu) {
                    int n = JOptionPane.showConfirmDialog (�er�eve, "Ye�il yumurtalar ve jambon ister miydiniz?", "Tuhaf Bir Soru", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) etiketiKoy ("��rennnn�!");
                    else if (n == JOptionPane.NO_OPTION)  etiketiKoy ("Ben almayay�m!");
                    else etiketiKoy ("Hadi ama -- s�yle bana!");

                // Yes/No (programc� diliyle)...
                }else if (komut == heYo�Komutu) {
                    Object[] se�enekler = {"Evet, l�tfen", "S�z konusu olamaz!"};
                    int n = JOptionPane.showOptionDialog (�er�eve, "Ye�il yumurtalar ve jambon ister miydiniz?", "Aptalca Bir Soru", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, se�enekler, se�enekler[0]);
                    if (n == JOptionPane.YES_OPTION)  etiketiKoy ("�aka yap�yorsun!");
                    else if (n == JOptionPane.NO_OPTION) etiketiKoy ("Zaten ben de sevmiyordum.");
                    else etiketiKoy ("Hadi ama -- b�kt�rd�n!");

                // Yes/No/Cancel (programc� diliyle)
                }else if (komut == ehiKomutu) {
                    Object[] se�enekler = {"Evet, l�tfen", "Hay�r, te�ekk�rler", "Yumurta yok, jambon yok!"};
                    int n = JOptionPane.showOptionDialog (�er�eve, "Jambonla beraber birka� ye�il yumurta al�r m�yd�n�z?", "Aptalca Bir Soru", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, se�enekler, se�enekler[2]);
                    if (n == JOptionPane.YES_OPTION)  etiketiKoy ("��te geldi: ye�il yumurtalar ve jambonunuz!");
                    else if (n == JOptionPane.NO_OPTION) etiketiKoy ("TAMAM, sadece jambon, o halde.");
                    else if (n == JOptionPane.CANCEL_OPTION) etiketiKoy ("Ehm, ben de onlar� kesinlikle yemezdim!");
                    else etiketiKoy ("L�tfen ama, ne istedi�ini s�yle bana!");
                } // if-else karar� sonu...
                return;
        }}); // onu.. ifadesi sonu...

        return panoyuYarat (basitDiyalog�zah� + ":", radyoButonlar�, onuG�sterButonu);
    } // basitDiyalogKutusunuYarat() metodu sonu...

    // �kinci fi�le g�sterilecek olan JOptionPane.showInputDialog'lu paneli yaratal�m...
    private JPanel daha�okDiyalogKutusunuYarat() {
        final int butonSay�s� = 5;
        JRadioButton[] radyoButonlar� = new JRadioButton[butonSay�s�];
        final ButtonGroup grup = new ButtonGroup();

        JButton onuG�sterButonu = null;

        final String biriniSe�Komutu = "birinise�";
        final String metinGiri�iKomutu = "metinsat�r�";
        final String otomotiksizKomut = "otomotiksiztercih";
        final String gelenekselTercihKomutu = "gelenekseltercih";
        final String mod�lersizKomut = "mod�lersiz";

        radyoButonlar�[0] = new JRadioButton ("Tercihlerden birini al�n");
        radyoButonlar�[0].setActionCommand (biriniSe�Komutu);

        radyoButonlar�[1] = new JRadioButton ("Tercihinizi kendiniz yaz�n");
        radyoButonlar�[1].setActionCommand (metinGiri�iKomutu);

        radyoButonlar�[2] = new JRadioButton ("Otomatik-kapanmayan diyalog");
        radyoButonlar�[2].setActionCommand (otomotiksizKomut);

        radyoButonlar�[3] = new JRadioButton ("Girilecek cevab� ge�erlileme diyalo�u");
        radyoButonlar�[3].setActionCommand (gelenekselTercihKomutu);

        radyoButonlar�[4] = new JRadioButton ("Mod�ler-olmayan diyalog");
        radyoButonlar�[4].setActionCommand (mod�lersizKomut);

        for (int i = 0; i < butonSay�s�; i++) grup.add (radyoButonlar�[i]);

        radyoButonlar�[0].setSelected (true);

        onuG�sterButonu = new JButton ("G�ster onu!");

        onuG�sterButonu.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // Tercihlerden birini al�n...
                if (komut == biriniSe�Komutu) {
                    Object[] ihtimaller = {"jambon", "dil peyniri", "tatl� patates"};
                    String dizge = (String)JOptionPane.showInputDialog (�er�eve, "C�mleyi tamamla:\n\"Ye�il yumurtalar ve...\"", "Geleneksel Diyalog", JOptionPane.PLAIN_MESSAGE, ikon, ihtimaller, "jambon");

                    // E�er se�ilen bir dizge d�nd�r�ld�yse onu ekle...
                    if ((dizge != null) && (dizge.length() > 0)) {etiketiKoy ("Ye�il yumurtalar ve... " + dizge + "!"); return;}

                    // E�er d�nen null veya iptalse...
                    etiketiKoy ("Hadi ama, bir tercihle c�mleyi tamamlay�verseydin!");

                // Tercihinizi kendiniz yaz�n
                }else if (komut == metinGiri�iKomutu) {
                    String dizge = (String)JOptionPane.showInputDialog (�er�eve, "C�mleyi tamamla:\n\"Ye�il yumurtalar ve...\"", "Geleneksel Diyalog", JOptionPane.PLAIN_MESSAGE, ikon, null, "jambon");

                    // E�er girilen bir dizge d�nd�r�ld�yse onu ekle...
                    if ((dizge != null) && (dizge.length() > 0)) {etiketiKoy ("Ye�il yumurtalar ve... " + dizge + "!"); return;}

                    // E�er d�nen null veya iptalse...
                    etiketiKoy ("Hadi ama, bir giri�le c�mleyi tamamlay�verseydin!");

                // Otomatik-kapanmayan diyalog...
                }else if (komut == otomotiksizKomut) {
                    final JOptionPane tercihPanosu = new JOptionPane ("Bu diyalo�u kapatman�n tek yolu a�a��daki d��melerden birine basmakt�r.\nAnlad�n m�?", JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION);
                    final JDialog diyalog = new JDialog (�er�eve, "Bir d��meyi t�klay�n", true);
                    diyalog.setContentPane (tercihPanosu);
                    diyalog.setDefaultCloseOperation (JDialog.DO_NOTHING_ON_CLOSE);

                    diyalog.addWindowListener (new WindowAdapter() {
                        public void windowClosing (WindowEvent olay) {
                            etiketiKoy ("Beyhuda pencereyi cevaps�z kapatmaya u�ra�ma.");
                    }}); // diy.. ifadesi sonu...

                    tercihPanosu.addPropertyChangeListener (
                        new PropertyChangeListener() {
                            public void propertyChange (PropertyChangeEvent olay) {
                                String �zellik = olay.getPropertyName();
                                if (diyalog.isVisible() && (olay.getSource() == tercihPanosu) && (JOptionPane.VALUE_PROPERTY.equals (�zellik))) diyalog.setVisible (false);
                    }}); // ter.. ifadesi sonu...

                    diyalog.pack();
                    diyalog.setLocationRelativeTo (�er�eve);
                    diyalog.setVisible (true);

                    int de�er = ((Integer)tercihPanosu.getValue()).intValue();
                    if (de�er == JOptionPane.YES_OPTION) etiketiKoy ("Aferin.");
                    else if (de�er == JOptionPane.NO_OPTION)  etiketiKoy ("Anlamad�ysan, tekrar bu yaz�y� oku ve 'Evet anlad�m'a bas!");
                    else etiketiKoy ("Pencere ka��n�lamazcas�na kapat�ld� (ESC?).");

                // Girilecek cevab� ge�erlileme diyalo�u...
                }else if (komut == gelenekselTercihKomutu) {
                    gelenekselDiyalog.setLocationRelativeTo (�er�eve);
                    gelenekselDiyalog.setVisible (true);

                    String dizge = gelenekselDiyalog.ge�erliMetniAl();
                    if (dizge != null) /* Ge�erliyse */ etiketiKoy ("Tebrikler!  Girdi�iniz do�ru cevap \""+ dizge + "\".");

                // Mod�ler-olmayan diyalog...
                }else if (komut == mod�lersizKomut) {
                    final JDialog diyalog = new JDialog (�er�eve, "Mod�ler-Olmayan Bir Diyalog");

                    JLabel etiket = new JLabel ("<html><p align=center>Bu bir mod�ler-olmayan diyalog'dur.<br>Bunlardan bir veya daha �o�unu kullanabilir<br>ve hala ana pencereyi kullanabilirsiniz.");
                    etiket.setHorizontalAlignment (JLabel.CENTER);
                    Font fon = etiket.getFont();
                    etiket.setFont (etiket.getFont().deriveFont (fon.PLAIN, 14.0f));

                    JButton kapatmaD��mesi = new JButton("Close");

                    kapatmaD��mesi.addActionListener (new ActionListener() {
                        public void actionPerformed (ActionEvent olay) {
                            diyalog.setVisible (false);
                            diyalog.dispose();
                    }}); // kap.. ifadesi sonu...

                    JPanel kapatmaPaneli = new JPanel();
                    kapatmaPaneli.setLayout (new BoxLayout (kapatmaPaneli, BoxLayout.LINE_AXIS));
                    kapatmaPaneli.add (Box.createHorizontalGlue());
                    kapatmaPaneli.add (kapatmaD��mesi);
                    kapatmaPaneli.setBorder (BorderFactory. createEmptyBorder (0,0,5,5));

                    JPanel i�erikPanosu = new JPanel (new BorderLayout());
                    i�erikPanosu.add (etiket, BorderLayout.CENTER);
                    i�erikPanosu.add (kapatmaPaneli, BorderLayout.PAGE_END);
                    i�erikPanosu.setOpaque (true);
                    diyalog.setContentPane (i�erikPanosu);

                    diyalog.setSize (new Dimension (300, 150));
                    diyalog.setLocationRelativeTo (�er�eve);
                    diyalog.setVisible (true);
                } // if-else karar� sonu...
        }}); // onu.. ifadesi sonu...

        return panoyuYarat (daha�okDiyalog�zah� + ":", radyoButonlar�, onuG�sterButonu);
    } // daha�okDiyalogKutusunuYarat() metodu sonu...

    // 3.fi�le g�sterilecek olan JOptionPane.showMessageDialog'lu ikon panelini yaratal�m...
    private JPanel ikonDiyalogKutusunuYarat() {
        JButton onuG�sterButonu = null;

        final int butonSay�s� = 6;
        JRadioButton[] radyoButonlar� = new JRadioButton[butonSay�s�];
        final ButtonGroup grup = new ButtonGroup();

        final String sadeKomutu = "sade";
        final String bilgiKomutu = "bilgi";
        final String soruKomutu = "soru";
        final String hataKomutu = "hata";
        final String ikazKomutu = "ikaz";
        final String gelenekselKomut = "geleneksel";

        radyoButonlar�[0] = new JRadioButton ("Sade (ikonsuz)");
        radyoButonlar�[0].setActionCommand (sadeKomutu);

        radyoButonlar�[1] = new JRadioButton ("Bilgi ikonu");
        radyoButonlar�[1].setActionCommand (bilgiKomutu);

        radyoButonlar�[2] = new JRadioButton ("Soru ikonu");
        radyoButonlar�[2].setActionCommand (soruKomutu);

        radyoButonlar�[3] = new JRadioButton ("Hata ikonu");
        radyoButonlar�[3].setActionCommand (hataKomutu);

        radyoButonlar�[4] = new JRadioButton ("�kaz ikonu");
        radyoButonlar�[4].setActionCommand (ikazKomutu);

        radyoButonlar�[5] = new JRadioButton ("Geleneksel ikon");
        radyoButonlar�[5].setActionCommand (gelenekselKomut);

        for (int i = 0; i < butonSay�s�; i++) grup.add (radyoButonlar�[i]);

        radyoButonlar�[0].setSelected (true);

        onuG�sterButonu = new JButton ("G�ster onu!");

        onuG�sterButonu.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                String komut = grup.getSelection().getActionCommand();

                // Sade (ikonsuz)...
                if (komut == sadeKomutu) JOptionPane.showMessageDialog (�er�eve, "Yumurtalar�n ye�il olmalar� gerekmiyor.", "Sade bir mesaj", JOptionPane.PLAIN_MESSAGE);

                // Bilgi ikonu...
                else if (komut == bilgiKomutu) JOptionPane.showMessageDialog (�er�eve, "Yumurtalar�n ye�il olmalar� gerekmiyor.", "Tuhaf bir bilgisel diyalog", JOptionPane.INFORMATION_MESSAGE);

                // Soru ikonu...
                else if (komut == soruKomutu) JOptionPane.showMessageDialog (�er�eve, "Bir soru i�in b�ylesi bir mesaj diyalo�u\nkullanmamal�s�n, TAMAM MI?", "G�l�n� bir soru", JOptionPane.QUESTION_MESSAGE);

                // Hata ikonu...
                else if (komut == hataKomutu) JOptionPane.showMessageDialog (�er�eve, "Yumurtalar�n ye�il olmalar� gerekmiyor.", "Sa�ma bir hata", JOptionPane.ERROR_MESSAGE);

                // �kaz ikonu...
                else if (komut == ikazKomutu) JOptionPane.showMessageDialog (�er�eve,"Yumurtalar�n ye�il olmalar� �art de�il.", "Ama�s�z bir ikaz", JOptionPane.WARNING_MESSAGE);

                // Geleneksel ikon...
                else if (komut == gelenekselKomut) JOptionPane.showMessageDialog (�er�eve, "Yumurtalar�n ye�il olmalar� gerekmiyor.", "G�l�n� bir geleneksel diyalog", JOptionPane.INFORMATION_MESSAGE, ikon);
        }}); // onu.. ifadesi sonu...

        return ikiKolonluPanoyuYarat (ikon�zah� + ":", radyoButonlar�, onuG�sterButonu);
    } // ikonDiyalogKutusunuYarat() metodu sonu...

    // basitDiyalogKutusunuYarat() ve daha�okDiyalogKutusunuYarat() metodlar�nca kullan�lacak olan
    // bir a��klama, tek s�tunluk radyo butonlar� ve "G�ster onu!" d��mesini i�eren bir pano yarat�r...
    private JPanel panoyuYarat (String a��klama, JRadioButton[] radyoButonlar�, JButton g�sterButonu) {
        int tercihSay�s� = radyoButonlar�.length;
        JPanel kutu = new JPanel();
        JLabel etiket = new JLabel (a��klama);

        kutu.setLayout (new BoxLayout (kutu, BoxLayout.PAGE_AXIS));
        kutu.add (etiket);

        for (int i = 0; i < tercihSay�s�; i++) kutu.add (radyoButonlar�[i]);

        JPanel pano = new JPanel (new BorderLayout());
        pano.add (kutu, BorderLayout.PAGE_START);
        pano.add (g�sterButonu, BorderLayout.PAGE_END);
        return pano;
    } // panoyuYarat(..) metodu sonu...

     private JPanel ikiKolonluPanoyuYarat (String a��klama, JRadioButton[] radyoButonlar�, JButton g�sterButonu) {
        JLabel etiket = new JLabel (a��klama);
        int herbirS�tundakiRadyo = radyoButonlar�.length / 2;

        JPanel �zgara = new JPanel (new GridLayout (0, 2));
        for (int i = 0; i < herbirS�tundakiRadyo; i++) {
            �zgara.add (radyoButonlar�[i]);
            �zgara.add (radyoButonlar�[i + herbirS�tundakiRadyo]);
        } // for d�ng�s� sonu...

        JPanel kutu = new JPanel();
        kutu.setLayout (new BoxLayout (kutu, BoxLayout.PAGE_AXIS));
        kutu.add (etiket);
        �zgara.setAlignmentX (0.0f);
        kutu.add (�zgara);

        JPanel pano = new JPanel (new BorderLayout());
        pano.add (kutu, BorderLayout.PAGE_START);
        pano.add (g�sterButonu, BorderLayout.PAGE_END);

        return pano;
    } // ikiKolonluPanoyuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Diyalog G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_12 yeni��erikPanosu = new J5c_12 (�er�eve);
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_12 s�n�f� sonu...