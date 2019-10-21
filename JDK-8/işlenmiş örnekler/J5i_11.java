// J5i_11.java: MouseMotionEventDemo (FareHareketiOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

// Gereken dosya: J5i_10x.java= BlankArea.java
public class J5i_11 extends JPanel implements MouseMotionListener {
    J5i_10x bo�Alan;
    JTextArea kay�tD�k�m�;
    static final String YEN�SATIR = System.getProperty ("line.separator");

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Fare Hareketi Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_11(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public J5i_11() {// Kurucu...
        super (new GridLayout (0,1));
        bo�Alan = new J5i_10x (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        add (bo�Alan);

        kay�tD�k�m� = new JTextArea();
        kay�tD�k�m�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kay�tD�k�m�.setForeground (Color.WHITE);
        kay�tD�k�m�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (kay�tD�k�m�,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        kayd�ra�.setPreferredSize (new Dimension (200, 75));
        add(kayd�ra�);

        // Fare hareketlerini bo� alana ve i�erik panosuna duyarlayal�m...
        bo�Alan.addMouseMotionListener (this);
        addMouseMotionListener (this);
        setPreferredSize (new Dimension (450, 450));
        /* �evre tampon fare hareketlerini de i�erik panosunda farzeder...
        * Bo� alan ve �evre tampon harekete ve s�r�klemeye duyarl�,
        * ancak m�dahalesiz metin alan� duyars�zd�r. */
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_11() kurucusu sonu...

    public void mouseMoved (MouseEvent olay) {hareketiG�ster ("Fare hareketlendi", olay);}
    public void mouseDragged (MouseEvent olay) {hareketiG�ster ("Fare s�r�klendi", olay);}

    void hareketiG�ster (String olayA��klamas�, MouseEvent olay) {
        kay�tD�k�m�.append (olayA��klamas� + " (" + olay.getX() + "," + olay.getY() + ")"
                + "==>�lgili komponent: " + olay.getComponent().getClass().getName()
                + YEN�SATIR);
        kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
    } // hareketiG�ster(..) metodu sonu...
} // J5i_11 s�n�f� sonu...