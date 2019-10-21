// J8e_2.java: BidiTextComponentDemo (ElifBeTe/Alfabe(t)MetinD�k�man�G�sterisi) �rne�i.

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
    AbstractDocument d�k�man;
    static final int AZAM�_KARAKTER = 500;
    JTextArea durumAlan�;
    String yeniSat�r = "\n";

    HashMap<Object, Action> hareketlerListesi;

    protected Yap�lan�iptalHareketi yap�lan�n�ptaliHareketi;
    protected TekraryapHareketi yap�lan�nTekrar�Hareketi;
    protected UndoManager iptalEt = new UndoManager();

    public J8e_2() {// Kurucu...
        super ("ElifBeTe Metin Par�as� G�sterisi");

        // �st-yar� de�i�tirilebilir metin panosu kuruluyor...
        metinPanosu = new JTextPane();
        metinPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1f) );
        // _ar_SA, Suudi Arapcas�n� sa�dan sola y�nlendirir...
        //metinPanosu.getDocument().putProperty (TextAttribute.RUN_DIRECTION, TextAttribute.RUN_DIRECTION_RTL);
        //Locale arabicSaudiArabia = new Locale.Builder().setLanguage ("ar").setRegion ("SA").build();
        //metinPanosu.setComponentOrientation (ComponentOrientation.getOrientation (arabicSaudiArabia));
        metinPanosu.setCaretPosition (0);
        metinPanosu.setMargin (new Insets (5, 5, 5, 5));
        StyledDocument stilliD�k�man = metinPanosu.getStyledDocument();
        if (stilliD�k�man instanceof AbstractDocument) {
            d�k�man = (AbstractDocument)stilliD�k�man;
            d�k�man.setDocumentFilter (new J8e_2x (AZAM�_KARAKTER));
        }else {
            System.err.println ("Metin panosu d�k�man� desteklenen AbstractDocument de�ildir!");
            System.exit (-1);
        } // if-else karar� sonu...
        JScrollPane metinKayd�ra�� = new JScrollPane (metinPanosu);
        metinKayd�ra��.setPreferredSize (new Dimension (200, 100));

        // Alt-yar� m�dahalesiz durum metin-alan� kuruluyor...
        durumAlan� = new JTextArea (5, 30);
        durumAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.5f) );
        durumAlan�.setForeground (Color.WHITE);
        durumAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane durumKayd�ra�� = new JScrollPane (durumAlan�);

        // Metin panosu ile durum alan� paravanl� panoyla birle�tirilecek...
        JSplitPane paravanl�Pano = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT,
                metinKayd�ra��,
                durumKayd�ra��);
        paravanl�Pano.setOneTouchExpandable (true); // Paravan a��l�r/kapan�r d��meli...

        // Alt imle� konum sat�r� kurulacak...
        JPanel durumSat�r� = new JPanel (new GridLayout (1, 1));
        durumSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.4f) );

        �mle�DinlemeEtiketi imle�Etiketi = new �mle�DinlemeEtiketi ("�mle� Durumu");
        imle�Etiketi.setForeground (Color.YELLOW);
        durumSat�r�.add (imle�Etiketi);

        // Her iki par�ay� da i�erik panolu �er�eveye ekleyelim...
        getContentPane().add (paravanl�Pano, BorderLayout.CENTER);
        getContentPane().add (durumSat�r�, BorderLayout.PAGE_END);

        // Metin panosunun men� �ubu�unu kural�m...
        hareketlerListesi = hareketTablosunuYarat (metinPanosu);
        JMenu d�zenlemeMen�s� = d�zenlemeMen�s�n�Yarat();
        JMenu stilMen�s� = stilMen�s�n�Yarat();
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.add (d�zenlemeMen�s�);
        men��ubu�u.add (stilMen�s�);
        setJMenuBar (men��ubu�u);

        // Metin panosunda yukar�/a�a��/ileri/geri k�sayol tu�lar�n� kural�m...
        hareketTu�lar�();

        // Metin panosuna ilk (de�i�tirilebilir) ingilizce-arapca metni yazal�m...
        ilk_en_ar_Metni();
        metinPanosu.setCaretPosition (0);

        // Dinleyicileri (geri al�nabilirlik, d�k�man de�i�ikli�i ve imle� konumu) kural�m...
        d�k�man.addUndoableEditListener (new GeriAl�nabilirDinleyicim());
        d�k�man.addDocumentListener (new D�k�manDinleyicim());
        metinPanosu.addCaretListener (imle�Etiketi);
    } // J8e_2() kurucusu sonu...

    // �mle� hareketleri ve se�ilen metni dinler, alt imle� durum sat�r�nda raporlar...
    protected class �mle�DinlemeEtiketi extends JLabel implements CaretListener {
        public �mle�DinlemeEtiketi (String etiket) {super (etiket);} // Kurucu...
        public void caretUpdate (CaretEvent olay) {konumuVeSe�ileniG�ster (olay.getDot(), olay.getMark());} // haz�r metod...

        protected void konumuVeSe�ileniG�ster (final int nokta, final int se�ilen) {
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {
                    if (nokta == se�ilen) {// Se�ilen yoksa...
                        try {
                            Rectangle imle�Kordinat� = metinPanosu.modelToView (nokta);
                            // Karakter konumu piksel g�r�nt� koordinat�na �evrilir (0: 5-5'dir)...
                            setText ("imle�: metin konumu: " + nokta + ", piksel konumu = "
                                    + "[" + imle�Kordinat�.x + ", " + imle�Kordinat�.y + "]" + yeniSat�r);
                        }catch (BadLocationException ist) {setText ("imle�: piksel konumu: " + nokta + yeniSat�r);}
                    }else if (nokta < se�ilen) {// Se�ilen noktadan ilerdeyse...
                                   setText ("se�ilen: " + nokta + "'dan " + se�ilen + "'a" + yeniSat�r);
                             }else {// Se�ilen noktadan gerideyse...
                                   setText ("se�ilen: " + se�ilen + "'den " + nokta + "'a" + yeniSat�r);
                                       } // i�-else karar� sonu
                             } // d��-else karar� sonu...
                } // run() haz�r ip metodu sonu...
            ); // Swi.. ifadesi sonu...
        } // konumuVeSe�ileniG�ster(..) �zel ip metodu sonu...
    } // �mle�DinlemeEtiketi s�n�f� sonu...

    private HashMap<Object, Action> hareketTablosunuYarat (JTextComponent metinKomponenti) {
        HashMap<Object, Action> hareketlerListesi = new HashMap<Object, Action>();
        Action[] hareketlerListesiDizisi = metinKomponenti.getActions();
        for (int i = 0; i < hareketlerListesiDizisi.length; i++) {
            Action hareket = hareketlerListesiDizisi[i];
            hareketlerListesi.put (hareket.getValue (Action.NAME), hareket);
        } // for d�ng�s� sonu...
        return hareketlerListesi;
    } // hareketTablosunuYarat(..) metodu sonu...

    private Action hareketi�smenAl (String ad) {return hareketlerListesi.get (ad);} //getActionByName

    protected JMenu d�zenlemeMen�s�n�Yarat() {
        JMenu men� = new JMenu ("D�zenle");

        yap�lan�n�ptaliHareketi = new Yap�lan�iptalHareketi();
        men�.add (yap�lan�n�ptaliHareketi);

        yap�lan�nTekrar�Hareketi = new TekraryapHareketi();
        men�.add (yap�lan�nTekrar�Hareketi);

        men�.addSeparator();

        men�.add (hareketi�smenAl (DefaultEditorKit.cutAction));
        men�.add (hareketi�smenAl (DefaultEditorKit.copyAction));
        men�.add (hareketi�smenAl (DefaultEditorKit.pasteAction));

        men�.addSeparator();

        men�.add (hareketi�smenAl (DefaultEditorKit.selectAllAction));

        return men�;
    } // d�zenlemeMen�s�n�Yarat() metodu sonu...

    class Yap�lan�iptalHareketi extends AbstractAction {
        public Yap�lan�iptalHareketi() {// Kurucu...
            super ("�ptal");
            setEnabled (false); // �lk anda hen�z yap�lan olmad���ndan etkinsiz...
        } // Yap�lan�iptalHareketi() kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {
            try {iptalEt.undo();
            }catch (CannotUndoException ist) {
                System.err.println ("Yap�lan iptal edilemiyor: " + ist);
                ist.printStackTrace();}
            iptalDurumunuG�ncelle();
            yap�lan�nTekrar�Hareketi.tekrarDurumunuG�ncelle();
        } // actionPerformed(..) haz�r metodu sonu...

        protected void iptalDurumunuG�ncelle() {
            if (iptalEt.canUndo()) {
                setEnabled (true);
                putValue(Action.NAME, iptalEt.getUndoPresentationName());
            }else {
                setEnabled (false);
                putValue (Action.NAME, "�ptal");
            } // else karar� sonu...
        } // iptalDurumunuG�ncelle() metodu sonu...
    } // Yap�lan�iptalHareketi s�n�f� sonu...

    class TekraryapHareketi extends AbstractAction {
        public TekraryapHareketi() {// Kurucu...
            super ("Tekrar");
            setEnabled (false);
        } // TekraryapHareketi() kurucusu sonu...

        public void actionPerformed (ActionEvent olay) {
            try {iptalEt.redo();
            }catch (CannotRedoException ist) {
                System.err.println ("Tekrar yap�lam�yor: " + ist);
                ist.printStackTrace();}
            tekrarDurumunuG�ncelle();
            yap�lan�n�ptaliHareketi.iptalDurumunuG�ncelle();
        } // actionPerformed(..) haz�r metodu sonu...

        protected void tekrarDurumunuG�ncelle() {
            if (iptalEt.canRedo()) {
                setEnabled (true);
                putValue (Action.NAME, iptalEt.getRedoPresentationName());
            }else {
                setEnabled (false);
                putValue (Action.NAME, "Tekrar");
            } // else karar� sonu...
        } // tekrarDurumunuG�ncelle() metodu sonu...
    } // TekraryapHareketi s�n�f� sonu...

    protected JMenu stilMen�s�n�Yarat() {
        JMenu men� = new JMenu ("Stil");

        Action hareket = new StyledEditorKit.BoldAction();
        hareket.putValue (Action.NAME, "Bold/Koyu");
        men�.add (hareket);

        hareket = new StyledEditorKit.ItalicAction();
        hareket.putValue (Action.NAME, "Italic/Yat�k");
        men�.add (hareket);

        hareket = new StyledEditorKit.UnderlineAction();
        hareket.putValue (Action.NAME, "Underline/Alt�izgili");
        men�.add (hareket);

        men�.addSeparator();

        men�.add (new StyledEditorKit.FontSizeAction ("12", 12));
        men�.add (new StyledEditorKit.FontSizeAction ("14", 14));
        men�.add (new StyledEditorKit.FontSizeAction ("18", 18));
        men�.add (new StyledEditorKit.FontSizeAction ("25", 25));

        men�.addSeparator();

        men�.add (new StyledEditorKit.FontFamilyAction ("Serif", "Serif"));
        men�.add (new StyledEditorKit.FontFamilyAction ("SansSerif", "SansSerif"));

        men�.addSeparator();

        men�.add (new StyledEditorKit.ForegroundAction ("K�rm�z�", Color.red));
        men�.add (new StyledEditorKit.ForegroundAction ("Ye�il", Color.green));
        men�.add (new StyledEditorKit.ForegroundAction ("Mavi", Color.blue));
        men�.add (new StyledEditorKit.ForegroundAction ("Siyah", Color.black));
        men�.add (new StyledEditorKit.ForegroundAction ("Beyaz", Color.white));
        men�.add (new StyledEditorKit.ForegroundAction ("Yellow", Color.yellow));

        return men�;
    } // stilMen�s�n�Yarat() metodu sonu...

    protected void hareketTu�lar�() {
        InputMap girdiHaritas� = metinPanosu.getInputMap();

        // Ctrl-b ile bir karakter geriye...
        KeyStroke tu� = KeyStroke.getKeyStroke (KeyEvent.VK_G, Event.CTRL_MASK);
        girdiHaritas�.put (tu�, DefaultEditorKit.backwardAction);

        // Ctrl-I ile bir karakter ileri
        tu� = KeyStroke.getKeyStroke (KeyEvent.VK_I, Event.CTRL_MASK);
        girdiHaritas�.put (tu�, DefaultEditorKit.forwardAction);

       // Ctrl-y ile bir sat�r yukar�...
       tu� = KeyStroke.getKeyStroke (KeyEvent.VK_Y, Event.CTRL_MASK);
       girdiHaritas�.put (tu�, DefaultEditorKit.upAction);

       // Ctrl-a ile bir sat�r a�a��...
       tu� = KeyStroke.getKeyStroke (KeyEvent.VK_A, Event.CTRL_MASK);
       girdiHaritas�.put (tu�, DefaultEditorKit.downAction);
    } // hareketTu�lar�() metodu sonu...

    protected void ilk_en_ar_Metni() {
        String elifBeTeMetni = "Hello World in Arabic is " +
                "\u0645\u0631\u062D\u0628\u0627\u0020\u0627\u0644\u0639\u0627\u0644\u0645." +
                yeniSat�r + "Selam d�nya'n�n arapcas�: Merhaba el'Allah.";  

        String ilkDizgeler[] = {elifBeTeMetni};

        SimpleAttributeSet[] �zellikler = �zellikleriKur (ilkDizgeler.length);

        try {for (int i = 0; i < ilkDizgeler.length; i++) // insertString(..) haz�r metoduyla metin panosuna yazd�ral�m...
                d�k�man.insertString (
                        d�k�man.getLength(),
                        ilkDizgeler[i] + yeniSat�r,
                        �zellikler[i]);
        }catch (BadLocationException ist) {System.err.println ("�lk metni yerle�tiremiyorum.");}
    } // ilk_en_ar_Metni() metodu sonu...

    protected SimpleAttributeSet[] �zellikleriKur (int uzunluk) {
        SimpleAttributeSet[] �zellikler = new SimpleAttributeSet[uzunluk];

        �zellikler[0] = new SimpleAttributeSet();
        StyleConstants.setFontSize (�zellikler[0], 18);
        StyleConstants.setFontFamily (�zellikler[0], "Serif");

        return �zellikler;
    } // �zellikleriKur(..) metodu sonu...

    protected class GeriAl�nabilirDinleyicim implements UndoableEditListener {
        public void undoableEditHappened (UndoableEditEvent olay) {
            iptalEt.addEdit (olay.getEdit());
            yap�lan�n�ptaliHareketi.iptalDurumunuG�ncelle();
            yap�lan�nTekrar�Hareketi.tekrarDurumunuG�ncelle();
        } // undoableEditHappened(..) haz�r metodu sonu...
    } // GeriAl�nabilirDinleyicim s�n�f� sonu...

    protected class D�k�manDinleyicim implements DocumentListener {
        public void insertUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}
        public void removeUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}
        public void changedUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}

        private void de�i�ikli�iG�ster (DocumentEvent olay) {
            Document d�k�man = olay.getDocument();
            int de�i�tirileninUzunlu�u = olay.getLength();
            durumAlan�.append (olay.getType().toString() + ": " + de�i�tirileninUzunlu�u
                    + " karakter" + ((de�i�tirileninUzunlu�u == 1) ? ". " : "ler. ")
                    + " Metnin uzunlu�u = " + d�k�man.getLength() + "." + yeniSat�r);
        } // de�i�ikli�iG�ster(..) metodu sonu...
    } // D�k�manDinleyicim s�n�f� sonu...

    private static void yaratVeG�sterGUI() {
        final J8e_2 �er�eve = new J8e_2(); // "extends Jframe"li s�n�f kurucusunu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocation (500, 100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    UIManager.put ("swing.boldMetal", Boolean.FALSE);
                    yaratVeG�sterGUI();
                } // run() haz�r ip metodu sonu...
            } // "new Runnable" ip s�n�f� referanss�z nesne yarat�m� sonu...
        ); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J8e_2 s�n�f� sonu...