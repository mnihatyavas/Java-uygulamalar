// J5e_4.java: Diva (DivaRestoran) örneði.

import java.awt.Color;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.RadialGradientPaint;
import java.awt.AWTEvent;
import java.awt.geom.Point2D;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class J5e_4 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Diva Restoran");
        LayerUI<JPanel> projektörlüTabaka = new Projektör();
        JPanel komponentliPaneli = komponentliPaneliYarat();
        JLayer<JPanel> yeniÝçerikPanosu = new JLayer<JPanel>(komponentliPaneli, projektörlüTabaka);
        çerçeve.add (yeniÝçerikPanosu);
        çerçeve.setSize (300, 200);
        çerçeve.setLocationRelativeTo (null); // Pencere masaüstünde ortalansýn...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private static JPanel komponentliPaneliYarat() {
        JPanel panel = new JPanel();

        ButtonGroup radyoDüðmesiGrubu = new ButtonGroup();
        JRadioButton radyoDüðmesi;
        panel.add (radyoDüðmesi = new JRadioButton ("Biftek", true));
        radyoDüðmesiGrubu.add (radyoDüðmesi);
        panel.add (radyoDüðmesi = new JRadioButton ("Piliç"));
        radyoDüðmesiGrubu.add (radyoDüðmesi);
        panel.add (radyoDüðmesi = new JRadioButton ("Çoban Salata"));
        radyoDüðmesiGrubu.add (radyoDüðmesi);

        panel.add (new JCheckBox ("Keççap"));
        panel.add (new JCheckBox ("Hardal"));
        panel.add (new JCheckBox ("Turþu"));

        panel.add (new JLabel ("Özel yemek talepleri:"));
        panel.add (new JTextField (20));

        JButton sipariþDüðmesi = new JButton ("Sipariþinizi Verin");
        panel.add (sipariþDüðmesi);

        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        return panel;
    } // komponentliPaneliYarat() metodu sonu...
} // J5e_4 sýnýfý sonu...

class Projektör extends LayerUI<JPanel> {
    private boolean fareAktifMi;
    private int fareX, fareY;

    @Override
    public void installUI (JComponent komponent) {
        super.installUI (komponent);
        JLayer projektörlüTabaka = (JLayer)komponent;
        projektörlüTabaka.setLayerEventMask (
                AWTEvent.MOUSE_EVENT_MASK |
                AWTEvent.MOUSE_MOTION_EVENT_MASK
        ); // pro.. ifadesi sonu...
    } // installUI(..) hazýr override metodu sonu...

    @Override
    public void uninstallUI (JComponent komponent) {
        JLayer projektörlüTabaka = (JLayer)komponent;
        projektörlüTabaka.setLayerEventMask (0);
        super.uninstallUI (komponent);
    } // uninstallUI(..) hazýr override metodu sonu...

    @Override
    public void paint (Graphics g, JComponent komponent) {
        Graphics2D g2 = (Graphics2D)g.create();

        // Görüntüyü boya...
        super.paint (g2, komponent);

        if (fareAktifMi) {
            // Fare odaklý dairesel kademeli transparan/þeffaflýk yaratalým...
            Point2D odakNoktasý = new Point2D.Float (fareX, fareY);
            float yarýçap = 72;
            float[] þeffaflýkAralýðý = {0.0f, 1.0f};
            Color[] renkAralýðý = {new Color (0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
            RadialGradientPaint boya = new RadialGradientPaint (odakNoktasý, yarýçap, þeffaflýkAralýðý, renkAralýðý);
            g2.setPaint (boya);
            g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, .6f));
            g2.fillRect (0, 0, komponent.getWidth(), komponent.getHeight());
        } // if kararý sonu...

        g2.dispose();
    } // paint(..) hazýr override metodu sonu...

    @Override
    protected void processMouseEvent (MouseEvent olay, JLayer projektörlüTabaka) {
        if (olay.getID() == MouseEvent.MOUSE_ENTERED) fareAktifMi = true;
        if (olay.getID() == MouseEvent.MOUSE_EXITED) fareAktifMi = false;
        projektörlüTabaka.repaint(); // paint(..) metodunu çaðýrýr...
    } // processMouseEvent(..) hazýr override metodu sonu...

    @Override
    protected void processMouseMotionEvent (MouseEvent olay, JLayer projektörlüTabaka) {
        Point nokta = SwingUtilities.convertPoint (olay.getComponent(), olay.getPoint(), projektörlüTabaka);
        fareX = nokta.x;
        fareY = nokta.y;
        projektörlüTabaka.repaint(); //  // paint(..) metodunu çaðýrýr...
    } // processMouseMotionEvent(..) hazýr override metodu sonu...
} // Projektör sýnýfý sonu...