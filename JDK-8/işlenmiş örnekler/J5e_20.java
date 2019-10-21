// J5e_20.java: TextBatchPrintingDemo (MetinYýðýnýYazdýrmaGösterisi) örneði.

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

/* Gerekli html dosyalarý:
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

/* Týklanan bölümü açar. Alt-E ile "yazdýrma listesi"ne koyar. Alt-A ile tekrar
 * "bölümler listesi"ne döner. Alt-T ile yazdýrma listesindekilerin hepsini temizler.
 * Alt-Y ile seçilen bölümleri teker teker (yýðýn) yazdýrýr ve listeyi temizler.
 * Alt-K ile de programý kapatýr. (Kýsakesek karakterleri heryerden eriþilebilir.)
 */
public class J5e_20 implements HyperlinkListener, ListSelectionListener {
    // Yazdýrma listesinde ve sayfa cebindeki sayfalarý temsil eden bir sýnýf...
    static class SayfaBirimi extends JEditorPane {
        String baþlýk; // Yüklenen dökümanýn varsa baþlýðý, yoksa yureli kullanýlýr...
        public String toString() {
            if (baþlýk == null) {
                String dizge = (String) getDocument().getProperty (Document.TitleProperty);
                baþlýk = (dizge == null ? getPage().toString() : dizge);
            } // if kararý sonu...
            return baþlýk;
        } // toString() hazýr metodu sonu...
    } // SayfaBirimi sýnýfý sonu...

    // Sýnýf tip deðiþkenleri...
    static String varsayýlýSayfa = "html/index.html"; // Varsayýlý sayfa ileti satýrýndan deðiþtirilebilir...
    static String varsayýlýMesaj = "Select: Alt-A  Print: Alt-P  Quit: Alt-Q";
    static PrintService varsayýlýYazdýrmaServisi = PrintServiceLookup.lookupDefaultPrintService();
    SayfaBirimi bölümBirimi; // Aktüel gösterilen sayfa birimi...
    URL anaSayfa; // Sayfa tarayýcý için baþlangýç sayfasý...
    Map<URL, SayfaBirimi> sayfaCebi = new HashMap<URL, SayfaBirimi>(); // Açýlan sayfalarýn cebi...
    JList seçilenBölümler; // Yazdýrýlmak üzere seçilen sayfalar...
    JLabel mesajEtiketi; // Bilgi ve hata mesajlarý etiketi...

    /* Program 3 bölümden oluþmaktadýr:
     *    1: Yýðýn yazdýrým
     *    2: UI kontrol rutinleri
     *    3: Baþlangýç deðerleri ve kurulumlar
     */

    // Bölüm 1: Yýðýn yazdýrým

    // Seçilen bölümlerin herbirini ayrý birer sicimle yazdýrýr...
    void seçilenBölümleriYazdýr() {
        DefaultListModel bölümler = (DefaultListModel) seçilenBölümler.getModel();
        int n = bölümler.getSize();
        if (n < 1) {mesajEtiketi.setText ("Seçilen bölüm YOK!"); return;}
        if (varsayýlýYazdýrmaServisi == null) {mesajEtiketi.setText ("Yazdýrma servisi YOK!"); return;}

        for (int i = 0; i < n; i++) {
            final SayfaBirimi bölüm = (SayfaBirimi) bölümler.getElementAt (i);
            // Seçilen aralýktaki herbir bölüm ayrý birer sicimle yazdýrýlacak...
            Runnable yazdýrmaGörevi = new Runnable() {
                public void run() {
                    try {bölüm.print (// 1.false:"Yaz diyaloðu yok"; 2.false:"etkileþimsiz/yýðýn-kip yazdýrma"...
                            null, null, false, varsayýlýYazdýrmaServisi, null, false);
                    }catch (PrinterException ist) {JOptionPane.showMessageDialog (null,
                            "Bölüm yazdýrma hatasý [" + bölüm.getPage() + "]==>\n" + ist,
                            "Yazdýrma Hatasý", JOptionPane.WARNING_MESSAGE);
                    } // try-catch bloðu sonu...
                } // run() hazýr sicim metodu sonu...
            }; // Run.. ifadesi sonu...
            new Thread (yazdýrmaGörevi).start();
        } // for döngüsü sonu...

        bölümler.removeAllElements(); // Seçilmiþ olsun olmasýn hepsi temizlenir...
        mesajEtiketi.setText (n + ".bölümün yazdýrýlmasý tamamlandý");
    } // seçilenBölümleriYazdýr() metodu sonu...

    // Bölüm 2: UI kontrol metodlarý

