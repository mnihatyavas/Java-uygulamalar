// J5f_19: SpringForm (KaynakFormu) örneði.

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_19 {
    private static void yaratVeGösterGUI() {
        String[] etiketler = {"Ýsim: ", "Adres: ", "Eposta: ", "Tel: ", "Fax: "};
        int komponentÇiftiSayýsý = etiketler.length;

        // Herbir komponent çiftini kaynak serilimli panelimize kuralým...
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        for (int i = 0; i < komponentÇiftiSayýsý; i++) {
            JLabel etiket = new JLabel (etiketler[i], JLabel.TRAILING); // Saða yanaþýk...
            etiket.setForeground (Color.WHITE);
            panel.add (etiket);
            JTextField metinSatýrý = new JTextField (10);
            etiket.setLabelFor (metinSatýrý);
            panel.add (metinSatýrý);
        } // for döngüsü sonu...

        // Komponent çiftlerinin kesif serilim düzenlemesini yapalým...
        J5f_13x.kesifIzgaraYap (
                panel, // Konteyner kabý...
                komponentÇiftiSayýsý, // Satýr sayýsý...
                2, // Kolon sayýsý...
                6, 6, // Ýlk konum (x, y)...
                6, 6); // Yatay ve dikey tampon boþluðu...

        // Pencereyi kurup, düzenli komponent panel'li içerik panomuzu kuralým...
        JFrame çerçeve = new JFrame ("Kaynak Formu");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque (true);
        çerçeve.setContentPane (panel);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_19 sýnýfý sonu...