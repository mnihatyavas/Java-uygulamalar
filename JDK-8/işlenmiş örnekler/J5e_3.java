// J5e_3.java: DesktopDemo (Masa�st�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.net.URL;
import java.net.URI;
import java.net.URISyntaxException;

import java.io.File;
import java.io.IOException;

// Gereken resim dosyas�: resim/masa.gif
public class J5e_3 extends JFrame {
    JButton uygulamay�F�rlatD��mesi = new JButton ("Uygulamay� F�rlat");
    JButton taray�c�y�F�rlatD��mesi = new JButton ("Taray�c�y� F�rlat");
    JButton epostay�F�rlatD��mesi = new JButton ("Epostay� F�rlat");
    JRadioButton a�RadyoD��mesi = new JRadioButton ("A�", true);
    JRadioButton d�zenleRadyoD��mesi = new JRadioButton ("D�zenle");
    JRadioButton yazRadyoD��mesi = new JRadioButton ("Yaz");
    JTextField taray�c�YurelMetinSat�r� = new JTextField();
    JTextField epostaMetinSat�r� = new JTextField();
    JTextField metinSat�r� = new JTextField();
    ButtonGroup radyoD��meleriGrubu = new ButtonGroup();
    JLabel yurelEtiketi = new JLabel ("Yurel:");
    JLabel epostaEtiketi = new JLabel ("E-posta:");
    JLabel dosyaEtiketi = new JLabel ("Dosya:");
    JButton dosyaBulD��mesi = new JButton ("Bul");
    JFileChooser dosyaSe�ici = new JFileChooser();
    File dosya;

    private Desktop masa�st�;
    private Desktop.Action aksiyon = Desktop.Action.OPEN;
    
    public J5e_3() {// Kurucu...
        // T�m GUI komponentleri ba�latal�m...
        komponentleriBa�lat();
        // Bu kullan�c�daki grafik uyumlulu�u onaylan�ncaya dek t�m d��meleri etkinsizle�rirelim...
        aksiyonlar�Etkinsizle�tir();
        // VM uygulamay� bu kullan�c�da destekliyorsa aksiyonlar� etkinle�tiralim...
        if (Desktop.isDesktopSupported()) {
            masa�st� = Desktop.getDesktop();
            desteklenenAksiyonlar�Etkinle�tir();
        } // if karar� sonu...
        �er�eve�konunuY�kle();
        setResizable (false); // Pencere ebatlar� de�i�tirilemez olsun...
        setLocation (500,100);
    } // J5e_3() kurucusu sonu...

