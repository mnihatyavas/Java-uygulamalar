// J5c_73.java: TextFieldDemo (MetinSatýrýGösterisi) örneði.

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

// Gereken metin dosyasý: metinAlanýÝçeriði.txt=mny1.txt...
public class J5c_73 extends JFrame implements DocumentListener {
    private JTextField metinSatýrý;
    private JLabel metinSatýrýEtiketi;
    private JTextArea metinAlaný;
    private JScrollPane kaydýrmaPanosu;
    private JLabel durumBildirenEtiket;

    final static Color  BULUNDU_RENGÝ = Color.LIGHT_GRAY;
    final static Color  HATA_RENGÝ = Color.PINK;
    final static String ÝPTAL_HAREKETÝ = "araþtýrmanýn-iptali";
    final Color metinSatýrýZeminRengi;
    final Highlighter ýþýldatýcý;
    final Highlighter.HighlightPainter ýþýldatanBoyayýcý;
    
    
    public J5c_73() {// Kurucu...
        komponentleriKur();

        InputStream oku = getClass().getResourceAsStream ("mny1.txt");
        try {metinAlaný.read (new InputStreamReader (oku), null);
        }catch (IOException ist) {ist.printStackTrace();}

        ýþýldatýcý = new DefaultHighlighter();
        ýþýldatanBoyayýcý = new DefaultHighlighter.DefaultHighlightPainter (BULUNDU_RENGÝ);
        metinAlaný.setHighlighter (ýþýldatýcý);

        metinSatýrýZeminRengi = metinSatýrý.getBackground();
        metinSatýrý.getDocument().addDocumentListener (this); // Metin satýrýný dinleyiciye duyarlayalým...

        InputMap giriþHaritasý = metinSatýrý.getInputMap (JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap hareketHaritasý = metinSatýrý.getActionMap();
        giriþHaritasý.put (KeyStroke.getKeyStroke ("ESCAPE"), ÝPTAL_HAREKETÝ);
        hareketHaritasý.put (ÝPTAL_HAREKETÝ, new ÝptalHareketi());
    } // J5c_73() kurucusu sonu...
    
    private void komponentleriKur() {
        setTitle ("Metin Satýrý Gösterisi");
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE

        metinSatýrýEtiketi = new JLabel();
        metinSatýrýEtiketi.setText ("Araþtýrýlacak metni girin:");
        metinSatýrý = new JTextField();
        metinAlaný = new JTextArea();
        durumBildirenEtiket = new JLabel();

        metinAlaný.setRows (5);
        metinAlaný.setColumns (20);
        metinAlaný.setLineWrap (true);
        metinAlaný.setWrapStyleWord (true);
        metinAlaný.setEditable (false); // Müdahalesiz....
        kaydýrmaPanosu = new JScrollPane (metinAlaný);

        GroupLayout grupSerilimi = new GroupLayout (getContentPane());
        getContentPane().setLayout (grupSerilimi);

        // 1. Yatay eksen için bir paralel grup yaratalým...
        ParallelGroup yatayGrup = grupSerilimi.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir sýralý, bir de paralel grup yaratalým...
        SequentialGroup ys1 = grupSerilimi.createSequentialGroup();
        ParallelGroup yp1 = grupSerilimi.createParallelGroup (GroupLayout.Alignment.TRAILING);
        // Yatay sýralý gruba boþluk ekleyelim...
        ys1.addContainerGap();
        // Kaydýrma panosunu ve durum etiketini yatay paralele ekleyelim...
        yp1.addComponent (kaydýrmaPanosu, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);
        yp1.addComponent (durumBildirenEtiket, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE);

        // Yeni bir yatay sýralý grup daha yaratýp metin satýrýný ve etiketini ekleyelim...
        SequentialGroup ys2 = grupSerilimi.createSequentialGroup();
        ys2.addComponent (metinSatýrýEtiketi);
        ys2.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        ys2.addComponent (metinSatýrý, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE);
        // Yatay sýralý 2'yi yatay paralele, onu da yatay sýralý 1'e ekleyelim...
        yp1.addGroup (ys2);
        ys1.addGroup (yp1);
        ys1.addContainerGap();
        // Yatay sýralýyý yatay gruba, onu da grup serilimine ekleyelim...
        yatayGrup.addGroup (GroupLayout.Alignment.TRAILING, ys1);
        grupSerilimi.setHorizontalGroup (yatayGrup);

        // 2. Dikey eksen için bir paralel grup yaratalým...
        ParallelGroup dikeyGrup = grupSerilimi.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir dikey sýralý grup yaratýp boþluk ekleyelim...
        SequentialGroup ds1 = grupSerilimi.createSequentialGroup();
        ds1.addContainerGap();
        // Bir dikey paralel grup yaratýp metin satýrýný ve etiketini ekleyelim...
        ParallelGroup dp1 = grupSerilimi.createParallelGroup (GroupLayout.Alignment.BASELINE);
        dp1.addComponent (metinSatýrýEtiketi);
        dp1.addComponent (metinSatýrý, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE);
        // Dikey paraleli dikey sýralýya ekleyelim...
        ds1.addGroup (dp1);
        ds1.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        // Dikey sýralýya kaydýrma panosunu ve durum etiketini ekleyelim...
        ds1.addComponent (kaydýrmaPanosu, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE);
        ds1.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        ds1.addComponent (durumBildirenEtiket);
        ds1.addContainerGap();
        // Dikey sýralýyý dikey gruba, onu da ana grup serilimine ekleyelim...
        dikeyGrup.addGroup (ds1);
        grupSerilimi.setVerticalGroup (dikeyGrup);

        setLocation (500,100);
        pack(); // Ýçerik panosu paketli/kompakt olsun...
    } // komponentleriKur() metodu sonu...

    // DocumentListener/DökümanDinleyici'nin hazýr metodlarý:
    public void insertUpdate (DocumentEvent olay) {araþtýr();}
    public void removeUpdate (DocumentEvent olay) {araþtýr();}
    public void changedUpdate (DocumentEvent olay) {}

    public void araþtýr() {
        ýþýldatýcý.removeAllHighlights();

        String araþtýrýlanÝbare = metinSatýrý.getText();
        if (araþtýrýlanÝbare.length() <= 0) {mesaj ("Araþtýrýlacak bir ibareyi henüz girmediniz!"); return;}

        String metinAlanýÝçeriði = metinAlaný.getText();
        int endeks = metinAlanýÝçeriði.indexOf (araþtýrýlanÝbare, 0);
        if (endeks >= 0) {// Eþleþme bulundu...
            try {int son = endeks + araþtýrýlanÝbare.length();
                ýþýldatýcý.addHighlight (endeks, son, ýþýldatanBoyayýcý);
                metinAlaný.setCaretPosition (son);
                metinSatýrý.setBackground (metinSatýrýZeminRengi);
                mesaj ("Araþtýrýlan ilk ["+ araþtýrýlanÝbare + "] ibaresi bulundu. Yeni bir araþtýrma için ESC'ye basýn!");
            }catch (BadLocationException ist) {ist.printStackTrace();}
        }else {metinSatýrý.setBackground (HATA_RENGÝ);
            mesaj ("Araþtýrýlan [" + araþtýrýlanÝbare + "] ibaresi bulunamadý. Yeni bir araþtýrma için ESC'ye basýn!");
        } // if-else kararý sonu...
    } // araþtýr() metodu sonu...

    void mesaj (String mesaj) {durumBildirenEtiket.setText (mesaj);}

    class ÝptalHareketi extends AbstractAction {
        public void actionPerformed (ActionEvent olay) {
            ýþýldatýcý.removeAllHighlights();
            metinSatýrý.setText ("");
            metinSatýrý.setBackground (metinSatýrýZeminRengi);
        } // actionPerformed(..) hazýr metodu sonu...
    } // ÝptalHareketi sýnýfý sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                // Koyu metalik yazý stilini kapatalým...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                // Yaratýlen referanssýz içerik panosu nesnesini gçrünür kýlalým...
                new J5c_73().setVisible (true);
            } // run() hazýr sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_73 sýnýfý sonu...