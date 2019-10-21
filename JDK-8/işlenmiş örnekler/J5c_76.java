// J5c_76.java: ToolBarDemo (AletÇubuðuGösterisi) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.net.URL;

/* Gereken resim dosyalarý:
 * resim/Back24.gif: Geri
 * resim/Forward24.gif: Ýleri
 * resim/Up24.gif: Yukarý
 */
public class J5c_76 extends JPanel implements ActionListener {
    protected JTextArea metinAlaný;
    protected String yeniSatýr = "\n";
    static final private String ÖNCEKÝ = "önceki";
    static final private String YUKARI = "yukarý";
    static final private String SONRAKÝ = "sonraki";

    public J5c_76() {// Kurucu...
        super (new BorderLayout());

        // Alet çubuðunu yaratalým...
        JToolBar aletÇubuðu = new JToolBar ("Alet Çubuðu");
        aletÇubuðu.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tüm renkler...
        butonlarýAletçubuðunaEkle (aletÇubuðu);

        // Aletçubuðu düðmeleri týklanýnca raporlarýn yazýlacaðý 5 satýr, 30 kolonluk
        // müdahalesiz kaydýrma çubuklu metin alanýný yaratýp içerik panosuna ekleyelim...
        metinAlaný = new JTextArea (5, 30);
        metinAlaný.setEditable (false);
        metinAlaný.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        metinAlaný.setForeground (Color.WHITE);

        JScrollPane alanKaydýrmaPanosu = new JScrollPane (metinAlaný);
        setPreferredSize (new Dimension (470, 130)); // Ýçerik panosu tercihi boyutu...
        add (aletÇubuðu, BorderLayout.PAGE_START);
        add (alanKaydýrmaPanosu, BorderLayout.CENTER);
    } // J5c_77() kuruusu sonu...

    protected void butonlarýAletçubuðunaEkle (JToolBar aletÇubuðu) {
        JButton düðme = null;

        // Ýlk buton...
        düðme = butonÝkonunuYarat (
                "Back24", // Resim dosyasý adý...
                ÖNCEKÝ, // Aksiyon komutu...
                "Bir öncekine yönlendirir", // Alet ucu ipucu...
                "Bir Önceki"); // Alt-ernatif resim (namevcutsa alt) yazýsý...
        aletÇubuðu.add (düðme);

        // Ýkinci buton...
        düðme = butonÝkonunuYarat (
                "Up24",
                YUKARI,
                "Bir üst seviyedekine yönlendirir",
                "Bir Üst Seviye");
        aletÇubuðu.add (düðme);

        // Üçüncü buton...
        düðme = butonÝkonunuYarat (
                "Forward24",
                SONRAKÝ,
                "Bir sonrakine yönlendirir",
                "Bir Sonraki");
        aletÇubuðu.add (düðme);
    } // butonlarýAletçubuðunaEkle(..) metodu sonu...

    protected JButton butonÝkonunuYarat (
            String resimAdý,
            String aksiyonKomutu,
            String aletÝpucuMetni,
            String alternatifMetin) {
        // Buton resmini okuyalým...
        String resminYolu = "resim/" + resimAdý + ".gif";
        URL resimYureli = J5c_76.class.getResource (resminYolu);

        // Düðmeye resim ikonunu (mevcutsa) kuralým...
        JButton düðme = new JButton();
        düðme.setActionCommand (aksiyonKomutu);
        düðme.setToolTipText (aletÝpucuMetni);
        düðme.addActionListener (this); // Düðmeyi dinleyiciye duyarlayalým...

        if (resimYureli != null) // Resim mevcutsa...
            düðme.setIcon (new ImageIcon (resimYureli, alternatifMetin));
        else {// Resim dosyasý bulunamazsa...
            düðme.setText (alternatifMetin);
            System.err.println ("HATA: [" + resminYolu + "] adlý resim dosyasý bulunamadý!");
        } // if-else kararý sonu...

        return düðme;
    } // butonÝkonunuYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        String rapor = null;

        // Herbir butonu tek tek yönetelim...
        if (ÖNCEKÝ.equals (komut)) // Ýlk düðme týklanmýþsa...
            rapor = "bir önceki <þeye>";
        else if (YUKARI.equals(komut)) // Ýkinci düðme týklanmýþsa...
            rapor = "bir üst seviyedeki <þeye>";
        else if (SONRAKÝ.equals(komut)) // Üçüncü düðme týklanmýþsa...
            rapor = "bir sonraki <þeye>";

        metinAlaný.append ("Eðer bu gerçek bir uygulama olsaydý, yönelim: " + rapor + " olacaktý." + yeniSatýr);
        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Alet Çubuðu Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_76()); // Referanssýz sýnýf içerik panosu nesnesi; kurucuyu çaðýrýr...
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazýlým yok...
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_76 sýnýfý sonu...