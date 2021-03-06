// J5e_7.java: FocusTraversalDemo (OdaklanmaGeçişiGösterisi) örneği.

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
    JCheckBox odaklanmaÇentiği;
    static OdaklanmaGeçişiPolitikam yeniPolitika;

    public J5e_7() {// Kurucu...
        super (new BorderLayout());

        JTextField metinSatırı1 = new JTextField ("JTF 1");
        JTextField metinSatırı2 = new JTextField ("JTextField 2");
        JTextField metinSatırı3 = new JTextField ("JTF 3");
        JTextField metinSatırı4 = new JTextField ("JTextField 4");
        JTextField metinSatırı5 = new JTextField ("JTF 5");
        JTextField metinSatırı6 = new JTextField ("JTextField 6");

        JTable tablo = new JTable (4, 3);

        odaklanmaÇentiği = new JCheckBox ("Kullanıcının Kendi Özel Odaklanma Geçişi Politikası");
        odaklanmaÇentiği.setActionCommand ("açKapa");
        odaklanmaÇentiği.addActionListener (this); // Dinleyiciye duyarlıyoruz...
        odaklanmaÇentiği.setFocusable (false);  // Çentik kutusu odaklanma döngüsüne tabi değil...

        // HTML ve p elementleriyle 2 satırlık açıklama girelim...
        etiket = new JLabel ("<html>Komponent'ten komponent'e geçiş için Tab (veya Shift-Tab) kullanın.<p>Tablo'ya direk giriş/çıkış için Ctrl-Tab (veya Ctrl-Shift-Tab) kullanın.</html>");

        JPanel solMetinSatırıPaneli = new JPanel (new GridLayout (3, 0));
        solMetinSatırıPaneli.add (metinSatırı1, BorderLayout.PAGE_START);
        solMetinSatırıPaneli.add (metinSatırı3, BorderLayout.CENTER);
        solMetinSatırıPaneli.add (metinSatırı5, BorderLayout.PAGE_END);
        solMetinSatırıPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel sağMetinSatırıPaneli = new JPanel (new GridLayout (3, 0));
        sağMetinSatırıPaneli.add (metinSatırı2, BorderLayout.PAGE_START);
        sağMetinSatırıPaneli.add (metinSatırı4, BorderLayout.CENTER);
        sağMetinSatırıPaneli.add (metinSatırı6, BorderLayout.PAGE_END);
        sağMetinSatırıPaneli.setBorder (BorderFactory.createEmptyBorder (5,5,5,5));

        JPanel tabloPaneli = new JPanel (new GridLayout (0, 1));
        tabloPaneli.add (tablo, BorderLayout.CENTER);
        tabloPaneli.setBorder (BorderFactory.createEtchedBorder());

        JPanel altPanel = new JPanel (new GridLayout (2, 0));
        altPanel.add (odaklanmaÇentiği, BorderLayout.PAGE_START);
        altPanel.add (etiket, BorderLayout.PAGE_END);

        add (solMetinSatırıPaneli, BorderLayout.LINE_START);
        add (sağMetinSatırıPaneli, BorderLayout.CENTER);
        add (tabloPaneli, BorderLayout.LINE_END);
        add (altPanel, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu çerçeve rengi...

        Vector<Component> vektörDüzeni = new Vector<Component>(7);
        vektörDüzeni.add (metinSatırı1);
        vektörDüzeni.add (metinSatırı2);
        vektörDüzeni.add (metinSatırı3);
        vektörDüzeni.add (metinSatırı4);
        vektörDüzeni.add (metinSatırı5);
        vektörDüzeni.add (metinSatırı6);
        vektörDüzeni.add (tablo);
        yeniPolitika = new OdaklanmaGeçişiPolitikam (vektörDüzeni);
    } // J5e_7() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if ("açKapa".equals (olay.getActionCommand()))
            çerçeve.setFocusTraversalPolicy (odaklanmaÇentiği.isSelected() ? yeniPolitika : null);
    } // actionPerformed(..) hazır metodu sonu...

    public static class OdaklanmaGeçişiPolitikam extends FocusTraversalPolicy {
        Vector<Component> vektörDüzeni;

        public OdaklanmaGeçişiPolitikam (Vector<Component> vektörDüzeni) {
            this.vektörDüzeni = new Vector<Component> (vektörDüzeni.size());
            this.vektörDüzeni.addAll (vektörDüzeni);
        } // OdaklanmaGeçişiPolitikam(..) kurucusu sonu...

        public Component getComponentAfter (Container kab, Component komponent) {
            int endeks = (vektörDüzeni.indexOf (komponent) + 1) % vektörDüzeni.size();
            return vektörDüzeni.get (endeks);
        } // getComponentAfter(..) hazır metodu sonu...

        public Component getComponentBefore (Container kab, Component komponent) {
            int endeks = vektörDüzeni.indexOf (komponent) - 1;
            if (endeks < 0) endeks = vektörDüzeni.size() - 1;
            return vektörDüzeni.get(endeks);
        } // getComponentBefore(..) hazır metodu sonu...

        public Component getDefaultComponent (Container kab) {return vektörDüzeni.get (0);}
        public Component getLastComponent (Container kab) {return vektörDüzeni.lastElement();}
        public Component getFirstComponent (Container kab) {return vektörDüzeni.get (0);}
    } // OdaklanmaGeçişiPolitikam sınıfı sonu...

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Odaklanma Geçişi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniİçerikPanosu = new J5e_7(); // Kurucuyu çağırır...
        yeniİçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniİçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        try {// Uygun ve mevcut bir LookAndFeel/BakVeHisset sınıfı seçelim...
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
        } // try-catch.. bloğu sonu...
        UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazı kullanılmayacak...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_7 sınıfı sonu...