// J5c_11b.java: ButtonDemo (DüðmeGösterimi) örneði.

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/* Gereken resim dosyalarý:
 *  resim/sað.gif
 *  resim/orta.gif
 *  resim/sol.gif
 */
public class J5c_11b extends JPanel implements ActionListener {
    protected JButton b1, b2, b3;

    public J5c_11b() {// Kurucu...
        ImageIcon solDüðmeÝkonu = resimÝkonuYarat ("resim/sað.gif");
        ImageIcon ortaDüðmeÝkonu = resimÝkonuYarat ("resim/orta.gif");
        ImageIcon saðDüðmeÝkonu = resimÝkonuYarat ("resim/sol.gif");

        b1 = new JButton ("Orta düðmeyi etkisizleþtir", solDüðmeÝkonu);
        b1.setVerticalTextPosition (AbstractButton.CENTER);
        b1.setHorizontalTextPosition (AbstractButton.LEADING); //=LEFT
        b1.setMnemonic (KeyEvent.VK_T);
        b1.setActionCommand ("etkisizleþtir");

        b2 = new JButton ("Orta düðme", ortaDüðmeÝkonu);
        b2.setVerticalTextPosition (AbstractButton.BOTTOM);
        b2.setHorizontalTextPosition (AbstractButton.CENTER);
        b2.setMnemonic (KeyEvent.VK_O);

        b3 = new JButton ("Orta düðmeti etkinleþtir", saðDüðmeÝkonu);
        // Belirtilmeyince varsayýlan deðerleri alýr: CENTER, TRAILING (RIGHT).
        b3.setMnemonic (KeyEvent.VK_E);
        b3.setActionCommand ("etkinleþtir");
        b3.setEnabled (false);

        // Düðme 1 ve 3'ü dinlemeye alalým...
        b1.addActionListener (this);
        b3.addActionListener (this);

        // düðmelerin herbirinin üstünde bekleyince þu ipuçlarý belirsin:
        b1.setToolTipText ("Bu düðmeyi týklarsanýz bu ve orta düðme etkisizleþir.");
        b2.setToolTipText ("Bu düðmeyi týklamanýz hiçbirþeyi deðiþtirmez.");
        b3.setToolTipText ("Bu düðmeyi týklarsanýz ilk ve orta düðme tekrar etkinleþir.");

        // Varsayýlý FlowLayout serimiyle düðmeleri içerik panosuna ekleyelim.
        add (b1);
        add (b2);
        add (b3);
    } // J5c_11b() kurucusu sonu...

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

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_11b.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Düðme Gösterimi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_11b yeniÝçerikPanosu = new J5c_11b(); // Kurucuyu çaðýrýr...
        // "extends JPanel" gereði, bu sýnýf bir içerik panosudur...
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
} // J5c_11b sýnýfý sonu...