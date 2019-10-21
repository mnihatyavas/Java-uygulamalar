// J5f_3.java: BoxAlignmentDemo (KutuHizalamaG�sterisi) �rne�i.

import java.awt.BorderLayout; // S�n�r serilim...
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.net.URL;

/* Gereken resimler:
 *   resim/orta.gif
 *   resim/portre/portre-�gsd.gif
 */
public class J5f_3 extends JPanel {
    public J5f_3() {// Kurucu...
        super (new BorderLayout());
        JTabbedPane fi�liPano = new JTabbedPane();
        fi�liPano.setBackground (Color.BLACK);
        fi�liPano.setForeground (Color.WHITE);

        JPanel ikiD��meliPanel = new JPanel();
        ikiD��meliPanel.setBackground (Color.BLUE);
        // Varsay�l� FlowLayout/Ak��Serilimi kullan�lacak...
        ikiD��meliPanel.add (ikiD��meliPaneliKur (false)); // Hizalama de�i�meyecek..
        ikiD��meliPanel.add (ikiD��meliPaneliKur (true)); // Hizalama d�zenlenecek...
        fi�liPano.addTab ("Hizalamada De�i�iklik", ikiD��meliPanel);

        JPanel etiketVeKomponentliPanel = new JPanel();
        etiketVeKomponentliPanel.setBackground (Color.ORANGE);
        // Varsay�l� FlowLayout/Ak��Serilimi kullan�lacak.
        etiketVeKomponentliPanel.add (etiketVeKomponentliPaneliKur (false));
        etiketVeKomponentliPanel.add (etiketVeKomponentliPaneliKur (true));
        fi�liPano.addTab ("X hizalamada uyumsuzluk", etiketVeKomponentliPanel);

        JPanel ikiKomponentliPanel = new JPanel();
        ikiKomponentliPanel.setBackground (Color.GREEN);
        // Varsay�l� FlowLayout/Ak��Serilim kullan�lacak.
        ikiKomponentliPanel.add (ikiKomponentliPaneliKur (false));
        ikiKomponentliPanel.add (ikiKomponentliPaneliKur (true));
        fi�liPano.addTab ("Y hizalamada uyumsuzluk", ikiKomponentliPanel);

        // fi�liPano'yu (extends JPanel'li) i�erik panosuna ekleyelim...
        add (fi�liPano, BorderLayout.CENTER);
    } // J5f_3() kurucusu sonu...

    protected JPanel ikiD��meliPaneliKur (boolean hizalamaDe�i�ecekMi) {
        JButton d��me1 = new JButton ("Bir JButton", resim�konuYarat ("resim/orta.gif"));
        // D��me metni ikonun alt�nda ve ortalanm�� olsun...
        d��me1.setVerticalTextPosition (AbstractButton.BOTTOM);
        d��me1.setHorizontalTextPosition (AbstractButton.CENTER);

        JButton d��me2 = new JButton ("Di�er Bir JButton", resim�konuYarat ("resim/portre/portre-�gsd.gif"));
        d��me2.setVerticalTextPosition (AbstractButton.BOTTOM);
        d��me2.setHorizontalTextPosition (AbstractButton.CENTER);

        String panelKutusuBa�l���;
        if (hizalamaDe�i�ecekMi) {
            panelKutusuBa�l��� = "�stenen";
            // Her iki d��me de panel kutusunun dibine oturtulacak...
            d��me1.setAlignmentY (BOTTOM_ALIGNMENT);
            d��me2.setAlignmentY (BOTTOM_ALIGNMENT);
        } else panelKutusuBa�l��� = "Varsay�l�";

        JPanel panelKutusu = new JPanel();
        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBa�l���));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.X_AXIS));
        panelKutusu.add (d��me1);
        panelKutusu.add (d��me2);
        return panelKutusu;
    } // ikiD��meliPaneliKur(..) metodu sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        URL resimYureli = J5f_3.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    protected JPanel etiketVeKomponentliPaneliKur (boolean hizalans�nM�) {
        JPanel panelKutusu = new JPanel();

        JComponent komponent = new JPanel();
        Dimension ebat = new Dimension (150,100);
        // Komponent ebat� pencere ebat de�i�ikli�inden etkilenmesin...
        komponent.setMaximumSize (ebat);
        komponent.setPreferredSize (ebat);
        komponent.setMinimumSize (ebat);
        TitledBorder ba�l�k = new TitledBorder (// Komponent ba�l��� kutu-i�i �st ortada olacak...
                new LineBorder (Color.black),
                "Bir JPanel",
                TitledBorder.CENTER,
                TitledBorder.BELOW_TOP);
        ba�l�k.setTitleColor (Color.black);
        komponent.setBorder (ba�l�k);

        JLabel etiket = new JLabel ("Bu bir JLabel'd�r");

        String panelKutusuBa�l���;
        if (hizalans�nM�) {
            panelKutusuBa�l��� = "Uyumlu";
            etiket.setAlignmentX (CENTER_ALIGNMENT);
        } else panelKutusuBa�l��� = "Uyumsuz";

        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBa�l���));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.Y_AXIS)); // Etiket ve komponent altalta...
        panelKutusu.add (etiket);
        panelKutusu.add (komponent);
        return panelKutusu;
    } // etiketVeKomponentliPaneliKur(..) metodu sonu...

    protected JPanel ikiKomponentliPaneliKur (boolean hizalans�nM�) {
        JPanel panelKutusu = new JPanel();
        String panelKutusuBa�l���;

        JComponent komponent1 = new JPanel();
        Dimension ebat = new Dimension (100, 50);
        // Komponentlerin ebat� sabit kalacak...
        komponent1.setMaximumSize (ebat);
        komponent1.setPreferredSize (ebat);
        komponent1.setMinimumSize (ebat);
        TitledBorder ba�l�k = new TitledBorder (
                new LineBorder (Color.black),
                "Bir JPanel",
                TitledBorder.CENTER,
                TitledBorder.BELOW_TOP);
        ba�l�k.setTitleColor (Color.black);
        komponent1.setBorder (ba�l�k);

        JComponent komponent2 = new JPanel();
        komponent2.setMaximumSize (ebat);
        komponent2.setPreferredSize (ebat);
        komponent2.setMinimumSize (ebat);
        komponent2.setBorder (ba�l�k);

        if (hizalans�nM�) panelKutusuBa�l��� = "Uyumlu";
        else {komponent2.setAlignmentY (TOP_ALIGNMENT);
            panelKutusuBa�l��� = "Uyumsuz";
        } // if-else karar� sonu...

        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBa�l���));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.X_AXIS)); // �ki komponent yanyana...
        panelKutusu.add (komponent1);
        panelKutusu.add (komponent2);
        return panelKutusu;
    } // ikiKomponentliPaneliKur(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kutu Hizalama G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_3 yeni��erikPanosu = new J5f_3(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_3 s�n�f� sonu...