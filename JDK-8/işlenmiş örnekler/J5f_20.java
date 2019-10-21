// J5f_20.java: SpringGrid (KaynakIzgara) örneði.

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_20 {
    private static void yaratVeGösterGUI() {
        // Kaynak serilimli panel'i yaratýp içine 12 adet metin satýrlý komponent ekleyelim...
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        for (int i = 0; i < 12; i++) {
            JTextField metinSatýrý = new JTextField (Integer.toString (i));
            metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
            metinSatýrý.setForeground (Color.WHITE);

            // 4.metin satýrýný ekstra büyük yapalým...
            if (i == 4) {
                metinSatýrý.setFont (new Font ("Courier", Font.BOLD, 20));
                metinSatýrý.setText ("Bu metin satýrý ekstra büyüktür.");
            } // for döngüsü sonu...

            panel.add (metinSatýrý);
        } // for döngüsü sonu...

        /* Komponetlerin paneldeki serilimini düzenleyelim. Unutmayalým ki bu metod
         * tüm komponent bölüm büyüklüðünü en büyüðe göre ayarlar; kesif deðildir. */
        J5f_13x.ýzgaraYap (
                panel, // Konteyner panel'i
                4, 3, // 12 komponent, 4 satýr ve 3 kolona paylaþtýrýlacak...
                5, 5, // ilk (x,y) konumu...
                5, 5); // Her komponentin yatay ve dikey aralýðý...

        // Pencereyi kurup komponentli içerik panosu panelini kuralým....
        JFrame çerçeve = new JFrame ("Kaynak Izgara");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque (true);
        çerçeve.setContentPane (panel);
        çerçeve.setLocation (250,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_20 sýnýfý sonu...