// J5c_55.java: SplitPaneDemo (B�lmePanosuG�sterisi) �rne�i.

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

/* Gerekli resim dosyalar�:
 * Ku�.gif
 * Kedi.gif
 * K�pek.gif
 * Tav�an.gif
 * Domuz.gif
 * KathyVeO�lu.gif
 * EtiketliY�z.gif
 * Diller.gif
 * Y�ld�zlar.gif
 * K�rm�z�D�k.gif
 * Sol.gif
 * Orta.gif
 * Sa�.gif
 */
public class J5c_55 extends JPanel implements ListSelectionListener {
    private JLabel resimEtiketi;
    private JList liste;
    private JSplitPane b�lmePanosu;
    private String[] resimAdlar� = { "Ku�", "Kedi", "K�pek", "Tav�an", "Domuz", "KathyVeO�lu",
            "EtiketliY�z", "Diller", "Y�ld�zlar", "K�rm�z�D�k", "Sol", "Orta", "Sa�"};

    public J5c_55() {// Kurucu...
        // Resimlerin listesini yarat�p kayd�rma panosuna koyal�m...
        liste = new JList (resimAdlar�);
        liste.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this); // Listeyi dinleyiciye duyarlayal�m...
        liste.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        liste.setForeground (Color.WHITE);
        JScrollPane listeKayd�rmaPanosu = new JScrollPane (liste);

        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        JScrollPane resimKayd�rmaPanosu = new JScrollPane (resimEtiketi);

        // ��inde liste ve resim kayd�rma panolar�n�n bulunaca�� b�lme panosunu yaratal�m...
        b�lmePanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,
                listeKayd�rmaPanosu, resimKayd�rmaPanosu);
        b�lmePanosu.setOneTouchExpandable (true); // Aradaki b�lme duvar� �st�ndeki sol/sa� pano a�ma/kapama d��meleri...
        b�lmePanosu.setDividerLocation (150); // Aradaki b�lme duvar� konumu...

        // B�lme panosundaki 2 komponente de enk���k ebatlar�n� temin edelim...
        Dimension enk���kEbat = new Dimension (100, 50);
        listeKayd�rmaPanosu.setMinimumSize (enk���kEbat);
        resimKayd�rmaPanosu.setMinimumSize (enk���kEbat);

        // B�lme panosuna a��l��taki tercihi ebat� temin edelim...
        b�lmePanosu.setPreferredSize (new Dimension (400, 200));

        resimEtiketiniG�ncelle (resimAdlar�[liste.getSelectedIndex()]);
        //add (b�lmePanosu);
    } // J5c_55() kurucusu sonu...
    
    // Listeden yeni se�ilmeleri dinler...
    public void valueChanged (ListSelectionEvent olay) {
        JList liste = (JList)olay.getSource();
        resimEtiketiniG�ncelle (resimAdlar�[liste.getSelectedIndex()]);
    } // valueChanged(..) haz�r metodu sonu...
    
    // Se�ilen resmi sunar...
    protected void resimEtiketiniG�ncelle (String resimAd�) {
        ImageIcon ikon = resim�konuYarat ("resim/" + resimAd� + ".gif");
        resimEtiketi.setIcon (ikon);
        if  (ikon != null) resimEtiketi.setText (null);
        else resimEtiketi.setText ("Resmi Bulamad�m!");
    } // resimEtiketiniG�ncelle(..) metodu sonu...

    //  J5c_56.java (SplitPaneDemo2.java) taraf�ndan kullan�lacak...
    public JList resimListesiniAl() {return liste;}

    public JSplitPane b�lmePanosunuAl() {return b�lmePanosu;}
   
    protected static ImageIcon resim�konuYarat (String yol) {
       java.net.URL resimYureli = J5c_55.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("B�lme Panosu G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_55 b�lmePanosuG�sterisi = new J5c_55(); // Kurucuyu �a��r...
        �er�eve.getContentPane().add (b�lmePanosuG�sterisi.b�lmePanosunuAl());
        //�er�eve.setContentPane (b�lmePanosuG�sterisi);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_55 s�n�f� sonu...