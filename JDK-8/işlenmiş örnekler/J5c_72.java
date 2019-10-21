// J5c_72.java: TextDemo (MetinGösterisi) örneði.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class J5c_72 extends JPanel implements ActionListener {
    protected JTextField metinSatýrý;
    protected JTextArea metinAlaný;
    private final static String yeniSatýr = "\n";

    public J5c_72() {// Kurucu...
        super (new GridBagLayout());

        metinSatýrý = new JTextField (20);
        metinSatýrý.addActionListener (this); // Enter'layýnca dinleyiciye duyarlayalým...
        metinSatýrý.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu zemin renkleri...
        metinSatýrý.setForeground (Color.WHITE);


        metinAlaný = new JTextArea (5, 20);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        metinAlaný.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk zemin renkleri...
        metinAlaný.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu yazý renkleri...

        // Her iki komponenti de sýnýrlayýcýlýlarýyla birlikte içerik paneline ekleyelim...
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;

        sýnýrlayýcýlar.fill = GridBagConstraints.HORIZONTAL;
        add (metinSatýrý, sýnýrlayýcýlar);

        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 1.0;
        add (kaydýrmaPanosu, sýnýrlayýcýlar);
    } // J5c_72() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String metin = metinSatýrý.getText();
        metinAlaný.append (metin + yeniSatýr);
        metinSatýrý.selectAll();
        // Metin alanýna yeni eklenen daima görünür kýlýnsýn...
        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Metin Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_72()); // Referanssýz nesne yaratýmý kurucuyu çaðýrýr...
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_72 sýnýfý sonu...