// J5e_7.java: FocusTraversalDemo (OdaklanmaGe�i�iG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FocusTraversalPolicy;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.Vector;

public class J5e_7 extends JPanel implements ActionListener {
    static JFrame �er�eve;
    JLabel etiket;
    JCheckBox odaklanma�enti�i;
    static OdaklanmaGe�i�iPolitikam yeniPolitika;

    public J5e_7() {// Kurucu...
        super (new BorderLayout());

        JTextField metinSat�r�1 = new JTextField ("JTF 1");
        JTextField metinSat�r�2 = new JTextField ("JTextField 2");
        JTextField metinSat�r�3 = new JTextField ("JTF 3");
        JTextField metinSat�r�4 = new JTextField ("JTextField 4");
        JTextField metinSat�r�5 = new JTextField ("JTF 5");
        JTextField metinSat�r�6 = new JTextField ("JTextField 6");

        JTable tablo = new JTable (4, 3);

        odaklanma�enti�i = new JCheckBox ("Kullan�c�n�n Kendi �zel Odaklanma Ge�i�i Politikas�");
        odaklanma�enti�i.setActionCommand ("a�Kapa");
        odaklanma�enti�i.addActionListener (this); // Dinleyiciye duyarl�yoruz...
        odaklanma�enti�i.setFocusable (false);  // �entik kutusu odaklanma d�ng�s�ne tabi de�il...

        // HTML ve p elementleriyle 2 sat�rl�k a��klama girelim...
        etiket = new JLabel ("<html>Komponent'ten komponent'e ge�i� i�in Tab (veya Shift-Tab) kullan�n.<p>Tablo'ya direk giri�/��k�� i�in Ctrl-Tab (veya Ctrl-Shift-Tab) kullan�n.</html>");

        JPanel solMetinSat�r�Paneli = new JPanel (new GridLayout (3, 0));
        solMetinSat�r�Paneli.add (metinSat�r�1, BorderLayout.PAGE_START);
        solMetinSat�r�Paneli.add (metinSat�r�3, BorderLayout.CENTER);
        solMetinSat�r�Paneli.add (metinSat�r�5, BorderLayout.PAGE_END);
        solMetinSat�r�Paneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel sa�MetinSat�r�Paneli = new JPanel (new GridLayout (3, 0));
        sa�MetinSat�r�Paneli.add (metinSat�r�2, BorderLayout.PAGE_START);
        sa�MetinSat�r�Paneli.add (metinSat�r�4, BorderLayout.CENTER);
        sa�MetinSat�r�Paneli.add (metinSat�r�6, BorderLayout.PAGE_END);
        sa�MetinSat�r�Paneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel tabloPaneli = new JPanel (new GridLayout (0, 1));
        tabloPaneli.add (tablo, BorderLayout.CENTER);
        tabloPaneli.setBorder (BorderFactory.createEtchedBorder());

        JPanel altPanel = new JPanel (new GridLayout (2, 0));
        altPanel.add (odaklanma�enti�i, BorderLayout.PAGE_START);
        altPanel.add (etiket, BorderLayout.PAGE_END);

        add (solMetinSat�r�Paneli, BorderLayout.LINE_START);
        add (sa�MetinSat�r�Paneli, BorderLayout.CENTER);
        add (tabloPaneli, BorderLayout.LINE_END);
        add (altPanel, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu �er�eve rengi...

        Vector<Component> vekt�rD�zeni = new Vector<Component>(7);
        vekt�rD�zeni.add (metinSat�r�1);
        vekt�rD�zeni.add (metinSat�r�2);
        vekt�rD�zeni.add (metinSat�r�3);
        vekt�rD�zeni.add (metinSat�r�4);
        vekt�rD�zeni.add (metinSat�r�5);
        vekt�rD�zeni.add (metinSat�r�6);
        vekt�rD�zeni.add (tablo);
        yeniPolitika = new OdaklanmaGe�i�iPolitikam (vekt�rD�zeni);
    } // J5e_7() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("a�Kapa".equals (olay.getActionCommand()))
            �er�eve.setFocusTraversalPolicy (odaklanma�enti�i.isSelected() ? yeniPolitika : null);
    } // actionPerformed(..) haz�r metodu sonu...

    public static class OdaklanmaGe�i�iPolitikam extends FocusTraversalPolicy {
        Vector<Component> vekt�rD�zeni;

        public OdaklanmaGe�i�iPolitikam (Vector<Component> vekt�rD�zeni) {
            this.vekt�rD�zeni = new Vector<Component> (vekt�rD�zeni.size());
            this.vekt�rD�zeni.addAll (vekt�rD�zeni);
        } // OdaklanmaGe�i�iPolitikam(..) kurucusu sonu...

        public Component getComponentAfter (Container kab, Component komponent) {
            int endeks = (vekt�rD�zeni.indexOf (komponent) + 1) % vekt�rD�zeni.size();
            return vekt�rD�zeni.get (endeks);
        } // getComponentAfter(..) haz�r metodu sonu...

        public Component getComponentBefore (Container kab, Component komponent) {
            int endeks = vekt�rD�zeni.indexOf (komponent) - 1;
            if (endeks < 0) endeks = vekt�rD�zeni.size() - 1;
            return vekt�rD�zeni.get(endeks);
        } // getComponentBefore(..) haz�r metodu sonu...

        public Component getDefaultComponent (Container kab) {return vekt�rD�zeni.get (0);}
        public Component getLastComponent (Container kab) {return vekt�rD�zeni.lastElement();}
        public Component getFirstComponent (Container kab) {return vekt�rD�zeni.get (0);}
    } // OdaklanmaGe�i�iPolitikam s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        �er�eve = new JFrame ("Odaklanma Ge�i�i G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_7(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        try {// Uygun ve mevcut bir LookAndFeel/BakVeHisset s�n�f� se�elim...
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {// javax.swing.*
            ist.printStackTrace();
        }catch (IllegalAccessException ist) {// java.lang.*
            ist.printStackTrace();
        }catch (InstantiationException ist) {// java.lang.*
            ist.printStackTrace();
        }catch (ClassNotFoundException ist) {// java.lang.*
            ist.printStackTrace();
        } // try-catch.. blo�u sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz� kullan�lmayacak...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_7 s�n�f� sonu...