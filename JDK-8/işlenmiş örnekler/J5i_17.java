// J5i_17.java: WindowEventDemo ( PencereOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.Frame;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.SwingUtilities;
import javax.swing.JScrollPane;

public class J5i_17 extends JFrame
        implements WindowListener, WindowFocusListener, WindowStateListener {
    static final String yeniSatýr = System.getProperty ("line.separator");
    static final  String boþluk = "    ";
    JTextArea bildirimlerinDökümü;
    static J5i_17 çerçeve = new J5i_17 ("Pencere Olayý Gösterisi"); // Kurucuyu çaðýrýr...

    public J5i_17 (String baþlýk) {super (baþlýk);} // Kurucu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        çerçeve.setDefaultCloseOperation (3); // 3= JFrame.DO_NOTHING_ON_CLOSE
        çerçeve.parçalarýÝçerikPanosunaEkle();
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private void parçalarýÝçerikPanosunaEkle() {
        bildirimlerinDökümü = new JTextArea();
        bildirimlerinDökümü.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        bildirimlerinDökümü.setForeground (Color.YELLOW);
        bildirimlerinDökümü.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (bildirimlerinDökümü);
        kaydýraç.setPreferredSize (new Dimension (550, 450));
        getContentPane().add (kaydýraç, BorderLayout.CENTER);

        addWindowListener (this); // Dinleyiciler super içerik panosunda...
        addWindowFocusListener (this);
        addWindowStateListener (this);

        pencereYönetimi();
    } // parçalarýÝçerikPanosunaEkle() metodu sonu...
    
    // Pencere yöneticisinin hangi pencere durumlarýný desteklediðini kontrol edelim...
    public void pencereYönetimi() {
        Toolkit aletKutusu = çerçeve.getToolkit();
        if (! (aletKutusu.isFrameStateSupported (Frame.ICONIFIED)))
            mesaj ("Pencere yöneticiniz  ICONIFIED/ÝKONLAÞTIRILMA'yý desteklemiyor.");
        else mesaj ("Pencere yöneticiniz  ICONIFIED/ÝKONLAÞTIRILMA'yý destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_VERT)))
            mesaj ("Pencere yöneticiniz MAXIMIZED_VERT/DÝKEZ_AZAMÝLEÞTÝRÝLME'yi desteklemiyor.");
        else mesaj ("Pencere yöneticiniz MAXIMIZED_VERT/DÝKEZ_AZAMÝLEÞTÝRÝLME'yi destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_HORIZ)))
            mesaj ("Pencere yöneticiniz MAXIMIZED_HORIZ/YATAY_AZAMÝLEÞTÝRÝLME'yi desteklemiyor.");
        else mesaj ("Pencere yöneticiniz MAXIMIZED_HORIZ/YATAY_AZAMÝLEÞTÝRÝLME'yi destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_BOTH)))
            mesaj ("Pencere yöneticiniz MAXIMIZED_BOTH/HERÝKÝ_AZAMÝLEÞTÝRÝLME'yi desteklemiyor.");
        else mesaj ("Pencere yöneticiniz MAXIMIZED_BOTH/HERÝKÝ_AZAMÝLEÞTÝRÝLME'yi destekliyor.");
    } // pencereYönetimi() metodu sonu...

    private void mesaj (String msj) {
        bildirimlerinDökümü.append (msj + yeniSatýr);
        //System.out.println (msj); // Gerek duyulursa açýlabilir...
    } // mesaj(..) metodu sonu...

    // Gerek görülen pencere dinleyici hazýr metodlarýný iþleyelim...
    public void windowClosing (WindowEvent olay) {
        mesaj ("Pencere dinleyicisinin çaðýrdýðý hazýr metod: windowClosing/pencereKapanýyor.");
        // Kapanmadan önce 5 sn duraksatýp mesajlarý görelim...GÖREMEDÝK!!
        new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (çerçeve.isDisplayable()) çerçeve.dispose();
            } // actionPerformed(..) hazýr metodu sonu...
        }; // new.. ifadesi sonu...
        try {Thread.sleep (5000);}catch (Exception ist){} // Aldýrma
    } // windowClosing(..) hazýr metodu sonu...

    public void windowClosed (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowClosed/pencereKapatýldý.");}
    public void windowOpened (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowOpened/pencereAçýldý.");}
    public void windowIconified (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowIconified/pencereÝkonlaþtýrýldý.");}
    public void windowDeiconified (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowDeiconified/pencereÝkonsuzlaþtýrýldý.");}
    public void windowActivated (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowActivated/pencereAktifleþtirildi.");}
    public void windowDeactivated (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowDeactivated/pencereAktifsizleþtirildi.");}
    public void windowGainedFocus (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowGainedFocus/pencereOdaklanmaKazandý.");}
    public void windowLostFocus (WindowEvent olay) {mesaj ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowLostFocus/pencereOdaklanmaKaybetti.");}
    public void windowStateChanged (WindowEvent olay) {durumMesajý ("Pencere dinleyicisinin çaðrýlan hazýr metodu: windowStateChanged/pencereDurumDeðiþtirdi.", olay);}

    private void durumMesajý (String önÝzah, WindowEvent olay) {
        int yeniDurum = olay.getNewState();
        int eskiDurum = olay.getOldState();
        String msj = önÝzah
                + yeniSatýr + boþluk + "Eski durum: " + durumuDizgeyeÇevir (eskiDurum)
                + yeniSatýr + boþluk + "Yeni durum: " + durumuDizgeyeÇevir (yeniDurum);
        mesaj (msj);
    } // durumMesajý(..) metodu sonu...

    private String durumuDizgeyeÇevir (int durum) {
        if (durum == Frame.NORMAL) return "NORMAL";

        String durumDizgesi = " ";
        if ((durum & Frame.ICONIFIED) != 0) durumDizgesi += "ICONIFIED/ÝKONLAÞTIRILDI";

        // MAXIMIZED_BOTH iki bitlik (yatay-dikey) kontrol gerektirmekte...
        if ((durum & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) durumDizgesi += "MAXIMIZED_BOTH/HERÝKÝ_AZAMÝLEÞTÝRÝLDÝ";
        else {
            if ((durum & Frame.MAXIMIZED_VERT) != 0) durumDizgesi += "MAXIMIZED_VERT/DÝKEY_AZAMÝLEÞTÝRÝLDÝ";
            if ((durum & Frame.MAXIMIZED_HORIZ) != 0) durumDizgesi += "MAXIMIZED_HORIZ/YATAY_AZAMÝLEÞTÝRÝLDÝ";
        } // else kararý sonu...

        if (" ".equals (durumDizgesi)) durumDizgesi = "BÝLÝNMÝYOR";

        return durumDizgesi.trim();
    } // durumuDizgeyeÇevir(..) metodu sonu...
} // J5i_17 sýnýfý sonu...