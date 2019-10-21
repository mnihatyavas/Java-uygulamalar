// J5c_73.java: TextFieldDemo (MetinSat�r�G�sterisi) �rne�i.

import java.awt.Color;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import javax.swing.text.Highlighter;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.BadLocationException;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// Gereken metin dosyas�: metinAlan���eri�i.txt=mny1.txt...
public class J5c_73 extends JFrame implements DocumentListener {
    private JTextField metinSat�r�;
    private JLabel metinSat�r�Etiketi;
    private JTextArea metinAlan�;
    private JScrollPane kayd�rmaPanosu;
    private JLabel durumBildirenEtiket;

    final static Color  BULUNDU_RENG� = Color.LIGHT_GRAY;
    final static Color  HATA_RENG� = Color.PINK;
    final static String �PTAL_HAREKET� = "ara�t�rman�n-iptali";
    final Color metinSat�r�ZeminRengi;
    final Highlighter ���ldat�c�;
    final Highlighter.HighlightPainter ���ldatanBoyay�c�;
    
    
    public J5c_73() {// Kurucu...
        komponentleriKur();

        InputStream oku = getClass().getResourceAsStream ("mny1.txt");
        try {metinAlan�.read (new InputStreamReader (oku), null);
        }catch (IOException ist) {ist.printStackTrace();}

        ���ldat�c� = new DefaultHighlighter();
        ���ldatanBoyay�c� = new DefaultHighlighter.DefaultHighlightPainter (BULUNDU_RENG�);
        metinAlan�.setHighlighter (���ldat�c�);

        metinSat�r�ZeminRengi = metinSat�r�.getBackground();
        metinSat�r�.getDocument().addDocumentListener (this); // Metin sat�r�n� dinleyiciye duyarlayal�m...

        InputMap giri�Haritas� = metinSat�r�.getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap hareketHaritas� = metinSat�r�.getActionMap();
        giri�Haritas�.put (KeyStroke.getKeyStroke ("ESCAPE"), �PTAL_HAREKET�);
        hareketHaritas�.put (�PTAL_HAREKET�, new �ptalHareketi());
    } // J5c_73() kurucusu sonu...
    
    private void komponentleriKur() {
        setTitle ("Metin Sat�r� G�sterisi");
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE

        metinSat�r�Etiketi = new JLabel();
        metinSat�r�Etiketi.setText ("Ara�t�r�lacak metni girin:");
        metinSat�r� = new JTextField();
        metinAlan� = new JTextArea();
        durumBildirenEtiket = new JLabel();

        metinAlan�.setRows (5);
        metinAlan�.setColumns (20);
        metinAlan�.setLineWrap (true);
        metinAlan�.setWrapStyleWord (true);
        metinAlan�.setEditable (false); // M�dahalesiz....
        kayd�rmaPanosu = new JScrollPane (metinAlan�);

        GroupLayout grupSerilimi = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilimi);

