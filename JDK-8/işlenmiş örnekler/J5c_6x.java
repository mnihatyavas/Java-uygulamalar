// J5c_6x.java: CrayonPanel (PastelPaneli) alt-örneði.

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
    JToggleButton kýrmýzýPastel;
    JToggleButton sarýPastel;
    JToggleButton yeþilPastel;
    JToggleButton maviPastel;

    public void updateChooser() {
        Color renk = getColorFromModel();
        if (Color.red.equals (renk)) kýrmýzýPastel.setSelected (true);
        else if (Color.yellow.equals (renk)) sarýPastel.setSelected (true);
        else if (Color.green.equals (renk)) yeþilPastel.setSelected (true);
        else if (Color.blue.equals (renk)) maviPastel.setSelected (true);
    } // updateChooser() metodu sonu...

    protected JToggleButton pasteliYarat (String isim, Border normalSýnýr) {
        JToggleButton pastelKalem = new JToggleButton();
        pastelKalem.setActionCommand (isim);
        pastelKalem.addActionListener (this);

        // Mevcutsa resimleri, deðilse eþdeðer metni kuralým...
        ImageIcon ikon = resimÝkonunuYarat ("resim/" + isim + ".gif");
        if (ikon != null) {
            pastelKalem.setIcon (ikon);
            pastelKalem.setToolTipText (isim + " pastel kalemi");
            pastelKalem.setBorder (normalSýnýr);
        }else {pastelKalem.setText ("Resim bulunamadý. Bu " + isim + " butondur.");
            pastelKalem.setFont (pastelKalem.getFont().deriveFont (Font.ITALIC));
            pastelKalem.setHorizontalAlignment (JButton.HORIZONTAL);
            pastelKalem.setBorder (BorderFactory.createLineBorder (Color.BLACK));
        } // if-else kararý sonu...

        return pastelKalem;
    } // pasteliYarat(..) metodu sonu...

    protected void buildChooser() {
        setLayout (new GridLayout (0, 1));

        ButtonGroup pastelKutusu = new ButtonGroup();
        Border sýnýr = BorderFactory.createEmptyBorder (4,4,4,4);

        kýrmýzýPastel = pasteliYarat ("kýrmýzý", sýnýr);
        pastelKutusu.add (kýrmýzýPastel);
        add (kýrmýzýPastel);

        sarýPastel = pasteliYarat ("sarý", sýnýr);
        pastelKutusu.add (sarýPastel);
        add (sarýPastel);

        yeþilPastel = pasteliYarat ("yeþil", sýnýr);
        pastelKutusu.add (yeþilPastel);
        add (yeþilPastel);

        maviPastel = pasteliYarat ("mavi", sýnýr);
        pastelKutusu.add (maviPastel);
        add (maviPastel);
    } // buildChooser() metodu sonu...

    // Yol geçerliyse bir ImageIcon/ResimÝkonu, deðilse null döndürür...
    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_6x.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        Color yeniRenk = null;
        String komut = ((JToggleButton)olay.getSource()).getActionCommand();
        if ("yeþil".equals (komut)) yeniRenk = Color.green;
        else if ("kýrmýzý".equals (komut)) yeniRenk = Color.red;
        else if ("sarý".equals (komut)) yeniRenk = Color.yellow;
        else if ("mavi".equals (komut)) yeniRenk = Color.blue;
        getColorSelectionModel().setSelectedColor (yeniRenk);
    } // actionPerformed(..) metodu sonu...

    public String getDisplayName() {return "Pasteller";}
    public Icon getSmallDisplayIcon() {return null;}
    public Icon getLargeDisplayIcon() {return null;}
} // J5c_6x sýnýfý sonu...