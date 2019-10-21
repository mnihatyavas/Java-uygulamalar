// J5c_9.java: Converter (�evirici) �rne�i.

/* Bu uygulaman�n gerektirdi�i dosyalar:
 * J5c_9x1.java = ConversionPanel.java/�evirmePaneli
 * J5c_9x2.java = Unit.java/Birim
 * J5c_9x3.java = ConverterRangeModel.java/De�i�tiriciErimiModeli
 * J5c_9x4.java = FollowerRangeModel.java/Takip�iErimiModeli
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Dimension;

public class J5c_9 {
    J5c_9x1 metrikPanel, abdPanel;
    J5c_9x2[] metrikMesafe = new J5c_9x2[3];
    J5c_9x2[] abdMesafe = new J5c_9x2[4];
    final static boolean �OKRENKL� = true;

    // Kullanaca��n�z bak ve hisseti belirleyin: null (varsay�lan), "Metal", "System", "Motif"ve "GTK+"
    final static String BAKVEH�SSET = null;

    J5c_9x3 veriModeli = new J5c_9x3();
    JPanel anaPano;

    /* 2 de�i�im paneli (metrik ve abd) kullan�ld�. ABD/USA/Amerikan ile UK/BK/�ngiliz
     * mesafe �l��mleri ayn� olsa da program�n muhtemel gni�letilmesinde hacim
     * �l��mlerinde farklar olu�maktad�r.
     */
    public J5c_9() {// Kurucu...
        // 1 metre temelli metrik mesafe nesnelerini J5c_9x2/Unit'le yarat�p,
        // bu birimlerle J5c_9x1/ConversionPanel'i ba�latal�m...
        metrikMesafe[0] = new J5c_9x2 ("Santimetre", 0.01);
        metrikMesafe[1] = new J5c_9x2 ("Metre", 1.0);
        metrikMesafe[2] = new J5c_9x2 ("Kilometre", 1000.0);
        metrikPanel = new J5c_9x1 (this, "Metrik Sistem", metrikMesafe, veriModeli);

        // 1 metre'nin kar��l��� olan ABD J5c_9x2/Unit nesnelerini yarat�p,
        // bu birimlerle J5c_9x1/ConversionPanel'i ba�latal�m...
        abdMesafe[0] = new J5c_9x2 ("�n�", 0.0254);
        abdMesafe[1] = new J5c_9x2 ("Fit", 0.305);
        abdMesafe[2] = new J5c_9x2 ("Yard", 0.914);
        abdMesafe[3] = new J5c_9x2 ("Mil", 1613.0);
        abdPanel = new J5c_9x1 (this, "ABD Sistem", abdMesafe, new J5c_9x4 (veriModeli));

        // Bir JPanel yaratarak, J5c_9x1/ConversionPanel'i ona ekleyelim...
        anaPano = new JPanel();
        anaPano.setLayout (new BoxLayout (anaPano, BoxLayout.PAGE_AXIS));
        if (�OKRENKL�) {
            anaPano.setOpaque (true); // Saydams�z...
            // Zemin rengi her a��l��ta rasgele olsun...
            anaPano.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if karar� sonu...
        anaPano.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        anaPano.add (Box.createRigidArea (new Dimension (0, 5)));
        anaPano.add (metrikPanel);
        anaPano.add (Box.createRigidArea (new Dimension (0, 5)));
        anaPano.add (abdPanel);
        anaPano.add (Box.createGlue());
        azamiDe�erleriKoy (true);
    } // J5c_9() kurucusu sonu...

    public void azamiDe�erleriKoy (boolean mevcutDe�erleriYenile) {
        double metrik�arpan = metrikPanel.getMultiplier();
        double abd�arpan = abdPanel.getMultiplier();
        int azami = J5c_9x1.AZAM�;

        if (metrik�arpan > abd�arpan) azami = (int)(J5c_9x1.AZAM� * (abd�arpan / metrik�arpan));

        veriModeli.setMaximum (azami);

        if (mevcutDe�erleriYenile) veriModeli.setDoubleValue (azami);
    } // azamiDe�erleriKoy(..) metodu sonu...

    private static void bakVeHissetiBa�lat() {
        String bakVeHisset = null; // "System" veya "Metal"

        if (BAKVEH�SSET != null) {
            if (BAKVEH�SSET.equals ("Metal")) bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
            else if (BAKVEH�SSET.equals ("System")) bakVeHisset = UIManager.getSystemLookAndFeelClassName();
            else if (BAKVEH�SSET.equals ("Motif")) bakVeHisset = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            else if (BAKVEH�SSET.equals ("GTK+")) bakVeHisset = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            else {System.err.println ("Belirtilen BAKVEH�SSET de�eri bilinmiyor: [" + BAKVEH�SSET + "]");
                bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName(); // "Metal"a ayarla...
            } // �� if-else karar� sonu...

            try {UIManager.setLookAndFeel (bakVeHisset);
            }catch (ClassNotFoundException ist) {
                System.err.println ("Belirtilen bakVeHisset'in s�n�f�n� bulamad�m: [" + bakVeHisset + "]");
                System.err.println ("S�n�f yolu i�in L&F library/k�t�phaneyi dahil ettin mi?");
                System.err.println ("Varsay�l� null bakVeHisset kullan�lacak.");
            }catch (UnsupportedLookAndFeelException ist) {
                System.err.println ("Bu platformda belirtilen bakVeHisset (" + bakVeHisset + ") desteklenmiyor!");
                System.err.println ("Varsay�l� null bakVeHisset kullan�lacak.");
            }catch (Exception ist) {
                System.err.println ("Ba�ka sebeplerden dolay� belirtilen bakVeHisset (" + bakVeHisset + ") istisna f�rlat�yor!");
                ist.printStackTrace();
                System.err.println ("Varsay�l� null bakVeHisset kullan�lacak.");
            } // try-catch-catch-catch blo�u sonu...
        } // D�� if karar� sonu...
    } // bakVeHissetiBa�lat() metodu sonu...

    private static void yaratVeG�sterGUI() {
        // Bak ve hisset'i kural�m...
        bakVeHissetiBa�lat();

        // Penceremizi yarat�p kural�m...
        JFrame �er�eve = new JFrame ("�evirici");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Ayr�ca penceremize s�n�f nesneli ve anaPano'lu bir i�erik panosu yarat�p kural�m...
        J5c_9 �evirici = new J5c_9();
        �evirici.anaPano.setOpaque (true);
        �er�eve.setContentPane (�evirici.anaPano);

        // ��erikleri haz�rlanan penceremizi paketleyip g�sterelim...
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_9 s�n�f� sonu...