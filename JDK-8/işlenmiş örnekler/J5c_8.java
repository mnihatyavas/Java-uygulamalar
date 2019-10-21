// J5c_8.java: ComboBoxDemo2 (AçýlýrKutuGösterimi2) örneði.

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.util.Date;
import java.text.SimpleDateFormat;

public class J5c_8 extends JPanel implements ActionListener {
    static JFrame çerçeve;
    JLabel etiket;
    String þimdikiTarihFormatý;

    public J5c_8() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));
        String[] kalýpÖrnekleri = {
            "dd MMMMM yyyy",
            "dd.MM.yy",
            "MM/dd/yy",
            "yyyy.MM.dd G 'at' hh:mm:ss z",
            "EEE, MMM d, ''yy",
            "h:mm a",
            "H:mm:ss:SSS",
            "K:mm a,z",
            "yyyy.MMMM.dd G hh:mm a",
            "EEEE, d MMMM, yyyy; hh:mm:ss a"
        }; // Str.. ifadesi sonu...

        þimdikiTarihFormatý = kalýpÖrnekleri[9];

        // Bir tarih kalýbý seçme/girme açýklamasý yapalým...
        JLabel kalýpEtiketi1 = new JLabel ("Kendi özel tarih kalýbýnýzý girin veya");
        JLabel kalýpEtiketi2 = new JLabel ("mevcut listeden birini seçin ve [ENT]");

        JComboBox kalýpListesi = new JComboBox (kalýpÖrnekleri);
        kalýpListesi.setEditable (true);
        kalýpListesi.addActionListener (this);

        // Seçilen/girilen formatlý zamaný görüntüleme baþlýðýný koyalým...
        JLabel biçimliZaman = new JLabel ("Formatlý Son Tarih/Zaman", JLabel.LEADING); //= LEFT

        etiket = new JLabel (" ");
        etiket.setForeground (Color.black);
        etiket.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLineBorder (Color.black),
                BorderFactory.createEmptyBorder (5,5,5,5)
        )); // eti.. ifadesi sonu...

        // Hazýrlanan komponentleri 2 panele ve sonra da içerik panosuna ekleyerek serimleyelim...
        JPanel kalýpPaneli1 = new JPanel();
        kalýpPaneli1.setLayout (new BoxLayout (kalýpPaneli1, BoxLayout.PAGE_AXIS));
        kalýpPaneli1.add (kalýpEtiketi1);
        kalýpPaneli1.add (kalýpEtiketi2);
        kalýpListesi.setAlignmentX (Component.LEFT_ALIGNMENT);
        kalýpPaneli1.add (kalýpListesi);

        JPanel kalýpPaneli2 = new JPanel (new GridLayout (0, 1));
        kalýpPaneli2.add (biçimliZaman);
        kalýpPaneli2.add (etiket);

        kalýpPaneli1.setAlignmentX (Component.LEFT_ALIGNMENT);
        kalýpPaneli2.setAlignmentX (Component.LEFT_ALIGNMENT);

        add (kalýpPaneli1);
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (kalýpPaneli2);

        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        // Zemin rengini rasgele yapalým...
        setBackground (new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));

        yenidenBiçimlendir();
    } // J5c_8() kurucusu sonu...

    // Açýlýr kutuda (kayýt seçip/yazýp) Ent basmaya duyarlý...
    public void actionPerformed (ActionEvent olay) {
        JComboBox açýlýrKutu = (JComboBox)olay.getSource();
        String yeniSeçim = (String)açýlýrKutu.getSelectedItem();
        þimdikiTarihFormatý = yeniSeçim;
        yenidenBiçimlendir();
    } // actionPerformed(..) metodu sonu...

    // þimdikiTarihFormatý'la güncel tarihi biçimlendirelim...
    public void yenidenBiçimlendir() {
        Date þimdi = new Date();
        try {SimpleDateFormat biçimlendirici = new SimpleDateFormat (þimdikiTarihFormatý);
            String tarihStr = biçimlendirici.format (þimdi);
            etiket.setForeground (Color.black);
            etiket.setText (tarihStr);
        }catch (IllegalArgumentException ist) {
            etiket.setForeground (Color.red);
            etiket.setText ("Hata: " + ist.getMessage());
        } // try-catch bloðu sonu...
    } // yenidenBiçimlendir() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("AçýlýrKutu Gösterimi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_8();
        komponent.setOpaque (true);
        çerçeve.setContentPane (komponent);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_8 sýnýfý sonu...