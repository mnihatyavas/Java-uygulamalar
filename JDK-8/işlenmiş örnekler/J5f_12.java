// J5f_12.java: GridLayoutDemo (IzgaraSerilimG�sterisi) �rne�i.

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
    static final String aral�kListesi[] = {"0", "5", "10", "15", "20"};
    final static int azamiAral�k = 20;
    JComboBox a��l�rKutu1;
    JComboBox a��l�rKutu2;
    JButton uygulaD��mesi = new JButton ("Aral�klar� Uygula");
    GridLayout �zgaraSerilim = new GridLayout (0,2);

    public J5f_12 (String ad) {
        super (ad);
        //setResizable (false); // Pencere ebatlar� m�dahalesiz...
    } // J5f_12(..) kurucusu sonu...

    public void komponentleriPanoyaEkle (final Container kab) {
        ilkAral�klar();
        final JPanel d��melerPaneli = new JPanel();
        d��melerPaneli.setLayout (�zgaraSerilim);
        d��melerPaneli.setBackground (Color.BLUE);
        JPanel kontrolPaneli = new JPanel();
        kontrolPaneli.setLayout (new GridLayout (2,3));
        kontrolPaneli.setBackground (Color.ORANGE);

        // D��me ve panelinin tercihi ebat�n� kural�m...
        JButton d��me = new JButton ("Sadece taklit d��mesi");
        Dimension d��meEbat� = d��me.getPreferredSize();
        d��melerPaneli.setPreferredSize (new Dimension ((int)(d��meEbat�.getWidth() * 2.5) + azamiAral�k, (int)(d��meEbat�.getHeight() * 3.5) + azamiAral�k * 2));

        // D��meleri (5 adet) �zgara serilim paneline ekleyelim...
        d��melerPaneli.add (new JButton ("D��me 1"));
        d��melerPaneli.add (new JButton ("D��me 2"));
        d��melerPaneli.add (new JButton ("D��me 3"));
        d��melerPaneli.add (new JButton ("Uzun-�simli D��me 4"));
        d��melerPaneli.add (new JButton ("5"));

        // Kontrol kaneli komponentlerini kural�m...
        kontrolPaneli.add (new Label ("Yatay aral�k:"));
        kontrolPaneli.add (new Label ("Dikey aral�k:"));
        kontrolPaneli.add (new Label (" "));
        kontrolPaneli.add (a��l�rKutu1);
        kontrolPaneli.add (a��l�rKutu2);
        kontrolPaneli.add (uygulaD��mesi);

        // Uygulama d��mesini dinleyiciye duyarlay�p, se�ilecek aral�klar� uygulatal�m...
        uygulaD��mesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay){
                String yatayAral�k = (String)a��l�rKutu1.getSelectedItem();
                String dikeyAral�k = (String)a��l�rKutu2.getSelectedItem();
                �zgaraSerilim.setHgap (Integer.parseInt (yatayAral�k));
                �zgaraSerilim.setVgap (Integer.parseInt (dikeyAral�k));
                �zgaraSerilim.layoutContainer (d��melerPaneli);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // uyg.. ifadesi sonu...

        // Her 2 paneli de yatay ayra�la kab'a (i�erik panosuna) ekleyelim...
        kab.add (d��melerPaneli, BorderLayout.NORTH);
        kab.add (new JSeparator(), BorderLayout.CENTER);
        kab.add (kontrolPaneli, BorderLayout.SOUTH);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    public void ilkAral�klar() {
        a��l�rKutu1 = new JComboBox (aral�kListesi);
        a��l�rKutu2 = new JComboBox (aral�kListesi);
    } // ilkAral�klar() metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5f_12 �er�eve = new J5f_12 ("Izgara Serilim G�sterisi"); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.komponentleriPanoyaEkle (�er�eve.getContentPane());
        �er�eve.setResizable (false); // Pencere ebatlar� m�dahalesiz...
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (Exception ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_12 s�n�f� sonu...