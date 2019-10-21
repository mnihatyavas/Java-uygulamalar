// J5c_35.java: MenuLayoutDemo (Men�Yerle�tirmeG�sterisi) �rne�i.

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
    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)));

        // Men� �ubu�u dikey yerle�tiriliyor; ard���k men�ler alt-alta s�ralanacak...
        men��ubu�u.setLayout (new BoxLayout (men��ubu�u, BoxLayout.PAGE_AXIS));
        men��ubu�u.add (men�Yarat ("Men� 1"));
        men��ubu�u.add (men�Yarat ("Men� 2"));
        men��ubu�u.add (men�Yarat ("Men� 3"));

        men��ubu�u.setBorder (BorderFactory.createMatteBorder (0,0,0,1,Color.BLACK)); // S�n�rlar:�st-alt-sol-sa�/0-0-0-1...
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    public JMenu men�Yarat (String ba�l�k) {
        JMenu men� = new YatayMen� (ba�l�k);
        men�.add (ba�l�k + " i�indeki 1.men� birimi");
        men�.add (ba�l�k + " i�indeki 2.men� birimi");
        men�.add (ba�l�k + " i�indeki 3.men� birimi");

        JMenu altMen� = new YatayMen� ("Altmen�");
        altMen�.add ("1.altmen� birimi");
        altMen�.add ("2.altmen� birimi");
        men�.add (altMen�);

        return men�;
    } // men�Yarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Men� Yerle�tirme G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_35 g�steri = new J5c_35();
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        i�erikPanosu.add (g�steri.men��ubu�unuYarat(), BorderLayout.LINE_START);
        �er�eve.setBounds (500,100, 300,100);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...

    class YatayMen� extends JMenu {
        YatayMen� (String etiket) {
            super (etiket);
            JPopupMenu f�rlayanMen� = getPopupMenu();
            f�rlayanMen�.setLayout (new BoxLayout (f�rlayanMen�, BoxLayout.LINE_AXIS));
        } // YatayMen�(..) kurucusu sonu...

        public Dimension getMinimumSize() {return getPreferredSize();}
        public Dimension getMaximumSize() {return getPreferredSize();}

        public void setPopupMenuVisible (boolean b) {
            boolean g�r�ns�nM� = isPopupMenuVisible();
            if (b != g�r�ns�nM�) {
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
                    } // else-21 karar� sonu...
                    getPopupMenu().show (this, x, y);
                }else getPopupMenu().setVisible (false);
            } // if-13 karar� sonu...
        } // setPopupMenuVisible(..) haz�r metodu sonu...
    } // YatayMen� s�n�f� sonu...
} // 5c_34 s�n�f� sonu...