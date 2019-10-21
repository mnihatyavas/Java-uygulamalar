// J5i_12.java: MouseWheelEventDemo (FareTekeriOlayýGösterisi) örneði.

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
    JTextArea kayýtDökümü;
    JScrollPane kaydýraç;
    static final String YENÝSATIR = System.getProperty ("line.separator");

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Fare Tekeri Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_12(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public J5i_12() {// Kurucu...
        super (new BorderLayout());

        kayýtDökümü = new JTextArea();
        kayýtDökümü.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 065f) );
        kayýtDökümü.setForeground (Color.WHITE);
        kayýtDökümü.setEditable (false); // Müdahalesiz...
        kaydýraç = new JScrollPane (kayýtDökümü);
        kaydýraç.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kaydýraç.setPreferredSize (new Dimension (400, 250));
        add (kaydýraç, BorderLayout.CENTER);
        kayýtDökümü.append ("Bu metin alaný, üzerinde fare tekeri hareketi olaylarýný gösterir." + YENÝSATIR);
        kayýtDökümü.addMouseWheelListener (this); // Metin alaný dinleyiciye duyarlý...
        
        setPreferredSize (new Dimension (450, 350));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_12() kurucusu sonu...

    // Metin alanýnda fare teker olayý yazýlýr; dikey kaydýraç üzerinde ise (yazmadan) kaydýracý yukarý/aþaðý kaydýrýr...
    public void mouseWheelMoved (MouseWheelEvent olay) {
        String mesaj;
        int kertik = olay.getWheelRotation();
        if (kertik < 0) mesaj = "Fare tekeri YUKARI " + -kertik + " kertik döndü" + YENÝSATIR;
        else mesaj = "Fare tekeri AÞAÐI " + kertik + " kertik döndü" + YENÝSATIR;

        if (olay.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            mesaj += "    Kayma tipi: WHEEL_UNIT_SCROLL/TEKER_BÝRÝMÝ_KAYMASI" + YENÝSATIR;
            mesaj += "    Kayma miktarý: " + " herbir kertik için " + olay.getScrollAmount() + " birim artýþ" + YENÝSATIR;
            mesaj += "    Kayma birimi: " + olay.getUnitsToScroll() + " birim artýþ" + YENÝSATIR;
            mesaj += "    Dikey birim artýþ: " + kaydýraç.getVerticalScrollBar().getUnitIncrement (1) + " piksel" + YENÝSATIR;
        }else {mesaj += "    Kayma tipi: WHEEL_BLOCK_SCROLL/TEKER_BLOÐU_KAYMASI" + YENÝSATIR; // Ne demekse?..
            mesaj += "    Dikey blok artýþ: " + kaydýraç.getVerticalScrollBar().getBlockIncrement (1) + " piksel" + YENÝSATIR;
        } // if-else bloðu sonu...

        olayýGöster (mesaj, olay);
    } // mouseWheelMoved(..) hazýr metodu sonu...

    void olayýGöster (String msj, MouseWheelEvent olay) {
        kayýtDökümü.append (olay.getComponent().getClass().getName() + ": " + msj);
        kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
    } // olayýGöster(..) metodu sonu...
} // J5i_12 sýnýfý sonu...