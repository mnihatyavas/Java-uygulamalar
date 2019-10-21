// J5c_31.java: ListDialogRunner (ListeDiyalo�uKo�turucusu) �rne�i.

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
    static JFrame �er�eve;
    static String[] bebekAdlar� = {"Arlo", "Cosmo", "Elmo", "Hugo", "Jethro",
        "Laszlo", "Lezzo", "Milo", "Nemo", "Otto", "Ringo", "Rocco", "Rollo", 
        "Apo", "Cemo", "Bilo", "Ezo", "Fato", "G�lo", "H�sso", "�bo", "Maho",
        "Memo", "Neco", "Pa�o", "S�lo"};

    public static JPanel yaratUI() {
        // 2 etiket yaratal�m...
        JLabel tan�tma = new JLabel ("Se�ilen bebek ad�:");
        final JLabel bebekAd� = new JLabel (bebekAdlar�[0]);
        tan�tma.setLabelFor (bebekAd�);
        bebekAd�.setFont (new Font ("Serif", Font.ITALIC, 36)); // �lk yaz� fonu...

        // Sonraki bebek ad�n� se�ecek d��meyi yarat�p, dinleyiciye duyarlayal�m...Create the button.
        final JButton adSe�enD��me = new JButton ("Yeni bebek ad�n� se�");
        adSe�enD��me.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                bebekAd�.setFont (yeniFonAl());
                bebekAd�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
                String se�ilenAd = J5c_31x.diyalo�uG�ster (
                        �er�eve,
                        adSe�enD��me,
                        "O ile biten sevimli bebek [takma] adlar�:",
                        "Bebek Ad� Se�icisi",
                        bebekAdlar�,
                        bebekAd�.getText(),
                        null);
                bebekAd�.setText (se�ilenAd);
        }}); // adS.. ifadesi sonu...

        // (D��meden d�nd���m�zde) yarat�p komponentleri ekleyece�imiz panel...
        JPanel panel = new JPanel();
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        panel.setBorder (BorderFactory.createEmptyBorder (20,20,10,20));
        tan�tma.setAlignmentX (JComponent.CENTER_ALIGNMENT);
        bebekAd�.setAlignmentX (JComponent.CENTER_ALIGNMENT);
        adSe�enD��me.setAlignmentX (JComponent.CENTER_ALIGNMENT);

        // Etiketleri ekleyelim...
        panel.add (tan�tma);
        panel.add (Box.createVerticalStrut (5)); // Ara takoz bo�lu�u...
        panel.add (bebekAd�);

        // Ara bo�luk ve d��meyi de ekleyelim...
        panel.add (Box.createRigidArea (new Dimension (220,10)));
        panel.add (adSe�enD��me);
        panel.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        return panel;
    } // yaratUI() metodu sonu...

    protected static Font yeniFonAl() {
        String[] mevcutYaz�AilesiAdlar� = null; // Bilgisayar�n�zdaki yaz� fonlar�...
        String yaz�FonuAd� = null; 

        GraphicsEnvironment grafik�evresi = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (grafik�evresi != null) mevcutYaz�AilesiAdlar� = grafik�evresi.getAvailableFontFamilyNames();

        // D��meyi bu t�klamada kullanaca��m�z yaz� fonu...
        if (mevcutYaz�AilesiAdlar� != null) {// Mevcutlardan rasgele bir yaz� fonu se�ece�iz...
            int i = (int)(Math.random() * mevcutYaz�AilesiAdlar�.length);
            System.out.println ("Bu kerre kullan�lan yaz� fonumuz: [" + mevcutYaz�AilesiAdlar�[i] + "]");
            return new Font (mevcutYaz�AilesiAdlar�[i], Font.PLAIN, 50);
        }else return new Font ("Serif", Font.ITALIC, 36); // Namevcutsa, yaz� fonumuz Serif-yat�k-36 olsun...
    } // yeniFonAl() metodu sonu...

    private static void yaratVeG�sterGUI() {
        �er�eve = new JFrame ("Bebe�e Bir Ad Se�elim");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = yaratUI();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setBounds (550,100, 220,220);
        //�er�eve.pack(); // Paket kullanmad�m, zira baz� yaz� tipleri b�y�kl��� s��m�yor...
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_31 s�n�f� sonu...