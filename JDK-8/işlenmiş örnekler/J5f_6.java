// J5f_6.java: CardLayoutDemo (KartSerilimiG�sterisi) �rne�i.

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
    JPanel kartPaneli; // D��meler ve metinsat�r�n� i�eren, CardLayout/KartSerilim kullanan panel...
    final static String D��ME_PANEL� = "JButton'lu Kart Paneli";
    final static String MET�NSATIRI_PANEL� = "JTextField'l� Kart Paneli";

    public void kabaKomponentleriEkle (Container kab) {
        JPanel a��l�rKutuluPanel = new JPanel(); // Varsay�l� FlowLayout/Ak��Serilimi kullan�lacak...
        String a��l�rKutuBirimleri[] = { D��ME_PANEL�, MET�NSATIRI_PANEL� };
        JComboBox a��l�rKutu = new JComboBox (a��l�rKutuBirimleri);
        a��l�rKutu.setEditable (false); // M�dahalesiz...
        a��l�rKutu.addItemListener (this); // Dinleyiciye duyarl�...
        a��l�rKutuluPanel.add (a��l�rKutu);
        a��l�rKutuluPanel.setBackground (Color.getHSBColor (0.27f, 0.84f, 0.45f)); // HueSaturationBrightness...

        JPanel kartl�Panel1 = new JPanel();
        kartl�Panel1.add (new JButton ("D��me 1"));
        kartl�Panel1.add (new JButton ("D��me 2"));
        kartl�Panel1.add (new JButton ("D��me 3"));
        kartl�Panel1.setBackground (Color.getHSBColor (0.75f, 0.84f, 0.65f));

        JPanel kartl�Panel2 = new JPanel();
        kartl�Panel2.add (new JTextField ("Metin Sat�r�", 20));
        kartl�Panel2.setBackground (Color.getHSBColor (0.91f, 0.84f, 0.85f));

        kartPaneli = new JPanel (new CardLayout());
        kartPaneli.add (kartl�Panel1, D��ME_PANEL�);
        kartPaneli.add (kartl�Panel2, MET�NSATIRI_PANEL�);

        kab.add (a��l�rKutuluPanel, BorderLayout.PAGE_START);
        kab.add (kartPaneli, BorderLayout.CENTER);
    } // kabaKomponentleriEkle(..) metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        CardLayout kartSerilim = (CardLayout)(kartPaneli.getLayout());
        kartSerilim.show (kartPaneli, (String)olay.getItem());
    } // itemStateChanged(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kart Serilimi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_6 g�steri = new J5f_6(); // Varsay�l� kurucuyu �a��r�r...
        g�steri.kabaKomponentleriEkle (�er�eve.getContentPane());
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace();
        }catch (InstantiationException ist) {ist.printStackTrace();
        }catch (ClassNotFoundException ist) {ist.printStackTrace();
        } // try-catch blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_6 s�n�f� sonu...