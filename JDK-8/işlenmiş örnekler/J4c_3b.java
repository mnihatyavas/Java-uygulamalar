// J4c_3b.java: PropertiesArgsDemoFrame (�zelliklerArg�manlar�G�sterim�er�evesi) �rne�i.

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

public class J4c_3b extends JFrame {
    public static void main (String[] a) {
        final String javaS�r�m� = System.getProperty ("java.runtime.version");
        final String  sunF�rlat�c� = System.getProperty ("sun.java.launcher");
        final String  jnlp�zelli�im = String.valueOf (System.getProperty ("jnlp.myProperty"));
        final String  t�m�zellikler = String.valueOf (System.getProperties());

        try {SwingUtilities.invokeAndWait (new Runnable() {
                public void run() {yaratVeG�sterGUI (javaS�r�m�, sunF�rlat�c�, jnlp�zelli�im, t�m�zellikler);}
            }); // Sw.. ifadesi sonu...
        }catch (Exception ist) {System.err.println ("HATA: yaratVeG�sterGUI tamamlanamad�!");
        } // try-catch blo�u sonu...
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI (String javaS�r�m�, String sunF�rlat�c�, String jnlp�zelli�im, String t�m�zellikler) {
        J4c_3b �er�eve = new J4c_3b ();
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        String metin = "�ZELL�KLER: java.version = " + javaS�r�m� +
            ",  sun.java.launcher = " + sunF�rlat�c� +
            ",   jnlp.myProperty = " + jnlp�zelli�im +
            ",  T�m �evresel �zellikler==> " + t�m�zellikler;

        JTextArea metinAlan� = new JTextArea (metin);
        metinAlan�.setLineWrap (true);
        metinAlan�.setWrapStyleWord (true);
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        kayd�rmaPanosu.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //kayd�rmaPanosu.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        kayd�rmaPanosu.setPreferredSize (new Dimension (450, 250));
        metinAlan�.setBackground (new Color (0,250,250));
        �er�eve.add (kayd�rmaPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratGUI(..) metodu sonu...
} // J4c_3b s�n�f� sonu...