    // Komponentleri yarat�p paketleyelim...
    private void komponentleriBa�lat() {
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        setTitle ("Masa�st� G�sterisi");

        taray�c�YurelMetinSat�r�.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {tfD��mesiT�kland���nda (null);}});
        taray�c�y�F�rlatD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {tfD��mesiT�kland���nda (olay);}});

        epostaMetinSat�r�.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {efD��mesiT�kland���nda (null);}});
        epostay�F�rlatD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {efD��mesiT�kland���nda (olay);}});

        a�RadyoD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {a�RD��mesiT�kland���nda (olay);}});
        d�zenleRadyoD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {d�zenleRD��mesiT�kland���nda (olay);}});
        yazRadyoD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {yazRD��mesiT�kland���nda (olay);}});

        metinSat�r�.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {ufD��mesiT�kland���nda (null);}});
        dosyaBulD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {bulD��mesiT�kland���nda (olay);}});
        uygulamay�F�rlatD��mesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {ufD��mesiT�kland���nda (olay);}});

        radyoD��meleriGrubu.add (a�RadyoD��mesi);
        radyoD��meleriGrubu.add (d�zenleRadyoD��mesi);
        radyoD��meleriGrubu.add (yazRadyoD��mesi);

        // Komponentleri yaay/dikey gruplamayla serimleyelim...
        Container kab�er�evesi = this.getContentPane();        
        GroupLayout yerle�im = new GroupLayout (kab�er�evesi);
        kab�er�evesi.setLayout (yerle�im);
        yerle�im.setAutoCreateContainerGaps (true);
        yerle�im.setAutoCreateGaps (true);

        // Yatay gruplama...
        GroupLayout.SequentialGroup temelYatayGrup = yerle�im.createSequentialGroup();

        GroupLayout.ParallelGroup etiketYGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.LEADING);
        etiketYGrubu.addComponent (yurelEtiketi, GroupLayout.Alignment.TRAILING);
        etiketYGrubu.addComponent (epostaEtiketi, GroupLayout.Alignment.TRAILING);
        etiketYGrubu.addComponent (dosyaEtiketi, GroupLayout.Alignment.TRAILING);

        GroupLayout.ParallelGroup metinSat�r�YGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.LEADING);
        metinSat�r�YGrubu.addComponent (taray�c�YurelMetinSat�r�);
        metinSat�r�YGrubu.addComponent (epostaMetinSat�r�);

        GroupLayout.SequentialGroup radyoD��mesiYGrubu = yerle�im.createSequentialGroup();
        radyoD��mesiYGrubu.addComponent (a�RadyoD��mesi);
        radyoD��mesiYGrubu.addComponent (d�zenleRadyoD��mesi);
        radyoD��mesiYGrubu.addComponent (yazRadyoD��mesi);

        metinSat�r�YGrubu.addGroup (radyoD��mesiYGrubu);

        GroupLayout.SequentialGroup dosyaYGrubu = yerle�im.createSequentialGroup();
        dosyaYGrubu.addComponent (metinSat�r�);
        dosyaYGrubu.addComponent (dosyaBulD��mesi);

        metinSat�r�YGrubu.addGroup (dosyaYGrubu);

        GroupLayout.ParallelGroup f�rlatD��mesiYGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.LEADING);
        f�rlatD��mesiYGrubu.addComponent (taray�c�y�F�rlatD��mesi);
        f�rlatD��mesiYGrubu.addComponent (epostay�F�rlatD��mesi);
        f�rlatD��mesiYGrubu.addComponent (uygulamay�F�rlatD��mesi);

        temelYatayGrup.addGroup (etiketYGrubu);
        temelYatayGrup.addGroup (metinSat�r�YGrubu);
        temelYatayGrup.addGroup (f�rlatD��mesiYGrubu);

        yerle�im.setHorizontalGroup (temelYatayGrup);
        
        // Dikey gruplama...
        GroupLayout.SequentialGroup temelDikeyGrup = yerle�im.createSequentialGroup();

        GroupLayout.ParallelGroup yurelDGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.BASELINE);
        yurelDGrubu.addComponent (yurelEtiketi);
        yurelDGrubu.addComponent (taray�c�YurelMetinSat�r�);
        yurelDGrubu.addComponent (taray�c�y�F�rlatD��mesi);

        GroupLayout.ParallelGroup epostaDGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.BASELINE);
        epostaDGrubu.addComponent (epostaEtiketi);
        epostaDGrubu.addComponent (epostaMetinSat�r�);
        epostaDGrubu.addComponent (epostay�F�rlatD��mesi);

        GroupLayout.ParallelGroup radyoButonuDGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.BASELINE);
        radyoButonuDGrubu.addComponent (a�RadyoD��mesi);
        radyoButonuDGrubu.addComponent (d�zenleRadyoD��mesi);
        radyoButonuDGrubu.addComponent (yazRadyoD��mesi);

        GroupLayout.ParallelGroup dosyaDGrubu = yerle�im.createParallelGroup (GroupLayout.Alignment.BASELINE);
        dosyaDGrubu.addComponent (dosyaEtiketi);
        dosyaDGrubu.addComponent (metinSat�r�);
        dosyaDGrubu.addComponent (dosyaBulD��mesi);
        dosyaDGrubu.addComponent (uygulamay�F�rlatD��mesi);

        temelDikeyGrup.addGroup (yurelDGrubu);
        temelDikeyGrup.addGroup (epostaDGrubu);
        temelDikeyGrup.addGroup (radyoButonuDGrubu);
        temelDikeyGrup.addGroup (dosyaDGrubu);

        yerle�im.setVerticalGroup (temelDikeyGrup);
        pack();
    } // komponentleriBa�lat() metodu sonu...

    // Kullan�c�n�n metin sat�r�na girece�i yurel dosya ad�yla taray�c� f�rlat�l�rsa...
    private void tfD��mesiT�kland���nda (ActionEvent olay) {
        URI yuri = null; // URL:UniversalResourceLocater, URI:UniversalResourceItem
        try {yuri = new URI (taray�c�YurelMetinSat�r�.getText());
            masa�st�.browse (yuri);
        }catch (IOException ist) {System.err.println ("HATA: Sistem belirtilen [" + yuri + "] yuri dosyas�n� bulam�yor!"); ist.printStackTrace();
        }catch (URISyntaxException ist) {System.err.println ("HATA: Girilen URI dosya yolunda ge�ersiz karakter var!"); ist.printStackTrace();
        } // try-catch.. blo�u sonu...
    } // tfD��mesiT�kland���nda(..) metodu sonu...

    // Kullan�c�n�n metin sat�r�na girece�i eposta adres-li/siz varsay�l� eposta sunucusunu f�rlat�rsa...
    private void efD��mesiT�kland���nda (ActionEvent olay) {
        String epostaAdresi = epostaMetinSat�r�.getText();
        URI epostaYuri = null;
        try {
            if (epostaAdresi.length() > 0) {
                epostaYuri = new URI ("eposta-kime", epostaAdresi, null);
                masa�st�.mail (epostaYuri);
            }else masa�st�.mail();
        }catch (IOException ist) {ist.printStackTrace();
        }catch (URISyntaxException ist) {ist.printStackTrace();
        } // try-catch.. blo�u sonu...
    } // efD��mesiT�kland���nda(..) metodu sonu...

    // Uygulamay� f�rlat d��mesini t�klamadan a�/d�zenle/yaz'dan biri se�ilmelidir...
    private void a�RD��mesiT�kland���nda (ActionEvent olay) {aksiyon = Desktop.Action.OPEN;}
    private void d�zenleRD��mesiT�kland���nda (ActionEvent olay) {aksiyon = Desktop.Action.EDIT;}
    private void yazRD��mesiT�kland���nda (ActionEvent olay) {aksiyon = Desktop.Action.PRINT;}

    // Dosya ad� metin sat�r�na girilmeyip DosyaSe�ici diyalogla bulunmak istenildi�inde...
    private void bulD��mesiT�kland���nda (ActionEvent olay) {
        if (olay.getSource() == dosyaBulD��mesi) {
            int d�n��De�eri = dosyaSe�ici.showOpenDialog (J5e_3.this);
            if (d�n��De�eri == JFileChooser.APPROVE_OPTION) {
                dosya = dosyaSe�ici.getSelectedFile();
                metinSat�r�.setText (dosya.getAbsolutePath());
            } // if karar� sonu...
        } // d��-if karar� sonu...
    } // bulD��mesiT�kland���nda(..) metodu sonu...

    // Metin sat�r�na girilen veya bul d��mesiyle se�ilen dosya ad�yla uygulama f�rlat�l�rsa...
    private void ufD��mesiT�kland���nda (ActionEvent olay) {
        File dosya = new File (metinSat�r�.getText());
        try {switch (aksiyon) {
                case OPEN:
                    masa�st�.open (dosya);
                    break;
                case EDIT:
                    masa�st�.edit (dosya);
                    break;
                case PRINT:
                    masa�st�.print (dosya);
                    break;
            } // switch blo�u sonu...
        }catch (IOException ist) {System.err.println ("HATA: [" + dosya + "] dosyas� �zerinde istenilen i�lsm yap�lam�yor!"); ist.printStackTrace();
        } // try-catch blo�u sonu...
    } // ufD��mesiT�kland���nda(..) metodu sonu...

    // Fonksiyonelliklerinin desteklendi�i do�rulan�ncaya dek grafik komponentleri etkinsizle�tirelim...
    private void aksiyonlar�Etkinsizle�tir() {
        taray�c�YurelMetinSat�r�.setEnabled (false);
        taray�c�y�F�rlatD��mesi.setEnabled (false);

        epostaMetinSat�r�.setEnabled (false);
        epostay�F�rlatD��mesi.setEnabled (false);

        a�RadyoD��mesi.setEnabled (false);
        d�zenleRadyoD��mesi.setEnabled (false);
        yazRadyoD��mesi.setEnabled (false);

        metinSat�r�.setEnabled (false);
        dosyaBulD��mesi.setEnabled (false);
        uygulamay�F�rlatD��mesi.setEnabled (false);
    } // aksiyonlar�Etkinsizle�tir() metodu sonu...

   // Desteklenen aksiyonlar� (a�->taray�c�/eposta, a�-d�zenle-yaz->dosya) etkinle�tirelim...
    private void desteklenenAksiyonlar�Etkinle�tir() {
        if (masa�st�.isSupported (Desktop.Action.BROWSE)) {taray�c�YurelMetinSat�r�.setEnabled (true); taray�c�y�F�rlatD��mesi.setEnabled (true);}
        if (masa�st�.isSupported (Desktop.Action.MAIL)) {epostaMetinSat�r�.setEnabled (true); epostay�F�rlatD��mesi.setEnabled (true);}
        if (masa�st�.isSupported (Desktop.Action.OPEN)) a�RadyoD��mesi.setEnabled (true);
        if (masa�st�.isSupported (Desktop.Action.EDIT)) d�zenleRadyoD��mesi.setEnabled (true);
        if (masa�st�.isSupported (Desktop.Action.PRINT)) yazRadyoD��mesi.setEnabled (true);
        if (d�zenleRadyoD��mesi.isEnabled() || a�RadyoD��mesi.isEnabled() || yazRadyoD��mesi.isEnabled()) {
            metinSat�r�.setEnabled (true);
            uygulamay�F�rlatD��mesi.setEnabled (true);
            dosyaBulD��mesi.setEnabled (true);
        } // if karar� sonu...
    } // desteklenenAksiyonlar�Etkinle�tir() metodu sonu...

    // Y�klenen ikonu penceremizin ikon resmi olarak kural�m...
    private void �er�eve�konunuY�kle() {
        URL resimYureli = null;
        ImageIcon resim�konu = null;

        resimYureli = J5e_3.class.getResource ("resim/masa.gif");
        resim�konu = new ImageIcon (resimYureli);
        Image resim = resim�konu.getImage();
        this.setIconImage (resim);
    } // �er�eve�konunuY�kle() metodu sonu...

    public static void main (String args[]) {
        // Uygun Look and Feel (Bak ve Hisset)'i se�in...
        try {//UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. blo�u sonu...

        // Metal'in koyu yaz� fonlar�n� kapatal�m...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);

        // Bu uygulaman�nGUI'sini yarat�p g�sterecek raporamal� bir sicim run'l� i�erik panosu kural�m...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {new J5e_3().setVisible (true);} // Kurucuyu �a��r�r...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_3 s�n�f� sonu...