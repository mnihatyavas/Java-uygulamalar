// J4a_6x1.java: DynamicTreePanel (DinamikA�a�Paneli) alt-�rne�i.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

// Gereken alt-alt-program: J4a_6x2.java=DynamicTree
public class J4a_6x1 extends JPanel implements ActionListener {
    private int yeniDalSoneki = 1;
    private static String EKLE_KOMUTU = "tek-ekle";
    private static String S�L_KOMUTU = "tek-sil";
    private static String TEM�ZLE_KOMUTU = "t�m-temizle";
    private J4a_6x2 a�a�Paneli; // J4a_6x2/DynamicTree/DinamikA�a�...

    public J4a_6x1() {
        super (new BorderLayout());

        // Ba�lang�� a�a� yap�s�n� yaratal�m...
        a�a�Paneli = new J4a_6x2();
        a�ac�Kur (a�a�Paneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (S�L_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizlelButonu = new JButton ("Temizle");
        temizlelButonu.setActionCommand (TEM�ZLE_KOMUTU);
        temizlelButonu.addActionListener (this);

        // Olu�turulanlar� serimleyelim...
        a�a�Paneli.setPreferredSize (new Dimension (300, 150));
        add (a�a�Paneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0, 3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizlelButonu);

        add (panel, BorderLayout.SOUTH);
    } // J4a_6x1() kurucusu sonu...

    public void a�ac�Kur (J4a_6x2 a�a�Paneli) {
        String e1Ad� = new String ("Ebeveyn 1");
        String e2Ad� = new String ("Ebeveyn 2");
        String �1Ad� = new String ("�ocuk 1");
        String �2Ad� = new String ("�ocuk 2");

        DefaultMutableTreeNode e1, e2;

        e1 = a�a�Paneli.nesneyiEkle (null, e1Ad�);
        e2 = a�a�Paneli.nesneyiEkle (null, e2Ad�);

        a�a�Paneli.nesneyiEkle (e1, �1Ad�);
        a�a�Paneli.nesneyiEkle(e1, �2Ad�);

        a�a�Paneli.nesneyiEkle (e2, �1Ad�);
        a�a�Paneli.nesneyiEkle (e2, �2Ad�);
    } // a�ac�Kur(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) {
            a�a�Paneli.nesneyiEkle ("Yeni Dal-" + yeniDalSoneki++);
        }else if (S�L_KOMUTU.equals (komut)) {
            a�a�Paneli.se�ilenDal�Sil();
        }else if (TEM�ZLE_KOMUTU.equals (komut)) {
            a�a�Paneli.temizle();
        } // if-else.. karar� sonu...
    } // actionPerformed(..) metodu sonu...
} // J4a_6x1 s�n�f� sonu...