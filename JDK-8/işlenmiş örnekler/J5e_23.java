// J5e_23.java: TrayIconDemo (Tepsi�konuG�sterisi) �rne�i.

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.PopupMenu;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Image;
import java.awt.CheckboxMenuItem;
import java.awt.AWTException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.net.URL;

// TrayIcon/Tepsi�konu g�rev�ubu�u gizli simgelerdeki program ikonudur...
public class J5e_23 {
    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        // SystemTray/SistemTepsi deste�i mevudiyetini kontrol edelim...
        if (!SystemTray.isSupported()) {System.out.println ("SystemTray/SistemTepsisi desteklenmiyor!"); return;}

        final PopupMenu z�playanMen� = new PopupMenu();
        final TrayIcon tepsi�konu = new TrayIcon (resmiYarat ("resim/amp�l.gif", "tepsi ikonu"));
        final SystemTray sistemTepsisi = SystemTray.getSystemTray();

        // z�playanMen� komponentlerini yaratal�m...
        MenuItem hakk�ndaBirimi = new MenuItem ("Hakk�nda");
        CheckboxMenuItem �entikBirimi1 = new CheckboxMenuItem ("Otomatik ebatlamay� kur");
        CheckboxMenuItem �entikBirimi2 = new CheckboxMenuItem ("Alet ipucunu kur");
        Menu g�sterMen�s� = new Menu ("G�ster");
        MenuItem hataBirimi = new MenuItem ("Hata");
        MenuItem ikazBirimi = new MenuItem ("�kaz");
        MenuItem bilgiBirimi = new MenuItem ("Bilgi");
        MenuItem hi�Birimi = new MenuItem ("Hi�");
        MenuItem ��kBirimi = new MenuItem ("��k");
        
        // Men� komponentlerini z�playanMen�'ye ekleyelim...
        z�playanMen�.add (hakk�ndaBirimi);
        z�playanMen�.addSeparator(); // ayra�...
        z�playanMen�.add (�entikBirimi1);
        z�playanMen�.add (�entikBirimi2);
        z�playanMen�.addSeparator();
        z�playanMen�.add (g�sterMen�s�);
            g�sterMen�s�.add (hataBirimi);
            g�sterMen�s�.add (ikazBirimi);
            g�sterMen�s�.add (bilgiBirimi);
            g�sterMen�s�.add (hi�Birimi);
        z�playanMen�.add (��kBirimi);

        tepsi�konu.setPopupMenu (z�playanMen�);
        try {sistemTepsisi.add (tepsi�konu);
        }catch (AWTException ist) {System.err.println ("Tepsi ikonu sistem tepsisine eklenemedi!"); return;}

        tepsi�konu.addActionListener (new ActionListener() {// Simgeye sol-�ift t�klay�nca...
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showMessageDialog (null, "Bu diyalog kutusu Sistem Tepsisi'nden �al��t�r�lm��t�r!");
        }}); // tep.. ifadesi sonu...

        hakk�ndaBirimi.addActionListener (new ActionListener() {// Sonrakiler simgeye sa� t�klay�nca...
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showMessageDialog (null, "Bu diyalog kutusu Hakk�nda men� birimiyle �al��t�r�lm��t�r!");
        }}); // hak.. ifadesi sonu...

        �entikBirimi1.addItemListener (new ItemListener() {
            public void itemStateChanged (ItemEvent olay) {
                int �entikBirimi = olay.getStateChange();
                if (�entikBirimi == ItemEvent.SELECTED) tepsi�konu.setImageAutoSize (true);
                else tepsi�konu.setImageAutoSize (false);
        }}); // �en.. ifadesi sonu...

        �entikBirimi2.addItemListener (new ItemListener() {
            public void itemStateChanged (ItemEvent olay) {
                int �entikBirimi = olay.getStateChange();
                if (�entikBirimi == ItemEvent.SELECTED) tepsi�konu.setToolTip ("Sun Tepsi �konu");
                else tepsi�konu.setToolTip (null);
        }}); // �en.. ifadesi sonu...

        ActionListener aksiyonDinleyicisi = new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                MenuItem men�Birimi = (MenuItem)olay.getSource();
                // TrayIcon.MessageType mesajTipi = null;
                System.out.println (men�Birimi.getLabel()); // Dos ekran�na t�klanan men� birimi yaz�l�r...
                if ("Hata".equals (men�Birimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.ERROR;
                    tepsi�konu.displayMessage ("Sun Tepsi�konu G�sterisi", "Bu bir hata mesaj�d�r!", TrayIcon.MessageType.ERROR);
                }else if ("�kaz".equals (men�Birimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.WARNING;
                    tepsi�konu.displayMessage ("Sun Tepsi�konu G�sterisi", "Bu bir ikaz mesaj�d�r!", TrayIcon.MessageType.WARNING);
                }else if ("Bilgi".equals (men�Birimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.INFO;
                    tepsi�konu.displayMessage ("Sun Tepsi�konu G�sterisi", "Bu bir bilgi mesaj�d�r!", TrayIcon.MessageType.INFO);
                }else if ("Hi�".equals (men�Birimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.NONE;
                    tepsi�konu.displayMessage ("Sun Tepsi�konu G�sterisi", "Bu bir normal mesajd�r!", TrayIcon.MessageType.NONE);
                } // if-else.. karar� sonu...
            } // actionPerformed(..) haz�r metodu sonu...
        }; // Act.. ifadesi sonu...

        hataBirimi.addActionListener (aksiyonDinleyicisi); // Men� birimlerini dinleyiciye duyarl�yoruz...
        ikazBirimi.addActionListener (aksiyonDinleyicisi);
        bilgiBirimi.addActionListener (aksiyonDinleyicisi);
        hi�Birimi.addActionListener (aksiyonDinleyicisi);

        ��kBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sistemTepsisi.remove (tepsi�konu);
                System.exit (0);
        }}); // ��k.. ifadesi sonu...
    } // yaratVeG�sterGUI() metodu sonu...

    protected static Image resmiYarat (String yol, String izah) {
        URL resimYureli = J5e_23.class.getResource (yol);
        if (resimYureli == null) {System.err.println ("Resim dosyas� ["  + yol + "] bulunamad�!");return null;
        }else return (new ImageIcon (resimYureli, izah)).getImage();
    } // resmiYarat(..) metodu sonu...
} // J5e_23 s�n�f� sonu...