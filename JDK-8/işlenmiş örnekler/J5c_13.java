// J5c_13.java: DynamicTreeDemo (DinamikAðaçGösterimi) örneði.

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

import javax.swing.tree.DefaultMutableTreeNode;

// Gerekli dosya: J5c_13x.java/DynamicTree.java...
public class J5c_13 extends JPanel  implements ActionListener {
    private int yeniDalSoneki = 1;
    private static String EKLE_KOMUTU = "ekle";
    private static String SÝL_KOMUTU = "sil";
    private static String TEMÝZLE_KOMUTU = "temizle";

    private J5c_13x aðaçPaneli; // J5c_13x=DynamicTree

    public J5c_13() {// Kurucu...
        super (new BorderLayout());
        
        // Komponentleri (dallar, butonlar) yaratýp içerik panosuna koyalým...
        aðaçPaneli = new J5c_13x();
        aðacýDallandýr (aðaçPaneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        ekleButonu.setBackground (Color.CYAN);

        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (SÝL_KOMUTU);
        silButonu.addActionListener (this);
        silButonu.setBackground (Color.CYAN);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEMÝZLE_KOMUTU);
        temizleButonu.addActionListener (this);
        temizleButonu.setBackground (Color.CYAN);

        aðaçPaneli.setPreferredSize (new Dimension (300, 150));
        add (aðaçPaneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J5c_13() kurucusu sonu...

    public void aðacýDallandýr (J5c_13x aðaçPaneli) {
        String e1Adý = new String ("Ebeveyn 1");
        String e2Adý = new String ("Ebeveyn 2");
        String ç1Adý = new String ("Çocuk 1");
        String ç2Adý = new String ("Çocuk 2");
        String ç3Adý = new String ("Çocuk 3");

        DefaultMutableTreeNode e1, e2;

        e1 = aðaçPaneli.dalNesnesiEkle (null, e1Adý);
        e2 = aðaçPaneli.dalNesnesiEkle (null, e2Adý);

        aðaçPaneli.dalNesnesiEkle (e1, ç1Adý);
        aðaçPaneli.dalNesnesiEkle (e1, ç2Adý);
        aðaçPaneli.dalNesnesiEkle (e1, ç3Adý);

        aðaçPaneli.dalNesnesiEkle (e2, ç1Adý);
        aðaçPaneli.dalNesnesiEkle (e2, ç2Adý);
        aðaçPaneli.dalNesnesiEkle (e2, ç3Adý);
    } // aðacýDallandýr(..) metodu sonu...
    
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) aðaçPaneli.dalNesnesiEkle ("Yeni Dal " + yeniDalSoneki++);
        else if (SÝL_KOMUTU.equals (komut)) aðaçPaneli.þimdikiDalýSil();
        else if (TEMÝZLE_KOMUTU.equals (komut)) {aðaçPaneli.temizle(); yeniDalSoneki = 1;}
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Dynamik Aðaç Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE

        J5c_13 yeniÝçerikPanosu = new J5c_13();
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
} // J5c_13 sýnýfý sonu...