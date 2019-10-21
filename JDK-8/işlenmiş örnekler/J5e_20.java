// J5e_20.java: TextBatchPrintingDemo (MetinY���n�Yazd�rmaG�sterisi) �rne�i.

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.print.PrinterException;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import java.util.HashMap;
import java.util.Map;

import java.net.URL;
import java.net.MalformedURLException;

import java.io.IOException;

/* Gerekli html dosyalar�:
 * html/index.html
 * html/chapter1.html
 * html/chapter2.html
 * html/chapter3.html
 * html/chapter4.html
 * html/chapter5.html
 * html/chapter6.html
 * html/chapter7.html
 * html/chapter8.html
 * html/chapter9.html
 * html/chapter10.html
 * html/chapter11.html
 */

/* T�klanan b�l�m� a�ar. Alt-E ile "yazd�rma listesi"ne koyar. Alt-A ile tekrar
 * "b�l�mler listesi"ne d�ner. Alt-T ile yazd�rma listesindekilerin hepsini temizler.
 * Alt-Y ile se�ilen b�l�mleri teker teker (y���n) yazd�r�r ve listeyi temizler.
 * Alt-K ile de program� kapat�r. (K�sakesek karakterleri heryerden eri�ilebilir.)
 */
public class J5e_20 implements HyperlinkListener, ListSelectionListener {
    // Yazd�rma listesinde ve sayfa cebindeki sayfalar� temsil eden bir s�n�f...
    static class SayfaBirimi extends JEditorPane {
        String ba�l�k; // Y�klenen d�k�man�n varsa ba�l���, yoksa yureli kullan�l�r...
        public String toString() {
            if (ba�l�k == null) {
                String dizge = (String) getDocument().getProperty (Document.TitleProperty);
                ba�l�k = (dizge == null ? getPage().toString() : dizge);
            } // if karar� sonu...
            return ba�l�k;
        } // toString() haz�r metodu sonu...
    } // SayfaBirimi s�n�f� sonu...

    // S�n�f tip de�i�kenleri...
    static String varsay�l�Sayfa = "html/index.html"; // Varsay�l� sayfa ileti sat�r�ndan de�i�tirilebilir...
    static String varsay�l�Mesaj = "Select: Alt-A  Print: Alt-P  Quit: Alt-Q";
    static PrintService varsay�l�Yazd�rmaServisi = PrintServiceLookup.lookupDefaultPrintService();
    SayfaBirimi b�l�mBirimi; // Akt�el g�sterilen sayfa birimi...
    URL anaSayfa; // Sayfa taray�c� i�in ba�lang�� sayfas�...
    Map<URL, SayfaBirimi> sayfaCebi = new HashMap<URL, SayfaBirimi>(); // A��lan sayfalar�n cebi...
    JList se�ilenB�l�mler; // Yazd�r�lmak �zere se�ilen sayfalar...
    JLabel mesajEtiketi; // Bilgi ve hata mesajlar� etiketi...

    /* Program 3 b�l�mden olu�maktad�r:
     *    1: Y���n yazd�r�m
     *    2: UI kontrol rutinleri
     *    3: Ba�lang�� de�erleri ve kurulumlar
     */

    // B�l�m 1: Y���n yazd�r�m

    // Se�ilen b�l�mlerin herbirini ayr� birer sicimle yazd�r�r...
    void se�ilenB�l�mleriYazd�r() {
        DefaultListModel b�l�mler = (DefaultListModel) se�ilenB�l�mler.getModel();
        int n = b�l�mler.getSize();
        if (n < 1) {mesajEtiketi.setText ("Se�ilen b�l�m YOK!"); return;}
        if (varsay�l�Yazd�rmaServisi == null) {mesajEtiketi.setText ("Yazd�rma servisi YOK!"); return;}

        for (int i = 0; i < n; i++) {
            final SayfaBirimi b�l�m = (SayfaBirimi) b�l�mler.getElementAt (i);
            // Se�ilen aral�ktaki herbir b�l�m ayr� birer sicimle yazd�r�lacak...
            Runnable yazd�rmaG�revi = new Runnable() {
                public void run() {
                    try {b�l�m.print (// 1.false:"Yaz diyalo�u yok"; 2.false:"etkile�imsiz/y���n-kip yazd�rma"...
                            null, null, false, varsay�l�Yazd�rmaServisi, null, false);
                    }catch (PrinterException ist) {JOptionPane.showMessageDialog (null,
                            "B�l�m yazd�rma hatas� [" + b�l�m.getPage() + "]==>\n" + ist,
                            "Yazd�rma Hatas�", JOptionPane.WARNING_MESSAGE);
                    } // try-catch blo�u sonu...
                } // run() haz�r sicim metodu sonu...
            }; // Run.. ifadesi sonu...
            new Thread (yazd�rmaG�revi).start();
        } // for d�ng�s� sonu...

        b�l�mler.removeAllElements(); // Se�ilmi� olsun olmas�n hepsi temizlenir...
        mesajEtiketi.setText (n + ".b�l�m�n yazd�r�lmas� tamamland�");
    } // se�ilenB�l�mleriYazd�r() metodu sonu...

