// J5f_19: SpringForm (KaynakFormu) �rne�i.

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

// Gereken dosya: J5f_13x.java=SpringUtilities.java
public class J5f_19 {
    private static void yaratVeG�sterGUI() {
        String[] etiketler = {"�sim: ", "Adres: ", "Eposta: ", "Tel: ", "Fax: "};
        int komponent�iftiSay�s� = etiketler.length;

        // Herbir komponent �iftini kaynak serilimli panelimize kural�m...
        JPanel panel = new JPanel (new SpringLayout());
        panel.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        for (int i = 0; i < komponent�iftiSay�s�; i++) {
            JLabel etiket = new JLabel (etiketler[i], JLabel.TRAILING); // Sa�a yana��k...
            etiket.setForeground (Color.WHITE);
            panel.add (etiket);
            JTextField metinSat�r� = new JTextField (10);
            etiket.setLabelFor (metinSat�r�);
            panel.add (metinSat�r�);
        } // for d�ng�s� sonu...

        // Komponent �iftlerinin kesif serilim d�zenlemesini yapal�m...
        J5f_13x.kesifIzgaraYap (
                panel, // Konteyner kab�...
                komponent�iftiSay�s�, // Sat�r say�s�...
                2, // Kolon say�s�...
                6, 6, // �lk konum (x, y)...
                6, 6); // Yatay ve dikey tampon bo�lu�u...

        // Pencereyi kurup, d�zenli komponent panel'li i�erik panomuzu kural�m...
        JFrame �er�eve = new JFrame ("Kaynak Formu");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        panel.setOpaque (true);
        �er�eve.setContentPane (panel);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_19 s�n�f� sonu...