// J5i_1.java: Beeper (Bipleyici) �rne�i.

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
    JButton d��me;

    public J5i_1() {// Kurucu...
        super (new BorderLayout());
        d��me = new JButton ("Biplet beni");
        //d��me.setBackground (Color.BLACK);
        //d��me.setForeground (Color.WHITE);
        d��me.setPreferredSize (new Dimension (200, 80)); // D��me t�m pencereyi doldurur...
        add (d��me, BorderLayout.CENTER);
        d��me.addActionListener (this); // D��meyi dinleyiciye duyarlayal�m...
    } // J5i_1() kuruusu sonu...

    public void actionPerformed (ActionEvent olay) {Toolkit.getDefaultToolkit().beep();}

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Bipleyici");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_1(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_1 s�n�f� sonu...