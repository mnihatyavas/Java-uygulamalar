// J5f_17.java: SpringDemo3 (KaynakG�sterisi3) �rne�i.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class J5f_17 {
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kaynak G�sterisi 3");
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
        kaynakSerilim.putConstraint (SpringLayout.WEST, etiket, 5, SpringLayout.WEST, i�erikPanosu); // x konumu...
        kaynakSerilim.putConstraint (SpringLayout.NORTH, etiket, 5, SpringLayout.NORTH, i�erikPanosu); // y konumu...

        // S�n�rlay�c�larla metin sat�r�n� etiketin sa��na/do�usuna (+5,5) konumland�ral�m...
        kaynakSerilim.putConstraint (SpringLayout.WEST, metinSat�r�, 5, SpringLayout.EAST, etiket); // x konumu...
        kaynakSerilim.putConstraint (SpringLayout.NORTH, metinSat�r�, 5, SpringLayout.NORTH, i�erikPanosu); // y konumu...

        // S�n�rlay�c�larla i�erik panosunu metin sat�r�n�n 5 sa��na/do�u ve 5 alt�na/g�ney a�al�m...
        kaynakSerilim.putConstraint (SpringLayout.EAST, i�erikPanosu, 5, SpringLayout.EAST, metinSat�r�);
        kaynakSerilim.putConstraint (SpringLayout.SOUTH, i�erikPanosu, 5, SpringLayout.SOUTH, metinSat�r�);

        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_17 s�n�f� sonu...