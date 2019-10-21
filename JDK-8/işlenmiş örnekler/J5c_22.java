// J5c_22.java: GlassPaneDemo (CamPanoGösterimi) örneði.

import java.awt.Container;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.FlowLayout;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JComponent;
import javax.swing.AbstractButton;
import javax.swing.SwingUtilities;

import javax.swing.event.MouseInputAdapter;

public class J5c_22 {
    static private CamPanom camPanom;

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Cam Pano Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Komponentleri yaratýp ekleyelim...
        JCheckBox çentikKutusu = new JCheckBox ("Cam pano \"þeffaf\"");
        /* Çentiklendiðinde menü çubuðu ve içerik panosunu þeffaf camla kapar
         * ve onlarý görünseler de etkisiz kýlar, týklandýðýnda sadece kýrmýzý bir
         * nokta belirir, ta ki çentiksizlene dek...*/
        çentikKutusu.setSelected (false);
        
        // 2 butonu ve çentik kutusunu ekleyeceðimiz içerik panosunu kuralým...
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setLayout (new FlowLayout());
        içerikPanosu.add (çentikKutusu);
        içerikPanosu.add (new JButton("Buton-1"));
        içerikPanosu.add (new JButton("Buton-2"));

        // Ýçerik panosu üstüne menü çubuðunu kuralým...
        JMenuBar menüÇubuðu = new JMenuBar();
        JMenu menü = new JMenu ("Menü");
        menü.add (new JMenuItem ("Aç"));
        menü.add (new JMenuItem ("Sakla"));
        menü.add (new JMenuItem ("Çýk"));
        menüÇubuðu.add (menü);
        çerçeve.setJMenuBar (menüÇubuðu);

        // Cam panoyu kuralým. Menü çubuðunu ve içerik panosunu þeffaf kapatacaktýr.
        // Sadece çentik kutusu dinleyiciye duyarlý olacaktýr...
        camPanom = new CamPanom (çentikKutusu, menüÇubuðu, çerçeve.getContentPane());
        çentikKutusu.addItemListener (camPanom);
        çerçeve.setGlassPane (camPanom);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_22 sýnýfý sonu...

// Cam panomuz mevcutken (çentikli) týklayýnca kýrmýzý nokta çizebilmeliyiz...
class CamPanom extends JComponent implements ItemListener {
    Point nokta;

    public CamPanom (AbstractButton soyutButon, JMenuBar menüÇubuðu, Container içerikPanosu) {
        ÇKDinleyici dinleyici = new ÇKDinleyici (soyutButon, menüÇubuðu, this, içerikPanosu);
        addMouseListener (dinleyici);
        addMouseMotionListener (dinleyici);
    } // CamPanom(..) kurucusu sonu...

    // Çentikli kutunun týklanmasýna duyarlý kýlalým...
    public void itemStateChanged (ItemEvent olay) {setVisible (olay.getStateChange() == ItemEvent.SELECTED);}
    public void setPoint (Point nokta) {this.nokta = nokta;}

    protected void paintComponent (Graphics g) {
        if (nokta != null) {
            g.setColor (Color.red);
            g.fillOval (nokta.x - 10, nokta.y - 10, 20, 20);
        } // if kararý sonu...
    } // paintComponent(..) hazýr metodu sonu...
} // CamPanom sýnýfý sonu...

// ÇK/Çentik kutusunu ilgilendiren tüm fare olaylarýný dinler ve raporlar...
class ÇKDinleyici extends MouseInputAdapter {
    Toolkit aletkutusu;
    Component çentikKutusu;
    JMenuBar menüÇubuðu;
    CamPanom camPano;
    Container içerikPanosu;

    public ÇKDinleyici (Component çentikKutusu, JMenuBar menüÇubuðu, CamPanom camPano, Container içerikPanosu) {
        aletkutusu = Toolkit.getDefaultToolkit();
        this.çentikKutusu = çentikKutusu;
        this.menüÇubuðu = menüÇubuðu;
        this.camPano = camPano;
        this.içerikPanosu = içerikPanosu;
    } // ÇKDinleyici(..) kurucusu sonu...

    public void mouseMoved (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mouseDragged (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mouseClicked (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mouseEntered (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mouseExited (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mousePressed (MouseEvent olay) {fareOlayýnýRaporla (olay, false);}
    public void mouseReleased (MouseEvent olay) {fareOlayýnýRaporla (olay, true);}

    private void fareOlayýnýRaporla (MouseEvent olay, boolean repaint) {
        Point camPanoNoktasý = olay.getPoint();
        Container içerik = içerikPanosu;
        Point içerikNoktasý = SwingUtilities.convertPoint (camPano, camPanoNoktasý, içerikPanosu);
        if (içerikNoktasý.y < 0) { // Ýçerik panosunda deðilsek...
            if ((içerikNoktasý.y + menüÇubuðu.getHeight()) >= 0) { 
                // Fare olayý menü çubuðu üzerinde demektir...
                // Ýstediðin yönetimi buraya kodla...
            }else { 
                // Fare olayý dýþardadýr demektir... Ona göre yönet....
            }
        }else {
            // Fare içerik panosunda demektir...
            // Kesin hangi komponent üzerinde olduðunu saptayalým...
            Component komponent = SwingUtilities.getDeepestComponentAt (içerik, içerikNoktasý.x, içerikNoktasý.y);
            if ((komponent != null) && (komponent.equals (çentikKutusu))) {
                // Olaylarý çentik kutusu üzerine yönlendir...
                Point komponentNoktasý = SwingUtilities.convertPoint (camPano, camPanoNoktasý, komponent);
                komponent.dispatchEvent (new MouseEvent (komponent, olay.getID(), olay.getWhen(), olay.getModifiers(), komponentNoktasý.x, komponentNoktasý.y, olay.getClickCount(), olay.isPopupTrigger()));
            } // iç-if kararý sonu...
        } // dýþ else kararý sonu...

        // Gerekiyorsa cam panoyo güncelleyelim...
        if (repaint) {
            camPano.setPoint (camPanoNoktasý);
            camPano.repaint();
        } // if kararý sonu...
    } // fareOlayýnýRaporla(..) metodu sonu...
} // ÇKDinleyici sýnýfý sonu...