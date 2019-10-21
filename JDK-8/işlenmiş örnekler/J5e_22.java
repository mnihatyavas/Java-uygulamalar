// J5e_22.java: TranslucentWindowDemo (�effafPencereG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.GridBagLayout;;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import static java.awt.GraphicsDevice.WindowTranslucency.TRANSLUCENT;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

// J5e_8.java: GradientTranslucentWindowDemo.java �rne�i gibidir...
public class J5e_22 extends JFrame {
    public J5e_22() {// Kurucu...
        super ("�effaf Pencere");
        setLayout (new GridBagLayout());
        setSize (300,200);
        setLocationRelativeTo (null); // Ekran� ortalar...
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JPanel panel = new JPanel();
        panel.setBackground (Color.CYAN);
        JButton d��me = new JButton ("Ben Bir D��meyim");
        d��me.setBackground (Color.YELLOW);
        d��me.setForeground (Color.RED);
        panel.add (d��me);
        add (panel);
    } // J5e_22() kurucusu sonu...

    public static void main (String[] args) {
        // GraphicsDevice/GrafikAyg�t�'m�z �effafl��� desteklemiyorsa ��k...
        GraphicsEnvironment grafik�evremiz = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice grafikAyg�t�m�z = grafik�evremiz.getDefaultScreenDevice();
        if (!grafikAyg�t�m�z.isWindowTranslucencySupported (TRANSLUCENT)) {
            System.err.println ("UYARI: �effafl�k Desteklenmiyor!"); System.exit (0);
        } // if karar� sonu...
        JFrame.setDefaultLookAndFeelDecorated (true);
        SwingUtilities.invokeLater (new Runnable() {
            @Override
            public void run() {
                J5e_22 �effafPencere = new J5e_22(); // Kurucuyu �a��r�r...
                // Pencere %55 opak/net/�effafs�z, yani %45 �effaf olacak...
                �effafPencere.setOpacity (0.55f);
                �effafPencere.setVisible (true);
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_22 s�n�f� sonu...