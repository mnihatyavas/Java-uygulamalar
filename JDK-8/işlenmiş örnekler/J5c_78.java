// J5c_78.java: TopLevelDemo (ÜstSeviyeGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class J5c_78 {
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Üst Seviye Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Yeþilimsi bir menü çubuðu yaratalým...
        JMenuBar yeþilimsiMenüÇubuðu = new JMenuBar();
        yeþilimsiMenüÇubuðu.setOpaque (true);
        yeþilimsiMenüÇubuðu.setBackground (new Color (154, 165, 127));
        yeþilimsiMenüÇubuðu.setPreferredSize (new Dimension (250, 20));

        // Ýçerik panosuna sarýmtýrak bir etiket yapýþtýralým...
        JLabel sarýmsýEtiket = new JLabel();
        sarýmsýEtiket.setOpaque (true);
        sarýmsýEtiket.setBackground (new Color (248, 213, 131));
        sarýmsýEtiket.setPreferredSize (new Dimension (250, 180));

        // Menü çubuðu ve etiketi içerik panosuna koyalým...
        çerçeve.setJMenuBar (yeþilimsiMenüÇubuðu);
        çerçeve.getContentPane().add (sarýmsýEtiket, BorderLayout.CENTER);

        // Pencereyi konumlandýrýp paketleyip görünür kýlalým...
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_78 sýnýfý sonu...