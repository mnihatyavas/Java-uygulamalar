// J5c_18.java: FrameDemo (�er�eveG�sterimi) �rne�i.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class J5c_18 {
    /**
     * GUI'yi yarat�p g�sterelim. Sicim �oklu i�lem g�venli�i i�in
     * bu metod olay-raporlayan bir sicimden y�r�t�lmelidir.
     */
    private static void yaratVeG�sterGUI() {
        // Penceremizi yarat�p kural�m...
        JFrame �er�eve = new JFrame ("�er�eve G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE

        JLabel etiket = new JLabel ("GUI YARATIMI VE G�STER�M�.");
        etiket.setPreferredSize (new Dimension (175, 100));
        etiket.setForeground (new Color ((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
        �er�eve.getContentPane().add (etiket, BorderLayout.CENTER);

        // Pencereyi paketleyip g�r�nt�leyelim...
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        // Bu uygulaman�n GrafikBirimiAray�z�n�-GUI yaratmak ve g�stermek i�in
        // olay raporlayan sicimin ko�turmas�nda bir g�rev metodu planlayal�m...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_18 s�n�f� sonu...