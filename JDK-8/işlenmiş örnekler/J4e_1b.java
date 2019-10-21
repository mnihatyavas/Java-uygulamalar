// J4e_1b.java: DynamicTreePanel (DinamikA�a�Paneli) �rne�i.

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
    private static String S�L_KOMUTU = "sil";
    private static String TEM�ZLE_KOMUTU = "temizle";

    private J4e_1x2 a�a�Paneli; // J4e_1x2=DynamicTree/DinamikA�a�...

    public J4e_1b() {
        super (new BorderLayout());

        // Komponentleri (a�a�Paneli ve butonlar paneli) yaratal�m...
        a�a�Paneli = new J4e_1x2();
        a�ac�Dalland�r (a�a�Paneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (S�L_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEM�ZLE_KOMUTU);
        temizleButonu.addActionListener (this);

        a�a�Paneli.setPreferredSize (new Dimension (300, 150));
        add (a�a�Paneli, BorderLayout.NORTH);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J4e_1b() kurucusu sonu...

    public void a�ac�Dalland�r (J4e_1x2 a�a�Paneli) {
        String e1Ad� = new String ("Ebeveyn-1");
        String e2Ad� = new String ("Ebeveyn-2");
        String �1Ad� = new String ("�ocuk-1");
        String �2Ad� = new String ("�ocuk-2");

        DefaultMutableTreeNode e1, e2;

        e1 = a�a�Paneli.nesneEkle (null, e1Ad�);
        e2 = a�a�Paneli.nesneEkle (null, e2Ad�);

        a�a�Paneli.nesneEkle (e1, �1Ad�);
        a�a�Paneli.nesneEkle (e1, �2Ad�);

        a�a�Paneli.nesneEkle (e2, �1Ad�);
        a�a�Paneli.nesneEkle (e2, �2Ad�);
    } // a�ac�Dalland�r(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) a�a�Paneli.nesneEkle ("Yeni Dal-" + yeniDalSoneki++);
        else if (S�L_KOMUTU.equals (komut)) a�a�Paneli.se�iliDal�Sil();
        else if (TEM�ZLE_KOMUTU.equals (komut)) a�a�Paneli.temizle();
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Dinamik A�a� Paneli");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J4e_1b yeni��erikPanosu = new J4e_1b();
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
} // J4e_1b s�n�f� sonu...