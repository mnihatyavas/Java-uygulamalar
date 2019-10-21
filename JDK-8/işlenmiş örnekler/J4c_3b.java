// J4c_3b.java: PropertiesArgsDemoFrame (ÖzelliklerArgümanlarýGösterimÇerçevesi) örneði.

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

public class J4c_3b extends JFrame {
    public static void main (String[] a) {
        final String javaSürümü = System.getProperty ("java.runtime.version");
        final String  sunFýrlatýcý = System.getProperty ("sun.java.launcher");
        final String  jnlpÖzelliðim = String.valueOf (System.getProperty ("jnlp.myProperty"));
        final String  tümÖzellikler = String.valueOf (System.getProperties());

        try {SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratVeGösterGUI (javaSürümü, sunFýrlatýcý, jnlpÖzelliðim, tümÖzellikler);}
            }); // Sw.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("HATA: yaratVeGösterGUI tamamlanamadý!");
        } // try-catch bloðu sonu...
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI (String javaSürümü, String sunFýrlatýcý, String jnlpÖzelliðim, String tümÖzellikler) {
        J4c_3b çerçeve = new J4c_3b ();
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        String metin = "ÖZELLÝKLER: java.version = " + javaSürümü +
            ",  sun.java.launcher = " + sunFýrlatýcý +
            ",   jnlp.myProperty = " + jnlpÖzelliðim +
            ",  Tüm Çevresel Özellikler==> " + tümÖzellikler;

        JTextArea metinAlaný = new JTextArea (metin);
        metinAlaný.setLineWrap (true);
        metinAlaný.setWrapStyleWord (true);
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        kaydýrmaPanosu.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //kaydýrmaPanosu.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        kaydýrmaPanosu.setPreferredSize (new Dimension (450, 250));
        metinAlaný.setBackground (new Color (0,250,250));
        çerçeve.add (kaydýrmaPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratGUI(..) metodu sonu...
} // J4c_3b sýnýfý sonu...