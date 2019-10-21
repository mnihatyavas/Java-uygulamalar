// J5e_4.java: Diva (DivaRestoran) �rne�i.

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
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Diva Restoran");
        LayerUI<JPanel> projekt�rl�Tabaka = new Projekt�r();
        JPanel komponentliPaneli = komponentliPaneliYarat();
        JLayer<JPanel> yeni��erikPanosu = new JLayer<JPanel>(komponentliPaneli, projekt�rl�Tabaka);
        �er�eve.add (yeni��erikPanosu);
        �er�eve.setSize (300, 200);
        �er�eve.setLocationRelativeTo (null); // Pencere masa�st�nde ortalans�n...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private static JPanel komponentliPaneliYarat() {
        JPanel panel = new JPanel();

        ButtonGroup radyoD��mesiGrubu = new ButtonGroup();
        JRadioButton radyoD��mesi;
        panel.add (radyoD��mesi = new JRadioButton ("Biftek", true));
        radyoD��mesiGrubu.add (radyoD��mesi);
        panel.add (radyoD��mesi = new JRadioButton ("Pili�"));
        radyoD��mesiGrubu.add (radyoD��mesi);
        panel.add (radyoD��mesi = new JRadioButton ("�oban Salata"));
        radyoD��mesiGrubu.add (radyoD��mesi);

        panel.add (new JCheckBox ("Ke��ap"));
        panel.add (new JCheckBox ("Hardal"));
        panel.add (new JCheckBox ("Tur�u"));

        panel.add (new JLabel ("�zel yemek talepleri:"));
        panel.add (new JTextField (20));

        JButton sipari�D��mesi = new JButton ("Sipari�inizi Verin");
        panel.add (sipari�D��mesi);

        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        return panel;
    } // komponentliPaneliYarat() metodu sonu...
} // J5e_4 s�n�f� sonu...

class Projekt�r extends LayerUI<JPanel> {
    private boolean fareAktifMi;
    private int fareX, fareY;

    @Override
    public void installUI (JComponent komponent) {
        super.installUI (komponent);
        JLayer projekt�rl�Tabaka = (JLayer)komponent;
        projekt�rl�Tabaka.setLayerEventMask (
                AWTEvent.MOUSE_EVENT_MASK |
                AWTEvent.MOUSE_MOTION_EVENT_MASK
        ); // pro.. ifadesi sonu...
    } // installUI(..) haz�r override metodu sonu...

    @Override
    public void uninstallUI (JComponent komponent) {
        JLayer projekt�rl�Tabaka = (JLayer)komponent;
        projekt�rl�Tabaka.setLayerEventMask (0);
        super.uninstallUI (komponent);
    } // uninstallUI(..) haz�r override metodu sonu...

    @Override
    public void paint (Graphics g, JComponent komponent) {
        Graphics2D g2 = (Graphics2D)g.create();

        // G�r�nt�y� boya...
        super.paint (g2, komponent);

        if (fareAktifMi) {
            // Fare odakl� dairesel kademeli transparan/�effafl�k yaratal�m...
            Point2D odakNoktas� = new Point2D.Float (fareX, fareY);
            float yar��ap = 72;
            float[] �effafl�kAral��� = {0.0f, 1.0f};
            Color[] renkAral��� = {new Color (0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
            RadialGradientPaint boya = new RadialGradientPaint (odakNoktas�, yar��ap, �effafl�kAral���, renkAral���);
            g2.setPaint (boya);
            g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, .6f));
            g2.fillRect (0, 0, komponent.getWidth(), komponent.getHeight());
        } // if karar� sonu...

        g2.dispose();
    } // paint(..) haz�r override metodu sonu...

    @Override
    protected void processMouseEvent (MouseEvent olay, JLayer projekt�rl�Tabaka) {
        if (olay.getID() == MouseEvent.MOUSE_ENTERED) fareAktifMi = true;
        if (olay.getID() == MouseEvent.MOUSE_EXITED) fareAktifMi = false;
        projekt�rl�Tabaka.repaint(); // paint(..) metodunu �a��r�r...
    } // processMouseEvent(..) haz�r override metodu sonu...

    @Override
    protected void processMouseMotionEvent (MouseEvent olay, JLayer projekt�rl�Tabaka) {
        Point nokta = SwingUtilities.convertPoint (olay.getComponent(), olay.getPoint(), projekt�rl�Tabaka);
        fareX = nokta.x;
        fareY = nokta.y;
        projekt�rl�Tabaka.repaint(); //  // paint(..) metodunu �a��r�r...
    } // processMouseMotionEvent(..) haz�r override metodu sonu...
} // Projekt�r s�n�f� sonu...