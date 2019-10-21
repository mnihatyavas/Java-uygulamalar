// J5g_3.java: SynthDialog (YapayÝkilikonuþma) örneði.

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

/* Gereken dosyalar: düðmeler2.xml
 *   resim/textfield.png
 *   resim/button.png
 *   resim/button_over.png
 *   resim/button_press.png
 *   resim/checkbox_off.png
 *   resim/checkbox_on.png
 * NOT: düðmeler1.xml==> "bind, region, null" hatasý veriyor; kullanýþsýz...
 */
public class J5g_3 extends JFrame {
    public J5g_3() {// Kurucu...
        JLabel etiket = new JLabel ("Neyi Bulacaksýn:");
        JTextField metinSatýrý = new JTextField();
        JCheckBox büyükKüçükÇentiði = new JCheckBox("Büyük/Küçüðe Uymalý");
        JCheckBox sarmalaÇentiði = new JCheckBox("Kelime Sarmalanabilir");
        JCheckBox bütünlükÇentiði = new JCheckBox("Kelime Bütünlüðü");
        JCheckBox geriyeÇentiði = new JCheckBox("Geriye Araþtýr");
        JButton bulDüðmesi = new JButton ("Bul");
        JButton iptalDüðmesi = new JButton ("Ýptal");

        // Parça çevresi gereksiz boþluklarýn silinmesi...
        büyükKüçükÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        sarmalaÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        bütünlükÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));
        geriyeÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        GroupLayout grupSerilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilim);
        grupSerilim.setAutoCreateGaps (true);
        grupSerilim.setAutoCreateContainerGaps (true);

        // Yatay grup serilim (soldan-saða kolonvari) planlamasý...
        grupSerilim.setHorizontalGroup (grupSerilim.createSequentialGroup()
            .addComponent (etiket)
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (metinSatýrý)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (LEADING)
                        .addComponent (büyükKüçükÇentiði)
                        .addComponent (bütünlükÇentiði))
                    .addGroup(grupSerilim.createParallelGroup (LEADING)
                        .addComponent (sarmalaÇentiði)
                        .addComponent (geriyeÇentiði))))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addComponent (bulDüðmesi)
                .addComponent (iptalDüðmesi))
        ); // gru.. ifadesi sonu...

        grupSerilim.linkSize (SwingConstants.HORIZONTAL, bulDüðmesi, iptalDüðmesi);

        // Dikey grup serilim (soldan-saða satýrvari) planlamasý...
        grupSerilim.setVerticalGroup (grupSerilim.createSequentialGroup()
            .addGroup (grupSerilim.createParallelGroup (BASELINE)
                .addComponent (etiket)
                .addComponent (metinSatýrý)
                .addComponent (bulDüðmesi))
            .addGroup (grupSerilim.createParallelGroup (LEADING)
                .addGroup (grupSerilim.createSequentialGroup()
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (büyükKüçükÇentiði)
                        .addComponent (sarmalaÇentiði))
                    .addGroup (grupSerilim.createParallelGroup (BASELINE)
                        .addComponent (bütünlükÇentiði)
                        .addComponent (geriyeÇentiði)))
                .addComponent (iptalDüðmesi))
        ); // gru.. ifadesi sonu...

        setTitle ("Bul");
        setLocationRelativeTo (null);
        pack();
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
    } // J5g_3() kurucusu sonu...

    private static void bakVeHissetiBaþlat() {
         SynthLookAndFeel bakVeHisset = new SynthLookAndFeel();
            try {bakVeHisset.load (J5g_3.class.getResourceAsStream ("düðmeler2.xml"), J5g_3.class);
                UIManager.setLookAndFeel (bakVeHisset);
            }catch (Exception ist) {// java.text.ParseException
                System.err.println ("Varsayýlý BakVeHisset'i kuruyorum, zira...");
                System.err.println ("Aþaðýda belirtilen sebepten dolayý BakVeHisset'i kuramýyorum: " + bakVeHisset);
                ist.printStackTrace();
            } // try-catch bloðu sonu...
    } // bakVeHissetiBaþlat() metodu sonu...

    public static void main (String args[]) {
        EventQueue.invokeLater (new Runnable() {
            public void run() {
                bakVeHissetiBaþlat();
                JFrame.setDefaultLookAndFeelDecorated (true);	
                new J5g_3().setVisible (true); // Kurucuyu çaðýrýr...
            } // run() hazýr sicim metodu sonu...
        }); // Eve.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5g_3 sýnýfý sonu...