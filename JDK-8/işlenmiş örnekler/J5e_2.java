// J5e_2.java: ActionDemo (AksiyonGösterisi) örneği.

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
 * programını bu program sınıfının altına, jars altdizinine koyar ve
 *   java -cp .;jars/jlfgr-1_0.jar ActionDemo
 * komutuyla çalıştırırsanız Bak ve Hisset Grafik etkisini daha iyi gözlemlersiniz.
 *
 * Gerekli resim dosyaları:
 *   resim/Back24.gif
 *   resim/Up24.gif
 *   resim/Forward24.gif
 */
public class J5e_2 extends JPanel implements ItemListener {
    protected JTextArea metinAlanı;
    protected String yeniSatır = "\n";
    protected Action solAksiyon, ortaAksiyon, sağAksiyon;
    protected JCheckBoxMenuItem[] çentikliBirimler;

    public J5e_2() {// Kurucu...
        super (new BorderLayout());

        // Kaydırmalı bir metin alanı yaratalım...
        metinAlanı = new JTextArea (5, 30);
        metinAlanı.setEditable (false); // Müdahalesiz...
        metinAlanı.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açık renkler...
        JScrollPane kaydırmaPanosu = new JScrollPane (metinAlanı);

        // İçerik panosunu serimleyelim...
        setPreferredSize (new Dimension (450, 150));
        add (kaydırmaPanosu, BorderLayout.CENTER);

        // Menü ve alt çubuğu tarafından paylaşılan aksiyonları yaratalım...
        solAksiyon =   new SolAksiyon ("Sola git",
                seyirİkonunuYarat ("Back24"),
                "Bu ilk (sola) düğmesi/menü-birimi'dir.", 
                new Integer (KeyEvent.VK_L));
        ortaAksiyon = new OrtaAksiyon ("Yukarıya git",
                seyirİkonunuYarat ("Up24"),
                "Bu orta (yukarı) düğmesi/menü-birimi'dir.", 
                new Integer (KeyEvent.VK_Y));
        sağAksiyon =  new SağAksiyon ("Sağa git",
                seyirİkonunuYarat ("Forward24"),
                "Bu son (sağa) düğmesi/menü-birimi'dir.", 
                new Integer (KeyEvent.VK_S));
    } // J53_2() kurucusu sonu...

    public class SolAksiyon extends AbstractAction {
        public SolAksiyon (String menüSeçenekleriMetni, ImageIcon düğmeİkonu,
                String ipucuİzahı, Integer kısatuşKarakteri) {
            super (menüSeçenekleriMetni, düğmeİkonu);
            putValue (SHORT_DESCRIPTION, ipucuİzahı);
            putValue (MNEMONIC_KEY, kısatuşKarakteri);
        } // SolAksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuGöster ("İlk (sola) düğmesi/menü-birimi aksiyonu", olay);}
    } // SolAksiyon sınıfı sonu...

    public class OrtaAksiyon extends AbstractAction {
        public OrtaAksiyon (String menüSeçenekleriMetni, ImageIcon düğmeİkonu,
                String ipucuİzahı, Integer kısatuşKarakteri) {
            super (menüSeçenekleriMetni, düğmeİkonu);
            putValue (SHORT_DESCRIPTION, ipucuİzahı);
            putValue (MNEMONIC_KEY, kısatuşKarakteri);
        } // OrtaAksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuGöster ("Orta (yukarıya) düğmesi/menü-birimi aksiyonu", olay);}
    } // OrtaAksiyon sınıfı sonu...

    public class SağAksiyon extends AbstractAction {
        public SağAksiyon (String menüSeçenekleriMetni, ImageIcon düğmeİkonu,
                String ipucuİzahı, Integer kısatuşKarakteri) {
            super (menüSeçenekleriMetni, düğmeİkonu);
            putValue (SHORT_DESCRIPTION, ipucuİzahı);
            putValue (MNEMONIC_KEY, kısatuşKarakteri);
        } // SağAksiyon(..) kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {sonucuGöster ("Son (sağa) düğmesi/menü-birimi aksiyonu", olay);}
    } // SağAksiyon sınıfı sonu...

    public void sonucuGöster (String aksiyonAçıklaması, ActionEvent olay) {
        String rapor = ("Aksiyon olayı tespit edildi: " + aksiyonAçıklaması + yeniSatır +
                "--->Olayın kaynağı: " + olay.getSource() + yeniSatır);
        metinAlanı.append (rapor);
    } // sonucuGöster(..) metodu sonu...

    protected static ImageIcon seyirİkonunuYarat (String resminAdı) {
        String yol = "resim/" + resminAdı + ".gif";
        URL resimYureli = J5e_2.class.getResource (yol);

        if (resimYureli == null) {System.err.println ("[" + yol + "] adlı resim dosyasını bulamadım!"); return null;
        }else return new ImageIcon (resimYureli);
    } // seyirİkonunuYarat(..) metodu sonu...

