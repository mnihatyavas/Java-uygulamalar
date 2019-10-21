// J5c_52.java: SpinnerDemo2 (Saya�G�sterisi2) �rne�i.

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* Gerekli java dosyalar�:
 *   J5c_51.java=SpinnerDemo.java
 *   J5c_51x1=CyclingSpinnerListModel.java
 *   J5c_51x2=SpringUtilities.java
 */
public class J5c_52 extends J5c_51 {
// Tek fark ayY�l�EtkilesinMi=true de�i�ikli�idir...
    public J5c_52() {super (true);} // Kurucu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Saya� G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_52()); // Kurucuyu �a��r...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz�l�m� kapat...
                yaratVeG�sterGUI();
            } // run() haz�r sicim metodu sonu...
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_52 s�n�f� sonu...