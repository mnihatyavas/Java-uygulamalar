// J5e_18.java: TapTapTap (TýkTýkTýk) örneði.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLayer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComponent;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

import java.beans.PropertyChangeEvent;

public class J5e_18 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {new J5e_18().yaratGUI();}
        }); // Swi.. isoldursi sonu...
    } // main(..) metodu sonu...

    private JButton yemekSipariþDüðmesi;

    public void yaratGUI() {
        JFrame çerçeve = new JFrame ("Týk Týk Týk");
        final DönerliBekletmeTabakasý tabaka = new DönerliBekletmeTabakasý();
        JPanel panel = komponentleriKur();
        panel.setBackground (Color.CYAN);
        JLayer<JPanel> komponentliTabaka = new JLayer<JPanel>(panel, tabaka);

        final Timer zamanlayýcý = new Timer (5000, new ActionListener() {
            public void actionPerformed (ActionEvent olay) {tabaka.stop();}
        }); // fin.. isoldursi sonu...
        zamanlayýcý.setRepeats (false); // Zamanlayýcý 5 sn'liðine 1 kez çalýþacak, tekrarlanmayacak...

        // Sipariþ düðmesiyle, ve kesintili olarak bekleme tabakalý zamanlayýcýyý çalýþtýrýr...
        yemekSipariþDüðmesi.addActionListener (
            new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    tabaka.start();
                    if (!zamanlayýcý.isRunning()) zamanlayýcý.start();
        }}); // yem.. isoldursi sonu...

    çerçeve.add (komponentliTabaka);
    
    çerçeve.setSize(300, 200);
    çerçeve.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    çerçeve.setLocationRelativeTo (null);
    çerçeve.setVisible (true);
  } // yaratGUI() metodu sonu...

    private JPanel komponentleriKur() {
        JPanel p = new JPanel();

        ButtonGroup yemekGrubu = new ButtonGroup();
        JRadioButton radyoDüðmesi;
        p.add (radyoDüðmesi = new JRadioButton ("Biftek", true));
        yemekGrubu.add (radyoDüðmesi);
        p.add (radyoDüðmesi = new JRadioButton ("Piliç"));
        yemekGrubu.add (radyoDüðmesi);
        p.add (radyoDüðmesi = new JRadioButton ("Yoðurtlu Döner"));
        yemekGrubu.add (radyoDüðmesi);

        p.add (new JCheckBox ("Keççap"));
        p.add (new JCheckBox ("Mayonez"));
        p.add (new JCheckBox ("Turþu"));

        p.add (new JLabel ("Özel Yemek Ýstekleriniz:"));
        p.add (new JTextField (20));

        yemekSipariþDüðmesi = new JButton ("Sipariþinizi Verin");
        p.add (yemekSipariþDüðmesi);

        return p;
    } // komponentleriKur() metodu sonu...
} // J5e_18 sýnýfý sonu...

class DönerliBekletmeTabakasý extends LayerUI<JPanel> implements ActionListener {
    private boolean çalýþýyorMu;
    private boolean duracakMý;
    private Timer zamanlayýcý;

    private int açý;
    private int sayaç;
    private int sýnýr = 15;

    @Override
    public void paint (Graphics g, JComponent k) {
        int en = k.getWidth();
        int boy = k.getHeight();

        // Ýçerik panosu komponentini boyayalým...
        super.paint (g, k);

        if (!çalýþýyorMu) return;

        Graphics2D g2 = (Graphics2D)g.create();

        float soldur = (float)sayaç / (float)sýnýr;
        // Bekleme tabakasýný grileþtirelim...
        Composite kompozit = g2.getComposite();
        g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, .5f * soldur));
        g2.fillRect (0, 0, en, boy);
        g2.setComposite (kompozit);

        // Bekletme çemberini boyayalým...
        int s = Math.min (en, boy) / 5;
        int cx = en / 2;
        int cy = boy / 2;
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke (new BasicStroke (s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setPaint (Color.white);
        g2.rotate (Math.PI * açý / 180, cx, cy);

        for (int i = 0; i < 12; i++) {
            float ölçek = (11.0f - (float)i) / 11.0f;
            g2.drawLine (cx + s, cy, cx + s * 2, cy);
            g2.rotate (-Math.PI / 6, cx, cy);
            g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, ölçek * soldur));
        } // for döngüsü sonu...

        g2.dispose();
    } // paint(..) hazýr esgeçme metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (çalýþýyorMu) {
            firePropertyChange ("týk", 0, 1);
            açý += 3;
            if (açý >= 360) açý = 0;
            if (duracakMý) {
                if (--sayaç == 0) {
                    çalýþýyorMu = false;
                    zamanlayýcý.stop();
                } // iç-if kararý sonu...
            }else if (sayaç < sýnýr) sayaç++;
        } // dýþ-if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    public void start() {
        if (çalýþýyorMu) return;

        // Animasyon için bir sicim çalýþtýralým...
        çalýþýyorMu = true;
        duracakMý = false;
        sayaç = 0;
        int fps = 24;
        int týk = 1000 / fps;

        zamanlayýcý = new Timer (týk, this);
        zamanlayýcý.start();
    } // start() hazýr metodu sonu...

    public void stop() {duracakMý = true;}

    @Override
    public void applyPropertyChange (PropertyChangeEvent pce, JLayer tabaka) {
        if ("týk".equals(pce.getPropertyName())) tabaka.repaint();
    } // applyPropertyChange(..) hazýr esgeçme metodu sonu...
} // DönerliBekletmeTabakasý sýnýfý sonu...