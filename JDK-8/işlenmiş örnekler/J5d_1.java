// J5d_1.java: Flipper (Sayaç) örneði.

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;

import javax.swing.border.Border;

import java.util.List;
import java.util.Random;

public class J5d_1 extends JFrame implements ActionListener {
    private final GridBagConstraints sýnýrlayýcýlar;
    private final JTextField sayýSatýrý, dubleSatýrý, sapmaSatýrý;
    private final Border sýnýr = BorderFactory.createLoweredBevelBorder();
    private final JButton baþlatDüðmesi, durdurDüðmesi;
    private SayaçGörevi sayaçGörevi;

    public J5d_1() {// Kurucu...
        super ("Sayaç");
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Müdahalesiz metin satýrý kutularýný kuralým...
        getContentPane().setLayout (new GridBagLayout());
        sýnýrlayýcýlar = new GridBagConstraints();
        sýnýrlayýcýlar.insets = new Insets (3, 10, 3, 10);
        sayýSatýrý = metinSatýrýnýKur();
        dubleSatýrý = metinSatýrýnýKur();
        sapmaSatýrý = metinSatýrýnýKur();

        // Butonlarý kuralým...
        baþlatDüðmesi = butonuKur ("Baþlat");
        durdurDüðmesi = butonuKur ("Durdur");
        durdurDüðmesi.setEnabled (false); // Ýlk anda deaktif...

        // Konumlu ve paketli pencereyi gösterelim...
        setLocation (200,100);
        pack();
        setVisible (true);
    } // J5d_1() kurucusu sonu...

    private JTextField metinSatýrýnýKur() {
        JTextField metinSatýrý = new JTextField (20);
        metinSatýrý.setEditable (false); // Müdahalesiz...
        metinSatýrý.setHorizontalAlignment (JTextField.RIGHT); // Rakamlar saða yanaþýk...
        metinSatýrý.setBorder (sýnýr);
        getContentPane().add (metinSatýrý, sýnýrlayýcýlar);
        return metinSatýrý;
    } // metinSatýrýnýKur() metodu sonu...

    private JButton butonuKur (String baþlýk) {
        JButton düðme = new JButton (baþlýk);
        düðme.setActionCommand (baþlýk);
        düðme.addActionListener (this);// Düðmeyi dinleyiciye duyarlayalým...
        getContentPane().add (düðme, sýnýrlayýcýlar);
        return düðme;
    } // butonuKur(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("Baþlat" == olay.getActionCommand()) {
            baþlatDüðmesi.setEnabled (false);
            durdurDüðmesi.setEnabled (true);
            (sayaçGörevi = new SayaçGörevi()).execute(); // SayaçGörevi hazýr metodlarýný çalýþtýrýr...
        }else if ("Durdur" == olay.getActionCommand()) {
            baþlatDüðmesi.setEnabled (true);
            durdurDüðmesi.setEnabled (false);
            sayaçGörevi.cancel (true);
            sayaçGörevi = null;
        } // iç-if kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    private class SayaçGörevi extends SwingWorker<Void, ÝlkÝkiSayaç> {
        @Override
        protected Void doInBackground() {
            long sayaç = 0;
            long dublesi = 0;
            Random random = new Random();
            while (!isCancelled()) {// Durdur'a basýlmamýþsa...
                dublesi++;
                if (random.nextBoolean()) sayaç++; // dublesi 2 artarken sayaç tek artar...
                publish (new ÝlkÝkiSayaç (sayaç, dublesi));
            } // while döngüsü sonu...
            return null;
        } // doInBackground() hazýr metodu sonu...

        @Override
        protected void process (List<ÝlkÝkiSayaç> sayaçÇifti) {
            ÝlkÝkiSayaç ikiSayaç = sayaçÇifti.get (sayaçÇifti.size() - 1);
            sayýSatýrý.setText (String.format ("%d", ikiSayaç.sayaç));
            dubleSatýrý.setText (String.format ("%d", ikiSayaç.dublesi));
            sapmaSatýrý.setText (String.format("%.10g", ((double)ikiSayaç.sayaç) / ((double)ikiSayaç.dublesi) - 0.5));
        } // process(..) hazýr metodu sonu...
    } // SayaçGörevi sýnýfý sonu...

    private static class ÝlkÝkiSayaç {
        private final long sayaç, dublesi;
        ÝlkÝkiSayaç (long sayaç, long dublesi) {
            this.sayaç = sayaç;
            this.dublesi = dublesi;
        } // ÝlkÝkiSayaç(..) kurucusu sonu...
    } // ÝlkÝkiSayaç sýnýfý sonu...

    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {new J5d_1();} // Kurucuyu çaðýrýr...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5d_1 sýnýfý sonu...