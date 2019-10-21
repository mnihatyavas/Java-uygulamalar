// J5c_39.java: ProgressBarDemo (Geli�en�ubukG�sterisi) �rne�i.

import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.BorderFactory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.util.Random;

public class J5c_39 extends JPanel implements ActionListener, PropertyChangeListener {
    private JProgressBar geli�en�ubuk;
    private JButton ba�latD��mesi;
    private JTextArea metinAlan�;
    private G�rev vazife;

    class G�rev extends SwingWorker<Void, Void> {
        // Zemin siciminde y�r�t�len esas g�rev metodu...
        @Override // Bu metod g�vdesiz soyut haz�r metodun overriding/esge�mesi'dir...
        public Void doInBackground() {
            Random tesad�fi = new Random();
            int geli�me = 0;

            // Geli�me �uu�una ba�lang�� de�erini verelim...
            setProgress (0);
            while (geli�me < 100) {
                // D�zenli birer sn.uykularla i�leyecek...
                try {Thread.sleep (1000); //tesad�fi.nextInt (1000): de�i�en (0->1) sn.de�il...
                }catch (InterruptedException bo�ver) {}
                //tesad�fi (0->10 aras�) de�il d�zg�n birer basamakl� geli�me.
                geli�me += tesad�fi.nextInt (10); // Geli�me tesad�fi (0->10) basamakl� olacak...
                setProgress (Math.min (geli�me, 100));
            } // while d�ng�s� sonu...

            return null;
        } // doInBackground() haz�r metodu sonu...

        // Olay raporlayan sicimde i�letilir...
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep(); // �ubuk geli�mesi bitince bipler...
            ba�latD��mesi.setEnabled (true);
            setCursor (null); // Metin alan�nda imle� g�r�nmesin...
            metinAlan�.append ("Bitti!\n");
        } // done() haz�r metodu sonu...
    } // G�rev s�n�f� sonu...

    public J5c_39() {// Kurucu...
        super (new BorderLayout());

        // D��meli, geli�me �ubuklu ve kayd�rmal� metin alanl� UI'yi yarat�p kural�m...
        ba�latD��mesi = new JButton ("Ba�lat");
        ba�latD��mesi.setActionCommand ("ba�lat");
        ba�latD��mesi.addActionListener (this);

        geli�en�ubuk = new JProgressBar (0, 100);
        geli�en�ubuk.setValue (0);
        geli�en�ubuk.setStringPainted (true);

        metinAlan� = new JTextArea (5, 20);
        metinAlan�.setMargin (new Insets (5,5,5,5)); // padding i� tamponlar...
        metinAlan�.setEditable (false); // M�dahalesiz...
        metinAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel panel = new JPanel();
        panel.add (ba�latD��mesi);
        panel.add (geli�en�ubuk);
        panel.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        add (panel, BorderLayout.PAGE_START);
        add (new JScrollPane (metinAlan�), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20)); // Komponentler i�in marj bo�luklar�...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_39() kurucusu sonu...

    // Ba�lat d��mesine duyarl�...
    public void actionPerformed (ActionEvent olay) {
        ba�latD��mesi.setEnabled (false); // Geli�me tamamlan�ncaya kadar deaktive olsun...
        // Geli�me tamamlan�ncaya kadar cam simit imleci d�ns�n...
        setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        //javax.swing.SwingWorker tiplemesi yeniden kullan�lmad���ndan ihtiya�ta yeniden yarat�lmal�d�r...
        vazife = new G�rev();
        vazife.addPropertyChangeListener (this); // �ubu�un her geli�mesine duyarl� olarak rapor verecek...
        vazife.execute(); // G�rev sicimini i�letir...
    } // actionPerformed(..) haz�r metodu sonu...

    // G�rev siciminindeki �ubu�un her geli�im de�i�mesinde i�letilir...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName()) {
            int geli�me = (Integer) olay.getNewValue();
            geli�en�ubuk.setValue (geli�me);
            metinAlan�.append (String.format ("G�rev [%% %d] tamamland�.\n", vazife.getProgress()));
        } // if karar� sonu...
    } // propertyChange(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Geli�en �ubuk G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_39(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_39 s�n�f� sonu...