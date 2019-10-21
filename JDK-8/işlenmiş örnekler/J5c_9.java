// J5c_9.java: Converter (Çevirici) örneði.

/* Bu uygulamanýn gerektirdiði dosyalar:
 * J5c_9x1.java = ConversionPanel.java/ÇevirmePaneli
 * J5c_9x2.java = Unit.java/Birim
 * J5c_9x3.java = ConverterRangeModel.java/DeðiþtiriciErimiModeli
 * J5c_9x4.java = FollowerRangeModel.java/TakipçiErimiModeli
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
    final static boolean ÇOKRENKLÝ = true;

    // Kullanacaðýnýz bak ve hisseti belirleyin: null (varsayýlan), "Metal", "System", "Motif"ve "GTK+"
    final static String BAKVEHÝSSET = null;

    J5c_9x3 veriModeli = new J5c_9x3();
    JPanel anaPano;

    /* 2 deðiþim paneli (metrik ve abd) kullanýldý. ABD/USA/Amerikan ile UK/BK/Ýngiliz
     * mesafe ölçümleri ayný olsa da programýn muhtemel gniþletilmesinde hacim
     * ölçümlerinde farklar oluþmaktadýr.
     */
    public J5c_9() {// Kurucu...
        // 1 metre temelli metrik mesafe nesnelerini J5c_9x2/Unit'le yaratýp,
        // bu birimlerle J5c_9x1/ConversionPanel'i baþlatalým...
        metrikMesafe[0] = new J5c_9x2 ("Santimetre", 0.01);
        metrikMesafe[1] = new J5c_9x2 ("Metre", 1.0);
        metrikMesafe[2] = new J5c_9x2 ("Kilometre", 1000.0);
        metrikPanel = new J5c_9x1 (this, "Metrik Sistem", metrikMesafe, veriModeli);

        // 1 metre'nin karþýlýðý olan ABD J5c_9x2/Unit nesnelerini yaratýp,
        // bu birimlerle J5c_9x1/ConversionPanel'i baþlatalým...
        abdMesafe[0] = new J5c_9x2 ("Ýnç", 0.0254);
        abdMesafe[1] = new J5c_9x2 ("Fit", 0.305);
        abdMesafe[2] = new J5c_9x2 ("Yard", 0.914);
        abdMesafe[3] = new J5c_9x2 ("Mil", 1613.0);
        abdPanel = new J5c_9x1 (this, "ABD Sistem", abdMesafe, new J5c_9x4 (veriModeli));

        // Bir JPanel yaratarak, J5c_9x1/ConversionPanel'i ona ekleyelim...
        anaPano = new JPanel();
        anaPano.setLayout (new BoxLayout (anaPano, BoxLayout.PAGE_AXIS));
        if (ÇOKRENKLÝ) {
            anaPano.setOpaque (true); // Saydamsýz...
            // Zemin rengi her açýlýþta rasgele olsun...
            anaPano.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if kararý sonu...
        anaPano.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));
        anaPano.add (Box.createRigidArea (new Dimension (0, 5)));
        anaPano.add (metrikPanel);
        anaPano.add (Box.createRigidArea (new Dimension (0, 5)));
        anaPano.add (abdPanel);
        anaPano.add (Box.createGlue());
        azamiDeðerleriKoy (true);
    } // J5c_9() kurucusu sonu...

    public void azamiDeðerleriKoy (boolean mevcutDeðerleriYenile) {
        double metrikÇarpan = metrikPanel.getMultiplier();
        double abdÇarpan = abdPanel.getMultiplier();
        int azami = J5c_9x1.AZAMÝ;

        if (metrikÇarpan > abdÇarpan) azami = (int)(J5c_9x1.AZAMÝ * (abdÇarpan / metrikÇarpan));

        veriModeli.setMaximum (azami);

        if (mevcutDeðerleriYenile) veriModeli.setDoubleValue (azami);
    } // azamiDeðerleriKoy(..) metodu sonu...

    private static void bakVeHissetiBaþlat() {
        String bakVeHisset = null; // "System" veya "Metal"

        if (BAKVEHÝSSET != null) {
            if (BAKVEHÝSSET.equals ("Metal")) bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
            else if (BAKVEHÝSSET.equals ("System")) bakVeHisset = UIManager.getSystemLookAndFeelClassName();
            else if (BAKVEHÝSSET.equals ("Motif")) bakVeHisset = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            else if (BAKVEHÝSSET.equals ("GTK+")) bakVeHisset = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            else {System.err.println ("Belirtilen BAKVEHÝSSET deðeri bilinmiyor: [" + BAKVEHÝSSET + "]");
                bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName(); // "Metal"a ayarla...
            } // Ýç if-else kararý sonu...

            try {UIManager.setLookAndFeel (bakVeHisset);
            }catch (ClassNotFoundException ist) {
                System.err.println ("Belirtilen bakVeHisset'in sýnýfýný bulamadým: [" + bakVeHisset + "]");
                System.err.println ("Sýnýf yolu için L&F library/kütüphaneyi dahil ettin mi?");
                System.err.println ("Varsayýlý null bakVeHisset kullanýlacak.");
            }catch (UnsupportedLookAndFeelException ist) {
                System.err.println ("Bu platformda belirtilen bakVeHisset (" + bakVeHisset + ") desteklenmiyor!");
                System.err.println ("Varsayýlý null bakVeHisset kullanýlacak.");
            }catch (Exception ist) {
                System.err.println ("Baþka sebeplerden dolayý belirtilen bakVeHisset (" + bakVeHisset + ") istisna fýrlatýyor!");
                ist.printStackTrace();
                System.err.println ("Varsayýlý null bakVeHisset kullanýlacak.");
            } // try-catch-catch-catch bloðu sonu...
        } // Dýþ if kararý sonu...
    } // bakVeHissetiBaþlat() metodu sonu...

    private static void yaratVeGösterGUI() {
        // Bak ve hisset'i kuralým...
        bakVeHissetiBaþlat();

        // Penceremizi yaratýp kuralým...
        JFrame çerçeve = new JFrame ("Çevirici");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Ayrýca penceremize sýnýf nesneli ve anaPano'lu bir içerik panosu yaratýp kuralým...
        J5c_9 çevirici = new J5c_9();
        çevirici.anaPano.setOpaque (true);
        çerçeve.setContentPane (çevirici.anaPano);

        // Ýçerikleri hazýrlanan penceremizi paketleyip gösterelim...
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_9 sýnýfý sonu...