// J5f_6.java: CardLayoutDemo (KartSerilimiGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class J5f_6 implements ItemListener {
    JPanel kartPaneli; // Düðmeler ve metinsatýrýný içeren, CardLayout/KartSerilim kullanan panel...
    final static String DÜÐME_PANELÝ = "JButton'lu Kart Paneli";
    final static String METÝNSATIRI_PANELÝ = "JTextField'lý Kart Paneli";

    public void kabaKomponentleriEkle (Container kab) {
        JPanel açýlýrKutuluPanel = new JPanel(); // Varsayýlý FlowLayout/AkýþSerilimi kullanýlacak...
        String açýlýrKutuBirimleri[] = { DÜÐME_PANELÝ, METÝNSATIRI_PANELÝ };
        JComboBox açýlýrKutu = new JComboBox (açýlýrKutuBirimleri);
        açýlýrKutu.setEditable (false); // Müdahalesiz...
        açýlýrKutu.addItemListener (this); // Dinleyiciye duyarlý...
        açýlýrKutuluPanel.add (açýlýrKutu);
        açýlýrKutuluPanel.setBackground (Color.getHSBColor (0.27f, 0.84f, 0.45f)); // HueSaturationBrightness...

        JPanel kartlýPanel1 = new JPanel();
        kartlýPanel1.add (new JButton ("Düðme 1"));
        kartlýPanel1.add (new JButton ("Düðme 2"));
        kartlýPanel1.add (new JButton ("Düðme 3"));
        kartlýPanel1.setBackground (Color.getHSBColor (0.75f, 0.84f, 0.65f));

        JPanel kartlýPanel2 = new JPanel();
        kartlýPanel2.add (new JTextField ("Metin Satýrý", 20));
        kartlýPanel2.setBackground (Color.getHSBColor (0.91f, 0.84f, 0.85f));

        kartPaneli = new JPanel (new CardLayout());
        kartPaneli.add (kartlýPanel1, DÜÐME_PANELÝ);
        kartPaneli.add (kartlýPanel2, METÝNSATIRI_PANELÝ);

        kab.add (açýlýrKutuluPanel, BorderLayout.PAGE_START);
        kab.add (kartPaneli, BorderLayout.CENTER);
    } // kabaKomponentleriEkle(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        CardLayout kartSerilim = (CardLayout)(kartPaneli.getLayout());
        kartSerilim.show (kartPaneli, (String)olay.getItem());
    } // itemStateChanged(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kart Serilimi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_6 gösteri = new J5f_6(); // Varsayýlý kurucuyu çaðýrýr...
        gösteri.kabaKomponentleriEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_6 sýnýfý sonu...