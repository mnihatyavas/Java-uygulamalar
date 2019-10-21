// J5c_70.java: TextAreaDemo (MetinAlanýGösterisi) örneði.

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import javax.swing.text.BadLocationException;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J5c_70 extends JFrame implements DocumentListener {
    private JLabel etiket;
    private JScrollPane kaydýrmaPanosu;
    private JTextArea metinAlaný;

    private static final String AKSÝYON_YAP = "yap";
    private static enum Kip { SOK, TAMAMLA };
    private final List<String> kelimeler;
    private Kip kip = Kip.SOK;

    public J5c_70() {// Kurucu...
        super ("Metin Alaný Gösterisi");
        komponentleriKur();

        // Metin alaný deðiþikliðini dinleyiciye duyarlayalým...
        metinAlaný.getDocument().addDocumentListener (this);

        InputMap veriGiriþHaritasý = metinAlaný.getInputMap();
        ActionMap aksiyonHaritasý = metinAlaný.getActionMap();
        veriGiriþHaritasý.put (KeyStroke.getKeyStroke ("ENTER"), AKSÝYON_YAP);
        aksiyonHaritasý.put (AKSÝYON_YAP, new AksiyonYap());

        kelimeler = new ArrayList<String>(5);
        kelimeler.add ("kývýlcým");
        kelimeler.add ("özel");
        kelimeler.add ("dürbün");
        kelimeler.add ("muhtaþem");
        kelimeler.add ("salýným");
    } // J5c_70() kurucusu sonu...

    private void komponentleriKur() {
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        //0=..DO_NOTHING_ON_CLOSE, 1=..HIDE_ON_CLOSE, 2=..DISPOSE_ON_CLOSE
        etiket = new JLabel ("'muhteþem' veya 'Salýným' vb gibi yatay/dikey kelimeler yazmayý dene:");
        etiket.setForeground (Color.BLUE);

        metinAlaný = new JTextArea();
        metinAlaný.setColumns (20);
        metinAlaný.setLineWrap (true);
        metinAlaný.setRows (5);
        metinAlaný.setWrapStyleWord (true);
        metinAlaný.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        metinAlaný.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        kaydýrmaPanosu = new JScrollPane (metinAlaný);

        GroupLayout serilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (serilim);

        // Yatay eksen için bir paralel grup yaratalým...
        ParallelGroup yatayParalelGrup = serilim.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir ardýþýk grup ve bir de yatay grup yaratalým...
        SequentialGroup yatayArdýþýkGrup = serilim.createSequentialGroup();
        ParallelGroup yatayParalelGrup2 = serilim.createParallelGroup (GroupLayout.Alignment.TRAILING);
        // Kaydýrma panosunu  ve etiketi yatay paralel grup 2'ye ekleyelim...
        yatayParalelGrup2.addComponent (kaydýrmaPanosu, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
        yatayParalelGrup2.addComponent (etiket, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
        // Yatay ardýþýk gruba bir içerik boþluðu ekleyelim...
        yatayArdýþýkGrup.addContainerGap();
        // Yatay paralel grup 2'yi ve sonra da bir içerik boþluðunu ardýþýða ekleyelim...
        yatayArdýþýkGrup.addGroup (yatayParalelGrup2);
        yatayArdýþýkGrup.addContainerGap();
        // Yatay ardýþýðý yatay paralele ekleyelim...
        yatayParalelGrup.addGroup (Alignment.TRAILING, yatayArdýþýkGrup);
        // Sonuç yatay grubumuzu yaratalým...
        serilim.setHorizontalGroup (yatayParalelGrup);

        // Dikey eksen için bir paralel grup yaratalým...
        ParallelGroup dikeyParalelGrup = serilim.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir (dikey) ardýþýk grup yaratalým...
        SequentialGroup dikeyArdýþýkGrup = serilim.createSequentialGroup();
        // Ardýþýða boþluk ekleyelim...
        dikeyArdýþýkGrup.addContainerGap();
        // Etiketi ardýþýða ekleyelim...
        dikeyArdýþýkGrup.addComponent (etiket);
        dikeyArdýþýkGrup.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        // Ardýþýða kaydýrma panosunu ve boþluðu ekleyelim...
        dikeyArdýþýkGrup.addComponent (kaydýrmaPanosu, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
        dikeyArdýþýkGrup.addContainerGap();
        // Ardýþýðý dikey paralele ekleyelim...
        dikeyParalelGrup.addGroup (dikeyArdýþýkGrup);
        // Sonuç dikey grubumuzu yaratalým...
        serilim.setVerticalGroup (dikeyParalelGrup);
        pack();
    } // komponentleriKur() metodu sonu...

    // Döküman dinleyici hazýr metodlarý:
    public void changedUpdate (DocumentEvent aldýrma) {}
    public void removeUpdate (DocumentEvent aldýrma) { }

    public void insertUpdate (DocumentEvent olay) {
        if (olay.getLength() != 1) return;

        int konum = olay.getOffset();
        String içerik = null;
        try {içerik = metinAlaný.getText (0, konum + 1);
        }catch (BadLocationException ist) {ist.printStackTrace();}

        // Kelime baþlangýçlarýný tespit edelim...
        int k;
        for (k = konum; k >= 0; k--) if (! Character.isLetter (içerik.charAt (k))) break;

        if (konum - k < 2) // Kelime deðil (harf!)...
            return;

        String önek = içerik.substring (k + 1).toLowerCase();
        int n = Collections.binarySearch (kelimeler, önek);
        if (n < 0 && -n <= kelimeler.size()) {
            String eþleþen = kelimeler.get (-n - 1);
            if (eþleþen.startsWith (önek)) {
                // Tamamlama tespiti...
                String tamamlama = eþleþen.substring (konum - k);
                // Deðiþikliði dökümaný hala tararken yapamayýz, bu yüzden 
                // bu iþlemi sonra yapacak hatýrlatmayý býrakalým...
                SwingUtilities.invokeLater (new GöreviTamamla (tamamlama, konum + 1));
            } // iç-if kararý sonu...
        }else // Hiç eþleþme bulunamadý...
            kip = Kip.SOK;
    } // insertUpdate(..) hazýr metodu sonu...

    private class GöreviTamamla implements Runnable {
        String tamamlama;
        int konum;

        GöreviTamamla (String tamamlama, int konum) {
            this.tamamlama = tamamlama;
            this.konum = konum;
        } // GöreviTamamla(..) kurucusu sonu...

        public void run() {// Runnable sicimi otomatik koþturmasý...
            metinAlaný.insert (tamamlama, konum);
            metinAlaný.setCaretPosition (konum + tamamlama.length());
            metinAlaný.moveCaretPosition (konum);
            kip = Kip.TAMAMLA;
        } // run() hazýr sicim metodu sonu...
    } // GöreviTamamla sýnýfý sonu...

    private class AksiyonYap extends AbstractAction {
        public void actionPerformed (ActionEvent olay) {
            if (kip == Kip.TAMAMLA) {
                int konum = metinAlaný.getSelectionEnd();
                metinAlaný.insert (" ", konum);
                metinAlaný.setCaretPosition (konum + 1);
                kip = Kip.SOK;
            } else metinAlaný.replaceSelection ("\n");
        } // actionPerformed(..) hazýr metodu sonu...
    } // AksiyonYap sýnýfý sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                new J5c_70().setVisible (true); // Kurucuyu çaðýr, pencereyi göster...
            } // run() hazýr sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_70 sýnýfý sonu...