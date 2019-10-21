// J5i_11.java: MouseMotionEventDemo (FareHareketiOlayýGösterisi) örneði.

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
    J5i_10x boþAlan;
    JTextArea kayýtDökümü;
    static final String YENÝSATIR = System.getProperty ("line.separator");

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Fare Hareketi Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_11(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public J5i_11() {// Kurucu...
        super (new GridLayout (0,1));
        boþAlan = new J5i_10x (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        add (boþAlan);

        kayýtDökümü = new JTextArea();
        kayýtDökümü.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kayýtDökümü.setForeground (Color.WHITE);
        kayýtDökümü.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (kayýtDökümü,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        kaydýraç.setPreferredSize (new Dimension (200, 75));
        add(kaydýraç);

        // Fare hareketlerini boþ alana ve içerik panosuna duyarlayalým...
        boþAlan.addMouseMotionListener (this);
        addMouseMotionListener (this);
        setPreferredSize (new Dimension (450, 450));
        /* Çevre tampon fare hareketlerini de içerik panosunda farzeder...
        * Boþ alan ve çevre tampon harekete ve sürüklemeye duyarlý,
        * ancak müdahalesiz metin alaný duyarsýzdýr. */
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_11() kurucusu sonu...

    public void mouseMoved (MouseEvent olay) {hareketiGöster ("Fare hareketlendi", olay);}
    public void mouseDragged (MouseEvent olay) {hareketiGöster ("Fare sürüklendi", olay);}

    void hareketiGöster (String olayAçýklamasý, MouseEvent olay) {
        kayýtDökümü.append (olayAçýklamasý + " (" + olay.getX() + "," + olay.getY() + ")"
                + "==>Ýlgili komponent: " + olay.getComponent().getClass().getName()
                + YENÝSATIR);
        kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
    } // hareketiGöster(..) metodu sonu...
} // J5i_11 sýnýfý sonu...