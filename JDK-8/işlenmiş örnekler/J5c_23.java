// J5c_23.java: HtmlDemo (HtmlGösterimi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class J5c_23 extends JPanel implements ActionListener {
    JLabel etiket;
    JTextArea htmlMetinAlaný;

    public J5c_23() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        String ilkHtmlMetni = "<html>\n" +
                "Renk ve yazý-fonu testi:\n" +
                "<ul>\n" +
                "<li><font color=red>red</font>\n" +
                "<li><font color=blue>blue</font>\n" +
                "<li><font color=green>green</font>\n" +
                "<li><font size=-2>küçük</font>\n" +
                "<li><font size=+2>büyük</font>\n" +
                "<li><i>yatýk</i>\n" +
                "<li><b>koyu</b>\n" +
                "</ul>\n";

        htmlMetinAlaný = new JTextArea (10, 20);
        htmlMetinAlaný.setText (ilkHtmlMetni);
        htmlMetinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane kaydýrmaPanosu = new JScrollPane (htmlMetinAlaný);

        JButton düðme = new JButton ("Etiketi Deðiþtir");
        düðme.setMnemonic (KeyEvent.VK_D);
        düðme.setAlignmentX (Component.CENTER_ALIGNMENT);
        düðme.addActionListener (this);

        etiket = new JLabel (ilkHtmlMetni) {
            public Dimension getPreferredSize() {return new Dimension (200, 200);}
            public Dimension getMinimumSize() {return new Dimension (200, 200);}
            public Dimension getMaximumSize() {return new Dimension (200, 200);}
        }; // eti.. ifadesi sonu...
        etiket.setVerticalAlignment (SwingConstants.CENTER);
        etiket.setHorizontalAlignment (SwingConstants.CENTER);

        JPanel solPanel = new JPanel();
        solPanel.setLayout (new BoxLayout (solPanel, BoxLayout.PAGE_AXIS));
        solPanel.setBorder (BorderFactory.createCompoundBorder (
            BorderFactory.createTitledBorder ("HTML metnini düzenle, sonra düðmeyi týkla:"),
            BorderFactory.createEmptyBorder (10,10,10,10)));
        solPanel.add (kaydýrmaPanosu);
        solPanel.add (Box.createRigidArea (new Dimension (0,10)));
        solPanel.add (düðme);
        solPanel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel saðPanel = new JPanel();
        saðPanel.setLayout (new BoxLayout (saðPanel, BoxLayout.PAGE_AXIS));
        saðPanel.setBorder (BorderFactory.createCompoundBorder (
            BorderFactory.createTitledBorder ("HTML etkili Etiket:"),
            BorderFactory.createEmptyBorder (10,10,10,10)));
        saðPanel.add (etiket);
        saðPanel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        add (solPanel);
        add (Box.createRigidArea (new Dimension (10,0)));
        add (saðPanel);
        setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
    } // J5c_23() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {etiket.setText (htmlMetinAlaný.getText());}

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Html Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_23());
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {// Metal'in koyu yazý fonlarý kullanýmýný kapatalým...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_23 sýnýfý sonu...