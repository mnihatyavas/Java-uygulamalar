// J5e_3.java: DesktopDemo (MasaüstüGösterisi) örneði.

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

// Gereken resim dosyasý: resim/masa.gif
public class J5e_3 extends JFrame {
    JButton uygulamayýFýrlatDüðmesi = new JButton ("Uygulamayý Fýrlat");
    JButton tarayýcýyýFýrlatDüðmesi = new JButton ("Tarayýcýyý Fýrlat");
    JButton epostayýFýrlatDüðmesi = new JButton ("Epostayý Fýrlat");
    JRadioButton açRadyoDüðmesi = new JRadioButton ("Aç", true);
    JRadioButton düzenleRadyoDüðmesi = new JRadioButton ("Düzenle");
    JRadioButton yazRadyoDüðmesi = new JRadioButton ("Yaz");
    JTextField tarayýcýYurelMetinSatýrý = new JTextField();
    JTextField epostaMetinSatýrý = new JTextField();
    JTextField metinSatýrý = new JTextField();
    ButtonGroup radyoDüðmeleriGrubu = new ButtonGroup();
    JLabel yurelEtiketi = new JLabel ("Yurel:");
    JLabel epostaEtiketi = new JLabel ("E-posta:");
    JLabel dosyaEtiketi = new JLabel ("Dosya:");
    JButton dosyaBulDüðmesi = new JButton ("Bul");
    JFileChooser dosyaSeçici = new JFileChooser();
    File dosya;

    private Desktop masaüstü;
    private Desktop.Action aksiyon = Desktop.Action.OPEN;
    
    public J5e_3() {// Kurucu...
        // Tüm GUI komponentleri baþlatalým...
        komponentleriBaþlat();
        // Bu kullanýcýdaki grafik uyumluluðu onaylanýncaya dek tüm düðmeleri etkinsizleþrirelim...
        aksiyonlarýEtkinsizleþtir();
        // VM uygulamayý bu kullanýcýda destekliyorsa aksiyonlarý etkinleþtiralim...
        if (Desktop.isDesktopSupported()) {
            masaüstü = Desktop.getDesktop();
            desteklenenAksiyonlarýEtkinleþtir();
        } // if kararý sonu...
        çerçeveÝkonunuYükle();
        setResizable (false); // Pencere ebatlarý deðiþtirilemez olsun...
        setLocation (500,100);
    } // J5e_3() kurucusu sonu...

