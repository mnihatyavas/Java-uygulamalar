// J9d_8.java: SimpleDrawString (BasitÇizimDizgesi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class J9d_8 {
    public static void main (String[] argüman) {
        String dizge = argüman.length > 0? argüman[0] : "Basit g2D.drawString örneði.";
        Frame çerçeve = new Frame ("Grafikle Dizge Çizimi") {
            public void paint (Graphics g) {
                Graphics2D g2D = (Graphics2D)g;
                Font fon = new Font ("Arial", Font.BOLD, 48);
                g2D.setFont (fon);
                g2D.setColor (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tesadüfi tüm renkler...
                g2D.drawString (dizge, 40, 80);
                fon = new Font ("Verdana", Font.ITALIC, 18);
                g2D.setFont (fon);
                g2D.drawString ("Yeni yazý rengi için, pencere ebatýný kapat/aç!..", 40, 120);
            } // paint(..) hazýr metodu sonu...
        }; // Fra..ifadesi sonu...
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        çerçeve.setVisible (true);
        çerçeve.setSize (750, 150);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setBackground (Color.CYAN);
    } // main(..) metodu sonu...
} // J9d_8 sýnýfý sonu...