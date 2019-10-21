// J5c_2.java: ButtonDemo (ButonG�sterimi) �rne�i.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/* Gereken resim dosyalar�:
 *   right.gif=Sa�.gif
 *   middle.gif=Orta.gif
 *   left.gif=Sol.gif
 */
public class J5c_2 extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_2() {// Kurucu...
        ImageIcon solButon�konu = resim�konuYarat ("right.gif");
        ImageIcon ortaButon�konu = resim�konuYarat ("middle.gif");
        ImageIcon sa�Buton�konu = resim�konuYarat("left.gif");

        b1 = new JButton ("Orta butonu etkisizle�tir", solButon�konu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING);
        b1.setMnemonic (KeyEvent.VK_D);
        b1.setActionCommand ("etkisizle�tir");

        b2 = new JButton ("Orta buton", ortaButon�konu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_M);

        b3 = new JButton ("Orta butonu etkinle�tir", sa�Buton�konu);
        // Varsay�l� metin konumlama ge�erli olacak: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E);
        b3.setActionCommand ("etkinle�tir");
        b3.setEnabled (false);

        // b1 ve b3 butonlar�n� Dinle...
        b1.addActionListener (this);
        b3.addActionListener (this);

        b1.setToolTipText ("Orta butonu etkisizle�tirmek i�in bu butonu t�klay�n.");
        b2.setToolTipText ("Bu orta buton t�klanmaya duyars�zd�r.");
        b3.setToolTipText ("Orta butonu etkinle�tirmek i�in bu butonu t�klay�n.");

        // Varsay�l� FlowLayout ile eldeki komponentleri i�erik panosuna ekleyelim...
        add (b1);
        add (b2);
        add (b3);
        setBackground (Color.BLUE);
    } // J5c_2() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("etkisizle�tir".equals (olay.getActionCommand())) {
            b2.setEnabled (false);
            b1.setEnabled (false);
            b3.setEnabled (true);
        }else {
            b2.setEnabled (true);
            b1.setEnabled (true);
            b3.setEnabled (false);
        } // else karar� sonu...
    } // actionPerformed(..) metodu sonu...

    // Yol ge�erliyse bir resim ikonu, de�ilse null d�nd�r�r...
    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_2.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] isimli dosya bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("ButonG�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_2 yeni��erikPanosu = new J5c_2();
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
} // J5c_2 s�n�f� sonu...