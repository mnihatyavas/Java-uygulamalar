// J5c_30.java: ListDemo (ListeG�sterimi) �rne�i.

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class J5c_30 extends JPanel implements ListSelectionListener {
    private JList liste;
    private DefaultListModel listeModeli;

    private static final String i�eAlStr = "��e Al";
    private static final String i�tenAtStr = "��ten At";
    private JButton i�tenAtD��mesi;
    private JTextField i�g�renAd�;

    public J5c_30() {// Kurucu...
        super (new BorderLayout());

        listeModeli = new DefaultListModel();
        listeModeli.addElement ("Hatice Yava� Ka�ar");
        listeModeli.addElement ("Nihal Yava� Candan");
        listeModeli.addElement ("Sevim Yava�");
        listeModeli.addElement ("M.Nihat Yava�");

        // Listeyi yarat�p bir kayd�rma panosuna koyal�m...
        liste = new JList (listeModeli);
        liste.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this);
        liste.setVisibleRowCount (5);
        liste.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane listeKayd�rmaPanosu = new JScrollPane (liste);

        JButton i�eAlD��mesi = new JButton (i�eAlStr);
        ��eAlDinleyicisi i�eAlDinleyicisi = new ��eAlDinleyicisi (i�eAlD��mesi);
        i�eAlD��mesi.setActionCommand (i�eAlStr);
        i�eAlD��mesi.addActionListener (i�eAlDinleyicisi);
        i�eAlD��mesi.setEnabled (false);

        i�tenAtD��mesi = new JButton (i�tenAtStr);
        i�tenAtD��mesi.setActionCommand (i�tenAtStr);
        i�tenAtD��mesi.addActionListener (new ��tenAtDinleyicisi());

        i�g�renAd� = new JTextField (10);
        i�g�renAd�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        i�g�renAd�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        i�g�renAd�.addActionListener (i�eAlDinleyicisi);
        i�g�renAd�.getDocument().addDocumentListener (i�eAlDinleyicisi);
        String isim = listeModeli.getElementAt (liste.getSelectedIndex()).toString();

        // BoxLayout kullanan bir panel yaratal�m...
        JPanel d��mePanosu = new JPanel();
        d��mePanosu.setLayout (new BoxLayout (d��mePanosu, BoxLayout.LINE_AXIS));
        d��mePanosu.add (i�tenAtD��mesi);
        d��mePanosu.add (Box.createHorizontalStrut (5));
        d��mePanosu.add (new JSeparator (SwingConstants.VERTICAL));
        d��mePanosu.add (Box.createHorizontalStrut (5));
        d��mePanosu.add (i�g�renAd�);
        d��mePanosu.add (i�eAlD��mesi);
        d��mePanosu.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        d��mePanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        add (listeKayd�rmaPanosu, BorderLayout.CENTER);
        add (d��mePanosu, BorderLayout.PAGE_END);
    } // J5c_30() kurucu sonu...

    class ��tenAtDinleyicisi implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            // �ncelikle listede i�ten at�lacak bir i�g�ren se�ilmi� olmal�d�r...
            int endeks = liste.getSelectedIndex();
            listeModeli.remove (endeks);

            if (listeModeli.getSize() == 0) i�tenAtD��mesi.setEnabled (false);
            else if (endeks == listeModeli.getSize()) {
                endeks--;
                liste.setSelectedIndex (endeks);
                liste.ensureIndexIsVisible (endeks);
            } // i�-if karar� sonu...
        } // actionPerformed(..) haz�r metodu sonu...
    } // ��tenAtDinleyicisi s�n�f� sonu...

    // Bu dinleyici metin sat�r� ve i�e al d��mesi taraf�ndan m��tereken payla��lmaktad�r...
    class ��eAlDinleyicisi implements ActionListener, DocumentListener {
        private boolean aktifMi = false;
        private JButton d��me;

        public ��eAlDinleyicisi (JButton d��me) {this.d��me = d��me;} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            String isim = i�g�renAd�.getText();

            // Kullan�c� ge�erli isim girmediyse...
            if (isim.equals ("") || listedeMevcutMu (isim)) {// �sim "" bo� olmaz, ��nk� d��me deaktive oluyor...
                Toolkit.getDefaultToolkit().beep();
                i�g�renAd�.requestFocusInWindow();
                i�g�renAd�.selectAll();
                return;
            } // if karar� sonu...

            int endeks = liste.getSelectedIndex();
            if (endeks == -1) endeks = 0; // Se�ili yoksa liste ba��na ekle...
            else endeks++; // Yoksa se�ilenin alt�na ekle...

            listeModeli.insertElementAt (i�g�renAd�.getText(), endeks);
            // Listede se�ili ismin alt�na (araya) ekler. Sadece liste sonuna eklemek isteseydik:
            // listeModeli.addElement (i�g�renAd�.getText());

            // Metin sat�r�n� yeni giri� i�in temizleyelim...
            i�g�renAd�.requestFocusInWindow();
            i�g�renAd�.setText ("");

            // Yeni i�e al�nan� listede se�ili ve g�r�n�r tutal�m...
            liste.setSelectedIndex (endeks);
            liste.ensureIndexIsVisible (endeks);
        } // actionPerformed(..) haz�r metodu sonu...

        // Listedeki mevcudiyet, ekstra bo�luklar ve b�y�k-k���k harf y�n�nden test edilebilirdi...
        protected boolean listedeMevcutMu (String isim) {return listeModeli.contains (isim);}

        // DocumentListener override/esge�me haz�r metodlar�...
        public void insertUpdate (DocumentEvent olay) {d��meyiAktiveEt();}
        public void removeUpdate (DocumentEvent olay) {bo�MetinSat�r�n�Y�net (olay);}
        public void changedUpdate (DocumentEvent olay) {if (!bo�MetinSat�r�n�Y�net (olay)) d��meyiAktiveEt();}

        private void d��meyiAktiveEt() {if (!aktifMi) d��me.setEnabled (true);}

        private boolean bo�MetinSat�r�n�Y�net (DocumentEvent olay) {
            if (olay.getDocument().getLength() <= 0) {
                d��me.setEnabled (false);
                aktifMi = false;
                return true;
            } // if karar� sonu...
            return false;
        } // bo�MetinSat�r�n�Y�net(..) metodu sonu...
    } // ��eAlDinleyicisi s�n�f� sonu...

    // Bu metod ListSelectionListener dinleyicisi i�in gereklidir...
    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting() == false) {
            if (liste.getSelectedIndex() == -1) i�tenAtD��mesi.setEnabled (false);
            else i�tenAtD��mesi.setEnabled (true);
        } // d��-if karar� sonu...
    } // valueChanged(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Liste G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_30();
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
} // J5c_30 s�n�f� sonu...