// J5f_8.java: Find (Bul) örneði.

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

// GroupLayout/GrupSerilimi gösterisi...
public class J5f_8 extends JFrame {
    public J5f_8() {// Kurucu...
        JLabel etiket = new JLabel ("Ne bulunacak:");;
        JTextField metinSatýrý = new JTextField();
        JCheckBox halÇentikKutusu = new JCheckBox ("Harf Uyumu");
        JCheckBox sarÇentikKutusu = new JCheckBox ("Etrafý Sarýlý");
        JCheckBox tümÇentikKutusu = new JCheckBox ("Tüm Kelimeler");
        JCheckBox geriÇentikKutusu = new JCheckBox ("Geriye Araþtýr");
        JButton bulDüðmesi = new JButton ("Bul");
        JButton iptalDüðmesi = new JButton ("Ýptal");

        // Çentik kutusu çevre varsayýlý boþluklarý silelim...
        halÇentikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        sarÇentikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        tümÇentikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        geriÇentikKutusu.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        GroupLayout grupSerilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilim);
        grupSerilim.setAutoCreateGaps (true);
        grupSerilim.setAutoCreateContainerGaps (true);

        // 1. Yatay grup serilim tasarýmý...
        grupSerilim.setHorizontalGroup (grupSerilim.createSequentialGroup()
            .addComponent (etiket)
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (metinSatýrý)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (halÇentikKutusu)
                        .addComponent (tümÇentikKutusu))
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (sarÇentikKutusu)
                        .addComponent (geriÇentikKutusu))))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (bulDüðmesi)
                .addComponent (iptalDüðmesi))
        ); // gru.. ifadesi sonu...

        grupSerilim.linkSize (SwingConstants.HORIZONTAL, bulDüðmesi, iptalDüðmesi);

        // 2. Dikey grup serilim tasarýmý...
        grupSerilim.setVerticalGroup (grupSerilim.createSequentialGroup()
            .addGroup (grupSerilim.createParallelGroup (BASELINE)
                .addComponent (etiket)
                .addComponent (metinSatýrý)
                .addComponent (bulDüðmesi))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (halÇentikKutusu)
                        .addComponent (sarÇentikKutusu))
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (tümÇentikKutusu)
                        .addComponent (geriÇentikKutusu)))
                        .addComponent (iptalDüðmesi))
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
                } // try-catch bloðu sonu...
                new J5f_8().setVisible (true); // Kurucuyu çaðýrýr...
            } // run() hazýr sicim metodu sonu...
        }); // Eve.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_8 sýnýfý sonu...