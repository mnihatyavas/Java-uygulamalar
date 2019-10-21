// J5c_32.java: MenuLookDemo (Men�yeBak��G�sterimi) �rne�i.

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

// Gereken resim dosyas�: resim/orta.gif
public class J5c_32 {
    JTextArea metinAlan�;
    JScrollPane kayd�rmaPanosu;

    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u;
        JMenu men�, altMen�;
        JMenuItem men�Birimi;
        JRadioButtonMenuItem rbmBirimi;
        JCheckBoxMenuItem cbmBirimi;

        // Men� �ubu�unu yaratal�m...
        men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Men� �ubu�undaki ilk men�m�z� yaratal�m...
        men� = new JMenu ("�lk Men�");
        men�.setMnemonic (KeyEvent.VK_M);
        men�.getAccessibleContext().setAccessibleDescription ("Bu programda men� birimleri olan tek men�");
        men��ubu�u.add (men�);

        // 3 adet men� birimi...
        men�Birimi = new JMenuItem ("Sadece metinli men� birimi", KeyEvent.VK_S);
        //men�Birimi.setMnemonic(KeyEvent.VK_S); // Alt_S b�yle de atanabilir...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1,  ActionEvent.ALT_MASK));
        men�Birimi.getAccessibleContext().setAccessibleDescription ("Bu men� birimi ger�ekte herhangi bir i�lem yapmaz");
        men�.add (men�Birimi);

        ImageIcon ikon = resim�konunuYarat ("resim/orta.gif");
        men�Birimi = new JMenuItem ("Hem metin hem de ikonlu men� birimi", ikon);
        men�Birimi.setMnemonic (KeyEvent.VK_H);
        men�.add (men�Birimi);

        men�Birimi = new JMenuItem (ikon);
        men�Birimi = new JMenuItem ("Yaln�zca ikonlu men� birimi", ikon);
        men�Birimi.setMnemonic (KeyEvent.VK_Y);
        men�.add (men�Birimi);

        // 2 adet radyo d��mesi gruplu men� birimi...
        men�.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbmBirimi = new JRadioButtonMenuItem ("Birinci radyo d��mesi men� birimi");
        rbmBirimi.setSelected (true);
        rbmBirimi.setMnemonic (KeyEvent.VK_B);
        grup.add (rbmBirimi);
        men�.add (rbmBirimi);

        rbmBirimi = new JRadioButtonMenuItem ("Di�er radyo d��mesi men� birimi");
        rbmBirimi.setMnemonic (KeyEvent.VK_D);
        grup.add (rbmBirimi);
        men�.add (rbmBirimi);

        // 2 adet �entik kutusu gruplu men� birimleri...
        men�.addSeparator();
        cbmBirimi = new JCheckBoxMenuItem ("�lk tercih kutusu men� birimi");
        cbmBirimi.setMnemonic (KeyEvent.VK_T);
        men�.add (cbmBirimi);

        cbmBirimi = new JCheckBoxMenuItem ("Di�er tercih kutusu men� birimi");
        cbmBirimi.setMnemonic(KeyEvent.VK_K);
        men�.add (cbmBirimi);

        // 1 adet alt men�...
        men�.addSeparator();
        altMen� = new JMenu ("Bir alt men�");
        altMen�.setMnemonic(KeyEvent.VK_A);

        men�Birimi = new JMenuItem ("Alt men�deki ilk men� birimi");
        men�Birimi.setMnemonic (KeyEvent.VK_L);
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK));
        altMen�.add (men�Birimi);

        men�Birimi = new JMenuItem ("Alt men�deki ikinci men� birimi");
        men�Birimi.setMnemonic (KeyEvent.VK_M);
        altMen�.add (men�Birimi);
        men�.add (altMen�);

        // Men� �ubu�undaki ikinci men�m�z� yaratal�m...
        men� = new JMenu ("Di�er Men�");
        men�.setMnemonic (KeyEvent.VK_D);
        men�.getAccessibleContext().setAccessibleDescription ("Bu men�n�n hen�z hi�bir men� birimi yok");
        men��ubu�u.add (men�);

        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    public Container i�erikPanosunuYarat() {
        // Bir i�erik paneli yaratal�m...
        JPanel i�erikPaneli = new JPanel (new BorderLayout());
        i�erikPaneli.setOpaque (true);

        // Kayd�rmal� bir metin alan� yaratal�m...
        metinAlan� = new JTextArea (5, 30);
        metinAlan�.setEditable (true); // �stedi�ini (beydude) yazabilirsin...
        metinAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        kayd�rmaPanosu = new JScrollPane (metinAlan�);

        // Kayd�rma panolu metin alan�n� i�erik paneline ekleyelim...
        i�erikPaneli.add (kayd�rmaPanosu, BorderLayout.CENTER);

        return i�erikPaneli;
    } // i�erikPanosunuYarat() metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_32.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Men�ye Bak�� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_32 g�steri = new J5c_32();
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
} // J5c_32 s�n�f� sonu...