// J4a_6x1.java: DynamicTreePanel (DinamikAðaçPaneli) alt-örneði.

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
    private static String SÝL_KOMUTU = "tek-sil";
    private static String TEMÝZLE_KOMUTU = "tüm-temizle";
    private J4a_6x2 aðaçPaneli; // J4a_6x2/DynamicTree/DinamikAðaç...

    public J4a_6x1() {
        super (new BorderLayout());

        // Baþlangýç aðaç yapýsýný yaratalým...
        aðaçPaneli = new J4a_6x2();
        aðacýKur (aðaçPaneli);

        JButton ekleButonu = new JButton ("Ekle");
        ekleButonu.setActionCommand (EKLE_KOMUTU);
        ekleButonu.addActionListener (this);
        
        JButton silButonu = new JButton ("Sil");
        silButonu.setActionCommand (SÝL_KOMUTU);
        silButonu.addActionListener (this);
        
        JButton temizlelButonu = new JButton ("Temizle");
        temizlelButonu.setActionCommand (TEMÝZLE_KOMUTU);
        temizlelButonu.addActionListener (this);

        // Oluþturulanlarý serimleyelim...
        aðaçPaneli.setPreferredSize (new Dimension (300, 150));
        add (aðaçPaneli, BorderLayout.CENTER);

        JPanel panel = new JPanel (new GridLayout (0, 3));
        panel.add (ekleButonu);
        panel.add (silButonu); 
        panel.add (temizlelButonu);

        add (panel, BorderLayout.SOUTH);
    } // J4a_6x1() kurucusu sonu...

    public void aðacýKur (J4a_6x2 aðaçPaneli) {
        String e1Adý = new String ("Ebeveyn 1");
        String e2Adý = new String ("Ebeveyn 2");
        String ç1Adý = new String ("Çocuk 1");
        String ç2Adý = new String ("Çocuk 2");

        DefaultMutableTreeNode e1, e2;

        e1 = aðaçPaneli.nesneyiEkle (null, e1Adý);
        e2 = aðaçPaneli.nesneyiEkle (null, e2Adý);

        aðaçPaneli.nesneyiEkle (e1, ç1Adý);
        aðaçPaneli.nesneyiEkle(e1, ç2Adý);

        aðaçPaneli.nesneyiEkle (e2, ç1Adý);
        aðaçPaneli.nesneyiEkle (e2, ç2Adý);
    } // aðacýKur(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE_KOMUTU.equals (komut)) {
            aðaçPaneli.nesneyiEkle ("Yeni Dal-" + yeniDalSoneki++);
        }else if (SÝL_KOMUTU.equals (komut)) {
            aðaçPaneli.seçilenDalýSil();
        }else if (TEMÝZLE_KOMUTU.equals (komut)) {
            aðaçPaneli.temizle();
        } // if-else.. kararý sonu...
    } // actionPerformed(..) metodu sonu...
} // J4a_6x1 sýnýfý sonu...