// J8e_9x.java: ShowString (G�sterilenDizge) alt-�rne�i.

import java.awt.Color;
import java.awt.Frame;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class J8e_9x extends Frame {
    FontMetrics yaz�Tipi�l�e�i;
    String ��kt�Dizgesi;
    
    J8e_9x (String hedef, String ba�l�k) {// Kurucu...
        setTitle (ba�l�k);
        ��kt�Dizgesi = hedef;

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {System.exit (0);}
        }); // add.. ifadesi sonu...

        setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
        setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.75f) );

        Font yaz�Tipi = new Font ("Monospaced", Font.PLAIN, 36);
        yaz�Tipi�l�e�i = getFontMetrics (yaz�Tipi);
        setFont (yaz�Tipi);

        int en = 0;
        for (int i = 0; i < ��kt�Dizgesi.length(); i++) en += yaz�Tipi�l�e�i.charWidth (��kt�Dizgesi.charAt (i));
        en += 24;
        setSize (en, yaz�Tipi�l�e�i.getHeight() + 60);
        setLocation (getSize().width/2, getSize().height/2);
        setVisible (true);
    } // J8e_9x(..) kurucusu sonu...

    public void paint (Graphics g) {
        Insets bo�luk = getInsets();
        int x = bo�luk.left;
        int y = bo�luk.top;
        g.drawString (��kt�Dizgesi, x + 6, y + yaz�Tipi�l�e�i.getAscent() + 14);
    } // paint(..) haz�r metodu sonu...
} // J8e_9x s�n�f� sonu...