// J5f_3.java: BoxAlignmentDemo (KutuHizalamaGösterisi) örneði.

import java.awt.BorderLayout; // Sýnýr serilim...
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
 *   resim/portre/portre-çgsd.gif
 */
public class J5f_3 extends JPanel {
    public J5f_3() {// Kurucu...
        super (new BorderLayout());
        JTabbedPane fiþliPano = new JTabbedPane();
        fiþliPano.setBackground (Color.BLACK);
        fiþliPano.setForeground (Color.WHITE);

        JPanel ikiDüðmeliPanel = new JPanel();
        ikiDüðmeliPanel.setBackground (Color.BLUE);
        // Varsayýlý FlowLayout/AkýþSerilimi kullanýlacak...
        ikiDüðmeliPanel.add (ikiDüðmeliPaneliKur (false)); // Hizalama deðiþmeyecek..
        ikiDüðmeliPanel.add (ikiDüðmeliPaneliKur (true)); // Hizalama düzenlenecek...
        fiþliPano.addTab ("Hizalamada Deðiþiklik", ikiDüðmeliPanel);

        JPanel etiketVeKomponentliPanel = new JPanel();
        etiketVeKomponentliPanel.setBackground (Color.ORANGE);
        // Varsayýlý FlowLayout/AkýþSerilimi kullanýlacak.
        etiketVeKomponentliPanel.add (etiketVeKomponentliPaneliKur (false));
        etiketVeKomponentliPanel.add (etiketVeKomponentliPaneliKur (true));
        fiþliPano.addTab ("X hizalamada uyumsuzluk", etiketVeKomponentliPanel);

        JPanel ikiKomponentliPanel = new JPanel();
        ikiKomponentliPanel.setBackground (Color.GREEN);
        // Varsayýlý FlowLayout/AkýþSerilim kullanýlacak.
        ikiKomponentliPanel.add (ikiKomponentliPaneliKur (false));
        ikiKomponentliPanel.add (ikiKomponentliPaneliKur (true));
        fiþliPano.addTab ("Y hizalamada uyumsuzluk", ikiKomponentliPanel);

        // fiþliPano'yu (extends JPanel'li) içerik panosuna ekleyelim...
        add (fiþliPano, BorderLayout.CENTER);
    } // J5f_3() kurucusu sonu...

    protected JPanel ikiDüðmeliPaneliKur (boolean hizalamaDeðiþecekMi) {
        JButton düðme1 = new JButton ("Bir JButton", resimÝkonuYarat ("resim/orta.gif"));
        // Düðme metni ikonun altýnda ve ortalanmýþ olsun...
        düðme1.setVerticalTextPosition (AbstractButton.BOTTOM);
        düðme1.setHorizontalTextPosition (AbstractButton.CENTER);

        JButton düðme2 = new JButton ("Diðer Bir JButton", resimÝkonuYarat ("resim/portre/portre-çgsd.gif"));
        düðme2.setVerticalTextPosition (AbstractButton.BOTTOM);
        düðme2.setHorizontalTextPosition (AbstractButton.CENTER);

        String panelKutusuBaþlýðý;
        if (hizalamaDeðiþecekMi) {
            panelKutusuBaþlýðý = "Ýstenen";
            // Her iki düðme de panel kutusunun dibine oturtulacak...
            düðme1.setAlignmentY (BOTTOM_ALIGNMENT);
            düðme2.setAlignmentY (BOTTOM_ALIGNMENT);
        } else panelKutusuBaþlýðý = "Varsayýlý";

        JPanel panelKutusu = new JPanel();
        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBaþlýðý));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.X_AXIS));
        panelKutusu.add (düðme1);
        panelKutusu.add (düðme2);
        return panelKutusu;
    } // ikiDüðmeliPaneliKur(..) metodu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        URL resimYureli = J5f_3.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    protected JPanel etiketVeKomponentliPaneliKur (boolean hizalansýnMý) {
        JPanel panelKutusu = new JPanel();

        JComponent komponent = new JPanel();
        Dimension ebat = new Dimension (150,100);
        // Komponent ebatý pencere ebat deðiþikliðinden etkilenmesin...
        komponent.setMaximumSize (ebat);
        komponent.setPreferredSize (ebat);
        komponent.setMinimumSize (ebat);
        TitledBorder baþlýk = new TitledBorder (// Komponent baþlýðý kutu-içi üst ortada olacak...
                new LineBorder (Color.black),
                "Bir JPanel",
                TitledBorder.CENTER,
                TitledBorder.BELOW_TOP);
        baþlýk.setTitleColor (Color.black);
        komponent.setBorder (baþlýk);

        JLabel etiket = new JLabel ("Bu bir JLabel'dýr");

        String panelKutusuBaþlýðý;
        if (hizalansýnMý) {
            panelKutusuBaþlýðý = "Uyumlu";
            etiket.setAlignmentX (CENTER_ALIGNMENT);
        } else panelKutusuBaþlýðý = "Uyumsuz";

        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBaþlýðý));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.Y_AXIS)); // Etiket ve komponent altalta...
        panelKutusu.add (etiket);
        panelKutusu.add (komponent);
        return panelKutusu;
    } // etiketVeKomponentliPaneliKur(..) metodu sonu...

    protected JPanel ikiKomponentliPaneliKur (boolean hizalansýnMý) {
        JPanel panelKutusu = new JPanel();
        String panelKutusuBaþlýðý;

        JComponent komponent1 = new JPanel();
        Dimension ebat = new Dimension (100, 50);
        // Komponentlerin ebatý sabit kalacak...
        komponent1.setMaximumSize (ebat);
        komponent1.setPreferredSize (ebat);
        komponent1.setMinimumSize (ebat);
        TitledBorder baþlýk = new TitledBorder (
                new LineBorder (Color.black),
                "Bir JPanel",
                TitledBorder.CENTER,
                TitledBorder.BELOW_TOP);
        baþlýk.setTitleColor (Color.black);
        komponent1.setBorder (baþlýk);

        JComponent komponent2 = new JPanel();
        komponent2.setMaximumSize (ebat);
        komponent2.setPreferredSize (ebat);
        komponent2.setMinimumSize (ebat);
        komponent2.setBorder (baþlýk);

        if (hizalansýnMý) panelKutusuBaþlýðý = "Uyumlu";
        else {komponent2.setAlignmentY (TOP_ALIGNMENT);
            panelKutusuBaþlýðý = "Uyumsuz";
        } // if-else kararý sonu...

        panelKutusu.setBorder (BorderFactory.createTitledBorder (panelKutusuBaþlýðý));
        panelKutusu.setLayout (new BoxLayout (panelKutusu, BoxLayout.X_AXIS)); // Ýki komponent yanyana...
        panelKutusu.add (komponent1);
        panelKutusu.add (komponent2);
        return panelKutusu;
    } // ikiKomponentliPaneliKur(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kutu Hizalama Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5f_3 yeniÝçerikPanosu = new J5f_3(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5f_3 sýnýfý sonu...