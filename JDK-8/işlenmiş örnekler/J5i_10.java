// J5i_10.java: MouseEventDemo (FareOlayýGösterisi) örneði.

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
    J5i_10x boþAlan;
    JTextArea kayýtDökümü;
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
        JFrame çerçeve = new JFrame ("Fare Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_10(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public J5i_10() {//Kurucu...
        super (new GridLayout (0,1));
        boþAlan = new J5i_10x (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        add (boþAlan);
        kayýtDökümü = new JTextArea();
        kayýtDökümü.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        kayýtDökümü.setForeground (Color.WHITE);
        kayýtDökümü.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (kayýtDökümü);
        kaydýraç.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kaydýraç.setPreferredSize (new Dimension (200, 75));
        add (kaydýraç);
        // Boþ alan ve panele giriþ-çýkýþ-týklama olaylarýný fare dinleyicisine duyarlýyoruz...
        boþAlan.addMouseListener (this);
        addMouseListener (this);
        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_10() kurucusu sonu...

    public void mouseEntered (MouseEvent olay) {olayýKaydet ("Fare giriþi", olay);}
    public void mouseExited (MouseEvent olay) {olayýKaydet ("Fare çýkýþý", olay);}
    public void mousePressed (MouseEvent olay) {olayýKaydet ("Fareye basýldý (aralýksýz týklama #: " + olay.getClickCount() + ")", olay);}
    public void mouseReleased (MouseEvent olay) {olayýKaydet ("Fare býrakýldý (aralýksýz týklama #: " + olay.getClickCount() + ")", olay);}
    public void mouseClicked (MouseEvent olay) {olayýKaydet ("Fare týklandý (aralýksýz týklama #: " + olay.getClickCount() + ")", olay);}

    void olayýKaydet (String olayAçýklamasý, MouseEvent olay) {
        kayýtDökümü.append (olayAçýklamasý + " tespit edilen komponent: "
                + olay.getComponent().getClass().getName() + "." + YENÝSATIR);
        kayýtDökümü.setCaretPosition (kayýtDökümü.getDocument().getLength());
    } // olayýKaydet(..) metodu sonu...
} // J5i_10 sýnýfý sonu...