// J5g_1.java: LookAndFeelDemo (BakVeHissetG�sterisi) �rne�i.

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.OceanTheme;

// Gereken dosya: J5g_1x.java=TestTheme.java
public class J5g_1 implements ActionListener {
    private static String etiket�neki = "D��menin t�klanma say�s�: ";
    private int t�klamaSay�s� = 0;
    final JLabel etiket = new JLabel (etiket�neki + "0");

    // BakVeHisset ge�erli sabiteleri: null (varsay�l�), "Metal", "System", "Motif" ve "GTK"
    static String BAKVEH�SSET = null;

    // "Metal" se�ti�inizde 3 tema tercihiniz var: "DefaultMetal", "Ocean" ve "�zel"
    static String TEMA = null;

    public Component par�alar�Yarat() {
        JButton d��me = new JButton ("Ben bir javax.swing.JButton d��mesiyim (Alt_D)!");
        d��me.setMnemonic (KeyEvent.VK_D); // Alt_D k�sayol tu�unu d��meye ba�layal�m...
        d��me.addActionListener (this); // D��meyi dinleyiciye duyarlayal�m...
        etiket.setLabelFor (d��me); // Etiketle d��meyi birliktelikleyelim...

         // Par�alar� tek �zgara kolonlu panele alt-alta koyup �evresini tampolayal�m...
        JPanel panel = new JPanel (new GridLayout (0, 1));
        panel.add (d��me);
        panel.add (etiket);
        panel.setBorder (BorderFactory.createEmptyBorder (30, 30, 10, 30)); // �st, sol, alt ve sa� tamponlar...

        return panel;
    } // par�alar�Yarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        t�klamaSay�s�++;
        etiket.setText (etiket�neki + t�klamaSay�s�);
    } // actionPerformed(..) haz�r metodu sonu...

    private static void bakVeHissetiBa�lat() {
        String bakVeHisset = null;

        if (BAKVEH�SSET != null) {
            if (BAKVEH�SSET.equals ("Metal")) bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
              // veya: {bakVeHisset = "javax.swing.plaf.metal.MetalLookAndFeel";}...
            else if (BAKVEH�SSET.equals ("System")) bakVeHisset = UIManager.getSystemLookAndFeelClassName();
            else if (BAKVEH�SSET.equals ("Motif")) bakVeHisset = "com.sun.java.swing.plaf.motif.MotifLookAndFeel"; // Bu bende namevcut...
            else if (BAKVEH�SSET.equals ("GTK")) bakVeHisset = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"; // Bu bende namevcut...
            else {System.err.println ("Ge�ersiz bir BAKVEH�SSET belirtilmi�: [" + BAKVEH�SSET + "]");
                bakVeHisset = UIManager.getCrossPlatformLookAndFeelClassName();
            } // else karar� sonu...

            try {UIManager.setLookAndFeel (bakVeHisset);
                if (BAKVEH�SSET.equals ("Metal")) {
                  if (TEMA.equals ("DefaultMetal")) MetalLookAndFeel.setCurrentTheme (new DefaultMetalTheme());
                  else if (TEMA.equals ("Ocean")) MetalLookAndFeel.setCurrentTheme (new OceanTheme());
                  else MetalLookAndFeel.setCurrentTheme (new J5g_1x()); // Tema "�zel"...

                  UIManager.setLookAndFeel (new MetalLookAndFeel()); 
                }	// if karar� sonu...
            }catch (ClassNotFoundException ist) {//java.lang
                System.err.println ("Belirtilen BakVeHisset s�n�f� bulunamad�:" + bakVeHisset);
                System.err.println ("BakVeHisset k�t�phanesini s�n�f yoluna koydunuz mu?");
                System.err.println ("Varsay�l� BakVeHisset kullan�lacakt�r.");
            }catch (UnsupportedLookAndFeelException ist) {
                System.err.println ("Bu platformda belirtilen BakVeHisset kullan�lamaz/desteklenmiyor: " + bakVeHisset);
                System.err.println ("Varsay�l� BakVeHisset kullan�lacakt�r.");
            }catch (Exception ist) {// Varsay�l� (di�er t�m istisnalar)...
                System.err.println ("Herhal�karda belirtti�iniz BakVeHisset'e ula�am�yorum: " + bakVeHisset);
                System.err.println ("Varsay�l� BakVeHisset kullan�lacakt�r.");
                ist.printStackTrace();
            } // try-catch.. blo�u sonu...
        } // D�� if karar� sonu...
    } // bakVeHissetiBa�lat() metodu sonu...

    private static void yaratVeG�sterGUI() {
        bakVeHissetiBa�lat();
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame �er�eve = new JFrame ("BakVeHisset G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5g_1 g�steri = new J5g_1(); // Varsay�l� kurucuyu �a��r�r...
        Component par�alar = g�steri.par�alar�Yarat();
        �er�eve.getContentPane().add (par�alar, BorderLayout.CENTER);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } //yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        BAKVEH�SSET = args.length > 0 ? args[0] : "Metal";
        TEMA = args.length > 1 ? args[1] : "�zel";
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5g_1 s�n�f� sonu...