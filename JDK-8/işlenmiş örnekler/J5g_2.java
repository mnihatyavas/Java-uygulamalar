// J5g_2.java: SynthApplication (SentetikUygulama) �rne�i.

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthLookAndFeel;

/* Gereken dosyalar: d��meler1.xml
 *   resim/button.png
 *   resim/button2.png
 *
 * NOT: d��meler1.xml==> "bind, region, null" hatas� veriyor; kullan��s�z...
 */
public class J5g_2 implements ActionListener {
    private int t�klanmaSay�s� = 0;
    private static String sentetikDosya = "d��meler1.xml";
    final JLabel etiket = new JLabel ("D��menin t�klanma say�s�: " + "0");

    public Component par�alar�Yarat() {
        JButton d��me = new JButton ("Ben bir javax.swing.JButton d��mesiyim!");
        d��me.setMnemonic (KeyEvent.VK_D);
        d��me.addActionListener (this); // D��me dinleyiciye duyarl�...
        etiket.setLabelFor (d��me); // Etiketin d��meye ilintilenmesi...

        // D��me ve etiket par�as�n�, �evresi tamponlu tek kolonlu bir panel'e ekleyelim...
        JPanel panel = new JPanel (new GridLayout (0, 1));
        panel.add (d��me);
        panel.add (etiket);
        panel.setBorder (BorderFactory.createEmptyBorder (30, 30, 10, 30)); // �st, sol, alt ve sa� tamponlar...

        return panel;
    } // par�alar�Yarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        t�klanmaSay�s�++;
        etiket.setText ("D��menin t�klanma say�s�: " + t�klanmaSay�s�);
    } // actionPerformed(..) haz�r metodu sonu...

    private static void bakVeHissetiBa�lat() {
       SynthLookAndFeel bakVeHisset = new SynthLookAndFeel();
            try {bakVeHisset.load (J5g_2.class.getResourceAsStream (sentetikDosya), J5g_2.class);
                UIManager.setLookAndFeel (bakVeHisset);
            }catch (Exception ist) {System.err.println ("Belirtilen BakVeHisset'i bulamad�m: [" + bakVeHisset + "]");
                System.err.println ("Varsay�l� BakVeHissetKullan�lacak.");
                ist.printStackTrace();
            } // try-catch blo�u sonu...
    } // bakVeHissetiBa�lat() metodu sonu...

    private static void yaratVeG�sterGUI() {
        bakVeHissetiBa�lat();
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame �er�eve = new JFrame ("Sentetik Uygulama");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5g_2 uygulama = new J5g_2(); // Varsay�l� kurucuyu �a��r�r...
        Component par�alar = uygulama.par�alar�Yarat();
        �er�eve.getContentPane().add (par�alar, BorderLayout.CENTER);
        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5g_2 s�n�f� sonu...