// J5i_10x.java: BlankArea (BoþAlan) alt-örneði.

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.BorderFactory;

public class J5i_10x extends JLabel {
    Dimension asgariEbat = new Dimension (100, 50);

    public J5i_10x (Color renk) {// Kurucu..
        setBackground (renk);
        setOpaque (true);
        setBorder (BorderFactory.createLineBorder (Color.black));
    } // J5i_10x(..) kurucusu sonu...

    public Dimension getMinimumSize() {return asgariEbat;}
    public Dimension getPreferredSize() {return asgariEbat;}
} // J5i_10x sýnýfý sonu...