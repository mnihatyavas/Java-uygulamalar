// J5g_3.java: SynthDialog (Yapay�kilikonu�ma) �rne�i.

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import static javax.swing.GroupLayout.Alignment.BASELINE;
import static javax.swing.GroupLayout.Alignment.LEADING;
import javax.swing.plaf.synth.SynthLookAndFeel;

/* Gereken dosyalar: d��meler2.xml
 *   resim/textfield.png
 *   resim/button.png
 *   resim/button_over.png
 *   resim/button_press.png
 *   resim/checkbox_off.png
 *   resim/checkbox_on.png
 * NOT: d��meler1.xml==> "bind, region, null" hatas� veriyor; kullan��s�z...
 */
public class J5g_3 extends JFrame {
    public J5g_3() {// Kurucu...
        JLabel etiket = new JLabel ("Neyi Bulacaks�n:");
        JTextField metinSat�r� = new JTextField();
        JCheckBox b�y�kK���k�enti�i = new JCheckBox("B�y�k/K����e Uymal�");
        JCheckBox sarmala�enti�i = new JCheckBox("Kelime Sarmalanabilir");
        JCheckBox b�t�nl�k�enti�i = new JCheckBox("Kelime B�t�nl���");
        JCheckBox geriye�enti�i = new JCheckBox("Geriye Ara�t�r");
        JButton bulD��mesi = new JButton ("Bul");
        JButton iptalD��mesi = new JButton ("�ptal");

        // Par�a �evresi gereksiz bo�luklar�n silinmesi...
        b�y�kK���k�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        sarmala�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        b�t�nl�k�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        geriye�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        GroupLayout grupSerilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilim);
        grupSerilim.setAutoCreateGaps (true);
        grupSerilim.setAutoCreateContainerGaps (true);

        // Yatay grup serilim (soldan-sa�a kolonvari) planlamas�...
        grupSerilim.setHorizontalGroup (grupSerilim.createSequentialGroup()
            .addComponent (etiket)
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (metinSat�r�)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (b�y�kK���k�enti�i)
                        .addComponent (b�t�nl�k�enti�i))
                    .addGroup(grupSerilim.createParallelGroup (LEADING)
                        .addComponent (sarmala�enti�i)
                        .addComponent (geriye�enti�i))))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (bulD��mesi)
                .addComponent (iptalD��mesi))
        ); // gru.. ifadesi sonu...

        grupSerilim.linkSize (SwingConstants.HORIZONTAL, bulD��mesi, iptalD��mesi);

        // Dikey grup serilim (soldan-sa�a sat�rvari) planlamas�...
        grupSerilim.setVerticalGroup (grupSerilim.createSequentialGroup()
            .addGroup (grupSerilim.createParallelGroup (BASELINE)
                .addComponent (etiket)
                .addComponent (metinSat�r�)
                .addComponent (bulD��mesi))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (b�y�kK���k�enti�i)
                        .addComponent (sarmala�enti�i))
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (b�t�nl�k�enti�i)
                        .addComponent (geriye�enti�i)))
                .addComponent (iptalD��mesi))
        ); // gru.. ifadesi sonu...

        setTitle ("Bul");
        setLocationRelativeTo (null);
        pack();
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
    } // J5g_3() kurucusu sonu...

    private static void bakVeHissetiBa�lat() {
         SynthLookAndFeel bakVeHisset = new SynthLookAndFeel();
            try {bakVeHisset.load (J5g_3.class.getResourceAsStream ("d��meler2.xml"), J5g_3.class);
                UIManager.setLookAndFeel (bakVeHisset);
            }catch (Exception ist) {// java.text.ParseException
                System.err.println ("Varsay�l� BakVeHisset'i kuruyorum, zira...");
                System.err.println ("A�a��da belirtilen sebepten dolay� BakVeHisset'i kuram�yorum: " + bakVeHisset);
                ist.printStackTrace();
            } // try-catch blo�u sonu...
    } // bakVeHissetiBa�lat() metodu sonu...

    public static void main (String args[]) {
        EventQueue.invokeLater (new Runnable() {
            public void run() {
                bakVeHissetiBa�lat();
                JFrame.setDefaultLookAndFeelDecorated (true);	
                new J5g_3().setVisible (true); // Kurucuyu �a��r�r...
            } // run() haz�r sicim metodu sonu...
        }); // Eve.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5g_3 s�n�f� sonu...