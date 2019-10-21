// J5c_39.java: ProgressBarDemo (GeliþenÇubukGösterisi) örneði.

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
    private JProgressBar geliþenÇubuk;
    private JButton baþlatDüðmesi;
    private JTextArea metinAlaný;
    private Görev vazife;

    class Görev extends SwingWorker<Void, Void> {
        // Zemin siciminde yürütülen esas görev metodu...
        @Override // Bu metod gövdesiz soyut hazýr metodun overriding/esgeçmesi'dir...
        public Void doInBackground() {
            Random tesadüfi = new Random();
            int geliþme = 0;

            // Geliþme çuuðuna baþlangýç deðerini verelim...
            setProgress (0);
            while (geliþme < 100) {
                // Düzenli birer sn.uykularla iþleyecek...
                try {Thread.sleep (1000); //tesadüfi.nextInt (1000): deðiþen (0->1) sn.deðil...
                }catch (InterruptedException boþver) {}
                //tesadüfi (0->10 arasý) deðil düzgün birer basamaklý geliþme.
                geliþme += tesadüfi.nextInt (10); // Geliþme tesadüfi (0->10) basamaklý olacak...
                setProgress (Math.min (geliþme, 100));
            } // while döngüsü sonu...

            return null;
        } // doInBackground() hazýr metodu sonu...

        // Olay raporlayan sicimde iþletilir...
        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep(); // Çubuk geliþmesi bitince bipler...
            baþlatDüðmesi.setEnabled (true);
            setCursor (null); // Metin alanýnda imleç görünmesin...
            metinAlaný.append ("Bitti!\n");
        } // done() hazýr metodu sonu...
    } // Görev sýnýfý sonu...

    public J5c_39() {// Kurucu...
        super (new BorderLayout());

        // Düðmeli, geliþme çubuklu ve kaydýrmalý metin alanlý UI'yi yaratýp kuralým...
        baþlatDüðmesi = new JButton ("Baþlat");
        baþlatDüðmesi.setActionCommand ("baþlat");
        baþlatDüðmesi.addActionListener (this);

        geliþenÇubuk = new JProgressBar (0, 100);
        geliþenÇubuk.setValue (0);
        geliþenÇubuk.setStringPainted (true);

        metinAlaný = new JTextArea (5, 20);
        metinAlaný.setMargin (new Insets (5,5,5,5)); // padding iç tamponlar...
        metinAlaný.setEditable (false); // Müdahalesiz...
        metinAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel panel = new JPanel();
        panel.add (baþlatDüðmesi);
        panel.add (geliþenÇubuk);
        panel.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        add (panel, BorderLayout.PAGE_START);
        add (new JScrollPane (metinAlaný), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20)); // Komponentler için marj boþluklarý...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_39() kurucusu sonu...

    // Baþlat düðmesine duyarlý...
    public void actionPerformed (ActionEvent olay) {
        baþlatDüðmesi.setEnabled (false); // Geliþme tamamlanýncaya kadar deaktive olsun...
        // Geliþme tamamlanýncaya kadar cam simit imleci dönsün...
        setCursor (Cursor.getPredefinedCursor (Cursor.WAIT_CURSOR));
        //javax.swing.SwingWorker tiplemesi yeniden kullanýlmadýðýndan ihtiyaçta yeniden yaratýlmalýdýr...
        vazife = new Görev();
        vazife.addPropertyChangeListener (this); // Çubuðun her geliþmesine duyarlý olarak rapor verecek...
        vazife.execute(); // Görev sicimini iþletir...
    } // actionPerformed(..) hazýr metodu sonu...

    // Görev siciminindeki çubuðun her geliþim deðiþmesinde iþletilir...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName()) {
            int geliþme = (Integer) olay.getNewValue();
            geliþenÇubuk.setValue (geliþme);
            metinAlaný.append (String.format ("Görev [%% %d] tamamlandý.\n", vazife.getProgress()));
        } // if kararý sonu...
    } // propertyChange(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Geliþen Çubuk Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_39(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_39 sýnýfý sonu...