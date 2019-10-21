// J5f_16.java: SpringDemo2 (KaynakGösterisi2) örneði.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class J5f_16 {
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaynak Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        içerikPanosu.setLayout (kaynakSerilim);
        JLabel etiket = new JLabel ("Etiket: ");
        JTextField metinSatýrý = new JTextField ("Metin satýrý", 15);
        içerikPanosu.add (etiket);
        içerikPanosu.add (metinSatýrý);

        // Sýnýrlayýcýlarla etiketi içerik panosunun (5,5) konumuna ayarlayalým...
        kaynakSerilim.putConstraint (SpringLayout.WEST, etiket, 5, SpringLayout.WEST, içerikPanosu);
        kaynakSerilim.putConstraint (SpringLayout.NORTH, etiket, 5, SpringLayout.NORTH, içerikPanosu);

        // Sýnýrlayýcýlarla metin satýrýný etiketin saðýna/doðusuna (+5,5) konumuna ayarlayalým...
        kaynakSerilim.putConstraint (SpringLayout.WEST, metinSatýrý, 5, SpringLayout.EAST, etiket);
        kaynakSerilim.putConstraint (SpringLayout.NORTH, metinSatýrý, 5, SpringLayout.NORTH, içerikPanosu);

        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_16 sýnýfý sonu...