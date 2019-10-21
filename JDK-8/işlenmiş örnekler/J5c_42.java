// J5c_42.java: RadioButtonDemo (RadyoD��mesiG�sterimi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/* Gereken resim dosyalar�:
 *   resim/Ku�.gif
 *   resim/Kedi.gif
 *   resim/K�pek.gif
 *   resim/Tav�an.gif
 *   resim/Domuz.gif
 */
public class J5c_42 extends JPanel implements ActionListener {
    static String ku�Dizgesi = "Ku�";
    static String kediDizgesi = "Kedi";
    static String k�pekDizgesi = "K�pek";
    static String tav�anDizgesi = "Tav�an";
    static String domuzDizgesi = "Domuz";
    JLabel resimEtiketi;

    public J5c_42() {// Kurucu...
        super (new BorderLayout());
        Color renk = new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

        // 5 adet radyo d��mesi yaratal�m...
        JRadioButton ku�D��mesi = new JRadioButton ();
        ku�D��mesi.setMnemonic (KeyEvent.VK_K);
        ku�D��mesi.setActionCommand (ku�Dizgesi);
        ku�D��mesi.setSelected (true);
        ku�D��mesi.setBackground (renk);

        JRadioButton kediD��mesi = new JRadioButton ();
        kediD��mesi.setMnemonic (KeyEvent.VK_E);
        kediD��mesi.setActionCommand (kediDizgesi);
        kediD��mesi.setBackground (renk);

        JRadioButton k�pekD��mesi = new JRadioButton ();
        k�pekD��mesi.setMnemonic (KeyEvent.VK_P);
        k�pekD��mesi.setActionCommand (k�pekDizgesi);
        k�pekD��mesi.setBackground (renk);

        JRadioButton tav�anD��mesi = new JRadioButton ();
        tav�anD��mesi.setMnemonic (KeyEvent.VK_T);
        tav�anD��mesi.setActionCommand (tav�anDizgesi);
        tav�anD��mesi.setBackground (renk);

        JRadioButton domuzD��mesi = new JRadioButton ();
        domuzD��mesi.setMnemonic (KeyEvent.VK_D);
        domuzD��mesi.setActionCommand (domuzDizgesi);
        domuzD��mesi.setBackground (renk);

        // Radyo d��melerini ayn� grupta toplayal�m...
        ButtonGroup grup = new ButtonGroup();
        grup.add (ku�D��mesi);
        grup.add (kediD��mesi);
        grup.add (k�pekD��mesi);
        grup.add (tav�anD��mesi);
        grup.add (domuzD��mesi);

        // Radyo d��melerini dinleyiciye duyarlayal�m...
        ku�D��mesi.addActionListener (this);
        kediD��mesi.addActionListener (this);
        k�pekD��mesi.addActionListener (this);
        tav�anD��mesi.addActionListener (this);
        domuzD��mesi.addActionListener (this);

        // Resim etiketini kural�m...
        resimEtiketi = new JLabel (resim�konunuYarat ("resim/" + ku�Dizgesi + ".gif"));

        // T�m resimleri de kapsayacak ebat...
        resimEtiketi.setPreferredSize (new Dimension (125, 129));

        // Radyo d��melerini bir panelle ayn� s�tunda alt-alta s�ralayal�m...
        JPanel radyoPaneli = new JPanel (new GridLayout (0, 1));
        radyoPaneli.add (ku�D��mesi);
        radyoPaneli.add (kediD��mesi);
        radyoPaneli.add (k�pekD��mesi);
        radyoPaneli.add (tav�anD��mesi);
        radyoPaneli.add (domuzD��mesi);
        radyoPaneli.setBackground (renk);

        add (radyoPaneli, BorderLayout.LINE_START); // Sola biti�ik...
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20)); // Kenar marjlar�...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_42() kurucusu sonu...

    // Radyo d��melerini dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        resimEtiketi.setIcon (resim�konunuYarat ("resim/" + olay.getActionCommand() + ".gif"));
    } // actionPerformed(..) haz�r metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_42.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Radyo D��mesi G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_42();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_42 s�n�f� sonu...