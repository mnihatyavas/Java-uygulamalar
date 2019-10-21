// J5f_11.java: GridBagLayoutDemo (IzgaraÇantaSerilimGösterimi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Insets;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.ComponentOrientation;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

public class J5f_11 {
    final static boolean dolsunMu = false; // Orijinali: true, true, false...
    final static boolean xAðýrlýklýMý = false;
    final static boolean SAÐDAN_SOLA_MI = true;

    public static void komponentleriPanoyaEkle (Container kab) {
        if (SAÐDAN_SOLA_MI) kab.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

        JButton düðme;

        kab.setLayout (new GridBagLayout());
        GridBagConstraints sýnýrlayýcý = new GridBagConstraints();

        if (dolsunMu) // doðal yükseklik, azami geniþlik...
            sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;

        // Þimdi 5 düðmeyi de sýnýrlayýcýlarýyla teker teker kuralým...
        düðme = new JButton ("Düðme 1");
        if (xAðýrlýklýMý) sýnýrlayýcý.weightx = 0.5;
        sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcý.gridx = 0; // Ýzgaranýn (0,0) konumuna...
        sýnýrlayýcý.gridy = 0;
        kab.add (düðme, sýnýrlayýcý);

        düðme = new JButton ("Düðme 2");
        sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcý.weightx = 0.5;
        sýnýrlayýcý.gridx = 1; // Izgaranýn (1,0) konumuna...
        sýnýrlayýcý.gridy = 0;
        kab.add (düðme, sýnýrlayýcý);

        düðme = new JButton ("Düðme 3");
        sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcý.weightx = 0.5;
        sýnýrlayýcý.gridx = 2; // Izgaranýn (2,0) konumuna...
        sýnýrlayýcý.gridy = 0;
        kab.add(düðme, sýnýrlayýcý);

        düðme = new JButton ("Uzun-Ýsimli Düðme 4");
        sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcý.ipady = 40; // Yükseklik varsayýlý deðil, 40px...
        sýnýrlayýcý.weightx = 0.0;
        sýnýrlayýcý.gridwidth = 3; // 3 Izgara hücresi uzunluðunda...
        sýnýrlayýcý.gridx = 0; // Izgaranýn (0,1) konumuna
        sýnýrlayýcý.gridy = 1;
        kab.add (düðme, sýnýrlayýcý);

        düðme = new JButton ("5");
        sýnýrlayýcý.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcý.ipady = 0; // Yüksekliðini tekrar varsayýlý yapalým...
        sýnýrlayýcý.weighty = 1.0; // Düðmenin varsayýlý yüksekliðinden fazlasý talep ediliyor...
        sýnýrlayýcý.anchor = GridBagConstraints.PAGE_END; // Bu düðme pencere dibine oturacak...
        sýnýrlayýcý.insets = new Insets (10,0,0,0);  // Düðme üstü 10px aralýk kalacak...
        sýnýrlayýcý.gridx = 1; // Izgaranýn (1,2) konumuna...
        sýnýrlayýcý.gridy = 2;
        sýnýrlayýcý.gridwidth = 2; // Bu düðme 2 Izgara hücresi uzunluðunda olacak...
        kab.add (düðme, sýnýrlayýcý);
        kab.setBackground (Color.PINK);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Izgara Çanta Serilim Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriPanoyaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_11 sýnýfý sonu...