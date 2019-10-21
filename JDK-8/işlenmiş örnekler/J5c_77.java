// J5c_77.java: ToolBarDemo2 (Alet�ubu�uG�sterisi2) �rne�i.

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

/* Bir �nceki �rnekteki alet �ubu�una, raporlayan bir buton ve bir veri sat�r� daha eklenmi�tir.
 * Gereken resim dosyalar�:
 * resim/Back24.gif
 * resim/Forward24.gif
 * resim/Up24.gif
 */
public class J5c_77 extends JPanel implements ActionListener {
    protected JTextArea raporMetinAlan�;
    protected String yeniSat�r = "\n";
    static final private String �NCE = "�nce";
    static final private String YUKARI = "yukar�";
    static final private String SONRA = "sonra";
    static final private String YEN�_BUTON = "di�er";
    static final private String G�R�LEN_SATIR = "metin";

    public J5c_77() {// Kurucu...
        super (new BorderLayout());

        // Alet �ubu�unu yaratal�m...
        JToolBar alet�ubu�u = new JToolBar ("Alet �ubu�u");
        alet�ubu�u.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...
        komponentleriEkle (alet�ubu�u);
        alet�ubu�u.setFloatable (false);
        alet�ubu�u.setRollover (true);

        // Rapor metin alan�n� yeterli ebatl� kural�m...
        raporMetinAlan� = new JTextArea (5, 30);
        raporMetinAlan�.setEditable (false); // M�dahalesiz...
        raporMetinAlan�.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        raporMetinAlan�.setForeground (Color.WHITE);

        JScrollPane alanKayd�rmaPanosu = new JScrollPane (raporMetinAlan�);

        // ��erik panosunu serimleyelim...
        setPreferredSize (new Dimension (560, 130));
        add (alet�ubu�u, BorderLayout.PAGE_START);
        add (alanKayd�rmaPanosu, BorderLayout.CENTER);
    } // J5c_77() kurucusu sonu...

    protected void komponentleriEkle (JToolBar alet�ubu�u) {
        JButton d��me = null;

        // �lk d��meyi ekleyelim...
        d��me = ikonluD��meyiYarat (
                "Back24",
                �NCE,
                "Bir �nceki �eye g�nderir",
                "Bir �nceye");
        alet�ubu�u.add (d��me);

        // �kinci d��meyi ekleyelim...
        d��me = ikonluD��meyiYarat ("Up24", YUKARI, "Bir �st seviyedeki �eye g�nderir", "Bir �ste");
        alet�ubu�u.add (d��me);

        // ���nc� d��meyi ekleyelim...
        d��me = ikonluD��meyiYarat ("Forward24", SONRA, "Bir sonraki �eye g�nderir", "Bir Sonraya");
        alet�ubu�u.add (d��me);

        // Araya bir ayra� koyal�m...
        alet�ubu�u.addSeparator();

        // Yeni d�rd�nc� d��meyi ekleyelim...
        d��me = new JButton ("�konsuz bir d��me");
        d��me.setActionCommand (YEN�_BUTON);
        d��me.setToolTipText ("Herhangi bir �ey yapar");
        d��me.addActionListener (this); // Dinleyiciye duyarlayal�m...
        alet�ubu�u.add (d��me);

        // Yeni be�inci (d��me de�il) metin sat�r� komponentini ekleyelim...
        JTextField metinSat�r� = new JTextField ("Bir Metin Sat�r�");
        metinSat�r�.setColumns (10);
        metinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // �ok a��k renkler...
        metinSat�r�.addActionListener (this); // Dinleyiciye duyarlayal�m...
        metinSat�r�.setActionCommand (G�R�LEN_SATIR);
        alet�ubu�u.add (metinSat�r�);
    } // komponentleriEkle(..) metodu sonu...

    protected JButton ikonluD��meyiYarat (
            String resminAd�,
            String aksiyonKomutu,
            String alet�pucuMetni,
            String alternatifMetin) {
        // Resim dosyas� ad�n� tamamlay�p, dosyay� bulal�m...
        String resimDosyas�Yolu = "resim/" + resminAd� + ".gif";
        URL resimYureli = J5c_77.class.getResource (resimDosyas�Yolu);

        // Resim ikonlu d��meyi yarat�p kural�m...
        JButton d��me = new JButton();
        d��me.setActionCommand (aksiyonKomutu);
        d��me.setToolTipText (alet�pucuMetni);
        d��me.addActionListener (this); // Dinleyiciye duyarlayal�m...

        if (resimYureli != null) // Resim mevcutsa...
            d��me.setIcon (new ImageIcon (resimYureli, alternatifMetin));
        else {// Resim namevcutsa...
            d��me.setText (alternatifMetin);
            System.err.println ("[" + resimDosyas�Yolu + "] adl� resim dosyas� bulunamad�!");
        } // else karar� sonu...

        return d��me;
    } // ikonluD��meyiYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        String rapor = null;

        // Herbir duyarl� komponenti tek tek y�netelim...
        if (�NCE.equals (komut)) // �lk ikonlu d��me t�klanm��sa...
            rapor = "sizi bir �nceki <�eye> g�nderecekti.";
        else if (YUKARI.equals (komut)) // �kinci ikonlu d��me t�klanm��sa...
            rapor = "sizi bir �st seviyedeki <�eye> g�nderecekti.";
        else if (SONRA.equals (komut)) // ���nc� ikonlu d��me t�klanm��sa...
            rapor = "sizi bir sonraki <�eye> g�nderecekti.";
        else if (YEN�_BUTON.equals (komut)) // D�rd�nc� ikonsuz d��me t�klanm��sa...
            rapor = "bu d��meye duyarl� herhangi bir �ey yapabilecekti.";
        else if (G�R�LEN_SATIR.equals (komut)) {// Be�inci metin sat�r� komponenti Enter'lanm��sa...
            JTextField metinSat�r� = (JTextField)olay.getSource();
            String girilenMetin = metinSat�r�.getText();
            metinSat�r�.setText ("");
            rapor = "girilen a�a��daki metni herhangi bir �ey i�in kullanabilecekti: "
                    + yeniSat�r + "-->\"" + girilenMetin + "\"";
        } // else karar� sonu...

        sonucuRaporla ("E�er bu ger�ek bir uygulama olsayd�, " + rapor);
    } // actionPerformed(..) haz�r metodu sonu...

    protected void sonucuRaporla (String aksiyonRaporu) {
        raporMetinAlan�.append (aksiyonRaporu + yeniSat�r);
        raporMetinAlan�.setCaretPosition (raporMetinAlan�.getDocument().getLength());
    } // sonucuRaporla(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Alet �ubu�u G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_77()); // Kurucuyu �a��r�r...
        �er�eve.setLocation (450,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz�l�m yok...
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_77 s�n�f� sonu...