// J5c_78.java: TopLevelDemo (�stSeviyeG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class J5c_78 {
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("�st Seviye G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Ye�ilimsi bir men� �ubu�u yaratal�m...
        JMenuBar ye�ilimsiMen��ubu�u = new JMenuBar();
        ye�ilimsiMen��ubu�u.setOpaque (true);
        ye�ilimsiMen��ubu�u.setBackground (new Color (154, 165, 127));
        ye�ilimsiMen��ubu�u.setPreferredSize (new Dimension (250, 20));

        // ��erik panosuna sar�mt�rak bir etiket yap��t�ral�m...
        JLabel sar�ms�Etiket = new JLabel();
        sar�ms�Etiket.setOpaque (true);
        sar�ms�Etiket.setBackground (new Color (248, 213, 131));
        sar�ms�Etiket.setPreferredSize (new Dimension (250, 180));

        // Men� �ubu�u ve etiketi i�erik panosuna koyal�m...
        �er�eve.setJMenuBar (ye�ilimsiMen��ubu�u);
        �er�eve.getContentPane().add (sar�ms�Etiket, BorderLayout.CENTER);

        // Pencereyi konumland�r�p paketleyip g�r�n�r k�lal�m...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_78 s�n�f� sonu...