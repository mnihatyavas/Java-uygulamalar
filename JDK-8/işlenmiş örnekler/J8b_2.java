// J8b_2.java: ThaiDigits (TaylandRakamlarý) örneði.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.text.NumberFormat;

import java.util.Locale;

public class J8b_2 extends JPanel {
    AbstractDocument soyutDöküman;
    protected JTextPane metinPanosu;
    static double sayý;

    public J8b_2() {// Kurucu...
        super (new GridBagLayout());

        metinPanosu = new JTextPane();
        metinPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.45f) );
        metinPanosu.setForeground (Color.YELLOW);
        metinPanosu.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (metinPanosu);

        // Metin panolu kaydýracý "extends JPanel"e ekleyelim...
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 1.0;
        add (kaydýraç, sýnýrlayýcýlar);

        String çýktýDizgesi = new String();
        Locale[] taylandYerelDizisi = {
            new Locale ("th"),
            new Locale ("th", "TH"),
            new Locale("th", "TH", "TH")};
        for (Locale birYerel : taylandYerelDizisi) {
            NumberFormat sayýBiçimi = NumberFormat.getNumberInstance (birYerel);
            çýktýDizgesi = çýktýDizgesi + birYerel.toString() + ": ";
            çýktýDizgesi = çýktýDizgesi + sayýBiçimi.format (sayý) + "\n";
        } // for döngüsü sonu...

        StyledDocument stilliDöküman = metinPanosu.getStyledDocument();
        if (stilliDöküman instanceof AbstractDocument)  soyutDöküman = (AbstractDocument)stilliDöküman;
        else {System.err.println ("Metin panosu dökümaný bir soyut döküman deðildir; gösterilemez!");
            System.exit (-1);
        } // if-else kararý sonu...

        SimpleAttributeSet basitÖzellikKümesi = new SimpleAttributeSet();
        StyleConstants.setFontFamily (basitÖzellikKümesi, "SansSerif");
        StyleConstants.setFontSize (basitÖzellikKümesi, 24);

        try {soyutDöküman.insertString (soyutDöküman.getLength(), çýktýDizgesi, basitÖzellikKümesi);
        }catch (BadLocationException ist) {System.err.println ("Metin panoya konulamýyor!" + ist);}
    } // J8b_2() kurucusu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Tayland Rakamlarý");
        çerçeve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J8b_2()); // Kurucuyu çaðýrýr...
        çerçeve.setSize (475, 150);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {if (args.length == 1) sayý = Double.valueOf (args[0]);
            else sayý = 12345670.89;
        }catch (Exception ist) {sayý = 12345670.89;}

        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J8b_2 sýnýfý sonu...