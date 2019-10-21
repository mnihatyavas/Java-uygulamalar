// J5f_12.java: GridLayoutDemo (IzgaraSerilimGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class J5f_12 extends JFrame {
    static final String aralýkListesi[] = {"0", "5", "10", "15", "20"};
    final static int azamiAralýk = 20;
    JComboBox açýlýrKutu1;
    JComboBox açýlýrKutu2;
    JButton uygulaDüðmesi = new JButton ("Aralýklarý Uygula");
    GridLayout ýzgaraSerilim = new GridLayout (0,2);

    public J5f_12 (String ad) {
        super (ad);
        //setResizable (false); // Pencere ebatlarý müdahalesiz...
    } // J5f_12(..) kurucusu sonu...

    public void komponentleriPanoyaEkle (final Container kab) {
        ilkAralýklar();
        final JPanel düðmelerPaneli = new JPanel();
        düðmelerPaneli.setLayout (ýzgaraSerilim);
        düðmelerPaneli.setBackground (Color.BLUE);
        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.setLayout (new GridLayout (2,3));
        kontrolPaneli.setBackground (Color.ORANGE);

        // Düðme ve panelinin tercihi ebatýný kuralým...
        JButton düðme = new JButton ("Sadece taklit düðmesi");
        Dimension düðmeEbatý = düðme.getPreferredSize();
        düðmelerPaneli.setPreferredSize (new Dimension ((int)(düðmeEbatý.getWidth() * 2.5) + azamiAralýk, (int)(düðmeEbatý.getHeight() * 3.5) + azamiAralýk * 2));

        // Düðmeleri (5 adet) ýzgara serilim paneline ekleyelim...
        düðmelerPaneli.add (new JButton ("Düðme 1"));
        düðmelerPaneli.add (new JButton ("Düðme 2"));
        düðmelerPaneli.add (new JButton ("Düðme 3"));
        düðmelerPaneli.add (new JButton ("Uzun-Ýsimli Düðme 4"));
        düðmelerPaneli.add (new JButton ("5"));

        // Kontrol kaneli komponentlerini kuralým...
        kontrolPaneli.add (new Label ("Yatay aralýk:"));
        kontrolPaneli.add (new Label ("Dikey aralýk:"));
        kontrolPaneli.add (new Label (" "));
        kontrolPaneli.add (açýlýrKutu1);
        kontrolPaneli.add (açýlýrKutu2);
        kontrolPaneli.add (uygulaDüðmesi);

        // Uygulama düðmesini dinleyiciye duyarlayýp, seçilecek aralýklarý uygulatalým...
        uygulaDüðmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay){
                String yatayAralýk = (String)açýlýrKutu1.getSelectedItem();
                String dikeyAralýk = (String)açýlýrKutu2.getSelectedItem();
                ýzgaraSerilim.setHgap (Integer.parseInt (yatayAralýk));
                ýzgaraSerilim.setVgap (Integer.parseInt (dikeyAralýk));
                ýzgaraSerilim.layoutContainer (düðmelerPaneli);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // uyg.. ifadesi sonu...

        // Her 2 paneli de yatay ayraçla kab'a (içerik panosuna) ekleyelim...
        kab.add (düðmelerPaneli, BorderLayout.NORTH);
        kab.add (new JSeparator(), BorderLayout.CENTER);
        kab.add (kontrolPaneli, BorderLayout.SOUTH);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    public void ilkAralýklar() {
        açýlýrKutu1 = new JComboBox (aralýkListesi);
        açýlýrKutu2 = new JComboBox (aralýkListesi);
    } // ilkAralýklar() metodu sonu...

    private static void yaratVeGösterGUI() {
        J5f_12 çerçeve = new J5f_12 ("Izgara Serilim Gösterisi"); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.komponentleriPanoyaEkle (çerçeve.getContentPane());
        çerçeve.setResizable (false); // Pencere ebatlarý müdahalesiz...
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (Exception ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_12 sýnýfý sonu...