// J5f_11.java: GridBagLayoutDemo (Izgara�antaSerilimG�sterimi) �rne�i.

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
    final static boolean xA��rl�kl�M� = false;
    final static boolean SA�DAN_SOLA_MI = true;

    public static void komponentleriPanoyaEkle (Container kab) {
        if (SA�DAN_SOLA_MI) kab.setComponentOrientation (ComponentOrientation.RIGHT_TO_LEFT);

        JButton d��me;

        kab.setLayout (new GridBagLayout());
        GridBagConstraints s�n�rlay�c� = new GridBagConstraints();

        if (dolsunMu) // do�al y�kseklik, azami geni�lik...
            s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;

        // �imdi 5 d��meyi de s�n�rlay�c�lar�yla teker teker kural�m...
        d��me = new JButton ("D��me 1");
        if (xA��rl�kl�M�) s�n�rlay�c�.weightx = 0.5;
        s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�.gridx = 0; // �zgaran�n (0,0) konumuna...
        s�n�rlay�c�.gridy = 0;
        kab.add (d��me, s�n�rlay�c�);

        d��me = new JButton ("D��me 2");
        s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�.weightx = 0.5;
        s�n�rlay�c�.gridx = 1; // Izgaran�n (1,0) konumuna...
        s�n�rlay�c�.gridy = 0;
        kab.add (d��me, s�n�rlay�c�);

        d��me = new JButton ("D��me 3");
        s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�.weightx = 0.5;
        s�n�rlay�c�.gridx = 2; // Izgaran�n (2,0) konumuna...
        s�n�rlay�c�.gridy = 0;
        kab.add(d��me, s�n�rlay�c�);

        d��me = new JButton ("Uzun-�simli D��me 4");
        s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�.ipady = 40; // Y�kseklik varsay�l� de�il, 40px...
        s�n�rlay�c�.weightx = 0.0;
        s�n�rlay�c�.gridwidth = 3; // 3 Izgara h�cresi uzunlu�unda...
        s�n�rlay�c�.gridx = 0; // Izgaran�n (0,1) konumuna
        s�n�rlay�c�.gridy = 1;
        kab.add (d��me, s�n�rlay�c�);

        d��me = new JButton ("5");
        s�n�rlay�c�.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�.ipady = 0; // Y�ksekli�ini tekrar varsay�l� yapal�m...
        s�n�rlay�c�.weighty = 1.0; // D��menin varsay�l� y�ksekli�inden fazlas� talep ediliyor...
        s�n�rlay�c�.anchor = GridBagConstraints.PAGE_END; // Bu d��me pencere dibine oturacak...
        s�n�rlay�c�.insets = new Insets (10,0,0,0);  // D��me �st� 10px aral�k kalacak...
        s�n�rlay�c�.gridx = 1; // Izgaran�n (1,2) konumuna...
        s�n�rlay�c�.gridy = 2;
        s�n�rlay�c�.gridwidth = 2; // Bu d��me 2 Izgara h�cresi uzunlu�unda olacak...
        kab.add (d��me, s�n�rlay�c�);
        kab.setBackground (Color.PINK);
    } // komponentleriPanoyaEkle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Izgara �anta Serilim G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        komponentleriPanoyaEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_11 s�n�f� sonu...