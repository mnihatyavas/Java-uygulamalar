// J5e_8.java: GradientTranslucentWindowDemo (KademeliYarısaydamPencereGösterisi) örneği.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsDevice.WindowTranslucency.PERPIXEL_TRANSLUCENT;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class J5e_8 extends JFrame {
    public J5e_8() {// Kurucu...
        super ("Kadameli Yarısaydam Pencere");

        setBackground (new Color (0,0,0,0));
        setSize (new Dimension (300, 200));
        setLocationRelativeTo (null);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                if (g instanceof Graphics2D) {
                    final int K = 140; // 255: tam şeffaf/saydam; 0: tam opak/net...
                    final int Y = 140; // K:Kırmızı, Y:Yeşil, M:Mavi...
                    final int M = 140; // İstenilen renk kombinasyonu seçilebilir...

                    Paint pencereRenkVeSaydamlık = new GradientPaint (
                            0.0f, 0.0f, new Color (K, Y, M, 0), 0.0f,
                            getHeight(), new Color (K, Y, M, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint (pencereRenkVeSaydamlık);
                    g2d.fillRect (0, 0, getWidth(), getHeight());
                } // if kararı sonu...
            } // paintComponent(..) hazır override metodu sonu...
        }; // JPa.. ifadesi sonu...
        setContentPane (panel);
        setLayout (new GridBagLayout());
        add (new JButton ("Ben bir Düğmeyim"));
    } // J5e_8() kurucusu sonu...

    public static void main (String[] args) {
        // Grafik çevresinden grafik aracının yarı saydam pencereyi desteklemesini kontrol edelim...
        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAracı = grafikÇevresi.getDefaultScreenDevice();
        boolean yarısaydamlıkDestekleniyorMu = grafikAracı.isWindowTranslucencySupported (PERPIXEL_TRANSLUCENT);

        // Desteklenmiyorsa programı sonlandıralım...
        if (!yarısaydamlıkDestekleniyorMu) {
            System.out.println ("Herbir ekran noktasında yarısaydamlık desteklenmiyor!");
            System.exit (0);
        } // if kararı sonu...

        JFrame.setDefaultLookAndFeelDecorated (true); // BakHisset süsünü etkinleştirelim...
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
                J5e_8 kadameliYarısaydamPencere = new J5e_8(); // Kurucuyu çağırır...
                kadameliYarısaydamPencere.setVisible (true);
            } // run() hazır override metodu sonu...
        }); // Swi..ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_8 sınıfı sonu...