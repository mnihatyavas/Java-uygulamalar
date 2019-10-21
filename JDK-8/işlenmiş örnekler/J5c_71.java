// J5c_71.java: TextComponentDemo (MetinKomponentiGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import javax.swing.InputMap;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.text.JTextComponent;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.StyledDocument;
import javax.swing.text.Document;
import javax.swing.text.AbstractDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.BadLocationException;

import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.event.UndoableEditEvent;

import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import java.util.HashMap;

/* Gereken java dosyasý:
 *  J5c_71x.java= DocumentSizeFilter.java
 */
public class J5c_71 extends JFrame {
    JTextPane metinPanosu;
    AbstractDocument soyutDöküman;
    static final int GÝRÝLEBÝLECEK_AZAMÝ_KARAKTER = 500;
    JTextArea metinAlaný;
    String yeniSatýr = "\n";
    HashMap<Object, Action> hareketler;

    // undo/gerial yardýmcýlarý...
    protected GerialHareketi gerialHareketi;
    protected TekraryapHareketi tekraryapHareketi;
    protected UndoManager gerialYöneticisi = new UndoManager();

    public J5c_71() {// Kurucu...
        super ("Metin Komponenti Gösterisi");

        //Metin panosunu yaratýp þekillendirelim...
        metinPanosu = new JTextPane();
        metinPanosu.setCaretPosition (0);
        metinPanosu.setMargin (new Insets (5,5,5,5));
        StyledDocument stilliDöküman = metinPanosu.getStyledDocument();
        if (stilliDöküman instanceof AbstractDocument) {
            soyutDöküman = (AbstractDocument)stilliDöküman;
            soyutDöküman.setDocumentFilter (new J5c_71x (GÝRÝLEBÝLECEK_AZAMÝ_KARAKTER));
        }else {System.err.println ("Metin panosu dökümaný bir AbstractDocument/SoyutDöküman deðildir!");
            System.exit (-1);
        } // if-else kararý sonu...
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinPanosu);
        kaydýrmaPanosu.setPreferredSize (new Dimension (300, 200));

        // Döküman deðiþikliði durumlarýný (Change/INSERT/REMOVE) raporlayan metin alanýný yaratýp kuralým...
        metinAlaný = new JTextArea (5, 33);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane durumKaydýrmaPanosu = new JScrollPane (metinAlaný);

