// J5i_5.java: FocusEventDemo (OdaklanmaOlay�G�sterisi) �rne�i.

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
    final static String yeniSat�r = "\n";
    JTextArea g�r�nt�Metinalan�;

    public J5i_5 (String ba�l�k) {super (ba�l�k);} // Kurucu...

    public void par�alar�KabaEkle (final Container kab) {
        GridBagLayout �zgara�antaSerilim = new GridBagLayout();
        kab.setLayout (�zgara�antaSerilim);

        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        s�n�rlay�c�lar.fill = GridBagConstraints.HORIZONTAL;
        s�n�rlay�c�lar.weightx = 1.0; //  Kolonu verili geni�li�e esnet...
        JTextField metinSat�r� = new JTextField ("JTextField");
        metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinSat�r�.setMargin (new Insets (0,2,0,2)); // Sol-�st-sa�-alt tamponlar...
        metinSat�r�.addFocusListener (this); // Dinleyiciye duyarl�...
        �zgara�antaSerilim.setConstraints (metinSat�r�, s�n�rlay�c�lar);
        add (metinSat�r�);

        s�n�rlay�c�lar.weightx = 0.1; // Her yatay par�a eklentisini kolona s��d�r...
        s�n�rlay�c�lar.fill = GridBagConstraints.NONE;
        JLabel etiket = new JLabel ("JLabel");
        etiket.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        etiket.setBorder (BorderFactory.createEmptyBorder (0,5,0,5)); // Sol-�st-sa�-alt tamponlar...
        etiket.addFocusListener (this); // Dinleyiciye duyarl�; ancak etikete odaklan�lamaz!...
        �zgara�antaSerilim.setConstraints (etiket, s�n�rlay�c�lar);
        add (etiket);

        String a��l�r�neki = "JComboBox Birim #";
        final int adet = 15;
        Vector<String> vekt�rListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vekt�rListesi.addElement (a��l�r�neki + i);
        JComboBox a��l�rKutu = new JComboBox (vekt�rListesi);
        a��l�rKutu.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        a��l�rKutu.setSelectedIndex (1);
        a��l�rKutu.addFocusListener (this); // Dinleyiciye duyarl�...
        �zgara�antaSerilim.setConstraints (a��l�rKutu, s�n�rlay�c�lar);
        add (a��l�rKutu);

        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER; // Kolona son par�a...
        JButton d��me = new JButton ("JButton");
        d��me.addFocusListener (this); // Dinleyiciye Duyarl�...
        �zgara�antaSerilim.setConstraints (d��me, s�n�rlay�c�lar);
        add (d��me);

        s�n�rlay�c�lar.weightx = 0.0; // Yatay tek komponent...
        s�n�rlay�c�lar.weighty = 0.1; // Sat�r y�ksekli�i esnek...
        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        String liste�neki = "JList Birim #";
        vekt�rListesi = new Vector<String>(adet);
        for (int i = 0; i < adet; i++) vekt�rListesi.addElement (liste�neki + i);
        JList liste = new JList (vekt�rListesi);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        liste.setSelectedIndex (1); // Varsay�l� se�ili liste birimi...
        liste.addFocusListener (this); // Dinleyiciye duyarl�...
        JScrollPane listeKayd�rmas� = new JScrollPane (liste);
        �zgara�antaSerilim.setConstraints (listeKayd�rmas�, s�n�rlay�c�lar);
        add (listeKayd�rmas�);

        s�n�rlay�c�lar.weighty = 1.0; // Bu sat�r olabildi�ince y�ksek...
        s�n�rlay�c�lar.gridheight = GridBagConstraints.REMAINDER; // Son sat�r par�as�...
        g�r�nt�Metinalan� = new JTextArea();
        g�r�nt�Metinalan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        g�r�nt�Metinalan�.setForeground (Color.WHITE);
        g�r�nt�Metinalan�.setEditable (false); // M�dahalesiz...
        g�r�nt�Metinalan�.setRequestFocusEnabled (false); // Odaklan�lmaya kapal�...
        g�r�nt�Metinalan�.addFocusListener (this); // Dinleyiciye duyarl�; ancak JLabel gibi m�dahalesiz oldu�undan odak duyars�z...
        JScrollPane g�r�nt�Kayd�rmas� = new JScrollPane (g�r�nt�Metinalan�);
        �zgara�antaSerilim.setConstraints (g�r�nt�Kayd�rmas�, s�n�rlay�c�lar);
        add (g�r�nt�Kayd�rmas�);

        setPreferredSize (new Dimension (450, 450));
        ((JPanel)kab).setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // par�alar�KabaEkle(..) metodu sonu...

    public void focusGained (FocusEvent olay) {mesaj ("Odaklan�ld�", olay);}
    public void focusLost (FocusEvent olay) {mesaj ("OdakSIZlan�ld�", olay);}

    void mesaj (String �nek, FocusEvent olay) {
        g�r�nt�Metinalan�.append (�nek
                + (olay.isTemporary() ? " (ge�ici):" : ": ")
                + olay.getComponent().getClass().getName()
                + "; Ayr�l�nal par�a: "
                + (olay.getOppositeComponent() != null ? olay.getOppositeComponent().getClass().getName() : "null")
                + yeniSat�r);
        g�r�nt�Metinalan�.setCaretPosition (g�r�nt�Metinalan�.getDocument().getLength());
    } // mesaj(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        J5i_5 �er�eve = new J5i_5 ("Odaklanma Olay� G�sterisi"); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.par�alar�KabaEkle (�er�eve.getContentPane());
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {ist.printStackTrace();
        }catch (IllegalAccessException ist) {ist.printStackTrace(); // java.lang.*
        }catch (InstantiationException ist) {ist.printStackTrace(); // java.lang.*
        }catch (ClassNotFoundException ist) {ist.printStackTrace(); // java.lang.*
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_5 s�n�f� sonu...