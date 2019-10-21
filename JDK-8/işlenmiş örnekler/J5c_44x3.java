// J5c_44x3.java: Corner (Köþe) alt-örneði.

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;

public class J5c_44x3 extends JComponent {
    protected void paintComponent (Graphics g) {
        // Cetvel içini kirli turuncu'yla boyar...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (0, 0, getWidth(), getHeight());
    } // paintComponent(..) hazýr metodu sonu...
} // J5c_44x3 sýnýfý sonu...