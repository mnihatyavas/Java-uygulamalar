// J4b_2b.java: DynamicTreePanel (DinamikAðaçPaneli) örneði.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import javax.swing.tree.DefaultMutableTreeNode;

// Gereken alt-program: J4b_2x2.java=DynamicTree
public class J4b_2b extends JPanel implements ActionListener {
    private int yeniDalSoneki = 1;
    private static String EKLE_KOMUTU = "ekle";
    private static String SÝL_KOMUTU = "sil";
    private static String TEMÝZLE_KOMUTU = "temizle";

    private J4b_2x2 aðaçPaneli; // J4b_2x2=DynamicTree/DinamikAðaç

    public J4b_2b() {
        super (new BorderLayout());
        
        // JPanel ve JButton komponentlerini/parçalarýný yaratalým...
        aðaçPaneli = new J4b_2x2();
        aðacýYeþert (aðaçPaneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (SÝL_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEMÝZLE_KOMUTU);
        temizleButonu.addActionListener (this);

        // Komponentlerimizi içerikPanosu'na serelim...
        aðaçPaneli.setPreferredSize (new Dimension (300, 150));
        add (aðaçPaneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J4b_2b() kurucusu sonu...

    public void aðacýYeþert (J4b_2x2 aðaçPaneli) {
        String e1Adý = new String ("Ebeveyn-1");
        String e2Adý = new String ("Ebeveyn-2");
        String ç1Adý = new String ("Çocuk-1");
        String ç2Adý = new String ("Çocuk-2");

        DefaultMutableTreeNode e1, e2;

        e1 = aðaçPaneli.nesneEkle (null, e1Adý);
        e2 = aðaçPaneli.nesneEkle (null, e2Adý);

        aðaçPaneli.nesneEkle (e1, ç1Adý);
        aðaçPaneli.nesneEkle (e1, ç2Adý);

        aðaçPaneli.nesneEkle (e2, ç1Adý);
        aðaçPaneli.nesneEkle (e2, ç2Adý);
    } // aðacýYeþert(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) aðaçPaneli.nesneEkle ("Yeni Dal-" + yeniDalSoneki++);
        else if (SÝL_KOMUTU.equals (komut)) aðaçPaneli.seçiliDalýSil();
        else if (TEMÝZLE_KOMUTU.equals (komut)) aðaçPaneli.temizle();
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeGösterGUI() {// sonra J4b_2b'yle çaðrýlavcak...
        // Pencereyi yaratýp kuralým..
        JFrame çerçeve = new JFrame ("J4b_2b/DynamicTreePanel");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Ýçerik panosunu yaratýp kuralým...
        J4b_2b yeniÝçerikPanosu = new J4b_2b();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        // Ýçerikli penceremizi paketlenmiþ olarak gösterelim...
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...


    // J4b_2b adýyla ayrý bir uygulama olarak koþturulacak...
    public static void main (String[] args) {
        // Bu uygulamanýn GUI'sini yaratýp gösterecek güvenli bir sicim görevi programlayalým...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J4b_2b sýnýfý sonu...