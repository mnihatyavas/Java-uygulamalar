// J5c_13.java: DynamicTreeDemo (DinamikA�a�G�sterimi) �rne�i.

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
    private static String S�L_KOMUTU = "sil";
    private static String TEM�ZLE_KOMUTU = "temizle";

    private J5c_13x a�a�Paneli; // J5c_13x=DynamicTree

    public J5c_13() {// Kurucu...
        super (new BorderLayout());
        
        // Komponentleri (dallar, butonlar) yarat�p i�erik panosuna koyal�m...
        a�a�Paneli = new J5c_13x();
        a�ac�Dalland�r (a�a�Paneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        ekleButonu.setBackground (Color.CYAN);

        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (S�L_KOMUTU);
        silButonu.addActionListener (this);
        silButonu.setBackground (Color.CYAN);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEM�ZLE_KOMUTU);
        temizleButonu.addActionListener (this);
        temizleButonu.setBackground (Color.CYAN);

        a�a�Paneli.setPreferredSize (new Dimension (300, 150));
        add (a�a�Paneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J5c_13() kurucusu sonu...

    public void a�ac�Dalland�r (J5c_13x a�a�Paneli) {
        String e1Ad� = new String ("Ebeveyn 1");
        String e2Ad� = new String ("Ebeveyn 2");
        String �1Ad� = new String ("�ocuk 1");
        String �2Ad� = new String ("�ocuk 2");
        String �3Ad� = new String ("�ocuk 3");

        DefaultMutableTreeNode e1, e2;

        e1 = a�a�Paneli.dalNesnesiEkle (null, e1Ad�);
        e2 = a�a�Paneli.dalNesnesiEkle (null, e2Ad�);

        a�a�Paneli.dalNesnesiEkle (e1, �1Ad�);
        a�a�Paneli.dalNesnesiEkle (e1, �2Ad�);
        a�a�Paneli.dalNesnesiEkle (e1, �3Ad�);

        a�a�Paneli.dalNesnesiEkle (e2, �1Ad�);
        a�a�Paneli.dalNesnesiEkle (e2, �2Ad�);
        a�a�Paneli.dalNesnesiEkle (e2, �3Ad�);
    } // a�ac�Dalland�r(..) metodu sonu...
    
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) a�a�Paneli.dalNesnesiEkle ("Yeni Dal " + yeniDalSoneki++);
        else if (S�L_KOMUTU.equals (komut)) a�a�Paneli.�imdikiDal�Sil();
        else if (TEM�ZLE_KOMUTU.equals (komut)) {a�a�Paneli.temizle(); yeniDalSoneki = 1;}
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Dynamik A�a� G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE

        J5c_13 yeni��erikPanosu = new J5c_13();
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
} // J5c_13 s�n�f� sonu...