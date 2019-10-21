// J5c_71.java: TextComponentDemo (MetinKomponentiG�sterisi) �rne�i.

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

/* Gereken java dosyas�:
 *  J5c_71x.java= DocumentSizeFilter.java
 */
public class J5c_71 extends JFrame {
    JTextPane metinPanosu;
    AbstractDocument soyutD�k�man;
    static final int G�R�LEB�LECEK_AZAM�_KARAKTER = 500;
    JTextArea metinAlan�;
    String yeniSat�r = "\n";
    HashMap<Object, Action> hareketler;

    // undo/gerial yard�mc�lar�...
    protected GerialHareketi gerialHareketi;
    protected TekraryapHareketi tekraryapHareketi;
    protected UndoManager gerialY�neticisi = new UndoManager();

    public J5c_71() {// Kurucu...
        super ("Metin Komponenti G�sterisi");

        //Metin panosunu yarat�p �ekillendirelim...
        metinPanosu = new JTextPane();
        metinPanosu.setCaretPosition (0);
        metinPanosu.setMargin (new Insets (5,5,5,5));
        StyledDocument stilliD�k�man = metinPanosu.getStyledDocument();
        if (stilliD�k�man instanceof AbstractDocument) {
            soyutD�k�man = (AbstractDocument)stilliD�k�man;
            soyutD�k�man.setDocumentFilter (new J5c_71x (G�R�LEB�LECEK_AZAM�_KARAKTER));
        }else {System.err.println ("Metin panosu d�k�man� bir AbstractDocument/SoyutD�k�man de�ildir!");
            System.exit (-1);
        } // if-else karar� sonu...
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinPanosu);
        kayd�rmaPanosu.setPreferredSize (new Dimension (300, 200));

        // D�k�man de�i�ikli�i durumlar�n� (Change/INSERT/REMOVE) raporlayan metin alan�n� yarat�p kural�m...
        metinAlan� = new JTextArea (5, 33);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane durumKayd�rmaPanosu = new JScrollPane (metinAlan�);

