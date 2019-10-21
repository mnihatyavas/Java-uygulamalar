// J5e_7.java: FocusTraversalDemo (OdaklanmaGeçiþiGösterisi) örneði.

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
    static JFrame çerçeve;
    JLabel etiket;
    JCheckBox odaklanmaÇentiði;
    static OdaklanmaGeçiþiPolitikam yeniPolitika;

    public J5e_7() {// Kurucu...
        super (new BorderLayout());

        JTextField metinSatýrý1 = new JTextField ("JTF 1");
        JTextField metinSatýrý2 = new JTextField ("JTextField 2");
        JTextField metinSatýrý3 = new JTextField ("JTF 3");
        JTextField metinSatýrý4 = new JTextField ("JTextField 4");
        JTextField metinSatýrý5 = new JTextField ("JTF 5");
        JTextField metinSatýrý6 = new JTextField ("JTextField 6");

        JTable tablo = new JTable (4, 3);

        odaklanmaÇentiði = new JCheckBox ("Kullanýcýnýn Kendi Özel Odaklanma Geçiþi Politikasý");
        odaklanmaÇentiði.setActionCommand ("açKapa");
        odaklanmaÇentiði.addActionListener (this); // Dinleyiciye duyarlýyoruz...
        odaklanmaÇentiði.setFocusable (false);  // Çentik kutusu odaklanma döngüsüne tabi deðil...

        // HTML ve p elementleriyle 2 satýrlýk açýklama girelim...
        etiket = new JLabel ("<html>Komponent'ten komponent'e geçiþ için Tab (veya Shift-Tab) kullanýn.<p>Tablo'ya direk giriþ/çýkýþ için Ctrl-Tab (veya Ctrl-Shift-Tab) kullanýn.</html>");

        JPanel solMetinSatýrýPaneli = new JPanel (new GridLayout (3, 0));
        solMetinSatýrýPaneli.add (metinSatýrý1, BorderLayout.PAGE_START);
        solMetinSatýrýPaneli.add (metinSatýrý3, BorderLayout.CENTER);
        solMetinSatýrýPaneli.add (metinSatýrý5, BorderLayout.PAGE_END);
        solMetinSatýrýPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel saðMetinSatýrýPaneli = new JPanel (new GridLayout (3, 0));
        saðMetinSatýrýPaneli.add (metinSatýrý2, BorderLayout.PAGE_START);
        saðMetinSatýrýPaneli.add (metinSatýrý4, BorderLayout.CENTER);
        saðMetinSatýrýPaneli.add (metinSatýrý6, BorderLayout.PAGE_END);
        saðMetinSatýrýPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel tabloPaneli = new JPanel (new GridLayout (0, 1));
        tabloPaneli.add (tablo, BorderLayout.CENTER);
        tabloPaneli.setBorder (BorderFactory.createEtchedBorder());

        JPanel altPanel = new JPanel (new GridLayout (2, 0));
        altPanel.add (odaklanmaÇentiði, BorderLayout.PAGE_START);
        altPanel.add (etiket, BorderLayout.PAGE_END);

        add (solMetinSatýrýPaneli, BorderLayout.LINE_START);
        add (saðMetinSatýrýPaneli, BorderLayout.CENTER);
        add (tabloPaneli, BorderLayout.LINE_END);
        add (altPanel, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu çerçeve rengi...

        Vector<Component> vektörDüzeni = new Vector<Component>(7);
        vektörDüzeni.add (metinSatýrý1);
        vektörDüzeni.add (metinSatýrý2);
        vektörDüzeni.add (metinSatýrý3);
        vektörDüzeni.add (metinSatýrý4);
        vektörDüzeni.add (metinSatýrý5);
        vektörDüzeni.add (metinSatýrý6);
        vektörDüzeni.add (tablo);
        yeniPolitika = new OdaklanmaGeçiþiPolitikam (vektörDüzeni);
    } // J5e_7() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("açKapa".equals (olay.getActionCommand()))
            çerçeve.setFocusTraversalPolicy (odaklanmaÇentiði.isSelected() ? yeniPolitika : null);
    } // actionPerformed(..) hazýr metodu sonu...

    public static class OdaklanmaGeçiþiPolitikam extends FocusTraversalPolicy {
        Vector<Component> vektörDüzeni;

        public OdaklanmaGeçiþiPolitikam (Vector<Component> vektörDüzeni) {
            this.vektörDüzeni = new Vector<Component> (vektörDüzeni.size());
            this.vektörDüzeni.addAll (vektörDüzeni);
        } // OdaklanmaGeçiþiPolitikam(..) kurucusu sonu...

        public Component getComponentAfter (Container kab, Component komponent) {
            int endeks = (vektörDüzeni.indexOf (komponent) + 1) % vektörDüzeni.size();
            return vektörDüzeni.get (endeks);
        } // getComponentAfter(..) hazýr metodu sonu...

        public Component getComponentBefore (Container kab, Component komponent) {
            int endeks = vektörDüzeni.indexOf (komponent) - 1;
            if (endeks < 0) endeks = vektörDüzeni.size() - 1;
            return vektörDüzeni.get(endeks);
        } // getComponentBefore(..) hazýr metodu sonu...

        public Component getDefaultComponent (Container kab) {return vektörDüzeni.get (0);}
        public Component getLastComponent (Container kab) {return vektörDüzeni.lastElement();}
        public Component getFirstComponent (Container kab) {return vektörDüzeni.get (0);}
    } // OdaklanmaGeçiþiPolitikam sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Odaklanma Geçiþi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_7(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {// Uygun ve mevcut bir LookAndFeel/BakVeHisset sýnýfý seçelim...
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
        } // try-catch.. bloðu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazý kullanýlmayacak...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_7 sýnýfý sonu...