// J5c_35.java: MenuLayoutDemo (MenüYerleþtirmeGösterisi) örneði.

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

public class J5c_35 {
    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // Menü çubuðu dikey yerleþtiriliyor; ardýþýk menüler alt-alta sýralanacak...
        menüÇubuðu.setLayout (new BoxLayout (menüÇubuðu, BoxLayout.PAGE_AXIS));
        menüÇubuðu.add (menüYarat ("Menü 1"));
        menüÇubuðu.add (menüYarat ("Menü 2"));
        menüÇubuðu.add (menüYarat ("Menü 3"));

        menüÇubuðu.setBorder (BorderFactory.createMatteBorder (0,0,0,1,Color.BLACK)); // Sýnýrlar:üst-alt-sol-sað/0-0-0-1...
        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    public JMenu menüYarat (String baþlýk) {
        JMenu menü = new YatayMenü (baþlýk);
        menü.add (baþlýk + " içindeki 1.menü birimi");
        menü.add (baþlýk + " içindeki 2.menü birimi");
        menü.add (baþlýk + " içindeki 3.menü birimi");

        JMenu altMenü = new YatayMenü ("Altmenü");
        altMenü.add ("1.altmenü birimi");
        altMenü.add ("2.altmenü birimi");
        menü.add (altMenü);

        return menü;
    } // menüYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Menü Yerleþtirme Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_35 gösteri = new J5c_35();
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        içerikPanosu.add (gösteri.menüÇubuðunuYarat(), BorderLayout.LINE_START);
        çerçeve.setBounds (500,100, 300,100);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...

    class YatayMenü extends JMenu {
        YatayMenü (String etiket) {
            super (etiket);
            JPopupMenu fýrlayanMenü = getPopupMenu();
            fýrlayanMenü.setLayout (new BoxLayout (fýrlayanMenü, BoxLayout.LINE_AXIS));
        } // YatayMenü(..) kurucusu sonu...

        public Dimension getMinimumSize() {return getPreferredSize();}
        public Dimension getMaximumSize() {return getPreferredSize();}

        public void setPopupMenuVisible (boolean b) {
            boolean görünsünMü = isPopupMenuVisible();
            if (b != görünsünMü) {
                if ((b==true) && isShowing()) {
                    int x = 0;
                    int y = 0;
                    Container ebeveyn = getParent();
                    if (ebeveyn instanceof JPopupMenu) {
                        x = 0;
                        y = getHeight();
                    }else {
                        x = getWidth();
                        y = 0;
                    } // else-21 kararý sonu...
                    getPopupMenu().show (this, x, y);
                }else getPopupMenu().setVisible (false);
            } // if-13 kararý sonu...
        } // setPopupMenuVisible(..) hazýr metodu sonu...
    } // YatayMenü sýnýfý sonu...
} // 5c_34 sýnýfý sonu...