// J5i_4.java: DocumentEventDemo (DökümanOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.text.Document;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class J5i_4 extends JPanel implements ActionListener {
    JTextField metinSatýrý;
    JTextArea metinAlaný;
    JTextArea gösteriAlaný;

    public J5i_4() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ýzgaraçantaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        JButton düðme = new JButton ("Temizle");
        düðme.addActionListener (this); // Dinleyiciye duyarlayalým...

        metinSatýrý = new JTextField (20);
        metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinSatýrý.addActionListener (new MetinDinleyicim()); // Kendi metin dinleyicime duyarlayalým...
        metinSatýrý.getDocument().addDocumentListener (new DökümanDinleyicim()); // Kendi döküman dinleyicime duyarlayalým...
        metinSatýrý.getDocument().putProperty ("ad", "Metin Satýrý");

        metinAlaný = new JTextArea ("Metin satýrý ve metin alaný arasýnda kes-kopya-yapýþtýr yapýlabilir.");
        metinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinAlaný.getDocument().addDocumentListener (new DökümanDinleyicim()); // Kendi döküman dinleyicime duyarlayalým...
        metinAlaný.getDocument().putProperty ("ad", "Metin Alaný");

        JScrollPane kaydýrma1 = new JScrollPane (metinAlaný);
        kaydýrma1.setPreferredSize (new Dimension (200, 75));

        gösteriAlaný = new JTextArea();
        gösteriAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        gösteriAlaný.setForeground (Color.WHITE);
        gösteriAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýrma2 = new JScrollPane (gösteriAlaný);
        kaydýrma2.setPreferredSize (new Dimension (200, 75));

        sýnýrlayýcýlar.gridx = 0;
        sýnýrlayýcýlar.gridy = 0;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.fill = GridBagConstraints.HORIZONTAL;
        ýzgaraçantaSerilim.setConstraints (metinSatýrý, sýnýrlayýcýlar);
        add (metinSatýrý);

        sýnýrlayýcýlar.gridx = 0;
        sýnýrlayýcýlar.gridy = 1;
        sýnýrlayýcýlar.weightx = 0.0;
        sýnýrlayýcýlar.gridheight = 2;
        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        ýzgaraçantaSerilim.setConstraints (kaydýrma1, sýnýrlayýcýlar);
        add (kaydýrma1);

        sýnýrlayýcýlar.gridx = 1;
        sýnýrlayýcýlar.gridy = 0;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 1.0;
        ýzgaraçantaSerilim.setConstraints (kaydýrma2, sýnýrlayýcýlar);
        add (kaydýrma2);

        sýnýrlayýcýlar.gridx = 1;
        sýnýrlayýcýlar.gridy = 2;
        sýnýrlayýcýlar.weightx = 0.0;
        sýnýrlayýcýlar.gridheight = 1;
        sýnýrlayýcýlar.weighty = 0.0;
        sýnýrlayýcýlar.fill = GridBagConstraints.HORIZONTAL;
        ýzgaraçantaSerilim.setConstraints (düðme, sýnýrlayýcýlar);
        add (düðme);

        setPreferredSize (new Dimension (450, 250));
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_4() kurucusu sonu...

    // Metin satýrý ve alaný deðiþikliklerini yönetir...
    class MetinDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            int seçiliÝlk = metinAlaný.getSelectionStart();
            int seçiliSon = metinAlaný.getSelectionEnd();

            metinAlaný.replaceRange (metinSatýrý.getText(), seçiliÝlk, seçiliSon);
            metinSatýrý.selectAll();
        } // actionPerformed(..) hazýr metodu sonu...
    } // MetinDinleyicim sýnýfý sonu...

    // Metin satýrý ve alaný deðiþikliklerini kayda geçer...
    class DökümanDinleyicim implements DocumentListener {
        final String yeniSatýr = "\n";

        public void insertUpdate (DocumentEvent olay) {kayýtýGüncelle (olay, "'na sokuldu");}
        public void removeUpdate (DocumentEvent olay) {kayýtýGüncelle (olay, "'ndan silindi");}
        public void changedUpdate (DocumentEvent olay) {} // Aldýrma...

        public void kayýtýGüncelle (DocumentEvent olay, String hareket) {
            Document döküman = (Document)olay.getDocument();
            int deðiþenUzunluk = olay.getLength();
            gösteriAlaný.append (deðiþenUzunluk + " karakter" + ((deðiþenUzunluk == 1) ? " " : "ler ")
                    + döküman.getProperty ("ad") + hareket + "."+ yeniSatýr
                    + "---mevcut Metin uzunluðu = " + döküman.getLength() + yeniSatýr);
            gösteriAlaný.setCaretPosition (gösteriAlaný.getDocument().getLength());
        } // kayýtýGüncelle(..) metodu sonu...
    } // DökümanDinleyicim sýnýfý sonu...

    // Temizle düðmesi yönetimi...
    public void actionPerformed (ActionEvent olay) {
        gösteriAlaný.setText("");
        metinSatýrý.requestFocus();
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Döküman Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_4(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_4 sýnýfý sonu...