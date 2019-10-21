// J5e_6.java: FocusConceptsDemo (OdaklanmaKavramlar�G�sterisi) �rne�i.

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
    static JFrame �er�eve;
    JTextField metinSat�r�1, metinSat�r�2, metinSat�r�3, metinSat�r�4;
    JButton d��me1, d��me2, d��me3, d��me4;
    JTextArea metinAlan�;

    public J5e_6() {// Kurucu...
        super (new BorderLayout());

        d��me1 = new JButton ("JButton"); d��me1.setBackground (Color.BLACK); d��me1.setForeground (Color.WHITE);
        d��me2 = new JButton ("JButton"); d��me2.setBackground (Color.BLACK); d��me2.setForeground (Color.WHITE);
        d��me3 = new JButton ("JButton"); d��me3.setBackground (Color.BLACK); d��me3.setForeground (Color.WHITE);
        d��me4 = new JButton ("JButton"); d��me4.setBackground (Color.BLACK); d��me4.setForeground (Color.WHITE);
        JPanel d��mePaneli = new JPanel (new GridLayout (1,1));
        d��mePaneli.add (d��me1);
        d��mePaneli.add (d��me2);
        d��mePaneli.add (d��me3);
        d��mePaneli.add (d��me4);

        metinAlan� = new JTextArea ("JTextArea", 15, 40); metinAlan�.setBackground (Color.CYAN); metinAlan�.setForeground (Color.MAGENTA);
        JScrollPane kayd�rma = new JScrollPane (metinAlan�);
        JPanel metinAlan�Paneli = new JPanel (new BorderLayout());
        metinAlan�Paneli.add (kayd�rma, BorderLayout.CENTER);

        metinSat�r�1 = new JTextField ("JTextField"); metinSat�r�1.setBackground (Color.RED); metinSat�r�1.setForeground (Color.YELLOW);
        metinSat�r�2 = new JTextField ("JTextField"); metinSat�r�2.setBackground (Color.RED); metinSat�r�2.setForeground (Color.YELLOW);
        metinSat�r�3 = new JTextField ("JTextField"); metinSat�r�3.setBackground (Color.RED); metinSat�r�3.setForeground (Color.YELLOW);
        metinSat�r�4 = new JTextField ("JTextField"); metinSat�r�4.setBackground (Color.RED); metinSat�r�4.setForeground (Color.YELLOW);
        JPanel metinSat�r�Paneli = new JPanel (new GridLayout (1,1));
        metinSat�r�Paneli.add (metinSat�r�1);
        metinSat�r�Paneli.add (metinSat�r�2);
        metinSat�r�Paneli.add (metinSat�r�3);
        metinSat�r�Paneli.add (metinSat�r�4);

        add (d��mePaneli, BorderLayout.PAGE_START);
        add (metinAlan�Paneli, BorderLayout.CENTER);
        add (metinSat�r�Paneli, BorderLayout.PAGE_END);
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5e_6() kurucusu sonu...

    private static void yaratVeG�sterGUI() {
        �er�eve = new JFrame ("Odaklanma Kavramlar� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5e_6(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        // Uygun Look and Feel (Bak ve Hisset)'i se�elim...
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
        } // try-catch.. blo�u sonu...

        // javax.swing s�n�f�n�n koyu metal yaz� kullan�m�n� kapatal�m...
        UIManager.put ("swing.boldMetal", Boolean.FALSE);

        // GUI uygulamas�n� raporlamal� sicim run metoduyla planlayal�m...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_6 s�n�f� sonu...