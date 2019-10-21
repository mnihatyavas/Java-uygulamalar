// J5c_76.java: ToolBarDemo (Alet�ubu�uG�sterisi) �rne�i.

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

/* Gereken resim dosyalar�:
 * resim/Back24.gif: Geri
 * resim/Forward24.gif: �leri
 * resim/Up24.gif: Yukar�
 */
public class J5c_76 extends JPanel implements ActionListener {
    protected JTextArea metinAlan�;
    protected String yeniSat�r = "\n";
    static final private String �NCEK� = "�nceki";
    static final private String YUKARI = "yukar�";
    static final private String SONRAK� = "sonraki";

    public J5c_76() {// Kurucu...
        super (new BorderLayout());

        // Alet �ubu�unu yaratal�m...
        JToolBar alet�ubu�u = new JToolBar ("Alet �ubu�u");
        alet�ubu�u.setBackground (new Color ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) )); // T�m renkler...
        butonlar�Alet�ubu�unaEkle (alet�ubu�u);

        // Alet�ubu�u d��meleri t�klan�nca raporlar�n yaz�laca�� 5 sat�r, 30 kolonluk
        // m�dahalesiz kayd�rma �ubuklu metin alan�n� yarat�p i�erik panosuna ekleyelim...
        metinAlan� = new JTextArea (5, 30);
        metinAlan�.setEditable (false);
        metinAlan�.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
        metinAlan�.setForeground (Color.WHITE);

        JScrollPane alanKayd�rmaPanosu = new JScrollPane (metinAlan�);
        setPreferredSize (new Dimension (470, 130)); // ��erik panosu tercihi boyutu...
        add (alet�ubu�u, BorderLayout.PAGE_START);
        add (alanKayd�rmaPanosu, BorderLayout.CENTER);
    } // J5c_77() kuruusu sonu...

    protected void butonlar�Alet�ubu�unaEkle (JToolBar alet�ubu�u) {
        JButton d��me = null;

        // �lk buton...
        d��me = buton�konunuYarat (
                "Back24", // Resim dosyas� ad�...
                �NCEK�, // Aksiyon komutu...
                "Bir �ncekine y�nlendirir", // Alet ucu ipucu...
                "Bir �nceki"); // Alt-ernatif resim (namevcutsa alt) yaz�s�...
        alet�ubu�u.add (d��me);

        // �kinci buton...
        d��me = buton�konunuYarat (
                "Up24",
                YUKARI,
                "Bir �st seviyedekine y�nlendirir",
                "Bir �st Seviye");
        alet�ubu�u.add (d��me);

        // ���nc� buton...
        d��me = buton�konunuYarat (
                "Forward24",
                SONRAK�,
                "Bir sonrakine y�nlendirir",
                "Bir Sonraki");
        alet�ubu�u.add (d��me);
    } // butonlar�Alet�ubu�unaEkle(..) metodu sonu...

    protected JButton buton�konunuYarat (
            String resimAd�,
            String aksiyonKomutu,
            String alet�pucuMetni,
            String alternatifMetin) {
        // Buton resmini okuyal�m...
        String resminYolu = "resim/" + resimAd� + ".gif";
        URL resimYureli = J5c_76.class.getResource (resminYolu);

        // D��meye resim ikonunu (mevcutsa) kural�m...
        JButton d��me = new JButton();
        d��me.setActionCommand (aksiyonKomutu);
        d��me.setToolTipText (alet�pucuMetni);
        d��me.addActionListener (this); // D��meyi dinleyiciye duyarlayal�m...

        if (resimYureli != null) // Resim mevcutsa...
            d��me.setIcon (new ImageIcon (resimYureli, alternatifMetin));
        else {// Resim dosyas� bulunamazsa...
            d��me.setText (alternatifMetin);
            System.err.println ("HATA: [" + resminYolu + "] adl� resim dosyas� bulunamad�!");
        } // if-else karar� sonu...

        return d��me;
    } // buton�konunuYarat(..) metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();
        String rapor = null;

        // Herbir butonu tek tek y�netelim...
        if (�NCEK�.equals (komut)) // �lk d��me t�klanm��sa...
            rapor = "bir �nceki <�eye>";
        else if (YUKARI.equals(komut)) // �kinci d��me t�klanm��sa...
            rapor = "bir �st seviyedeki <�eye>";
        else if (SONRAK�.equals(komut)) // ���nc� d��me t�klanm��sa...
            rapor = "bir sonraki <�eye>";

        metinAlan�.append ("E�er bu ger�ek bir uygulama olsayd�, y�nelim: " + rapor + " olacakt�." + yeniSat�r);
        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Alet �ubu�u G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_76()); // Referanss�z s�n�f i�erik panosu nesnesi; kurucuyu �a��r�r...
        �er�eve.setLocation (500,100);
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
} // J5c_76 s�n�f� sonu...