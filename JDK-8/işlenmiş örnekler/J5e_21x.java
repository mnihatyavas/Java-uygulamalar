// J5e_21x.java: Picture (Resim) alt-�rne�i.

import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

import javax.accessibility.Accessible;

class J5e_21x extends JComponent
            implements MouseListener, FocusListener, Accessible {
    Image resim;

    public J5e_21x (Image resim) {
        this.resim = resim;
        setFocusable (true);
        addMouseListener (this);
        addFocusListener (this);
    } // J5e_21x(..) kurucusu sonu...

    public void mouseClicked (MouseEvent olay) {requestFocusInWindow();}
    public void mouseEntered (MouseEvent olay){}
    public void mouseExited (MouseEvent olay){}
    public void mousePressed (MouseEvent olay){}
    public void mouseReleased (MouseEvent olay) {}

    public void focusGained (FocusEvent olay) {this.repaint();} // Odaklanan resme k�rm�z� �er�eve...
    public void focusLost (FocusEvent olay) {this.repaint();} // Odaks�zlanan resme siyah �er�eve...

    protected void paintComponent (Graphics grafik) {
        Graphics g = grafik.create();
        g.setColor (Color.YELLOW); // Resim zemini sar�...
        g.fillRect (0, 0, resim == null ? 125 : resim.getWidth (this), resim == null ? 125 : resim.getHeight (this));
        if (resim != null) g.drawImage (resim, 0, 0, this); // Resmi kur...
        if (isFocusOwner()) g.setColor (Color.RED); // Odakl�ysa k�rm�z� �er�eve...
        else g.setColor (Color.BLACK); // Odaks�zsa siyah �er�eve...
        g.drawRect (0, 0, resim == null ? 125 : resim.getWidth(this)+1, resim == null ? 125 : resim.getHeight(this)+1);
        g.dispose();
    } // paintComponent(..) haz�r metodu sonu...
} // J5e_21x s�n�f� sonu...