// J5f_8.java: Find (Bul) �rne�i.

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;

// GroupLayout/GrupSerilimi g�sterisi...
public class J5f_8 extends JFrame {
    public J5f_8() {// Kurucu...
        JLabel etiket = new JLabel ("Ne bulunacak:");;
        JTextField metinSat�r� = new JTextField();
        JCheckBox hal�entikKutusu = new JCheckBox ("Harf Uyumu");
        JCheckBox sar�entikKutusu = new JCheckBox ("Etraf� Sar�l�");
        JCheckBox t�m�entikKutusu = new JCheckBox ("T�m Kelimeler");
        JCheckBox geri�entikKutusu = new JCheckBox ("Geriye Ara�t�r");
        JButton bulD��mesi = new JButton ("Bul");
        JButton iptalD��mesi = new JButton ("�ptal");

        // �entik kutusu �evre varsay�l� bo�luklar� silelim...
        hal�entikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        sar�entikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        t�m�entikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        geri�entikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        GroupLayout grupSerilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilim);
        grupSerilim.setAutoCreateGaps (true);
        grupSerilim.setAutoCreateContainerGaps (true);

        // 1. Yatay grup serilim tasar�m�...
        grupSerilim.setHorizontalGroup (grupSerilim.createSequentialGroup()
            .addComponent (etiket)
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (metinSat�r�)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (hal�entikKutusu)
                        .addComponent (t�m�entikKutusu))
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (sar�entikKutusu)
                        .addComponent (geri�entikKutusu))))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (bulD��mesi)
                .addComponent (iptalD��mesi))
        ); // gru.. ifadesi sonu...

        grupSerilim.linkSize (SwingConstants.HORIZONTAL, bulD��mesi, iptalD��mesi);

        // 2. Dikey grup serilim tasar�m�...
        grupSerilim.setVerticalGroup (grupSerilim.createSequentialGroup()
            .addGroup (grupSerilim.createParallelGroup (BASELINE)
                .addComponent (etiket)
                .addComponent (metinSat�r�)
                .addComponent (bulD��mesi))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (hal�entikKutusu)
                        .addComponent (sar�entikKutusu))
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (t�m�entikKutusu)
                        .addComponent (geri�entikKutusu)))
                        .addComponent (iptalD��mesi))
        ); // gru.. ifadesi sonu...

        setTitle("Bul");
        setLocationRelativeTo (null);
        pack();
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
    } // J5f_8() kurucusu sonu...

    public static void main (String args[]) {
        EventQueue.invokeLater (new Runnable() {
            public void run() {
                try {UIManager.setLookAndFeel (
                        "javax.swing.plaf.metal.MetalLookAndFeel");
                        //UIManager.getCrossPlatformLookAndFeelClassName());
                }catch (Exception ist) {ist.printStackTrace();
                } // try-catch blo�u sonu...
                new J5f_8().setVisible (true); // Kurucuyu �a��r�r...
            } // run() haz�r sicim metodu sonu...
        }); // Eve.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_8 s�n�f� sonu...