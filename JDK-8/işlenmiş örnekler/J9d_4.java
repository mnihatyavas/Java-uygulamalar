// J9d_4.java: FontSelector (FonSeçici) örneði.

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
    String seçilenFon = "Dialog";
    int seçilenStil = 0;
    int seçilenEbat = 12;
    static String metin;

    public static void main (String s[]) {
        metin = s.length > 0? s[0] : "Atik kýzýl tilki tembel köpeðin üstüne atladý.";
        JFrame çerçeve = new JFrame ("Yazý Fonu, Stili ve Ebatý Seçicisi");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        JApplet fonSeçici = new J9d_4(); // Varsayýlý parametresiz kuruculu, aplet referanslý sýnýf nesnesi yaratýlmasý...
        çerçeve.add (fonSeçici, BorderLayout.CENTER);
        fonSeçici.init();
        çerçeve.setLocation (450, 50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...

    public void init() {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {}

        JPanel fonSeçiciPanel = new JPanel();

        // Ýlk açýlýr kutu...
        fonSeçiciPanel.add (new JLabel ("Yazý fonu adý:"));
        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fonlar = new JComboBox (grafikÇevresi.getAvailableFontFamilyNames());
        fonlar.setSelectedItem (seçilenFon);
        fonlar.setMaximumRowCount (5);
        fonlar.addItemListener (this); // Yazý fonu adlarý dinleyiciye duyarlý...
        fonSeçiciPanel.add (fonlar);

        // Ýkinci açýlýr kutu...
        fonSeçiciPanel.add(new JLabel("Stili:"));
        String[] stilAdlarý = {"Plain", "Bold", "Italic", "Bold Italic"};
        stiller = new JComboBox (stilAdlarý);
        stiller.addItemListener (this); // Yazý stili adlarý dinleyiciye duyarlý...
        fonSeçiciPanel.add (stiller);

        // Yazý ebatý çarký...
        fonSeçiciPanel.add (new JLabel ("Ebatý:"));
        ebatlar = new JSpinner (new SpinnerNumberModel (12, 6, 50, 1));
        ebatlar.addChangeListener (this); // Yazý ebatý deðiþikliði dinleyiciye duyarlý...
        fonSeçiciPanel.add (ebatlar);

        dizgeDenemePaneli = new MetinDenemePaneli();
        dizgeDenemePaneli.setFont (new Font (seçilenFon, seçilenStil, seçilenEbat));
        //dizgeDenemePaneli.setBackground (Color.white);

        add (BorderLayout.NORTH, fonSeçiciPanel);
        add (BorderLayout.CENTER, dizgeDenemePaneli);
    } // init() JApplet hazýr metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        if (olay.getStateChange() != ItemEvent.SELECTED) return;

        if (olay.getSource() == fonlar) seçilenFon = (String)fonlar.getSelectedItem();
        else seçilenStil = stiller.getSelectedIndex();

        dizgeDenemePaneli.setFont (new Font (seçilenFon, seçilenStil, seçilenEbat));
    } // itemStateChanged(..) hazýr metodu sonu...

    public void stateChanged (ChangeEvent olay) {
        try {String size = ebatlar.getModel().getValue().toString();
            seçilenEbat = Integer.parseInt (size);
            dizgeDenemePaneli.setFont (new Font (seçilenFon, seçilenStil, seçilenEbat));
        }catch (NumberFormatException ist) {}
    } // stateChanged(..) hazýr metodu sonu...
} // J9d_4 sýnýfý sonu...

class MetinDenemePaneli extends JComponent {
    public Dimension getPreferredSize() {return new Dimension (700,150);}
    public void setFont (Font yazýFonu) {super.setFont (yazýFonu); repaint();}

    public void paintComponent (Graphics g) {
        super.paintComponent (g);
        g.setColor (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) ) ); // Tesadüfi açýk renkler...
        g.fillRect (0, 0, getWidth(), getHeight());
        g.setColor (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) ); // Tesadüfi koyu renkler...
        g.setFont (getFont());
        FontMetrics fonÖlçekleri = g.getFontMetrics();
        String dizge = J9d_4.metin;
        int x = getWidth()/2 - fonÖlçekleri.stringWidth (dizge)/2;
        int y = getHeight() - 80;
        g.drawString (dizge, x, y);
    } // paintComponent(..) hazýr metodu sonu...
} // MetinDenemePaneli sýnýfý sonu...