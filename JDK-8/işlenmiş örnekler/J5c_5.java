// J5c_5.java: ColorChooserDemo (RenkSe�iciG�sterim) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
import javax.swing.BorderFactory;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class J5c_5 extends JPanel implements ChangeListener {
    protected JColorChooser renkSe�ici;
    protected JLabel metinRengiEtiketi;

    public J5c_5() {// Kurucu...
        super (new BorderLayout());

        // Pencerenin �st k�sm�na MET�N renk de�i�im panelini kural�m...
        metinRengiEtiketi = new JLabel ("E�itim B�lgesine Ho�geldiniz!", JLabel.CENTER);
        metinRengiEtiketi.setForeground (Color.yellow);
        metinRengiEtiketi.setBackground (Color.blue);
        metinRengiEtiketi.setOpaque (true); // saydams�z opak tuval...
        metinRengiEtiketi.setFont (new Font ("SansSerif", Font.BOLD, 24));
        metinRengiEtiketi.setPreferredSize (new Dimension (100, 65));

        JPanel metinRengiEtiketiPaneli = new JPanel (new BorderLayout());
        metinRengiEtiketiPaneli.add (metinRengiEtiketi, BorderLayout.CENTER);
        metinRengiEtiketiPaneli.setBorder (BorderFactory.createTitledBorder ("Renkler"));

        // metinRengiEtiketi'ndeki metnin rengini de�i�tirmek i�in renk se�iciyi kural�m...
        renkSe�ici = new JColorChooser (metinRengiEtiketi.getForeground());
        renkSe�ici.getSelectionModel().addChangeListener (this);
        renkSe�ici.setBorder (BorderFactory.createTitledBorder ("Metin Rengini Se�in"));

        add (metinRengiEtiketiPaneli, BorderLayout.CENTER);
        add (renkSe�ici, BorderLayout.PAGE_END);
    } // J5c_5() kurucusu sonu...

    public void stateChanged (ChangeEvent olay) {
        Color yeniRenk = renkSe�ici.getColor();
        metinRengiEtiketi.setForeground (yeniRenk);
    } // stateChanged(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Renk Se�ici G�sterim");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_5();
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
} // J5c_5 s�n�f� sonu...