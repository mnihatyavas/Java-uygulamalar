// J5c_44x3.java: Corner (K��e) alt-�rne�i.

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;

public class J5c_44x3 extends JComponent {
    protected void paintComponent (Graphics g) {
        // Cetvel i�ini kirli turuncu'yla boyar...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (0, 0, getWidth(), getHeight());
    } // paintComponent(..) haz�r metodu sonu...
} // J5c_44x3 s�n�f� sonu...