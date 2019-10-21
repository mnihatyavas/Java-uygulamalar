// J5e_24.java: Wallpaper (Duvarka��d�) �rne�i.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.GradientPaint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

// J5e_18.java=T�kT�kT�k.java, J5e_12.java=Miyop.java ve J5e_4.java=DivaRestoran.java �rnekleri gibidir...
public class J5e_24 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Duvarka��d� G�sterisi");
        JPanel panel = komponentleriKur();
        LayerUI<JComponent> tabaka = new Duvarka��d�Tabakas�();
        JLayer<JComponent> panelliTabaka = new JLayer<JComponent>(panel, tabaka);
        �er�eve.add (panelliTabaka);
        �er�eve.setSize (300, 200);
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private static JPanel komponentleriKur() {
        JPanel panelim = new JPanel();

        ButtonGroup anayemekGrubu = new ButtonGroup();
        JRadioButton radyoD��mesi;
        panelim.add (radyoD��mesi = new JRadioButton ("Biftek"));
        anayemekGrubu.add (radyoD��mesi);
        panelim.add (radyoD��mesi = new JRadioButton ("Pili�"));
        anayemekGrubu.add (radyoD��mesi);
        panelim.add (radyoD��mesi = new JRadioButton ("Yo�urtlu D�ner", true));
        anayemekGrubu.add (radyoD��mesi);

        panelim.add (new JCheckBox ("Ke��ap"));
        panelim.add (new JCheckBox ("Mayonez"));
        panelim.add (new JCheckBox ("Tur�u"));

        panelim.add (new JLabel ("�zel yemek talepleriniz:"));
        panelim.add (new JTextField (20));

        JButton sipari�D��mesi = new JButton ("Sipari�inizi Verin");
        panelim.add (sipari�D��mesi);

        return panelim;
    } // komponentleriKur() metodu sonu...
} // J5e_24 s�n�f� sonu...

class Duvarka��d�Tabakas� extends LayerUI<JComponent> {
    @Override
    public void paint (Graphics g, JComponent k) {
        super.paint (g, k);
        Graphics2D g2 = (Graphics2D) g.create();
        int en = k.getWidth();
        int boy = k.getHeight();
        g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, .5f));
        g2.setPaint (new GradientPaint (0, 0, Color.YELLOW, 0, boy, Color.BLUE));
        g2.fillRect (0, 0, en, boy);
        g2.dispose();
    } // paint(..) haz�r esge�me metodu...
} // Duvarka��d�Tabakas� s�n�f� sonu...