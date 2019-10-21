// J5c_20.java: Framework (Penceresanatý) örneði.

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class J5c_20 extends WindowAdapter {
    public int pencereSayýsý = 0;
    private Point sonKonum = null;
    private int büyükX = 500;
    private int büyükY = 500;

    public J5c_20() {// Kurucu...
        Dimension ekranEbatý = Toolkit.getDefaultToolkit().getScreenSize();
        büyükX = ekranEbatý.width - 50;
        büyükY = ekranEbatý.height - 50;
        yeniPencereYap();
    } // J5c_20() kurucusu sonu...

    public void yeniPencereYap() {
        JFrame çerçeve = new Pencerem (this);

        pencereSayýsý++;
        System.out.println ("Görünen pencere sayýsý: " + pencereSayýsý);

        if (sonKonum != null) {
            // Yeni pencereyi öncekinden 65-40 sað-aþaðý'ya,
            // ancak ekran sonuna ulaþýlmýþsa sol-üst'e konumlandýr...
            sonKonum.translate (65, 40);
            if ((sonKonum.x > büyükX) || (sonKonum.y > büyükY)) sonKonum.setLocation (0, 0);
            çerçeve.setLocation (sonKonum);
        }else sonKonum = çerçeve.getLocation();

        System.out.println ("Pencerenin konumu: " + sonKonum);
        if (pencereSayýsý == 1) {
            çerçeve.setLocationRelativeTo (null);
            çerçeve.setVisible (true);
            sonKonum.translate (-65, -40);
        }else çerçeve.setVisible (true);
    } // yeniPencereYap() metodu sonu...

    public void windowClosed (WindowEvent olay) {
        pencereSayýsý--;
        System.out.println ("Görünen pencere sayýsý = " + pencereSayýsý);
        if (pencereSayýsý <= 0) {
            System.out.println ("Tüm pencereler kapanmýþ. Bay bay!");
            System.exit (0);
        } // if kararý sonu...
    } // windowClosed(..) hazýr metodu sonu...

    class Pencerem extends JFrame {
        protected Dimension varsayýlýEbat = new Dimension (200, 200);
        protected J5c_20 çerçeveNesnesi = null;

        public Pencerem (J5c_20 kontrolör) {// Kurucu...
            super ("Yeni Pencere");
            çerçeveNesnesi = kontrolör;
            setDefaultCloseOperation (DISPOSE_ON_CLOSE); // Sað-üst X, o pencereyi kapar...
            addWindowListener (çerçeveNesnesi);

            JMenu menü = new JMenu ("Pencere");
            menü.setMnemonic (KeyEvent.VK_P); // Alt-P menüyü açar...
            JMenuItem birim = null;

            // "Yeni" ile yeni bir pencere yaratýlýr...
            birim = new JMenuItem ("Yeni");
            birim.setMnemonic (KeyEvent.VK_Y); // Alt-Y...
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Yeni bir pencere yaratýldý");
                    çerçeveNesnesi.yeniPencereYap();
            }}); // bir.. ifadesi sonu...
            menü.add (birim);

            // "Kapat" ile o pencere kapatýlýr...
            birim = new JMenuItem ("Kapat");
            birim.setMnemonic (KeyEvent.VK_K); // Alt-K...
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Bu pencere kapatýlýyor");
                    Pencerem.this.setVisible (false);
                    Pencerem.this.dispose();
            }}); // bir.. ifadesi sonu...
            menü.add (birim);

            // "Terket" ile mevcut tüm pencereler kapatýlýr...
            birim = new JMenuItem ("Terket");
            birim.setMnemonic (KeyEvent.VK_T); // Alt-T
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Topyekün kapat talebi alýndý");
                    çerçeveNesnesi.terket (Pencerem.this);
            }}); // bir.. ifadesi sonu...
            menü.add (birim);

            JMenuBar menüÇubuðu = new JMenuBar();
            menüÇubuðu.add (menü);

            setJMenuBar (menüÇubuðu);
            setSize (varsayýlýEbat);
        } // Pencerem(..) kurucusu sonu...
   } // Pencerem sýnýfý sonu...

    public void terket (JFrame çerçeve) {
        if (terketOnaylandýMý (çerçeve)) {
            System.out.println ("Tüm pencereler kapatýlýyor...");
            System.exit (0);
        } // if kararý sonu...
        System.out.println ("Terket iþlemi onaylanmadý; kalýyoruz.");
    } // terket(..) metodu sonu...

    private boolean terketOnaylandýMý (JFrame çerçeve) {
        String dizge1 = "Terket";
        String dizge2 = "Ýptal";
        Object[] seçenekler = {dizge1, dizge2};
        int n = JOptionPane.showOptionDialog (
                çerçeve,
                "Pencereler henüz kapanmadý.\nGerçekten tümünün kapanmasýný istiyor musun?",
                "Terket Onayý",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                seçenekler,
                dizge2);

        if (n == JOptionPane.YES_OPTION) return true;
        else return false;
    } // terketOnaylandýMý(..) metodu sonu...

    private static void yaratVeGösterGUI() {J5c_20 pencereSanatý = new J5c_20();}

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_20 sýnýfý sonu...