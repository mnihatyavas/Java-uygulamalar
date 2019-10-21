// J5f_16.java: SpringDemo2 (KaynakG�sterisi2) �rne�i.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class J5f_16 {
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kaynak G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        i�erikPanosu.setLayout (kaynakSerilim);
        JLabel etiket = new JLabel ("Etiket: ");
        JTextField metinSat�r� = new JTextField ("Metin sat�r�", 15);
        i�erikPanosu.add (etiket);
        i�erikPanosu.add (metinSat�r�);

        // S�n�rlay�c�larla etiketi i�erik panosunun (5,5) konumuna ayarlayal�m...
        kaynakSerilim.putConstraint (SpringLayout.WEST, etiket, 5, SpringLayout.WEST, i�erikPanosu);
        kaynakSerilim.putConstraint (SpringLayout.NORTH, etiket, 5, SpringLayout.NORTH, i�erikPanosu);

        // S�n�rlay�c�larla metin sat�r�n� etiketin sa��na/do�usuna (+5,5) konumuna ayarlayal�m...
        kaynakSerilim.putConstraint (SpringLayout.WEST, metinSat�r�, 5, SpringLayout.EAST, etiket);
        kaynakSerilim.putConstraint (SpringLayout.NORTH, metinSat�r�, 5, SpringLayout.NORTH, i�erikPanosu);

        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_16 s�n�f� sonu...