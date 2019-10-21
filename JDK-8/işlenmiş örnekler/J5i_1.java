// J5i_1.java: Beeper (Bipleyici) örneði.

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class J5i_1 extends JPanel implements ActionListener {
    JButton düðme;

    public J5i_1() {// Kurucu...
        super (new BorderLayout());
        düðme = new JButton ("Biplet beni");
        //düðme.setBackground (Color.BLACK);
        //düðme.setForeground (Color.WHITE);
        düðme.setPreferredSize (new Dimension (200, 80)); // Düðme tüm pencereyi doldurur...
        add (düðme, BorderLayout.CENTER);
        düðme.addActionListener (this); // Düðmeyi dinleyiciye duyarlayalým...
    } // J5i_1() kuruusu sonu...

    public void actionPerformed (ActionEvent olay) {Toolkit.getDefaultToolkit().beep();}

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Bipleyici");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_1(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_1 sýnýfý sonu...