// J5e_6.java: FocusConceptsDemo (OdaklanmaKavramlarýGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

public class J5e_6 extends JPanel {
    static JFrame çerçeve;
    JTextField metinSatýrý1, metinSatýrý2, metinSatýrý3, metinSatýrý4;
    JButton düðme1, düðme2, düðme3, düðme4;
    JTextArea metinAlaný;

    public J5e_6() {// Kurucu...
        super (new BorderLayout());

        düðme1 = new JButton ("JButton"); düðme1.setBackground (Color.BLACK); düðme1.setForeground (Color.WHITE);
        düðme2 = new JButton ("JButton"); düðme2.setBackground (Color.BLACK); düðme2.setForeground (Color.WHITE);
        düðme3 = new JButton ("JButton"); düðme3.setBackground (Color.BLACK); düðme3.setForeground (Color.WHITE);
        düðme4 = new JButton ("JButton"); düðme4.setBackground (Color.BLACK); düðme4.setForeground (Color.WHITE);
        JPanel düðmePaneli = new JPanel (new GridLayout (1,1));
        düðmePaneli.add (düðme1);
        düðmePaneli.add (düðme2);
        düðmePaneli.add (düðme3);
        düðmePaneli.add (düðme4);

        metinAlaný = new JTextArea ("JTextArea", 15, 40); metinAlaný.setBackground (Color.CYAN); metinAlaný.setForeground (Color.MAGENTA);
        JScrollPane kaydýrma = new JScrollPane (metinAlaný);
        JPanel metinAlanýPaneli = new JPanel (new BorderLayout());
        metinAlanýPaneli.add (kaydýrma, BorderLayout.CENTER);

        metinSatýrý1 = new JTextField ("JTextField"); metinSatýrý1.setBackground (Color.RED); metinSatýrý1.setForeground (Color.YELLOW);
        metinSatýrý2 = new JTextField ("JTextField"); metinSatýrý2.setBackground (Color.RED); metinSatýrý2.setForeground (Color.YELLOW);
        metinSatýrý3 = new JTextField ("JTextField"); metinSatýrý3.setBackground (Color.RED); metinSatýrý3.setForeground (Color.YELLOW);
        metinSatýrý4 = new JTextField ("JTextField"); metinSatýrý4.setBackground (Color.RED); metinSatýrý4.setForeground (Color.YELLOW);
        JPanel metinSatýrýPaneli = new JPanel (new GridLayout (1,1));
        metinSatýrýPaneli.add (metinSatýrý1);
        metinSatýrýPaneli.add (metinSatýrý2);
        metinSatýrýPaneli.add (metinSatýrý3);
        metinSatýrýPaneli.add (metinSatýrý4);

        add (düðmePaneli, BorderLayout.PAGE_START);
        add (metinAlanýPaneli, BorderLayout.CENTER);
        add (metinSatýrýPaneli, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5e_6() kurucusu sonu...

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Odaklanma Kavramlarý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5e_6(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        // Uygun Look and Feel (Bak ve Hisset)'i seçelim...
        try {
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel ("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel ("javax.swing.plaf.metal.MetalLookAndFeel");
        }catch (UnsupportedLookAndFeelException ist) {// javax.swing...
            ist.printStackTrace();
        }catch (IllegalAccessException ist) {// java.lang...
            ist.printStackTrace();
        }catch (InstantiationException ist) {// java.lang...
            ist.printStackTrace();
        }catch (ClassNotFoundException ist) {// java.lang...
            ist.printStackTrace();
        } // try-catch.. bloðu sonu...

        // javax.swing sýnýfýnýn koyu metal yazý kullanýmýný kapatalým...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);

        // GUI uygulamasýný raporlamalý sicim run metoduyla planlayalým...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_6 sýnýfý sonu...