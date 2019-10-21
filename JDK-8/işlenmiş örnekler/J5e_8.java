// J5e_8.java: GradientTranslucentWindowDemo (KademeliYar�saydamPencereG�sterisi) �rne�i.

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
        super ("Kadameli Yar�saydam Pencere");

        setBackground (new Color (0,0,0,0));
        setSize (new Dimension (300, 200));
        setLocationRelativeTo (null);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent (Graphics g) {
                if (g instanceof Graphics2D) {
                    final int K = 140; // 255: tam �effaf/saydam; 0: tam opak/net...
                    final int Y = 140; // K:K�rm�z�, Y:Ye�il, M:Mavi...
                    final int M = 140; // �stenilen renk kombinasyonu se�ilebilir...

                    Paint pencereRenkVeSaydaml�k = new GradientPaint (
                            0.0f, 0.0f, new Color (K, Y, M, 0), 0.0f,
                            getHeight(), new Color (K, Y, M, 255), true);
                    Graphics2D g2d = (Graphics2D)g;
                    g2d.setPaint (pencereRenkVeSaydaml�k);
                    g2d.fillRect (0, 0, getWidth(), getHeight());
                } // if karar� sonu...
            } // paintComponent(..) haz�r override metodu sonu...
        }; // JPa.. ifadesi sonu...
        setContentPane (panel);
        setLayout (new GridBagLayout());
        add (new JButton ("Ben bir D��meyim"));
    } // J5e_8() kurucusu sonu...

    public static void main (String[] args) {
        // Grafik �evresinden grafik arac�n�n yar� saydam pencereyi desteklemesini kontrol edelim...
        GraphicsEnvironment grafik�evresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikArac� = grafik�evresi.getDefaultScreenDevice();
        boolean yar�saydaml�kDestekleniyorMu = grafikArac�.isWindowTranslucencySupported (PERPIXEL_TRANSLUCENT);

        // Desteklenmiyorsa program� sonland�ral�m...
        if (!yar�saydaml�kDestekleniyorMu) {
            System.out.println ("Herbir ekran noktas�nda yar�saydaml�k desteklenmiyor!");
            System.exit (0);
        } // if karar� sonu...

        JFrame.setDefaultLookAndFeelDecorated (true); // BakHisset s�s�n� etkinle�tirelim...
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
                J5e_8 kadameliYar�saydamPencere = new J5e_8(); // Kurucuyu �a��r�r...
                kadameliYar�saydamPencere.setVisible (true);
            } // run() haz�r override metodu sonu...
        }); // Swi..ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_8 s�n�f� sonu...