    // {@code HyperlinkListener} hazýr metodu...
    public void hyperlinkUpdate (HyperlinkEvent olay) {
        URL yurel = olay.getURL();
        EventType olayTipi = olay.getEventType();

        if (olayTipi == EventType.ENTERED) mesajEtiketi.setText ("Yurel'e git: [" + yurel + "]");
        else if (olayTipi == EventType.EXITED)  mesajEtiketi.setText (varsayýlýMesaj);
        else if (olayTipi == EventType.ACTIVATED) {bölümüKur (yurel); mesajEtiketi.setText (varsayýlýMesaj);}
    } // hyperlinkUpdate(..) hazýr metodu sonu...

    // {@code ListSelectionListener} hazýr metodu...
    public void valueChanged (ListSelectionEvent olay) {
        if (!olay.getValueIsAdjusting()) {
            int endeks = ((JList)olay.getSource()).getSelectedIndex();
            if (endeks >= 0) {
                SayfaBirimi bölüm = (SayfaBirimi)seçilenBölümler.getModel().getElementAt (endeks);
                URL yurel = bölüm.getPage();
                if (!yurel.equals (bölümBirimi.getPage())) bölümüKur (yurel);
            } // if kararý sonu...
        } // dýþ-if kararý sonu...
    } // valueChanged(..) hazýr metodu sonu...

    // Týklanan (yeni) bölüm içeriðini açar...
    void bölümüKur (URL yurel) {
        SayfaBirimi bölüm = sayfaCebi.get (yurel);
        if (bölüm == null) {
            bölüm = bölümüYarat (yurel);
            sayfaCebi.put (yurel, bölüm);
        } // if kararý sonu...
        if (bölümBirimi != null) {
            Container kab = bölümBirimi.getParent();
            if (kab != null) {
                kab.remove (bölümBirimi);
                kab.add (bölüm);
            } // iç-if kararý sonu...
        } // dýþ-if kararý sonu...
        bölümBirimi = bölüm;
        seçiliBölümleriGüncelle();
    } // bölümüKur(..) metodu sonu...

    void seçiliBölümleriGüncelle() {
        ListModel bölümler = seçilenBölümler.getModel();
        int n = bölümler.getSize();
        if (n > 0) {
            URL yurel = bölümBirimi.getPage();
            int endeks = seçilenBölümler.getSelectedIndex();
            if (endeks >= 0) {
                SayfaBirimi seçilen = (SayfaBirimi) bölümler.getElementAt (endeks);
                if (yurel.equals (seçilen.getPage())) return;
            } // if kararý sonu...
            for (int i = 0; i < n; i++) {
                SayfaBirimi birim = (SayfaBirimi) bölümler.getElementAt (i);
                if (yurel.equals (birim.getPage())) {
                    seçilenBölümler.setSelectedIndex (i);
                    return;
                } // if kararý sonu...
            } // for döngüsü sonu...
            seçilenBölümler.clearSelection();
        } // dýþ-if kararý sonu...
    } // seçiliBölümleriGüncelle() metodu sonu...

    // Bölüm 3: Baþlangýçlar ve kurulum

    SayfaBirimi bölümüYarat (URL yurel) {
        SayfaBirimi bölüm = new SayfaBirimi();
        bölüm.setPreferredSize (new Dimension (800, 600));
        bölüm.setEditable (false); // Müdahalesiz...
        bölüm.addHyperlinkListener (this);
        try {bölüm.setPage (yurel);
        }catch (IOException ist) {mesajEtiketi.setText ("Yurel yükleme hatasý [" + yurel + "]: " + ist);}
        return bölüm;
    } // bölümüYarat(..) metodu sonu...

    JMenuItem menüBirimiYarat (Action hareket, int temsilci, KeyStroke kýsakesek) {
        JMenuItem birim = new JMenuItem (hareket);
        birim.setMnemonic(temsilci);
        birim.setAccelerator(kýsakesek);
        return birim;
    } // menüBirimiYarat(..) metodu sonu...

    void yaratVeGösterGUI() {
        mesajEtiketi = new JLabel (varsayýlýMesaj);

        seçilenBölümler = new JList (new DefaultListModel());
        seçilenBölümler.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        seçilenBölümler.addListSelectionListener (this);

        bölümüKur (anaSayfa);

        JSplitPane ayýrmaPanosu = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane (bölümBirimi), new JScrollPane (seçilenBölümler));

        JMenu dosyaMenüsü = new JMenu ("Dosya");
        dosyaMenüsü.setMnemonic (KeyEvent.VK_D);

