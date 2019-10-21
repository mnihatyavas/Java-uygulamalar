// J5i_12.java: MouseWheelEventDemo (FareTekeriOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

public class J5i_12 extends JPanel implements MouseWheelListener {
    JTextArea kay�tD�k�m�;
    JScrollPane kayd�ra�;
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
        JFrame �er�eve = new JFrame ("Fare Tekeri Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_12(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public J5i_12() {// Kurucu...
        super (new BorderLayout());

        kay�tD�k�m� = new JTextArea();
        kay�tD�k�m�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 065f) );
        kay�tD�k�m�.setForeground (Color.WHITE);
        kay�tD�k�m�.setEditable (false); // M�dahalesiz...
        kayd�ra� = new JScrollPane (kay�tD�k�m�);
        kayd�ra�.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kayd�ra�.setPreferredSize (new Dimension (400, 250));
        add (kayd�ra�, BorderLayout.CENTER);
        kay�tD�k�m�.append ("Bu metin alan�, �zerinde fare tekeri hareketi olaylar�n� g�sterir." + YEN�SATIR);
        kay�tD�k�m�.addMouseWheelListener (this); // Metin alan� dinleyiciye duyarl�...
        
        setPreferredSize (new Dimension (450, 350));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_12() kurucusu sonu...

    // Metin alan�nda fare teker olay� yaz�l�r; dikey kayd�ra� �zerinde ise (yazmadan) kayd�rac� yukar�/a�a�� kayd�r�r...
    public void mouseWheelMoved (MouseWheelEvent olay) {
        String mesaj;
        int kertik = olay.getWheelRotation();
        if (kertik < 0) mesaj = "Fare tekeri YUKARI " + -kertik + " kertik d�nd�" + YEN�SATIR;
        else mesaj = "Fare tekeri A�A�I " + kertik + " kertik d�nd�" + YEN�SATIR;

        if (olay.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            mesaj += "    Kayma tipi: WHEEL_UNIT_SCROLL/TEKER_B�R�M�_KAYMASI" + YEN�SATIR;
            mesaj += "    Kayma miktar�: " + " herbir kertik i�in " + olay.getScrollAmount() + " birim art��" + YEN�SATIR;
            mesaj += "    Kayma birimi: " + olay.getUnitsToScroll() + " birim art��" + YEN�SATIR;
            mesaj += "    Dikey birim art��: " + kayd�ra�.getVerticalScrollBar().getUnitIncrement (1) + " piksel" + YEN�SATIR;
        }else {mesaj += "    Kayma tipi: WHEEL_BLOCK_SCROLL/TEKER_BLO�U_KAYMASI" + YEN�SATIR; // Ne demekse?..
            mesaj += "    Dikey blok art��: " + kayd�ra�.getVerticalScrollBar().getBlockIncrement (1) + " piksel" + YEN�SATIR;
        } // if-else blo�u sonu...

        olay�G�ster (mesaj, olay);
    } // mouseWheelMoved(..) haz�r metodu sonu...

    void olay�G�ster (String msj, MouseWheelEvent olay) {
        kay�tD�k�m�.append (olay.getComponent().getClass().getName() + ": " + msj);
        kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
    } // olay�G�ster(..) metodu sonu...
} // J5i_12 s�n�f� sonu...