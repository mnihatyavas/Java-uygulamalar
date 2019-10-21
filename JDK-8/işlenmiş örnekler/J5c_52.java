// J5c_52.java: SpinnerDemo2 (SayaçGösterisi2) örneði.

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* Gerekli java dosyalarý:
 *   J5c_51.java=SpinnerDemo.java
 *   J5c_51x1=CyclingSpinnerListModel.java
 *   J5c_51x2=SpringUtilities.java
 */
public class J5c_52 extends J5c_51 {
// Tek fark ayYýlýEtkilesinMi=true deðiþikliðidir...
    public J5c_52() {super (true);} // Kurucu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sayaç Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_52()); // Kurucuyu çaðýr...
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazýlýmý kapat...
                yaratVeGösterGUI();
            } // run() hazýr sicim metodu sonu...
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_52 sýnýfý sonu...