    // B�l�m 2: UI kontrol metodlar�

    // {@code HyperlinkListener} haz�r metodu...
    public void hyperlinkUpdate (HyperlinkEvent olay) {
        URL yurel = olay.getURL();
        EventType olayTipi = olay.getEventType();

        if (olayTipi == EventType.ENTERED) mesajEtiketi.setText ("Yurel'e git: [" + yurel + "]");
        else if (olayTipi == EventType.EXITED)  mesajEtiketi.setText (varsay�l�Mesaj);
        else if (olayTipi == EventType.ACTIVATED) {b�l�m�Kur (yurel); mesajEtiketi.setText (varsay�l�Mesaj);}
    } // hyperlinkUpdate(..) haz�r metodu sonu...

    // {@code ListSelectionListener} haz�r metodu...
    public void valueChanged (ListSelectionEvent olay) {
        if (!olay.getValueIsAdjusting()) {
            int endeks = ((JList)olay.getSource()).getSelectedIndex();
            if (endeks >= 0) {
                SayfaBirimi b�l�m = (SayfaBirimi)se�ilenB�l�mler.getModel().getElementAt (endeks);
                URL yurel = b�l�m.getPage();
                if (!yurel.equals (b�l�mBirimi.getPage())) b�l�m�Kur (yurel);
            } // if karar� sonu...
        } // d��-if karar� sonu...
    } // valueChanged(..) haz�r metodu sonu...

    // T�klanan (yeni) b�l�m i�eri�ini a�ar...
    void b�l�m�Kur (URL yurel) {
        SayfaBirimi b�l�m = sayfaCebi.get (yurel);
        if (b�l�m == null) {
            b�l�m = b�l�m�Yarat (yurel);
            sayfaCebi.put (yurel, b�l�m);
        } // if karar� sonu...
        if (b�l�mBirimi != null) {
            Container kab = b�l�mBirimi.getParent();
            if (kab != null) {
                kab.remove (b�l�mBirimi);
                kab.add (b�l�m);
            } // i�-if karar� sonu...
        } // d��-if karar� sonu...
        b�l�mBirimi = b�l�m;
        se�iliB�l�mleriG�ncelle();
    } // b�l�m�Kur(..) metodu sonu...

    void se�iliB�l�mleriG�ncelle() {
        ListModel b�l�mler = se�ilenB�l�mler.getModel();
        int n = b�l�mler.getSize();
        if (n > 0) {
            URL yurel = b�l�mBirimi.getPage();
            int endeks = se�ilenB�l�mler.getSelectedIndex();
            if (endeks >= 0) {
                SayfaBirimi se�ilen = (SayfaBirimi) b�l�mler.getElementAt (endeks);
                if (yurel.equals (se�ilen.getPage())) return;
            } // if karar� sonu...
            for (int i = 0; i < n; i++) {
                SayfaBirimi birim = (SayfaBirimi) b�l�mler.getElementAt (i);
                if (yurel.equals (birim.getPage())) {
                    se�ilenB�l�mler.setSelectedIndex (i);
                    return;
                } // if karar� sonu...
            } // for d�ng�s� sonu...
            se�ilenB�l�mler.clearSelection();
        } // d��-if karar� sonu...
    } // se�iliB�l�mleriG�ncelle() metodu sonu...

    // B�l�m 3: Ba�lang��lar ve kurulum

    SayfaBirimi b�l�m�Yarat (URL yurel) {
        SayfaBirimi b�l�m = new SayfaBirimi();
        b�l�m.setPreferredSize (new Dimension (800, 600));
        b�l�m.setEditable (false); // M�dahalesiz...
        b�l�m.addHyperlinkListener (this);
        try {b�l�m.setPage (yurel);
        }catch (IOException ist) {mesajEtiketi.setText ("Yurel y�kleme hatas� [" + yurel + "]: " + ist);}
        return b�l�m;
    } // b�l�m�Yarat(..) metodu sonu...

    JMenuItem men�BirimiYarat (Action hareket, int temsilci, KeyStroke k�sakesek) {
        JMenuItem birim = new JMenuItem (hareket);
        birim.setMnemonic(temsilci);
        birim.setAccelerator(k�sakesek);
        return birim;
    } // men�BirimiYarat(..) metodu sonu...

    void yaratVeG�sterGUI() {
        mesajEtiketi = new JLabel (varsay�l�Mesaj);

        se�ilenB�l�mler = new JList (new DefaultListModel());
        se�ilenB�l�mler.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        se�ilenB�l�mler.addListSelectionListener (this);

        b�l�m�Kur (anaSayfa);

        JSplitPane ay�rmaPanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane (b�l�mBirimi), new JScrollPane (se�ilenB�l�mler));

