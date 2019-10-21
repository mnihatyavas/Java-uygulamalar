// J5c_8.java: ComboBoxDemo2 (A��l�rKutuG�sterimi2) �rne�i.

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
    static JFrame �er�eve;
    JLabel etiket;
    String �imdikiTarihFormat�;

    public J5c_8() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.PAGE_AXIS));
        String[] kal�p�rnekleri = {
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

        �imdikiTarihFormat� = kal�p�rnekleri[9];

        // Bir tarih kal�b� se�me/girme a��klamas� yapal�m...
        JLabel kal�pEtiketi1 = new JLabel ("Kendi �zel tarih kal�b�n�z� girin veya");
        JLabel kal�pEtiketi2 = new JLabel ("mevcut listeden birini se�in ve [ENT]");

        JComboBox kal�pListesi = new JComboBox (kal�p�rnekleri);
        kal�pListesi.setEditable (true);
        kal�pListesi.addActionListener (this);

        // Se�ilen/girilen formatl� zaman� g�r�nt�leme ba�l���n� koyal�m...
        JLabel bi�imliZaman = new JLabel ("Formatl� Son Tarih/Zaman", JLabel.LEADING); //= LEFT

        etiket = new JLabel (" ");
        etiket.setForeground (Color.black);
        etiket.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createLineBorder (Color.black),
                BorderFactory.createEmptyBorder (5,5,5,5)
        )); // eti.. ifadesi sonu...

        // Haz�rlanan komponentleri 2 panele ve sonra da i�erik panosuna ekleyerek serimleyelim...
        JPanel kal�pPaneli1 = new JPanel();
        kal�pPaneli1.setLayout (new BoxLayout (kal�pPaneli1, BoxLayout.PAGE_AXIS));
        kal�pPaneli1.add (kal�pEtiketi1);
        kal�pPaneli1.add (kal�pEtiketi2);
        kal�pListesi.setAlignmentX (Component.LEFT_ALIGNMENT);
        kal�pPaneli1.add (kal�pListesi);

        JPanel kal�pPaneli2 = new JPanel (new GridLayout (0, 1));
        kal�pPaneli2.add (bi�imliZaman);
        kal�pPaneli2.add (etiket);

        kal�pPaneli1.setAlignmentX (Component.LEFT_ALIGNMENT);
        kal�pPaneli2.setAlignmentX (Component.LEFT_ALIGNMENT);

        add (kal�pPaneli1);
        add (Box.createRigidArea (new Dimension (0, 10)));
        add (kal�pPaneli2);

        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));

        // Zemin rengini rasgele yapal�m...
        setBackground (new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));

        yenidenBi�imlendir();
    } // J5c_8() kurucusu sonu...

    // A��l�r kutuda (kay�t se�ip/yaz�p) Ent basmaya duyarl�...
    public void actionPerformed (ActionEvent olay) {
        JComboBox a��l�rKutu = (JComboBox)olay.getSource();
        String yeniSe�im = (String)a��l�rKutu.getSelectedItem();
        �imdikiTarihFormat� = yeniSe�im;
        yenidenBi�imlendir();
    } // actionPerformed(..) metodu sonu...

    // �imdikiTarihFormat�'la g�ncel tarihi bi�imlendirelim...
    public void yenidenBi�imlendir() {
        Date �imdi = new Date();
        try {SimpleDateFormat bi�imlendirici = new SimpleDateFormat (�imdikiTarihFormat�);
            String tarihStr = bi�imlendirici.format (�imdi);
            etiket.setForeground (Color.black);
            etiket.setText (tarihStr);
        }catch (IllegalArgumentException ist) {
            etiket.setForeground (Color.red);
            etiket.setText ("Hata: " + ist.getMessage());
        } // try-catch blo�u sonu...
    } // yenidenBi�imlendir() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A��l�rKutu G�sterimi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_8();
        komponent.setOpaque (true);
        �er�eve.setContentPane (komponent);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_8 s�n�f� sonu...