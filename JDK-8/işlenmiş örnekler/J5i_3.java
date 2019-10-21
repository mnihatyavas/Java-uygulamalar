// J5i_3.java: ContainerEventDemo (KabOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ContainerListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.util.Vector;

public class J5i_3 extends JPanel implements ContainerListener, ActionListener {
    JTextArea metinAlaný;
    JPanel butonPaneli;
    JButton ekleDüðmesi, silDüðmesi, temizleDüðmesi;
    Vector<JButton> düðmeListesi;
    static final String EKLE = "ekle";
    static final String SÝL = "sil";
    static final String TEMÝZLE = "temizle";
    static final String yeniSatýr = "\n";

    public J5i_3() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ýzgaraÇantasý = (GridBagLayout)getLayout();
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        // Boþ bir düðme listesi yaratalým...
        düðmeListesi = new Vector<JButton>();

        // Tüm komponent parçalarý (ekle-sil-temizle düðmeleri, düðme-paneli, kaydýrmalý metin alaný) yaratalým...
        ekleDüðmesi = new JButton ("Alt panele yeni bir düðme ekle");
        ekleDüðmesi.setActionCommand (EKLE);
        ekleDüðmesi.addActionListener (this); // Dinleyiciye duyarlayalým...

        silDüðmesi = new JButton ("Mevcut sondaki bir düðmeyi sil");
        silDüðmesi.setActionCommand (SÝL);
        silDüðmesi.addActionListener (this); // Dinleyiciye duyarlayalým...

        butonPaneli = new JPanel (new GridLayout (1,1));
        butonPaneli.setPreferredSize (new Dimension (200, 75));
        butonPaneli.addContainerListener (this); // Dinleyiciye duyarlayalým...

        metinAlaný = new JTextArea();
        metinAlaný.setBackground (Color.CYAN);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýrma = new JScrollPane (metinAlaný);
        kaydýrma.setPreferredSize (new Dimension (200, 75));

        temizleDüðmesi = new JButton ("Metin alaný içeriklerini tamamen temizle");
        temizleDüðmesi.setActionCommand (TEMÝZLE);
        temizleDüðmesi.addActionListener (this); // Dinleyiciye duyarlayalým...

        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH; // Fill entire cell.
        sýnýrlayýcýlar.weighty = 1.0;  // Y dikey boyu kaydýrma ve buton paneline eþit bölünecek...
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER; // Paylaþýmsýz satýr sonu...
        ýzgaraÇantasý.setConstraints (kaydýrma, sýnýrlayýcýlar);
        add (kaydýrma);

        sýnýrlayýcýlar.weighty = 0.0;
        ýzgaraÇantasý.setConstraints (temizleDüðmesi, sýnýrlayýcýlar);
        add (temizleDüðmesi);

        sýnýrlayýcýlar.weightx = 1.0;  // X yatay eni ekle ve sil düðmesine eþit bölünecek...
        sýnýrlayýcýlar.gridwidth = 1;  // Ence henüz satýr sonu DEÐÝL...
        ýzgaraÇantasý.setConstraints (ekleDüðmesi, sýnýrlayýcýlar);
        add (ekleDüðmesi);

        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER; // Ence satýr sonu...
        ýzgaraÇantasý.setConstraints (silDüðmesi, sýnýrlayýcýlar);
        add (silDüðmesi);

        sýnýrlayýcýlar.weighty = 1.0; // Kaydýrma ve buton paneline eþit yükseklik...
        ýzgaraÇantasý.setConstraints (butonPaneli, sýnýrlayýcýlar);
        add (butonPaneli);

        setPreferredSize (new Dimension(450, 400));
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_3() kurucusu sonu...

    // Ekle-sil-temizle düðme olaylarýna duyarlý...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE.equals (komut)) {
            JButton düðme = new JButton ("JButton #"
                                          + (düðmeListesi.size() + 1));
            düðmeListesi.addElement (düðme);
            butonPaneli.add (düðme);
            butonPaneli.revalidate(); // Yeni eklenen düðme görünsün...

        }else if (SÝL.equals (komut)) {
            int sonEndeks = düðmeListesi.size() - 1;
            try {JButton düðme = düðmeListesi.elementAt (sonEndeks);
                butonPaneli.remove (düðme);
                düðmeListesi.removeElementAt (sonEndeks);
                butonPaneli.revalidate(); // Silinen düðme görüntüden çýksýn...
                //butonPaneli.repaint();
            }catch (ArrayIndexOutOfBoundsException ist) {} // Olmayan düðmeyi sileceksen aldýrma...
        }else if (TEMÝZLE.equals (komut)) metinAlaný.setText("");
    } // actionPerformed(..) hazýr metodu sonu...

    public void componentAdded (ContainerEvent olay) {mesajYaz ("'e eklendi...", olay);}
    public void componentRemoved (ContainerEvent olay) {mesajYaz ("'den silindi...", olay);}

    void mesajYaz (String hareket, ContainerEvent olay) {
        metinAlaný.append (((JButton)olay.getChild()).getText() + "==>"
                + olay.getContainer().getClass().getName() + hareket + yeniSatýr);
        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
    } // mesajYaz(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kab Olaylarý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_3(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_3 sýnýfý sonu...