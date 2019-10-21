// J5c_6x.java: CrayonPanel (PastelPaneli) alt-�rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.BorderFactory;

import javax.swing.border.Border;

import javax.swing.colorchooser.AbstractColorChooserPanel;

public class J5c_6x extends AbstractColorChooserPanel implements ActionListener {
    JToggleButton k�rm�z�Pastel;
    JToggleButton sar�Pastel;
    JToggleButton ye�ilPastel;
    JToggleButton maviPastel;

    public void updateChooser() {
        Color renk = getColorFromModel();
        if (Color.red.equals (renk)) k�rm�z�Pastel.setSelected (true);
        else if (Color.yellow.equals (renk)) sar�Pastel.setSelected (true);
        else if (Color.green.equals (renk)) ye�ilPastel.setSelected (true);
        else if (Color.blue.equals (renk)) maviPastel.setSelected (true);
    } // updateChooser() metodu sonu...

    protected JToggleButton pasteliYarat (String isim, Border normalS�n�r) {
        JToggleButton pastelKalem = new JToggleButton();
        pastelKalem.setActionCommand (isim);
        pastelKalem.addActionListener (this);

        // Mevcutsa resimleri, de�ilse e�de�er metni kural�m...
        ImageIcon ikon = resim�konunuYarat ("resim/" + isim + ".gif");
        if (ikon != null) {
            pastelKalem.setIcon (ikon);
            pastelKalem.setToolTipText (isim + " pastel kalemi");
            pastelKalem.setBorder (normalS�n�r);
        }else {pastelKalem.setText ("Resim bulunamad�. Bu " + isim + " butondur.");
            pastelKalem.setFont (pastelKalem.getFont().deriveFont (Font.ITALIC));
            pastelKalem.setHorizontalAlignment (JButton.HORIZONTAL);
            pastelKalem.setBorder (BorderFactory.createLineBorder (Color.BLACK));
        } // if-else karar� sonu...

        return pastelKalem;
    } // pasteliYarat(..) metodu sonu...

    protected void buildChooser() {
        setLayout (new GridLayout (0, 1));

        ButtonGroup pastelKutusu = new ButtonGroup();
        Border s�n�r = BorderFactory.createEmptyBorder (4,4,4,4);

        k�rm�z�Pastel = pasteliYarat ("k�rm�z�", s�n�r);
        pastelKutusu.add (k�rm�z�Pastel);
        add (k�rm�z�Pastel);

        sar�Pastel = pasteliYarat ("sar�", s�n�r);
        pastelKutusu.add (sar�Pastel);
        add (sar�Pastel);

        ye�ilPastel = pasteliYarat ("ye�il", s�n�r);
        pastelKutusu.add (ye�ilPastel);
        add (ye�ilPastel);

        maviPastel = pasteliYarat ("mavi", s�n�r);
        pastelKutusu.add (maviPastel);
        add (maviPastel);
    } // buildChooser() metodu sonu...

    // Yol ge�erliyse bir ImageIcon/Resim�konu, de�ilse null d�nd�r�r...
    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_6x.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        Color yeniRenk = null;
        String komut = ((JToggleButton)olay.getSource()).getActionCommand();
        if ("ye�il".equals (komut)) yeniRenk = Color.green;
        else if ("k�rm�z�".equals (komut)) yeniRenk = Color.red;
        else if ("sar�".equals (komut)) yeniRenk = Color.yellow;
        else if ("mavi".equals (komut)) yeniRenk = Color.blue;
        getColorSelectionModel().setSelectedColor (yeniRenk);
    } // actionPerformed(..) metodu sonu...

    public String getDisplayName() {return "Pasteller";}
    public Icon getSmallDisplayIcon() {return null;}
    public Icon getLargeDisplayIcon() {return null;}
} // J5c_6x s�n�f� sonu...