// J5e_2.java: ActionDemo (AksiyonG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import java.net.URL;

/* Java Look and Feel Graphics Repository: jlfgr-1_0.jar
 * program�n� bu program s�n�f�n�n alt�na, jars altdizinine koyar ve
 *   java -cp .;jars/jlfgr-1_0.jar ActionDemo
 * komutuyla �al��t�r�rsan�z Bak ve Hisset Grafik etkisini daha iyi g�zlemlersiniz.
 *
 * Gerekli resim dosyalar�:
 *   resim/Back24.gif
 *   resim/Up24.gif
 *   resim/Forward24.gif
 */
public class J5e_2 extends JPanel implements ItemListener {
    protected JTextArea metinAlan�;
    protected String yeniSat�r = "\n";
    protected Action solAksiyon, ortaAksiyon, sa�Aksiyon;
    protected JCheckBoxMenuItem[] �entikliBirimler;

    public J5e_2() {// Kurucu...
        super (new BorderLayout());

        // Kayd�rmal� bir metin alan� yaratal�m...
        metinAlan� = new JTextArea (5, 30);
        metinAlan�.setEditable (false); // M�dahalesiz...
        metinAlan�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);

        // ��erik panosunu serimleyelim...
        setPreferredSize (new Dimension (450, 150));
        add (kayd�rmaPanosu, BorderLayout.CENTER);

