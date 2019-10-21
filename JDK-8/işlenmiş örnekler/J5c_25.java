// J5c_25.java: InternalFrameDemo (ÝçPencereGösterimi) örneði.

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/* Gereken dosya:
 * J5c_25x.java=MyInternalFrame.java
 */
public class J5c_25 extends JFrame implements ActionListener {
    JDesktopPane masaüstüPanosu;

    public J5c_25() {// Kurucu...
        super ("Ýç Pencere Gösterimi"); // Büyük pencere yaratýlýr...

        // Büyük penceremiz ekran kenarlarýndan 50*2=100 piksel içerden ebatlansýn..
        int ekranMarjý = 50;
        Dimension ekranEbatý = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds (ekranMarjý, ekranMarjý, ekranEbatý.width  - ekranMarjý * 2, ekranEbatý.height - ekranMarjý * 2);

        // GUI/GraphicsUnitInterface/GrafikBirimiArayüzü'müzü kuralým...
        masaüstüPanosu = new JDesktopPane(); // Ýç küçük pencereleri taþýrmayacak özel katmanlý bir pano...
        pencereYarat(); // Ýlk "küçük" penceremizi yaratalým...
        setContentPane (masaüstüPanosu); // Küçük pencereli masaüstüPanosu'nu büyük pencereye kuralým/ekleyelim...
        masaüstüPanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        setJMenuBar (menüÇubuðuYarat()); // Menü çubuðunu yaratýp penceremize kuralým/ekleyelim...

        // Küçük iç pencereleri büyük pencere içinde (taþýrmadan) sürükleme kipini açalým...
        masaüstüPanosu.setDragMode (JDesktopPane.OUTLINE_DRAG_MODE);
    } // J5c_25() kurucusu sonu...

    // Yeni bir (çoklu küçük iç) pencere yaratalým...
    protected void pencereYarat() {
        J5c_25x pencere = new J5c_25x();
        pencere.setVisible (true);
        masaüstüPanosu.add (pencere);
        pencere.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        try {pencere.setSelected (true);
        } catch (java.beans.PropertyVetoException ist) {}
    } // pencereYarat() metodu sonu...

    protected JMenuBar menüÇubuðuYarat() {
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Döküman menüsünü yaratalým...
        JMenu menü = new JMenu ("Döküman");
        menü.setMnemonic (KeyEvent.VK_D);
        menüÇubuðu.add (menü);

        // Ýlk "Yeni" menü birimini kuralým...
        JMenuItem menüBirimi = new JMenuItem ("Yeni");
        menüBirimi.setMnemonic (KeyEvent.VK_Y);
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_Y, ActionEvent.ALT_MASK));
        menüBirimi.setActionCommand ("yeni");
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        // Ýkinci "Çýk" menü birimini kuralým...
        menüBirimi = new JMenuItem ("Çýk");
        menüBirimi.setMnemonic (KeyEvent.VK_K); // Türkçe Ç-ý reddediliyor...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK));
        menüBirimi.setActionCommand ("çýk");
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        return menüÇubuðu;
    } // menüÇubuðuYarat() metodu sonu...

    // Menü seçimlerimlerine duyarlý...
    public void actionPerformed (ActionEvent olay) {
        if ("yeni".equals (olay.getActionCommand())) pencereYarat();
        else çýk();
    } // actionPerformed(..) hazýr metodu sonu...

    // Çýkarken uygulamayý kapatalým...
    protected void çýk() {System.exit (0);}

    private static void yaratVeGösterGUI() {
        // DokunVeHissetSüslü penceremizi garantileyelim...
        JFrame.setDefaultLookAndFeelDecorated (true);

        // Penceremizi yaratýp, görünür kýlalým...
        J5c_25 çerçeve = new J5c_25();
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_25 sýnýfý sonu...