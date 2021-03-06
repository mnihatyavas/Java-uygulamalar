// J5i_5.java: FocusEventDemo (OdaklanmaOlayıGösterisi) örneği.

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
    final static String yeniSatır = "\n";
    JTextArea görüntüMetinalanı;

    public J5i_5 (String başlık) {super (başlık);} // Kurucu...

    public void parçalarıKabaEkle (final Container kab) {
        GridBagLayout ızgaraÇantaSerilim = new GridBagLayout();
        kab.setLayout (ızgaraÇantaSerilim);

        GridBagConstraints sınırlayıcılar = new GridBagConstraints();

        sınırlayıcılar.fill = GridBagConstraints.HORIZONTAL;
        sınırlayıcılar.weightx = 1.0; //  Kolonu verili genişliğe esnet...
        JTextField metinSatırı = new JTextField ("JTextField");
        metinSatırı.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinSatırı.setMargin (new Insets (0,2,0,2)); // Sol-üst-sağ-alt tamponlar...
        metinSatırı.addFocusListener (this); // Dinleyiciye duyarlı...
        ızgaraÇantaSerilim.setConstraints (metinSatırı, sınırlayıcılar);
        add (metinSatırı);

        sınırlayıcılar.weightx = 0.1; // Her yatay parça eklentisini kolona sığdır...
        sınırlayıcılar.fill = GridBagConstraints.NONE;
        JLabel etiket = new JLabel ("JLabel");
        etiket.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        etiket.setBorder (BorderFactory.createEmptyBorder (0,5,0,5)); // Sol-üst-sağ-alt tamponlar...
        etiket.addFocusListener (this); // Dinleyiciye duyarlı; ancak etikete odaklanılamaz!...
        ızgaraÇantaSerilim.setConstraints (etiket, sınırlayıcılar);
        add (etiket);

        String açılırÖneki = "JComboBox Birim #";
        final int adet = 15;
        Vector<String> vektörListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vektörListesi.addElement (açılırÖneki + i);
        JComboBox açılırKutu = new JComboBox (vektörListesi);
        açılırKutu.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        açılırKutu.setSelectedIndex (1);
        açılırKutu.addFocusListener (this); // Dinleyiciye duyarlı...
        ızgaraÇantaSerilim.setConstraints (açılırKutu, sınırlayıcılar);
        add (açılırKutu);

        sınırlayıcılar.gridwidth = GridBagConstraints.REMAINDER; // Kolona son parça...
        JButton düğme = new JButton ("JButton");
        düğme.addFocusListener (this); // Dinleyiciye Duyarlı...
        ızgaraÇantaSerilim.setConstraints (düğme, sınırlayıcılar);
        add (düğme);

        sınırlayıcılar.weightx = 0.0; // Yatay tek komponent...
        sınırlayıcılar.weighty = 0.1; // Satır yüksekliği esnek...
        sınırlayıcılar.fill = GridBagConstraints.BOTH;
        String listeÖneki = "JList Birim #";
        vektörListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vektörListesi.addElement (listeÖneki + i);
        JList liste = new JList (vektörListesi);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        liste.setSelectedIndex (1); // Varsayılı seçili liste birimi...
        liste.addFocusListener (this); // Dinleyiciye duyarlı...
        JScrollPane listeKaydırması = new JScrollPane (liste);
        ızgaraÇantaSerilim.setConstraints (listeKaydırması, sınırlayıcılar);
        add (listeKaydırması);

        sınırlayıcılar.weighty = 1.0; // Bu satır olabildiğince yüksek...
        sınırlayıcılar.gridheight = GridBagConstraints.REMAINDER; // Son satır parçası...
        görüntüMetinalanı = new JTextArea();
        görüntüMetinalanı.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        görüntüMetinalanı.setForeground (Color.WHITE);
        görüntüMetinalanı.setEditable (false); // Müdahalesiz...
        görüntüMetinalanı.setRequestFocusEnabled (false); // Odaklanılmaya kapalı...
        görüntüMetinalanı.addFocusListener (this); // Dinleyiciye duyarlı; ancak JLabel gibi müdahalesiz olduğundan odak duyarsız...
        JScrollPane görüntüKaydırması = new JScrollPane (görüntüMetinalanı);
        ızgaraÇantaSerilim.setConstraints (görüntüKaydırması, sınırlayıcılar);
        add (görüntüKaydırması);

        setPreferredSize (new Dimension (450, 450));
        ((JPanel)kab).setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // parçalarıKabaEkle(..) metodu sonu...

    public void focusGained (FocusEvent olay) {mesaj ("Odaklanıldı", olay);}
    public void focusLost (FocusEvent olay) {mesaj ("OdakSIZlanıldı", olay);}

    void mesaj (String önek, FocusEvent olay) {
        görüntüMetinalanı.append (önek
                + (olay.isTemporary() ? " (geçici):" : ": ")
                + olay.getComponent().getClass().getName()
                + "; Ayrılınal parça: "
                + (olay.getOppositeComponent() != null ? olay.getOppositeComponent().getClass().getName() : "null")
                + yeniSatır);
        görüntüMetinalanı.setCaretPosition (görüntüMetinalanı.getDocument().getLength());
    } // mesaj(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        J5i_5 çerçeve = new J5i_5 ("Odaklanma Olayı Gösterisi"); // Kurucuyu çağırır...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.parçalarıKabaEkle (çerçeve.getContentPane());
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
        } // try-catch.. bloğu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_5 sınıfı sonu...