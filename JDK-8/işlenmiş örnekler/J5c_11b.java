// J5c_11b.java: ButtonDemo (D��meG�sterimi) �rne�i.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* Gereken resim dosyalar�:
 *  resim/sa�.gif
 *  resim/orta.gif
 *  resim/sol.gif
 */
public class J5c_11b extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_11b() {// Kurucu...
        ImageIcon solD��me�konu = resim�konuYarat ("resim/sa�.gif");
        ImageIcon ortaD��me�konu = resim�konuYarat ("resim/orta.gif");
        ImageIcon sa�D��me�konu = resim�konuYarat ("resim/sol.gif");

        b1 = new JButton ("Orta d��meyi etkisizle�tir", solD��me�konu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING); //=LEFT
        b1.setMnemonic (KeyEvent.VK_T);
        b1.setActionCommand ("etkisizle�tir");

        b2 = new JButton ("Orta d��me", ortaD��me�konu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_O);

        b3 = new JButton ("Orta d��meti etkinle�tir", sa�D��me�konu);
        // Belirtilmeyince varsay�lan de�erleri al�r: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E);
        b3.setActionCommand ("etkinle�tir");
        b3.setEnabled (false);

        // D��me 1 ve 3'� dinlemeye alal�m...
        b1.addActionListener (this);
        b3.addActionListener (this);

        // d��melerin herbirinin �st�nde bekleyince �u ipu�lar� belirsin:
        b1.setToolTipText ("Bu d��meyi t�klarsan�z bu ve orta d��me etkisizle�ir.");
        b2.setToolTipText ("Bu d��meyi t�klaman�z hi�bir�eyi de�i�tirmez.");
        b3.setToolTipText ("Bu d��meyi t�klarsan�z ilk ve orta d��me tekrar etkinle�ir.");

        // Varsay�l� FlowLayout serimiyle d��meleri i�erik panosuna ekleyelim.
        add (b1);
        add (b2);
        add (b3);
    } // J5c_11b() kurucusu sonu...

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

    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_11b.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("D��me G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_11b yeni��erikPanosu = new J5c_11b(); // Kurucuyu �a��r�r...
        // "extends JPanel" gere�i, bu s�n�f bir i�erik panosudur...
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
} // J5c_11b s�n�f� sonu...