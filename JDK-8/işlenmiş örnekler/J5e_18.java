// J5e_18.java: TapTapTap (T�kT�kT�k) �rne�i.

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

    private JButton yemekSipari�D��mesi;

    public void yaratGUI() {
        JFrame �er�eve = new JFrame ("T�k T�k T�k");
        final D�nerliBekletmeTabakas� tabaka = new D�nerliBekletmeTabakas�();
        JPanel panel = komponentleriKur();
        panel.setBackground (Color.CYAN);
        JLayer<JPanel> komponentliTabaka = new JLayer<JPanel>(panel, tabaka);

        final Timer zamanlay�c� = new Timer (5000, new ActionListener() {
            public void actionPerformed (ActionEvent olay) {tabaka.stop();}
        }); // fin.. isoldursi sonu...
        zamanlay�c�.setRepeats (false); // Zamanlay�c� 5 sn'li�ine 1 kez �al��acak, tekrarlanmayacak...

        // Sipari� d��mesiyle, ve kesintili olarak bekleme tabakal� zamanlay�c�y� �al��t�r�r...
        yemekSipari�D��mesi.addActionListener (
            new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    tabaka.start();
                    if (!zamanlay�c�.isRunning()) zamanlay�c�.start();
        }}); // yem.. isoldursi sonu...

    �er�eve.add (komponentliTabaka);
    
    �er�eve.setSize(300, 200);
    �er�eve.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    �er�eve.setLocationRelativeTo (null);
    �er�eve.setVisible (true);
  } // yaratGUI() metodu sonu...

    private JPanel komponentleriKur() {
        JPanel p = new JPanel();

        ButtonGroup yemekGrubu = new ButtonGroup();
        JRadioButton radyoD��mesi;
        p.add (radyoD��mesi = new JRadioButton ("Biftek", true));
        yemekGrubu.add (radyoD��mesi);
        p.add (radyoD��mesi = new JRadioButton ("Pili�"));
        yemekGrubu.add (radyoD��mesi);
        p.add (radyoD��mesi = new JRadioButton ("Yo�urtlu D�ner"));
        yemekGrubu.add (radyoD��mesi);

        p.add (new JCheckBox ("Ke��ap"));
        p.add (new JCheckBox ("Mayonez"));
        p.add (new JCheckBox ("Tur�u"));

        p.add (new JLabel ("�zel Yemek �stekleriniz:"));
        p.add (new JTextField (20));

        yemekSipari�D��mesi = new JButton ("Sipari�inizi Verin");
        p.add (yemekSipari�D��mesi);

        return p;
    } // komponentleriKur() metodu sonu...
} // J5e_18 s�n�f� sonu...

class D�nerliBekletmeTabakas� extends LayerUI<JPanel> implements ActionListener {
    private boolean �al���yorMu;
    private boolean duracakM�;
    private Timer zamanlay�c�;

    private int a��;
    private int saya�;
    private int s�n�r = 15;

    @Override
    public void paint (Graphics g, JComponent k) {
        int en = k.getWidth();
        int boy = k.getHeight();

        // ��erik panosu komponentini boyayal�m...
        super.paint (g, k);

        if (!�al���yorMu) return;

        Graphics2D g2 = (Graphics2D)g.create();

        float soldur = (float)saya� / (float)s�n�r;
        // Bekleme tabakas�n� grile�tirelim...
        Composite kompozit = g2.getComposite();
        g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, .5f * soldur));
        g2.fillRect (0, 0, en, boy);
        g2.setComposite (kompozit);

        // Bekletme �emberini boyayal�m...
        int s = Math.min (en, boy) / 5;
        int cx = en / 2;
        int cy = boy / 2;
        g2.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke (new BasicStroke (s / 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setPaint (Color.white);
        g2.rotate (Math.PI * a�� / 180, cx, cy);

        for (int i = 0; i < 12; i++) {
            float �l�ek = (11.0f - (float)i) / 11.0f;
            g2.drawLine (cx + s, cy, cx + s * 2, cy);
            g2.rotate (-Math.PI / 6, cx, cy);
            g2.setComposite (AlphaComposite.getInstance (AlphaComposite.SRC_OVER, �l�ek * soldur));
        } // for d�ng�s� sonu...

        g2.dispose();
    } // paint(..) haz�r esge�me metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (�al���yorMu) {
            firePropertyChange ("t�k", 0, 1);
            a�� += 3;
            if (a�� >= 360) a�� = 0;
            if (duracakM�) {
                if (--saya� == 0) {
                    �al���yorMu = false;
                    zamanlay�c�.stop();
                } // i�-if karar� sonu...
            }else if (saya� < s�n�r) saya�++;
        } // d��-if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    public void start() {
        if (�al���yorMu) return;

        // Animasyon i�in bir sicim �al��t�ral�m...
        �al���yorMu = true;
        duracakM� = false;
        saya� = 0;
        int fps = 24;
        int t�k = 1000 / fps;

        zamanlay�c� = new Timer (t�k, this);
        zamanlay�c�.start();
    } // start() haz�r metodu sonu...

    public void stop() {duracakM� = true;}

    @Override
    public void applyPropertyChange (PropertyChangeEvent pce, JLayer tabaka) {
        if ("t�k".equals(pce.getPropertyName())) tabaka.repaint();
    } // applyPropertyChange(..) haz�r esge�me metodu sonu...
} // D�nerliBekletmeTabakas� s�n�f� sonu...