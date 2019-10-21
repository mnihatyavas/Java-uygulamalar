// J5e_8.java: GradientTranslucentWindowDemo (KademeliYarýsaydamPencereGösterisi) örneði.

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
        super ("Kadameli Yarýsaydam Pencere");

        setBackground (new Color (0,0,0,0));
        setSize (new Dimension (300, 200));
        setLocationRelativeTo (null);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                if (g instanceof Graphics2D) {
                    final int K = 140; // 255: tam þeffaf/saydam; 0: tam opak/net...
                    final int Y = 140; // K:Kýrmýzý, Y:Yeþil, M:Mavi...
                    final int M = 140; // Ýstenilen renk kombinasyonu seçilebilir...

                    Paint pencereRenkVeSaydamlýk = new GradientPaint (
                            0.0f, 0.0f, new Color (K, Y, M, 0), 0.0f,
                            getHeight(), new Color (K, Y, M, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint (pencereRenkVeSaydamlýk);
                    g2d.fillRect (0, 0, getWidth(), getHeight());
                } // if kararý sonu...
            } // paintComponent(..) hazýr override metodu sonu...
        }; // JPa.. ifadesi sonu...
        setContentPane (panel);
        setLayout (new GridBagLayout());
        add (new JButton ("Ben bir Düðmeyim"));
    } // J5e_8() kurucusu sonu...

    public static void main (String[] args) {
        // Grafik çevresinden grafik aracýnýn yarý saydam pencereyi desteklemesini kontrol edelim...
        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAracý = grafikÇevresi.getDefaultScreenDevice();
        boolean yarýsaydamlýkDestekleniyorMu = grafikAracý.isWindowTranslucencySupported (PERPIXEL_TRANSLUCENT);

        // Desteklenmiyorsa programý sonlandýralým...
        if (!yarýsaydamlýkDestekleniyorMu) {
            System.out.println ("Herbir ekran noktasýnda yarýsaydamlýk desteklenmiyor!");
            System.exit (0);
        } // if kararý sonu...

        JFrame.setDefaultLookAndFeelDecorated (true); // BakHisset süsünü etkinleþtirelim...
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
                J5e_8 kadameliYarýsaydamPencere = new J5e_8(); // Kurucuyu çaðýrýr...
                kadameliYarýsaydamPencere.setVisible (true);
            } // run() hazýr override metodu sonu...
        }); // Swi..ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_8 sýnýfý sonu...