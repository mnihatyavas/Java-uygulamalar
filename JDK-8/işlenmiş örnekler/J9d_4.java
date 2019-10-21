// J9d_4.java: FontSelector (FonSe�ici) �rne�i.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JApplet;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class J9d_4 extends JApplet implements ChangeListener, ItemListener {
    MetinDenemePaneli dizgeDenemePaneli;
    JComboBox fonlar, stiller;
    JSpinner ebatlar;
    String se�ilenFon = "Dialog";
    int se�ilenStil = 0;
    int se�ilenEbat = 12;
    static String metin;

    public static void main (String s[]) {
        metin = s.length > 0? s[0] : "Atik k�z�l tilki tembel k�pe�in �st�ne atlad�.";
        JFrame �er�eve = new JFrame ("Yaz� Fonu, Stili ve Ebat� Se�icisi");
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        JApplet fonSe�ici = new J9d_4(); // Varsay�l� parametresiz kuruculu, aplet referansl� s�n�f nesnesi yarat�lmas�...
        �er�eve.add (fonSe�ici, BorderLayout.CENTER);
        fonSe�ici.init();
        �er�eve.setLocation (450, 50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {}

        JPanel fonSe�iciPanel = new JPanel();

        // �lk a��l�r kutu...
        fonSe�iciPanel.add (new JLabel ("Yaz� fonu ad�:"));
        GraphicsEnvironment grafik�evresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonlar = new JComboBox (grafik�evresi.getAvailableFontFamilyNames());
        fonlar.setSelectedItem (se�ilenFon);
        fonlar.setMaximumRowCount (5);
        fonlar.addItemListener (this); // Yaz� fonu adlar� dinleyiciye duyarl�...
        fonSe�iciPanel.add (fonlar);

        // �kinci a��l�r kutu...
        fonSe�iciPanel.add(new JLabel("Stili:"));
        String[] stilAdlar� = {"Plain", "Bold", "Italic", "Bold Italic"};
        stiller = new JComboBox (stilAdlar�);
        stiller.addItemListener (this); // Yaz� stili adlar� dinleyiciye duyarl�...
        fonSe�iciPanel.add (stiller);

        // Yaz� ebat� �ark�...
        fonSe�iciPanel.add (new JLabel ("Ebat�:"));
        ebatlar = new JSpinner (new SpinnerNumberModel (12, 6, 50, 1));
        ebatlar.addChangeListener (this); // Yaz� ebat� de�i�ikli�i dinleyiciye duyarl�...
        fonSe�iciPanel.add (ebatlar);

        dizgeDenemePaneli = new MetinDenemePaneli();
        dizgeDenemePaneli.setFont (new Font (se�ilenFon, se�ilenStil, se�ilenEbat));
        //dizgeDenemePaneli.setBackground (Color.white);

        add (BorderLayout.NORTH, fonSe�iciPanel);
        add (BorderLayout.CENTER, dizgeDenemePaneli);
    } // init() JApplet haz�r metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() != ItemEvent.SELECTED) return;

        if (olay.getSource() == fonlar) se�ilenFon = (String)fonlar.getSelectedItem();
        else se�ilenStil = stiller.getSelectedIndex();

        dizgeDenemePaneli.setFont (new Font (se�ilenFon, se�ilenStil, se�ilenEbat));
    } // itemStateChanged(..) haz�r metodu sonu...

    public void stateChanged (ChangeEvent olay) {
        try {String size = ebatlar.getModel().getValue().toString();
            se�ilenEbat = Integer.parseInt (size);
            dizgeDenemePaneli.setFont (new Font (se�ilenFon, se�ilenStil, se�ilenEbat));
        }catch (NumberFormatException ist) {}
    } // stateChanged(..) haz�r metodu sonu...
} // J9d_4 s�n�f� sonu...

class MetinDenemePaneli extends JComponent {
    public Dimension getPreferredSize() {return new Dimension (700,150);}
    public void setFont (Font yaz�Fonu) {super.setFont (yaz�Fonu); repaint();}

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        g.setColor (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ) ); // Tesad�fi a��k renkler...
        g.fillRect (0, 0, getWidth(), getHeight());
        g.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) ); // Tesad�fi koyu renkler...
        g.setFont (getFont());
        FontMetrics fon�l�ekleri = g.getFontMetrics();
        String dizge = J9d_4.metin;
        int x = getWidth()/2 - fon�l�ekleri.stringWidth (dizge)/2;
        int y = getHeight() - 80;
        g.drawString (dizge, x, y);
    } // paintComponent(..) haz�r metodu sonu...
} // MetinDenemePaneli s�n�f� sonu...