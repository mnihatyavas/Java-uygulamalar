// J5c_18.java: FrameDemo (ÇerçeveGösterimi) örneði.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class J5c_18 {
    /**
     * GUI'yi yaratýp gösterelim. Sicim çoklu iþlem güvenliði için
     * bu metod olay-raporlayan bir sicimden yürütülmelidir.
     */
    private static void yaratVeGösterGUI() {
        // Penceremizi yaratýp kuralým...
        JFrame çerçeve = new JFrame ("Çerçeve Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE

        JLabel etiket = new JLabel ("GUI YARATIMI VE GÖSTERÝMÝ.");
        etiket.setPreferredSize (new Dimension (175, 100));
        etiket.setForeground (new Color ((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
        çerçeve.getContentPane().add (etiket, BorderLayout.CENTER);

        // Pencereyi paketleyip görüntüleyelim...
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        // Bu uygulamanýn GrafikBirimiArayüzünü-GUI yaratmak ve göstermek için
        // olay raporlayan sicimin koþturmasýnda bir görev metodu planlayalým...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_18 sýnýfý sonu...