        // Men� ve alt �ubu�u taraf�ndan payla��lan aksiyonlar� yaratal�m...
        solAksiyon =   new SolAksiyon ("Sola git",
                seyir�konunuYarat ("Back24"),
                "Bu ilk (sola) d��mesi/men�-birimi'dir.", 
                new Integer (KeyEvent.VK_L));
        ortaAksiyon = new OrtaAksiyon ("Yukar�ya git",
                seyir�konunuYarat ("Up24"),
                "Bu orta (yukar�) d��mesi/men�-birimi'dir.", 
                new Integer (KeyEvent.VK_Y));
        sa�Aksiyon =  new Sa�Aksiyon ("Sa�a git",
                seyir�konunuYarat ("Forward24"),
                "Bu son (sa�a) d��mesi/men�-birimi'dir.", 
                new Integer (KeyEvent.VK_S));
    } // J53_2() kurucusu sonu...

    public class SolAksiyon extends AbstractAction {
        public SolAksiyon (String men�Se�enekleriMetni, ImageIcon d��me�konu,
                String ipucu�zah�, Integer k�satu�Karakteri) {
            super (men�Se�enekleriMetni, d��me�konu);
            putValue (SHORT_DESCRIPTION, ipucu�zah�);
            putValue (MNEMONIC_KEY, k�satu�Karakteri);
        } // SolAksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuG�ster ("�lk (sola) d��mesi/men�-birimi aksiyonu", olay);}
    } // SolAksiyon s�n�f� sonu...

    public class OrtaAksiyon extends AbstractAction {
        public OrtaAksiyon (String men�Se�enekleriMetni, ImageIcon d��me�konu,
                String ipucu�zah�, Integer k�satu�Karakteri) {
            super (men�Se�enekleriMetni, d��me�konu);
            putValue (SHORT_DESCRIPTION, ipucu�zah�);
            putValue (MNEMONIC_KEY, k�satu�Karakteri);
        } // OrtaAksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuG�ster ("Orta (yukar�ya) d��mesi/men�-birimi aksiyonu", olay);}
    } // OrtaAksiyon s�n�f� sonu...

    public class Sa�Aksiyon extends AbstractAction {
        public Sa�Aksiyon (String men�Se�enekleriMetni, ImageIcon d��me�konu,
                String ipucu�zah�, Integer k�satu�Karakteri) {
            super (men�Se�enekleriMetni, d��me�konu);
            putValue (SHORT_DESCRIPTION, ipucu�zah�);
            putValue (MNEMONIC_KEY, k�satu�Karakteri);
        } // Sa�Aksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuG�ster ("Son (sa�a) d��mesi/men�-birimi aksiyonu", olay);}
    } // Sa�Aksiyon s�n�f� sonu...

    public void sonucuG�ster (String aksiyonA��klamas�, ActionEvent olay) {
        String rapor = ("Aksiyon olay� tespit edildi: " + aksiyonA��klamas� + yeniSat�r +
                "--->Olay�n kayna��: " + olay.getSource() + yeniSat�r);
        metinAlan�.append (rapor);
    } // sonucuG�ster(..) metodu sonu...

    protected static ImageIcon seyir�konunuYarat (String resminAd�) {
        String yol = "resim/" + resminAd� + ".gif";
        URL resimYureli = J5e_2.class.getResource (yol);

        if (resimYureli == null) {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;
        }else return new ImageIcon (resimYureli);
    } // seyir�konunuYarat(..) metodu sonu...

    public JMenuBar men��ubu�unuYarat() {
        JMenuItem men�Birimi = null;
        JMenuBar men��ubu�u;

        // Men� �ubu�unu yaratal�m...
        men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Normal renkler...

        // Men� �ubu�una eklenecek �lk men�m�z� yaratal�m...
        JMenu ilkMen� = new JMenu ("Se�enekli Aksiyon Men�s�");

        Action[] men�Se�ene�iAksiyonlar� = {solAksiyon, ortaAksiyon, sa�Aksiyon};
        for (int i = 0; i < men�Se�ene�iAksiyonlar�.length; i++) {
            men�Birimi = new JMenuItem (men�Se�ene�iAksiyonlar�[i]);
            men�Birimi.setIcon (null); // �konsuz men� birimleri...
            ilkMen�.add (men�Birimi);
        } // for d�ng�s� sonu...

        // Men� �ubu�una iki men� kural�m...
        men��ubu�u.add (ilkMen�);
        men��ubu�u.add (ikinciMen�y�Yarat());
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    protected JMenu ikinciMen�y�Yarat() {
        JMenu ikinciMen� = new JMenu ("Aksiyon Etkinle�tirme Men�s�");
        �entikliBirimler = new JCheckBoxMenuItem[3];

        �entikliBirimler[0] = new JCheckBoxMenuItem ("�lk (sola) aksiyon etkinle�tirildi");
        �entikliBirimler[1] = new JCheckBoxMenuItem ("�kinci (yukar�ya) aksiyon etkinle�tirildi");
        �entikliBirimler[2] = new JCheckBoxMenuItem ("���nc� (sa�a) aksiyon etkinle�tirildi");

        for (int i = 0; i < �entikliBirimler.length; i++) {
            �entikliBirimler[i].setSelected (true);
            �entikliBirimler[i].addItemListener (this); // �entik(siz)leme dinleyiciye duyarl�...
            ikinciMen�.add (�entikliBirimler[i]);
        } // for d�ng�s� sonu...

        return ikinciMen�;
    } // ikinciMen�y�Yarat() metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        JCheckBoxMenuItem �entikliBirim = (JCheckBoxMenuItem)(olay.getSource());
        boolean �entikliMi = (olay.getStateChange() == ItemEvent.SELECTED);

        // Uygun aksiyonun etkinle�tirilmesini kural�m...
        if (�entikliBirim == �entikliBirimler[0]) // Sola-ilk
            solAksiyon.setEnabled (�entikliMi);
        else if (�entikliBirim == �entikliBirimler[1]) // Yukar�ya-orta
            ortaAksiyon.setEnabled (�entikliMi);
        else if (�entikliBirim == �entikliBirimler[2]) // Sa�a-son
            sa�Aksiyon.setEnabled (�entikliMi);
    } // itemStateChanged(..) haz�r metodu sonu...

    public void alet�ubu�unuYarat() {
        JButton d��me = null;

        // Alet �ubu�unu yaratal�m...
        JToolBar alet�ubu�u = new JToolBar();
        alet�ubu�u.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Normal renkler...
        add (alet�ubu�u, BorderLayout.PAGE_START);

        // �lk-sola d��mesi...
        d��me = new JButton (solAksiyon);
        if (d��me.getIcon() != null) d��me.setText (""); // Metinsiz ikon d��mesi...
        alet�ubu�u.add (d��me);

        // Orta-yukar�ya d��mesi...
        d��me = new JButton (ortaAksiyon);
        if (d��me.getIcon() != null) d��me.setText(""); // Metinsiz ikon d��mesi...
        alet�ubu�u.add (d��me);

        // Son-sa�a d��mesi...
        d��me = new JButton (sa�Aksiyon);
        if (d��me.getIcon() != null) d��me.setText (""); // Metinsiz ikon d��mesi...
        alet�ubu�u.add (d��me);
    } // alet�ubu�unuYarat() metodu sonu...

    private static void yaratVeG�sterGBA() {
        JFrame �er�eve = new JFrame ("Aksiyon G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5e_2 yeni��erikPanosu = new J5e_2(); // Kurucuyu �a��r�r...
        �er�eve.setJMenuBar (yeni��erikPanosu.men��ubu�unuYarat());
        yeni��erikPanosu.alet�ubu�unuYarat();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGBA()

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
             // GUI: GraphicsUnitInterface = GBA: GrafikBirimAray�z�...
            public void run() {yaratVeG�sterGBA();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_2 s�n�f� sonu...