        JMenu dosyaMen�s� = new JMenu ("Dosya");
        dosyaMen�s�.setMnemonic (KeyEvent.VK_D);

        // "Sayfa Ekle" men� birimi ve klavye k�sakesek karakteri...
        dosyaMen�s�.add (men�BirimiYarat (
                new AbstractAction ("B�l�m Ekle") {
                    public void actionPerformed (ActionEvent olay) {
                        DefaultListModel b�l�m = (DefaultListModel) se�ilenB�l�mler.getModel();
                        b�l�m.addElement (b�l�mBirimi);
                        se�ilenB�l�mler.setSelectedIndex (b�l�m.getSize() - 1);
                   } // actionPerformed(..) haz�r metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_E, // Heryerden ula��labilir temsili karakter...
                KeyStroke.getKeyStroke (KeyEvent.VK_E, ActionEvent.ALT_MASK)));

        // "Se�ileni Yazd�r" men� birimi ve k�sakesek karakteri...
        dosyaMen�s�.add (men�BirimiYarat (
                new AbstractAction ("Se�ileni Yazd�r") {
                    public void actionPerformed (ActionEvent olay) {
                        se�ilenB�l�mleriYazd�r();
                    } // actionPerformed(..) haz�r metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_Y, // Heryerden karakteri...
                KeyStroke.getKeyStroke (KeyEvent.VK_Y, ActionEvent.ALT_MASK)));

        // "Listeyi Temizle" men� birimi ve k�sakesekleri...
        dosyaMen�s�.add (men�BirimiYarat (
                new AbstractAction ("Listeyi Temizle") {
                    public void actionPerformed (ActionEvent olay) {
                        DefaultListModel b�l�m = (DefaultListModel) se�ilenB�l�mler.getModel();
                        b�l�m.removeAllElements();
                    } // actionPerformed(..) haz�r metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_T, // heryerden
                KeyStroke.getKeyStroke (KeyEvent.VK_T, ActionEvent.ALT_MASK)));

        dosyaMen�s�.addSeparator(); // Men� birimlerini katagorile...

        // "Ana Sayfa" men� birimi ve k�sakese�i...
        dosyaMen�s�.add (men�BirimiYarat (
                new AbstractAction ("Ana Sayfa") {
                    public void actionPerformed (ActionEvent olay) {b�l�m�Kur (anaSayfa);}
                }, // new.. ifadesi sonu...
                KeyEvent.VK_A, // heryerden eri�im...
                KeyStroke.getKeyStroke (KeyEvent.VK_A, ActionEvent.ALT_MASK))); // Men� i�inden eri�im...

        // "Kapat" men� birimi ve k�sakese�i...
        dosyaMen�s�.add (men�BirimiYarat (
                new AbstractAction ("Kapat") {
                    public void actionPerformed (ActionEvent olay) {
                        for (Window p : Window.getWindows()) {p.dispose();}
                    } // actionPerformed(..) haz�r metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_K, // heryerden eri�im...
                KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK))); // Men�den eri�im...

        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.add (dosyaMen�s�);

        JPanel i�erikPaneli = new JPanel();
        i�erikPaneli.setLayout (new BoxLayout (i�erikPaneli, BoxLayout.Y_AXIS));
        i�erikPaneli.add (ay�rmaPanosu);
        i�erikPaneli.add (mesajEtiketi);

        JFrame �er�eve = new JFrame ("Metin Y���n� Yazd�rma G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setJMenuBar (men��ubu�u);
        �er�eve.setContentPane (i�erikPaneli);
        �er�eve.pack();
        �er�eve.setLocationRelativeTo (null); // Ekranda ortala...
        �er�eve.setVisible (true);

        if (varsay�l�Yazd�rmaServisi == null) // Yazd�rma olanaks�z, uyar� yap�lacak...
            JOptionPane.showMessageDialog (�er�eve, "Varsay�l� yazd�rma servisi YOK!",
                    "Yazd�rma Servisi �kaz�", JOptionPane.WARNING_MESSAGE);
    } // void yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        final J5e_20 g�steri = new J5e_20(); // Varsay�l� kurucuyu �a��r�r...
        g�steri.anaSayfa = g�steri.getClass().getResource (varsay�l�Sayfa);
        // �stenen �zel sayfa ad� giri� iletisinden yazd�r�labilir...
        if (args.length > 0) {String sayfaAd� = args[0];
            try {URL yurel = new URL (sayfaAd�); g�steri.anaSayfa = yurel;
            }catch (MalformedURLException ist) {System.err.println ("HATA: bozuk sayfa ad� [" + sayfaAd� + "]==> " + ist);}
        } // if karar� sonu...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                g�steri.yaratVeG�sterGUI();
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_20 s�n�f� sonu...