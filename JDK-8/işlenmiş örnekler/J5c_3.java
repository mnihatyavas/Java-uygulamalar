// J5c_3.java: ButtonHtmlDemo (ButonHtmlG�sterimi) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

// Gereken resim dosyalar�: right/middle/left.gif
public class J5c_3 extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_3() {
        ImageIcon solButon�konu = resim�konuYarat ("right.gif");
        ImageIcon ortaButon�konu = resim�konuYarat ("middle.gif");
        ImageIcon sa�Buton�konu = resim�konuYarat ("left.gif");

        b1 = new JButton ("<html><center><b><u>E</u>tkisizle�tir==></b><br><font color=#aabb22>Orta butonu</font></center></html>", solButon�konu);
        Font yaz�Fonu = b1.getFont().deriveFont (Font.BOLD);
        b1.setFont (yaz�Fonu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING);
        b1.setMnemonic (KeyEvent.VK_E);
        b1.setActionCommand ("etkisizle�tir");

        b2 = new JButton ("Orta buton", ortaButon�konu);
        b2.setFont (yaz�Fonu);
        b2.setForeground (new Color (0xaabb22));
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_O);

        b3 = new JButton ("<html><center><b>E<u>T</u>kinle�tir==></b><br>"
                + "<font color=#aabb22>Orta butonu</font>", 
                sa�Buton�konu);
        b3.setFont (yaz�Fonu);
        // Varsay�l� metin dikey-yatay konumlamay� kullanal�m: CENTER, TRAILING (RIGHT).
        b3.setMnemonic(KeyEvent.VK_T);
        b3.setActionCommand ("etkinle�tir");
        b3.setEnabled (false);

        // Buton 1 ve 3 olaylar�n� D�NLE...
        b1.addActionListener (this);
        b3.addActionListener (this);

        // Butonlara, fareyi �zerlerinde bekletti�imizde g�r�necek olan ipu�lar�n� tan�mlayal�m...
        b1.setToolTipText ("Orta butonu etkisizle�tirmek i�in bu butonu t�klay�n.");
        b2.setToolTipText ("Bu buton t�klanmaya duyars�zd�r.");
        b3.setToolTipText ("Orta butonu etkinle�tirmek i�in bu butonu t�klay�n.");

        // Varsay�l� FlowLayout yerle�imiyle komponentlerimizi i�eri�e ekleyelim...
        add (b1);
        add (b2);
        add (b3);
        setBackground (new Color (0xdd5500));
    } // J5c_3() kurucusu sonu...

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

    protected static ImageIcon resim�konuYarat (String adresliDosya) {
        java.net.URL resimYureli = J5c_3.class.getResource (adresliDosya);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + adresliDosya + "] dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Html Buton G�sterimi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // ��eri�i referanss�z yarat�p �er�eveye ekleyelim...
        �er�eve.add (new J5c_3());

        // Penceremizi paketli g�sterelim...
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_3 s�n�f� sonu...