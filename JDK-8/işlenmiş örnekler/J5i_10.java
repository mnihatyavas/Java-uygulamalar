// J5i_10.java: MouseEventDemo (FareOlay�G�sterisi) �rne�i.

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseListener;
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

// Gereken dosya: J5i_10x.java=BlankArea.java
public class J5i_10 extends JPanel implements MouseListener {
    J5i_10x bo�Alan;
    JTextArea kay�tD�k�m�;
    static final String YEN�SATIR = System.getProperty ("line.separator");
    
    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();} 
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Fare Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_10(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public J5i_10() {//Kurucu...
        super (new GridLayout (0,1));
        bo�Alan = new J5i_10x (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        add (bo�Alan);
        kay�tD�k�m� = new JTextArea();
        kay�tD�k�m�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        kay�tD�k�m�.setForeground (Color.WHITE);
        kay�tD�k�m�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (kay�tD�k�m�);
        kayd�ra�.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kayd�ra�.setPreferredSize (new Dimension (200, 75));
        add (kayd�ra�);
        // Bo� alan ve panele giri�-��k��-t�klama olaylar�n� fare dinleyicisine duyarl�yoruz...
        bo�Alan.addMouseListener (this);
        addMouseListener (this);
        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_10() kurucusu sonu...

    public void mouseEntered (MouseEvent olay) {olay�Kaydet ("Fare giri�i", olay);}
    public void mouseExited (MouseEvent olay) {olay�Kaydet ("Fare ��k���", olay);}
    public void mousePressed (MouseEvent olay) {olay�Kaydet ("Fareye bas�ld� (aral�ks�z t�klama #: " + olay.getClickCount() + ")", olay);}
    public void mouseReleased (MouseEvent olay) {olay�Kaydet ("Fare b�rak�ld� (aral�ks�z t�klama #: " + olay.getClickCount() + ")", olay);}
    public void mouseClicked (MouseEvent olay) {olay�Kaydet ("Fare t�kland� (aral�ks�z t�klama #: " + olay.getClickCount() + ")", olay);}

    void olay�Kaydet (String olayA��klamas�, MouseEvent olay) {
        kay�tD�k�m�.append (olayA��klamas� + " tespit edilen komponent: "
                + olay.getComponent().getClass().getName() + "." + YEN�SATIR);
        kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
    } // olay�Kaydet(..) metodu sonu...
} // J5i_10 s�n�f� sonu...