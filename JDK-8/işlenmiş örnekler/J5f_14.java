// J5f_14.java: SpringCompactGrid (KaynakKesifIzgara) örneði.

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_14 {
    private static void yaratVeGösterGUI() {
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.CYAN);

        int satýrSayýsý = 10;
        int kolonSayýsý = 10;
        for (int s = 0; s < satýrSayýsý; s++)
            for (int k = 0; k < kolonSayýsý; k++) {
                int tamsayý = (int)Math.pow (s, k);
                JTextField metinSatýrý = new JTextField (Integer.toString (tamsayý));
                metinSatýrý.setEditable (false); // Müdahalesiz...
                metinSatýrý.setBackground (Color.GRAY);
                metinSatýrý.setForeground (Color.WHITE);
                panel.add (metinSatýrý);
            } //Dýþ ve iç-for döngüleri sonu...

        // Metin satýrlý panel parçalarýný serimletelim...
        J5f_13x.kesifIzgaraYap (
                panel,
                satýrSayýsý, kolonSayýsý,
                3, 3,// Ýlk (x,y) konumu...
                3, 3); // Herbir (x,y) aralýðý...

        JFrame çerçeve = new JFrame ("Kaynak Kesif Izgara");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque(true);
        çerçeve.setContentPane (panel);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_14 sýnýfý sonu...