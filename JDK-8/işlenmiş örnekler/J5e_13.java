// J5e_13.java: ShapedWindowDemo (-eliptik-ÞekilliPencereGösterisi) örneði.

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
        super ("Eliptik Þekilli Þeffaf Pencere");
        setLayout (new GridBagLayout());

        // Pencerenin þeklinin kurulmasý componentResized(..) hazýr metodu içinde
        // yapýlýp, olasý ekran deðiþikliðine uyum saðlanýlacak...
        addComponentListener (new ComponentAdapter() {
            @Override
            public void componentResized (ComponentEvent olay) {
                setShape (new Ellipse2D.Double (0, 0, getWidth(), getHeight()));
        }}); // add.. ifadesi sonu...

        setUndecorated (true);
        setSize (400,200);
        setLocationRelativeTo (null);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JButton düðme = new JButton ("Ben bir Düðmeyim");
        düðme.setBackground (Color.YELLOW);
        düðme.setForeground (Color.RED);

        JPanel panel = new JPanel();
        panel.add (düðme);
        panel.setBackground (Color.GREEN);

        add (panel);
    } // J5e_13() kurucusu sonu...

    public static void main (String[] args) {
        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAygýtý = grafikÇevresi.getDefaultScreenDevice();
        final boolean þeffaflýkDestekleniyorMu = grafikAygýtý.isWindowTranslucencySupported (TRANSLUCENT);

        // Eðer þeffaflýk desteklenmiyorsa, programdan çýkalým...
        if (!grafikAygýtý.isWindowTranslucencySupported (PERPIXEL_TRANSPARENT)) {
            System.err.println ("Þeffaf þekilli pencereler desteklenmiyor!");
            System.exit (0);
        } // if kararý sonu...

        // Þekilli destekleniyor, ama þeffaflýk desteklenmiyorsa, þeffafsýz net pencere yaratýlacak...
        if (!þeffaflýkDestekleniyorMu) System.out.println (
                "Þeffaflýk desteklenmiyor, opak/net bir pencere yaratýlacak!");

        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {J5e_13 eliptikPencere = new J5e_13(); // Kurucuyu çaðýrýr...
                // Destekleniyorsa opak netliði %70 yapalým...
                if (þeffaflýkDestekleniyorMu) eliptikPencere.setOpacity (0.7f);
                eliptikPencere.setVisible (true);
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_13 sýnýfý sonu...