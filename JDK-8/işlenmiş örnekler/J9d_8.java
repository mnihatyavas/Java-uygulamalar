// J9d_8.java: SimpleDrawString (Basit�izimDizgesi) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class J9d_8 {
    public static void main (String[] arg�man) {
        String dizge = arg�man.length > 0? arg�man[0] : "Basit g2D.drawString �rne�i.";
        Frame �er�eve = new Frame ("Grafikle Dizge �izimi") {
            public void paint (Graphics g) {
                Graphics2D g2D = (Graphics2D)g;
                Font fon = new Font ("Arial", Font.BOLD, 48);
                g2D.setFont (fon);
                g2D.setColor (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tesad�fi t�m renkler...
                g2D.drawString (dizge, 40, 80);
                fon = new Font ("Verdana", Font.ITALIC, 18);
                g2D.setFont (fon);
                g2D.drawString ("Yeni yaz� rengi i�in, pencere ebat�n� kapat/a�!..", 40, 120);
            } // paint(..) haz�r metodu sonu...
        }; // Fra..ifadesi sonu...
        �er�eve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        �er�eve.setVisible (true);
        �er�eve.setSize (750, 150);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setBackground (Color.CYAN);
    } // main(..) metodu sonu...
} // J9d_8 s�n�f� sonu...