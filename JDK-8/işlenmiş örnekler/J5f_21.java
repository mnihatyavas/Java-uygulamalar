// J5f_21.java: TabDemo (FiþGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

public class J5f_21 {
    final static int fazladanPencereGeniþliði = 100;

    public void parçalarýPanoyaEkle (Container kab) {
        JTabbedPane fiþliPano = new JTabbedPane();

        // Fiþli panel kartlarýný yaratalým...
        JPanel panelKartý1 = new JPanel() {
            // Tek satýra sýralanmasý için kart geniþliðini biraz geniþ tutalým...
            public Dimension getPreferredSize() {
                Dimension ebat = super.getPreferredSize();
                ebat.width += fazladanPencereGeniþliði;
                return ebat;
            } // getPreferredSize() hazýr metodu sonu...
        }; // JPa.. ifadesi sonu...
        panelKartý1.add (new JButton ("Düðme 1"));
        panelKartý1.add (new JButton ("Düðme 2"));
        panelKartý1.add (new JButton ("Düðme 3"));

        JPanel panelKartý2 = new JPanel();
        panelKartý2.add (new JTextField ("Metin Satýrý", 20));

        fiþliPano.addTab ("Düðmeli Panel Fiþi", panelKartý1);
        fiþliPano.addTab ("Metinsatýrlý Panel Fiþi", panelKartý2);

        panelKartý1.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        panelKartý2.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        fiþliPano.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        fiþliPano.setForeground (Color.RED);

        kab.add (fiþliPano, BorderLayout.CENTER);
    } // parçalarýPanoyaEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Fiþ Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_21 gösteri = new J5f_21(); // Varsayýlý kurucuyu çaðýrýr...
        gösteri.parçalarýPanoyaEkle (çerçeve.getContentPane());
        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception ist) {ist.printStackTrace();}
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5c_21 sýnýfý sonu...