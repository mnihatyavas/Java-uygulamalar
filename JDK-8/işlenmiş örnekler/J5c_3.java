// J5c_3.java: ButtonHtmlDemo (ButonHtmlGösterimi) örneði.

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

// Gereken resim dosyalarý: right/middle/left.gif
public class J5c_3 extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_3() {
        ImageIcon solButonÝkonu = resimÝkonuYarat ("right.gif");
        ImageIcon ortaButonÝkonu = resimÝkonuYarat ("middle.gif");
        ImageIcon saðButonÝkonu = resimÝkonuYarat ("left.gif");

        b1 = new JButton ("<html><center><b><u>E</u>tkisizleþtir==></b><br><font color=#aabb22>Orta butonu</font></center></html>", solButonÝkonu);
        Font yazýFonu = b1.getFont().deriveFont (Font.BOLD);
        b1.setFont (yazýFonu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING);
        b1.setMnemonic (KeyEvent.VK_E);
        b1.setActionCommand ("etkisizleþtir");

        b2 = new JButton ("Orta buton", ortaButonÝkonu);
        b2.setFont (yazýFonu);
        b2.setForeground (new Color (0xaabb22));
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_O);

        b3 = new JButton ("<html><center><b>E<u>T</u>kinleþtir==></b><br>"
                + "<font color=#aabb22>Orta butonu</font>", 
                saðButonÝkonu);
        b3.setFont (yazýFonu);
        // Varsayýlý metin dikey-yatay konumlamayý kullanalým: CENTER, TRAILING (RIGHT).
        b3.setMnemonic(KeyEvent.VK_T);
        b3.setActionCommand ("etkinleþtir");
        b3.setEnabled (false);

        // Buton 1 ve 3 olaylarýný DÝNLE...
        b1.addActionListener (this);
        b3.addActionListener (this);

        // Butonlara, fareyi üzerlerinde beklettiðimizde görünecek olan ipuçlarýný tanýmlayalým...
        b1.setToolTipText ("Orta butonu etkisizleþtirmek için bu butonu týklayýn.");
        b2.setToolTipText ("Bu buton týklanmaya duyarsýzdýr.");
        b3.setToolTipText ("Orta butonu etkinleþtirmek için bu butonu týklayýn.");

        // Varsayýlý FlowLayout yerleþimiyle komponentlerimizi içeriðe ekleyelim...
        add (b1);
        add (b2);
        add (b3);
        setBackground (new Color (0xdd5500));
    } // J5c_3() kurucusu sonu...

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

    protected static ImageIcon resimÝkonuYarat (String adresliDosya) {
        java.net.URL resimYureli = J5c_3.class.getResource (adresliDosya);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + adresliDosya + "] dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Html Buton Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Ýçeriði referanssýz yaratýp çerçeveye ekleyelim...
        çerçeve.add (new J5c_3());

        // Penceremizi paketli gösterelim...
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_3 sýnýfý sonu...