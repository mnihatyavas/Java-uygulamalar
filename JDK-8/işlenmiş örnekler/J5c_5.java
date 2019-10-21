// J5c_5.java: ColorChooserDemo (RenkSeçiciGösterim) örneði.

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
    protected JColorChooser renkSeçici;
    protected JLabel metinRengiEtiketi;

    public J5c_5() {// Kurucu...
        super (new BorderLayout());

        // Pencerenin üst kýsmýna METÝN renk deðiþim panelini kuralým...
        metinRengiEtiketi = new JLabel ("Eðitim Bölgesine Hoþgeldiniz!", JLabel.CENTER);
        metinRengiEtiketi.setForeground (Color.yellow);
        metinRengiEtiketi.setBackground (Color.blue);
        metinRengiEtiketi.setOpaque (true); // saydamsýz opak tuval...
        metinRengiEtiketi.setFont (new Font ("SansSerif", Font.BOLD, 24));
        metinRengiEtiketi.setPreferredSize (new Dimension (100, 65));

        JPanel metinRengiEtiketiPaneli = new JPanel (new BorderLayout());
        metinRengiEtiketiPaneli.add (metinRengiEtiketi, BorderLayout.CENTER);
        metinRengiEtiketiPaneli.setBorder (BorderFactory.createTitledBorder ("Renkler"));

        // metinRengiEtiketi'ndeki metnin rengini deðiþtirmek için renk seçiciyi kuralým...
        renkSeçici = new JColorChooser (metinRengiEtiketi.getForeground());
        renkSeçici.getSelectionModel().addChangeListener (this);
        renkSeçici.setBorder (BorderFactory.createTitledBorder ("Metin Rengini Seçin"));

        add (metinRengiEtiketiPaneli, BorderLayout.CENTER);
        add (renkSeçici, BorderLayout.PAGE_END);
    } // J5c_5() kurucusu sonu...

    public void stateChanged (ChangeEvent olay) {
        Color yeniRenk = renkSeçici.getColor();
        metinRengiEtiketi.setForeground (yeniRenk);
    } // stateChanged(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Renk Seçici Gösterim");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_5();
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
} // J5c_5 sýnýfý sonu...