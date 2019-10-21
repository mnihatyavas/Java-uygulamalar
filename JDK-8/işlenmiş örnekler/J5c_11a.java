// J5c_11a.java: CustomIconDemo (GelenekselÝkonGösterimi) örneði.

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
        Icon solButonÝkonu = new J5c_11ax (SwingConstants.RIGHT); // Ok saða bakacak...
        Icon ortaButonÝkonu = resimÝkonunuYarat ("resim/orta.gif", "Orta buton");
        Icon saðButonÝkonu = new J5c_11ax (SwingConstants.LEFT); // Ok sola bakacak...

        b1 = new JButton ("Orta butonu etkisizleþtir", solButonÝkonu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING); // = LEFT/Sol
        b1.setMnemonic (KeyEvent.VK_Z); // Alt_Z ile týklar...
        b1.setActionCommand ("etkisizleþtir");

        b2 = new JButton ("Orta buton", ortaButonÝkonu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic(KeyEvent.VK_O); // Alt_O ile týklar...

        b3 = new JButton ("Orta butonu etkinleþtir", saðButonÝkonu);
        // Belirtilmediðinden varsayýlý metin konumu: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E); // Alt_E ile týklar...
        b3.setActionCommand ("");
        b3.setEnabled (false);

        b1.addActionListener (this);
        b3.addActionListener (this);

        b1.setToolTipText ("Orta butonu ve kendini etkisizleþtirir.");
        b2.setToolTipText ("Duyarsýzdýr.");
        b3.setToolTipText ("Ýlk ve orta butonu etkinleþtirir.");

        add (b1);
        add (b2);
        add (b3);
    } // J5c_11a() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getActionCommand().equals ("etkisizleþtir")) {
            b2.setEnabled (false);
            b1.setEnabled (false);
            b3.setEnabled (true);
        }else { 
            b2.setEnabled (true);
            b1.setEnabled (true);
            b3.setEnabled (false);
        } // if-else kararý sonu...
    } // actionPerformed(..) metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol, String izah) {
        java.net.URL resimYureli = null;
        try {resimYureli = new java.net.URL (yol);}catch (Exception ist){} // J5c_11a.class.getResource(path);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...
    
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Geleneksel Ýkon Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent yeniÝçerikPanosu = new J5c_11a();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_11a sýnýfý sonu...