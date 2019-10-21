// J5c_33.java: MenuDemo (MenüGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;

/* Gereken resim dosyasý:
 * resim/orta.gif
 */
public class J5c_33 implements ActionListener, ItemListener {
    JTextArea açýklamaAlaný;
    JScrollPane kaydýrmaPanosu;
    String yeniSatýr = "\n";

    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu;
        JMenu menü, altMenü;
        JMenuItem menüBirimi;
        JRadioButtonMenuItem rbMenüBirimi;
        JCheckBoxMenuItem cbMenüBirimi;

        // Menü çubuðunu yaratalým...
        menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Menü çubuðundaki ilk menüyü kuralým...
        menü = new JMenu ("Ýlk Menü");
        menü.setMnemonic (KeyEvent.VK_M); // Alt-M...
        menü.getAccessibleContext().setAccessibleDescription ("Bu programda menü birimleri olan tek menü");
        menüÇubuðu.add (menü);

        // 3 adet (metin ve ikonlu) menü birimleri gruplandýrmasý...
        menüBirimi = new JMenuItem ("Sadece metinli menü birimi", KeyEvent.VK_S); // Alt_S...
        //menüBirimi.setMnemonic(KeyEvent.VK_S); // Bir üst kurucuda zaten tanýmlandý...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1, ActionEvent.ALT_MASK)); // Her yerden Alt_1...
        menüBirimi.getAccessibleContext().setAccessibleDescription ("Bu menü çubuklarý sadece dinleyici açýklamasýný sunar");
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        ImageIcon ikon = resimÝkonunuYarat ("resim/orta.gif");
        menüBirimi = new JMenuItem ("Hem metin hem de ikonlu menü birimi", ikon);
        menüBirimi.setMnemonic (KeyEvent.VK_H);
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        menüBirimi = new JMenuItem (ikon); // Sadece ikon görünecek...
        //menüBirimi.setMnemonic (KeyEvent.VK_K); // Görünmediðinden dolayý iptal...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK)); // Her yerden Alt_K...
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        // 2 adet radyo düðmeli menü birimleri gruplandýrmasý...
        menü.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbMenüBirimi = new JRadioButtonMenuItem ("Radyo düðmeli menü birimi");
        rbMenüBirimi.setSelected (true);
        rbMenüBirimi.setMnemonic (KeyEvent.VK_R); // Alt_R...
        grup.add (rbMenüBirimi);
        rbMenüBirimi.addActionListener (this);
        menü.add (rbMenüBirimi);

        rbMenüBirimi = new JRadioButtonMenuItem ("Diðer radyo düðmeli menü birimi");
        rbMenüBirimi.setMnemonic (KeyEvent.VK_D);
        grup.add (rbMenüBirimi);
        rbMenüBirimi.addActionListener (this);
        menü.add (rbMenüBirimi);

        // 2 adet tercih kutulu menü birimleri gruplandýmasý...
        menü.addSeparator();
        cbMenüBirimi = new JCheckBoxMenuItem ("Tercih kutulu menü birimi");
        cbMenüBirimi.setMnemonic (KeyEvent.VK_T);
        cbMenüBirimi.addItemListener (this); // Tercih kutusunun çentikli/çentiksizliðine de duyarlý...
        menü.add (cbMenüBirimi);

        cbMenüBirimi = new JCheckBoxMenuItem ("Diðer tercih kutulu menü birimi");
        cbMenüBirimi.setMnemonic (KeyEvent.VK_H);
        cbMenüBirimi.addItemListener (this);
        menü.add (cbMenüBirimi);

        // Bir adet de alt menü...
        menü.addSeparator();
        altMenü = new JMenu ("Bir alt menü");
        altMenü.setMnemonic (KeyEvent.VK_A);

        menüBirimi = new JMenuItem ("Alt menüdeki ilk menü birimi");
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK)); // Her yerden Alt_2...
        menüBirimi.addActionListener (this);
        altMenü.add (menüBirimi);

        menüBirimi = new JMenuItem ("Alt menüdeki diðer menü birimi"); // Alt_? yok, týklamalýsýnýz...
        menüBirimi.addActionListener (this);
        altMenü.add (menüBirimi);
        menü.add (altMenü);

        // Menü çubuðundaki ikinci menüyü kuralým...
        menü = new JMenu ("Ýkinci Menü");
        menü.setMnemonic (KeyEvent.VK_N);
        menü.getAccessibleContext().setAccessibleDescription ("Henüz hiç bir menü birimi eklenmedi");
        menüÇubuðu.add (menü);

        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    public Container içerikPanosunuYarat() {
        // Kaydýrmalý (müdahalesiz) metin alanlý içerik panosunu/panelini yaratýp kuralým...
        JPanel içerikPaneli = new JPanel (new BorderLayout());
        içerikPaneli.setOpaque (true);
        açýklamaAlaný = new JTextArea (5, 30);
        açýklamaAlaný.setEditable (false); // müdahalesiz...
        açýklamaAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        açýklamaAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        kaydýrmaPanosu = new JScrollPane (açýklamaAlaný);
        içerikPaneli.add (kaydýrmaPanosu, BorderLayout.CENTER);

        return içerikPaneli;
    } // içerikPanosunuYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Aksiyon olayý tespit edildi." + yeniSatýr +
                "-->Olay kaynaðý: " +  kaynak.getText() + " (Bir " + sýnýfAdýnýAl (kaynak) + " tiplemesi)";
        açýklamaAlaný.append (rapor + yeniSatýr);
        açýklamaAlaný.setCaretPosition (açýklamaAlaný.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Item/birim olayý tespit edildi." + yeniSatýr +
                "-->Olay kaynaðý: " +  kaynak.getText() + " (Bir " + sýnýfAdýnýAl (kaynak) + " tiplemesi)" + yeniSatýr +
                "-->Yeni çentik durumu: " + ((olay.getStateChange() == ItemEvent.SELECTED) ? "seçili/çentikli" : "seçimsiz/çentiksiz");
        açýklamaAlaný.append (rapor + yeniSatýr);
        açýklamaAlaný.setCaretPosition (açýklamaAlaný.getDocument().getLength());
    } // itemStateChanged(..) hazýr metodu sonu...

    // Sadece sýnýf adýný (Action Listener: "an instance of JMenuItem" ve
    // Item Listener: "an instance of JCheckBoxMenuItem") döndürür...
    protected String sýnýfAdýnýAl (Object nesne) {
        String sýnýfDizgesi = nesne.getClass().getName();
        //int noktaEndeksi = sýnýfDizgesi.lastIndexOf (".");
        return sýnýfDizgesi; //.substring (noktaEndeksi + 1);
    } // sýnýfAdýnýAl(..) metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_33.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Menü Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_33 gösteri = new J5c_33();
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
} // J5c_33 sýnýfý sonu...