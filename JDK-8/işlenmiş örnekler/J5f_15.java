// J5f_15.java: SpringDemo1 (KaynakGösterisi1) örneði.

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

public class J5f_15 {
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaynak Gösterisi 1");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        içerikPanosu.setLayout (kaynakSerilim);
        içerikPanosu.add (new JLabel ("Etiket: "));
        içerikPanosu.add (new JTextField ("Metin satýrý", 15));
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_15 sýnýfý sonu...