        // 1. Yatay eksen i�in bir paralel grup yaratal�m...
        ParallelGroup yatayGrup = grupSerilimi.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir s�ral�, bir de paralel grup yaratal�m...
        SequentialGroup ys1 = grupSerilimi.createSequentialGroup();
        ParallelGroup yp1 = grupSerilimi.createParallelGroup (GroupLayout.Alignment.TRAILING);
        // Yatay s�ral� gruba bo�luk ekleyelim...
        ys1.addContainerGap();
        // Kayd�rma panosunu ve durum etiketini yatay paralele ekleyelim...
        yp1.addComponent (kayd�rmaPanosu, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
        yp1.addComponent (durumBildirenEtiket, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);

        // Yeni bir yatay s�ral� grup daha yarat�p metin sat�r�n� ve etiketini ekleyelim...
        SequentialGroup ys2 = grupSerilimi.createSequentialGroup();
        ys2.addComponent (metinSat�r�Etiketi);
        ys2.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        ys2.addComponent (metinSat�r�, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE);
        // Yatay s�ral� 2'yi yatay paralele, onu da yatay s�ral� 1'e ekleyelim...
        yp1.addGroup (ys2);
        ys1.addGroup (yp1);
        ys1.addContainerGap();
        // Yatay s�ral�y� yatay gruba, onu da grup serilimine ekleyelim...
        yatayGrup.addGroup (GroupLayout.Alignment.TRAILING, ys1);
        grupSerilimi.setHorizontalGroup (yatayGrup);

        // 2. Dikey eksen i�in bir paralel grup yaratal�m...
        ParallelGroup dikeyGrup = grupSerilimi.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir dikey s�ral� grup yarat�p bo�luk ekleyelim...
        SequentialGroup ds1 = grupSerilimi.createSequentialGroup();
        ds1.addContainerGap();
        // Bir dikey paralel grup yarat�p metin sat�r�n� ve etiketini ekleyelim...
        ParallelGroup dp1 = grupSerilimi.createParallelGroup (GroupLayout.Alignment.BASELINE);
        dp1.addComponent (metinSat�r�Etiketi);
        dp1.addComponent (metinSat�r�, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        // Dikey paraleli dikey s�ral�ya ekleyelim...
        ds1.addGroup (dp1);
        ds1.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        // Dikey s�ral�ya kayd�rma panosunu ve durum etiketini ekleyelim...
        ds1.addComponent (kayd�rmaPanosu, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE);
        ds1.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        ds1.addComponent (durumBildirenEtiket);
        ds1.addContainerGap();
        // Dikey s�ral�y� dikey gruba, onu da ana grup serilimine ekleyelim...
        dikeyGrup.addGroup (ds1);
        grupSerilimi.setVerticalGroup (dikeyGrup);

        setLocation (500,100);
        pack(); // ��erik panosu paketli/kompakt olsun...
    } // komponentleriKur() metodu sonu...

    // DocumentListener/D�k�manDinleyici'nin haz�r metodlar�:
    public void insertUpdate (DocumentEvent olay) {ara�t�r();}
    public void removeUpdate (DocumentEvent olay) {ara�t�r();}
    public void changedUpdate (DocumentEvent olay) {}

    public void ara�t�r() {
        ���ldat�c�.removeAllHighlights();

        String ara�t�r�lan�bare = metinSat�r�.getText();
        if (ara�t�r�lan�bare.length() <= 0) {mesaj ("Ara�t�r�lacak bir ibareyi hen�z girmediniz!"); return;}

        String metinAlan���eri�i = metinAlan�.getText();
        int endeks = metinAlan���eri�i.indexOf (ara�t�r�lan�bare, 0);
        if (endeks >= 0) {// E�le�me bulundu...
            try {int son = endeks + ara�t�r�lan�bare.length();
                ���ldat�c�.addHighlight (endeks, son, ���ldatanBoyay�c�);
                metinAlan�.setCaretPosition (son);
                metinSat�r�.setBackground (metinSat�r�ZeminRengi);
                mesaj ("Ara�t�r�lan ilk ["+ ara�t�r�lan�bare + "] ibaresi bulundu. Yeni bir ara�t�rma i�in ESC'ye bas�n!");
            }catch (BadLocationException ist) {ist.printStackTrace();}
        }else {metinSat�r�.setBackground (HATA_RENG�);
            mesaj ("Ara�t�r�lan [" + ara�t�r�lan�bare + "] ibaresi bulunamad�. Yeni bir ara�t�rma i�in ESC'ye bas�n!");
        } // if-else karar� sonu...
    } // ara�t�r() metodu sonu...

    void mesaj (String mesaj) {durumBildirenEtiket.setText (mesaj);}

    class �ptalHareketi extends AbstractAction {
        public void actionPerformed (ActionEvent olay) {
            ���ldat�c�.removeAllHighlights();
            metinSat�r�.setText ("");
            metinSat�r�.setBackground (metinSat�r�ZeminRengi);
        } // actionPerformed(..) haz�r metodu sonu...
    } // �ptalHareketi s�n�f� sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                // Koyu metalik yaz� stilini kapatal�m...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                // Yarat�len referanss�z i�erik panosu nesnesini g�r�n�r k�lal�m...
                new J5c_73().setVisible (true);
            } // run() haz�r sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_73 s�n�f� sonu...