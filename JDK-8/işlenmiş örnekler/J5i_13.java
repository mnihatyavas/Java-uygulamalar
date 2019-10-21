// J5i_13.java: MultiListener (ÇokluDinleyici) örneði.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

public class J5i_13 extends JPanel implements ActionListener {
    JTextArea üstMetinAlaný;
    JTextArea altMetinAlaný;
    JButton düðme1, düðme2;
    final static String yeniSatýr = "\n";

    public J5i_13() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ýzgaraÇantaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        JLabel etiket = null;

        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        etiket = new JLabel ("Senin duyduklarýn:");
        ýzgaraÇantaSerilim.setConstraints (etiket, sýnýrlayýcýlar);
        add (etiket);

        sýnýrlayýcýlar.weighty = 1.0;
        üstMetinAlaný = new JTextArea();
        üstMetinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        üstMetinAlaný.setForeground (Color.YELLOW);
        üstMetinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane üstKaydýraç = new JScrollPane (üstMetinAlaný);
        Dimension tercihiEbat = new Dimension (200, 75);
        üstKaydýraç.setPreferredSize (tercihiEbat);
        ýzgaraÇantaSerilim.setConstraints (üstKaydýraç, sýnýrlayýcýlar);
        add (üstKaydýraç);

        sýnýrlayýcýlar.weightx = 0.0;
        sýnýrlayýcýlar.weighty = 0.0;
        etiket = new JLabel ("Dam üstündeki saksaðanýn duyduklarý:");
        ýzgaraÇantaSerilim.setConstraints (etiket, sýnýrlayýcýlar);
        add (etiket);

        sýnýrlayýcýlar.weighty = 1.0;
        altMetinAlaný = new JTextArea();
        altMetinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.45f) );
        altMetinAlaný.setForeground (Color.CYAN);
        altMetinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane altKaydýraç = new JScrollPane (altMetinAlaný);
        altKaydýraç.setPreferredSize (tercihiEbat);
        ýzgaraÇantaSerilim.setConstraints (altKaydýraç, sýnýrlayýcýlar);
        add (altKaydýraç);

        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 0.0;
        sýnýrlayýcýlar.gridwidth = 1;
        sýnýrlayýcýlar.insets = new Insets (1,1, 0,1);
        düðme1 = new JButton ("Lak lak lak");
        ýzgaraÇantaSerilim.setConstraints (düðme1, sýnýrlayýcýlar);
        add (düðme1);

        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        düðme2 = new JButton ("Deme yahu!");
        ýzgaraÇantaSerilim.setConstraints (düðme2, sýnýrlayýcýlar);
        add (düðme2);

        düðme1.addActionListener (this); // Her iki düðme de dinleyiciye duyarlý...
        düðme2.addActionListener (this);
        // düðme2, ayrýca alt metin alaný için özel dinleyiciye de duyarlý...
        düðme2.addActionListener (new DamÜstüSaksaðaný (altMetinAlaný));

        setPreferredSize (new Dimension (250, 300));
        setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createMatteBorder (2,2,5,5,Color.ORANGE),
                BorderFactory.createEmptyBorder (5,5,5,5)));
    } // J5i_13() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        üstMetinAlaný.append (olay.getActionCommand() + yeniSatýr);
        üstMetinAlaný.setCaretPosition (üstMetinAlaný.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Çoklu Dinleyici");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_13(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_13 sýnýfý sonu...

class DamÜstüSaksaðaný implements ActionListener {
    JTextArea metinAlaným;
    public DamÜstüSaksaðaný (JTextArea ma) {metinAlaným = ma;} // Kurucu...

    public void actionPerformed (ActionEvent olay) {
        metinAlaným.append (olay.getActionCommand() + J5i_13.yeniSatýr);
        metinAlaným.setCaretPosition (metinAlaným.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...
} // DamÜstüSaksaðaný sýnýfý sonu...