// J4b_2b.java: DynamicTreePanel (DinamikA�a�Paneli) �rne�i.

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
    private static String S�L_KOMUTU = "sil";
    private static String TEM�ZLE_KOMUTU = "temizle";

    private J4b_2x2 a�a�Paneli; // J4b_2x2=DynamicTree/DinamikA�a�

    public J4b_2b() {
        super (new BorderLayout());
        
        // JPanel ve JButton komponentlerini/par�alar�n� yaratal�m...
        a�a�Paneli = new J4b_2x2();
        a�ac�Ye�ert (a�a�Paneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (S�L_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizleButonu = new JButton ("Temizle");
        temizleButonu.setActionCommand (TEM�ZLE_KOMUTU);
        temizleButonu.addActionListener (this);

        // Komponentlerimizi i�erikPanosu'na serelim...
        a�a�Paneli.setPreferredSize (new Dimension (300, 150));
        add (a�a�Paneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0,3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizleButonu);
        add (panel, BorderLayout.SOUTH);
    } // J4b_2b() kurucusu sonu...

    public void a�ac�Ye�ert (J4b_2x2 a�a�Paneli) {
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
    } // a�ac�Ye�ert(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) a�a�Paneli.nesneEkle ("Yeni Dal-" + yeniDalSoneki++);
        else if (S�L_KOMUTU.equals (komut)) a�a�Paneli.se�iliDal�Sil();
        else if (TEM�ZLE_KOMUTU.equals (komut)) a�a�Paneli.temizle();
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeG�sterGUI() {// sonra J4b_2b'yle �a�r�lavcak...
        // Pencereyi yarat�p kural�m..
        JFrame �er�eve = new JFrame ("J4b_2b/DynamicTreePanel");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // ��erik panosunu yarat�p kural�m...
        J4b_2b yeni��erikPanosu = new J4b_2b();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        // ��erikli penceremizi paketlenmi� olarak g�sterelim...
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...


    // J4b_2b ad�yla ayr� bir uygulama olarak ko�turulacak...
    public static void main (String[] args) {
        // Bu uygulaman�n GUI'sini yarat�p g�sterecek g�venli bir sicim g�revi programlayal�m...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J4b_2b s�n�f� sonu...