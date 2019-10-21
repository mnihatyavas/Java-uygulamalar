// J5g_2.java: SynthApplication (SentetikUygulama) örneði.

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

/* Gereken dosyalar: düðmeler1.xml
 *   resim/button.png
 *   resim/button2.png
 *
 * NOT: düðmeler1.xml==> "bind, region, null" hatasý veriyor; kullanýþsýz...
 */
public class J5g_2 implements ActionListener {
    private int týklanmaSayýsý = 0;
    private static String sentetikDosya = "düðmeler1.xml";
    final JLabel etiket = new JLabel ("Düðmenin týklanma sayýsý: " + "0");

    public Component parçalarýYarat() {
        JButton düðme = new JButton ("Ben bir javax.swing.JButton düðmesiyim!");
        düðme.setMnemonic (KeyEvent.VK_D);
        düðme.addActionListener (this); // Düðme dinleyiciye duyarlý...
        etiket.setLabelFor (düðme); // Etiketin düðmeye ilintilenmesi...

        // Düðme ve etiket parçasýný, çevresi tamponlu tek kolonlu bir panel'e ekleyelim...
        JPanel panel = new JPanel (new GridLayout (0, 1));
        panel.add (düðme);
        panel.add (etiket);
        panel.setBorder (BorderFactory.createEmptyBorder (30, 30, 10, 30)); // Üst, sol, alt ve sað tamponlar...

        return panel;
    } // parçalarýYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        týklanmaSayýsý++;
        etiket.setText ("Düðmenin týklanma sayýsý: " + týklanmaSayýsý);
    } // actionPerformed(..) hazýr metodu sonu...

    private static void bakVeHissetiBaþlat() {
       SynthLookAndFeel bakVeHisset = new SynthLookAndFeel();
            try {bakVeHisset.load (J5g_2.class.getResourceAsStream (sentetikDosya), J5g_2.class);
                UIManager.setLookAndFeel (bakVeHisset);
            }catch (Exception ist) {System.err.println ("Belirtilen BakVeHisset'i bulamadým: [" + bakVeHisset + "]");
                System.err.println ("Varsayýlý BakVeHissetKullanýlacak.");
                ist.printStackTrace();
            } // try-catch bloðu sonu...
    } // bakVeHissetiBaþlat() metodu sonu...

    private static void yaratVeGösterGUI() {
        bakVeHissetiBaþlat();
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame çerçeve = new JFrame ("Sentetik Uygulama");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5g_2 uygulama = new J5g_2(); // Varsayýlý kurucuyu çaðýrýr...
        Component parçalar = uygulama.parçalarýYarat();
        çerçeve.getContentPane().add (parçalar, BorderLayout.CENTER);
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5g_2 sýnýfý sonu...