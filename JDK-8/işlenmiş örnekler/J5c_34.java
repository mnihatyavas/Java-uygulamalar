// J5c_34.java: MenuGlueDemo (Men�Zamk�G�sterisi) �rne�i.

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.Box;

public class J5c_34 {
    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        men��ubu�u.add (men�Yarat ("Men� 1"));
        men��ubu�u.add (men�Yarat ("Men� 2"));
        men��ubu�u.add (Box.createHorizontalGlue()); // Araya zamk koy...
        men��ubu�u.add (men�Yarat ("Men� 3"));
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    public JMenu men�Yarat (String ba�l�k) {
        JMenu men� = new JMenu (ba�l�k);
        men�.add (ba�l�k + " i�indeki 1.men� birimi");
        men�.add (ba�l�k + " i�indeki 2.men� birimi");
        men�.add (ba�l�k + " i�indeki 3.men� birimi");
        return men�;
    } // men�Yarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Men� Zamk� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_34 g�steri = new J5c_34();
        �er�eve.setContentPane (g�steri.men��ubu�unuYarat());
        �er�eve.setBounds (500,100, 300,70);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_34 s�n�f� sonu...