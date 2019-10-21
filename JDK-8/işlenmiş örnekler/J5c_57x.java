// J5c_57x.java: SizeDisplayer (EbatG�sterici) alt-�rne�i.

import javax.swing.JComponent;
import javax.swing.Icon;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Graphics;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class J5c_57x extends JComponent {
    private Icon ikon;
    private String metin;
    private int xMetinTamponu = 5;
    private int yMetinTamponu = 5;
    private Rectangle metinEbat�R = new Rectangle();
    private Dimension metinEbat�D = new Dimension();
    
    private Dimension tercihiEbat, // null
            enk���kEbat, // null
            enb�y�kEbat; // null
    
    public J5c_57x (String metin, Icon ikon) {
        this.metin = metin;
        this.ikon = ikon;
        setOpaque (true);
    } // J5c_57x(..) kurucusu sonu...
    
    protected void paintComponent (Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        Dimension minSize = getMinimumSize();
        Dimension prefSize = getPreferredSize();
        Dimension size = getSize();
        int prefX = 0, prefY = 0;

        // Set hints so text looks nice.
        g2d.setRenderingHint (RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint (RenderingHints.KEY_RENDERING,
                             RenderingHints.VALUE_RENDER_QUALITY);
                             
        // Draw the maximum size rectangle if we're opaque.
        if (isOpaque()) {
            g2d.setColor (getBackground());
            g2d.fillRect (0, 0, size.width, size.height);
        }
        
        // Draw the icon.
        if (ikon != null) {
            Composite oldComposite = g2d.getComposite();
            g2d.setComposite (AlphaComposite.getInstance (
                                      AlphaComposite.SRC_OVER,
                                      0.4f)); // (0->1) �effaf->opak...
            ikon.paintIcon (this, g2d,
                           (size.width - ikon.getIconWidth())/2,
                           (size.height - ikon.getIconHeight())/2);
            g2d.setComposite (oldComposite);
        }
                
        // Draw the preferred size rectangle.
        prefX = (size.width - prefSize.width)/2;
        prefY = (size.height - prefSize.height)/2;
        g2d.setColor (Color.RED);
        g2d.drawRect (prefX, prefY, prefSize.width - 2, prefSize.height - 2);

        // Draw the minimum size rectangle.
        if (minSize.width != prefSize.width || minSize.height != prefSize.height) {
            int minX = (size.width - minSize.width)/2;
            int minY = (size.height - minSize.height)/2;
            g2d.setColor (Color.CYAN);
            g2d.drawRect (minX, minY, minSize.width - 2, minSize.height - 2);
        }
        
        //Draw the text.
        if (metin != null) {
            Dimension metinEbat� = getTextSize (g2d);
            g2d.setColor (getForeground());
            g2d.drawString (metin,
                           (size.width - metinEbat�.width)/2,
                           (size.height - metinEbat�.height)/2
                            + g2d.getFontMetrics().getAscent());
        }
        g2d.dispose();
    } // paintComponent(..) haz�r metodu sonu...
        
    private Dimension getTextSize (Graphics2D g2d) {
        if (metin == null) {
            metinEbat�D.setSize (0,0);
        } else {
            FontRenderContext frc;
            if (g2d != null) {
                frc = g2d.getFontRenderContext();
            } else {
                frc = new FontRenderContext (null, false, false);
            }
            Rectangle2D textRect = getFont().getStringBounds (metin, frc);
            metinEbat�R.setRect (textRect);
            metinEbat�D.setSize (metinEbat�R.width, metinEbat�R.height);
        }
        
        return metinEbat�D;
    } // getTextSize(..) haz�r metodu sonu...
        
    public Dimension getMinimumSize() {
        if (enk���kEbat != null) { //user has set the min size
            return enk���kEbat;
        } else {
            return getPreferredSize();
        }
    } // getMinimumSize() haz�r metodu sonu...
    
    public Dimension getPreferredSize() {
        if (tercihiEbat != null) { //user has set the pref size
            return tercihiEbat;
        } else {
            return calculatePreferredSize();
        }
    } // getPreferredSize() haz�r metodu sonu...
    
    public Dimension getMaximumSize() {
        if (enb�y�kEbat != null) { //user has set the max size
            return enb�y�kEbat;
        } else {
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }
    } // getMaximumSize() haz�r metodu sonu...
    
    public void setMinimumSize (Dimension newSize) {enk���kEbat = newSize;}
    public void setPreferredSize(Dimension newSize) {tercihiEbat = newSize;}
    public void setMaximumSize(Dimension newSize) {enb�y�kEbat = newSize;}
    
    private Dimension calculatePreferredSize() {
        Insets insets = getInsets();
        Dimension metinEbat� = getTextSize(null);
        int iconWidth = 0;
        int iconHeight = 0;
        
        if (ikon != null) {
            iconWidth = ikon.getIconWidth();
            iconHeight = ikon.getIconHeight();
        }

        return new Dimension (
                Math.max (iconWidth, metinEbat�.width + 2*xMetinTamponu)
                + insets.left + insets.right,
                Math.max (iconHeight, metinEbat�.height + 2*yMetinTamponu)
                + insets.top + insets.bottom);
    } // calculatePreferredSize() haz�r metodu sonu...
} // J5c_57x s�n�f� sonu...