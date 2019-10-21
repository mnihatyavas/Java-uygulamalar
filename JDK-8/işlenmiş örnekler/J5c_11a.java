// J5c_11a.java: CustomIconDemo (Geleneksel�konG�sterimi) �rne�i.

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import javax.swing.SwingConstants;

/* Gereken dosyalar:
 * J5c_11ax/ArrowIcon.java
 * resim/orta.gif
 */
public class J5c_11a extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_11a() {// Kurucu...
        Icon solButon�konu = new J5c_11ax (SwingConstants.RIGHT); // Ok sa�a bakacak...
        Icon ortaButon�konu = resim�konunuYarat ("resim/orta.gif", "Orta buton");
        Icon sa�Buton�konu = new J5c_11ax (SwingConstants.LEFT); // Ok sola bakacak...

        b1 = new JButton ("Orta butonu etkisizle�tir", solButon�konu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING); // = LEFT/Sol
        b1.setMnemonic (KeyEvent.VK_Z); // Alt_Z ile t�klar...
        b1.setActionCommand ("etkisizle�tir");

        b2 = new JButton ("Orta buton", ortaButon�konu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_O); // Alt_O ile t�klar...

        b3 = new JButton ("Orta butonu etkinle�tir", sa�Buton�konu);
        // Belirtilmedi�inden varsay�l� metin konumu: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E); // Alt_E ile t�klar...
        b3.setActionCommand ("");
        b3.setEnabled (false);

        b1.addActionListener (this);
        b3.addActionListener (this);

        b1.setToolTipText ("Orta butonu ve kendini etkisizle�tirir.");
        b2.setToolTipText ("Duyars�zd�r.");
        b3.setToolTipText ("�lk ve orta butonu etkinle�tirir.");

        add (b1);
        add (b2);
        add (b3);
    } // J5c_11a() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getActionCommand().equals ("etkisizle�tir")) {
            b2.setEnabled (false);
            b1.setEnabled (false);
            b3.setEnabled (true);
        }else { 
            b2.setEnabled (true);
            b1.setEnabled (true);
            b3.setEnabled (false);
        } // if-else karar� sonu...
    } // actionPerformed(..) metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol, String izah) {
        java.net.URL resimYureli = null;
        try {resimYureli = new java.net.URL (yol);}catch (Exception ist){} // J5c_11a.class.getResource(path);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...
    
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Geleneksel �kon G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent yeni��erikPanosu = new J5c_11a();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_11a s�n�f� sonu...