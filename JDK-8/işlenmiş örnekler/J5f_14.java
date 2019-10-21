// J5f_14.java: SpringCompactGrid (KaynakKesifIzgara) �rne�i.

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_14 {
    private static void yaratVeG�sterGUI() {
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.CYAN);

        int sat�rSay�s� = 10;
        int kolonSay�s� = 10;
        for (int s = 0; s < sat�rSay�s�; s++)
            for (int k = 0; k < kolonSay�s�; k++) {
                int tamsay� = (int)Math.pow (s, k);
                JTextField metinSat�r� = new JTextField (Integer.toString (tamsay�));
                metinSat�r�.setEditable (false); // M�dahalesiz...
                metinSat�r�.setBackground (Color.GRAY);
                metinSat�r�.setForeground (Color.WHITE);
                panel.add (metinSat�r�);
            } //D�� ve i�-for d�ng�leri sonu...

        // Metin sat�rl� panel par�alar�n� serimletelim...
        J5f_13x.kesifIzgaraYap (
                panel,
                sat�rSay�s�, kolonSay�s�,
                3, 3,// �lk (x,y) konumu...
                3, 3); // Herbir (x,y) aral���...

        JFrame �er�eve = new JFrame ("Kaynak Kesif Izgara");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque(true);
        �er�eve.setContentPane (panel);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_14 s�n�f� sonu...