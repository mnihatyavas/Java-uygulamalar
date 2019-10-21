// J5d_1.java: Flipper (Saya�) �rne�i.

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
    private final GridBagConstraints s�n�rlay�c�lar;
    private final JTextField say�Sat�r�, dubleSat�r�, sapmaSat�r�;
    private final Border s�n�r = BorderFactory.createLoweredBevelBorder();
    private final JButton ba�latD��mesi, durdurD��mesi;
    private Saya�G�revi saya�G�revi;

    public J5d_1() {// Kurucu...
        super ("Saya�");
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // M�dahalesiz metin sat�r� kutular�n� kural�m...
        getContentPane().setLayout (new GridBagLayout());
        s�n�rlay�c�lar = new GridBagConstraints();
        s�n�rlay�c�lar.insets = new Insets (3, 10, 3, 10);
        say�Sat�r� = metinSat�r�n�Kur();
        dubleSat�r� = metinSat�r�n�Kur();
        sapmaSat�r� = metinSat�r�n�Kur();

        // Butonlar� kural�m...
        ba�latD��mesi = butonuKur ("Ba�lat");
        durdurD��mesi = butonuKur ("Durdur");
        durdurD��mesi.setEnabled (false); // �lk anda deaktif...

        // Konumlu ve paketli pencereyi g�sterelim...
        setLocation (200,100);
        pack();
        setVisible (true);
    } // J5d_1() kurucusu sonu...

    private JTextField metinSat�r�n�Kur() {
        JTextField metinSat�r� = new JTextField (20);
        metinSat�r�.setEditable (false); // M�dahalesiz...
        metinSat�r�.setHorizontalAlignment (JTextField.RIGHT); // Rakamlar sa�a yana��k...
        metinSat�r�.setBorder (s�n�r);
        getContentPane().add (metinSat�r�, s�n�rlay�c�lar);
        return metinSat�r�;
    } // metinSat�r�n�Kur() metodu sonu...

    private JButton butonuKur (String ba�l�k) {
        JButton d��me = new JButton (ba�l�k);
        d��me.setActionCommand (ba�l�k);
        d��me.addActionListener (this);// D��meyi dinleyiciye duyarlayal�m...
        getContentPane().add (d��me, s�n�rlay�c�lar);
        return d��me;
    } // butonuKur(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("Ba�lat" == olay.getActionCommand()) {
            ba�latD��mesi.setEnabled (false);
            durdurD��mesi.setEnabled (true);
            (saya�G�revi = new Saya�G�revi()).execute(); // Saya�G�revi haz�r metodlar�n� �al��t�r�r...
        }else if ("Durdur" == olay.getActionCommand()) {
            ba�latD��mesi.setEnabled (true);
            durdurD��mesi.setEnabled (false);
            saya�G�revi.cancel (true);
            saya�G�revi = null;
        } // i�-if karar� sonu...
    } // actionPerformed(..) haz�r metodu sonu...

    private class Saya�G�revi extends SwingWorker<Void, �lk�kiSaya�> {
        @Override
        protected Void doInBackground() {
            long saya� = 0;
            long dublesi = 0;
            Random random = new Random();
            while (!isCancelled()) {// Durdur'a bas�lmam��sa...
                dublesi++;
                if (random.nextBoolean()) saya�++; // dublesi 2 artarken saya� tek artar...
                publish (new �lk�kiSaya� (saya�, dublesi));
            } // while d�ng�s� sonu...
            return null;
        } // doInBackground() haz�r metodu sonu...

        @Override
        protected void process (List<�lk�kiSaya�> saya��ifti) {
            �lk�kiSaya� ikiSaya� = saya��ifti.get (saya��ifti.size() - 1);
            say�Sat�r�.setText (String.format ("%d", ikiSaya�.saya�));
            dubleSat�r�.setText (String.format ("%d", ikiSaya�.dublesi));
            sapmaSat�r�.setText (String.format("%.10g", ((double)ikiSaya�.saya�) / ((double)ikiSaya�.dublesi) - 0.5));
        } // process(..) haz�r metodu sonu...
    } // Saya�G�revi s�n�f� sonu...

    private static class �lk�kiSaya� {
        private final long saya�, dublesi;
        �lk�kiSaya� (long saya�, long dublesi) {
            this.saya� = saya�;
            this.dublesi = dublesi;
        } // �lk�kiSaya�(..) kurucusu sonu...
    } // �lk�kiSaya� s�n�f� sonu...

    public static void main(String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {new J5d_1();} // Kurucuyu �a��r�r...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5d_1 s�n�f� sonu...