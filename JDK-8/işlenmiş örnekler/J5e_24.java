// J5e_24.java: Wallpaper (Duvarkaðýdý) örneði.

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

// J5e_18.java=TýkTýkTýk.java, J5e_12.java=Miyop.java ve J5e_4.java=DivaRestoran.java örnekleri gibidir...
public class J5e_24 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...

    public static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Duvarkaðýdý Gösterisi");
        JPanel panel = komponentleriKur();
        LayerUI<JComponent> tabaka = new DuvarkaðýdýTabakasý();
        JLayer<JComponent> panelliTabaka = new JLayer<JComponent>(panel, tabaka);
        çerçeve.add (panelliTabaka);
        çerçeve.setSize (300, 200);
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private static JPanel komponentleriKur() {
        JPanel panelim = new JPanel();

        ButtonGroup anayemekGrubu = new ButtonGroup();
        JRadioButton radyoDüðmesi;
        panelim.add (radyoDüðmesi = new JRadioButton ("Biftek"));
        anayemekGrubu.add (radyoDüðmesi);
        panelim.add (radyoDüðmesi = new JRadioButton ("Piliç"));
        anayemekGrubu.add (radyoDüðmesi);
        panelim.add (radyoDüðmesi = new JRadioButton ("Yoðurtlu Döner", true));
        anayemekGrubu.add (radyoDüðmesi);

        panelim.add (new JCheckBox ("Keççap"));
        panelim.add (new JCheckBox ("Mayonez"));
        panelim.add (new JCheckBox ("Turþu"));

        panelim.add (new JLabel ("Özel yemek talepleriniz:"));
        panelim.add (new JTextField (20));

        JButton sipariþDüðmesi = new JButton ("Sipariþinizi Verin");
        panelim.add (sipariþDüðmesi);

        return panelim;
    } // komponentleriKur() metodu sonu...
} // J5e_24 sýnýfý sonu...

class DuvarkaðýdýTabakasý extends LayerUI<JComponent> {
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
    } // paint(..) hazýr esgeçme metodu...
} // DuvarkaðýdýTabakasý sýnýfý sonu...