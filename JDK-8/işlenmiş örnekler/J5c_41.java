// J5c_41.java: ProgressMonitorDemo (GeliþmeMonitoruGösterisi) örneði.

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.Insets; // Ýç padding/tampon aralýðý için...

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
import javax.swing.BorderFactory; // Dýþ margin/marj aralýðý için...

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.util.Random;

// Geliþme çubuðu raporun yazýldýðý ana pencerede deðil, ayrý bir geliþme monitorundadýr...
public class J5c_41 extends JPanel implements ActionListener, PropertyChangeListener {
    private ProgressMonitor geliþmeMonitoru; // Gerçekte Ý-ikonlu'lu JOptionPane panosudur...
    private JButton baþlatDüðmesi;
    private JTextArea metinAlaný;
    private Görev vazife;

    class Görev extends SwingWorker<Void, Void> {
        @Override
        public Void doInBackground() {
            Random tesadüfi = new Random();
            int geliþme = 0;
            setProgress (0); // Metin  alaný için rapor üretecek...
            //geliþmeMonitoru.setValue (0);
            try {Thread.sleep (1000);
                while (geliþme < 100 && !isCancelled()) {
                    Thread.sleep (tesadüfi.nextInt (1000));
                    geliþme += tesadüfi.nextInt (10);
                    setProgress (Math.min (geliþme, 100)); // Geliþme monitoruna deðil...
                } // while döngüsü sonu...
            }catch (InterruptedException aldýrma) {}
            return null;
        } // doInBackground() hazýr metodu sonu...

        @Override
        public void done() {
            Toolkit.getDefaultToolkit().beep();
            baþlatDüðmesi.setEnabled (true);
            //geliþmeMonitoru.setProgress (0);
        } // done() hazýr metodu sonu...
    } // Görev sýnýfý sonu...

    public J5c_41() {// Kurucu...
        super (new BorderLayout());

        // Düðme ve metin alanýný yaratýp kuralým...
        baþlatDüðmesi = new JButton ("Baþlat");
        baþlatDüðmesi.setActionCommand ("baþlat");
        baþlatDüðmesi.addActionListener (this);

        metinAlaný = new JTextArea (5, 20);
        metinAlaný.setMargin (new Insets (5,5,5,5));
        metinAlaný.setEditable (false);
        metinAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        add (baþlatDüðmesi, BorderLayout.PAGE_START); // Düðme metin alaný geniþliðindedir...
        add (new JScrollPane (metinAlaný), BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20, 20, 20, 20));
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_41() kurucusu sonu...

    // Baþlat düðmesine duyarlý...
    public void actionPerformed (ActionEvent olay) {
        geliþmeMonitoru = new ProgressMonitor (J5c_41.this, "Geliþme Görevini Koþturur", "", 0, 100);
        geliþmeMonitoru.setProgress (0);
        vazife = new Görev();
        vazife.addPropertyChangeListener (this);
        vazife.execute();
        baþlatDüðmesi.setEnabled (false);
    } // actionPerformed(..) hazýr metodu sonu...

    // Geliþme monitoru deðiþimine duyarlý...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("progress" == olay.getPropertyName() ) {
            int geliþme = (Integer)olay.getNewValue();
            geliþmeMonitoru.setProgress (geliþme);
            String mesaj = String.format ("Görev [%% %d] tamamlandý.\n", geliþme);
            geliþmeMonitoru.setNote (mesaj); // Mesaj geliþme monitoruna da (akmadan) yazýlsýn...
            metinAlaný.append (mesaj);
            if (geliþmeMonitoru.isCanceled() || vazife.isDone()) {
                Toolkit.getDefaultToolkit().beep();
                if (geliþmeMonitoru.isCanceled()) {
                    vazife.cancel (true);
                    metinAlaný.append ("Görev iptal edildi!\n\n");
                }else metinAlaný.append ("Bitti!\n\n");
                baþlatDüðmesi.setEnabled (true);
            } // 13-if kararý sonu...
        } // 9-if kararý sonu...
    } // propertyChange(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Geliþme Monitoru Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_41();
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
} // J5c_41 sýnýfý sonu...