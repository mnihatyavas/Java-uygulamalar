// J5c_31.java: ListDialogRunner (ListeDiyaloðuKoþturucusu) örneði.

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;

/* Gereken dosya:
 * J5c_31x.java=ListDialog.java
 */
public class J5c_31 {
    static JFrame çerçeve;
    static String[] bebekAdlarý = {"Arlo", "Cosmo", "Elmo", "Hugo", "Jethro",
        "Laszlo", "Lezzo", "Milo", "Nemo", "Otto", "Ringo", "Rocco", "Rollo", 
        "Apo", "Cemo", "Bilo", "Ezo", "Fato", "Gülo", "Hüsso", "Ýbo", "Maho",
        "Memo", "Neco", "Paþo", "Sülo"};

    public static JPanel yaratUI() {
        // 2 etiket yaratalým...
        JLabel tanýtma = new JLabel ("Seçilen bebek adý:");
        final JLabel bebekAdý = new JLabel (bebekAdlarý[0]);
        tanýtma.setLabelFor (bebekAdý);
        bebekAdý.setFont (new Font ("Serif", Font.ITALIC, 36)); // Ýlk yazý fonu...

        // Sonraki bebek adýný seçecek düðmeyi yaratýp, dinleyiciye duyarlayalým...Create the button.
        final JButton adSeçenDüðme = new JButton ("Yeni bebek adýný seç");
        adSeçenDüðme.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                bebekAdý.setFont (yeniFonAl());
                bebekAdý.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
                String seçilenAd = J5c_31x.diyaloðuGöster (
                        çerçeve,
                        adSeçenDüðme,
                        "O ile biten sevimli bebek [takma] adlarý:",
                        "Bebek Adý Seçicisi",
                        bebekAdlarý,
                        bebekAdý.getText(),
                        null);
                bebekAdý.setText (seçilenAd);
        }}); // adS.. ifadesi sonu...

        // (Düðmeden döndüðümüzde) yaratýp komponentleri ekleyeceðimiz panel...
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        panel.setBorder (BorderFactory.createEmptyBorder (20,20,10,20));
        tanýtma.setAlignmentX (JComponent.CENTER_ALIGNMENT);
        bebekAdý.setAlignmentX (JComponent.CENTER_ALIGNMENT);
        adSeçenDüðme.setAlignmentX (JComponent.CENTER_ALIGNMENT);

        // Etiketleri ekleyelim...
        panel.add (tanýtma);
        panel.add (Box.createVerticalStrut (5)); // Ara takoz boþluðu...
        panel.add (bebekAdý);

        // Ara boþluk ve düðmeyi de ekleyelim...
        panel.add (Box.createRigidArea (new Dimension (220,10)));
        panel.add (adSeçenDüðme);
        panel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        return panel;
    } // yaratUI() metodu sonu...

    protected static Font yeniFonAl() {
        String[] mevcutYazýAilesiAdlarý = null; // Bilgisayarýnýzdaki yazý fonlarý...
        String yazýFonuAdý = null; 

        GraphicsEnvironment grafikÇevresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (grafikÇevresi != null) mevcutYazýAilesiAdlarý = grafikÇevresi.getAvailableFontFamilyNames();

        // Düðmeyi bu týklamada kullanacaðýmýz yazý fonu...
        if (mevcutYazýAilesiAdlarý != null) {// Mevcutlardan rasgele bir yazý fonu seçeceðiz...
            int i = (int)(Math.random() * mevcutYazýAilesiAdlarý.length);
            System.out.println ("Bu kerre kullanýlan yazý fonumuz: [" + mevcutYazýAilesiAdlarý[i] + "]");
            return new Font (mevcutYazýAilesiAdlarý[i], Font.PLAIN, 50);
        }else return new Font ("Serif", Font.ITALIC, 36); // Namevcutsa, yazý fonumuz Serif-yatýk-36 olsun...
    } // yeniFonAl() metodu sonu...

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Bebeðe Bir Ad Seçelim");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = yaratUI();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setBounds (550,100, 220,220);
        //çerçeve.pack(); // Paket kullanmadým, zira bazý yazý tipleri büyüklüðü sýðmýyor...
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_31 sýnýfý sonu...