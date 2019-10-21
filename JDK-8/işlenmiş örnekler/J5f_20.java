// J5f_20.java: SpringGrid (KaynakIzgara) �rne�i.

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_20 {
    private static void yaratVeG�sterGUI() {
        // Kaynak serilimli panel'i yarat�p i�ine 12 adet metin sat�rl� komponent ekleyelim...
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        for (int i = 0; i < 12; i++) {
            JTextField metinSat�r� = new JTextField (Integer.toString (i));
            metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
            metinSat�r�.setForeground (Color.WHITE);

            // 4.metin sat�r�n� ekstra b�y�k yapal�m...
            if (i == 4) {
                metinSat�r�.setFont (new Font ("Courier", Font.BOLD, 20));
                metinSat�r�.setText ("Bu metin sat�r� ekstra b�y�kt�r.");
            } // for d�ng�s� sonu...

            panel.add (metinSat�r�);
        } // for d�ng�s� sonu...

        /* Komponetlerin paneldeki serilimini d�zenleyelim. Unutmayal�m ki bu metod
         * t�m komponent b�l�m b�y�kl���n� en b�y��e g�re ayarlar; kesif de�ildir. */
        J5f_13x.�zgaraYap (
                panel, // Konteyner panel'i
                4, 3, // 12 komponent, 4 sat�r ve 3 kolona payla�t�r�lacak...
                5, 5, // ilk (x,y) konumu...
                5, 5); // Her komponentin yatay ve dikey aral���...

        // Pencereyi kurup komponentli i�erik panosu panelini kural�m....
        JFrame �er�eve = new JFrame ("Kaynak Izgara");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque (true);
        �er�eve.setContentPane (panel);
        �er�eve.setLocation (250,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_20 s�n�f� sonu...