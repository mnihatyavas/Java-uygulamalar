// J5e_12.java: Myopia (Miyop) �rne�i.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.Kernel;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLayer;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class J5e_12 {
    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratGUI();}}); 
    } // main(..) metodu sonu...

    public static void yaratGUI() {
        JFrame �er�eve = new JFrame ("Miyop Yak�n Bulan�kl���");
        LayerUI<JComponent> bulan�kTabaka = new Bulan�kTabaka(); // Kurucusunu �a��r�r...
        JPanel panel = komponentliPaneliKur();
        JLayer<JComponent> bulan�kPanel = new JLayer<JComponent>(panel, bulan�kTabaka);
        �er�eve.add (bulan�kPanel);
        �er�eve.setSize (300, 200);
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratGUI() metodu sonu...

    private static JPanel komponentliPaneliKur() {
        JPanel panel = new JPanel();

        ButtonGroup d��meGrubu = new ButtonGroup();
        JRadioButton radyoD��mesi;
        panel.add (radyoD��mesi = new JRadioButton ("Biftek"));
        d��meGrubu.add (radyoD��mesi);
        panel.add (radyoD��mesi = new JRadioButton ("Pili�"));
        d��meGrubu.add (radyoD��mesi);
        panel.add (radyoD��mesi = new JRadioButton ("Yo�urtlu D�ner", true));
        d��meGrubu.add (radyoD��mesi);

        panel.add (new JCheckBox ("Ke��ap"));
        panel.add (new JCheckBox ("Mayonez"));
        panel.add (new JCheckBox ("Tur�u"));

        panel.add (new JLabel ("�zel Yemek/Tatl�/Salata/Me�rubat Talebiniz:"));
        panel.add (new JTextField (20));

        JButton sipari�D��mesi = new JButton ("Sipari�inizi Verin");
        panel.add (sipari�D��mesi);

        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // �ok a��k renkler...
        return panel;
    } // komponentliPaneliKur() metodu sonu...
} // J5e_12 s�n�f� sonu...

class Bulan�kTabaka extends LayerUI<JComponent> {
    private BufferedImage miyopResim;
    private BufferedImageOp miyopla�t�rma��lemi;

    public Bulan�kTabaka() {// Kurucu...
        float miyopla�t�rmaOran� = 1.0f / 9.0f;
        float[] kerneliBulan�kla�t�r = {// ��erik panomuzdaki toplam 9 komponent de ayn� oranda bulan�kla�t�r�lacak...
                miyopla�t�rmaOran�, miyopla�t�rmaOran�, miyopla�t�rmaOran�,
                miyopla�t�rmaOran�, miyopla�t�rmaOran�, miyopla�t�rmaOran�,
                miyopla�t�rmaOran�, miyopla�t�rmaOran�, miyopla�t�rmaOran�
        }; // flo.. dizisi sonu...
        miyopla�t�rma��lemi = new ConvolveOp (// 9 komponenti de paint() ile tek-tek miyopla�t�r�r...
                new Kernel (3, 3, kerneliBulan�kla�t�r),
                ConvolveOp.EDGE_NO_OP,
                null
        ); // miy.. ifadesi sonu...
    } // Bulan�kTabaka() kurucusu sonu...

    @Override
    public void paint (Graphics g, JComponent komponent) {
        int geni�lik = komponent.getWidth();
        int y�kseklik = komponent.getHeight();

        if (geni�lik == 0 || y�kseklik == 0) return;

        // Miyop resim namevcutsa veya komponent �l��leriyle uyumsuzsa yenisini yarat�r...
        if (miyopResim == null ||
                miyopResim.getWidth() != geni�lik ||
                miyopResim.getHeight() != y�kseklik)
            miyopResim = new BufferedImage (geni�lik, y�kseklik, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2Da = miyopResim.createGraphics();
        g2Da.setClip (g.getClip());
        super.paint (g2Da, komponent);
        g2Da.dispose();

        Graphics2D g2Db = (Graphics2D)g;
        g2Db.drawImage (miyopResim, miyopla�t�rma��lemi, 0, 0);
  }  // paint(..) haz�r esge� metodu sonu...
} // Bulan�kTabaka s�n�f� sonu...