        // Durum ve ana metin alanlar� aras�na d��meli bir b�lme panosu koyal�m...
        JSplitPane ay�rmaPanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT,
                kayd�rmaPanosu, durumKayd�rmaPanosu);
        ay�rmaPanosu.setOneTouchExpandable (true); // Genle�me d��meli olsun...

        // �mle� ve se�me durumlar� raporlayan etiket sat�rl� imle� panosunu yarat�p kural�m...
        JPanel imle�Panosu = new JPanel (new GridLayout (1, 1));
        �mle�DinleyiciEtiket imleceDuyarl�Etiket = new �mle�DinleyiciEtiket ("�mle� Durumu");
        imle�Panosu.add (imleceDuyarl�Etiket);

        // Kurulan komponentleri i�erik panosuna ekleyelim...
        getContentPane().add (ay�rmaPanosu, BorderLayout.CENTER);
        getContentPane().add (imle�Panosu, BorderLayout.PAGE_END);

        // 2 men�l� men� �ubu�unu kural�m...
        hareketler = hareketTablosunuYarat (metinPanosu);
        JMenu d�zenleMen�s� = d�zenleMen�s�n�Yarat();
        JMenu stilleMen�s� = stilleMen�s�n�Yarat();
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.add (d�zenleMen�s�);
        men��ubu�u.add (stilleMen�s�);
        setJMenuBar (men��ubu�u);

        // Baz� Ctrl-k�sayol tu�lar� ekleyelim...
        k�sayolTu�lar�Ekle();

        // Metin panosuna ba�lang�� metnini koyal�m...
        ba�lang��D�k�man�();
        metinPanosu.setCaretPosition (0);

        // Gerial�nabilir d�zenlemeye duyarlayal�m...
        soyutD�k�man.addUndoableEditListener (new Gerial�nabilirD�zenlemeDinleyicim());
        // Metindeki imlece duyarlayal�m...
        metinPanosu.addCaretListener (imleceDuyarl�Etiket);
        // D�k�man de�i�ikliklerine duyarlayal�m...
        soyutD�k�man.addDocumentListener (new D�k�manDinleyicim());
    } // J5c_71() kurucusu sonu...

    // Metni edit/d�zenleme men�s�n� yaratal�m...
    protected JMenu d�zenleMen�s�n�Yarat() {
        JMenu d�zenleMen�s� = new JMenu ("D�zenle");

        // Gerial ve Tekraryap, kendi yaratt���m�z hareket s�n�flar�d�r...
        gerialHareketi = new GerialHareketi();
        d�zenleMen�s�.add (gerialHareketi);

        tekraryapHareketi = new TekraryapHareketi();
        d�zenleMen�s�.add (tekraryapHareketi);

        d�zenleMen�s�.addSeparator();

        // D�zenle men�s�n�n cut/kes, copy/kopyala ve paste/yap��t�r men� birimleri
        // Varsay�l� edit�r Kutusundan ismen al�n�p men�ye yap��t�r�l�r...
        d�zenleMen�s�.add (aksiyonu�smenAl (DefaultEditorKit.cutAction));
        d�zenleMen�s�.add (aksiyonu�smenAl (DefaultEditorKit.copyAction));
        d�zenleMen�s�.add (aksiyonu�smenAl (DefaultEditorKit.pasteAction));

        d�zenleMen�s�.addSeparator();

        // select-all/t�m�n�-se� men� birimi de yukardakiler gibi men�ye eklenir...
        d�zenleMen�s�.add (aksiyonu�smenAl (DefaultEditorKit.selectAllAction));
        return d�zenleMen�s�;
    } // d�zenleMen�s�n�Yarat() metodu sonu...

    class GerialHareketi extends AbstractAction {
        public GerialHareketi() {super ("Gerial"); setEnabled (false);} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            try {gerialY�neticisi.undo();
            }catch (CannotUndoException ist) {System.err.println ("Son yap�lan i�lem geri al�nam�yor: " + ist); ist.printStackTrace();}
            updateUndoState();
            tekraryapHareketi.updateRedoState();
        } // actionPerformed(..) haz�r fonksiyonu sonu...

        protected void updateUndoState() {
            if (gerialY�neticisi.canUndo()) {
                setEnabled (true);
                putValue (Action.NAME, gerialY�neticisi.getUndoPresentationName());
            }else {setEnabled (false);
                putValue (Action.NAME, "Gerial");
            } // if-else karar� sonu...
        } // updateUndoState() haz�r metodu sonu...
    } // GerialHareketi s�n�f� sonu...

    class TekraryapHareketi extends AbstractAction {
        public TekraryapHareketi() {super ("Tekraryap"); setEnabled (false);} // Kurucu...

        public void actionPerformed (ActionEvent olay) {
            try {gerialY�neticisi.redo();
            }catch (CannotRedoException ist) {System.err.println ("Son i�lem tekrar yap�lam�yor: " + ist); ist.printStackTrace();}
            updateRedoState();
            gerialHareketi.updateUndoState();
        } // actionPerformed(..) haz�r metodu sonu...

        protected void updateRedoState() {
            if (gerialY�neticisi.canRedo()) {
                setEnabled (true);
                putValue (Action.NAME, gerialY�neticisi.getRedoPresentationName());
            }else {setEnabled (false);
                putValue (Action.NAME, "Tekraryap");
            } // if-else karar� sonu...
        } // updateRedoState() haz�r s�n�f� sonu...
    } // TekraryapHareketi s�n�f� sonu...

    // Metni style/stilleme men�s�n� yaratal�m...
    protected JMenu stilleMen�s�n�Yarat() {
        JMenu stilleMen�s� = new JMenu ("Stille");

        Action men�BirimiHareketi = new StyledEditorKit.BoldAction();
        men�BirimiHareketi.putValue (Action.NAME, "Koyu");
        stilleMen�s�.add (men�BirimiHareketi);

        men�BirimiHareketi = new StyledEditorKit.ItalicAction();
        men�BirimiHareketi.putValue (Action.NAME, "Yat�k");
        stilleMen�s�.add (men�BirimiHareketi);

        men�BirimiHareketi = new StyledEditorKit.UnderlineAction();
        men�BirimiHareketi.putValue (Action.NAME, "Alt��izgili");
        stilleMen�s�.add (men�BirimiHareketi);

        stilleMen�s�.addSeparator();

        stilleMen�s�.add (new StyledEditorKit.FontSizeAction ("12", 12));
        stilleMen�s�.add (new StyledEditorKit.FontSizeAction ("14", 14));
        stilleMen�s�.add (new StyledEditorKit.FontSizeAction ("18", 18));
        stilleMen�s�.add (new StyledEditorKit.FontSizeAction ("25", 25));

        stilleMen�s�.addSeparator();

        stilleMen�s�.add (new StyledEditorKit.FontFamilyAction ("Serif", "Serif")); // K���k...
        stilleMen�s�.add (new StyledEditorKit.FontFamilyAction ("SansSerif", "SansSerif")); // Varsay�l�...

        stilleMen�s�.addSeparator();

        stilleMen�s�.add (new StyledEditorKit.ForegroundAction ("K�rm�z�", Color.red));
        stilleMen�s�.add (new StyledEditorKit.ForegroundAction ("Ye�il", Color.green));
        stilleMen�s�.add (new StyledEditorKit.ForegroundAction ("Mavi", Color.blue));
        stilleMen�s�.add (new StyledEditorKit.ForegroundAction ("Sar�", Color.yellow));
        stilleMen�s�.add (new StyledEditorKit.ForegroundAction ("Siyah", Color.black));

        return stilleMen�s�;
    } // stilleMen�s�n�Yarat() metodu sonu...

    // Metin alan�ndaki imle� hareketlerini dinler ve raporlar...
    protected class �mle�DinleyiciEtiket extends JLabel implements CaretListener {
        public �mle�DinleyiciEtiket (String etiketDizgesi) {super (etiketDizgesi);} // Kurucu...

        // CaretListener haz�r fonksiyonu...
        public void caretUpdate (CaretEvent olay) {se�ileninBilgisiniG�ster (olay.getDot(), olay.getMark());}

        //setText ve modelToView metodlar� try-catch istisna yakalayan sicim gerektiriyor...
        protected void se�ileninBilgisiniG�ster (final int imle�Konumu, final int se�meBa�lang�c�) {
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {
                    if (imle�Konumu == se�meBa�lang�c�) {// Se�ilen aral�k yok; imle� konumu raporlanacak...
                        try {Rectangle imle�Koordinatlar� = metinPanosu.modelToView (imle�Konumu);
                            // �mle� koordinatlar�n� g�r�nt� koordinatlar�na �evirelim...
                            setText ("�mle�: metinde ka��nc� krk.de: " + imle�Konumu +
                                    ", g�r�nt� (x,y) konumu=[" + imle�Koordinatlar�.x + "," +
                                    imle�Koordinatlar�.y + "]" + yeniSat�r);
                        }catch (BadLocationException ist) {setText ("�mle�: metinde ka��nc� krk.de: " + imle�Konumu + yeniSat�r);}
                    // Se�ilme yap�lm��...
                    }else if (imle�Konumu < se�meBa�lang�c�) 
                        setText ("Se�ilen metin aral���: " + imle�Konumu + "'ten " + se�meBa�lang�c� + ".krk.e kadar" + yeniSat�r);
                        else setText ("Se�ilen metin aral���: " + se�meBa�lang�c� + "'ten " + imle�Konumu + ".krk'e kadar" + yeniSat�r);
                } // run() sicim haz�r metodu sonu...
            }); // Swi.. ifadesi sonu...
        } // se�ileninBilgisiniG�ster(..) metodu sonu...
    } // �mle�DinleyiciEtiket s�n�f� sonu...

    // Gerial�nabilir d�zenlemeleri dinler...
    protected class Gerial�nabilirD�zenlemeDinleyicim implements UndoableEditListener {
        public void undoableEditHappened (UndoableEditEvent olay) {
            // De�i�ikli�i hat�rlar ve men�leri g�nceller...
            gerialY�neticisi.addEdit (olay.getEdit());
            gerialHareketi.updateUndoState();
            tekraryapHareketi.updateRedoState();
        } // undoableEditHappened(..) haz�r metodu sonu...
    } // Gerial�nabilirD�zenlemeDinleyicim s�n�f� sonu...

    // D�k�mandaki de�i�iklikleri (INSERT/REMOVE/CHANGE) raporlar...
    protected class D�k�manDinleyicim implements DocumentListener {
        public void insertUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}
        public void removeUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}
        public void changedUpdate (DocumentEvent olay) {de�i�ikli�iG�ster (olay);}

        private void de�i�ikli�iG�ster (DocumentEvent olay) {
            Document d�k�man = olay.getDocument();
            int de�i�tirilenUzunluk = olay.getLength();
            String de�i�iklikAd� = olay.getType().toString();
            if (de�i�iklikAd�.equals ("INSERT")) de�i�iklikAd� = "ARAYA G�R�LEN karakter";
            else if (de�i�iklikAd�.equals ("REMOVE")) de�i�iklikAd� = "S�L�NEN karakter";
            else if (de�i�iklikAd�.equals ("CHANGE")) de�i�iklikAd� = "DE���T�R�LEN karakter";
            metinAlan�.append (de�i�iklikAd� + ((de�i�tirilenUzunluk == 1) ? ": " : "ler: ") + de�i�tirilenUzunluk +
                "; Toplam metin uzunlu�u = " + d�k�man.getLength() + "." + yeniSat�r);
        } // de�i�ikli�iG�ster(..) metodu sonu...
    } // D�k�manDinleyicim s�n�f� sonu...

    // Metin i�inde ileri-geri-yukar�-a�a�� hereketleri k�sayol tu�lar�yla yapar...
    protected void k�sayolTu�lar�Ekle() {
        InputMap giri�Haritas� = metinPanosu.getInputMap();

        // Ctrl-l/soL ile bir karakter sola gider...
        KeyStroke tu� = KeyStroke.getKeyStroke (KeyEvent.VK_L, Event.CTRL_MASK);
        giri�Haritas�.put (tu�, DefaultEditorKit.backwardAction);

        // Ctrl-s/Sa� ile bir karakter sa�a gider...
        tu� = KeyStroke.getKeyStroke (KeyEvent.VK_S, Event.CTRL_MASK);
        giri�Haritas�.put (tu�, DefaultEditorKit.forwardAction);

        // Ctrl-y/Yukar� ile bir sat�r yukar� gider...
        tu� = KeyStroke.getKeyStroke (KeyEvent.VK_Y, Event.CTRL_MASK);
        giri�Haritas�.put (tu�, DefaultEditorKit.upAction);

        // Ctrl-a/A�a�� ile bir sat�r a�a�� gider...
        tu� = KeyStroke.getKeyStroke (KeyEvent.VK_A, Event.CTRL_MASK);
        giri�Haritas�.put (tu�, DefaultEditorKit.downAction);
    } // k�sayolTu�lar�Ekle() metodu sonu...

    protected void ba�lang��D�k�man�() {
        String ba�lang��Dizgesi[] =
                { "�mleci konumland�rmak i�in ok tu�lar�ndan ziyade fareyi kullan�n.",
                  "Metni kesmek, kopyalamak, yap��t�rmak ve se�mek i�in 'D�zenle' men�s�n� kullan�n.",
                  "Ve ayr�ca gerial ile tekraryap de�i�iklikleri i�in de.",
                  "Metin stillerini de�i�tirmek i�in 'Stille' men�s�n� kullan�n.",
                  "�mleci ilerletmek i�in 4 oku veya k�sayol tu�lar�n� kullan�n:",
                  "Ctrl-s, Ctrl-l, Ctrl-y, Ctrl-a (sa�-sol-yukar�-a�a��).",
                  "NOT: Bu metin edit�r�ne azami 500 karakter girilebilir." };

        SimpleAttributeSet[] nitelikler = ba�lang��Nitelikleri (ba�lang��Dizgesi.length);

        try {for (int i = 0; i < ba�lang��Dizgesi.length; i ++)
            soyutD�k�man.insertString (soyutD�k�man.getLength(), ba�lang��Dizgesi[i] + yeniSat�r, nitelikler[i]);
        }catch (BadLocationException ist) {System.err.println ("Ba�lang�� metnini yerle�tiremedi."); ist.printStackTrace();}
    } // ba�lang��D�k�man�() metodu sonu...

    protected SimpleAttributeSet[] ba�lang��Nitelikleri (int dizgeSay�s�) {
        // Dizgelerin teker teker niteliklerini atayal�m...
        SimpleAttributeSet[] nitelikler = new SimpleAttributeSet[dizgeSay�s�];

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
    } // ba�lang��Nitelikleri(..) metodu sonu...

    // A�a��daki 2 metod edit�r kutusunda ismen mevcut olan
    // aksiyonu (men� birimini) bulmam�z� sa�lar...
    private HashMap<Object, Action> hareketTablosunuYarat (JTextComponent metinKomponenti) {
        HashMap<Object, Action> hareketler = new HashMap<Object, Action>();
        Action[] hareketlerDizisi = metinKomponenti.getActions();
        for (int i = 0; i < hareketlerDizisi.length; i++) {
            Action aksiyon = hareketlerDizisi[i];
            hareketler.put (aksiyon.getValue (Action.NAME), aksiyon);
        } // for d�ng�s� sonu...
        return hareketler;
    } // hareketTablosunuYarat(..) metodu sonu...

    private Action aksiyonu�smenAl (String isim) {return hareketler.get (isim);}

    private static void yaratVeG�sterGUI() {
        final J5c_71 �er�eve = new J5c_71(); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setLocation (500, 100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_71 s�n�f� sonu...

/* ��kt�:
**  >java J5c_71  **
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n insertString metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n replace metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n replace metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n replace metodu i�indeyiz.
J5c_71x=DocumentSizeFilter s�n�f�n�n replace metodu i�indeyiz.
*/