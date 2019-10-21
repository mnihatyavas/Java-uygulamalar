// J5c_77.java: ToolBarDemo2 (AletÇubuðuGösterisi2) örneði.

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;

import java.net.URL;

/* Bir önceki örnekteki alet çubuðuna, raporlayan bir buton ve bir veri satýrý daha eklenmiþtir.
 * Gereken resim dosyalarý:
 * resim/Back24.gif
 * resim/Forward24.gif
 * resim/Up24.gif
 */
public class J5c_77 extends JPanel implements ActionListener {
    protected JTextArea raporMetinAlaný;
    protected String yeniSatýr = "\n";
    static final private String ÖNCE = "önce";
    static final private String YUKARI = "yukarý";
    static final private String SONRA = "sonra";
    static final private String YENÝ_BUTON = "diðer";
    static final private String GÝRÝLEN_SATIR = "metin";

    public J5c_77() {// Kurucu...
        super (new BorderLayout());

        // Alet çubuðunu yaratalým...
        JToolBar aletÇubuðu = new JToolBar ("Alet Çubuðu");
        aletÇubuðu.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // Tüm renkler...
        komponentleriEkle (aletÇubuðu);
        aletÇubuðu.setFloatable (false);
        aletÇubuðu.setRollover (true);

        // Rapor metin alanýný yeterli ebatlý kuralým...
        raporMetinAlaný = new JTextArea (5, 30);
        raporMetinAlaný.setEditable (false); // Müdahalesiz...
        raporMetinAlaný.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        raporMetinAlaný.setForeground (Color.WHITE);

        JScrollPane alanKaydýrmaPanosu = new JScrollPane (raporMetinAlaný);

        // Ýçerik panosunu serimleyelim...
        setPreferredSize (new Dimension (560, 130));
        add (aletÇubuðu, BorderLayout.PAGE_START);
        add (alanKaydýrmaPanosu, BorderLayout.CENTER);
    } // J5c_77() kurucusu sonu...

    protected void komponentleriEkle (JToolBar aletÇubuðu) {
        JButton düðme = null;

        // Ýlk düðmeyi ekleyelim...
        düðme = ikonluDüðmeyiYarat (
                "Back24",
                ÖNCE,
                "Bir önceki þeye gönderir",
                "Bir Önceye");
        aletÇubuðu.add (düðme);

        // Ýkinci düðmeyi ekleyelim...
        düðme = ikonluDüðmeyiYarat ("Up24", YUKARI, "Bir üst seviyedeki þeye gönderir", "Bir Üste");
        aletÇubuðu.add (düðme);

        // Üçüncü düðmeyi ekleyelim...
        düðme = ikonluDüðmeyiYarat ("Forward24", SONRA, "Bir sonraki þeye gönderir", "Bir Sonraya");
        aletÇubuðu.add (düðme);

        // Araya bir ayraç koyalým...
        aletÇubuðu.addSeparator();

        // Yeni dördüncü düðmeyi ekleyelim...
        düðme = new JButton ("Ýkonsuz bir düðme");
        düðme.setActionCommand (YENÝ_BUTON);
        düðme.setToolTipText ("Herhangi bir þey yapar");
        düðme.addActionListener (this); // Dinleyiciye duyarlayalým...
        aletÇubuðu.add (düðme);

        // Yeni beþinci (düðme deðil) metin satýrý komponentini ekleyelim...
        JTextField metinSatýrý = new JTextField ("Bir Metin Satýrý");
        metinSatýrý.setColumns (10);
        metinSatýrý.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Çok açýk renkler...
        metinSatýrý.addActionListener (this); // Dinleyiciye duyarlayalým...
        metinSatýrý.setActionCommand (GÝRÝLEN_SATIR);
        aletÇubuðu.add (metinSatýrý);
    } // komponentleriEkle(..) metodu sonu...

    protected JButton ikonluDüðmeyiYarat (
            String resminAdý,
            String aksiyonKomutu,
            String aletÝpucuMetni,
            String alternatifMetin) {
        // Resim dosyasý adýný tamamlayýp, dosyayý bulalým...
        String resimDosyasýYolu = "resim/" + resminAdý + ".gif";
        URL resimYureli = J5c_77.class.getResource (resimDosyasýYolu);

        // Resim ikonlu düðmeyi yaratýp kuralým...
        JButton düðme = new JButton();
        düðme.setActionCommand (aksiyonKomutu);
        düðme.setToolTipText (aletÝpucuMetni);
        düðme.addActionListener (this); // Dinleyiciye duyarlayalým...

        if (resimYureli != null) // Resim mevcutsa...
            düðme.setIcon (new ImageIcon (resimYureli, alternatifMetin));
        else {// Resim namevcutsa...
            düðme.setText (alternatifMetin);
            System.err.println ("[" + resimDosyasýYolu + "] adlý resim dosyasý bulunamadý!");
        } // else kararý sonu...

        return düðme;
    } // ikonluDüðmeyiYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        String rapor = null;

        // Herbir duyarlý komponenti tek tek yönetelim...
        if (ÖNCE.equals (komut)) // Ýlk ikonlu düðme týklanmýþsa...
            rapor = "sizi bir önceki <þeye> gönderecekti.";
        else if (YUKARI.equals (komut)) // Ýkinci ikonlu düðme týklanmýþsa...
            rapor = "sizi bir üst seviyedeki <þeye> gönderecekti.";
        else if (SONRA.equals (komut)) // Üçüncü ikonlu düðme týklanmýþsa...
            rapor = "sizi bir sonraki <þeye> gönderecekti.";
        else if (YENÝ_BUTON.equals (komut)) // Dördüncü ikonsuz düðme týklanmýþsa...
            rapor = "bu düðmeye duyarlý herhangi bir þey yapabilecekti.";
        else if (GÝRÝLEN_SATIR.equals (komut)) {// Beþinci metin satýrý komponenti Enter'lanmýþsa...
            JTextField metinSatýrý = (JTextField)olay.getSource();
            String girilenMetin = metinSatýrý.getText();
            metinSatýrý.setText ("");
            rapor = "girilen aþaðýdaki metni herhangi bir þey için kullanabilecekti: "
                    + yeniSatýr + "-->\"" + girilenMetin + "\"";
        } // else kararý sonu...

        sonucuRaporla ("Eðer bu gerçek bir uygulama olsaydý, " + rapor);
    } // actionPerformed(..) hazýr metodu sonu...

    protected void sonucuRaporla (String aksiyonRaporu) {
        raporMetinAlaný.append (aksiyonRaporu + yeniSatýr);
        raporMetinAlaný.setCaretPosition (raporMetinAlaný.getDocument().getLength());
    } // sonucuRaporla(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Alet Çubuðu Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_77()); // Kurucuyu çaðýrýr...
        çerçeve.setLocation (450,100);
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
} // J5c_77 sýnýfý sonu...