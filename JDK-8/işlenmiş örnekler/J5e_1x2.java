// J5e_1x2.java: Corner (K��e) alt-�rne�i.

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;

public class J5e_1x2 extends JComponent implements Accessible {
    protected void paintComponent (Graphics g) {
        // Kayd�rma-cetvel k��elerini (kertiksiz) cetvel rengine boyar...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (0, 0, getWidth(), getHeight());
    } // paintComponent(..) haz�r metodu sonu...

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) accessibleContext = new Eri�ilebilirK��e();
        return accessibleContext;
    } // getAccessibleContext() haz�r metodu sonu...

    protected class Eri�ilebilirK��e extends AccessibleJComponent {
        // Her�eyi miraslar, override/esge�me yok...
    } // Eri�ilebilirK��e s�n�f� sonu...
} // J5e_1x2 s�n�f� sonu...