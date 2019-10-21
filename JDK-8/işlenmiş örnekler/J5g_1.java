// J5g_1.java: LookAndFeelDemo (BakVeHissetGösterisi) örneði.

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.OceanTheme;

// Gereken dosya: J5g_1x.java=TestTheme.java
public class J5g_1 implements ActionListener {
    private static String etiketÖneki = "Düðmenin týklanma sayýsý: ";
    private int týklamaSayýsý = 0;
    final JLabel etiket = new JLabel (etiketÖneki + "0");

    // BakVeHisset geçerli sabiteleri: null (varsayýlý), "Metal", "System", "Motif" ve "GTK"
    static String BAKVEHÝSSET = null;

    // "Metal" seçtiðinizde 3 tema tercihiniz var: "DefaultMetal", "Ocean" ve "Özel"
    static String TEMA = null;

    public Component parçalarýYarat() {
        JButton düðme = new JButton ("Ben bir javax.swing.JButton düðmesiyim (Alt_D)!");
        düðme.setMnemonic (KeyEvent.VK_D); // Alt_D kýsayol tuþunu düðmeye baðlayalým...
        düðme.addActionListener (this); // Düðmeyi dinleyiciye duyarlayalým...
        etiket.setLabelFor (düðme); // Etiketle düðmeyi birliktelikleyelim...

         // Parçalarý tek ýzgara kolonlu panele alt-alta koyup çevresini tampolayalým...
        JPanel panel = new JPanel (new GridLayout (0, 1));
        panel.add (düðme);
        panel.add (etiket);
        panel.setBorder (BorderFactory.createEmptyBorder (30, 30, 10, 30)); // üst, sol, alt ve sað tamponlar...

        return panel;
    } // parçalarýYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        týklamaSayýsý++;
        etiket.setText (etiketÖneki + týklamaSayýsý);
    } // actionPerformed(..) hazýr metodu sonu...

    private static void bakVeHissetiBaþlat() {
        String bakVeHisset = null;

        if (BAKVEHÝSSET != null) {
            if (BAKVEHÝSSET.equals ("Metal")) bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
              // veya: {bakVeHisset = "javax.swing.plaf.metal.MetalLookAndFeel";}...
            else if (BAKVEHÝSSET.equals ("System")) bakVeHisset = UIManager.getSystemLookAndFeelClassName();
            else if (BAKVEHÝSSET.equals ("Motif")) bakVeHisset = "com.sun.java.swing.plaf.motif.MotifLookAndFeel"; // Bu bende namevcut...
            else if (BAKVEHÝSSET.equals ("GTK")) bakVeHisset = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"; // Bu bende namevcut...
            else {System.err.println ("Geçersiz bir BAKVEHÝSSET belirtilmiþ: [" + BAKVEHÝSSET + "]");
                bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
            } // else kararý sonu...

            try {UIManager.setLookAndFeel (bakVeHisset);
                if (BAKVEHÝSSET.equals ("Metal")) {
                  if (TEMA.equals ("DefaultMetal")) MetalLookAndFeel.setCurrentTheme (new DefaultMetalTheme());
                  else if (TEMA.equals ("Ocean")) MetalLookAndFeel.setCurrentTheme (new OceanTheme());
                  else MetalLookAndFeel.setCurrentTheme (new J5g_1x()); // Tema "Özel"...

                  UIManager.setLookAndFeel (new MetalLookAndFeel()); 
                }	// if kararý sonu...
            }catch (ClassNotFoundException ist) {//java.lang
                System.err.println ("Belirtilen BakVeHisset sýnýfý bulunamadý:" + bakVeHisset);
                System.err.println ("BakVeHisset kütüphanesini sýnýf yoluna koydunuz mu?");
                System.err.println ("Varsayýlý BakVeHisset kullanýlacaktýr.");
            }catch (UnsupportedLookAndFeelException ist) {
                System.err.println ("Bu platformda belirtilen BakVeHisset kullanýlamaz/desteklenmiyor: " + bakVeHisset);
                System.err.println ("Varsayýlý BakVeHisset kullanýlacaktýr.");
            }catch (Exception ist) {// Varsayýlý (diðer tüm istisnalar)...
                System.err.println ("Herhalükarda belirttiðiniz BakVeHisset'e ulaþamýyorum: " + bakVeHisset);
                System.err.println ("Varsayýlý BakVeHisset kullanýlacaktýr.");
                ist.printStackTrace();
            } // try-catch.. bloðu sonu...
        } // Dýþ if kararý sonu...
    } // bakVeHissetiBaþlat() metodu sonu...

    private static void yaratVeGösterGUI() {
        bakVeHissetiBaþlat();
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame çerçeve = new JFrame ("BakVeHisset Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5g_1 gösteri = new J5g_1(); // Varsayýlý kurucuyu çaðýrýr...
        Component parçalar = gösteri.parçalarýYarat();
        çerçeve.getContentPane().add (parçalar, BorderLayout.CENTER);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } //yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        BAKVEHÝSSET = args.length > 0 ? args[0] : "Metal";
        TEMA = args.length > 1 ? args[1] : "Özel";
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5g_1 sýnýfý sonu...