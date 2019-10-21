// J5i_17.java: WindowEventDemo ( PencereOlay�G�sterisi) �rne�i.

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
    static final String yeniSat�r = System.getProperty ("line.separator");
    static final  String bo�luk = "    ";
    JTextArea bildirimlerinD�k�m�;
    static J5i_17 �er�eve = new J5i_17 ("Pencere Olay� G�sterisi"); // Kurucuyu �a��r�r...

    public J5i_17 (String ba�l�k) {super (ba�l�k);} // Kurucu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        �er�eve.setDefaultCloseOperation (3); // 3= JFrame.DO_NOTHING_ON_CLOSE
        �er�eve.par�alar���erikPanosunaEkle();
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private void par�alar���erikPanosunaEkle() {
        bildirimlerinD�k�m� = new JTextArea();
        bildirimlerinD�k�m�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        bildirimlerinD�k�m�.setForeground (Color.YELLOW);
        bildirimlerinD�k�m�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (bildirimlerinD�k�m�);
        kayd�ra�.setPreferredSize (new Dimension (550, 450));
        getContentPane().add (kayd�ra�, BorderLayout.CENTER);

        addWindowListener (this); // Dinleyiciler super i�erik panosunda...
        addWindowFocusListener (this);
        addWindowStateListener (this);

        pencereY�netimi();
    } // par�alar���erikPanosunaEkle() metodu sonu...
    
    // Pencere y�neticisinin hangi pencere durumlar�n� destekledi�ini kontrol edelim...
    public void pencereY�netimi() {
        Toolkit aletKutusu = �er�eve.getToolkit();
        if (! (aletKutusu.isFrameStateSupported (Frame.ICONIFIED)))
            mesaj ("Pencere y�neticiniz  ICONIFIED/�KONLA�TIRILMA'y� desteklemiyor.");
        else mesaj ("Pencere y�neticiniz  ICONIFIED/�KONLA�TIRILMA'y� destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_VERT)))
            mesaj ("Pencere y�neticiniz MAXIMIZED_VERT/D�KEZ_AZAM�LE�T�R�LME'yi desteklemiyor.");
        else mesaj ("Pencere y�neticiniz MAXIMIZED_VERT/D�KEZ_AZAM�LE�T�R�LME'yi destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_HORIZ)))
            mesaj ("Pencere y�neticiniz MAXIMIZED_HORIZ/YATAY_AZAM�LE�T�R�LME'yi desteklemiyor.");
        else mesaj ("Pencere y�neticiniz MAXIMIZED_HORIZ/YATAY_AZAM�LE�T�R�LME'yi destekliyor.");

        if (! (aletKutusu.isFrameStateSupported (Frame.MAXIMIZED_BOTH)))
            mesaj ("Pencere y�neticiniz MAXIMIZED_BOTH/HER�K�_AZAM�LE�T�R�LME'yi desteklemiyor.");
        else mesaj ("Pencere y�neticiniz MAXIMIZED_BOTH/HER�K�_AZAM�LE�T�R�LME'yi destekliyor.");
    } // pencereY�netimi() metodu sonu...

    private void mesaj (String msj) {
        bildirimlerinD�k�m�.append (msj + yeniSat�r);
        //System.out.println (msj); // Gerek duyulursa a��labilir...
    } // mesaj(..) metodu sonu...

    // Gerek g�r�len pencere dinleyici haz�r metodlar�n� i�leyelim...
    public void windowClosing (WindowEvent olay) {
        mesaj ("Pencere dinleyicisinin �a��rd��� haz�r metod: windowClosing/pencereKapan�yor.");
        // Kapanmadan �nce 5 sn duraksat�p mesajlar� g�relim...G�REMED�K!!
        new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (�er�eve.isDisplayable()) �er�eve.dispose();
            } // actionPerformed(..) haz�r metodu sonu...
        }; // new.. ifadesi sonu...
        try {Thread.sleep (5000);}catch (Exception ist){} // Ald�rma
    } // windowClosing(..) haz�r metodu sonu...

    public void windowClosed (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowClosed/pencereKapat�ld�.");}
    public void windowOpened (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowOpened/pencereA��ld�.");}
    public void windowIconified (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowIconified/pencere�konla�t�r�ld�.");}
    public void windowDeiconified (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowDeiconified/pencere�konsuzla�t�r�ld�.");}
    public void windowActivated (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowActivated/pencereAktifle�tirildi.");}
    public void windowDeactivated (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowDeactivated/pencereAktifsizle�tirildi.");}
    public void windowGainedFocus (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowGainedFocus/pencereOdaklanmaKazand�.");}
    public void windowLostFocus (WindowEvent olay) {mesaj ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowLostFocus/pencereOdaklanmaKaybetti.");}
    public void windowStateChanged (WindowEvent olay) {durumMesaj� ("Pencere dinleyicisinin �a�r�lan haz�r metodu: windowStateChanged/pencereDurumDe�i�tirdi.", olay);}

    private void durumMesaj� (String �n�zah, WindowEvent olay) {
        int yeniDurum = olay.getNewState();
        int eskiDurum = olay.getOldState();
        String msj = �n�zah
                + yeniSat�r + bo�luk + "Eski durum: " + durumuDizgeye�evir (eskiDurum)
                + yeniSat�r + bo�luk + "Yeni durum: " + durumuDizgeye�evir (yeniDurum);
        mesaj (msj);
    } // durumMesaj�(..) metodu sonu...

    private String durumuDizgeye�evir (int durum) {
        if (durum == Frame.NORMAL) return "NORMAL";

        String durumDizgesi = " ";
        if ((durum & Frame.ICONIFIED) != 0) durumDizgesi += "ICONIFIED/�KONLA�TIRILDI";

        // MAXIMIZED_BOTH iki bitlik (yatay-dikey) kontrol gerektirmekte...
        if ((durum & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) durumDizgesi += "MAXIMIZED_BOTH/HER�K�_AZAM�LE�T�R�LD�";
        else {
            if ((durum & Frame.MAXIMIZED_VERT) != 0) durumDizgesi += "MAXIMIZED_VERT/D�KEY_AZAM�LE�T�R�LD�";
            if ((durum & Frame.MAXIMIZED_HORIZ) != 0) durumDizgesi += "MAXIMIZED_HORIZ/YATAY_AZAM�LE�T�R�LD�";
        } // else karar� sonu...

        if (" ".equals (durumDizgesi)) durumDizgesi = "B�L�NM�YOR";

        return durumDizgesi.trim();
    } // durumuDizgeye�evir(..) metodu sonu...
} // J5i_17 s�n�f� sonu...