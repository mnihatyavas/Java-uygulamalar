// J5i_5.java: FocusEventDemo (OdaklanmaOlayýGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.BorderFactory;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.Vector;

public class J5i_5 extends JFrame implements FocusListener {
    final static String yeniSatýr = "\n";
    JTextArea görüntüMetinalaný;

    public J5i_5 (String baþlýk) {super (baþlýk);} // Kurucu...

    public void parçalarýKabaEkle (final Container kab) {
        GridBagLayout ýzgaraÇantaSerilim = new GridBagLayout();
        kab.setLayout (ýzgaraÇantaSerilim);

        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        sýnýrlayýcýlar.fill = GridBagConstraints.HORIZONTAL;
        sýnýrlayýcýlar.weightx = 1.0; //  Kolonu verili geniþliðe esnet...
        JTextField metinSatýrý = new JTextField ("JTextField");
        metinSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinSatýrý.setMargin (new Insets (0,2,0,2)); // Sol-üst-sað-alt tamponlar...
        metinSatýrý.addFocusListener (this); // Dinleyiciye duyarlý...
        ýzgaraÇantaSerilim.setConstraints (metinSatýrý, sýnýrlayýcýlar);
        add (metinSatýrý);

        sýnýrlayýcýlar.weightx = 0.1; // Her yatay parça eklentisini kolona sýðdýr...
        sýnýrlayýcýlar.fill = GridBagConstraints.NONE;
        JLabel etiket = new JLabel ("JLabel");
        etiket.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        etiket.setBorder (BorderFactory.createEmptyBorder (0,5,0,5)); // Sol-üst-sað-alt tamponlar...
        etiket.addFocusListener (this); // Dinleyiciye duyarlý; ancak etikete odaklanýlamaz!...
        ýzgaraÇantaSerilim.setConstraints (etiket, sýnýrlayýcýlar);
        add (etiket);

        String açýlýrÖneki = "JComboBox Birim #";
        final int adet = 15;
        Vector<String> vektörListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vektörListesi.addElement (açýlýrÖneki + i);
        JComboBox açýlýrKutu = new JComboBox (vektörListesi);
        açýlýrKutu.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        açýlýrKutu.setSelectedIndex (1);
        açýlýrKutu.addFocusListener (this); // Dinleyiciye duyarlý...
        ýzgaraÇantaSerilim.setConstraints (açýlýrKutu, sýnýrlayýcýlar);
        add (açýlýrKutu);

        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER; // Kolona son parça...
        JButton düðme = new JButton ("JButton");
        düðme.addFocusListener (this); // Dinleyiciye Duyarlý...
        ýzgaraÇantaSerilim.setConstraints (düðme, sýnýrlayýcýlar);
        add (düðme);

        sýnýrlayýcýlar.weightx = 0.0; // Yatay tek komponent...
        sýnýrlayýcýlar.weighty = 0.1; // Satýr yüksekliði esnek...
        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        String listeÖneki = "JList Birim #";
        vektörListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vektörListesi.addElement (listeÖneki + i);
        JList liste = new JList (vektörListesi);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        liste.setSelectedIndex (1); // Varsayýlý seçili liste birimi...
        liste.addFocusListener (this); // Dinleyiciye duyarlý...
        JScrollPane listeKaydýrmasý = new JScrollPane (liste);
        ýzgaraÇantaSerilim.setConstraints (listeKaydýrmasý, sýnýrlayýcýlar);
        add (listeKaydýrmasý);

        sýnýrlayýcýlar.weighty = 1.0; // Bu satýr olabildiðince yüksek...
        sýnýrlayýcýlar.gridheight = GridBagConstraints.REMAINDER; // Son satýr parçasý...
        görüntüMetinalaný = new JTextArea();
        görüntüMetinalaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        görüntüMetinalaný.setForeground (Color.WHITE);
        görüntüMetinalaný.setEditable (false); // Müdahalesiz...
        görüntüMetinalaný.setRequestFocusEnabled (false); // Odaklanýlmaya kapalý...
        görüntüMetinalaný.addFocusListener (this); // Dinleyiciye duyarlý; ancak JLabel gibi müdahalesiz olduðundan odak duyarsýz...
        JScrollPane görüntüKaydýrmasý = new JScrollPane (görüntüMetinalaný);
        ýzgaraÇantaSerilim.setConstraints (görüntüKaydýrmasý, sýnýrlayýcýlar);
        add (görüntüKaydýrmasý);

        setPreferredSize (new Dimension (450, 450));
        ((JPanel)kab).setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // parçalarýKabaEkle(..) metodu sonu...

    public void focusGained (FocusEvent olay) {mesaj ("Odaklanýldý", olay);}
    public void focusLost (FocusEvent olay) {mesaj ("OdakSIZlanýldý", olay);}

    void mesaj (String önek, FocusEvent olay) {
        görüntüMetinalaný.append (önek
                + (olay.isTemporary() ? " (geçici):" : ": ")
                + olay.getComponent().getClass().getName()
                + "; Ayrýlýnal parça: "
                + (olay.getOppositeComponent() != null ? olay.getOppositeComponent().getClass().getName() : "null")
                + yeniSatýr);
        görüntüMetinalaný.setCaretPosition (görüntüMetinalaný.getDocument().getLength());
    } // mesaj(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        J5i_5 çerçeve = new J5i_5 ("Odaklanma Olayý Gösterisi"); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.parçalarýKabaEkle (çerçeve.getContentPane());
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace(); // java.lang.*
        }catch (ClassNotFoundException ist) {ist.printStackTrace(); // java.lang.*
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_5 sýnýfý sonu...