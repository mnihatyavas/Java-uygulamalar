// J5e_13.java: ShapedWindowDemo (-eliptik-�ekilliPencereG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsDevice.WindowTranslucency.*;
import java.awt.geom.Ellipse2D;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class J5e_13 extends JFrame {
    public J5e_13() {// Kurucu...
        super ("Eliptik �ekilli �effaf Pencere");
        setLayout (new GridBagLayout());

        // Pencerenin �eklinin kurulmas� componentResized(..) haz�r metodu i�inde
        // yap�l�p, olas� ekran de�i�ikli�ine uyum sa�lan�lacak...
        addComponentListener (new ComponentAdapter() {
            @Override
            public void componentResized (ComponentEvent olay) {
                setShape (new Ellipse2D.Double (0, 0, getWidth(), getHeight()));
        }}); // add.. ifadesi sonu...

        setUndecorated (true);
        setSize (400,200);
        setLocationRelativeTo (null);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JButton d��me = new JButton ("Ben bir D��meyim");
        d��me.setBackground (Color.YELLOW);
        d��me.setForeground (Color.RED);

        JPanel panel = new JPanel();
        panel.add (d��me);
        panel.setBackground (Color.GREEN);

        add (panel);
    } // J5e_13() kurucusu sonu...

    public static void main (String[] args) {
        GraphicsEnvironment grafik�evresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAyg�t� = grafik�evresi.getDefaultScreenDevice();
        final boolean �effafl�kDestekleniyorMu = grafikAyg�t�.isWindowTranslucencySupported (TRANSLUCENT);

        // E�er �effafl�k desteklenmiyorsa, programdan ��kal�m...
        if (!grafikAyg�t�.isWindowTranslucencySupported (PERPIXEL_TRANSPARENT)) {
            System.err.println ("�effaf �ekilli pencereler desteklenmiyor!");
            System.exit (0);
        } // if karar� sonu...

        // �ekilli destekleniyor, ama �effafl�k desteklenmiyorsa, �effafs�z net pencere yarat�lacak...
        if (!�effafl�kDestekleniyorMu) System.out.println (
                "�effafl�k desteklenmiyor, opak/net bir pencere yarat�lacak!");

        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {J5e_13 eliptikPencere = new J5e_13(); // Kurucuyu �a��r�r...
                // Destekleniyorsa opak netli�i %70 yapal�m...
                if (�effafl�kDestekleniyorMu) eliptikPencere.setOpacity (0.7f);
                eliptikPencere.setVisible (true);
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_13 s�n�f� sonu...