        // Durum ve ana metin alanlarý arasýna düðmeli bir bölme panosu koyalým...
        JSplitPane ayýrmaPanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT,
                kaydýrmaPanosu, durumKaydýrmaPanosu);
        ayýrmaPanosu.setOneTouchExpandable (true); // Genleþme düðmeli olsun...

        // Ýmleç ve seçme durumlarý raporlayan etiket satýrlý imleç panosunu yaratýp kuralým...
        JPanel imleçPanosu = new JPanel (new GridLayout (1, 1));
        ÝmleçDinleyiciEtiket imleceDuyarlýEtiket = new ÝmleçDinleyiciEtiket ("Ýmleç Durumu");
        imleçPanosu.add (imleceDuyarlýEtiket);

        // Kurulan komponentleri içerik panosuna ekleyelim...
        getContentPane().add (ayýrmaPanosu, BorderLayout.CENTER);
        getContentPane().add (imleçPanosu, BorderLayout.PAGE_END);

        // 2 menülü menü çubuðunu kuralým...
        hareketler = hareketTablosunuYarat (metinPanosu);
        JMenu düzenleMenüsü = düzenleMenüsünüYarat();
        JMenu stilleMenüsü = stilleMenüsünüYarat();
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.add (düzenleMenüsü);
        menüÇubuðu.add (stilleMenüsü);
        setJMenuBar (menüÇubuðu);

        // Bazý Ctrl-kýsayol tuþlarý ekleyelim...
        kýsayolTuþlarýEkle();

        // Metin panosuna baþlangýç metnini koyalým...
        baþlangýçDökümaný();
        metinPanosu.setCaretPosition (0);

        // Gerialýnabilir düzenlemeye duyarlayalým...
        soyutDöküman.addUndoableEditListener (new GerialýnabilirDüzenlemeDinleyicim());
        // Metindeki imlece duyarlayalým...
        metinPanosu.addCaretListener (imleceDuyarlýEtiket);
        // Döküman deðiþikliklerine duyarlayalým...
        soyutDöküman.addDocumentListener (new DökümanDinleyicim());
    } // J5c_71() kurucusu sonu...

    // Metni edit/düzenleme menüsünü yaratalým...
    protected JMenu düzenleMenüsünüYarat() {
        JMenu düzenleMenüsü = new JMenu ("Düzenle");

        // Gerial ve Tekraryap, kendi yarattýðýmýz hareket sýnýflarýdýr...
        gerialHareketi = new GerialHareketi();
        düzenleMenüsü.add (gerialHareketi);

        tekraryapHareketi = new TekraryapHareketi();
        düzenleMenüsü.add (tekraryapHareketi);

        düzenleMenüsü.addSeparator();

        // Düzenle menüsünün cut/kes, copy/kopyala ve paste/yapýþtýr menü birimleri
        // Varsayýlý editör Kutusundan ismen alýnýp menüye yapýþtýrýlýr...
        düzenleMenüsü.add (aksiyonuÝsmenAl (DefaultEditorKit.cutAction));
        düzenleMenüsü.add (aksiyonuÝsmenAl (DefaultEditorKit.copyAction));
        düzenleMenüsü.add (aksiyonuÝsmenAl (DefaultEditorKit.pasteAction));

        düzenleMenüsü.addSeparator();

        // select-all/tümünü-seç menü birimi de yukardakiler gibi menüye eklenir...
        düzenleMenüsü.add (aksiyonuÝsmenAl (DefaultEditorKit.selectAllAction));
        return düzenleMenüsü;
    } // düzenleMenüsünüYarat() metodu sonu...

    class GerialHareketi extends AbstractAction {
        public GerialHareketi() {super ("Gerial"); setEnabled (false);} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            try {gerialYöneticisi.undo();
            }catch (CannotUndoException ist) {System.err.println ("Son yapýlan iþlem geri alýnamýyor: " + ist); ist.printStackTrace();}
            updateUndoState();
            tekraryapHareketi.updateRedoState();
        } // actionPerformed(..) hazýr fonksiyonu sonu...

        protected void updateUndoState() {
            if (gerialYöneticisi.canUndo()) {
                setEnabled (true);
                putValue (Action.NAME, gerialYöneticisi.getUndoPresentationName());
            }else {setEnabled (false);
                putValue (Action.NAME, "Gerial");
            } // if-else kararý sonu...
        } // updateUndoState() hazýr metodu sonu...
    } // GerialHareketi sýnýfý sonu...

    class TekraryapHareketi extends AbstractAction {
        public TekraryapHareketi() {super ("Tekraryap"); setEnabled (false);} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            try {gerialYöneticisi.redo();
            }catch (CannotRedoException ist) {System.err.println ("Son iþlem tekrar yapýlamýyor: " + ist); ist.printStackTrace();}
            updateRedoState();
            gerialHareketi.updateUndoState();
        } // actionPerformed(..) hazýr metodu sonu...

        protected void updateRedoState() {
            if (gerialYöneticisi.canRedo()) {
                setEnabled (true);
                putValue (Action.NAME, gerialYöneticisi.getRedoPresentationName());
            }else {setEnabled (false);
                putValue (Action.NAME, "Tekraryap");
            } // if-else kararý sonu...
        } // updateRedoState() hazýr sýnýfý sonu...
    } // TekraryapHareketi sýnýfý sonu...

    // Metni style/stilleme menüsünü yaratalým...
    protected JMenu stilleMenüsünüYarat() {
        JMenu stilleMenüsü = new JMenu ("Stille");

        Action menüBirimiHareketi = new StyledEditorKit.BoldAction();
        menüBirimiHareketi.putValue (Action.NAME, "Koyu");
        stilleMenüsü.add (menüBirimiHareketi);

        menüBirimiHareketi = new StyledEditorKit.ItalicAction();
        menüBirimiHareketi.putValue (Action.NAME, "Yatýk");
        stilleMenüsü.add (menüBirimiHareketi);

        menüBirimiHareketi = new StyledEditorKit.UnderlineAction();
        menüBirimiHareketi.putValue (Action.NAME, "Altýçizgili");
        stilleMenüsü.add (menüBirimiHareketi);

        stilleMenüsü.addSeparator();

        stilleMenüsü.add (new StyledEditorKit.FontSizeAction ("12", 12));
        stilleMenüsü.add (new StyledEditorKit.FontSizeAction ("14", 14));
        stilleMenüsü.add (new StyledEditorKit.FontSizeAction ("18", 18));
        stilleMenüsü.add (new StyledEditorKit.FontSizeAction ("25", 25));

        stilleMenüsü.addSeparator();

        stilleMenüsü.add (new StyledEditorKit.FontFamilyAction ("Serif", "Serif")); // Küçük...
        stilleMenüsü.add (new StyledEditorKit.FontFamilyAction ("SansSerif", "SansSerif")); // Varsayýlý...

        stilleMenüsü.addSeparator();

        stilleMenüsü.add (new StyledEditorKit.ForegroundAction ("Kýrmýzý", Color.red));
        stilleMenüsü.add (new StyledEditorKit.ForegroundAction ("Yeþil", Color.green));
        stilleMenüsü.add (new StyledEditorKit.ForegroundAction ("Mavi", Color.blue));
        stilleMenüsü.add (new StyledEditorKit.ForegroundAction ("Sarý", Color.yellow));
        stilleMenüsü.add (new StyledEditorKit.ForegroundAction ("Siyah", Color.black));

        return stilleMenüsü;
    } // stilleMenüsünüYarat() metodu sonu...

    // Metin alanýndaki imleç hareketlerini dinler ve raporlar...
    protected class ÝmleçDinleyiciEtiket extends JLabel implements CaretListener {
        public ÝmleçDinleyiciEtiket (String etiketDizgesi) {super (etiketDizgesi);} // Kurucu...

        // CaretListener hazýr fonksiyonu...
        public void caretUpdate (CaretEvent olay) {seçileninBilgisiniGöster (olay.getDot(), olay.getMark());}

        //setText ve modelToView metodlarý try-catch istisna yakalayan sicim gerektiriyor...
        protected void seçileninBilgisiniGöster (final int imleçKonumu, final int seçmeBaþlangýcý) {
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {
                    if (imleçKonumu == seçmeBaþlangýcý) {// Seçilen aralýk yok; imleç konumu raporlanacak...
                        try {Rectangle imleçKoordinatlarý = metinPanosu.modelToView (imleçKonumu);
                            // Ýmleç koordinatlarýný görüntü koordinatlarýna çevirelim...
                            setText ("Ýmleç: metinde kaçýncý krk.de: " + imleçKonumu +
                                    ", görüntü (x,y) konumu=[" + imleçKoordinatlarý.x + "," +
                                    imleçKoordinatlarý.y + "]" + yeniSatýr);
                        }catch (BadLocationException ist) {setText ("Ýmleç: metinde kaçýncý krk.de: " + imleçKonumu + yeniSatýr);}
                    // Seçilme yapýlmýþ...
                    }else if (imleçKonumu < seçmeBaþlangýcý) 
                        setText ("Seçilen metin aralýðý: " + imleçKonumu + "'ten " + seçmeBaþlangýcý + ".krk.e kadar" + yeniSatýr);
                        else setText ("Seçilen metin aralýðý: " + seçmeBaþlangýcý + "'ten " + imleçKonumu + ".krk'e kadar" + yeniSatýr);
                } // run() sicim hazýr metodu sonu...
            }); // Swi.. ifadesi sonu...
        } // seçileninBilgisiniGöster(..) metodu sonu...
    } // ÝmleçDinleyiciEtiket sýnýfý sonu...

    // Gerialýnabilir düzenlemeleri dinler...
    protected class GerialýnabilirDüzenlemeDinleyicim implements UndoableEditListener {
        public void undoableEditHappened (UndoableEditEvent olay) {
            // Deðiþikliði hatýrlar ve menüleri günceller...
            gerialYöneticisi.addEdit (olay.getEdit());
            gerialHareketi.updateUndoState();
            tekraryapHareketi.updateRedoState();
        } // undoableEditHappened(..) hazýr metodu sonu...
    } // GerialýnabilirDüzenlemeDinleyicim sýnýfý sonu...

    // Dökümandaki deðiþiklikleri (INSERT/REMOVE/CHANGE) raporlar...
    protected class DökümanDinleyicim implements DocumentListener {
        public void insertUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}
        public void removeUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}
        public void changedUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}

        private void deðiþikliðiGöster (DocumentEvent olay) {
            Document döküman = olay.getDocument();
            int deðiþtirilenUzunluk = olay.getLength();
            String deðiþiklikAdý = olay.getType().toString();
            if (deðiþiklikAdý.equals ("INSERT")) deðiþiklikAdý = "ARAYA GÝRÝLEN karakter";
            else if (deðiþiklikAdý.equals ("REMOVE")) deðiþiklikAdý = "SÝLÝNEN karakter";
            else if (deðiþiklikAdý.equals ("CHANGE")) deðiþiklikAdý = "DEÐÝÞTÝRÝLEN karakter";
            metinAlaný.append (deðiþiklikAdý + ((deðiþtirilenUzunluk == 1) ? ": " : "ler: ") + deðiþtirilenUzunluk +
                "; Toplam metin uzunluðu = " + döküman.getLength() + "." + yeniSatýr);
        } // deðiþikliðiGöster(..) metodu sonu...
    } // DökümanDinleyicim sýnýfý sonu...

    // Metin içinde ileri-geri-yukarý-aþaðý hereketleri kýsayol tuþlarýyla yapar...
    protected void kýsayolTuþlarýEkle() {
        InputMap giriþHaritasý = metinPanosu.getInputMap();

        // Ctrl-l/soL ile bir karakter sola gider...
        KeyStroke tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_L, Event.CTRL_MASK);
        giriþHaritasý.put (tuþ, DefaultEditorKit.backwardAction);

        // Ctrl-s/Sað ile bir karakter saða gider...
        tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_S, Event.CTRL_MASK);
        giriþHaritasý.put (tuþ, DefaultEditorKit.forwardAction);

        // Ctrl-y/Yukarý ile bir satýr yukarý gider...
        tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_Y, Event.CTRL_MASK);
        giriþHaritasý.put (tuþ, DefaultEditorKit.upAction);

        // Ctrl-a/Aþaðý ile bir satýr aþaðý gider...
        tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_A, Event.CTRL_MASK);
        giriþHaritasý.put (tuþ, DefaultEditorKit.downAction);
    } // kýsayolTuþlarýEkle() metodu sonu...

    protected void baþlangýçDökümaný() {
        String baþlangýçDizgesi[] =
                { "Ýmleci konumlandýrmak için ok tuþlarýndan ziyade fareyi kullanýn.",
                  "Metni kesmek, kopyalamak, yapýþtýrmak ve seçmek için 'Düzenle' menüsünü kullanýn.",
                  "Ve ayrýca gerial ile tekraryap deðiþiklikleri için de.",
                  "Metin stillerini deðiþtirmek için 'Stille' menüsünü kullanýn.",
                  "Ýmleci ilerletmek için 4 oku veya kýsayol tuþlarýný kullanýn:",
                  "Ctrl-s, Ctrl-l, Ctrl-y, Ctrl-a (sað-sol-yukarý-aþaðý).",
                  "NOT: Bu metin editörüne azami 500 karakter girilebilir." };

        SimpleAttributeSet[] nitelikler = baþlangýçNitelikleri (baþlangýçDizgesi.length);

        try {for (int i = 0; i < baþlangýçDizgesi.length; i ++)
            soyutDöküman.insertString (soyutDöküman.getLength(), baþlangýçDizgesi[i] + yeniSatýr, nitelikler[i]);
        }catch (BadLocationException ist) {System.err.println ("Baþlangýç metnini yerleþtiremedi."); ist.printStackTrace();}
    } // baþlangýçDökümaný() metodu sonu...

    protected SimpleAttributeSet[] baþlangýçNitelikleri (int dizgeSayýsý) {
        // Dizgelerin teker teker niteliklerini atayalým...
        SimpleAttributeSet[] nitelikler = new SimpleAttributeSet[dizgeSayýsý];

        nitelikler[0] = new SimpleAttributeSet();
        StyleConstants.setFontFamily (nitelikler[0], "SansSerif");
        StyleConstants.setFontSize (nitelikler[0], 16);

        nitelikler[1] = new SimpleAttributeSet (nitelikler[0]);
        StyleConstants.setBold (nitelikler[1], true);

        nitelikler[2] = new SimpleAttributeSet (nitelikler[0]);
        StyleConstants.setItalic (nitelikler[2], true);

        nitelikler[3] = new SimpleAttributeSet (nitelikler[0]);
        StyleConstants.setFontSize (nitelikler[3], 20);

        nitelikler[4] = new SimpleAttributeSet(nitelikler[0]);
        StyleConstants.setFontSize(nitelikler[4], 12);

        nitelikler[5] = new SimpleAttributeSet (nitelikler[0]);
        StyleConstants.setForeground (nitelikler[5], Color.red);

        nitelikler[6] = new SimpleAttributeSet (nitelikler[0]);
        StyleConstants.setItalic (nitelikler[6], true);

        return nitelikler;
    } // baþlangýçNitelikleri(..) metodu sonu...

    // Aþaðýdaki 2 metod editör kutusunda ismen mevcut olan
    // aksiyonu (menü birimini) bulmamýzý saðlar...
    private HashMap<Object, Action> hareketTablosunuYarat (JTextComponent metinKomponenti) {
        HashMap<Object, Action> hareketler = new HashMap<Object, Action>();
        Action[] hareketlerDizisi = metinKomponenti.getActions();
        for (int i = 0; i < hareketlerDizisi.length; i++) {
            Action aksiyon = hareketlerDizisi[i];
            hareketler.put (aksiyon.getValue (Action.NAME), aksiyon);
        } // for döngüsü sonu...
        return hareketler;
    } // hareketTablosunuYarat(..) metodu sonu...

    private Action aksiyonuÝsmenAl (String isim) {return hareketler.get (isim);}

    private static void yaratVeGösterGUI() {
        final J5c_71 çerçeve = new J5c_71(); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocation (500, 100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_71 sýnýfý sonu...

/* Çýktý:
**  >java J5c_71  **
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn insertString metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn replace metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn replace metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn replace metodu içindeyiz.
J5c_71x=DocumentSizeFilter sýnýfýnýn replace metodu içindeyiz.
*/