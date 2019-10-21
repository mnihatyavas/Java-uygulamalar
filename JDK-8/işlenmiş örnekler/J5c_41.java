// J5c_41.java: ProgressMonitorDemo (Geli�meMonitoruG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Insets; // �� padding/tampon aral��� i�in...

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingWorker;
import javax.swing.ProgressMonitor;
import javax.swing.BorderFactory; // D�� margin/marj aral��� i�in...

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.util.Random;

// Geli�me �ubu�u raporun yaz�ld��� ana pencerede de�il, ayr� bir geli�me monitorundad�r...
public class J5c_41 extends JPanel implements ActionListener, PropertyChangeListener {
    private ProgressMonitor geli�meMonitoru; // Ger�ekte �-ikonlu'lu JOptionPane panosudur...
    private JButton ba�latD��mesi;
    private JTextArea metinAlan�;
    private G�rev vazife;

    class G�rev extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random tesad�fi = new Random();
            int geli�me = 0;
            setProgress (0); // Metin  alan� i�in rapor �retecek...
            //geli�meMonitoru.setValue (0);
            try {Thread.sleep (1000);
                while (geli�me < 100 && !isCancelled()) {
                    Thread.sleep (tesad�fi.nextInt (1000));
                    geli�me += tesad�fi.nextInt (10);
                    setProgress (Math.min (geli�me, 100)); // Geli�me monitoruna de�il...
                } // while d�ng�s� sonu...
            }catch (InterruptedException ald�rma) {}
            return null;
        } // doInBackground() haz�r metodu sonu...

        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            ba�latD��mesi.setEnabled (true);
            //geli�meMonitoru.setProgress (0);
        } // done() haz�r metodu sonu...
    } // G�rev s�n�f� sonu...

    public J5c_41() {// Kurucu...
        super (new BorderLayout());

        // D��me ve metin alan�n� yarat�p kural�m...
        ba�latD��mesi = new JButton ("Ba�lat");
        ba�latD��mesi.setActionCommand ("ba�lat");
        ba�latD��mesi.addActionListener (this);

        metinAlan� = new JTextArea (5, 20);
        metinAlan�.setMargin (new Insets (5,5,5,5));
        metinAlan�.setEditable (false);
        metinAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        add (ba�latD��mesi, BorderLayout.PAGE_START); // D��me metin alan� geni�li�indedir...
        add (new JScrollPane (metinAlan�), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_41() kurucusu sonu...

    // Ba�lat d��mesine duyarl�...
    public void actionPerformed (ActionEvent olay) {
        geli�meMonitoru = new ProgressMonitor (J5c_41.this, "Geli�me G�revini Ko�turur", "", 0, 100);
        geli�meMonitoru.setProgress (0);
        vazife = new G�rev();
        vazife.addPropertyChangeListener (this);
        vazife.execute();
        ba�latD��mesi.setEnabled (false);
    } // actionPerformed(..) haz�r metodu sonu...

    // Geli�me monitoru de�i�imine duyarl�...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName() ) {
            int geli�me = (Integer)olay.getNewValue();
            geli�meMonitoru.setProgress (geli�me);
            String mesaj = String.format ("G�rev [%% %d] tamamland�.\n", geli�me);
            geli�meMonitoru.setNote (mesaj); // Mesaj geli�me monitoruna da (akmadan) yaz�ls�n...
            metinAlan�.append (mesaj);
            if (geli�meMonitoru.isCanceled() || vazife.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (geli�meMonitoru.isCanceled()) {
                    vazife.cancel (true);
                    metinAlan�.append ("G�rev iptal edildi!\n\n");
                }else metinAlan�.append ("Bitti!\n\n");
                ba�latD��mesi.setEnabled (true);
            } // 13-if karar� sonu...
        } // 9-if karar� sonu...
    } // propertyChange(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Geli�me Monitoru G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_41();
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
} // J5c_41 s�n�f� sonu...