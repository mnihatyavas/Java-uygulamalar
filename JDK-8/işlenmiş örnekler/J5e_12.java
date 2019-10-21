// J5e_12.java: Myopia (Miyop) örneði.

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
        JFrame çerçeve = new JFrame ("Miyop Yakýn Bulanýklýðý");
        LayerUI<JComponent> bulanýkTabaka = new BulanýkTabaka(); // Kurucusunu çaðýrýr...
        JPanel panel = komponentliPaneliKur();
        JLayer<JComponent> bulanýkPanel = new JLayer<JComponent>(panel, bulanýkTabaka);
        çerçeve.add (bulanýkPanel);
        çerçeve.setSize (300, 200);
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratGUI() metodu sonu...

    private static JPanel komponentliPaneliKur() {
        JPanel panel = new JPanel();

        ButtonGroup düðmeGrubu = new ButtonGroup();
        JRadioButton radyoDüðmesi;
        panel.add (radyoDüðmesi = new JRadioButton ("Biftek"));
        düðmeGrubu.add (radyoDüðmesi);
        panel.add (radyoDüðmesi = new JRadioButton ("Piliç"));
        düðmeGrubu.add (radyoDüðmesi);
        panel.add (radyoDüðmesi = new JRadioButton ("Yoðurtlu Döner", true));
        düðmeGrubu.add (radyoDüðmesi);

        panel.add (new JCheckBox ("Keççap"));
        panel.add (new JCheckBox ("Mayonez"));
        panel.add (new JCheckBox ("Turþu"));

        panel.add (new JLabel ("Özel Yemek/Tatlý/Salata/Meþrubat Talebiniz:"));
        panel.add (new JTextField (20));

        JButton sipariþDüðmesi = new JButton ("Sipariþinizi Verin");
        panel.add (sipariþDüðmesi);

        panel.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Çok açýk renkler...
        return panel;
    } // komponentliPaneliKur() metodu sonu...
} // J5e_12 sýnýfý sonu...

class BulanýkTabaka extends LayerUI<JComponent> {
    private BufferedImage miyopResim;
    private BufferedImageOp miyoplaþtýrmaÝþlemi;

    public BulanýkTabaka() {// Kurucu...
        float miyoplaþtýrmaOraný = 1.0f / 9.0f;
        float[] kerneliBulanýklaþtýr = {// Ýçerik panomuzdaki toplam 9 komponent de ayný oranda bulanýklaþtýrýlacak...
                miyoplaþtýrmaOraný, miyoplaþtýrmaOraný, miyoplaþtýrmaOraný,
                miyoplaþtýrmaOraný, miyoplaþtýrmaOraný, miyoplaþtýrmaOraný,
                miyoplaþtýrmaOraný, miyoplaþtýrmaOraný, miyoplaþtýrmaOraný
        }; // flo.. dizisi sonu...
        miyoplaþtýrmaÝþlemi = new ConvolveOp (// 9 komponenti de paint() ile tek-tek miyoplaþtýrýr...
                new Kernel (3, 3, kerneliBulanýklaþtýr),
                ConvolveOp.EDGE_NO_OP,
                null
        ); // miy.. ifadesi sonu...
    } // BulanýkTabaka() kurucusu sonu...

    @Override
    public void paint (Graphics g, JComponent komponent) {
        int geniþlik = komponent.getWidth();
        int yükseklik = komponent.getHeight();

        if (geniþlik == 0 || yükseklik == 0) return;

        // Miyop resim namevcutsa veya komponent ölçüleriyle uyumsuzsa yenisini yaratýr...
        if (miyopResim == null ||
                miyopResim.getWidth() != geniþlik ||
                miyopResim.getHeight() != yükseklik)
            miyopResim = new BufferedImage (geniþlik, yükseklik, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2Da = miyopResim.createGraphics();
        g2Da.setClip (g.getClip());
        super.paint (g2Da, komponent);
        g2Da.dispose();

        Graphics2D g2Db = (Graphics2D)g;
        g2Db.drawImage (miyopResim, miyoplaþtýrmaÝþlemi, 0, 0);
  }  // paint(..) hazýr esgeç metodu sonu...
} // BulanýkTabaka sýnýfý sonu...