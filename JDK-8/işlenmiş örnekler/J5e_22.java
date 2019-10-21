// J5e_22.java: TranslucentWindowDemo (ÞeffafPencereGösterisi) örneði.

import java.awt.Color;
import java.awt.GridBagLayout;;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

// J5e_8.java: GradientTranslucentWindowDemo.java örneði gibidir...
public class J5e_22 extends JFrame {
    public J5e_22() {// Kurucu...
        super ("Þeffaf Pencere");
        setLayout (new GridBagLayout());
        setSize (300,200);
        setLocationRelativeTo (null); // Ekraný ortalar...
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        panel.setBackground (Color.CYAN);
        JButton düðme = new JButton ("Ben Bir Düðmeyim");
        düðme.setBackground (Color.YELLOW);
        düðme.setForeground (Color.RED);
        panel.add (düðme);
        add (panel);
    } // J5e_22() kurucusu sonu...

    public static void main (String[] args) {
        // GraphicsDevice/GrafikAygýtý'mýz þeffaflýðý desteklemiyorsa çýk...
        GraphicsEnvironment grafikÇevremiz = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAygýtýmýz = grafikÇevremiz.getDefaultScreenDevice();
        if (!grafikAygýtýmýz.isWindowTranslucencySupported (TRANSLUCENT)) {
            System.err.println ("UYARI: Þeffaflýk Desteklenmiyor!"); System.exit (0);
        } // if kararý sonu...
        JFrame.setDefaultLookAndFeelDecorated (true);
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
                J5e_22 þeffafPencere = new J5e_22(); // Kurucuyu çaðýrýr...
                // Pencere %55 opak/net/þeffafsýz, yani %45 þeffaf olacak...
                þeffafPencere.setOpacity (0.55f);
                þeffafPencere.setVisible (true);
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_22 sýnýfý sonu...