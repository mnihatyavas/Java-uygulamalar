// J5c_30.java: ListDemo (ListeGösterimi) örneði.

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

    private static final String iþeAlStr = "Ýþe Al";
    private static final String iþtenAtStr = "Ýþten At";
    private JButton iþtenAtDüðmesi;
    private JTextField iþgörenAdý;

    public J5c_30() {// Kurucu...
        super (new BorderLayout());

        listeModeli = new DefaultListModel();
        listeModeli.addElement ("Hatice Yavaþ Kaçar");
        listeModeli.addElement ("Nihal Yavaþ Candan");
        listeModeli.addElement ("Sevim Yavaþ");
        listeModeli.addElement ("M.Nihat Yavaþ");

        // Listeyi yaratýp bir kaydýrma panosuna koyalým...
        liste = new JList (listeModeli);
        liste.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this);
        liste.setVisibleRowCount (5);
        liste.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane listeKaydýrmaPanosu = new JScrollPane (liste);

        JButton iþeAlDüðmesi = new JButton (iþeAlStr);
        ÝþeAlDinleyicisi iþeAlDinleyicisi = new ÝþeAlDinleyicisi (iþeAlDüðmesi);
        iþeAlDüðmesi.setActionCommand (iþeAlStr);
        iþeAlDüðmesi.addActionListener (iþeAlDinleyicisi);
        iþeAlDüðmesi.setEnabled (false);

        iþtenAtDüðmesi = new JButton (iþtenAtStr);
        iþtenAtDüðmesi.setActionCommand (iþtenAtStr);
        iþtenAtDüðmesi.addActionListener (new ÝþtenAtDinleyicisi());

        iþgörenAdý = new JTextField (10);
        iþgörenAdý.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        iþgörenAdý.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        iþgörenAdý.addActionListener (iþeAlDinleyicisi);
        iþgörenAdý.getDocument().addDocumentListener (iþeAlDinleyicisi);
        String isim = listeModeli.getElementAt (liste.getSelectedIndex()).toString();

        // BoxLayout kullanan bir panel yaratalým...
        JPanel düðmePanosu = new JPanel();
        düðmePanosu.setLayout (new BoxLayout (düðmePanosu, BoxLayout.LINE_AXIS));
        düðmePanosu.add (iþtenAtDüðmesi);
        düðmePanosu.add (Box.createHorizontalStrut (5));
        düðmePanosu.add (new JSeparator (SwingConstants.VERTICAL));
        düðmePanosu.add (Box.createHorizontalStrut (5));
        düðmePanosu.add (iþgörenAdý);
        düðmePanosu.add (iþeAlDüðmesi);
        düðmePanosu.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        düðmePanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        add (listeKaydýrmaPanosu, BorderLayout.CENTER);
        add (düðmePanosu, BorderLayout.PAGE_END);
    } // J5c_30() kurucu sonu...

    class ÝþtenAtDinleyicisi implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            // Öncelikle listede iþten atýlacak bir iþgören seçilmiþ olmalýdýr...
            int endeks = liste.getSelectedIndex();
            listeModeli.remove (endeks);

            if (listeModeli.getSize() == 0) iþtenAtDüðmesi.setEnabled (false);
            else if (endeks == listeModeli.getSize()) {
                endeks--;
                liste.setSelectedIndex (endeks);
                liste.ensureIndexIsVisible (endeks);
            } // iç-if kararý sonu...
        } // actionPerformed(..) hazýr metodu sonu...
    } // ÝþtenAtDinleyicisi sýnýfý sonu...

    // Bu dinleyici metin satýrý ve iþe al düðmesi tarafýndan müþtereken paylaþýlmaktadýr...
    class ÝþeAlDinleyicisi implements ActionListener, DocumentListener {
        private boolean aktifMi = false;
        private JButton düðme;

        public ÝþeAlDinleyicisi (JButton düðme) {this.düðme = düðme;} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            String isim = iþgörenAdý.getText();

            // Kullanýcý geçerli isim girmediyse...
            if (isim.equals ("") || listedeMevcutMu (isim)) {// Ýsim "" boþ olmaz, çünkü düðme deaktive oluyor...
                Toolkit.getDefaultToolkit().beep();
                iþgörenAdý.requestFocusInWindow();
                iþgörenAdý.selectAll();
                return;
            } // if kararý sonu...

            int endeks = liste.getSelectedIndex();
            if (endeks == -1) endeks = 0; // Seçili yoksa liste baþýna ekle...
            else endeks++; // Yoksa seçilenin altýna ekle...

            listeModeli.insertElementAt (iþgörenAdý.getText(), endeks);
            // Listede seçili ismin altýna (araya) ekler. Sadece liste sonuna eklemek isteseydik:
            // listeModeli.addElement (iþgörenAdý.getText());

            // Metin satýrýný yeni giriþ için temizleyelim...
            iþgörenAdý.requestFocusInWindow();
            iþgörenAdý.setText ("");

            // Yeni iþe alýnaný listede seçili ve görünür tutalým...
            liste.setSelectedIndex (endeks);
            liste.ensureIndexIsVisible (endeks);
        } // actionPerformed(..) hazýr metodu sonu...

        // Listedeki mevcudiyet, ekstra boþluklar ve büyük-küçük harf yönünden test edilebilirdi...
        protected boolean listedeMevcutMu (String isim) {return listeModeli.contains (isim);}

        // DocumentListener override/esgeçme hazýr metodlarý...
        public void insertUpdate (DocumentEvent olay) {düðmeyiAktiveEt();}
        public void removeUpdate (DocumentEvent olay) {boþMetinSatýrýnýYönet (olay);}
        public void changedUpdate (DocumentEvent olay) {if (!boþMetinSatýrýnýYönet (olay)) düðmeyiAktiveEt();}

        private void düðmeyiAktiveEt() {if (!aktifMi) düðme.setEnabled (true);}

        private boolean boþMetinSatýrýnýYönet (DocumentEvent olay) {
            if (olay.getDocument().getLength() <= 0) {
                düðme.setEnabled (false);
                aktifMi = false;
                return true;
            } // if kararý sonu...
            return false;
        } // boþMetinSatýrýnýYönet(..) metodu sonu...
    } // ÝþeAlDinleyicisi sýnýfý sonu...

    // Bu metod ListSelectionListener dinleyicisi için gereklidir...
    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting() == false) {
            if (liste.getSelectedIndex() == -1) iþtenAtDüðmesi.setEnabled (false);
            else iþtenAtDüðmesi.setEnabled (true);
        } // dýþ-if kararý sonu...
    } // valueChanged(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Liste Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_30();
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
} // J5c_30 sýnýfý sonu...