// J5e_1x2.java: Corner (Köþe) alt-örneði.

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;

import javax.accessibility.Accessible;
import javax.accessibility.AccessibleContext;

public class J5e_1x2 extends JComponent implements Accessible {
    protected void paintComponent (Graphics g) {
        // Kaydýrma-cetvel köþelerini (kertiksiz) cetvel rengine boyar...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (0, 0, getWidth(), getHeight());
    } // paintComponent(..) hazýr metodu sonu...

    public AccessibleContext getAccessibleContext() {
        if (accessibleContext == null) accessibleContext = new EriþilebilirKöþe();
        return accessibleContext;
    } // getAccessibleContext() hazýr metodu sonu...

    protected class EriþilebilirKöþe extends AccessibleJComponent {
        // Herþeyi miraslar, override/esgeçme yok...
    } // EriþilebilirKöþe sýnýfý sonu...
} // J5e_1x2 sýnýfý sonu...