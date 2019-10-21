// J5c_32.java: MenuLookDemo (MenüyeBakýþGösterimi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

// Gereken resim dosyasý: resim/orta.gif
public class J5c_32 {
    JTextArea metinAlaný;
    JScrollPane kaydýrmaPanosu;

    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu;
        JMenu menü, altMenü;
        JMenuItem menüBirimi;
        JRadioButtonMenuItem rbmBirimi;
        JCheckBoxMenuItem cbmBirimi;

        // Menü çubuðunu yaratalým...
        menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Menü çubuðundaki ilk menümüzü yaratalým...
        menü = new JMenu ("Ýlk Menü");
        menü.setMnemonic (KeyEvent.VK_M);
        menü.getAccessibleContext().setAccessibleDescription ("Bu programda menü birimleri olan tek menü");
        menüÇubuðu.add (menü);

        // 3 adet menü birimi...
        menüBirimi = new JMenuItem ("Sadece metinli menü birimi", KeyEvent.VK_S);
        //menüBirimi.setMnemonic(KeyEvent.VK_S); // Alt_S böyle de atanabilir...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1,  ActionEvent.ALT_MASK));
        menüBirimi.getAccessibleContext().setAccessibleDescription ("Bu menü birimi gerçekte herhangi bir iþlem yapmaz");
        menü.add (menüBirimi);

        ImageIcon ikon = resimÝkonunuYarat ("resim/orta.gif");
        menüBirimi = new JMenuItem ("Hem metin hem de ikonlu menü birimi", ikon);
        menüBirimi.setMnemonic (KeyEvent.VK_H);
        menü.add (menüBirimi);

        menüBirimi = new JMenuItem (ikon);
        menüBirimi = new JMenuItem ("Yalnýzca ikonlu menü birimi", ikon);
        menüBirimi.setMnemonic (KeyEvent.VK_Y);
        menü.add (menüBirimi);

        // 2 adet radyo düðmesi gruplu menü birimi...
        menü.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbmBirimi = new JRadioButtonMenuItem ("Birinci radyo düðmesi menü birimi");
        rbmBirimi.setSelected (true);
        rbmBirimi.setMnemonic (KeyEvent.VK_B);
        grup.add (rbmBirimi);
        menü.add (rbmBirimi);

        rbmBirimi = new JRadioButtonMenuItem ("Diðer radyo düðmesi menü birimi");
        rbmBirimi.setMnemonic (KeyEvent.VK_D);
        grup.add (rbmBirimi);
        menü.add (rbmBirimi);

        // 2 adet çentik kutusu gruplu menü birimleri...
        menü.addSeparator();
        cbmBirimi = new JCheckBoxMenuItem ("Ýlk tercih kutusu menü birimi");
        cbmBirimi.setMnemonic (KeyEvent.VK_T);
        menü.add (cbmBirimi);

        cbmBirimi = new JCheckBoxMenuItem ("Diðer tercih kutusu menü birimi");
        cbmBirimi.setMnemonic(KeyEvent.VK_K);
        menü.add (cbmBirimi);

        // 1 adet alt menü...
        menü.addSeparator();
        altMenü = new JMenu ("Bir alt menü");
        altMenü.setMnemonic(KeyEvent.VK_A);

        menüBirimi = new JMenuItem ("Alt menüdeki ilk menü birimi");
        menüBirimi.setMnemonic (KeyEvent.VK_L);
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK));
        altMenü.add (menüBirimi);

        menüBirimi = new JMenuItem ("Alt menüdeki ikinci menü birimi");
        menüBirimi.setMnemonic (KeyEvent.VK_M);
        altMenü.add (menüBirimi);
        menü.add (altMenü);

        // Menü çubuðundaki ikinci menümüzü yaratalým...
        menü = new JMenu ("Diðer Menü");
        menü.setMnemonic (KeyEvent.VK_D);
        menü.getAccessibleContext().setAccessibleDescription ("Bu menünün henüz hiçbir menü birimi yok");
        menüÇubuðu.add (menü);

        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    public Container içerikPanosunuYarat() {
        // Bir içerik paneli yaratalým...
        JPanel içerikPaneli = new JPanel (new BorderLayout());
        içerikPaneli.setOpaque (true);

        // Kaydýrmalý bir metin alaný yaratalým...
        metinAlaný = new JTextArea (5, 30);
        metinAlaný.setEditable (true); // Ýstediðini (beydude) yazabilirsin...
        metinAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        kaydýrmaPanosu = new JScrollPane (metinAlaný);

        // Kaydýrma panolu metin alanýný içerik paneline ekleyelim...
        içerikPaneli.add (kaydýrmaPanosu, BorderLayout.CENTER);

        return içerikPaneli;
    } // içerikPanosunuYarat() metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_32.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Menüye Bakýþ Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_32 gösteri = new J5c_32();
        çerçeve.setJMenuBar (gösteri.menüÇubuðunuYarat());
        çerçeve.setContentPane (gösteri.içerikPanosunuYarat());
        çerçeve.setBounds (500,100, 450,260);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_32 sýnýfý sonu...