    // Komponentleri yaratýp paketleyelim...
    private void komponentleriBaþlat() {
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        setTitle ("Masaüstü Gösterisi");

        tarayýcýYurelMetinSatýrý.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {tfDüðmesiTýklandýðýnda (null);}});
        tarayýcýyýFýrlatDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {tfDüðmesiTýklandýðýnda (olay);}});

        epostaMetinSatýrý.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {efDüðmesiTýklandýðýnda (null);}});
        epostayýFýrlatDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {efDüðmesiTýklandýðýnda (olay);}});

        açRadyoDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {açRDüðmesiTýklandýðýnda (olay);}});
        düzenleRadyoDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {düzenleRDüðmesiTýklandýðýnda (olay);}});
        yazRadyoDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {yazRDüðmesiTýklandýðýnda (olay);}});

        metinSatýrý.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {ufDüðmesiTýklandýðýnda (null);}});
        dosyaBulDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {bulDüðmesiTýklandýðýnda (olay);}});
        uygulamayýFýrlatDüðmesi.addActionListener (new ActionListener() {public void actionPerformed (ActionEvent olay) {ufDüðmesiTýklandýðýnda (olay);}});

        radyoDüðmeleriGrubu.add (açRadyoDüðmesi);
        radyoDüðmeleriGrubu.add (düzenleRadyoDüðmesi);
        radyoDüðmeleriGrubu.add (yazRadyoDüðmesi);

        // Komponentleri yaay/dikey gruplamayla serimleyelim...
        Container kabÇerçevesi = this.getContentPane();        
        GroupLayout yerleþim = new GroupLayout (kabÇerçevesi);
        kabÇerçevesi.setLayout (yerleþim);
        yerleþim.setAutoCreateContainerGaps (true);
        yerleþim.setAutoCreateGaps (true);

        // Yatay gruplama...
        GroupLayout.SequentialGroup temelYatayGrup = yerleþim.createSequentialGroup();

        GroupLayout.ParallelGroup etiketYGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.LEADING);
        etiketYGrubu.addComponent (yurelEtiketi, GroupLayout.Alignment.TRAILING);
        etiketYGrubu.addComponent (epostaEtiketi, GroupLayout.Alignment.TRAILING);
        etiketYGrubu.addComponent (dosyaEtiketi, GroupLayout.Alignment.TRAILING);

        GroupLayout.ParallelGroup metinSatýrýYGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.LEADING);
        metinSatýrýYGrubu.addComponent (tarayýcýYurelMetinSatýrý);
        metinSatýrýYGrubu.addComponent (epostaMetinSatýrý);

        GroupLayout.SequentialGroup radyoDüðmesiYGrubu = yerleþim.createSequentialGroup();
        radyoDüðmesiYGrubu.addComponent (açRadyoDüðmesi);
        radyoDüðmesiYGrubu.addComponent (düzenleRadyoDüðmesi);
        radyoDüðmesiYGrubu.addComponent (yazRadyoDüðmesi);

        metinSatýrýYGrubu.addGroup (radyoDüðmesiYGrubu);

        GroupLayout.SequentialGroup dosyaYGrubu = yerleþim.createSequentialGroup();
        dosyaYGrubu.addComponent (metinSatýrý);
        dosyaYGrubu.addComponent (dosyaBulDüðmesi);

        metinSatýrýYGrubu.addGroup (dosyaYGrubu);

        GroupLayout.ParallelGroup fýrlatDüðmesiYGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.LEADING);
        fýrlatDüðmesiYGrubu.addComponent (tarayýcýyýFýrlatDüðmesi);
        fýrlatDüðmesiYGrubu.addComponent (epostayýFýrlatDüðmesi);
        fýrlatDüðmesiYGrubu.addComponent (uygulamayýFýrlatDüðmesi);

        temelYatayGrup.addGroup (etiketYGrubu);
        temelYatayGrup.addGroup (metinSatýrýYGrubu);
        temelYatayGrup.addGroup (fýrlatDüðmesiYGrubu);

        yerleþim.setHorizontalGroup (temelYatayGrup);
        
        // Dikey gruplama...
        GroupLayout.SequentialGroup temelDikeyGrup = yerleþim.createSequentialGroup();

        GroupLayout.ParallelGroup yurelDGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.BASELINE);
        yurelDGrubu.addComponent (yurelEtiketi);
        yurelDGrubu.addComponent (tarayýcýYurelMetinSatýrý);
        yurelDGrubu.addComponent (tarayýcýyýFýrlatDüðmesi);

        GroupLayout.ParallelGroup epostaDGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.BASELINE);
        epostaDGrubu.addComponent (epostaEtiketi);
        epostaDGrubu.addComponent (epostaMetinSatýrý);
        epostaDGrubu.addComponent (epostayýFýrlatDüðmesi);

        GroupLayout.ParallelGroup radyoButonuDGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.BASELINE);
        radyoButonuDGrubu.addComponent (açRadyoDüðmesi);
        radyoButonuDGrubu.addComponent (düzenleRadyoDüðmesi);
        radyoButonuDGrubu.addComponent (yazRadyoDüðmesi);

        GroupLayout.ParallelGroup dosyaDGrubu = yerleþim.createParallelGroup (GroupLayout.Alignment.BASELINE);
        dosyaDGrubu.addComponent (dosyaEtiketi);
        dosyaDGrubu.addComponent (metinSatýrý);
        dosyaDGrubu.addComponent (dosyaBulDüðmesi);
        dosyaDGrubu.addComponent (uygulamayýFýrlatDüðmesi);

        temelDikeyGrup.addGroup (yurelDGrubu);
        temelDikeyGrup.addGroup (epostaDGrubu);
        temelDikeyGrup.addGroup (radyoButonuDGrubu);
        temelDikeyGrup.addGroup (dosyaDGrubu);

        yerleþim.setVerticalGroup (temelDikeyGrup);
        pack();
    } // komponentleriBaþlat() metodu sonu...

    // Kullanýcýnýn metin satýrýna gireceði yurel dosya adýyla tarayýcý fýrlatýlýrsa...
    private void tfDüðmesiTýklandýðýnda (ActionEvent olay) {
        URI yuri = null; // URL:UniversalResourceLocater, URI:UniversalResourceItem
        try {yuri = new URI (tarayýcýYurelMetinSatýrý.getText());
            masaüstü.browse (yuri);
        }catch (IOException ist) {System.err.println ("HATA: Sistem belirtilen [" + yuri + "] yuri dosyasýný bulamýyor!"); ist.printStackTrace();
        }catch (URISyntaxException ist) {System.err.println ("HATA: Girilen URI dosya yolunda geçersiz karakter var!"); ist.printStackTrace();
        } // try-catch.. bloðu sonu...
    } // tfDüðmesiTýklandýðýnda(..) metodu sonu...

    // Kullanýcýnýn metin satýrýna gireceði eposta adres-li/siz varsayýlý eposta sunucusunu fýrlatýrsa...
    private void efDüðmesiTýklandýðýnda (ActionEvent olay) {
        String epostaAdresi = epostaMetinSatýrý.getText();
        URI epostaYuri = null;
        try {
            if (epostaAdresi.length() > 0) {
                epostaYuri = new URI ("eposta-kime", epostaAdresi, null);
                masaüstü.mail (epostaYuri);
            }else masaüstü.mail();
        }catch (IOException ist) {ist.printStackTrace();
        }catch (URISyntaxException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...
    } // efDüðmesiTýklandýðýnda(..) metodu sonu...

    // Uygulamayý fýrlat düðmesini týklamadan aç/düzenle/yaz'dan biri seçilmelidir...
    private void açRDüðmesiTýklandýðýnda (ActionEvent olay) {aksiyon = Desktop.Action.OPEN;}
    private void düzenleRDüðmesiTýklandýðýnda (ActionEvent olay) {aksiyon = Desktop.Action.EDIT;}
    private void yazRDüðmesiTýklandýðýnda (ActionEvent olay) {aksiyon = Desktop.Action.PRINT;}

    // Dosya adý metin satýrýna girilmeyip DosyaSeçici diyalogla bulunmak istenildiðinde...
    private void bulDüðmesiTýklandýðýnda (ActionEvent olay) {
        if (olay.getSource() == dosyaBulDüðmesi) {
            int dönüþDeðeri = dosyaSeçici.showOpenDialog (J5e_3.this);
            if (dönüþDeðeri == JFileChooser.APPROVE_OPTION) {
                dosya = dosyaSeçici.getSelectedFile();
                metinSatýrý.setText (dosya.getAbsolutePath());
            } // if kararý sonu...
        } // dýþ-if kararý sonu...
    } // bulDüðmesiTýklandýðýnda(..) metodu sonu...

    // Metin satýrýna girilen veya bul düðmesiyle seçilen dosya adýyla uygulama fýrlatýlýrsa...
    private void ufDüðmesiTýklandýðýnda (ActionEvent olay) {
        File dosya = new File (metinSatýrý.getText());
        try {switch (aksiyon) {
                case OPEN:
                    masaüstü.open (dosya);
                    break;
                case EDIT:
                    masaüstü.edit (dosya);
                    break;
                case PRINT:
                    masaüstü.print (dosya);
                    break;
            } // switch bloðu sonu...
        }catch (IOException ist) {System.err.println ("HATA: [" + dosya + "] dosyasý üzerinde istenilen iþlsm yapýlamýyor!"); ist.printStackTrace();
        } // try-catch bloðu sonu...
    } // ufDüðmesiTýklandýðýnda(..) metodu sonu...

    // Fonksiyonelliklerinin desteklendiði doðrulanýncaya dek grafik komponentleri etkinsizleþtirelim...
    private void aksiyonlarýEtkinsizleþtir() {
        tarayýcýYurelMetinSatýrý.setEnabled (false);
        tarayýcýyýFýrlatDüðmesi.setEnabled (false);

        epostaMetinSatýrý.setEnabled (false);
        epostayýFýrlatDüðmesi.setEnabled (false);

        açRadyoDüðmesi.setEnabled (false);
        düzenleRadyoDüðmesi.setEnabled (false);
        yazRadyoDüðmesi.setEnabled (false);

        metinSatýrý.setEnabled (false);
        dosyaBulDüðmesi.setEnabled (false);
        uygulamayýFýrlatDüðmesi.setEnabled (false);
    } // aksiyonlarýEtkinsizleþtir() metodu sonu...

   // Desteklenen aksiyonlarý (aç->tarayýcý/eposta, aç-düzenle-yaz->dosya) etkinleþtirelim...
    private void desteklenenAksiyonlarýEtkinleþtir() {
        if (masaüstü.isSupported (Desktop.Action.BROWSE)) {tarayýcýYurelMetinSatýrý.setEnabled (true); tarayýcýyýFýrlatDüðmesi.setEnabled (true);}
        if (masaüstü.isSupported (Desktop.Action.MAIL)) {epostaMetinSatýrý.setEnabled (true); epostayýFýrlatDüðmesi.setEnabled (true);}
        if (masaüstü.isSupported (Desktop.Action.OPEN)) açRadyoDüðmesi.setEnabled (true);
        if (masaüstü.isSupported (Desktop.Action.EDIT)) düzenleRadyoDüðmesi.setEnabled (true);
        if (masaüstü.isSupported (Desktop.Action.PRINT)) yazRadyoDüðmesi.setEnabled (true);
        if (düzenleRadyoDüðmesi.isEnabled() || açRadyoDüðmesi.isEnabled() || yazRadyoDüðmesi.isEnabled()) {
            metinSatýrý.setEnabled (true);
            uygulamayýFýrlatDüðmesi.setEnabled (true);
            dosyaBulDüðmesi.setEnabled (true);
        } // if kararý sonu...
    } // desteklenenAksiyonlarýEtkinleþtir() metodu sonu...

    // Yüklenen ikonu penceremizin ikon resmi olarak kuralým...
    private void çerçeveÝkonunuYükle() {
        URL resimYureli = null;
        ImageIcon resimÝkonu = null;

        resimYureli = J5e_3.class.getResource ("resim/masa.gif");
        resimÝkonu = new ImageIcon (resimYureli);
        Image resim = resimÝkonu.getImage();
        this.setIconImage (resim);
    } // çerçeveÝkonunuYükle() metodu sonu...

    public static void main (String args[]) {
        // Uygun Look and Feel (Bak ve Hisset)'i seçin...
        try {//UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch.. bloðu sonu...

        // Metal'in koyu yazý fonlarýný kapatalým...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);

        // Bu uygulamanýnGUI'sini yaratýp gösterecek raporamalý bir sicim run'lý içerik panosu kuralým...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {new J5e_3().setVisible (true);} // Kurucuyu çaðýrýr...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_3 sýnýfý sonu...