    public JMenuBar menüÇubuğunuYarat() {
        JMenuItem menüBirimi = null;
        JMenuBar menüÇubuğu;

        // Menü çubuğunu yaratalım...
        menüÇubuğu = new JMenuBar();
        menüÇubuğu.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Normal renkler...

        // Menü çubuğuna eklenecek İlk menümüzü yaratalım...
        JMenu ilkMenü = new JMenu ("Seçenekli Aksiyon Menüsü");

        Action[] menüSeçeneğiAksiyonları = {solAksiyon, ortaAksiyon, sağAksiyon};
        for (int i = 0; i < menüSeçeneğiAksiyonları.length; i++) {
            menüBirimi = new JMenuItem (menüSeçeneğiAksiyonları[i]);
            menüBirimi.setIcon (null); // İkonsuz menü birimleri...
            ilkMenü.add (menüBirimi);
        } // for döngüsü sonu...

        // Menü çubuğuna iki menü kuralım...
        menüÇubuğu.add (ilkMenü);
        menüÇubuğu.add (ikinciMenüyüYarat());
        return menüÇubuğu;
    } // menüÇubuğunuYarat() metodu sonu...

    protected JMenu ikinciMenüyüYarat() {
        JMenu ikinciMenü = new JMenu ("Aksiyon Etkinleştirme Menüsü");
        çentikliBirimler = new JCheckBoxMenuItem[3];

        çentikliBirimler[0] = new JCheckBoxMenuItem ("İlk (sola) aksiyon etkinleştirildi");
        çentikliBirimler[1] = new JCheckBoxMenuItem ("İkinci (yukarıya) aksiyon etkinleştirildi");
        çentikliBirimler[2] = new JCheckBoxMenuItem ("Üçüncü (sağa) aksiyon etkinleştirildi");

        for (int i = 0; i < çentikliBirimler.length; i++) {
            çentikliBirimler[i].setSelected (true);
            çentikliBirimler[i].addItemListener (this); // Çentik(siz)leme dinleyiciye duyarlı...
            ikinciMenü.add (çentikliBirimler[i]);
        } // for döngüsü sonu...

        return ikinciMenü;
    } // ikinciMenüyüYarat() metodu sonu...

    public void itemStateChanged (ItemEvent olay) {
        JCheckBoxMenuItem çentikliBirim = (JCheckBoxMenuItem)(olay.getSource());
        boolean çentikliMi = (olay.getStateChange() == ItemEvent.SELECTED);

        // Uygun aksiyonun etkinleştirilmesini kuralım...
        if (çentikliBirim == çentikliBirimler[0]) // Sola-ilk
            solAksiyon.setEnabled (çentikliMi);
        else if (çentikliBirim == çentikliBirimler[1]) // Yukarıya-orta
            ortaAksiyon.setEnabled (çentikliMi);
        else if (çentikliBirim == çentikliBirimler[2]) // Sağa-son
            sağAksiyon.setEnabled (çentikliMi);
    } // itemStateChanged(..) hazır metodu sonu...

    public void aletÇubuğunuYarat() {
        JButton düğme = null;

        // Alet çubuğunu yaratalım...
        JToolBar aletÇubuğu = new JToolBar();
        aletÇubuğu.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Normal renkler...
        add (aletÇubuğu, BorderLayout.PAGE_START);

        // İlk-sola düğmesi...
        düğme = new JButton (solAksiyon);
        if (düğme.getIcon() != null) düğme.setText (""); // Metinsiz ikon düğmesi...
        aletÇubuğu.add (düğme);

        // Orta-yukarıya düğmesi...
        düğme = new JButton (ortaAksiyon);
        if (düğme.getIcon() != null) düğme.setText(""); // Metinsiz ikon düğmesi...
        aletÇubuğu.add (düğme);

        // Son-sağa düğmesi...
        düğme = new JButton (sağAksiyon);
        if (düğme.getIcon() != null) düğme.setText (""); // Metinsiz ikon düğmesi...
        aletÇubuğu.add (düğme);
    } // aletÇubuğunuYarat() metodu sonu...

    private static void yaratVeGösterGBA() {
        JFrame çerçeve = new JFrame ("Aksiyon Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5e_2 yeniİçerikPanosu = new J5e_2(); // Kurucuyu çağırır...
        çerçeve.setJMenuBar (yeniİçerikPanosu.menüÇubuğunuYarat());
        yeniİçerikPanosu.aletÇubuğunuYarat();
        yeniİçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniİçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGBA()

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
             // GUI: GraphicsUnitInterface = GBA: GrafikBirimArayüzü...
            public void run() {yaratVeGösterGBA();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_2 sınıfı sonu...