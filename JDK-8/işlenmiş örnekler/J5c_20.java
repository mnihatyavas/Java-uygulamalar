// J5c_20.java: Framework (Penceresanat�) �rne�i.

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
    public int pencereSay�s� = 0;
    private Point sonKonum = null;
    private int b�y�kX = 500;
    private int b�y�kY = 500;

    public J5c_20() {// Kurucu...
        Dimension ekranEbat� = Toolkit.getDefaultToolkit().getScreenSize();
        b�y�kX = ekranEbat�.width - 50;
        b�y�kY = ekranEbat�.height - 50;
        yeniPencereYap();
    } // J5c_20() kurucusu sonu...

    public void yeniPencereYap() {
        JFrame �er�eve = new Pencerem (this);

        pencereSay�s�++;
        System.out.println ("G�r�nen pencere say�s�: " + pencereSay�s�);

        if (sonKonum != null) {
            // Yeni pencereyi �ncekinden 65-40 sa�-a�a��'ya,
            // ancak ekran sonuna ula��lm��sa sol-�st'e konumland�r...
            sonKonum.translate (65, 40);
            if ((sonKonum.x > b�y�kX) || (sonKonum.y > b�y�kY)) sonKonum.setLocation (0, 0);
            �er�eve.setLocation (sonKonum);
        }else sonKonum = �er�eve.getLocation();

        System.out.println ("Pencerenin konumu: " + sonKonum);
        if (pencereSay�s� == 1) {
            �er�eve.setLocationRelativeTo (null);
            �er�eve.setVisible (true);
            sonKonum.translate (-65, -40);
        }else �er�eve.setVisible (true);
    } // yeniPencereYap() metodu sonu...

    public void windowClosed (WindowEvent olay) {
        pencereSay�s�--;
        System.out.println ("G�r�nen pencere say�s� = " + pencereSay�s�);
        if (pencereSay�s� <= 0) {
            System.out.println ("T�m pencereler kapanm��. Bay bay!");
            System.exit (0);
        } // if karar� sonu...
    } // windowClosed(..) haz�r metodu sonu...

    class Pencerem extends JFrame {
        protected Dimension varsay�l�Ebat = new Dimension (200, 200);
        protected J5c_20 �er�eveNesnesi = null;

        public Pencerem (J5c_20 kontrol�r) {// Kurucu...
            super ("Yeni Pencere");
            �er�eveNesnesi = kontrol�r;
            setDefaultCloseOperation (DISPOSE_ON_CLOSE); // Sa�-�st X, o pencereyi kapar...
            addWindowListener (�er�eveNesnesi);

            JMenu men� = new JMenu ("Pencere");
            men�.setMnemonic (KeyEvent.VK_P); // Alt-P men�y� a�ar...
            JMenuItem birim = null;

            // "Yeni" ile yeni bir pencere yarat�l�r...
            birim = new JMenuItem ("Yeni");
            birim.setMnemonic (KeyEvent.VK_Y); // Alt-Y...
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Yeni bir pencere yarat�ld�");
                    �er�eveNesnesi.yeniPencereYap();
            }}); // bir.. ifadesi sonu...
            men�.add (birim);

            // "Kapat" ile o pencere kapat�l�r...
            birim = new JMenuItem ("Kapat");
            birim.setMnemonic (KeyEvent.VK_K); // Alt-K...
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Bu pencere kapat�l�yor");
                    Pencerem.this.setVisible (false);
                    Pencerem.this.dispose();
            }}); // bir.. ifadesi sonu...
            men�.add (birim);

            // "Terket" ile mevcut t�m pencereler kapat�l�r...
            birim = new JMenuItem ("Terket");
            birim.setMnemonic (KeyEvent.VK_T); // Alt-T
            birim.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent olay) {
                    System.out.println ("Topyek�n kapat talebi al�nd�");
                    �er�eveNesnesi.terket (Pencerem.this);
            }}); // bir.. ifadesi sonu...
            men�.add (birim);

            JMenuBar men��ubu�u = new JMenuBar();
            men��ubu�u.add (men�);

            setJMenuBar (men��ubu�u);
            setSize (varsay�l�Ebat);
        } // Pencerem(..) kurucusu sonu...
   } // Pencerem s�n�f� sonu...

    public void terket (JFrame �er�eve) {
        if (terketOnayland�M� (�er�eve)) {
            System.out.println ("T�m pencereler kapat�l�yor...");
            System.exit (0);
        } // if karar� sonu...
        System.out.println ("Terket i�lemi onaylanmad�; kal�yoruz.");
    } // terket(..) metodu sonu...

    private boolean terketOnayland�M� (JFrame �er�eve) {
        String dizge1 = "Terket";
        String dizge2 = "�ptal";
        Object[] se�enekler = {dizge1, dizge2};
        int n = JOptionPane.showOptionDialog (
                �er�eve,
                "Pencereler hen�z kapanmad�.\nGer�ekten t�m�n�n kapanmas�n� istiyor musun?",
                "Terket Onay�",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                se�enekler,
                dizge2);

        if (n == JOptionPane.YES_OPTION) return true;
        else return false;
    } // terketOnayland�M�(..) metodu sonu...

    private static void yaratVeG�sterGUI() {J5c_20 pencereSanat� = new J5c_20();}

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_20 s�n�f� sonu...