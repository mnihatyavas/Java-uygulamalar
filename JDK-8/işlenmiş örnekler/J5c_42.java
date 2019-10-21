// J5c_42.java: RadioButtonDemo (RadyoDüðmesiGösterimi) örneði.

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

/* Gereken resim dosyalarý:
 *   resim/Kuþ.gif
 *   resim/Kedi.gif
 *   resim/Köpek.gif
 *   resim/Tavþan.gif
 *   resim/Domuz.gif
 */
public class J5c_42 extends JPanel implements ActionListener {
    static String kuþDizgesi = "Kuþ";
    static String kediDizgesi = "Kedi";
    static String köpekDizgesi = "Köpek";
    static String tavþanDizgesi = "Tavþan";
    static String domuzDizgesi = "Domuz";
    JLabel resimEtiketi;

    public J5c_42() {// Kurucu...
        super (new BorderLayout());
        Color renk = new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

        // 5 adet radyo düðmesi yaratalým...
        JRadioButton kuþDüðmesi = new JRadioButton ();
        kuþDüðmesi.setMnemonic (KeyEvent.VK_K);
        kuþDüðmesi.setActionCommand (kuþDizgesi);
        kuþDüðmesi.setSelected (true);
        kuþDüðmesi.setBackground (renk);

        JRadioButton kediDüðmesi = new JRadioButton ();
        kediDüðmesi.setMnemonic (KeyEvent.VK_E);
        kediDüðmesi.setActionCommand (kediDizgesi);
        kediDüðmesi.setBackground (renk);

        JRadioButton köpekDüðmesi = new JRadioButton ();
        köpekDüðmesi.setMnemonic (KeyEvent.VK_P);
        köpekDüðmesi.setActionCommand (köpekDizgesi);
        köpekDüðmesi.setBackground (renk);

        JRadioButton tavþanDüðmesi = new JRadioButton ();
        tavþanDüðmesi.setMnemonic (KeyEvent.VK_T);
        tavþanDüðmesi.setActionCommand (tavþanDizgesi);
        tavþanDüðmesi.setBackground (renk);

        JRadioButton domuzDüðmesi = new JRadioButton ();
        domuzDüðmesi.setMnemonic (KeyEvent.VK_D);
        domuzDüðmesi.setActionCommand (domuzDizgesi);
        domuzDüðmesi.setBackground (renk);

        // Radyo düðmelerini ayný grupta toplayalým...
        ButtonGroup grup = new ButtonGroup();
        grup.add (kuþDüðmesi);
        grup.add (kediDüðmesi);
        grup.add (köpekDüðmesi);
        grup.add (tavþanDüðmesi);
        grup.add (domuzDüðmesi);

        // Radyo düðmelerini dinleyiciye duyarlayalým...
        kuþDüðmesi.addActionListener (this);
        kediDüðmesi.addActionListener (this);
        köpekDüðmesi.addActionListener (this);
        tavþanDüðmesi.addActionListener (this);
        domuzDüðmesi.addActionListener (this);

        // Resim etiketini kuralým...
        resimEtiketi = new JLabel (resimÝkonunuYarat ("resim/" + kuþDizgesi + ".gif"));

        // Tüm resimleri de kapsayacak ebat...
        resimEtiketi.setPreferredSize (new Dimension (125, 129));

        // Radyo düðmelerini bir panelle ayný sütunda alt-alta sýralayalým...
        JPanel radyoPaneli = new JPanel (new GridLayout (0, 1));
        radyoPaneli.add (kuþDüðmesi);
        radyoPaneli.add (kediDüðmesi);
        radyoPaneli.add (köpekDüðmesi);
        radyoPaneli.add (tavþanDüðmesi);
        radyoPaneli.add (domuzDüðmesi);
        radyoPaneli.setBackground (renk);

        add (radyoPaneli, BorderLayout.LINE_START); // Sola bitiþik...
        add (resimEtiketi, BorderLayout.CENTER);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20)); // Kenar marjlarý...
        setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
    } // J5c_42() kurucusu sonu...

    // Radyo düðmelerini dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        resimEtiketi.setIcon (resimÝkonunuYarat ("resim/" + olay.getActionCommand() + ".gif"));
    } // actionPerformed(..) hazýr metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_42.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Radyo Düðmesi Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_42();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_42 sýnýfý sonu...