// J8b_2.java: ThaiDigits (TaylandRakamlar�) �rne�i.

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
    AbstractDocument soyutD�k�man;
    protected JTextPane metinPanosu;
    static double say�;

    public J8b_2() {// Kurucu...
        super (new GridBagLayout());

        metinPanosu = new JTextPane();
        metinPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.45f) );
        metinPanosu.setForeground (Color.YELLOW);
        metinPanosu.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (metinPanosu);

        // Metin panolu kayd�rac� "extends JPanel"e ekleyelim...
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 1.0;
        add (kayd�ra�, s�n�rlay�c�lar);

        String ��kt�Dizgesi = new String();
        Locale[] taylandYerelDizisi = {
            new Locale ("th"),
            new Locale ("th", "TH"),
            new Locale("th", "TH", "TH")};
        for (Locale birYerel : taylandYerelDizisi) {
            NumberFormat say�Bi�imi = NumberFormat.getNumberInstance (birYerel);
            ��kt�Dizgesi = ��kt�Dizgesi + birYerel.toString() + ": ";
            ��kt�Dizgesi = ��kt�Dizgesi + say�Bi�imi.format (say�) + "\n";
        } // for d�ng�s� sonu...

        StyledDocument stilliD�k�man = metinPanosu.getStyledDocument();
        if (stilliD�k�man instanceof AbstractDocument)  soyutD�k�man = (AbstractDocument)stilliD�k�man;
        else {System.err.println ("Metin panosu d�k�man� bir soyut d�k�man de�ildir; g�sterilemez!");
            System.exit (-1);
        } // if-else karar� sonu...

        SimpleAttributeSet basit�zellikK�mesi = new SimpleAttributeSet();
        StyleConstants.setFontFamily (basit�zellikK�mesi, "SansSerif");
        StyleConstants.setFontSize (basit�zellikK�mesi, 24);

        try {soyutD�k�man.insertString (soyutD�k�man.getLength(), ��kt�Dizgesi, basit�zellikK�mesi);
        }catch (BadLocationException ist) {System.err.println ("Metin panoya konulam�yor!" + ist);}
    } // J8b_2() kurucusu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Tayland Rakamlar�");
        �er�eve.setDefaultCloseOperation (3); // JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J8b_2()); // Kurucuyu �a��r�r...
        �er�eve.setSize (475, 150);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {if (args.length == 1) say� = Double.valueOf (args[0]);
            else say� = 12345670.89;
        }catch (Exception ist) {say� = 12345670.89;}

        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J8b_2 s�n�f� sonu...