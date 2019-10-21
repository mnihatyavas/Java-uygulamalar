// J5c_33.java: MenuDemo (Men�G�sterisi) �rne�i.

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

/* Gereken resim dosyas�:
 * resim/orta.gif
 */
public class J5c_33 implements ActionListener, ItemListener {
    JTextArea a��klamaAlan�;
    JScrollPane kayd�rmaPanosu;
    String yeniSat�r = "\n";

    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u;
        JMenu men�, altMen�;
        JMenuItem men�Birimi;
        JRadioButtonMenuItem rbMen�Birimi;
        JCheckBoxMenuItem cbMen�Birimi;

        // Men� �ubu�unu yaratal�m...
        men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Men� �ubu�undaki ilk men�y� kural�m...
        men� = new JMenu ("�lk Men�");
        men�.setMnemonic (KeyEvent.VK_M); // Alt-M...
        men�.getAccessibleContext().setAccessibleDescription ("Bu programda men� birimleri olan tek men�");
        men��ubu�u.add (men�);

        // 3 adet (metin ve ikonlu) men� birimleri grupland�rmas�...
        men�Birimi = new JMenuItem ("Sadece metinli men� birimi", KeyEvent.VK_S); // Alt_S...
        //men�Birimi.setMnemonic(KeyEvent.VK_S); // Bir �st kurucuda zaten tan�mland�...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1, ActionEvent.ALT_MASK)); // Her yerden Alt_1...
        men�Birimi.getAccessibleContext().setAccessibleDescription ("Bu men� �ubuklar� sadece dinleyici a��klamas�n� sunar");
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        ImageIcon ikon = resim�konunuYarat ("resim/orta.gif");
        men�Birimi = new JMenuItem ("Hem metin hem de ikonlu men� birimi", ikon);
        men�Birimi.setMnemonic (KeyEvent.VK_H);
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        men�Birimi = new JMenuItem (ikon); // Sadece ikon g�r�necek...
        //men�Birimi.setMnemonic (KeyEvent.VK_K); // G�r�nmedi�inden dolay� iptal...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK)); // Her yerden Alt_K...
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        // 2 adet radyo d��meli men� birimleri grupland�rmas�...
        men�.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbMen�Birimi = new JRadioButtonMenuItem ("Radyo d��meli men� birimi");
        rbMen�Birimi.setSelected (true);
        rbMen�Birimi.setMnemonic (KeyEvent.VK_R); // Alt_R...
        grup.add (rbMen�Birimi);
        rbMen�Birimi.addActionListener (this);
        men�.add (rbMen�Birimi);

        rbMen�Birimi = new JRadioButtonMenuItem ("Di�er radyo d��meli men� birimi");
        rbMen�Birimi.setMnemonic (KeyEvent.VK_D);
        grup.add (rbMen�Birimi);
        rbMen�Birimi.addActionListener (this);
        men�.add (rbMen�Birimi);

        // 2 adet tercih kutulu men� birimleri grupland�mas�...
        men�.addSeparator();
        cbMen�Birimi = new JCheckBoxMenuItem ("Tercih kutulu men� birimi");
        cbMen�Birimi.setMnemonic (KeyEvent.VK_T);
        cbMen�Birimi.addItemListener (this); // Tercih kutusunun �entikli/�entiksizli�ine de duyarl�...
        men�.add (cbMen�Birimi);

        cbMen�Birimi = new JCheckBoxMenuItem ("Di�er tercih kutulu men� birimi");
        cbMen�Birimi.setMnemonic (KeyEvent.VK_H);
        cbMen�Birimi.addItemListener (this);
        men�.add (cbMen�Birimi);

        // Bir adet de alt men�...
        men�.addSeparator();
        altMen� = new JMenu ("Bir alt men�");
        altMen�.setMnemonic (KeyEvent.VK_A);

        men�Birimi = new JMenuItem ("Alt men�deki ilk men� birimi");
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK)); // Her yerden Alt_2...
        men�Birimi.addActionListener (this);
        altMen�.add (men�Birimi);

        men�Birimi = new JMenuItem ("Alt men�deki di�er men� birimi"); // Alt_? yok, t�klamal�s�n�z...
        men�Birimi.addActionListener (this);
        altMen�.add (men�Birimi);
        men�.add (altMen�);

        // Men� �ubu�undaki ikinci men�y� kural�m...
        men� = new JMenu ("�kinci Men�");
        men�.setMnemonic (KeyEvent.VK_N);
        men�.getAccessibleContext().setAccessibleDescription ("Hen�z hi� bir men� birimi eklenmedi");
        men��ubu�u.add (men�);

        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    public Container i�erikPanosunuYarat() {
        // Kayd�rmal� (m�dahalesiz) metin alanl� i�erik panosunu/panelini yarat�p kural�m...
        JPanel i�erikPaneli = new JPanel (new BorderLayout());
        i�erikPaneli.setOpaque (true);
        a��klamaAlan� = new JTextArea (5, 30);
        a��klamaAlan�.setEditable (false); // m�dahalesiz...
        a��klamaAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        a��klamaAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        kayd�rmaPanosu = new JScrollPane (a��klamaAlan�);
        i�erikPaneli.add (kayd�rmaPanosu, BorderLayout.CENTER);

        return i�erikPaneli;
    } // i�erikPanosunuYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Aksiyon olay� tespit edildi." + yeniSat�r +
                "-->Olay kayna��: " +  kaynak.getText() + " (Bir " + s�n�fAd�n�Al (kaynak) + " tiplemesi)";
        a��klamaAlan�.append (rapor + yeniSat�r);
        a��klamaAlan�.setCaretPosition (a��klamaAlan�.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Item/birim olay� tespit edildi." + yeniSat�r +
                "-->Olay kayna��: " +  kaynak.getText() + " (Bir " + s�n�fAd�n�Al (kaynak) + " tiplemesi)" + yeniSat�r +
                "-->Yeni �entik durumu: " + ((olay.getStateChange() == ItemEvent.SELECTED) ? "se�ili/�entikli" : "se�imsiz/�entiksiz");
        a��klamaAlan�.append (rapor + yeniSat�r);
        a��klamaAlan�.setCaretPosition (a��klamaAlan�.getDocument().getLength());
    } // itemStateChanged(..) haz�r metodu sonu...

    // Sadece s�n�f ad�n� (Action Listener: "an instance of JMenuItem" ve
    // Item Listener: "an instance of JCheckBoxMenuItem") d�nd�r�r...
    protected String s�n�fAd�n�Al (Object nesne) {
        String s�n�fDizgesi = nesne.getClass().getName();
        //int noktaEndeksi = s�n�fDizgesi.lastIndexOf (".");
        return s�n�fDizgesi; //.substring (noktaEndeksi + 1);
    } // s�n�fAd�n�Al(..) metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_33.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Men� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_33 g�steri = new J5c_33();
        �er�eve.setJMenuBar (g�steri.men��ubu�unuYarat());
        �er�eve.setContentPane (g�steri.i�erikPanosunuYarat());
        �er�eve.setBounds (500,100, 450,260);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_33 s�n�f� sonu...