// J5c_55.java: SplitPaneDemo (BölmePanosuGösterisi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

/* Gerekli resim dosyalarý:
 * Kuþ.gif
 * Kedi.gif
 * Köpek.gif
 * Tavþan.gif
 * Domuz.gif
 * KathyVeOðlu.gif
 * EtiketliYüz.gif
 * Diller.gif
 * Yýldýzlar.gif
 * KýrmýzýDük.gif
 * Sol.gif
 * Orta.gif
 * Sað.gif
 */
public class J5c_55 extends JPanel implements ListSelectionListener {
    private JLabel resimEtiketi;
    private JList liste;
    private JSplitPane bölmePanosu;
    private String[] resimAdlarý = { "Kuþ", "Kedi", "Köpek", "Tavþan", "Domuz", "KathyVeOðlu",
            "EtiketliYüz", "Diller", "Yýldýzlar", "KýrmýzýDük", "Sol", "Orta", "Sað"};

    public J5c_55() {// Kurucu...
        // Resimlerin listesini yaratýp kaydýrma panosuna koyalým...
        liste = new JList (resimAdlarý);
        liste.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this); // Listeyi dinleyiciye duyarlayalým...
        liste.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        liste.setForeground (Color.WHITE);
        JScrollPane listeKaydýrmaPanosu = new JScrollPane (liste);

        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        JScrollPane resimKaydýrmaPanosu = new JScrollPane (resimEtiketi);

        // Ýçinde liste ve resim kaydýrma panolarýnýn bulunacaðý bölme panosunu yaratalým...
        bölmePanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,
                listeKaydýrmaPanosu, resimKaydýrmaPanosu);
        bölmePanosu.setOneTouchExpandable (true); // Aradaki bölme duvarý üstündeki sol/sað pano açma/kapama düðmeleri...
        bölmePanosu.setDividerLocation (150); // Aradaki bölme duvarý konumu...

        // Bölme panosundaki 2 komponente de enküçük ebatlarýný temin edelim...
        Dimension enküçükEbat = new Dimension (100, 50);
        listeKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        resimKaydýrmaPanosu.setMinimumSize (enküçükEbat);

        // Bölme panosuna açýlýþtaki tercihi ebatý temin edelim...
        bölmePanosu.setPreferredSize (new Dimension (400, 200));

        resimEtiketiniGüncelle (resimAdlarý[liste.getSelectedIndex()]);
        //add (bölmePanosu);
    } // J5c_55() kurucusu sonu...
    
    // Listeden yeni seçilmeleri dinler...
    public void valueChanged (ListSelectionEvent olay) {
        JList liste = (JList)olay.getSource();
        resimEtiketiniGüncelle (resimAdlarý[liste.getSelectedIndex()]);
    } // valueChanged(..) hazýr metodu sonu...
    
    // Seçilen resmi sunar...
    protected void resimEtiketiniGüncelle (String resimAdý) {
        ImageIcon ikon = resimÝkonuYarat ("resim/" + resimAdý + ".gif");
        resimEtiketi.setIcon (ikon);
        if  (ikon != null) resimEtiketi.setText (null);
        else resimEtiketi.setText ("Resmi Bulamadým!");
    } // resimEtiketiniGüncelle(..) metodu sonu...

    //  J5c_56.java (SplitPaneDemo2.java) tarafýndan kullanýlacak...
    public JList resimListesiniAl() {return liste;}

    public JSplitPane bölmePanosunuAl() {return bölmePanosu;}
   
    protected static ImageIcon resimÝkonuYarat (String yol) {
       java.net.URL resimYureli = J5c_55.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Bölme Panosu Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_55 bölmePanosuGösterisi = new J5c_55(); // Kurucuyu çaðýr...
        çerçeve.getContentPane().add (bölmePanosuGösterisi.bölmePanosunuAl());
        //çerçeve.setContentPane (bölmePanosuGösterisi);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_55 sýnýfý sonu...