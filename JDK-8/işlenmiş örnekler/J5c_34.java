// J5c_34.java: MenuGlueDemo (MenüZamkıGösterisi) örneği.

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.Box;

public class J5c_34 {
    public JMenuBar menüÇubuğunuYarat() {
        JMenuBar menüÇubuğu = new JMenuBar();
        menüÇubuğu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        menüÇubuğu.add (menüYarat ("Menü 1"));
        menüÇubuğu.add (menüYarat ("Menü 2"));
        menüÇubuğu.add (Box.createHorizontalGlue()); // Araya zamk koy...
        menüÇubuğu.add (menüYarat ("Menü 3"));
        return menüÇubuğu;
    } // menüÇubuğunuYarat() metodu sonu...

    public JMenu menüYarat (String başlık) {
        JMenu menü = new JMenu (başlık);
        menü.add (başlık + " içindeki 1.menü birimi");
        menü.add (başlık + " içindeki 2.menü birimi");
        menü.add (başlık + " içindeki 3.menü birimi");
        return menü;
    } // menüYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Menü Zamkı Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_34 gösteri = new J5c_34();
        çerçeve.setContentPane (gösteri.menüÇubuğunuYarat());
        çerçeve.setBounds (500,100, 300,70);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_34 sınıfı sonu...