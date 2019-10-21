// J4e_1b.java: DynamicTreePanel (DinamikAðaçPaneli) örneði.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.tree.DefaultMutableTreeNode;

// Gereken alt-program: J4e_1x2.java=DynamicTree
public class J4e_1b extends JPanel implements ActionListener {
    private int yeniDalSoneki = 1;
    private static String EKLE_KOMUTU = "ekle";
    private static String SÝL_KOMUTU = "sil";
    private static String TEMÝZLE_KOMUTU = "temizle";

    private J4e_1x2 aðaçPaneli; // J4e_1x2=DynamicTree/DinamikAðaç...

    public J4e_1b() {
        super (new BorderLayout());

        // Komponentleri (aðaçPaneli ve butonlar paneli) yaratalým...
        aðaçPaneli = new J4e_1x2();
        aðacýDallandýr (aðaçPaneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (SÝL_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEMÝZLE_KOMUTU);
        temizleButonu.addActionListener (this);

        aðaçPaneli.setPreferredSize (new Dimension (300, 150));
        add (aðaçPaneli, BorderLayout.NORTH);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J4e_1b() kurucusu sonu...

    public void aðacýDallandýr (J4e_1x2 aðaçPaneli) {
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
    } // aðacýDallandýr(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) aðaçPaneli.nesneEkle ("Yeni Dal-" + yeniDalSoneki++);
        else if (SÝL_KOMUTU.equals (komut)) aðaçPaneli.seçiliDalýSil();
        else if (TEMÝZLE_KOMUTU.equals (komut)) aðaçPaneli.temizle();
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Dinamik Aðaç Paneli");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J4e_1b yeniÝçerikPanosu = new J4e_1b();
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
} // J4e_1b sýnýfý sonu...