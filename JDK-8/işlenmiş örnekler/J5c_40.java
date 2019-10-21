// J5c_40.java: ProgressBarDemo2 (Geli�me�ubu�uG�sterisi2) �rne�i.

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

// �lkinden fark�: Bekleme d�nen cam halka/simit imlecinin olmamas� ve ilk-kalk��taki 1-2 sn'lik git-gel simulasyon/benzetme'si...
public class J5c_40 extends JPanel implements ActionListener, PropertyChangeListener {
    private JProgressBar geli�me�ubu�u;
    private JButton ba�latD��mesi;
    private JTextArea metinAlan�;
    private G�rev i�lem;

    class G�rev extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random tesad�fi = new Random();
            int geli�me = 0;
            //setProgress (0);
            geli�me�ubu�u.setValue (0);
            // �lk kalk�� simulasyonu/benzetmesini g�relim diye 1-3 sn. bekleyelim...
            try {Thread.sleep (1000 + tesad�fi.nextInt (3000)); }catch (InterruptedException ald�rma) {}
            while (geli�me < 100) {
                // (0->1) sn'lik beklemelerle ilerleyelim...
                try {Thread.sleep (tesad�fi.nextInt (1000)); }catch (InterruptedException bo�ge�) {}
                geli�me += tesad�fi.nextInt (10); // Geli�me art��� tesad�fi (0->10) olsun...
                setProgress (Math.min (geli�me, 100)); // Geli�me ad�m� daima k����� yani geli�me olacak...
            } // while d�ng�s� sonu...
            return null;
        } // doInBackground() haz�r metodu sonu...

        // Geli�me bitince otomatikmen i�letilir...
        public void done() {
            Toolkit.getDefaultToolkit().beep(); // Geli�me sonucunda bip'ler...
            ba�latD��mesi.setEnabled (true); // Biti� sonras� tekrar aktive...
            metinAlan�.append ("B�TT�!\n\n");
        } // done() haz�r metodu sonu...
    } // G�rev s�n�f� sonu...

    public J5c_40() {// Kurucu...
        super (new BorderLayout());

        // T�m komponentleri yarat�p kural�m...
        ba�latD��mesi = new JButton ("Ba�lat");
        ba�latD��mesi.setActionCommand ("ba�lat");
        ba�latD��mesi.addActionListener (this);

        geli�me�ubu�u = new JProgressBar (0, 100);
        geli�me�ubu�u.setValue (0);
        geli�me�ubu�u.setStringPainted (true); // Hen�z ba�lat�lmasa da %0 g�r�ns�n...

        metinAlan� = new JTextArea (5, 20);
        metinAlan�.setMargin (new Insets (5,5,5,5)); // �� padding/tampon bo�luklar�...
        metinAlan�.setEditable (false); // M�dahalesiz...
        metinAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel panel = new JPanel();
        panel.add (ba�latD��mesi);
        panel.add (geli�me�ubu�u);
        panel.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        add (panel, BorderLayout.PAGE_START);
        add (new JScrollPane (metinAlan�), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20)); // D�� margin/marj bo�luklar�...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_40() kurucusu sonu...

    // Ba�lat d��mesine duyarl�...
    public void actionPerformed (ActionEvent olay) {
        geli�me�ubu�u.setIndeterminate (true); // �lk-kalk�� git-geli ba�lat�l�r...
        ba�latD��mesi.setEnabled (false); // Bitinceye kadar deaktive...
        // Tekrar kullan�ms�z javax.swing.SwingWorker tiplemesi her kerede yeniden yarat�lmal�d�r...
        i�lem = new G�rev();
        i�lem.addPropertyChangeListener (this);
        i�lem.execute();
    } // actionPerformed(..) haz�r metodu sonu...

    // Geli�im �ubu�undaki de�i�ime duyarl�d�r...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName()) {
            int geli�im = (Integer)olay.getNewValue();
            geli�me�ubu�u.setIndeterminate (false); // Git-gel kapat�l�p geli�me rengi ikame edilir...
            geli�me�ubu�u.setValue (geli�im);
            metinAlan�.append (String.format ("G�rev [%% %d] tamamland�.\n", geli�im));
        } // if karar� sonu...
    } // propertyChange(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Geli�me �ubu�u G�sterimi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_40();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_40 s�n�f� sonu...