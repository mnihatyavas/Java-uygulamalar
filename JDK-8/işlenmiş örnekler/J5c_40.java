// J5c_40.java: ProgressBarDemo2 (GeliþmeÇubuðuGösterisi2) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.SwingWorker;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.util.Random;

// Ýlkinden farký: Bekleme dönen cam halka/simit imlecinin olmamasý ve ilk-kalkýþtaki 1-2 sn'lik git-gel simulasyon/benzetme'si...
public class J5c_40 extends JPanel implements ActionListener, PropertyChangeListener {
    private JProgressBar geliþmeÇubuðu;
    private JButton baþlatDüðmesi;
    private JTextArea metinAlaný;
    private Görev iþlem;

    class Görev extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random tesadüfi = new Random();
            int geliþme = 0;
            //setProgress (0);
            geliþmeÇubuðu.setValue (0);
            // Ýlk kalkýþ simulasyonu/benzetmesini görelim diye 1-3 sn. bekleyelim...
            try {Thread.sleep (1000 + tesadüfi.nextInt (3000)); }catch (InterruptedException aldýrma) {}
            while (geliþme < 100) {
                // (0->1) sn'lik beklemelerle ilerleyelim...
                try {Thread.sleep (tesadüfi.nextInt (1000)); }catch (InterruptedException boþgeç) {}
                geliþme += tesadüfi.nextInt (10); // Geliþme artýþý tesadüfi (0->10) olsun...
                setProgress (Math.min (geliþme, 100)); // Geliþme adýmý daima küçüðü yani geliþme olacak...
            } // while döngüsü sonu...
            return null;
        } // doInBackground() hazýr metodu sonu...

        // Geliþme bitince otomatikmen iþletilir...
        public void done() {
            Toolkit.getDefaultToolkit().beep(); // Geliþme sonucunda bip'ler...
            baþlatDüðmesi.setEnabled (true); // Bitiþ sonrasý tekrar aktive...
            metinAlaný.append ("BÝTTÝ!\n\n");
        } // done() hazýr metodu sonu...
    } // Görev sýnýfý sonu...

    public J5c_40() {// Kurucu...
        super (new BorderLayout());

        // Tüm komponentleri yaratýp kuralým...
        baþlatDüðmesi = new JButton ("Baþlat");
        baþlatDüðmesi.setActionCommand ("baþlat");
        baþlatDüðmesi.addActionListener (this);

        geliþmeÇubuðu = new JProgressBar (0, 100);
        geliþmeÇubuðu.setValue (0);
        geliþmeÇubuðu.setStringPainted (true); // Henüz baþlatýlmasa da %0 görünsün...

        metinAlaný = new JTextArea (5, 20);
        metinAlaný.setMargin (new Insets (5,5,5,5)); // Ýç padding/tampon boþluklarý...
        metinAlaný.setEditable (false); // Müdahalesiz...
        metinAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel panel = new JPanel();
        panel.add (baþlatDüðmesi);
        panel.add (geliþmeÇubuðu);
        panel.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        add (panel, BorderLayout.PAGE_START);
        add (new JScrollPane (metinAlaný), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20)); // Dýþ margin/marj boþluklarý...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_40() kurucusu sonu...

    // Baþlat düðmesine duyarlý...
    public void actionPerformed (ActionEvent olay) {
        geliþmeÇubuðu.setIndeterminate (true); // Ýlk-kalkýþ git-geli baþlatýlýr...
        baþlatDüðmesi.setEnabled (false); // Bitinceye kadar deaktive...
        // Tekrar kullanýmsýz javax.swing.SwingWorker tiplemesi her kerede yeniden yaratýlmalýdýr...
        iþlem = new Görev();
        iþlem.addPropertyChangeListener (this);
        iþlem.execute();
    } // actionPerformed(..) hazýr metodu sonu...

    // Geliþim çubuðundaki deðiþime duyarlýdýr...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName()) {
            int geliþim = (Integer)olay.getNewValue();
            geliþmeÇubuðu.setIndeterminate (false); // Git-gel kapatýlýp geliþme rengi ikame edilir...
            geliþmeÇubuðu.setValue (geliþim);
            metinAlaný.append (String.format ("Görev [%% %d] tamamlandý.\n", geliþim));
        } // if kararý sonu...
    } // propertyChange(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Geliþme Çubuðu Gösterimi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_40();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_40 sýnýfý sonu...