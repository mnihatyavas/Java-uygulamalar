// J5e_23.java: TrayIconDemo (TepsiÝkonuGösterisi) örneði.

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

// TrayIcon/TepsiÝkonu görevçubuðu gizli simgelerdeki program ikonudur...
public class J5e_23 {
    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        // SystemTray/SistemTepsi desteði mevudiyetini kontrol edelim...
        if (!SystemTray.isSupported()) {System.out.println ("SystemTray/SistemTepsisi desteklenmiyor!"); return;}

        final PopupMenu zýplayanMenü = new PopupMenu();
        final TrayIcon tepsiÝkonu = new TrayIcon (resmiYarat ("resim/ampül.gif", "tepsi ikonu"));
        final SystemTray sistemTepsisi = SystemTray.getSystemTray();

        // zýplayanMenü komponentlerini yaratalým...
        MenuItem hakkýndaBirimi = new MenuItem ("Hakkýnda");
        CheckboxMenuItem çentikBirimi1 = new CheckboxMenuItem ("Otomatik ebatlamayý kur");
        CheckboxMenuItem çentikBirimi2 = new CheckboxMenuItem ("Alet ipucunu kur");
        Menu gösterMenüsü = new Menu ("Göster");
        MenuItem hataBirimi = new MenuItem ("Hata");
        MenuItem ikazBirimi = new MenuItem ("Ýkaz");
        MenuItem bilgiBirimi = new MenuItem ("Bilgi");
        MenuItem hiçBirimi = new MenuItem ("Hiç");
        MenuItem çýkBirimi = new MenuItem ("Çýk");
        
        // Menü komponentlerini zýplayanMenü'ye ekleyelim...
        zýplayanMenü.add (hakkýndaBirimi);
        zýplayanMenü.addSeparator(); // ayraç...
        zýplayanMenü.add (çentikBirimi1);
        zýplayanMenü.add (çentikBirimi2);
        zýplayanMenü.addSeparator();
        zýplayanMenü.add (gösterMenüsü);
            gösterMenüsü.add (hataBirimi);
            gösterMenüsü.add (ikazBirimi);
            gösterMenüsü.add (bilgiBirimi);
            gösterMenüsü.add (hiçBirimi);
        zýplayanMenü.add (çýkBirimi);

        tepsiÝkonu.setPopupMenu (zýplayanMenü);
        try {sistemTepsisi.add (tepsiÝkonu);
        }catch (AWTException ist) {System.err.println ("Tepsi ikonu sistem tepsisine eklenemedi!"); return;}

        tepsiÝkonu.addActionListener (new ActionListener() {// Simgeye sol-çift týklayýnca...
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showMessageDialog (null, "Bu diyalog kutusu Sistem Tepsisi'nden çalýþtýrýlmýþtýr!");
        }}); // tep.. ifadesi sonu...

        hakkýndaBirimi.addActionListener (new ActionListener() {// Sonrakiler simgeye sað týklayýnca...
            public void actionPerformed (ActionEvent olay) {
                JOptionPane.showMessageDialog (null, "Bu diyalog kutusu Hakkýnda menü birimiyle çalýþtýrýlmýþtýr!");
        }}); // hak.. ifadesi sonu...

        çentikBirimi1.addItemListener (new ItemListener() {
            public void itemStateChanged (ItemEvent olay) {
                int çentikBirimi = olay.getStateChange();
                if (çentikBirimi == ItemEvent.SELECTED) tepsiÝkonu.setImageAutoSize (true);
                else tepsiÝkonu.setImageAutoSize (false);
        }}); // çen.. ifadesi sonu...

        çentikBirimi2.addItemListener (new ItemListener() {
            public void itemStateChanged (ItemEvent olay) {
                int çentikBirimi = olay.getStateChange();
                if (çentikBirimi == ItemEvent.SELECTED) tepsiÝkonu.setToolTip ("Sun Tepsi Ýkonu");
                else tepsiÝkonu.setToolTip (null);
        }}); // çen.. ifadesi sonu...

        ActionListener aksiyonDinleyicisi = new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                MenuItem menüBirimi = (MenuItem)olay.getSource();
                // TrayIcon.MessageType mesajTipi = null;
                System.out.println (menüBirimi.getLabel()); // Dos ekranýna týklanan menü birimi yazýlýr...
                if ("Hata".equals (menüBirimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.ERROR;
                    tepsiÝkonu.displayMessage ("Sun TepsiÝkonu Gösterisi", "Bu bir hata mesajýdýr!", TrayIcon.MessageType.ERROR);
                }else if ("Ýkaz".equals (menüBirimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.WARNING;
                    tepsiÝkonu.displayMessage ("Sun TepsiÝkonu Gösterisi", "Bu bir ikaz mesajýdýr!", TrayIcon.MessageType.WARNING);
                }else if ("Bilgi".equals (menüBirimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.INFO;
                    tepsiÝkonu.displayMessage ("Sun TepsiÝkonu Gösterisi", "Bu bir bilgi mesajýdýr!", TrayIcon.MessageType.INFO);
                }else if ("Hiç".equals (menüBirimi.getLabel())) {
                    // mesajTipi = TrayIcon.MessageType.NONE;
                    tepsiÝkonu.displayMessage ("Sun TepsiÝkonu Gösterisi", "Bu bir normal mesajdýr!", TrayIcon.MessageType.NONE);
                } // if-else.. kararý sonu...
            } // actionPerformed(..) hazýr metodu sonu...
        }; // Act.. ifadesi sonu...

        hataBirimi.addActionListener (aksiyonDinleyicisi); // Menü birimlerini dinleyiciye duyarlýyoruz...
        ikazBirimi.addActionListener (aksiyonDinleyicisi);
        bilgiBirimi.addActionListener (aksiyonDinleyicisi);
        hiçBirimi.addActionListener (aksiyonDinleyicisi);

        çýkBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                sistemTepsisi.remove (tepsiÝkonu);
                System.exit (0);
        }}); // çýk.. ifadesi sonu...
    } // yaratVeGösterGUI() metodu sonu...

    protected static Image resmiYarat (String yol, String izah) {
        URL resimYureli = J5e_23.class.getResource (yol);
        if (resimYureli == null) {System.err.println ("Resim dosyasý ["  + yol + "] bulunamadý!");return null;
        }else return (new ImageIcon (resimYureli, izah)).getImage();
    } // resmiYarat(..) metodu sonu...
} // J5e_23 sýnýfý sonu...