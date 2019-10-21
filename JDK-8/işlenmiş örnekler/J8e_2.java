// J8e_2.java: BidiTextComponentDemo (ElifBeTe/Alfabe(t)MetinDökümanýGösterisi) örneði.

import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Event;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import java.awt.font.TextAttribute;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.Action;
import javax.swing.AbstractAction;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.KeyStroke;
import javax.swing.InputMap;

import javax.swing.text.JTextComponent;
import javax.swing.text.Document;
import javax.swing.text.AbstractDocument;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.StyledEditorKit;

import javax.swing.event.UndoableEditListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;

import java.util.HashMap;
import java.util.Locale;

// Gereken dosya: J8e_2x.java = DocumentSizeFilter.java
public class J8e_2 extends JFrame {
    JTextPane metinPanosu;
    AbstractDocument döküman;
    static final int AZAMÝ_KARAKTER = 500;
    JTextArea durumAlaný;
    String yeniSatýr = "\n";

    HashMap<Object, Action> hareketlerListesi;

    protected YapýlanýiptalHareketi yapýlanýnÝptaliHareketi;
    protected TekraryapHareketi yapýlanýnTekrarýHareketi;
    protected UndoManager iptalEt = new UndoManager();

    public J8e_2() {// Kurucu...
        super ("ElifBeTe Metin Parçasý Gösterisi");

        // Üst-yarý deðiþtirilebilir metin panosu kuruluyor...
        metinPanosu = new JTextPane();
        metinPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1f) );
        // _ar_SA, Suudi Arapcasýný saðdan sola yönlendirir...
        //metinPanosu.getDocument().putProperty (TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_RTL);
        //Locale arabicSaudiArabia = new Locale.Builder().setLanguage ("ar").setRegion ("SA").build();
        //metinPanosu.setComponentOrientation (ComponentOrientation.getOrientation (arabicSaudiArabia));
        metinPanosu.setCaretPosition (0);
        metinPanosu.setMargin (new Insets (5, 5, 5, 5));
        StyledDocument stilliDöküman = metinPanosu.getStyledDocument();
        if (stilliDöküman instanceof AbstractDocument) {
            döküman = (AbstractDocument)stilliDöküman;
            döküman.setDocumentFilter (new J8e_2x (AZAMÝ_KARAKTER));
        }else {
            System.err.println ("Metin panosu dökümaný desteklenen AbstractDocument deðildir!");
            System.exit (-1);
        } // if-else kararý sonu...
        JScrollPane metinKaydýraðý = new JScrollPane (metinPanosu);
        metinKaydýraðý.setPreferredSize (new Dimension (200, 100));

        // Alt-yarý müdahalesiz durum metin-alaný kuruluyor...
        durumAlaný = new JTextArea (5, 30);
        durumAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.5f) );
        durumAlaný.setForeground (Color.WHITE);
        durumAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane durumKaydýraðý = new JScrollPane (durumAlaný);

        // Metin panosu ile durum alaný paravanlý panoyla birleþtirilecek...
        JSplitPane paravanlýPano = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT,
                metinKaydýraðý,
                durumKaydýraðý);
        paravanlýPano.setOneTouchExpandable (true); // Paravan açýlýr/kapanýr düðmeli...

        // Alt imleç konum satýrý kurulacak...
        JPanel durumSatýrý = new JPanel (new GridLayout (1, 1));
        durumSatýrý.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.4f) );

        ÝmleçDinlemeEtiketi imleçEtiketi = new ÝmleçDinlemeEtiketi ("Ýmleç Durumu");
        imleçEtiketi.setForeground (Color.YELLOW);
        durumSatýrý.add (imleçEtiketi);

        // Her iki parçayý da içerik panolu çerçeveye ekleyelim...
        getContentPane().add (paravanlýPano, BorderLayout.CENTER);
        getContentPane().add (durumSatýrý, BorderLayout.PAGE_END);

        // Metin panosunun menü çubuðunu kuralým...
        hareketlerListesi = hareketTablosunuYarat (metinPanosu);
        JMenu düzenlemeMenüsü = düzenlemeMenüsünüYarat();
        JMenu stilMenüsü = stilMenüsünüYarat();
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.add (düzenlemeMenüsü);
        menüÇubuðu.add (stilMenüsü);
        setJMenuBar (menüÇubuðu);

        // Metin panosunda yukarý/aþaðý/ileri/geri kýsayol tuþlarýný kuralým...
        hareketTuþlarý();

        // Metin panosuna ilk (deðiþtirilebilir) ingilizce-arapca metni yazalým...
        ilk_en_ar_Metni();
        metinPanosu.setCaretPosition (0);

        // Dinleyicileri (geri alýnabilirlik, döküman deðiþikliði ve imleç konumu) kuralým...
        döküman.addUndoableEditListener (new GeriAlýnabilirDinleyicim());
        döküman.addDocumentListener (new DökümanDinleyicim());
        metinPanosu.addCaretListener (imleçEtiketi);
    } // J8e_2() kurucusu sonu...

    // Ýmleç hareketleri ve seçilen metni dinler, alt imleç durum satýrýnda raporlar...
    protected class ÝmleçDinlemeEtiketi extends JLabel implements CaretListener {
        public ÝmleçDinlemeEtiketi (String etiket) {super (etiket);} // Kurucu...
        public void caretUpdate (CaretEvent olay) {konumuVeSeçileniGöster (olay.getDot(), olay.getMark());} // hazýr metod...

        protected void konumuVeSeçileniGöster (final int nokta, final int seçilen) {
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {
                    if (nokta == seçilen) {// Seçilen yoksa...
                        try {
                            Rectangle imleçKordinatý = metinPanosu.modelToView (nokta);
                            // Karakter konumu piksel görüntü koordinatýna çevrilir (0: 5-5'dir)...
                            setText ("imleç: metin konumu: " + nokta + ", piksel konumu = "
                                    + "[" + imleçKordinatý.x + ", " + imleçKordinatý.y + "]" + yeniSatýr);
                        }catch (BadLocationException ist) {setText ("imleç: piksel konumu: " + nokta + yeniSatýr);}
                    }else if (nokta < seçilen) {// Seçilen noktadan ilerdeyse...
                                   setText ("seçilen: " + nokta + "'dan " + seçilen + "'a" + yeniSatýr);
                             }else {// Seçilen noktadan gerideyse...
                                   setText ("seçilen: " + seçilen + "'den " + nokta + "'a" + yeniSatýr);
                                       } // iç-else kararý sonu
                             } // dýþ-else kararý sonu...
                } // run() hazýr ip metodu sonu...
            ); // Swi.. ifadesi sonu...
        } // konumuVeSeçileniGöster(..) özel ip metodu sonu...
    } // ÝmleçDinlemeEtiketi sýnýfý sonu...

    private HashMap<Object, Action> hareketTablosunuYarat (JTextComponent metinKomponenti) {
        HashMap<Object, Action> hareketlerListesi = new HashMap<Object, Action>();
        Action[] hareketlerListesiDizisi = metinKomponenti.getActions();
        for (int i = 0; i < hareketlerListesiDizisi.length; i++) {
            Action hareket = hareketlerListesiDizisi[i];
            hareketlerListesi.put (hareket.getValue (Action.NAME), hareket);
        } // for döngüsü sonu...
        return hareketlerListesi;
    } // hareketTablosunuYarat(..) metodu sonu...

    private Action hareketiÝsmenAl (String ad) {return hareketlerListesi.get (ad);} //getActionByName

    protected JMenu düzenlemeMenüsünüYarat() {
        JMenu menü = new JMenu ("Düzenle");

        yapýlanýnÝptaliHareketi = new YapýlanýiptalHareketi();
        menü.add (yapýlanýnÝptaliHareketi);

        yapýlanýnTekrarýHareketi = new TekraryapHareketi();
        menü.add (yapýlanýnTekrarýHareketi);

        menü.addSeparator();

        menü.add (hareketiÝsmenAl (DefaultEditorKit.cutAction));
        menü.add (hareketiÝsmenAl (DefaultEditorKit.copyAction));
        menü.add (hareketiÝsmenAl (DefaultEditorKit.pasteAction));

        menü.addSeparator();

        menü.add (hareketiÝsmenAl (DefaultEditorKit.selectAllAction));

        return menü;
    } // düzenlemeMenüsünüYarat() metodu sonu...

    class YapýlanýiptalHareketi extends AbstractAction {
        public YapýlanýiptalHareketi() {// Kurucu...
            super ("Ýptal");
            setEnabled (false); // Ýlk anda henüz yapýlan olmadýðýndan etkinsiz...
        } // YapýlanýiptalHareketi() kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {
            try {iptalEt.undo();
            }catch (CannotUndoException ist) {
                System.err.println ("Yapýlan iptal edilemiyor: " + ist);
                ist.printStackTrace();}
            iptalDurumunuGüncelle();
            yapýlanýnTekrarýHareketi.tekrarDurumunuGüncelle();
        } // actionPerformed(..) hazýr metodu sonu...

        protected void iptalDurumunuGüncelle() {
            if (iptalEt.canUndo()) {
                setEnabled (true);
                putValue(Action.NAME, iptalEt.getUndoPresentationName());
            }else {
                setEnabled (false);
                putValue (Action.NAME, "Ýptal");
            } // else kararý sonu...
        } // iptalDurumunuGüncelle() metodu sonu...
    } // YapýlanýiptalHareketi sýnýfý sonu...

    class TekraryapHareketi extends AbstractAction {
        public TekraryapHareketi() {// Kurucu...
            super ("Tekrar");
            setEnabled (false);
        } // TekraryapHareketi() kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {
            try {iptalEt.redo();
            }catch (CannotRedoException ist) {
                System.err.println ("Tekrar yapýlamýyor: " + ist);
                ist.printStackTrace();}
            tekrarDurumunuGüncelle();
            yapýlanýnÝptaliHareketi.iptalDurumunuGüncelle();
        } // actionPerformed(..) hazýr metodu sonu...

        protected void tekrarDurumunuGüncelle() {
            if (iptalEt.canRedo()) {
                setEnabled (true);
                putValue (Action.NAME, iptalEt.getRedoPresentationName());
            }else {
                setEnabled (false);
                putValue (Action.NAME, "Tekrar");
            } // else kararý sonu...
        } // tekrarDurumunuGüncelle() metodu sonu...
    } // TekraryapHareketi sýnýfý sonu...

    protected JMenu stilMenüsünüYarat() {
        JMenu menü = new JMenu ("Stil");

        Action hareket = new StyledEditorKit.BoldAction();
        hareket.putValue (Action.NAME, "Bold/Koyu");
        menü.add (hareket);

        hareket = new StyledEditorKit.ItalicAction();
        hareket.putValue (Action.NAME, "Italic/Yatýk");
        menü.add (hareket);

        hareket = new StyledEditorKit.UnderlineAction();
        hareket.putValue (Action.NAME, "Underline/Altçizgili");
        menü.add (hareket);

        menü.addSeparator();

        menü.add (new StyledEditorKit.FontSizeAction ("12", 12));
        menü.add (new StyledEditorKit.FontSizeAction ("14", 14));
        menü.add (new StyledEditorKit.FontSizeAction ("18", 18));
        menü.add (new StyledEditorKit.FontSizeAction ("25", 25));

        menü.addSeparator();

        menü.add (new StyledEditorKit.FontFamilyAction ("Serif", "Serif"));
        menü.add (new StyledEditorKit.FontFamilyAction ("SansSerif", "SansSerif"));

        menü.addSeparator();

        menü.add (new StyledEditorKit.ForegroundAction ("Kýrmýzý", Color.red));
        menü.add (new StyledEditorKit.ForegroundAction ("Yeþil", Color.green));
        menü.add (new StyledEditorKit.ForegroundAction ("Mavi", Color.blue));
        menü.add (new StyledEditorKit.ForegroundAction ("Siyah", Color.black));
        menü.add (new StyledEditorKit.ForegroundAction ("Beyaz", Color.white));
        menü.add (new StyledEditorKit.ForegroundAction ("Yellow", Color.yellow));

        return menü;
    } // stilMenüsünüYarat() metodu sonu...

    protected void hareketTuþlarý() {
        InputMap girdiHaritasý = metinPanosu.getInputMap();

        // Ctrl-b ile bir karakter geriye...
        KeyStroke tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_G, Event.CTRL_MASK);
        girdiHaritasý.put (tuþ, DefaultEditorKit.backwardAction);

        // Ctrl-I ile bir karakter ileri
        tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_I, Event.CTRL_MASK);
        girdiHaritasý.put (tuþ, DefaultEditorKit.forwardAction);

       // Ctrl-y ile bir satýr yukarý...
       tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_Y, Event.CTRL_MASK);
       girdiHaritasý.put (tuþ, DefaultEditorKit.upAction);

       // Ctrl-a ile bir satýr aþaðý...
       tuþ = KeyStroke.getKeyStroke (KeyEvent.VK_A, Event.CTRL_MASK);
       girdiHaritasý.put (tuþ, DefaultEditorKit.downAction);
    } // hareketTuþlarý() metodu sonu...

    protected void ilk_en_ar_Metni() {
        String elifBeTeMetni = "Hello World in Arabic is " +
                "\u0645\u0631\u062D\u0628\u0627\u0020\u0627\u0644\u0639\u0627\u0644\u0645." +
                yeniSatýr + "Selam dünya'nýn arapcasý: Merhaba el'Allah.";  

        String ilkDizgeler[] = {elifBeTeMetni};

        SimpleAttributeSet[] özellikler = özellikleriKur (ilkDizgeler.length);

        try {for (int i = 0; i < ilkDizgeler.length; i++) // insertString(..) hazýr metoduyla metin panosuna yazdýralým...
                döküman.insertString (
                        döküman.getLength(),
                        ilkDizgeler[i] + yeniSatýr,
                        özellikler[i]);
        }catch (BadLocationException ist) {System.err.println ("Ýlk metni yerleþtiremiyorum.");}
    } // ilk_en_ar_Metni() metodu sonu...

    protected SimpleAttributeSet[] özellikleriKur (int uzunluk) {
        SimpleAttributeSet[] özellikler = new SimpleAttributeSet[uzunluk];

        özellikler[0] = new SimpleAttributeSet();
        StyleConstants.setFontSize (özellikler[0], 18);
        StyleConstants.setFontFamily (özellikler[0], "Serif");

        return özellikler;
    } // özellikleriKur(..) metodu sonu...

    protected class GeriAlýnabilirDinleyicim implements UndoableEditListener {
        public void undoableEditHappened (UndoableEditEvent olay) {
            iptalEt.addEdit (olay.getEdit());
            yapýlanýnÝptaliHareketi.iptalDurumunuGüncelle();
            yapýlanýnTekrarýHareketi.tekrarDurumunuGüncelle();
        } // undoableEditHappened(..) hazýr metodu sonu...
    } // GeriAlýnabilirDinleyicim sýnýfý sonu...

    protected class DökümanDinleyicim implements DocumentListener {
        public void insertUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}
        public void removeUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}
        public void changedUpdate (DocumentEvent olay) {deðiþikliðiGöster (olay);}

        private void deðiþikliðiGöster (DocumentEvent olay) {
            Document döküman = olay.getDocument();
            int deðiþtirileninUzunluðu = olay.getLength();
            durumAlaný.append (olay.getType().toString() + ": " + deðiþtirileninUzunluðu
                    + " karakter" + ((deðiþtirileninUzunluðu == 1) ? ". " : "ler. ")
                    + " Metnin uzunluðu = " + döküman.getLength() + "." + yeniSatýr);
        } // deðiþikliðiGöster(..) metodu sonu...
    } // DökümanDinleyicim sýnýfý sonu...

    private static void yaratVeGösterGUI() {
        final J8e_2 çerçeve = new J8e_2(); // "extends Jframe"li sýnýf kurucusunu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.setLocation (500, 100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    UIManager.put ("swing.boldMetal", Boolean.FALSE);
                    yaratVeGösterGUI();
                } // run() hazýr ip metodu sonu...
            } // "new Runnable" ip sýnýfý referanssýz nesne yaratýmý sonu...
        ); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J8e_2 sýnýfý sonu...