        // "Sayfa Ekle" menü birimi ve klavye kýsakesek karakteri...
        dosyaMenüsü.add (menüBirimiYarat (
                new AbstractAction ("Bölüm Ekle") {
                    public void actionPerformed (ActionEvent olay) {
                        DefaultListModel bölüm = (DefaultListModel) seçilenBölümler.getModel();
                        bölüm.addElement (bölümBirimi);
                        seçilenBölümler.setSelectedIndex (bölüm.getSize() - 1);
                   } // actionPerformed(..) hazýr metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_E, // Heryerden ulaþýlabilir temsili karakter...
                KeyStroke.getKeyStroke (KeyEvent.VK_E, ActionEvent.ALT_MASK)));

        // "Seçileni Yazdýr" menü birimi ve kýsakesek karakteri...
        dosyaMenüsü.add (menüBirimiYarat (
                new AbstractAction ("Seçileni Yazdýr") {
                    public void actionPerformed (ActionEvent olay) {
                        seçilenBölümleriYazdýr();
                    } // actionPerformed(..) hazýr metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_Y, // Heryerden karakteri...
                KeyStroke.getKeyStroke (KeyEvent.VK_Y, ActionEvent.ALT_MASK)));

        // "Listeyi Temizle" menü birimi ve kýsakesekleri...
        dosyaMenüsü.add (menüBirimiYarat (
                new AbstractAction ("Listeyi Temizle") {
                    public void actionPerformed (ActionEvent olay) {
                        DefaultListModel bölüm = (DefaultListModel) seçilenBölümler.getModel();
                        bölüm.removeAllElements();
                    } // actionPerformed(..) hazýr metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_T, // heryerden
                KeyStroke.getKeyStroke (KeyEvent.VK_T, ActionEvent.ALT_MASK)));

        dosyaMenüsü.addSeparator(); // Menü birimlerini katagorile...

        // "Ana Sayfa" menü birimi ve kýsakeseði...
        dosyaMenüsü.add (menüBirimiYarat (
                new AbstractAction ("Ana Sayfa") {
                    public void actionPerformed (ActionEvent olay) {bölümüKur (anaSayfa);}
                }, // new.. ifadesi sonu...
                KeyEvent.VK_A, // heryerden eriþim...
                KeyStroke.getKeyStroke (KeyEvent.VK_A, ActionEvent.ALT_MASK))); // Menü içinden eriþim...

        // "Kapat" menü birimi ve kýsakeseði...
        dosyaMenüsü.add (menüBirimiYarat (
                new AbstractAction ("Kapat") {
                    public void actionPerformed (ActionEvent olay) {
                        for (Window p : Window.getWindows()) {p.dispose();}
                    } // actionPerformed(..) hazýr metodu sonu...
                }, // new.. ifadesi sonu...
                KeyEvent.VK_K, // heryerden eriþim...
                KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK))); // Menüden eriþim...

        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.add (dosyaMenüsü);

        JPanel içerikPaneli = new JPanel();
        içerikPaneli.setLayout (new BoxLayout (içerikPaneli, BoxLayout.Y_AXIS));
        içerikPaneli.add (ayýrmaPanosu);
        içerikPaneli.add (mesajEtiketi);

        JFrame çerçeve = new JFrame ("Metin Yýðýný Yazdýrma Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setJMenuBar (menüÇubuðu);
        çerçeve.setContentPane (içerikPaneli);
        çerçeve.pack();
        çerçeve.setLocationRelativeTo (null); // Ekranda ortala...
        çerçeve.setVisible (true);

        if (varsayýlýYazdýrmaServisi == null) // Yazdýrma olanaksýz, uyarý yapýlacak...
            JOptionPane.showMessageDialog (çerçeve, "Varsayýlý yazdýrma servisi YOK!",
                    "Yazdýrma Servisi Ýkazý", JOptionPane.WARNING_MESSAGE);
    } // void yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        final J5e_20 gösteri = new J5e_20(); // Varsayýlý kurucuyu çaðýrýr...
        gösteri.anaSayfa = gösteri.getClass().getResource (varsayýlýSayfa);
        // Ýstenen özel sayfa adý giriþ iletisinden yazdýrýlabilir...
        if (args.length > 0) {String sayfaAdý = args[0];
            try {URL yurel = new URL (sayfaAdý); gösteri.anaSayfa = yurel;
            }catch (MalformedURLException ist) {System.err.println ("HATA: bozuk sayfa adý [" + sayfaAdý + "]==> " + ist);}
        } // if kararý sonu...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                gösteri.yaratVeGösterGUI();
        }}); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_20 sýnýfý sonu...