// J5c_23.java: HtmlDemo (HtmlG�sterimi) �rne�i.

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
    JTextArea htmlMetinAlan�;

    public J5c_23() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        String ilkHtmlMetni = "<html>\n" +
                "Renk ve yaz�-fonu testi:\n" +
                "<ul>\n" +
                "<li><font color=red>red</font>\n" +
                "<li><font color=blue>blue</font>\n" +
                "<li><font color=green>green</font>\n" +
                "<li><font size=-2>k���k</font>\n" +
                "<li><font size=+2>b�y�k</font>\n" +
                "<li><i>yat�k</i>\n" +
                "<li><b>koyu</b>\n" +
                "</ul>\n";

        htmlMetinAlan� = new JTextArea (10, 20);
        htmlMetinAlan�.setText (ilkHtmlMetni);
        htmlMetinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane kayd�rmaPanosu = new JScrollPane (htmlMetinAlan�);

        JButton d��me = new JButton ("Etiketi De�i�tir");
        d��me.setMnemonic (KeyEvent.VK_D);
        d��me.setAlignmentX (Component.CENTER_ALIGNMENT);
        d��me.addActionListener (this);

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
            BorderFactory.createTitledBorder ("HTML metnini d�zenle, sonra d��meyi t�kla:"),
            BorderFactory.createEmptyBorder (10,10,10,10)));
        solPanel.add (kayd�rmaPanosu);
        solPanel.add (Box.createRigidArea (new Dimension (0,10)));
        solPanel.add (d��me);
        solPanel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        JPanel sa�Panel = new JPanel();
        sa�Panel.setLayout (new BoxLayout (sa�Panel, BoxLayout.PAGE_AXIS));
        sa�Panel.setBorder (BorderFactory.createCompoundBorder (
            BorderFactory.createTitledBorder ("HTML etkili Etiket:"),
            BorderFactory.createEmptyBorder (10,10,10,10)));
        sa�Panel.add (etiket);
        sa�Panel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        add (solPanel);
        add (Box.createRigidArea (new Dimension (10,0)));
        add (sa�Panel);
        setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
    } // J5c_23() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {etiket.setText (htmlMetinAlan�.getText());}

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Html G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_23());
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {// Metal'in koyu yaz� fonlar� kullan�m�n� kapatal�m...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_23 s�n�f� sonu...