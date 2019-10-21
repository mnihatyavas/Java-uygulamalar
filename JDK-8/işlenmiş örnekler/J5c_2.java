// J5c_2.java: ButtonDemo (ButonGösterimi) örneði.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/* Gereken resim dosyalarý:
 *   right.gif=Sað.gif
 *   middle.gif=Orta.gif
 *   left.gif=Sol.gif
 */
public class J5c_2 extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_2() {// Kurucu...
        ImageIcon solButonÝkonu = resimÝkonuYarat ("right.gif");
        ImageIcon ortaButonÝkonu = resimÝkonuYarat ("middle.gif");
        ImageIcon saðButonÝkonu = resimÝkonuYarat("left.gif");

        b1 = new JButton ("Orta butonu etkisizleþtir", solButonÝkonu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING);
        b1.setMnemonic (KeyEvent.VK_D);
        b1.setActionCommand ("etkisizleþtir");

        b2 = new JButton ("Orta buton", ortaButonÝkonu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_M);

        b3 = new JButton ("Orta butonu etkinleþtir", saðButonÝkonu);
        // Varsayýlý metin konumlama geçerli olacak: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E);
        b3.setActionCommand ("etkinleþtir");
        b3.setEnabled (false);

        // b1 ve b3 butonlarýný Dinle...
        b1.addActionListener (this);
        b3.addActionListener (this);

        b1.setToolTipText ("Orta butonu etkisizleþtirmek için bu butonu týklayýn.");
        b2.setToolTipText ("Bu orta buton týklanmaya duyarsýzdýr.");
        b3.setToolTipText ("Orta butonu etkinleþtirmek için bu butonu týklayýn.");

        // Varsayýlý FlowLayout ile eldeki komponentleri içerik panosuna ekleyelim...
        add (b1);
        add (b2);
        add (b3);
        setBackground (Color.BLUE);
    } // J5c_2() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("etkisizleþtir".equals (olay.getActionCommand())) {
            b2.setEnabled (false);
            b1.setEnabled (false);
            b3.setEnabled (true);
        }else {
            b2.setEnabled (true);
            b1.setEnabled (true);
            b3.setEnabled (false);
        } // else kararý sonu...
    } // actionPerformed(..) metodu sonu...

    // Yol geçerliyse bir resim ikonu, deðilse null döndürür...
    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_2.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] isimli dosya bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("ButonGösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_2 yeniÝçerikPanosu = new J5c_2();
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
} // J5c_2 sýnýfý sonu...